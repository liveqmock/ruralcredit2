package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.settle.CeBatchPaymentReq;
import com.creditease.rc.app.settle.CeBatchPaymentRes;
import com.creditease.rc.app.settle.CeFinalPaymentWSImplPortType;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IAccountInfoDAO;
import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IFinanceMoneyDao;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.IFinancePaymentService;
import com.creditease.rc.service.IGroupLoanRegistService;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.PayplatformParamsPropertiesUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class FinancePaymentService implements IFinancePaymentService {

	@Resource
	private CeFinalPaymentWSImplPortType ceFinalPaymentWSImplPortType;
	@Resource
	private INationalStandardCodeService nationalStandardCodeService;
	@Resource
	private IFinanceMoneyDao financeMoneyDao;
	@Resource
	private ICreditApplicationDAO creditApplicationDao;
	@Resource
	private IGroupLoanRegistService groupLoanRegistService;
	@Resource
	private IAccountInfoDAO accountInfoDAO;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private IAmountConfirmService amountConfirmService;
	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;

	private Logger log = Logger.getLogger(ProtocolManagementService.class);

	/**
	 * 组装数据，传给结算平台
	 * 
	 * @param creditApplicationId Integer
	 * @param parameter GroupLoanRegist
	 * @return Message
	 */
	@Override
	public Message financialpayment(Integer creditApplicationId, GroupLoanRegist parameter) {
		Message message = new Message();
		FinanceMoney financeMoney = new FinanceMoney();
		// 获得想结算平台传送的数据
		List<CeBatchPaymentReq> listCeBatchPaymentReq = object2CeBatchPaymentReq(creditApplicationId, financeMoney,
				parameter);
		CeBatchPaymentReq ceBatchPaymentReq = listCeBatchPaymentReq.get(0);
		log.info("***************财务付款预约入参：CeBatchPaymentReq**************");
		log.info(JsonUtil.getJson(ceBatchPaymentReq));
// 调用结算平台
		List<CeBatchPaymentRes> listCeBatchPaymentRes = ceFinalPaymentWSImplPortType
				.batchPayment(listCeBatchPaymentReq);
		OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
		operateLogBusinessStruct.setAccountNo(listCeBatchPaymentReq.get(0).getAccountNo());
		operateLogBusinessStruct.setAccountName(listCeBatchPaymentReq.get(0).getAccountName());
		operateLogBusinessStruct.setIdCard(financeMoney.getIdCard());
		operateLogBusinessStruct.setSubscribeTime(DateUtil.dateConvertString(financeMoney.getReserveTime()));
		operateLogBusinessStruct.setSubscribeAmount(listCeBatchPaymentReq.get(0).getAmount().toString());
		operateLogBusinessStruct.setBizid(listCeBatchPaymentReq.get(0).getBizId());
		operateLogBusinessStruct.setCreditapplicationId(financeMoney.getCreditapplicationId().longValue());
		if (!CommonUtil.isEmpty(listCeBatchPaymentRes)) {
			CeBatchPaymentRes ceBatchPaymentRes = listCeBatchPaymentRes.get(0);
			log.info("***************财务付款预约出参：CeBatchPaymentRes**************");
			log.info(JsonUtil.getJson(ceBatchPaymentRes));
			// 存结算平台的返回状态
			if (Constants.PAYPLATFORM_STATE_WAIT.equals(ceBatchPaymentRes.getState())) {
				financeMoney.setFinanceStatus(Constants.FINANCE_STATE_BESPEAK);// 已预约
				financeMoney.setReturnMsg("付款预约成功:" + ceBatchPaymentRes.getRetInfo() + ";订单号:"
						+ ceBatchPaymentRes.getBizId());
				operateLogBusinessStruct.setFunctionCode("500010");
				operateLogBusinessStruct.setResult("预约成功");
				message.setSuccess(true);
			} else {
				operateLogBusinessStruct.setFunctionCode("500011");
				operateLogBusinessStruct.setResult("预约失败");
				operateLogBusinessStruct.setRemark(ceBatchPaymentRes.getRetInfo());
				financeMoney.setFinanceStatus(Constants.FINANCE_STATE_FAIL);
				financeMoney.setReturnMsg("付款预约失败:" + ceBatchPaymentRes.getRetInfo() + ";订单号:"
						+ ceBatchPaymentRes.getBizId());
				message.setSuccess(false);
				message.setMsg("付款预约失败:" + ceBatchPaymentRes.getRetInfo());
			}
		} else {
			log.info("***************财务付款预约出参：结算平台没有返回参数！**************");
			operateLogBusinessStruct.setFunctionCode("500011");
			operateLogBusinessStruct.setResult("预约失败");
			operateLogBusinessStruct.setRemark("结算平台未返回消息");
			financeMoney.setFinanceStatus(Constants.FINANCE_STATE_FAIL);
			financeMoney.setReturnMsg("付款预约失败:结算平台未返回消息;订单号:" + financeMoney.getBizId());
			message.setSuccess(false);
			message.setMsg("付款预约失败:" + financeMoney.getReturnMsg());
		}
		operateLogService.insert(operateLogBusinessStruct);
		financeMoneyDao.insertFinanceMoney(financeMoney);
		return message;
	}

	/**
	 * 组装发往结算平台的数据
	 * 
	 * @param creditApplicationId 信贷审批单ID
	 * @param financeMoney 账户信息
	 * @param parameter 财务状态信息
	 * @return List<CeBatchPaymentReq> 发往结算平台的对象
	 */
	private List<CeBatchPaymentReq> object2CeBatchPaymentReq(Integer creditApplicationId, FinanceMoney financeMoney,
			GroupLoanRegist parameter) {
// 查某比业务的账号信息
		AccountInfo accountInfo = accountInfoDAO.selectByCrediApplicationId(Long.valueOf(creditApplicationId));
		List<CeBatchPaymentReq> list = new ArrayList<CeBatchPaymentReq>();
		CeBatchPaymentReq c = new CeBatchPaymentReq();
		String bizId = financeMoneyDao.getBizId().toString();// 订单号必填 用UUID生成还是通过序列生成 待做
		c.setBizId(bizId);
		String systemSourceId = PayplatformParamsPropertiesUtil.getProperty(Constants.SYSTEM_SOURCE_ID);// 系统来源ID 必填
		c.setSystemSourceId(systemSourceId);
		String productId = PayplatformParamsPropertiesUtil.getProperty(Constants.PRODUCT_ID);// 产品ID 必填
		c.setProductId(productId);
		String encryptionKey = PayplatformParamsPropertiesUtil.getProperty(Constants.ENCRYPTION_KEY);// 加密标识
		c.setChnId("BANK");// 渠道ID 可选
		c.setBankId(accountInfo.getBankNum().trim());// 当渠道号为THIRD时，银行行别必选 从数据库中取
		c.setPayBranchNo(accountInfo.getPayBranchno().trim());// 支付行号 当渠道号为BANK时，必选
		c.setOpenBankName(accountInfo.getBankName().trim());// 开户行名称 必选
		c.setAccountFlag(accountInfo.getCardFlag());// 卡折标识 必选
		c.setAccountName(accountInfo.getAccountName().trim());// 账户名称 必选
		c.setAccountNo(accountInfo.getAccount().trim());// 账号 必选
		c.setAccountType(accountInfo.getAccountType());// 账户类型 可选(系统默认为对私账户)
// 查询小组信息 包括 小组编号+组长姓名+分公司名称(区域部门)+小组放款金额（放款确认金额）金额是指合同金额
		CreditApplication creditApplication = creditApplicationDao.selectById(Long.valueOf(creditApplicationId));
		BorrowerServiceApp borrowerServiceApp = borrowerServiceAppDAO
				.selectBorrowerLeaderByCreditApplicationId(creditApplicationId);
		StringBuffer remark = new StringBuffer("");
		remark.append(creditApplication.getGroupNumber()).append("-").append(borrowerServiceApp.getName()).append("-")
				.append(creditApplication.getCompanyName()).append("-");// 备注 可选 存放小组编号+组长姓名+分公司名称(区域部门)+小组放款金额（放款确认金额）
		Date loanDate = null;
		DecimalFormat f = new DecimalFormat("######.00");
// 判断对公对私业务 对私从放款登记表中去数据，对公从额度确认表中去数据
		if ("1".equals(creditApplication.getBusinessType())) {// 对私
			GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
			groupLoanRegist.setCreditapplicationId(creditApplicationId);
			groupLoanRegist.setRegistStatus("1");
			GroupLoanRegist groupLoanRegistResult = groupLoanRegistService.selectDengji(groupLoanRegist);
			c.setAmount(new BigDecimal(f.format(groupLoanRegistResult.getRealAmount())));// 金额 必选
// remark.append(groupLoanRegistResult.getRealAmount());
			Date loanTime = groupLoanRegistResult.getLoanTime();
			financeMoney.setAmount(f.format(groupLoanRegistResult.getRealAmount()));// 金额 必选
			financeMoney.setAssociationId(groupLoanRegistResult.getGroupLoanRegistId());
			loanDate = getLoanDate(loanTime);
		} else {// 对公
			AmountConfirm amountConfirm = amountConfirmService.selectNew(creditApplicationId);
			c.setAmount(new BigDecimal(f.format(amountConfirm.getRealAmount())));// 金额 必选
			financeMoney.setAmount(f.format(amountConfirm.getRealAmount()));// 金额 必选
			financeMoney.setAssociationId(amountConfirm.getAmountConfirmId().intValue());
			loanDate = getLoanDate(amountConfirm.getLoanTime());
		}
// 取得合同金额
		AmountConfirm getAmount = amountConfirmService.selectNew(creditApplicationId);
		remark.append(f.format(getAmount.getAmount()));
		if (null != parameter) {
			loanDate = getLoanDate(parameter.getLoanConfirmTime());
		}
		c.setRemark(remark.toString());
		c.setReserveId(SpringSecurityUtils.getCurrentUser().getName_zh());// 预约人，当前登录人
		financeMoney.setRemark(remark.toString());
		financeMoney.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
		financeMoney.setReserveTime(loanDate);
		c.setReserveFlag(true);// 预约标识 必选
		GregorianCalendar gregorianCalendar = DateUtil.getGregorianCalendar(loanDate);
		c.setReserveTime(new XMLGregorianCalendarImpl(gregorianCalendar));// 一个对象 预约时间 条件 预约标识为true时，此域必填
		c.setMobile(accountInfo.getMobilephone());// "手机号可选
// 获得省市信息
		NationalStandardCode province = nationalStandardCodeService.selectByCode(accountInfo.getProvinceId());
		if (null != province) {
			String pName = province.getCityName();
			c.setProvince(pName);// 省
		}
		NationalStandardCode city = nationalStandardCodeService.selectByCode(accountInfo.getCityId());
		if (null != city) {
			String cName = city.getCityName();
			c.setCity(cName);// 市
		}
		c.setAreaCode("");// 区号
		c.setCitySuffix("");// 区县
		c.setSignInfo(new Md5PasswordEncoder().encodePassword(c.getSystemSourceId() + c.getBizId(), encryptionKey));// 摘要信息 必选
		list.add(c);
		financeMoney.setCreditapplicationId(creditApplicationId);
		financeMoney.setAccountFlag(accountInfo.getCardFlag());// 卡折标识 必选
		financeMoney.setOpenBankName(accountInfo.getBankName());// 开户行名称 必选
		financeMoney.setBankId(accountInfo.getBankNum());
		financeMoney.setProductId(productId);
		financeMoney.setType("F");// 类型
		financeMoney.setBizId(bizId);
		financeMoney.setSystemSourceId(systemSourceId);
		financeMoney.setChnId(c.getChnId());
		financeMoney.setAccountName(accountInfo.getAccountName());// 账户名称 必选
		financeMoney.setAccountNo(accountInfo.getAccount());// 账号 必选
		financeMoney.setPayWay("N");// 网银
		financeMoney.setOperatorId(SpringSecurityUtils.getCurrentUser().getUserId());
		financeMoney.setOperateDate(new Date());
		financeMoney.setHistoryFlag("F");
		financeMoney.setIdCard(accountInfo.getCredentialsNumber());// 证件类别
		financeMoney.setCardKind("01");
		financeMoney.setMobile(accountInfo.getMobilephone());
		return list;
	}

// 日期规则 如果是在当前时间之后，明天0点之前 则为当前时间加15分钟，如果是明天之后的时间，则为该时间日期的早上10点
	/**
	 * 根据日期规则调整付款时间
	 * 
	 * @param loanTime 付款日期
	 * @return 付款日期
	 */
	private Date getLoanDate(Date loanTime) {
		
		String sLoanTime = DateUtil.dateConvertString(loanTime);
		loanTime = DateUtil.stringConvertDate(sLoanTime);
		Date loanDate = null;
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		Date when = today.getTime();
		if (0 >= loanTime.compareTo(when)) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, 15);
			loanDate = now.getTime();
		} else {
			today.setTime(loanTime);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			today.add(Calendar.HOUR, 10);
			loanDate = today.getTime();
		}
		return loanDate;
	}
}

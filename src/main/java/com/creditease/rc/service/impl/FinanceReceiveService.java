package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.settle.CeBatchReceiptReq;
import com.creditease.rc.app.settle.CeBatchReceiptRes;
import com.creditease.rc.app.settle.CeFinalPaymentWSImplPortType;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IFinanceMoneyDao;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.LocalReserveListDTO;
import com.creditease.rc.domain.LocalReturnDTO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IFinanceReceiveService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IReceivablesRegistrationService;
import com.creditease.rc.service.IReceivedRecordListService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.PayplatformParamsPropertiesUtil;
import com.creditease.rc.vo.LocalReserveDTOVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.creditease.rc.vo.ReserveReturnRequestVo;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class FinanceReceiveService implements IFinanceReceiveService {

	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private CeFinalPaymentWSImplPortType ceFinalPaymentWSImplPortType;
	@Resource
	private IFinanceMoneyService financeMoneyService;
	@Resource
	private IReceivablesRegistrationService receivablesRegistrationService;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private IRural2CreditService rural2CreditService;
	@Resource
	private IReceivedRecordListService receivedRecordListService;

	@Resource
	private IFinanceMoneyDao financeMoneyDao;

	private Logger log = Logger.getLogger(ProtocolManagementService.class);

	@Override
	public Message financialReceive(List<Integer> receivedRecordIdList, ReceivedRecord parameter) {
		Message message = new Message();
		if (CommonUtil.isEmpty(receivedRecordIdList)) {
			message.setSuccess(false);
			message.setMsg("没有传收款登记ID");
			return message;
		}
		List<FinanceMoney> financeMoneyList = new ArrayList<FinanceMoney>();
		List<CeBatchReceiptReq> requestList = object2CeBatchPaymentReq(receivedRecordIdList, financeMoneyList,
				parameter);
		CeBatchReceiptReq CeBatchReceiptReq = requestList.get(0);
		log.info("***************财务收款款预约入参：CeBatchReceiptReq**************");
		log.info(JsonUtil.getJson(CeBatchReceiptReq));
		List<CeBatchReceiptRes> ceBatchReceiptResList = ceFinalPaymentWSImplPortType.batchReceipt(requestList);
		List<OperateLogBusinessStruct> oList = new ArrayList<OperateLogBusinessStruct>();
		for (FinanceMoney financeMoney : financeMoneyList) {
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setAccountNo(requestList.get(0).getAccountNo());
			operateLogBusinessStruct.setAccountName(requestList.get(0).getAccountName());
			operateLogBusinessStruct.setIdCard(financeMoney.getIdCard());
			operateLogBusinessStruct.setSubscribeTime(DateUtil.dateConvertString(financeMoney.getReserveTime()));
			operateLogBusinessStruct.setSubscribeAmount(requestList.get(0).getAmount().toString());
			operateLogBusinessStruct.setBizid(requestList.get(0).getBizId());
			operateLogBusinessStruct.setCreditapplicationId(financeMoney.getCreditapplicationId().longValue());
			if (!CommonUtil.isEmpty(ceBatchReceiptResList)) {
				CeBatchReceiptRes ceBatchReceiptRes = ceBatchReceiptResList.get(0);
				log.info("***************财务收款款预约出参：ceBatchReceiptRes**************");
				log.info(JsonUtil.getJson(ceBatchReceiptRes));
				// 存结算平台的返回状态
				financeMoney.setRemark(ceBatchReceiptRes.getRemark());
				if (Constants.PAYPLATFORM_STATE_WAIT.equals(ceBatchReceiptRes.getState())) {
					if ("U".equals(financeMoney.getType())) {
						operateLogBusinessStruct.setFunctionCode("500050");
					} else {
						operateLogBusinessStruct.setFunctionCode("500020");
					}
					financeMoney.setFinanceStatus(Constants.FINANCE_STATE_BESPEAK);// 已预约
					financeMoney.setReturnMsg("收款预约成功:" + ceBatchReceiptRes.getRetInfo() + ";订单号:"
							+ ceBatchReceiptRes.getBizId());
					message.setSuccess(true);
				} else {
					if ("U".equals(financeMoney.getType())) {
						operateLogBusinessStruct.setFunctionCode("500051");
					} else {
						operateLogBusinessStruct.setFunctionCode("500021");
					}
					financeMoney.setFinanceStatus(Constants.FINANCE_STATE_FAIL);
					financeMoney.setReturnMsg("收款预约失败:" + ceBatchReceiptRes.getRetInfo() + ";订单号:"
							+ ceBatchReceiptRes.getBizId());
					message.setSuccess(false);
					message.setMsg("收款预约失败:" + ceBatchReceiptRes.getRetInfo());
				}
			} else {
				log.info("***************财务收款款预约出参：结算平台没有返回参数！**************");
				operateLogBusinessStruct.setFunctionCode("500021");
				financeMoney.setFinanceStatus(Constants.FINANCE_STATE_FAIL);
				financeMoney.setReturnMsg("收款预约失败: 结算平台未返回消息;订单号:" + financeMoney.getBizId());
				message.setSuccess(false);
				message.setMsg("收款预约失败:" + financeMoney.getReturnMsg());
			}
			oList.add(operateLogBusinessStruct);
		}
		operateLogService.batchInsert(oList);
		// if(!CommonUtil.isEmpty(flist)){
		// for(FinanceMoney f:flist){
		// f.setHistoryFlag("T");
		// }
		// financeMoneyDao.batchUpdateFinanceMoney(flist);
		// }
		financeMoneyService.batchInsertReceiveFinanceMoney(financeMoneyList);
		if (message.isSuccess()) {
			receivablesRegistrationService.batchUpdateReceivedStatus(receivedRecordIdList, "0");
		}
		return message;

	}

	/**
	 * 组织发往结算的数据
	 * 
	 * @param receivedRecordIdList
	 *            收款登记表ID
	 * @param financeMoneyList
	 *            账务状态VO
	 * @param parameter
	 *            收款登记
	 * @return List<CeBatchReceiptReq>
	 */
	private List<CeBatchReceiptReq> object2CeBatchPaymentReq(List<Integer> receivedRecordIdList,
			List<FinanceMoney> financeMoneyList, ReceivedRecord parameter) {
		if (CommonUtil.isEmpty(receivedRecordIdList)) {
			return null;
		}
		List<CeBatchReceiptReq> requestList = new ArrayList<CeBatchReceiptReq>();
		String systemSourceId = PayplatformParamsPropertiesUtil.getProperty(Constants.SYSTEM_SOURCE_ID);// 系统来源ID 必填
		String bizId = financeMoneyService.getBizId().toString();// 订单号必填
		String productId = PayplatformParamsPropertiesUtil.getProperty(Constants.PRODUCT_ID);// 产品ID 必填
		CeBatchReceiptReq c = new CeBatchReceiptReq();
		c.setBizId(bizId);
		c.setSystemSourceId(systemSourceId);
		c.setProductId(productId);
		DecimalFormat f = new DecimalFormat("######.00");
		AccountInfo accountInfo = financeMoneyService.selectAccountInfo(receivedRecordIdList.get(0));
		c.setBankId(accountInfo.getBankNum().trim());// 当渠道号为THIRD时，银行行别必选
														// 从数据库中取
		c.setPayBranchNo(accountInfo.getPayBranchno().trim());// 支付行号
																// 当渠道号为BANK时，必选
		c.setOpenBankName(accountInfo.getBankName().trim());// 开户行名称 必选
		c.setAccountFlag(accountInfo.getCardFlag());// 卡折标识 必选
		c.setAccountName(accountInfo.getAccountName().trim());// 账户名称 必选
		c.setAccountNo(accountInfo.getAccount().trim());// 账号 必选
		c.setAccountType(accountInfo.getAccountType());// 账户类型 可选(系统默认为对私账户)
		// c.setAccountSourceId("信托");账户来源 可选
		c.setReserveFlag(true);// 预约标识 必选
		c.setCardKind("01");
		c.setIdCard(accountInfo.getCredentialsNumber());
		// 备注 可选 存放小组编号+组长姓名+分公司名称(区域部门)+小组放款金额（审批金额）
		c.setMobile(accountInfo.getMobilephone());// "手机号可选
		Double amount = 0.0;
		String remarkFromJSP = "";
		// 备注存小组信息
		StringBuffer remark = new StringBuffer("");
		Date receiveTime = null;
		Calendar calendar = Calendar.getInstance();
		if (null != parameter) {
			remarkFromJSP = parameter.getRemark();
			c.setReserveId(SpringSecurityUtils.getCurrentUser().getName_zh());// 预约人
			calendar.setTime(parameter.getReceivedTime());
			calendar.add(Calendar.MINUTE, 15);
			receiveTime = calendar.getTime();
		} else {
			calendar.setTime(new Date());
			calendar.add(Calendar.MINUTE, 15);
			receiveTime = calendar.getTime();
		}
		amount = construFinanceMoney(receivedRecordIdList, financeMoneyList, remark, c);
		for (FinanceMoney fm : financeMoneyList) {
			fm.setAccountFlag(accountInfo.getCardFlag());// 卡折标识// 必选
			fm.setOpenBankName(accountInfo.getBankName());// 开户行名称// 必选
			fm.setBankId(accountInfo.getBankNum());
			fm.setProductId(productId);
			fm.setBizId(bizId);
			fm.setSystemSourceId(systemSourceId);
			fm.setChnId(c.getChnId());
			fm.setAccountName(accountInfo.getAccountName());// 账户名称// 必选
			fm.setAccountNo(accountInfo.getAccount());// 账号 必选
			fm.setIdCard(accountInfo.getCredentialsNumber());// 证件号
			fm.setMobile(accountInfo.getMobilephone());
			fm.setRemark(remarkFromJSP);
			fm.setReserveTime(receiveTime);
		}
		if (null != parameter) {
			c.setReserveId(SpringSecurityUtils.getCurrentUser().getName_zh());
			remark.append(remarkFromJSP);
		}
		c.setRemark(remark.toString());
		GregorianCalendar gregorianCalendar = DateUtil.getGregorianCalendar(receiveTime);
		c.setReserveTime(new XMLGregorianCalendarImpl(gregorianCalendar));// 一个对象// 预约时间// 条件
		c.setAmount(new BigDecimal(f.format(amount).toString()));// 金额 必选
		String encryptionKey = PayplatformParamsPropertiesUtil.getProperty(Constants.ENCRYPTION_KEY);// 加密标识
		c.setSignInfo(new Md5PasswordEncoder().encodePassword(c.getSystemSourceId() + c.getBizId(), encryptionKey));// 摘要信息 必选
		requestList.add(c);
		return requestList;
	}

	/**
	 * 构造财务对象
	 * 
	 * @param receivedRecordIdList 收款登记id
	 * @param financeMoneyList 财务对象列表
	 * @param receivedRecordIdList
	 * @param remark 备注
	 * @param c 结算平台参数
	 * @return double 金额
	 */
	private Double construFinanceMoney(List<Integer> receivedRecordIdList, List<FinanceMoney> financeMoneyList,
			StringBuffer remark, CeBatchReceiptReq c) {
		Double amount = 0.0;
		// 正常收款预约
		for (Integer i : receivedRecordIdList) {
			ReceivedRecord receivedRecord = new ReceivedRecord();
			receivedRecord.setReceivedRecordId(i);
			receivedRecord.setHistoryFlag("F");
			// 条件应该为2或4，即收款失败或未预约
			// receivedRecord.setReceivedStatus("4");
			CreditApplication creditapplication = creditApplicationService.selectCreditApplicationByReceiveRecordId(i);
			remark.append(creditapplication.getGroupNumber()).append("-").append(creditapplication.getGroupName())
					.append("-").append(creditapplication.getCompanyName()).append("-");
			List<ReceivedRecord> receivedRecordList = receivablesRegistrationService
					.searchReceivedRecord(receivedRecord);
			if (CommonUtil.isEmpty(receivedRecordList)) {
				AmountConfirm parameter = new AmountConfirm();
				parameter.setAmountConfirmId(i.longValue());
				AmountConfirm f = financeMoneyService.selectFinanceMoneyBack(parameter);
				if (null != f) {
					FinanceMoney financeMoney = new FinanceMoney();
					financeMoney.setCreditapplicationId(f.getCreditapplicationId());
					financeMoney.setType("U");// 类型
					financeMoney.setAssociationId(f.getAmountConfirmId().intValue());
					financeMoney.setPayWay("N");
					financeMoney.setOperatorId(SpringSecurityUtils.getCurrentUser().getUserId());
					financeMoney.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
					financeMoney.setOperateDate(new Date());
					financeMoney.setHistoryFlag("F");
					financeMoney.setCardKind("01");
					financeMoney.setAmount(f.getRealAmount().toString());// 金额 必选
					financeMoneyList.add(financeMoney);
					amount = CurrencyUtil.add(amount, Double.valueOf(f.getRealAmount()));
					remark.append(f.getRealAmount()).append(";");
					c.setReserveId(SpringSecurityUtils.getCurrentUser().getName_zh());
				}
			} else {
				for (ReceivedRecord r : receivedRecordList) {
					FinanceMoney financeMoney = new FinanceMoney();
					financeMoney.setCreditapplicationId(r.getCreditapplicationId());
					financeMoney.setType("S");// 类型
					financeMoney.setAssociationId(r.getReceivedRecordId());
					financeMoney.setPayWay("N");
					financeMoney.setOperatorId(r.getOperatorId().toString());
					financeMoney.setOperatorName(r.getOperatorName());
					financeMoney.setOperateDate(new Date());
					financeMoney.setHistoryFlag("F");
					financeMoney.setCardKind("01");
					financeMoney.setAmount(r.getReceivedAmount().toString());// 金额 必选
					financeMoneyList.add(financeMoney);
					amount = CurrencyUtil.add(amount, r.getReceivedAmount());
					remark.append(r.getReceivedAmount()).append(";");
					c.setReserveId(r.getOperatorName());
				}
			}
		}
		return amount;
	}

	@Override
	public Message financialReceiveATC(List<Integer> receivedRecordIdList, Date paramDate, String remark) {
		// TODO Auto-generated method stub
		Message message = new Message();
		List<Long> receivedRecordIds = new ArrayList<Long>();
		for (Integer i : receivedRecordIdList) {
			receivedRecordIds.add(i.longValue());
		}
		ReserveReturnRequestVo reserveReturnRequestVo = rural2CreditService.reserveReturn(receivedRecordIds, paramDate,
				remark);
		boolean isSuccess = reserveReturnRequestVo.isSuccess();
		message.setSuccess(isSuccess);
		message.setCode(reserveReturnRequestVo.getCode());
		message.setMsg(reserveReturnRequestVo.getInfo());

		String financeStatus = isSuccess ? "1" : "3";
		String operatorId = SpringSecurityUtils.getCurrentUser().getUserId();
		String operatorName = SpringSecurityUtils.getCurrentUser().getName_zh();
		Date newDate = new Date();
		String msg1 = "【贷后系统收款预约：" + (isSuccess ? "‘成功’" : "‘失败’") + "】；";
		String msg2 = "【code:" + reserveReturnRequestVo.getCode() + "---info:" + reserveReturnRequestVo.getInfo()
				+ "】；";
		List<FinanceMoney> financeMoneys = new ArrayList<FinanceMoney>();
		List<LocalReturnDTO> localReturnDTOs = reserveReturnRequestVo.getLocalReturnDTOs();

		Calendar calendar = Calendar.getInstance();

		for (LocalReturnDTO lr : localReturnDTOs) {
			FinanceMoney financeMoney = new FinanceMoney();
			financeMoney.setType("S");
			financeMoney.setAssociationId(lr.getReceivedRecordId().intValue());
			financeMoney.setPayWay("N");// 默认的为N:网银
			financeMoney.setFinanceStatus(financeStatus);
			financeMoney.setOperatorId(operatorId);
			financeMoney.setOperatorName(operatorName);
			financeMoney.setOperateDate(newDate);
			financeMoney.setRemark(remark);
			financeMoney.setHistoryFlag("F");
			financeMoney.setBizId(lr.getBizId());
			financeMoney.setReserveId(lr.getReserveId());
			financeMoney.setOpenBankName(lr.getBankName());
			financeMoney.setProductId(lr.getProductId());
			financeMoney.setBankId(lr.getBankId());
			financeMoney.setAccountFlag(lr.getBankBook());
			financeMoney.setAccountNo(lr.getBankAccount());
			financeMoney.setAccountName(lr.getBankAccountName());
			financeMoney.setAmount(lr.getDestineAmount().toString());
			financeMoney.setCardKind("01");
			financeMoney.setIdCard(lr.getBankIdnumber());
			financeMoney.setMobile(lr.getMobile());
			financeMoney
					.setReturnMsg(msg1 + msg2 + "【订单号:" + lr.getReserveId() + "、金额:" + lr.getDestineAmount() + "】；");
			/*因之前贷后服务传参要求在当前时间基础上增加1h,所以在保存本地预约时间时，预约时间在贷后服务返回的时间基础上减1h*/
			calendar.setTime(lr.getDestineDate());
			calendar.add(Calendar.HOUR_OF_DAY, -1);
			financeMoney.setReserveTime(calendar.getTime());
			financeMoneys.add(financeMoney);
		}
		financeMoneyService.batchInsertReceiveFinanceMoney(financeMoneys);
		// 记操作日志
		List<ReceivedRecord> receivedRecords = receivedRecordListService.queryreceivedRecords(receivedRecordIdList);
		if (isSuccess) {
			receivablesRegistrationService.batchUpdateReceivedStatus(receivedRecordIdList, "0");
			for (FinanceMoney financeMoney : financeMoneys) {
				Integer getAssociationId = financeMoney.getAssociationId();
				Long creditapplicationId = null;
				for (ReceivedRecord receivedRecord : receivedRecords) {
					if (receivedRecord.getReceivedRecordId().equals(getAssociationId)) {
						creditapplicationId = receivedRecord.getCreditapplicationId().longValue();
					}
				}
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
				operateLogBusinessStruct.setFunctionCode("500020");
				operateLogBusinessStruct.setRemark("财务收款预约-预约成功" + financeMoney.getReturnMsg());
				operateLogService.insert(operateLogBusinessStruct);
			}
		} else {
			for (FinanceMoney financeMoney : financeMoneys) {
				Integer getAssociationId = financeMoney.getAssociationId();
				Long creditapplicationId = null;
				for (ReceivedRecord receivedRecord : receivedRecords) {
					if (receivedRecord.getReceivedRecordId().equals(getAssociationId)) {
						creditapplicationId = receivedRecord.getCreditapplicationId().longValue();
					}
				}
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
				operateLogBusinessStruct.setFunctionCode("500021");
				operateLogBusinessStruct.setRemark("财务收款预约-预约失败" + financeMoney.getReturnMsg());
				operateLogService.insert(operateLogBusinessStruct);
			}
		}
		return message;
	}

	@Override
	public Message updateQueryReserveResult(List<String> bizIdList) {
		log.info("================updateQueryReserveResult调度任务开始。。哈哈哈嘿。。======================"+new Date());
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setSuccess(false);
		LocalReserveDTOVo localReserveDTOVo = rural2CreditService.qyReserveSearch(bizIdList, null);
		String getRetCode = localReserveDTOVo.getRetCode();
		String getRetInfo = localReserveDTOVo.getRetInfo();
		if ("0".equals(getRetCode)) {
			message.setSuccess(true);
			List<Map<String, String>> maps1 = new ArrayList<Map<String, String>>();
			List<Map<String, String>> maps2 = new ArrayList<Map<String, String>>();
			List<LocalReserveListDTO> localReserveListDTOs = localReserveDTOVo.getLocalReserveListDTOs();
			for (LocalReserveListDTO lrld : localReserveListDTOs) {
				Map<String, String> map1 = new HashMap<String, String>();
				Map<String, String> map2 = new HashMap<String, String>();

				String bizId = lrld.getBizid();// 订单号
				String getReserveResult = lrld.getReserveResult();
				String receivedRecordResult = null;
				String financeMoneyResult = null;
				String returnMSG = null;
				// 这里后续又补了个8的状态，这是贷后约结算失败的状态，如果这个状态，贷后将不再约结算
				if ("9".equals(getReserveResult) || "8".equals(getReserveResult)) {// 收款失败
					receivedRecordResult = "2";
					financeMoneyResult = "3";
					returnMSG = "【贷后系统】返回收款失败消息！--订单号：" + bizId;
				} else if ("10".equals(getReserveResult)) {// 收款成功
					receivedRecordResult = "1";
					financeMoneyResult = "2";
					returnMSG = "【贷后系统】返回收款成功消息！--订单号：" + bizId;
				}
				map1.put("bizId", bizId);
				map1.put("financeMoneyResult", financeMoneyResult);
				map1.put("returnMSG", returnMSG);

				map2.put("bizId", bizId);
				map2.put("receivedRecordResult", receivedRecordResult);
				maps1.add(map1);
				maps2.add(map2);
			}
			boolean updateSuccess = false;
			updateSuccess = financeMoneyDao.updateFinanceMoneyStatusAndReturnMSG(maps1);
			updateSuccess = financeMoneyDao.updateReceivedRecordStatusBybizId(maps2);
			message.setSuccess(updateSuccess);
		}
		message.setMsg(getRetInfo);

		return message;
	}

	@Override
	public List<String> queryBizIdListByReceivedRecordIdList(List<Integer> receivedRecordIdList) {
		// TODO Auto-generated method stub
		return financeMoneyDao.queryBizIdListByReceivedRecordIdList(receivedRecordIdList);
	}
}

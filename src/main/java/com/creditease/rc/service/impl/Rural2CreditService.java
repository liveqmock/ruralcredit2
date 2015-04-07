/**
 * Title:Rural2CreditService.java
 * Description:
 */
package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import com.creditease.rc.app.comprehensive.DerateProposerIdInfoNoticeDTO;
import com.creditease.rc.app.comprehensive.DerateProposerIdInfoNoticeReq;
import com.creditease.rc.app.comprehensive.DerateProposerIdInfoNoticeRes;
import com.creditease.rc.app.comprehensive.IcpDerateServiceWS;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.credit.ActualChargeInfoDTO;
import com.creditease.rc.app.credit.ActualPeriodScheduleDTO;
import com.creditease.rc.app.credit.ActualPeriodScheduleDTO.OverdueChargeList;
import com.creditease.rc.app.credit.ActualPeriodScheduleDTO.PeriodChargeList;
import com.creditease.rc.app.credit.AllAheadScheduleDTO;
import com.creditease.rc.app.credit.ApplyIdListDTO;
import com.creditease.rc.app.credit.ApplyListDTO;
import com.creditease.rc.app.credit.ApplyListsDTO;
import com.creditease.rc.app.credit.ApplyListsDTO.RSellIdList;
import com.creditease.rc.app.credit.AppointChargeInfoDTO;
import com.creditease.rc.app.credit.AppointScheduleDTO;
import com.creditease.rc.app.credit.AppointScheduleDTO.TotalOverdueChargeList;
import com.creditease.rc.app.credit.AppointScheduleDTO.TotalPeriodChargeList;
import com.creditease.rc.app.credit.ChargeDiscountInfoDTO;
import com.creditease.rc.app.credit.ChgReturnTypeRequest;
import com.creditease.rc.app.credit.ChgReturnTypeResponse;
import com.creditease.rc.app.credit.ClientApplyRequest;
import com.creditease.rc.app.credit.ClientApplyRequest.PeriodChargeDiscountList;
import com.creditease.rc.app.credit.ClientApplyResponse;
import com.creditease.rc.app.credit.LoanBalanceDataRequest;
import com.creditease.rc.app.credit.LoanBalanceDataResponse;
import com.creditease.rc.app.credit.ModifySellerRequest;
import com.creditease.rc.app.credit.ModifySellerRequest.ApplyList;
import com.creditease.rc.app.credit.ModifySellerResponse;
import com.creditease.rc.app.credit.OfficeIdListDTO;
import com.creditease.rc.app.credit.OfficeIdListDTO.SellIdList;
import com.creditease.rc.app.credit.OverdueChargeInfoDTO;
import com.creditease.rc.app.credit.OverdueInfoRequest;
import com.creditease.rc.app.credit.OverdueInfoRequest.OfficeIdList;
import com.creditease.rc.app.credit.OverdueInfoResponse;
import com.creditease.rc.app.credit.OverdueInfoResult;
import com.creditease.rc.app.credit.QyClientApplyRequest;
import com.creditease.rc.app.credit.QyClientApplyResponse;
import com.creditease.rc.app.credit.QyClientApplyResponse.QyapplyList;
import com.creditease.rc.app.credit.QyReserveResult;
import com.creditease.rc.app.credit.QyReserveResultRequest;
import com.creditease.rc.app.credit.QyReserveResultRequest.ReserveList;
import com.creditease.rc.app.credit.QyReserveResultResponse;
import com.creditease.rc.app.credit.QyapplyListDTO;
import com.creditease.rc.app.credit.RSellIdListDTO;
import com.creditease.rc.app.credit.RSellIdListDTO.ApplyIdList;
import com.creditease.rc.app.credit.ReserveIdListDTO;
import com.creditease.rc.app.credit.ReserveListDTO;
import com.creditease.rc.app.credit.ReserveReturnRequest;
import com.creditease.rc.app.credit.ReserveReturnRequest.ReturnList;
import com.creditease.rc.app.credit.ReserveReturnResponse;
import com.creditease.rc.app.credit.ReserveReturnResponse.ReturnIdList;
import com.creditease.rc.app.credit.ReturnAmountRequest;
import com.creditease.rc.app.credit.ReturnAmountResponse;
import com.creditease.rc.app.credit.ReturnAmountResult;
import com.creditease.rc.app.credit.ReturnAmountResult.AllAheadSchedule;
import com.creditease.rc.app.credit.ReturnAmountResult.AppointSchedule;
import com.creditease.rc.app.credit.ReturnDerateListDTO;
import com.creditease.rc.app.credit.ReturnIdListDTO;
import com.creditease.rc.app.credit.ReturnListDTO;
import com.creditease.rc.app.credit.ReturnSchemeRequest;
import com.creditease.rc.app.credit.ReturnSchemeResponse;
import com.creditease.rc.app.credit.ReturnSchemeResult;
import com.creditease.rc.app.credit.ReturnSchemeResult.ApsList;
import com.creditease.rc.app.credit.ReturnSchemeResult.ReturnDerateList;
import com.creditease.rc.app.credit.RuralBusyService;
import com.creditease.rc.app.credit.SellIdListDTO;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.dao.IAmountConfirmDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IDiscountConfigurationDao;
import com.creditease.rc.dao.IFinanceMoneyDao;
import com.creditease.rc.dao.IRural2CreditDao;
import com.creditease.rc.dao.OrgamsDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.ClientApplyHistory;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.LocalClientApplyRequest;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalReserveListDTO;
import com.creditease.rc.domain.LocalReturnAmountRequest;
import com.creditease.rc.domain.LocalReturnAmountResult;
import com.creditease.rc.domain.LocalReturnDTO;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.LocalSellIdDTO;
import com.creditease.rc.domain.LocalWebServiceResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OverDueObj;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.service.IClientApplyHistoryService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.ObjectUtil;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.CreditDiscountVo;
import com.creditease.rc.vo.LocalReserveDTOVo;
import com.creditease.rc.vo.ReserveReturnRequestVo;
import com.creditease.rc.vo.ReturnSchemeResultVo;

/**
 * Title:Rural2CreditService.java Description: Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0 2013-5-15
 */
@Service
public class Rural2CreditService implements IRural2CreditService {

	/**
	 * @Description 默认构造器
	 */
	public Rural2CreditService() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private RuralBusyService ruralBusyService;
	Logger log = Logger.getLogger(Rural2CreditService.class);

	@Resource
	private ICreditApplicationDAO creditApplicationDAO;

	@Resource
	private IFinanceMoneyDao financeMoneyDao;

	@Resource
	private IRural2CreditDao rural2CreditDao;

	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private IClientApplyHistoryService clientApplyHistoryService;

	@Resource
	private IReturnPlanService returnPlanService;

	@Resource
	private IDiscountConfigurationDao discountConfigurationDao;

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;

	@Resource
	private IcpDerateServiceWS icpDerateServiceWS;
	@Resource
	private OrgamsDAO orgamsDAO;
	@Resource
	private IAmountConfirmDAO amountConfirmDAO;

	/**
	 * 
	 * @author 郝强
	 * @Description: 接收借款信息
	 * @param creditapplicationId
	 *            Long
	 * @return boolean
	 * @version v1.1 2013-5-16
	 */
	public Message clientApply(Long creditapplicationId) {
		// 构造一个用于调用webservice接口的对象
		ClientApplyRequest clientApplyRequest = constructClientApplyRequest(creditapplicationId);
		// 记Log日志
		log.info("**************RuralBusyService.clientApply(clientApplyRequest) requestParam******************");
		log.info(JsonUtil.getJackson(clientApplyRequest));
		// 调用clientApply接口
		ClientApplyResponse clientApplyResponse = ruralBusyService.clientApply(clientApplyRequest);
		log.info("**************RuralBusyService.clientApply(clientApplyRequest) responseParam******************");
		log.info(JsonUtil.getJackson(clientApplyResponse));
		LocalWebServiceResponse localWebServiceResponse = transforClientApplyResponse(clientApplyResponse);

		// System.out.println(clientApplyResponse.getRetCode());
		 //System.out.println(clientApplyResponse.getRetInfo());

		// 根据返回的code判断是否调用接口成功
		Message message = new Message();
		message.setSuccess(false);
		message.setCode(clientApplyResponse.getRetCode());
		message.setMsg(clientApplyResponse.getRetInfo());
		if ("0".equals(localWebServiceResponse.getRetCode())||"39".equals(localWebServiceResponse.getRetCode())) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 构造一个用于调用1.1 接收借款信息接口的对象
	 * 
	 * @author 郝强
	 * @param creditapplicationId
	 *            本地信贷申请编号
	 * @return ClientApplyRequest clientApply接口所需要的入参
	 */
	public ClientApplyRequest constructClientApplyRequest(Long creditapplicationId) {

		if (creditapplicationId == null) {
			// System.out.println("=======creditapplicationId是空！！！！");
			throw new AppBusinessException("creditapplicationId是空！！！！");
		}

		// 通过creditapplicationId去查询构成ClientApplyRequest对象的必要入参
		LocalClientApplyRequest localClientApplyRequest = creditApplicationDAO.queryLocalClientApplyRequest(creditapplicationId);
		ClientApplyRequest clientApplyRequest = new ClientApplyRequest();
		String getApplyId = localClientApplyRequest.getApplyId(); // 申请编号
		String getReturnType = localClientApplyRequest.getReturnType();// 还款方式
		String getLoanType = localClientApplyRequest.getLoanType();// 放款方式
		String getLoanBankAccount = localClientApplyRequest.getLoanBankAccount(); // 放款银行账号
		String getLoanBankClientName = localClientApplyRequest.getLoanBankClientName();// 放款人银行户名
		String getLoanBankProvince = localClientApplyRequest.getLoanBankProvince();// 放款银行所在省
		String getLoanBankCity = localClientApplyRequest.getLoanBankCity();// 放款银行所在市
		String getLoanBankName = localClientApplyRequest.getLoanBankName();// 放款开户行名称
		BigDecimal getApplyAmount = localClientApplyRequest.getApplyAmount();// 合同金额
		Long getProductId = localClientApplyRequest.getProductId();// 产品编号
		String getAmortisation = localClientApplyRequest.getAmortisation();// 分期数
		String getBorrowPurpose = localClientApplyRequest.getBorrowPurpose();// 借款用途
		if (CommonUtil.isEmpty(getBorrowPurpose)) {
			List<CustomerConsultPool> customerConsultPools = customerConsultPoolService.queryCustomerConsultPoolByCreditapplicationId(creditapplicationId);
			if (CommonUtil.isNotEmpty(customerConsultPools)) {
				CustomerConsultPool customerConsultPool = customerConsultPools.get(0);
				getBorrowPurpose = customerConsultPool.getBorrowUse();
			}
		}
		String getSellId = localClientApplyRequest.getSellId();// 信贷员编号
		String getSellName = localClientApplyRequest.getSellName();// 信贷员名字
		String getOfficeId = localClientApplyRequest.getOfficeId();// 分公司编号
		String getOfficeName = localClientApplyRequest.getOfficeName();
		String getClientName = localClientApplyRequest.getClientName();// 客户姓名
		String getIdNumber = localClientApplyRequest.getIdNumber();// 身份证号
		String getMobilePhone = localClientApplyRequest.getMobilePhone();// 手机号码
		String getBankBook = localClientApplyRequest.getBankBook();// 卡折标志
		String getBankAccount = localClientApplyRequest.getBankAccount();// 还款银行账号
		String getBankClientName = localClientApplyRequest.getBankClientName();// 还款人银行户名
		String getBankName = localClientApplyRequest.getBankName();// 还款开户行名称

		BigDecimal getLoanAmount = localClientApplyRequest.getLoanAmount();// 放款金额
		Date getLoanTime = localClientApplyRequest.getLoanTime();// 放款时间
		String getProductTypeId = localClientApplyRequest.getProductTypeId();// 产品分类编号
		
		
		
		/* 将放款省市做处理BEGIN */
		System.out.println("处理前的省-----" + getLoanBankProvince);
		getLoanBankProvince = getLoanBankProvince.replace("市", "").replace("维吾尔", "").replace("回族", "")
				.replace("壮族", "").replace("自治区", "").replace("省", "");
		System.out.println("处理后的省-----" + getLoanBankProvince);

		System.out.println("处理前的市-----" + getLoanBankCity);
		if ("市辖区".equals(getLoanBankCity)) {
			getLoanBankCity = getLoanBankProvince;
		}
		getLoanBankCity = getLoanBankCity.replace("单位", "区*").replace("地区", "").replace("市", "");
		System.out.println("处理后的市-----" + getLoanBankCity);

		/* 将放款省市做处理END */

		// 得到signInfo属性
		Properties properties = PropertiesUtil.loadProperties("spring/smp/smp.properties");
		String topDeparementdId = properties.getProperty("rc.topDeparementdId");

		clientApplyRequest.setApplyId(getApplyId);
		clientApplyRequest.setReturnType(getReturnType);
		clientApplyRequest.setLoanType(getLoanType);
		clientApplyRequest.setLoanBankAccount(getLoanBankAccount);
		clientApplyRequest.setLoanBankClientName(getLoanBankClientName);
		clientApplyRequest.setLoanBankProvince(getLoanBankProvince);
		clientApplyRequest.setLoanBankCity(getLoanBankCity);
		clientApplyRequest.setLoanBankName(getLoanBankName);
		clientApplyRequest.setApplyAmount(getApplyAmount.toString());
		clientApplyRequest.setProductId(getProductId.toString());
		clientApplyRequest.setAmortisation(getAmortisation);
		clientApplyRequest.setBorrowPurpose(getBorrowPurpose);
		clientApplyRequest.setSellId(getSellId);
		clientApplyRequest.setSellName(getSellName);
		clientApplyRequest.setOfficeId(getOfficeId);
		clientApplyRequest.setOfficeName(getOfficeName);
		clientApplyRequest.setClientName(getClientName);
		clientApplyRequest.setIdNumber(getIdNumber);
		clientApplyRequest.setMobilePhone(getMobilePhone);
		clientApplyRequest.setBankBook(getBankBook);
		clientApplyRequest.setBankAccount(getBankAccount);
		clientApplyRequest.setBankClientName(getBankClientName);
		clientApplyRequest.setBankName(getBankName);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String contractime = dateFormat.format(localClientApplyRequest.getContractTime());
		clientApplyRequest.setContractTime(contractime);//合同签订时间
		clientApplyRequest.setBankNumber(localClientApplyRequest.getBankNumber());//银行号
		clientApplyRequest.setSignInfo(signInfoEncrypt(new Date()));
		HashMap<Object,Object> selectBorrowContract = orgamsDAO.selectBorrowContract(creditapplicationId);
		//查询是否是信托还款（1：信托，0：债权转让）
		String lendingChannelByApplyId = amountConfirmDAO.selectLendingChannelByApplyId(getApplyId);
		if(CommonUtil.isNotEmpty(lendingChannelByApplyId)){
			if("1".equals(lendingChannelByApplyId)){
				if(selectBorrowContract.get("PLANID") != null){
					
					clientApplyRequest.setAccountid(selectBorrowContract.get("PLANID").toString());//信托计划id
				}
			}
		}
		

		// System.out.println("`````````````````````````````````" +
		// clientApplyRequest.getSignInfo());

		clientApplyRequest.setLoanAmount(getLoanAmount.toString());
		clientApplyRequest.setLoanTime(DateUtil.dateConvertString(getLoanTime));
		clientApplyRequest.setProductTypeId(getProductTypeId);
		clientApplyRequest.setDepartmentId(topDeparementdId);
		// 设置creditappliactionId 为了 划扣授权书
		clientApplyRequest.setRuralBorrowId(creditapplicationId.toString());
		/** 循环贷打折判断bengin **/
		CreditDiscountVo creditDiscountVo = discountConfigurationDao.queryCreditDisConfig(creditapplicationId);
		String getDiscountFlag = creditDiscountVo.getDiscountFlag();
		BigDecimal getDiscount = creditDiscountVo.getDiscount();
		if ("1".equals(getDiscountFlag)) {
			PeriodChargeDiscountList periodChargeDiscountList = new PeriodChargeDiscountList();
			List<ChargeDiscountInfoDTO> chargeDiscountInfoDTOs = periodChargeDiscountList.getChargeDiscountInfoDTO();
			ChargeDiscountInfoDTO chargeDiscountInfoDTO = new ChargeDiscountInfoDTO();
			chargeDiscountInfoDTO.setChargeType("HJYCB");
			chargeDiscountInfoDTO.setDiscount(getDiscount == null ? "1" : getDiscount.toString());
			chargeDiscountInfoDTOs.add(chargeDiscountInfoDTO);
			clientApplyRequest.setPeriodChargeDiscountList(periodChargeDiscountList);
		}
		/** 循环贷打折判断end **/

		return clientApplyRequest;
	}

	/**
	 * 传入信贷系统销售编号加密properties为signInfo两次
	 * 
	 * @author 郝强
	 * @param paramDate
	 *            日期
	 * @return signInfoEncrypt 加密后的结果
	 */
	private String signInfoEncrypt(Date paramDate) {
		// 得到signInfo属性
		Properties properties = PropertiesUtil.loadProperties("spring/credit/credit.properties");
		String signInfo = properties.getProperty("signInfo");

		String date = DateUtil.dateConvertString(paramDate);

		// MD5加密两次

		System.out.println("原始==========================" + date + signInfo);
		String signInfoEncrypt = new Md5PasswordEncoder().encodePassword(date + signInfo, null);

		System.out.println("加密1==========================" + signInfoEncrypt);

		signInfoEncrypt = new Md5PasswordEncoder().encodePassword(signInfoEncrypt, null);

		System.out.println("加密2==========================" + signInfoEncrypt);

		signInfoEncrypt = signInfoEncrypt.substring(0, 16);

		System.out.println("取前16位的结果============" + signInfoEncrypt);

		// 返回加密后的结果
		return signInfoEncrypt;
	}

	/**
	 * 
	 * @param clientApplyResponse
	 *            ClientApplyResponse
	 * @return LocalWebServiceResponse
	 */
	private LocalWebServiceResponse transforClientApplyResponse(ClientApplyResponse clientApplyResponse) {
		LocalWebServiceResponse localWebServiceResponse = new LocalWebServiceResponse();
		localWebServiceResponse.setRetCode(clientApplyResponse.getRetCode());
		localWebServiceResponse.setRetInfo(clientApplyResponse.getRetInfo());
		return localWebServiceResponse;
	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.2 查询还款计划
	 * @param creditapplicationId
	 *            Long
	 * @return List<LocalReturnSchemeResponse>
	 * @version v1.1 2013-5-16
	 */

	public List<LocalReturnSchemeResponse> returnScheme(Long creditapplicationId) {
		ReturnSchemeRequest returnSchemeRequest = constructReturnSchemeRequest(creditapplicationId);
		// 一个长达32位的ID很重要哦不能更新哦
		// 调接口之前要记录LOG哦~留下证据
		log.info("**************RuralBusyService.returnScheme(returnSchemeRequest) requestParam******************");
		log.info(JsonUtil.getJackson(returnSchemeRequest));
		// 通过这个ID去调webservice接口returnScheme
		ReturnSchemeResponse returnSchemeResponse = ruralBusyService.returnScheme(returnSchemeRequest);
		// 出参也要记日志哦~
		log.info("**************RuralBusyService.returnScheme(returnSchemeRequest) responseParam******************");
		log.info(JsonUtil.getJackson(returnSchemeResponse));
		// 返回的对象很变态属性超级多
		List<LocalReturnSchemeResponse> localReturnSchemeResponseList = transforReturnSchemeResponse(returnSchemeResponse);

		// List<LocalReturnSchemeResponse> localReturnSchemeResponseList =
		// testcon();
		return localReturnSchemeResponseList;
	}

	public ReturnSchemeResultVo returnSchemeResult(Long creditapplicationId) {
		ReturnSchemeRequest returnSchemeRequest = constructReturnSchemeRequest(creditapplicationId);
		// 一个长达32位的ID很重要哦不能更新哦
		// 调接口之前要记录LOG哦~留下证据
		log.info("**************RuralBusyService.returnScheme(returnSchemeRequest) requestParam******************");
		log.info(JsonUtil.getJackson(returnSchemeRequest));
		// 通过这个ID去调webservice接口returnScheme
		ReturnSchemeResponse returnSchemeResponse = ruralBusyService.returnScheme(returnSchemeRequest);
		// 出参也要记日志哦~
		log.info("**************RuralBusyService.returnScheme(returnSchemeRequest) responseParam******************");
		log.info(JsonUtil.getJackson(returnSchemeResponse));
		// 返回的对象很变态属性超级多
		ReturnSchemeResult returnSchemeResult = returnSchemeResponse.getReturnSchemeResult();

		ReturnSchemeResultVo returnSchemeResultVo = new ReturnSchemeResultVo();

		String clientName = returnSchemeResult.getClientName();
		String idNumber = returnSchemeResult.getIdNumber();
		String version = returnSchemeResult.getVersion();
		String returnType = returnSchemeResult.getReturnType();
		String bankAccount = returnSchemeResult.getBankAccount();
		String amount = returnSchemeResult.getAmount();
		String amortisation = returnSchemeResult.getAmortisation();
		String monthReturn = returnSchemeResult.getMonthReturn();
		String monthCorpus = returnSchemeResult.getMonthCorpus();
		String monthAccrual = returnSchemeResult.getMonthAccrual();
		String monthCharge = returnSchemeResult.getMonthCharge();
		String startTime = returnSchemeResult.getStartTime();
		String endTime = returnSchemeResult.getEndTime();
		String status = returnSchemeResult.getStatus();
		String total = returnSchemeResult.getTotal();
		String arrearage = returnSchemeResult.getArrearage();
		String payoffNum = returnSchemeResult.getPayoffNum();
		String breachDay = returnSchemeResult.getBreachDay();
		String bDays = returnSchemeResult.getBDays();
		String pNum = returnSchemeResult.getPNum();

		returnSchemeResultVo.setClientName(clientName);
		returnSchemeResultVo.setIdNumber(idNumber);
		returnSchemeResultVo.setVersion(version);
		returnSchemeResultVo.setReturnType(returnType);
		returnSchemeResultVo.setBankAccount(bankAccount);
		returnSchemeResultVo.setAmount(amount);
		returnSchemeResultVo.setAmortisation(amortisation);
		returnSchemeResultVo.setMonthReturn(monthReturn);
		returnSchemeResultVo.setMonthCorpus(monthCorpus);
		returnSchemeResultVo.setMonthAccrual(monthAccrual);
		returnSchemeResultVo.setMonthCharge(monthCharge);
		returnSchemeResultVo.setStartTime(startTime);
		returnSchemeResultVo.setEndTime(endTime);
		returnSchemeResultVo.setStatus(status);
		returnSchemeResultVo.setTotal(total);
		returnSchemeResultVo.setArrearage(arrearage);
		returnSchemeResultVo.setPayoffNum(payoffNum);
		returnSchemeResultVo.setBreachDay(breachDay);
		returnSchemeResultVo.setbDays(bDays);
		returnSchemeResultVo.setPayoffNum(pNum);
		return returnSchemeResultVo;
	}

	/**
	 * 构造一个调用查询还款计划接口的入参
	 * 
	 * @author 郝强
	 * @param creditapplicationId
	 *            本地信贷申请编号
	 * @return 调用查询还款计划接口的入参
	 */
	private ReturnSchemeRequest constructReturnSchemeRequest(Long creditapplicationId) {
		String sysGuid = creditApplicationDAO.queryUUid(creditapplicationId);
		// 对于错误信息进行处理
		if (sysGuid == null) {
			// System.out.println("=======sysGuid是空！！！！");
			throw new AppBusinessException("sysGuid是空！！！！");
		}
		ReturnSchemeRequest returnSchemeRequest = new ReturnSchemeRequest();
		returnSchemeRequest.setApplyId(sysGuid);
		// System.out.println("uuid======================" +
		// returnSchemeRequest.getApplyId());
		return returnSchemeRequest;
	}

	private List<LocalReturnSchemeResponse> testcon() {

		List<LocalReturnSchemeResponse> list = new ArrayList<LocalReturnSchemeResponse>();
		LocalReturnSchemeResponse localReturnSchemeResponse0 = new LocalReturnSchemeResponse();
		localReturnSchemeResponse0.setPeriod(1);
		localReturnSchemeResponse0.setIsReturned("1");
		localReturnSchemeResponse0.setIsOverdue("0");
		localReturnSchemeResponse0.setRepayDate(DateUtil.stringConvertDate("2013-01-27"));

		LocalReturnSchemeResponse localReturnSchemeResponse1 = new LocalReturnSchemeResponse();
		localReturnSchemeResponse1.setPeriod(2);
		localReturnSchemeResponse1.setIsReturned("1");
		localReturnSchemeResponse1.setIsOverdue("0");
		localReturnSchemeResponse1.setRepayDate(DateUtil.stringConvertDate("2014-02-27"));

		LocalReturnSchemeResponse localReturnSchemeResponse2 = new LocalReturnSchemeResponse();
		localReturnSchemeResponse2.setPeriod(3);
		localReturnSchemeResponse2.setIsReturned("0");
		localReturnSchemeResponse2.setIsOverdue("1");
		localReturnSchemeResponse2.setRepayDate(DateUtil.stringConvertDate("2014-03-27"));
		localReturnSchemeResponse2.setOverDays("2");

		LocalReturnSchemeResponse localReturnSchemeResponse3 = new LocalReturnSchemeResponse();
		localReturnSchemeResponse3.setPeriod(4);
		localReturnSchemeResponse3.setIsReturned("0");
		localReturnSchemeResponse3.setIsOverdue("1");
		localReturnSchemeResponse3.setRepayDate(DateUtil.stringConvertDate("2014-04-27"));
		localReturnSchemeResponse3.setOverDays("13");

		LocalReturnSchemeResponse localReturnSchemeResponse4 = new LocalReturnSchemeResponse();
		localReturnSchemeResponse4.setPeriod(5);
		localReturnSchemeResponse4.setIsReturned("0");
		localReturnSchemeResponse4.setIsOverdue("0");
		localReturnSchemeResponse4.setRepayDate(DateUtil.stringConvertDate("2014-05-27"));

		LocalReturnSchemeResponse localReturnSchemeResponse5 = new LocalReturnSchemeResponse();
		localReturnSchemeResponse5.setPeriod(6);
		localReturnSchemeResponse5.setIsReturned("0");
		localReturnSchemeResponse5.setIsOverdue("0");
		localReturnSchemeResponse5.setRepayDate(DateUtil.stringConvertDate("2014-06-27"));
		list.add(localReturnSchemeResponse0);
		list.add(localReturnSchemeResponse1);
		list.add(localReturnSchemeResponse2);
		list.add(localReturnSchemeResponse3);
		list.add(localReturnSchemeResponse4);
		return list;
	}

	/**
	 * 构造一个符合自身系统的还款计划对象
	 * 
	 * @author 郝强
	 * @param returnSchemeResponse
	 *            webservice接口返回的对象
	 * @return NewReturnPlan构造新的还款计划对象
	 */
	private List<LocalReturnSchemeResponse> transforReturnSchemeResponse(ReturnSchemeResponse returnSchemeResponse) {
		ReturnSchemeResult returnSchemeResult = returnSchemeResponse.getReturnSchemeResult();
		if (returnSchemeResult == null) {
			// System.out.println("returnSchemeResult为空！！！");
			throw new AppBusinessException("returnSchemeResult为空！！！");
		}
		List<LocalReturnSchemeResponse> localReturnSchemeResponses = new ArrayList<LocalReturnSchemeResponse>();
		String getCode = returnSchemeResult.getCode();
		String getMessage = returnSchemeResult.getMessage();
		// System.out.println("响应码：" + getCode);
		// System.out.println("响应信息：" + getMessage);

		ApsList apsList = returnSchemeResult.getApsList();
		List<ActualPeriodScheduleDTO> actualPeriodScheduleDTOs = apsList.getActualPeriodScheduleDTO();

		for (ActualPeriodScheduleDTO a : actualPeriodScheduleDTOs) {
			/** 返回的ActualPeriodScheduleDTO对象 **/
			String getPeriod = a.getPeriod();// 分期
			String getRepayDate = a.getRepayDate();// 还款日
			String getIsOverdue = a.getIsOverdue();// 是否逾期
			String getIsReturned = a.getIsReturned();// 是否还清
			String getReceivableMoney = a.getReceivableMoney();// 应收总金额
			String getReceivablePrincipal = a.getReceivablePrincipal();// 应收本金
			String getReceivableInterest = a.getReceivableInterest();// 应收利息
			String getReceivedMoney = a.getReceivedMoney();// 实收总金额
			String getReceivedPrincipal = a.getReceivedPrincipal();// 实收本金
			String getReceivedInterest = a.getReceivedInterest();// 实收利息

			String getReceivedlft = a.getReceivedlft();
			String getReceivablelft = a.getReceivablelft();
			String getReceivableMarrearage = a.getReceivableMarrearage();
			String getReceivableArrearage = a.getReceivableArrearage();
			String getOverDays = a.getOverDays();

			String getFQFWFReceivableCharge = "0"; // 应收分期服务费
			String getFQFWFReceivedCharge = "0"; // 实收分期服务费
			PeriodChargeList periodChargeList = a.getPeriodChargeList();// 分期服务费列表
			List<ActualChargeInfoDTO> actualChargeInfoDTOs = periodChargeList.getActualChargeInfoDTO();
			if (CommonUtil.isNotEmpty(actualChargeInfoDTOs)) {
				for (ActualChargeInfoDTO actualChargeInfoDTO : actualChargeInfoDTOs) {
					if ("FQFWF".equals(actualChargeInfoDTO.getChargeTypeCode())) {
						getFQFWFReceivableCharge = actualChargeInfoDTO.getReceivableCharge();
						getFQFWFReceivedCharge = actualChargeInfoDTO.getReceivedCharge();
					}
				}
			}
			String getFXReceivableCharge = "0";// 应收罚息
			String getFXReceivedCharge = "0";// 实收罚息
			String getZNJReceivableCharge = "0";// 应收滞纳金
			String getZNJReceivedCharge = "0";// 实收滞纳金
			OverdueChargeList chargeList = a.getOverdueChargeList();// 逾期费用列表
			List<OverdueChargeInfoDTO> overdueChargeInfoDTOs = chargeList.getOverdueChargeInfoDTO();
			if (CommonUtil.isNotEmpty(overdueChargeInfoDTOs)) {
				for (OverdueChargeInfoDTO overdueChargeInfoDTO : overdueChargeInfoDTOs) {
					if ("FX".equals(overdueChargeInfoDTO.getOChargeTypeCode())) {
						getFXReceivableCharge = overdueChargeInfoDTO.getOReceivableCharge();
						getFXReceivedCharge = overdueChargeInfoDTO.getOReceivedCharge();
					} else if ("ZNJ".equals(overdueChargeInfoDTO.getOChargeTypeCode())) {
						getZNJReceivableCharge = overdueChargeInfoDTO.getOReceivableCharge();
						getZNJReceivedCharge = overdueChargeInfoDTO.getOReceivedCharge();
					}
				}
			}

			/** 生成新的LocalReturnSchemeResponse对象 **/

			// System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDate::::::::::::"
			// + getRepayDate);

			LocalReturnSchemeResponse localReturnSchemeResponse = new LocalReturnSchemeResponse();
			localReturnSchemeResponse.setPeriod(Integer.parseInt(getPeriod == null ? "0" : getPeriod));
			localReturnSchemeResponse.setRepayDate(DateUtil.stringConvertDate(getRepayDate == null ? "0000-00-00" : getRepayDate));
			localReturnSchemeResponse.setIsOverdue(getIsOverdue == null ? "0" : getIsOverdue);

			localReturnSchemeResponse.setReceivableMoney(new BigDecimal(getReceivableMoney == null ? "0" : getReceivableMoney));
			localReturnSchemeResponse.setReceivablePrincipal(new BigDecimal(getReceivablePrincipal == null ? "0" : getReceivablePrincipal));
			localReturnSchemeResponse.setReceivableInterest(new BigDecimal(getReceivableInterest == null ? "0" : getReceivableInterest));
			localReturnSchemeResponse.setReceivedMoney(new BigDecimal(getReceivedMoney == null ? "0" : getReceivedMoney));
			localReturnSchemeResponse.setReceivedPrincipal(new BigDecimal(getReceivedPrincipal == null ? "0" : getReceivedPrincipal));
			localReturnSchemeResponse.setReceivedInterest(new BigDecimal(getReceivedInterest == null ? "0" : getReceivedInterest));

			localReturnSchemeResponse.setIsReturned(getIsReturned == null ? "0" : getIsReturned);

			localReturnSchemeResponse.setfQFWFReceivableCharge(new BigDecimal(getFQFWFReceivableCharge == null ? "0" : getFQFWFReceivableCharge));
			localReturnSchemeResponse.setfQFWFReceivedCharge(new BigDecimal(getFQFWFReceivedCharge == null ? "0" : getFQFWFReceivedCharge));
			localReturnSchemeResponse.setfXReceivableCharge(new BigDecimal(getFXReceivableCharge == null ? "0" : getFXReceivableCharge));
			localReturnSchemeResponse.setfXReceivedCharge(new BigDecimal(getFXReceivedCharge == null ? "0" : getFXReceivedCharge));
			localReturnSchemeResponse.setzNJReceivableCharge(new BigDecimal(getZNJReceivableCharge == null ? "0" : getZNJReceivableCharge));
			localReturnSchemeResponse.setzNJReceivedCharge(new BigDecimal(getZNJReceivedCharge == null ? "0" : getZNJReceivedCharge));

			localReturnSchemeResponse.setReceivedlft(new BigDecimal(getReceivedlft == null ? "0" : getReceivedlft));
			localReturnSchemeResponse.setReceivablelft(new BigDecimal(getReceivablelft == null ? "0" : getReceivablelft));
			localReturnSchemeResponse.setReceivableMarrearage(new BigDecimal(getReceivableMarrearage == null ? "0" : getReceivableMarrearage));
			localReturnSchemeResponse.setReceivableArrearage(new BigDecimal(getReceivableArrearage == null ? "0" : getReceivableArrearage));
			localReturnSchemeResponse.setOverDays(getOverDays == null ? "0" : getOverDays);

			localReturnSchemeResponses.add(localReturnSchemeResponse);
		}
		// 如果返回正确则需要进行的是把返回的对象重新封装成本地的domain类

		ReturnDerateList returnDerateList = returnSchemeResult.getReturnDerateList();

		if (returnDerateList != null) {
			List<ReturnDerateListDTO> returnDerateListDTOs = returnDerateList.getReturnDerateListDTO();

			if (CommonUtil.isNotEmpty(returnDerateListDTOs)) {
				for (ReturnDerateListDTO returnDerateListDTO : returnDerateListDTOs) {
					String getReturnDate = returnDerateListDTO.getReturnDate();
					Date compareDate = DateUtil.stringConvertDate(getReturnDate == null ? "0000-00-00" : getReturnDate);
					for (LocalReturnSchemeResponse localReturnSchemeResponse : localReturnSchemeResponses) {
						Date getRepayDate = localReturnSchemeResponse.getRepayDate();

						if (compareDate.compareTo(getRepayDate) == 0) {

							// String getReturnDate = returnDerateListDTO.
							// getReturnDate();
							String getDForfeit = returnDerateListDTO.getDForfeit();
							String getDLatefee = returnDerateListDTO.getDLatefee();
							String getFDate = returnDerateListDTO.getFDate();
							String getLDate = returnDerateListDTO.getLDate();
							String getDReason = returnDerateListDTO.getDReason();
							String getNDate = returnDerateListDTO.getNDate();

							localReturnSchemeResponse.setReturnDate(DateUtil.stringConvertDate(getReturnDate == null ? "0000-00-00" : getReturnDate));
							localReturnSchemeResponse.setdForfeit(new BigDecimal(getDForfeit == null ? "0" : getDForfeit));
							localReturnSchemeResponse.setdLatefee(new BigDecimal(getDLatefee == null ? "0" : getDLatefee));
							localReturnSchemeResponse.setfDate(DateUtil.stringConvertDate(getFDate == null ? "0000-00-00" : getFDate));
							localReturnSchemeResponse.setlDate(DateUtil.stringConvertDate(getLDate == null ? "0000-00-00" : getLDate));
							localReturnSchemeResponse.setdReason(getDReason);
							localReturnSchemeResponse.setnDate(DateUtil.stringConvertDate(getNDate == null ? "0000-00-00" : getNDate));
						}
					}
				}
			}
		}

		// 最后返回还款计划
		return localReturnSchemeResponses;
	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.3 查询逾期合同
	 * @param localOfficeIdDTOList
	 *            List<LocalOfficeIdDTO>
	 * @return List<CreditApplication>
	 * @version v1.0 2013-5-15
	 */
	public LocalOverdueInfoResponse overdueInfo(List<LocalOfficeIdDTO> localOfficeIdDTOList) {

		// 进行循环调用

		LocalOverdueInfoResponse localOverdueInfoResponse = new LocalOverdueInfoResponse();
		List<LocalOfficeIdDTO> localOfficeIdDTOListTemp = new ArrayList<LocalOfficeIdDTO>();
		int no = 0;
		for (LocalOfficeIdDTO localOfficeIdDTO : localOfficeIdDTOList) {
			localOfficeIdDTOListTemp.add(localOfficeIdDTO);
			no++;
			if (!(no < 5)) {
				recursionOverdueInfo(localOfficeIdDTOListTemp, localOverdueInfoResponse);
				localOfficeIdDTOListTemp.clear();
				no = 0;
			}
		}
		if (CommonUtil.isNotEmpty(localOfficeIdDTOListTemp)) {
			recursionOverdueInfo(localOfficeIdDTOListTemp, localOverdueInfoResponse);
		}

		return localOverdueInfoResponse;

	}

	private LocalOverdueInfoResponse recursionOverdueInfo(List<LocalOfficeIdDTO> localOfficeIdDTOList, LocalOverdueInfoResponse localOverdueInfoResponse) {
		// 记Log日志
		// 构造一个用于调用webservice接口的对象
		OverdueInfoRequest overdueInfoRequest = analysisLocalOverdueInfoRequest(localOfficeIdDTOList);
		log.info("**************RuralBusyService.overdueInfo(overdueInfoRequest) requestParam******************");
		log.info(JsonUtil.getJackson(overdueInfoRequest));
		// 调用clientApply接口
		OverdueInfoResponse overdueInfoResponse = ruralBusyService.overdueInfo(overdueInfoRequest);
		log.info("**************RuralBusyService.overdueInfo(overdueInfoResponse) responseParam******************");
		log.info(JsonUtil.getJackson(overdueInfoResponse));

		LocalOverdueInfoResponse localOverdueInfoResponseTemp = transforReturnSchemeResponseOverdueInfoResponse(overdueInfoResponse);
		// LocalOverdueInfoResponse localOverdueInfoResponseTemp = tttttt();
		int getaOverdueCountTotle = localOverdueInfoResponse.getaOverdueCountTotle();
		List<Long> getCreditApplicationIdList = localOverdueInfoResponse.getCreditApplicationIdList();
		Map<Long, OverDueObj> getOverDueObjMap = localOverdueInfoResponse.getOverDueObjMap();
		int getsOverdueCount = localOverdueInfoResponse.getsOverdueCount();

		int tempaOverdueCountTotle = localOverdueInfoResponseTemp.getaOverdueCountTotle();
		List<Long> tempCreditApplicationIdList = localOverdueInfoResponseTemp.getCreditApplicationIdList();
		Map<Long, OverDueObj> tempOverDueObjMap = localOverdueInfoResponseTemp.getOverDueObjMap();
		int tempsOverdueCount = localOverdueInfoResponseTemp.getsOverdueCount();

		localOverdueInfoResponse.setaOverdueCountTotle(getaOverdueCountTotle + tempaOverdueCountTotle);
		if (CommonUtil.isNotEmpty(getCreditApplicationIdList)) {
			getCreditApplicationIdList.addAll(tempCreditApplicationIdList);
		} else {
			localOverdueInfoResponse.setCreditApplicationIdList(tempCreditApplicationIdList);
		}
		if (getOverDueObjMap != null && getOverDueObjMap.size() > 0) {
			getOverDueObjMap.putAll(tempOverDueObjMap);
		} else {
			localOverdueInfoResponse.setOverDueObjMap(tempOverDueObjMap);
		}
		localOverdueInfoResponse.setsOverdueCount(getsOverdueCount + tempsOverdueCount);
		return localOverdueInfoResponse;
	}

	private LocalOverdueInfoResponse tttttt() {
		LocalOverdueInfoResponse localOverdueInfoResponse = new LocalOverdueInfoResponse();
		List<Long> creditApplicationIdList = new ArrayList<Long>();
		Long a = 56991l;
		Long b = 56993l;
		Long c = 44332l;
		Long d = 56659l;
		Long e = 55428l;
		creditApplicationIdList.add(a);
		creditApplicationIdList.add(b);
		creditApplicationIdList.add(c);
		creditApplicationIdList.add(d);
		creditApplicationIdList.add(e);
		localOverdueInfoResponse.setCreditApplicationIdList(creditApplicationIdList);
		localOverdueInfoResponse.setaOverdueCountTotle(5);
		localOverdueInfoResponse.setsOverdueCount(11);
		Map<Long, OverDueObj> map = new HashMap<Long, OverDueObj>();
		OverDueObj overDueObja = new OverDueObj();
		overDueObja.setaOverdueCount(1);
		overDueObja.setaOverdueMoney(new BigDecimal(1000));
		overDueObja.setaOverdueStart(new Date());
		overDueObja.setCreditApplicationId(a);
		overDueObja.setOverdueDayCount(1);
		overDueObja.setSysId("");

		OverDueObj overDueObjb = new OverDueObj();
		overDueObjb.setaOverdueCount(1);
		overDueObjb.setaOverdueMoney(new BigDecimal(1000));
		overDueObjb.setaOverdueStart(new Date());
		overDueObjb.setCreditApplicationId(b);
		overDueObjb.setOverdueDayCount(1);
		overDueObjb.setSysId("");

		OverDueObj overDueObjc = new OverDueObj();
		overDueObjc.setaOverdueCount(1);
		overDueObjc.setaOverdueMoney(new BigDecimal(1000));
		overDueObjc.setaOverdueStart(new Date());
		overDueObjc.setCreditApplicationId(c);
		overDueObjc.setOverdueDayCount(1);
		overDueObjc.setSysId("");

		OverDueObj overDueObjd = new OverDueObj();
		overDueObjd.setaOverdueCount(1);
		overDueObjd.setaOverdueMoney(new BigDecimal(1000));
		overDueObjd.setaOverdueStart(new Date());
		overDueObjd.setCreditApplicationId(d);
		overDueObjd.setOverdueDayCount(1);
		overDueObjd.setSysId("");

		OverDueObj overDueObje = new OverDueObj();
		overDueObje.setaOverdueCount(1);
		overDueObje.setaOverdueMoney(new BigDecimal(1000));
		overDueObje.setaOverdueStart(new Date());
		overDueObje.setCreditApplicationId(e);
		overDueObje.setOverdueDayCount(1);
		overDueObje.setSysId("");

		map.put(a, overDueObja);
		map.put(b, overDueObjb);
		map.put(c, overDueObjc);
		map.put(d, overDueObjd);
		map.put(e, overDueObje);
		localOverdueInfoResponse.setOverDueObjMap(map);
		return localOverdueInfoResponse;
	}

	/**
	 * 分析对象是否为空 为空则 生成默认的对象 不为空则根据入参组装对象
	 * 
	 * @author 郝强
	 * @param localOfficeIdDTOList
	 *            List<LocalOfficeIdDTO>
	 * @return OverdueInfoRequest
	 */
	private OverdueInfoRequest analysisLocalOverdueInfoRequest(List<LocalOfficeIdDTO> localOfficeIdDTOList) {

		// 先判断传入的localOfficeIdListDTO列表是不是为空
		if (CommonUtil.isEmpty(localOfficeIdDTOList)) {
			// 如果为空则查询当前登陆信息找到当前分公司和当前信贷员
			List<DepartmentEntity> departmentEntities = smpWSUtil.getDepartmentList();
			if (departmentEntities.size() == 1) {
				Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
				if (userAuthoritiesSet.contains("ROLE_LOAN_OFFICER")) {// 如果是客户经理
					String areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString();
					String userId = SpringSecurityUtils.getCurrentUser().getUserId();
					LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
					localOfficeIdDTO.setOfficeId(areaDepartmentId);
					List<LocalSellIdDTO> localSellIdDTOsList = new ArrayList<LocalSellIdDTO>();
					LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
					localSellIdDTO.setSellId(userId);
					localSellIdDTOsList.add(localSellIdDTO);
					localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
					localOfficeIdDTOList.add(localOfficeIdDTO);
				} else {// 如果是营业部经理 和风险经理
					String areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString();
					LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
					localOfficeIdDTO.setOfficeId(areaDepartmentId);
					localOfficeIdDTOList.add(localOfficeIdDTO);
				}
			} else {
				for (DepartmentEntity departmentEntity : departmentEntities) {
					LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
					localOfficeIdDTO.setOfficeId(departmentEntity.getDepartmentId());
					localOfficeIdDTOList.add(localOfficeIdDTO);
				}
			}
			// 组装调用webservice的入参
			OverdueInfoRequest overdueInfoRequest = constructOverdueInfoRequest(localOfficeIdDTOList);
			return overdueInfoRequest;
		} else {
			// 如果不为空则根据传入的参数调用webservice接口
			OverdueInfoRequest overdueInfoRequest = constructOverdueInfoRequest(localOfficeIdDTOList);
			return overdueInfoRequest;
		}

	}

	/**
	 * 组装用于调用调用1.3 查询逾期合同webservice接口的对象
	 * 
	 * @author 郝强
	 * @param localOfficeIdDTOList
	 *            本地的用于调用1.3 查询逾期合同webservice接口的对象
	 * @return webservice生成的对象
	 */
	private OverdueInfoRequest constructOverdueInfoRequest(List<LocalOfficeIdDTO> localOfficeIdDTOList) {

		OverdueInfoRequest overdueInfoRequest = new OverdueInfoRequest();
		OfficeIdList officeIdList = new OfficeIdList();
		List<OfficeIdListDTO> officeIdListDTOList = officeIdList.getOfficeIdListDTO();

		for (LocalOfficeIdDTO l : localOfficeIdDTOList) {
			OfficeIdListDTO officeIdListDTO = new OfficeIdListDTO();
			SellIdList sellIdList = new SellIdList();
			List<SellIdListDTO> sellIdListDTOList = sellIdList.getSellIdListDTO();
			if (CommonUtil.isNotEmpty(l.getLocalSellIdDTOs())) {
				for (LocalSellIdDTO s : l.getLocalSellIdDTOs()) {
					SellIdListDTO sellIdListDTO = new SellIdListDTO();
					sellIdListDTO.setSellId(s.getSellId());
					sellIdListDTOList.add(sellIdListDTO);
				}
			}
			officeIdListDTO.setSellIdList(sellIdList);
			officeIdListDTO.setOfficeId(l.getOfficeId());
			officeIdListDTOList.add(officeIdListDTO);
		}
		overdueInfoRequest.setOfficeIdList(officeIdList);
		return overdueInfoRequest;
	}

	/**
	 * 将逾期合同返回的对象转变成信贷申请单对象
	 * 
	 * @author 郝强
	 * @param overdueInfoResponse
	 *            逾期合同返回的对象
	 * @return 本地的信贷申请对象
	 */
	private LocalOverdueInfoResponse transforReturnSchemeResponseOverdueInfoResponse(OverdueInfoResponse overdueInfoResponse) {

		LocalOverdueInfoResponse localOverdueInfoResponse = new LocalOverdueInfoResponse();
		int sOverdueCount = 0;
		int aOverdueCountTotle = 0;

		OverdueInfoResult overdueInfoResult = overdueInfoResponse.getOverdueInfoResult();
		com.creditease.rc.app.credit.OverdueInfoResult.ApplyList applyList = overdueInfoResult.getApplyList();
		List<ApplyListsDTO> applyListsDTOs = applyList.getApplyListsDTO();
		String getCode = overdueInfoResult.getCode();

		if (!"0".equals(getCode)) {
			throw new AppBusinessException("调用1.3 查询逾期合同出错!");
		}
		List<Long> creditApplicationIdList = new ArrayList<Long>();
		List<OverDueObj> overDueObjs = new ArrayList<OverDueObj>();
		List<String> uUUIdList = new ArrayList<String>();
		Map<String, OverDueObj> tempMap = new HashMap<String, OverDueObj>();
		Map<Long, OverDueObj> overDueObjMap = new HashMap<Long, OverDueObj>();
		if (CommonUtil.isNotEmpty(applyListsDTOs)) {
			for (ApplyListsDTO applyListsDTO : applyListsDTOs) {
				RSellIdList sellIdList = applyListsDTO.getRSellIdList();
				if (sellIdList != null) {
					List<RSellIdListDTO> rSellIdListDTOList = sellIdList.getRSellIdListDTO();
					if (CommonUtil.isNotEmpty(rSellIdListDTOList)) {
						for (RSellIdListDTO sellIdListDTO : rSellIdListDTOList) {
							String getSOverdueCount = sellIdListDTO.getSOverdueCount();
							if (CommonUtil.isNotEmpty(getSOverdueCount)) {
								sOverdueCount = sOverdueCount + Integer.parseInt(getSOverdueCount);
							}
							ApplyIdList applyIdList = sellIdListDTO.getApplyIdList();
							if (applyIdList != null) {
								List<ApplyIdListDTO> applyIdListDTOs = applyIdList.getApplyIdListDTO();
								if (CommonUtil.isNotEmpty(applyIdListDTOs)) {
									for (ApplyIdListDTO applyIdListDTO : applyIdListDTOs) {
										String getAOverdueCount = applyIdListDTO.getAOverdueCount();
										if (CommonUtil.isNotEmpty(getAOverdueCount)) {
											aOverdueCountTotle = aOverdueCountTotle + Integer.parseInt(applyIdListDTO.getAOverdueCount());
										}
										String getApplyId = applyIdListDTO.getApplyId();
										if (CommonUtil.isNotEmpty(getApplyId)) {
											uUUIdList.add(getApplyId);
											OverDueObj overDueObj = new OverDueObj();
											overDueObj.setSysId(getApplyId);
											String getAOverdueStart = applyIdListDTO.getAOverdueStart();
											overDueObj.setaOverdueStart(CommonUtil.isEmpty(getAOverdueStart) ? null : DateUtil.stringConvertDate(getAOverdueStart));
											String getAOverdueMoney = applyIdListDTO.getAOverdueMoney();
											overDueObj.setaOverdueMoney(CommonUtil.isEmpty(getAOverdueMoney) ? null : new BigDecimal(getAOverdueMoney));
											String getAOverdueCountg = applyIdListDTO.getAOverdueCount();
											overDueObj.setaOverdueCount(CommonUtil.isEmpty(getAOverdueCountg) ? null : Integer.valueOf(getAOverdueCountg));
											String getOverdueDayCount = applyIdListDTO.getOverdueDayCount();
											System.out.println(getOverdueDayCount);
											overDueObj.setOverdueDayCount(CommonUtil.isEmpty(getOverdueDayCount) ? null : Integer.valueOf(getOverdueDayCount));
											tempMap.put(getApplyId, overDueObj);
										}
									}
								}
							}
						}
					}
				}

			}

			if (CommonUtil.isNotEmpty(uUUIdList)) {
				overDueObjs = creditApplicationDAO.queryCreditApplicationVoListByUUIdList(uUUIdList);
				// overDueObjMap
				if (CommonUtil.isNotEmpty(overDueObjs)) {
					for (OverDueObj overDueObj : overDueObjs) {
						String getSysId = overDueObj.getSysId();
						Long getCreditApplicationId = overDueObj.getCreditApplicationId();
						OverDueObj temp = tempMap.get(getSysId);
						temp.setCreditApplicationId(getCreditApplicationId);
						overDueObjMap.put(getCreditApplicationId, temp);
						creditApplicationIdList.add(getCreditApplicationId);
					}
				}
			}
		}
		localOverdueInfoResponse.setaOverdueCountTotle(aOverdueCountTotle);
		localOverdueInfoResponse.setsOverdueCount(sOverdueCount);
		localOverdueInfoResponse.setOverDueObjMap(overDueObjMap);
		localOverdueInfoResponse.setCreditApplicationIdList(creditApplicationIdList);
		return localOverdueInfoResponse;
	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.4 还款金额查询
	 * @param parameters
	 *            LocalReturnAmountRequest
	 * @return LocalReturnAmountResult
	 * @version v1.1 2013-5-20
	 */
	public LocalReturnAmountResult returnAmount(LocalReturnAmountRequest parameters) {
		// 构造一个用于调用webservice接口的对象
		ReturnAmountRequest returnAmountRequest = constructReturnAmountRequest(parameters);

		// 记Log日志
		log.info("**************RuralBusyService.returnAmount(returnAmountRequest) requestParam******************");
		log.info(JsonUtil.getJackson(returnAmountRequest));
		// 调用clientApply接口
		ReturnAmountResponse returnAmountResponse = ruralBusyService.returnAmount(returnAmountRequest);
		log.info("**************RuralBusyService.returnAmount(returnAmountResponse) responseParam******************");
		log.info(JsonUtil.getJackson(returnAmountResponse));

		// System.out.println(returnAmountResponse.getReturnAmountResult().getCode());
		// System.out.println(returnAmountResponse.getReturnAmountResult().getMessage());
		LocalReturnAmountResult localReturnAmountResult = transforReturnAmountResponse(returnAmountResponse);
		return localReturnAmountResult;
	}

	/**
	 * 构造一个用于调用webservice接口的对象
	 * 
	 * @author 郝强
	 * @param parameters
	 *            LocalReturnAmountRequest本地的对象
	 * @return ReturnAmountRequest
	 */
	private ReturnAmountRequest constructReturnAmountRequest(LocalReturnAmountRequest parameters) {

		if (!ObjectUtil.checkObjectIsPerfect(parameters)) {
			throw new AppBusinessException("传入的LocalReturnAmountRequest对象有的值为空！");
		}
		String sysGuid = creditApplicationDAO.queryUUid(parameters.getCreditapplicationId());
		// 对于错误信息进行处理
		if (sysGuid == null) {
			// System.out.println("=======sysGuid是空！！！！");
			throw new AppBusinessException("sysGuid是空！！！！");
		}
		ReturnAmountRequest returnAmountRequest = new ReturnAmountRequest();
		returnAmountRequest.setApplyId(sysGuid);
		returnAmountRequest.setIfPayAhead(parameters.getIfPayAhead());

		returnAmountRequest.setRegistReturnTime(DateUtil.dateConvertString(parameters.getRegistReturnTime()));
		return returnAmountRequest;
	}

	/**
	 * 将webservice结果转变成本地的对象
	 * 
	 * @author 郝强
	 * @param returnAmountResponse
	 *            webservice结果
	 * @return LocalReturnAmountResult 本地的对象
	 */
	private LocalReturnAmountResult transforReturnAmountResponse(ReturnAmountResponse returnAmountResponse) {

		ReturnAmountResult returnAmountResult = returnAmountResponse.getReturnAmountResult();
		LocalReturnAmountResult localReturnAmountResult = new LocalReturnAmountResult();
		localReturnAmountResult.setRetCode(returnAmountResult.getCode());
		localReturnAmountResult.setRetInfo(returnAmountResult.getMessage());

		// 正常还款列表
		AppointSchedule appointSchedule = returnAmountResult.getAppointSchedule();
		List<AppointScheduleDTO> appointScheduleDTOs = appointSchedule.getAppointScheduleDTO();
		// 提前还款列表
		AllAheadSchedule allAheadSchedule = returnAmountResult.getAllAheadSchedule();
		List<AllAheadScheduleDTO> allAheadScheduleDTOs = allAheadSchedule.getAllAheadScheduleDTO();

		if (CommonUtil.isNotEmpty(appointScheduleDTOs)) {
			AppointScheduleDTO appointScheduleDTO = appointScheduleDTOs.get(0);

			String totalReceivableMoney = appointScheduleDTO.getTotalReceivableMoney();
			String totalReceivablePrincipal = appointScheduleDTO.getTotalReceivablePrincipal();
			String totalReceivableInterest = appointScheduleDTO.getTotalReceivableInterest();

			totalReceivableMoney = "".equals(totalReceivableMoney) || totalReceivableMoney == null ? "0" : totalReceivableMoney; // 应收总金额
			totalReceivablePrincipal = "".equals(totalReceivablePrincipal) || totalReceivablePrincipal == null ? "0" : totalReceivablePrincipal; // 应收总本金
			totalReceivableInterest = "".equals(totalReceivableInterest) || totalReceivableInterest == null ? "0" : totalReceivableInterest; // 应收总利息

			String totalFQFWFReceivableCharge = "";
			String totalFXReceivableCharge = "";
			String totalZNJReceivableCharge = "";

			TotalPeriodChargeList totalPeriodChargeList = appointScheduleDTO.getTotalPeriodChargeList();
			List<AppointChargeInfoDTO> appointChargeInfoDTOs = totalPeriodChargeList.getAppointChargeInfoDTO();
			if (CommonUtil.isNotEmpty(appointChargeInfoDTOs)) {
				for (AppointChargeInfoDTO appointChargeInfoDTO : appointChargeInfoDTOs) {
					if ("FQFWF".equals(appointChargeInfoDTO.getChargeTypeCode())) {
						totalFQFWFReceivableCharge = appointChargeInfoDTO.getReceivableCharge();
					}
				}
			}
			totalFQFWFReceivableCharge = "".equals(totalFQFWFReceivableCharge) || totalFQFWFReceivableCharge == null ? "0" : totalFQFWFReceivableCharge; // 应收分期服务费

			TotalOverdueChargeList totalOverdueChargeList = appointScheduleDTO.getTotalOverdueChargeList();
			List<AppointChargeInfoDTO> appointChargeInfoDTOs2 = totalOverdueChargeList.getAppointChargeInfoDTO();

			if (CommonUtil.isNotEmpty(appointChargeInfoDTOs2)) {
				for (AppointChargeInfoDTO appointChargeInfoDTO : appointChargeInfoDTOs2) {
					if ("FX".equals(appointChargeInfoDTO.getChargeTypeCode())) {
						totalFXReceivableCharge = appointChargeInfoDTO.getReceivableCharge();
					} else if ("ZNJ".equals(appointChargeInfoDTO.getChargeTypeCode())) {
						totalZNJReceivableCharge = appointChargeInfoDTO.getReceivableCharge();
					}
				}
			}
			totalFXReceivableCharge = "".equals(totalFXReceivableCharge) || totalFXReceivableCharge == null ? "0" : totalFXReceivableCharge; // 应收罚息

			totalZNJReceivableCharge = "".equals(totalZNJReceivableCharge) || totalZNJReceivableCharge == null ? "0" : totalZNJReceivableCharge; // 应收滞纳金

			localReturnAmountResult.setTotalReceivableMoney(new BigDecimal(totalReceivableMoney));
			localReturnAmountResult.setTotalReceivablePrincipal(new BigDecimal(totalReceivablePrincipal));
			localReturnAmountResult.setTotalReceivableInterest(new BigDecimal(totalReceivableInterest));
			localReturnAmountResult.setTotalFQFWFReceivableCharge(new BigDecimal(totalFQFWFReceivableCharge));
			localReturnAmountResult.setTotalFXReceivableCharge(new BigDecimal(totalFXReceivableCharge));
			localReturnAmountResult.setTotalZNJReceivableCharge(new BigDecimal(totalZNJReceivableCharge));
		}

		if (CommonUtil.isNotEmpty(allAheadScheduleDTOs)) {
			AllAheadScheduleDTO allAheadScheduleDTO = allAheadScheduleDTOs.get(0);
			String totalPrincipal = allAheadScheduleDTO.getTotalPrincipal();
			String totalInterest = allAheadScheduleDTO.getTotalInterest();
			String totalOverdueFines = allAheadScheduleDTO.getTotalOverdueFines();
			String totalPeriodCharge = allAheadScheduleDTO.getTotalPeriodCharge();
			String totalOverdueInterest = allAheadScheduleDTO.getTotalOverdueInterest();
			String currentPrincipal = allAheadScheduleDTO.getCurrentPrincipal();
			String currentInterest = allAheadScheduleDTO.getCurrentInterest();
			String currentPeriodCharge = allAheadScheduleDTO.getCurrentPeriodCharge();
			String surplusPrincipal = allAheadScheduleDTO.getSurplusPrincipal();
			String behindInterests = allAheadScheduleDTO.getBehindInterests();
			String charge = allAheadScheduleDTO.getCharge();
			String penalbond = allAheadScheduleDTO.getPenalbond();
			String total = allAheadScheduleDTO.getTotal();

			localReturnAmountResult.setTotalPrincipal(new BigDecimal("".equals(totalPrincipal) || totalPrincipal == null ? "0" : totalPrincipal));
			localReturnAmountResult.setTotalInterest(new BigDecimal("".equals(totalInterest) || totalInterest == null ? "0" : totalInterest));
			localReturnAmountResult.setTotalOverdueFines(new BigDecimal("".equals(totalOverdueFines) || totalOverdueFines == null ? "0" : totalOverdueFines));
			localReturnAmountResult.setTotalPeriodCharge(new BigDecimal("".equals(totalPeriodCharge) || totalPeriodCharge == null ? "0" : totalPeriodCharge));
			localReturnAmountResult.setTotalOverdueInterest(new BigDecimal("".equals(totalOverdueInterest) || totalOverdueInterest == null ? "0" : totalOverdueInterest));
			localReturnAmountResult.setCurrentPrincipal(new BigDecimal("".equals(currentPrincipal) || currentPrincipal == null ? "0" : currentPrincipal));
			localReturnAmountResult.setCurrentInterest(new BigDecimal("".equals(currentInterest) || currentInterest == null ? "0" : currentInterest));
			localReturnAmountResult.setCurrentPeriodCharge(new BigDecimal("".equals(currentPeriodCharge) || currentPeriodCharge == null ? "0" : currentPeriodCharge));
			localReturnAmountResult.setSurplusPrincipal(new BigDecimal("".equals(surplusPrincipal) || surplusPrincipal == null ? "0" : surplusPrincipal));
			localReturnAmountResult.setBehindInterests(new BigDecimal("".equals(behindInterests) || behindInterests == null ? "0" : behindInterests));
			localReturnAmountResult.setCharge(new BigDecimal("".equals(charge) || charge == null ? "0" : charge));
			localReturnAmountResult.setPenalbond(new BigDecimal("".equals(penalbond) || penalbond == null ? "0" : penalbond));
			localReturnAmountResult.setTotal(new BigDecimal("".equals(total) || total == null ? "0" : total));
		}

		return localReturnAmountResult;

	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.5 预约还款
	 * @param receivedRecordIdList
	 *            List<Long>
	 * @param destineDate
	 *            Date
	 * @param remark
	 *            String
	 * @return ReserveReturnRequestVo
	 * @version v1.1 2013-5-21
	 */
	public ReserveReturnRequestVo reserveReturn(List<Long> receivedRecordIdList, Date destineDate, String remark) {
		ReserveReturnRequestVo reserveReturnRequestVo = new ReserveReturnRequestVo();
		// List<LocalReturnDTO> localReturnDTOList = new
		// ArrayList<LocalReturnDTO>();
		ReserveReturnRequest reserveReturnRequest = constructReserveReturnRequest(receivedRecordIdList, destineDate, reserveReturnRequestVo);
		// 记Log日志
		log.info("**************ruralBusyService.reserveReturn(reserveReturnRequest) requestParam******************");
		log.info(JsonUtil.getJackson(reserveReturnRequest));
		// 调用clientApply接口
		ReserveReturnResponse reserveReturnResponse = ruralBusyService.reserveReturn(reserveReturnRequest);
		log.info("**************ruralBusyService.reserveReturn(reserveReturnResponse) responseParam******************");
		log.info(JsonUtil.getJackson(reserveReturnResponse));
		// 转换对象
		reserveReturnRequestVo = transforReserveReturnResponse(reserveReturnRequestVo, reserveReturnResponse, remark);
		return reserveReturnRequestVo;

	}

	/**
	 * 用于调用预约还款的webservice接口对象
	 * 
	 * @author 郝强
	 * @param receivedRecordIdList
	 *            收款登记记录ID
	 * @param destineDate
	 *            预约还款时间
	 * @param reserveReturnRequestVo
	 *            ReserveReturnRequestVo
	 * @return 用于调用预约还款的webservice接口对象
	 */
	private ReserveReturnRequest constructReserveReturnRequest(List<Long> receivedRecordIdList, Date destineDate, ReserveReturnRequestVo reserveReturnRequestVo) {

		// 判断入参是不是都有值
		if (CommonUtil.isEmpty(receivedRecordIdList) || destineDate == null) {
			// System.out.println("Long receivedRecordId, Date destineDate入参不全！");
			throw new AppBusinessException("Long receivedRecordId, Date destineDate入参不全！");
		}

		String operatorId = SpringSecurityUtils.getCurrentUser().getUserId();
		List<LocalReturnDTO> localReturnDTOList = (List<LocalReturnDTO>) creditApplicationDAO.queryLocalReturnDTOList(receivedRecordIdList);

		ReturnList returnList = new ReturnList();
		List<ReturnListDTO> returnListDTOList = returnList.getReturnListDTO();

		ReserveReturnRequest reserveReturnRequest = new ReserveReturnRequest();
		reserveReturnRequest.setReturnList(null);
		Date date = new Date();
		for (LocalReturnDTO lr : localReturnDTOList) {
			ReturnListDTO returnListDTO = new ReturnListDTO();
			lr.setReserveId(financeMoneyDao.getBizId().toString());// 得到订单号id；也可用于贷后接口需要的预约编号，financemoney表中的另一个字段
			lr.setDestineDate(destineDate);
			returnListDTO.setReserveId(lr.getReserveId().toString());
			returnListDTO.setApplyId(lr.getApplyId());
			returnListDTO.setDestineDate(DateUtil.dateConvertString(destineDate, "yyyy-MM-dd HH:mm:ss"));
			returnListDTO.setDestineAmount(lr.getDestineAmount().toString());
			returnListDTO.setIfPayAhead(lr.getIfPayAhead());
			returnListDTO.setSignInfo(signInfoEncrypt(date));// md5加密
			returnListDTO.setReturnType(lr.getReturnType());
			returnListDTO.setBankBook(lr.getBankBook());
			returnListDTO.setBankAccountName(lr.getBankAccountName());
			returnListDTO.setBankAccount(lr.getBankAccount());
			returnListDTO.setBankName(lr.getBankName());
			returnListDTO.setBankIdnumber(lr.getBankIdnumber());
			returnListDTO.setSingleRtReceipt("1");
			returnListDTO.setSellId(operatorId);
			returnListDTOList.add(returnListDTO);
		}
		reserveReturnRequest.setReturnList(returnList);
		reserveReturnRequestVo.setLocalReturnDTOs(localReturnDTOList);
		return reserveReturnRequest;
	}

	/**
	 * 将调用预约还款接口的返回值转换成自身系统的对象
	 * 
	 * @author 郝强
	 * @param reserveReturnRequestVo
	 *            自身系统的对象
	 * @param reserveReturnResponse
	 *            调用预约还款接口的返回值
	 * @return ReserveReturnRequestVo
	 */
	private ReserveReturnRequestVo transforReserveReturnResponse(ReserveReturnRequestVo reserveReturnRequestVo, ReserveReturnResponse reserveReturnResponse, String remark) {
		String code = reserveReturnResponse.getRetCode();
		String info = reserveReturnResponse.getRetInfo();
		reserveReturnRequestVo.setSuccess("0".equals(code) ? true : false);
		reserveReturnRequestVo.setRemark(remark);
		reserveReturnRequestVo.setCode(code);
		reserveReturnRequestVo.setInfo(info);
		List<LocalReturnDTO> localReturnDTOs = reserveReturnRequestVo.getLocalReturnDTOs();
		ReturnIdList returnIdList = reserveReturnResponse.getReturnIdList();
		if (returnIdList != null) {
			List<ReturnIdListDTO> returnIdListDTOs = returnIdList.getReturnIdListDTO();
			for (ReturnIdListDTO returnIdListDTO : returnIdListDTOs) {
				String getReserveId = returnIdListDTO.getReserveId();
				String getReturnId = returnIdListDTO.getReturnId();
				for (LocalReturnDTO localReturnDTO : localReturnDTOs) {
					if (getReserveId.equals(localReturnDTO.getReserveId())) {
						localReturnDTO.setBizId(getReturnId);
						break;
					}
				}
			}
		}

		return reserveReturnRequestVo;
	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.6 信贷员ID变更
	 * @param creditapplicationIdList
	 *            List<Long>
	 * @param sellId
	 *            String
	 * @return boolean
	 * @version v1.1 2013-5-21
	 */
	public boolean modifySeller(List<Long> creditapplicationIdList, String sellId, String sellName) {

		ModifySellerRequest modifySellerRequest = constructModifySellerRequest(creditapplicationIdList, sellId, sellName);
		// 记Log日志
		log.info("**************RuralBusyService.modifySeller(modifySellerRequest) requestParam******************");
		log.info(JsonUtil.getJackson(modifySellerRequest));
		// 调用clientApply接口
		ModifySellerResponse modifySellerResponse = ruralBusyService.modifySeller(modifySellerRequest);
		log.info("**************RuralBusyService.modifySeller(returnAmountResponse) responseParam******************");
		log.info(JsonUtil.getJackson(modifySellerResponse));
		String code = modifySellerResponse.getRetCode();

		if ("0".equals(code)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 构造一个用于调用modifySeller接口入参的对象
	 * 
	 * @author 郝强
	 * @param creditapplicationIdList
	 *            业务申请单主键List
	 * @param sellId
	 *            信贷员ID
	 * @return ModifySellerRequest用于调用modifySeller接口入参的对象
	 */
	private ModifySellerRequest constructModifySellerRequest(List<Long> creditapplicationIdList, String sellId, String sellName) {
		if (CommonUtil.isEmpty(creditapplicationIdList)) {
			// System.out.println("业务申请单主键List为空！");
			throw new AppBusinessException("业务申请单主键List为空！");

		} else if (sellId == null || "".equals(sellId)) {
			// System.out.println("信贷员编号为空！");
			throw new AppBusinessException("信贷员编号为空！");
		}
		List<String> qUUidList = creditApplicationDAO.queryUUidList(creditapplicationIdList);

		ModifySellerRequest modifySellerRequest = new ModifySellerRequest();

		ApplyList applyList = new ApplyList();
		List<ApplyListDTO> applyListDTOList = applyList.getApplyListDTO();

		for (String s : qUUidList) {
			ApplyListDTO applyListDTO = new ApplyListDTO();
			applyListDTO.setApplyId(s);
			applyListDTO.setSellId(sellId);
			applyListDTO.setSellName(sellName);
			applyListDTOList.add(applyListDTO);
		}
		modifySellerRequest.setApplyList(applyList);
		return modifySellerRequest;
	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.7 变更还款方式
	 * @param localChgReturnTypeRequest
	 *            LocalChgReturnTypeRequest
	 * @return boolean
	 * @version v1.1 2013-5-21
	 */
	public Message chgReturnType(AccountInfo accountInfo) {
		Message message = new Message();
		// 判断 卡类型 是公司卡还是 个人卡
		String getAccountType = accountInfo.getAccountType();
		if ("0".equals(getAccountType)) {
			message.setSuccess(true);
			message.setMsg("公司卡不调贷后接口");
			return message;
		} else if ("1".equals(getAccountType)) {
			List<String> sysGuidList = rural2CreditDao.querySysGuidListByAccountInfoId(accountInfo.getAccountInfoId());
			if (CommonUtil.isEmpty(sysGuidList)) {
				message.setSuccess(true);
				message.setMsg("个人卡但信息没有进入还款中");
				return message;
			} else {
				if (sysGuidList.size() > 1) {
					message.setSuccess(false);
					message.setMsg("个人卡但查询到多条申请");
					return message;
				} else {
					ChgReturnTypeRequest chgReturnTypeRequest = constructChgReturnTypeRequest(accountInfo, sysGuidList.get(0));

					// 记Log日志
					log.info("**************RuralBusyService.chgReturnType(chgReturnTypeRequest) requestParam******************");
					log.info(JsonUtil.getJackson(chgReturnTypeRequest));
					// 调用clientApply接口
					ChgReturnTypeResponse chgReturnTypeResponse = ruralBusyService.chgReturnType(chgReturnTypeRequest);
					log.info("**************RuralBusyService.chgReturnType(chgReturnTypeResponse) responseParam******************");
					log.info(JsonUtil.getJackson(chgReturnTypeResponse));
					String code = chgReturnTypeResponse.getRetCode();

					if ("0".equals(code)) {
						message.setSuccess(true);
						message.setMsg("调取贷后接口返回成功");
						return message;
					} else {
						message.setSuccess(false);
						message.setMsg(chgReturnTypeResponse.getRetInfo());
						return message;
					}
				}
			}
		} else {
			message.setSuccess(false);
			message.setMsg("getAccountType值不对");
			return message;
		}

	}

	/**
	 * 构建一个用于调用chgReturnType接口的ChgReturnTypeRequest对象
	 * 
	 * @param localChgReturnTypeRequest
	 *            LocalChgReturnTypeRequest
	 * @return 用于调用chgReturnType接口的ChgReturnTypeRequest对象
	 */
	private ChgReturnTypeRequest constructChgReturnTypeRequest(AccountInfo accountInfo, String sysGuid) {

		ChgReturnTypeRequest chgReturnTypeRequest = new ChgReturnTypeRequest();

		chgReturnTypeRequest.setApplyId(sysGuid);
		chgReturnTypeRequest.setBankAccount(accountInfo.getAccount());
		chgReturnTypeRequest.setBankClientName(accountInfo.getAccountName());
		chgReturnTypeRequest.setBankName(DicUtil.convertCodeKeyToCodeValue("bankNum", accountInfo.getBankNum()));
		chgReturnTypeRequest.setReturnType(DicUtil.convertCodeKeyToCodeValue("creditBankNum", accountInfo.getBankNum()));
		chgReturnTypeRequest.setBankBook(accountInfo.getCardFlag());

		return chgReturnTypeRequest;

	}

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.8 查询划扣结果
	 * @param bizIdList
	 *            List<String>
	 * @return LocalReserveDTOVo
	 * @version v1.0 2013-5-15
	 */
	public LocalReserveDTOVo qyReserveSearch(List<String> bizIdList, String businessNumber) {
		QyReserveResultRequest qyReserveResultRequest = constructQyReserveResultRequest(bizIdList, businessNumber);

		/** 以下为为了防止发给贷后的信息超长做的限制，贷后修改后 可去除 **/
		List<ReserveIdListDTO> reserveIdListDTOsLoop = new ArrayList<ReserveIdListDTO>();

		LocalReserveDTOVo reserveDTOVo = new LocalReserveDTOVo();
		List<LocalReserveListDTO> localReserveListDTOs = new ArrayList<LocalReserveListDTO>();

		// 五条一发送
		ReserveList reserveList = qyReserveResultRequest.getReserveList();
		List<ReserveIdListDTO> reserveIdListDTOs = reserveList.getReserveIdListDTO();
		reserveIdListDTOsLoop.addAll(reserveIdListDTOs);
		reserveIdListDTOs.clear();
		int count = 0;
		List<ReserveIdListDTO> add = new ArrayList<ReserveIdListDTO>();
		if (CommonUtil.isNotEmpty(reserveIdListDTOsLoop)) {
			for (ReserveIdListDTO reserveIdListDTO : reserveIdListDTOsLoop) {
				add.add(reserveIdListDTO);
				count++;
				if (count == 5) {
					count = 0;
					// 调用接口
					reserveIdListDTOs.addAll(add);
					// 记Log日志
					log.info("**************RuralBusyService.qyReserveSearch(qyReserveResultRequest) requestParam******************");
					log.info(JsonUtil.getJackson(qyReserveResultRequest));
					// 调用clientApply接口
					QyReserveResultResponse qyReserveResultResponse = ruralBusyService.qyReserveSearch(qyReserveResultRequest);
					log.info("**************RuralBusyService.qyReserveSearch(qyReserveResultResponse) responseParam******************");
					log.info(JsonUtil.getJackson(qyReserveResultResponse));
					LocalReserveDTOVo reserveDTOVoTemp = transforQyReserveResultResponse(qyReserveResultResponse);

					String getRetCode = reserveDTOVoTemp.getRetCode();
					String getRetInfo = reserveDTOVoTemp.getRetInfo();
					List<LocalReserveListDTO> localReserveListDTOsTemp = reserveDTOVoTemp.getLocalReserveListDTOs();

					localReserveListDTOs.addAll(localReserveListDTOsTemp);

					reserveDTOVo.setRetCode(getRetCode);
					reserveDTOVo.setRetInfo(getRetInfo);

					add.clear();
					reserveIdListDTOs.clear();
					if (!"0".equals(getRetCode)) {
						break;
					}

				}
			}
		}

		if (CommonUtil.isNotEmpty(add)) {
			reserveIdListDTOs.addAll(add);
			// 记Log日志
			log.info("**************RuralBusyService.qyReserveSearch(qyReserveResultRequest) requestParam******************");
			log.info(JsonUtil.getJackson(qyReserveResultRequest));
			// 调用clientApply接口
			QyReserveResultResponse qyReserveResultResponse = ruralBusyService.qyReserveSearch(qyReserveResultRequest);
			log.info("**************RuralBusyService.qyReserveSearch(qyReserveResultResponse) responseParam******************");
			log.info(JsonUtil.getJackson(qyReserveResultResponse));
			LocalReserveDTOVo reserveDTOVoTemp = transforQyReserveResultResponse(qyReserveResultResponse);

			String getRetCode = reserveDTOVoTemp.getRetCode();
			String getRetInfo = reserveDTOVoTemp.getRetInfo();
			List<LocalReserveListDTO> localReserveListDTOsTemp = reserveDTOVoTemp.getLocalReserveListDTOs();

			localReserveListDTOs.addAll(localReserveListDTOsTemp);

			reserveDTOVo.setRetCode(getRetCode);
			reserveDTOVo.setRetInfo(getRetInfo);

			add.clear();
			reserveIdListDTOs.clear();
		}

		/** 以上为为了防止发给贷后的信息超长做的限制，贷后修改后 可去除 **/

		/** 注掉了以前的方法 **/

		// // 记Log日志
		// log.info("**************RuralBusyService.qyReserveSearch(qyReserveResultRequest) requestParam******************");
		// log.info(JsonUtil.getJackson(qyReserveResultRequest));
		// // 调用clientApply接口
		// QyReserveResultResponse qyReserveResultResponse =
		// ruralBusyService.qyReserveSearch(qyReserveResultRequest);
		// log.info("**************RuralBusyService.qyReserveSearch(qyReserveResultResponse) responseParam******************");
		// log.info(JsonUtil.getJackson(qyReserveResultResponse));
		//
		// LocalReserveDTOVo reserveDTOVo =
		// transforQyReserveResultResponse(qyReserveResultResponse);
		reserveDTOVo.setLocalReserveListDTOs(localReserveListDTOs);
		System.out.println("90909090909");
		return reserveDTOVo;
	}

	/**
	 * 
	 * @param bizIdList
	 *            List<String>
	 * @return QyReserveResultRequest
	 */
	private QyReserveResultRequest constructQyReserveResultRequest(List<String> bizIdList, String businessNumber) {
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();

		if (businessNumber != null && !"".equals(businessNumber)) {
			Map<String, String> map = new HashMap<String, String>();
			String applyid = rural2CreditDao.querySysGuidByBusinessNumber(businessNumber);
			map.put("APPLYID", applyid);
			maps.add(map);
		} else {
			if (CommonUtil.isEmpty(bizIdList)) {
				bizIdList = financeMoneyDao.queryAppointingReseive();
				maps = rural2CreditDao.queryBizIdAndSysguId(bizIdList);
			} else {
				maps = rural2CreditDao.queryBizIdAndSysguId(bizIdList);
			}

		}
		QyReserveResultRequest qyReserveResultRequest = new QyReserveResultRequest();
		ReserveList reserveList = new ReserveList();
		List<ReserveIdListDTO> reserveIdListDTOList = reserveList.getReserveIdListDTO();
		for (Map<String, String> map : maps) {
			ReserveIdListDTO reserveIdListDTO = new ReserveIdListDTO();
			reserveIdListDTO.setReserveId(map.get("RESERVEID"));
			reserveIdListDTO.setApplyId(map.get("APPLYID"));
			reserveIdListDTOList.add(reserveIdListDTO);
		}
		qyReserveResultRequest.setReserveList(reserveList);
		return qyReserveResultRequest;
	}

	/**
	 * 
	 * @param qyReserveResultResponse
	 *            QyReserveResultResponse
	 * @return LocalReserveDTOVo
	 */
	private LocalReserveDTOVo transforQyReserveResultResponse(QyReserveResultResponse qyReserveResultResponse) {

		LocalReserveDTOVo reserveDTOVo = new LocalReserveDTOVo();
		List<LocalReserveListDTO> localReserveListDTOs = new ArrayList<LocalReserveListDTO>();

		QyReserveResult qyReserveResult = qyReserveResultResponse.getQyReserveResult();
		String getCode = qyReserveResult.getCode();
		String getMessage = qyReserveResult.getMessage();
		// System.out.println("响应码：" + getCode);
		// System.out.println("响应信息：" + getMessage);

		reserveDTOVo.setRetCode(getCode);
		reserveDTOVo.setRetInfo(getMessage);

		com.creditease.rc.app.credit.QyReserveResult.ReserveList reserveList = qyReserveResult.getReserveList();
		List<ReserveListDTO> reserveListDTOs = reserveList.getReserveListDTO();
		if (CommonUtil.isNotEmpty(reserveListDTOs)) {
			for (ReserveListDTO rl : reserveListDTOs) {
				LocalReserveListDTO localReserveListDTO = new LocalReserveListDTO();
				localReserveListDTO.setReserveId(rl.getReserveId());
				localReserveListDTO.setReserveResult(rl.getReserveResult());

				localReserveListDTO.setApplyId(rl.getApplyId());
				localReserveListDTO.setReserveId(rl.getReserveId());
				localReserveListDTO.setBizid(rl.getBizid());
				localReserveListDTO.setClientName(rl.getClientName());
				localReserveListDTO.setReturnName(rl.getReturnName());
				localReserveListDTO.setIdNumber(rl.getIdNumber());
				localReserveListDTO.setProductId(rl.getProductId());
				localReserveListDTO.setOfficeId(rl.getOfficeId());
				localReserveListDTO.setApplyAmount(rl.getApplyAmount());
				localReserveListDTO.setAmortisation(rl.getAmortisation());
				localReserveListDTO.setLoanAmount(rl.getLoanAmount());
				localReserveListDTO.setStartTime(rl.getStartTime());
				localReserveListDTO.setReturnDate(rl.getReturnDate());
				localReserveListDTO.setdAmount(rl.getDAmount());
				localReserveListDTO.setKeapAmount(rl.getKeapAmount());
				localReserveListDTO.setKeapDate(rl.getKeapDate());
				localReserveListDTO.setReturnType(rl.getReturnType());
				localReserveListDTO.setReserveResult(rl.getReserveResult());
				localReserveListDTO.setReserveInfo(rl.getReserveInfo());

				localReserveListDTOs.add(localReserveListDTO);
			}
		}
		reserveDTOVo.setLocalReserveListDTOs(localReserveListDTOs);
		return reserveDTOVo;
	}

	/**
	 * @author 郝强
	 * @Description 1.9 查询借款人信息
	 * @param creditapplicationId
	 * @return LocalClientApplyRequest
	 * @version v1.0 2013-6-20
	 */
	@Override
	public LocalClientApplyRequest qyClientApply(Long creditapplicationId) {
		// TODO Auto-generated method stub
		QyClientApplyRequest qyClientApplyRequest = constructQyClientApplyRequest(creditapplicationId);

		// 记Log日志
		log.info("**************ruralBusyService.qyClientApply(qyClientApplyRequest) requestParam******************");
		log.info(JsonUtil.getJackson(qyClientApplyRequest));
		// 调用clientApply接口
		QyClientApplyResponse qyClientApplyResponse = ruralBusyService.qyClientApply(qyClientApplyRequest);
		log.info("**************ruralBusyService.qyClientApply(qyClientApplyResponse) responseParam******************");
		log.info(JsonUtil.getJackson(qyClientApplyResponse));

		LocalClientApplyRequest localClientApplyRequest = transforqyClientApplyResponse(qyClientApplyResponse);

		return localClientApplyRequest;
	}

	/**
	 * 构造用于调用1.9 查询借款人信息的入参
	 * 
	 * @param creditapplicationId
	 *            信贷申请ID
	 * @return QyClientApplyRequest
	 */
	private QyClientApplyRequest constructQyClientApplyRequest(Long creditapplicationId) {
		QyClientApplyRequest clientApplyRequest = new QyClientApplyRequest();
		String sysGuid = creditApplicationDAO.queryUUid(creditapplicationId);
		clientApplyRequest.setApplyId(sysGuid);
		return clientApplyRequest;
	}

	/**
	 * 1.9 查询借款人信息的返回值转换成本地对象
	 * 
	 * @param qyClientApplyResponse
	 *            1.9 查询借款人信息的返回值
	 * @return 成本地对象
	 */
	private LocalClientApplyRequest transforqyClientApplyResponse(QyClientApplyResponse qyClientApplyResponse) {
		LocalClientApplyRequest localClientApplyRequest = new LocalClientApplyRequest();
		QyapplyList qyapplyList = qyClientApplyResponse.getQyapplyList();
		List<QyapplyListDTO> qyapplyListDTOs = qyapplyList.getQyapplyListDTO();
		if (CommonUtil.isNotEmpty(qyapplyListDTOs)) {
			QyapplyListDTO qyapplyListDTO = qyapplyListDTOs.get(0);
			String getApplyId = qyapplyListDTO.getApplyId();
			String getReturnType = qyapplyListDTO.getReturnType();
			String getBankBook = qyapplyListDTO.getBankBook();
			String getBankAccount = qyapplyListDTO.getBankAccount();
			String getBankClientName = qyapplyListDTO.getBankClientName();
			String getBankName = qyapplyListDTO.getBankName();
			String getLoanType = qyapplyListDTO.getLoanType();
			String getLoanBankAccount = qyapplyListDTO.getLoanBankAccount();
			String getLoanBankClientName = qyapplyListDTO.getLoanBankClientName();
			String getLoanBankProvince = qyapplyListDTO.getLoanBankProvince();
			String getLoanBankCity = qyapplyListDTO.getLoanBankCity();
			String getLoanBankName = qyapplyListDTO.getLoanBankName();
			String getApplyAmount = qyapplyListDTO.getApplyAmount();
			String getLoanAmount = qyapplyListDTO.getLoanAmount();
			String getLoanTime = qyapplyListDTO.getLoanTime();
			String getProductTypeId = qyapplyListDTO.getProductTypeId();
			String getProductId = qyapplyListDTO.getProductId();
			String getAmortisation = qyapplyListDTO.getAmortisation();
			String getBorrowPurpose = qyapplyListDTO.getBorrowPurpose();
			String getBorrowPurposeSon = qyapplyListDTO.getBorrowPurposeSon();
			String getBorrowType = qyapplyListDTO.getBorrowType();
			String getSellId = qyapplyListDTO.getSellId();
			String getSellName = qyapplyListDTO.getSellName();
			String getOfficeId = qyapplyListDTO.getOfficeId();
			String getCity = qyapplyListDTO.getCity();
			String getClientName = qyapplyListDTO.getClientName();
			String getIdNumber = qyapplyListDTO.getIdNumber();
			String getHomeProvince = qyapplyListDTO.getHomeProvince();
			String getHomeCity = qyapplyListDTO.getHomeCity();
			String getHomeAddr = qyapplyListDTO.getHomeAddr();
			String getMobilePhone = qyapplyListDTO.getMobilePhone();
			String getHomeFixPhone = qyapplyListDTO.getHomeFixPhone();

			localClientApplyRequest.setApplyId(getApplyId); // 申请编号
			localClientApplyRequest.setReturnType(getReturnType); // 还款方式
			localClientApplyRequest.setLoanType(getLoanType); // 放款方式
			localClientApplyRequest.setLoanBankAccount(getLoanBankAccount); // 放款银行账号
			localClientApplyRequest.setLoanBankClientName(getLoanBankClientName); // 放款人银行户名
			localClientApplyRequest.setLoanBankProvince(getLoanBankProvince); // 放款银行所在省
			localClientApplyRequest.setLoanBankCity(getLoanBankCity); // 放款银行所在市
			localClientApplyRequest.setLoanBankName(getLoanBankName); // 放款开户行名称
			localClientApplyRequest.setApplyAmount(new BigDecimal("".equals(getApplyAmount) || getApplyAmount == null ? "0" : getApplyAmount)); // 合同金额
			localClientApplyRequest.setProductId(Long.valueOf(getProductId == null || "null".equals(getProductId) || "".equals(getProductId) ? "0" : getProductId)); // 产品编号
			localClientApplyRequest.setAmortisation(getAmortisation); // 分期数
			localClientApplyRequest.setBorrowPurpose(getBorrowPurpose); // 借款用途
			localClientApplyRequest.setSellId(getSellId); // 信贷员编号
			localClientApplyRequest.setSellName(getSellName); // 信贷员名字
			localClientApplyRequest.setOfficeId(getOfficeId); // 分公司编号
			localClientApplyRequest.setClientName(getClientName); // 客户姓名
			localClientApplyRequest.setIdNumber(getIdNumber); // 身份证号
			localClientApplyRequest.setMobilePhone(getMobilePhone); // 手机号码
			// localClientApplyRequest.setSignInfo ( ); // 摘要信息
			localClientApplyRequest.setLoanAmount(new BigDecimal("".equals(getLoanAmount) || getLoanAmount == null ? "0" : getLoanAmount)); // 放款金额
			localClientApplyRequest.setLoanTime(getLoanTime == null ? null : DateUtil.stringConvertDate(getLoanTime)); // 放款时间
			localClientApplyRequest.setProductTypeId(getProductTypeId); // 产品分类编号
			localClientApplyRequest.setBankBook(getBankBook); // 卡折标志
			localClientApplyRequest.setBankAccount(getBankAccount); // 还款银行账号
			localClientApplyRequest.setBankClientName(getBankClientName); // 还款人银行户名
			localClientApplyRequest.setBankName(getBankName); // 还款开户行名称
		}
		return localClientApplyRequest;
	}

	public String convert(Long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	public String convert(String mill) {
		Date date = new Date(Long.parseLong(mill));
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	@Override
	public Message updateClientApplyRecord(Long creditapplicationId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		String sysGuid = creditApplicationDAO.queryUUid(creditapplicationId);
		ClientApplyHistory clientApplyHistory = new ClientApplyHistory();
		clientApplyHistory.setCreditapplicationId(creditapplicationId);
		clientApplyHistory.setSysGuid(sysGuid);
		try {
			message = clientApply(creditapplicationId);
			boolean isSuccess = message.isSuccess();
			String getCode = message.getCode();
			String getMsg = message.getMsg();
			if (isSuccess) {
				// 调用成功
				// 删除表中creditapplicationId相同的数据
				clientApplyHistoryService.dynamicDelete(clientApplyHistory);
				// 插入新的数据
				clientApplyHistory.setIsSuccess("0");
				clientApplyHistory.setMessage("code：" + getCode + ",msg：" + getMsg);
				clientApplyHistoryService.dynamicInsert(clientApplyHistory);
				List<LocalReturnSchemeResponse> localReturnSchemeResponses = this.returnScheme(creditapplicationId);
				returnPlanService.insertReturnPlanFromIocalReturnSchemeResponses(localReturnSchemeResponses, creditapplicationId);
			} else {
				// 调用失败
				// 先查看是不是因为已经有还款计划了
				if ("2".equals(getCode)) {
					// 如果因为还款计划已经存在则先看看是不是已经有成功的
					ClientApplyHistory queryClientApplyHistory = new ClientApplyHistory();
					queryClientApplyHistory.setCreditapplicationId(creditapplicationId);
					queryClientApplyHistory.setIsSuccess("0");
					List<ClientApplyHistory> clientApplyHistories = clientApplyHistoryService.dynamicSelect(queryClientApplyHistory);
					// 如果已经有成功的则不做任何操作
					if (CommonUtil.isEmpty(clientApplyHistories)) {
						// 删除表中creditapplicationId相同的数据
						clientApplyHistoryService.dynamicDelete(clientApplyHistory);
						// 如果没有成功的则插入一条成功的
						clientApplyHistory.setIsSuccess("0");
						clientApplyHistory.setMessage("code：" + getCode + ",msg：" + "因为此数据是之前生成的因此补一条成功数据");
						clientApplyHistoryService.dynamicInsert(clientApplyHistory);
					}
				} else {
					// 查询有没有成功的数据
					ClientApplyHistory queryClientApplyHistory = new ClientApplyHistory();
					queryClientApplyHistory.setCreditapplicationId(creditapplicationId);
					queryClientApplyHistory.setIsSuccess("0");
					List<ClientApplyHistory> clientApplyHistories = clientApplyHistoryService.dynamicSelect(queryClientApplyHistory);
					// 有咋不插入失败数据
					// 咩有就插入失败数据
					if (CommonUtil.isEmpty(clientApplyHistories)) {
						// 删除表中creditapplicationId相同的数据
						queryClientApplyHistory.setIsSuccess(null);
						clientApplyHistoryService.dynamicDelete(queryClientApplyHistory);
						// 插入新的数据
						clientApplyHistory.setIsSuccess("1");
						clientApplyHistory.setMessage("code：" + getCode + ",msg：" + getMsg);
						clientApplyHistoryService.dynamicInsert(clientApplyHistory);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("调用贷后接口报错");
			// 调用失败
			// 查询有没有成功的数据
			ClientApplyHistory queryClientApplyHistory = new ClientApplyHistory();
			queryClientApplyHistory.setCreditapplicationId(creditapplicationId);
			queryClientApplyHistory.setIsSuccess("0");
			List<ClientApplyHistory> clientApplyHistories = clientApplyHistoryService.dynamicSelect(queryClientApplyHistory);
			// 有咋不插入失败数据
			// 咩有就插入失败数据
			if (CommonUtil.isEmpty(clientApplyHistories)) {
				// 删除表中creditapplicationId相同的数据
				queryClientApplyHistory.setIsSuccess(null);
				clientApplyHistoryService.dynamicDelete(queryClientApplyHistory);
				// 插入新的数据
				clientApplyHistory.setIsSuccess("1");
				clientApplyHistory.setMessage("msg：" + message.getMsg());
				clientApplyHistoryService.dynamicInsert(clientApplyHistory);
			}
		}
		return message;
	}

	@Override
	public LoanBalanceDataResponse loanBalanceData(Long creditapplicationId, String officeId, String sellerId) {
		// TODO Auto-generated method stub
		LoanBalanceDataRequest loanBalanceDataRequest = new LoanBalanceDataRequest();
		loanBalanceDataRequest.setSignInfo(signInfoEncrypt(new Date()));
		loanBalanceDataRequest.setSysId("rural2");
		loanBalanceDataRequest.setApplyId(creditapplicationId == null ? null : creditapplicationId.toString());
		loanBalanceDataRequest.setOfficeId(officeId);
		loanBalanceDataRequest.setSellerId(sellerId);
		log.info("**************RuralBusyService.loanBalanceData(loanBalanceDataRequest) requestParam******************");
		log.info(JsonUtil.getJackson(loanBalanceDataRequest));
		// 调用clientApply接口
		LoanBalanceDataResponse loanBalanceDataResponse = ruralBusyService.loanBalanceData(loanBalanceDataRequest);
		log.info("**************RuralBusyService.loanBalanceData(loanBalanceDataResponse) responseParam******************");
		log.info(JsonUtil.getJackson(loanBalanceDataResponse));
		return loanBalanceDataResponse;
	}

	@Override
	public boolean updateStaffComprehensive(String beforePropId, String afterPropId) {
		log.info("------->>开始调用：综合信贷系统，变更人员<<------");
		DerateProposerIdInfoNoticeReq req = new DerateProposerIdInfoNoticeReq();
		List<DerateProposerIdInfoNoticeDTO> dtos = new ArrayList<DerateProposerIdInfoNoticeDTO>();
		DerateProposerIdInfoNoticeDTO dto = new DerateProposerIdInfoNoticeDTO();
		dto.setBeforePropId(beforePropId);
		dto.setAfterPropId(afterPropId);
		dtos.add(dto);
		req.getDerateProposerIdInfoNoticeDTOList().addAll(dtos);
		try {
			DerateProposerIdInfoNoticeRes res = icpDerateServiceWS.changeProposerIdNotice(req);
			if (res.getCodeInfo().equals("1")) {
				log.info("------->>调用成功：综合信贷系统，变更人员<<------");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n\t1.请检查本地webservice地址配置文件 \n\t 或 \n\t2.检查服务端服务是否正常");
		}
		log.warn("------->>调用失败：综合信贷系统，变更人员<<------");
		return false;
	}
}

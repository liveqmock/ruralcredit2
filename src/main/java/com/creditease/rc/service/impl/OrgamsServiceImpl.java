package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.orgams.BaseResponse;
import com.creditease.rc.app.orgams.BorrowMatchingReq;
import com.creditease.rc.app.orgams.CashFlowVo;
import com.creditease.rc.app.orgams.MapConvertor;
import com.creditease.rc.app.orgams.MapEntry;
import com.creditease.rc.app.orgams.MatchBorrowReqVo;
import com.creditease.rc.app.orgams.MatchBorrowResVo;
import com.creditease.rc.app.orgams.MatchConfirmReq;
import com.creditease.rc.app.orgams.MatchWebService;
import com.creditease.rc.app.orgams.TradeDealVo;
import com.creditease.rc.app.orgams.UnMatchingReqVo;
import com.creditease.rc.app.orgams2.BorrowContractReq;
import com.creditease.rc.app.orgams2.BorrowContractRes;
import com.creditease.rc.app.orgams2.ContractStateReq;
import com.creditease.rc.app.orgams2.ContractStateRes;
import com.creditease.rc.app.orgams2.ICPWebService;
import com.creditease.rc.app.orgams2.SynBatchBorrowContract;
import com.creditease.rc.app.orgams2.SynBatchBorrowContractResponse;
import com.creditease.rc.app.pdf.ChargeInfo;
import com.creditease.rc.app.pdf.PeriodSchedule;
import com.creditease.rc.app.pdf.RepaymentPlanReq;
import com.creditease.rc.app.pdf.RepaymentPlanReqResult;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.dao.BorrowMatchingDAO;
import com.creditease.rc.dao.IContractAndLoanDao;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.OrgamsDAO;
import com.creditease.rc.dao.TradeDealFormDAO;
import com.creditease.rc.domain.BorrowMatching;
import com.creditease.rc.domain.TradeDealForm;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.ContractRateForQYResult;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

@Service
public class OrgamsServiceImpl implements IorgamsService {

	@Resource
	private MatchWebService matchWebService;
	@Resource
	private ICPWebService icpWebService;
	@Resource
	private OrgamsDAO orgamsDAO;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IBorrowingProductsService borrowingProductsService;
	
	@Resource
	private IContractAndLoanDao contractAndLoanDao;
	@Resource
	private BorrowMatchingDAO borrowMatchingDAO;
	@Resource
	private TradeDealFormDAO tradeDealFormDAO;
	@Resource
	private ICreditApplicationDAO iCreditApplicationDAO;
	Logger log = Logger.getLogger(OrgamsServiceImpl.class);
	
	/**
	 * 借款请求
	 */
	@Override
	public MatchBorrowResVo borrowMatchingReq(
			Long creditapplicationId,Double irr,Double amount) {
		// TODO 创建接口需要的对象
		MatchBorrowReqVo matchBorrowReqVo = getMatchBorrowReqVo(creditapplicationId,irr,amount);
		// 记Log日志
		log.info("**************matchWebService.borrowMatchingReq(matchBorrowReqVo); requestParam******************");
		log.info(JsonUtil.getJackson(matchBorrowReqVo));
		//调用接口
		MatchBorrowResVo borrowMatchingReq = matchWebService.borrowMatchingReq(matchBorrowReqVo);
		log.info("**************matchWebService.borrowMatchingReq(matchBorrowReqVo); responseParam******************");
		log.info(JsonUtil.getJackson(borrowMatchingReq));
		//保存返回信息
		BorrowMatching borrowMatching = new BorrowMatching();
		borrowMatching.setCreditapplicationId(creditapplicationId);
		borrowMatching.setMatchid(borrowMatchingReq.getMatchId());
		borrowMatching.setStatus(borrowMatchingReq.getStatus().toString());
		borrowMatching.setErrormsg(borrowMatchingReq.getErrorMsg());
		borrowMatching.setSubmittime(xmlDate2Date(borrowMatchingReq.getSubmitTime()));
		borrowMatching.setCreationTime(new Date());
		borrowMatchingDAO.insert(borrowMatching);
		List<TradeDealForm> tradeDealForms = new ArrayList<TradeDealForm>();
		if(CommonUtil.isNotEmpty(borrowMatchingReq.getTradeDealList())){
			
			List<TradeDealVo> tradeDealList = borrowMatchingReq.getTradeDealList();
			for (TradeDealVo tradeDealVo : tradeDealList) {
				TradeDealForm tradeDealForm = new TradeDealForm();
				tradeDealForm.setCreditapplicationId(creditapplicationId);
				tradeDealForm.setTradedealid(tradeDealVo.getTradeDealId());
				tradeDealForm.setInvestmatchid(tradeDealVo.getInvestMatchId());
				tradeDealForm.setInvestformid(tradeDealVo.getInvestFormId());
				tradeDealForm.setProductid(tradeDealVo.getProductId());
				tradeDealForm.setPlanid(tradeDealVo.getPlanId());
				tradeDealForm.setPlancode(tradeDealVo.getPlanCode());
				tradeDealForm.setPlanname(tradeDealVo.getPlanName());
				tradeDealForm.setFinanceformid(tradeDealVo.getFinanceFormId());
				tradeDealForm.setFinancematchid(tradeDealVo.getFinanceMatchId());
				tradeDealForm.setCreditorid(tradeDealVo.getCreditorId());
				tradeDealForm.setCreditortype(tradeDealVo.getCreditorType().toString());
				tradeDealForm.setIrr(tradeDealVo.getIrr());
				tradeDealForm.setAmount(tradeDealVo.getAmount());
				tradeDealForm.setTradedealtime(xmlDate2Date(tradeDealVo.getTradeDealTime()));
				tradeDealForm.setAmountbalance(tradeDealVo.getAmountBalance());
				tradeDealForm.setMatchstrategid(tradeDealVo.getMatchStrategId().toString());
				tradeDealForm.setOrgname(tradeDealVo.getOrganName());
				tradeDealForm.setCreationTime(new Date());
				tradeDealForm.setStatus(tradeDealVo.getStatus().toString());
				tradeDealForm.setTrustAccountName(tradeDealVo.getTrustAccountName());
				tradeDealForm.setAccountName(tradeDealVo.getAccountName());
				tradeDealForm.setAccountNo(tradeDealVo.getAccountNo().toString());
				tradeDealForm.setSubAccountName(tradeDealVo.getSubAccountName());
				
				tradeDealForms.add(tradeDealForm);
			}
			tradeDealFormDAO.bachInsert(tradeDealForms);
		}


		return borrowMatchingReq;
	}
	/**
	 * 借款请求封装参数
	 * @return
	 */
	private MatchBorrowReqVo getMatchBorrowReqVo(Long creditapplicationId,Double irr,Double amount){
		DecimalFormat df = new DecimalFormat("#.00");//用于BigDecimal保留两位小数
		HashMap<Object,Object> selectOrgams = orgamsDAO.selectOrgams(creditapplicationId);
		BorrowMatchingReq borrowMatchingReq = new BorrowMatchingReq();
		MatchBorrowReqVo matchBorrowReqVo = new MatchBorrowReqVo();
		matchBorrowReqVo.setApplyId(selectOrgams.get("SYSGUID").toString());//uuid
		matchBorrowReqVo.setFinanceFormId(creditapplicationId.toString());//融资报单号
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		matchBorrowReqVo.setMatchId("07202"+dateStr+creditapplicationId.toString());//融资方撮合id
		//matchBorrowReqVo.setFinancerId("");//融资客户唯一标志（身份证号）
		matchBorrowReqVo.setPeriod(Long.valueOf((String) selectOrgams.get("INSTALMENTS")));//借款期限
		matchBorrowReqVo.setSystemSign("07");//系统标识
		matchBorrowReqVo.setIrr(new BigDecimal(df.format(irr)));//融资收益率
		matchBorrowReqVo.setAmount(new BigDecimal(df.format(amount)));//融资总额度
		//matchBorrowReqVo.setPriority(null);//优先级
		//matchBorrowReqVo.setExpireTime(null);
		matchBorrowReqVo.setSignDate(dateToXmlDate(new Date()));//签约时间
		matchBorrowReqVo.setOperationId(Long.valueOf((String) selectOrgams.get("LOAN_OFFICER_ID")));//操作人ID
		//******还款计划start*******************
		RepaymentPlanReq rateInfo = new RepaymentPlanReq();

		ContractRateForQYResult contractRateForQYResult = contractAndLoanDao.qyContractRateForQYResult(creditapplicationId);
		String getClientName = contractRateForQYResult.getClientName();
		String getBorrowUse = contractRateForQYResult.getBorrowUse();
		String getBorrowUseText = contractRateForQYResult.getBorrowUseText();
		String getProductName = contractRateForQYResult.getProductName();
		Long getDepartmentId = contractRateForQYResult.getDepartmentId();
		Long getCatagoryId = contractRateForQYResult.getCatagoryId();
		Date getAuditDate = contractRateForQYResult.getAuditDate();
		Date getReqDate = contractRateForQYResult.getReqDate();
		Long getProductInfoId = contractRateForQYResult.getProductInfoId();
		BigDecimal bigDecimal = new BigDecimal(df.format(amount));
		BigDecimal getContractMoney = bigDecimal;
		int getPeriodCount = contractRateForQYResult.getPeriodCount();
		Date getLenderDate = contractRateForQYResult.getLenderDate();
		String getPaymentTypeCode = contractRateForQYResult.getPaymentTypeCode();
		String getParamName = contractRateForQYResult.getParamName();
		BigDecimal getParamValue = contractRateForQYResult.getParamValue();

		rateInfo.setDepartmentId(11011196l);
		rateInfo.setCatagoryId(getCatagoryId);
		rateInfo.setAuditDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getAuditDate)));
		// rateInfo.setReqDate(getReqDate);
		rateInfo.setProductInfoId(getProductInfoId);
		rateInfo.setContractMoney(getContractMoney);
		rateInfo.setPeriodCount(getPeriodCount);
		rateInfo.setLenderDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getLenderDate)));
		// rateInfo.setPaymentTypeCode(getPaymentTypeCode);
		// rateInfo.setParamName(getParamName);
		// rateInfo.setParamValue(getParamValue);

		/*
		 * System.out.println("产品类型Id"+rateInfo.getCatagoryId());
		 * System.out.println("合同金额"+rateInfo.getContractMoney());
		 */
		List<CashFlowVo> list = new ArrayList<CashFlowVo>();
		RepaymentPlanReqResult repaymentPlanReqResult = borrowingProductsService.advanceReturnPlan(rateInfo);//调用产品中心接口
		if(CommonUtil.isNotEmpty(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule())){
			List<PeriodSchedule> periodSchedule = repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule();
			
			for (PeriodSchedule periodSchedule2 : periodSchedule) {
				CashFlowVo cashFlowVo = new CashFlowVo();
				cashFlowVo.setPrincipalAmt(periodSchedule2.getAmortizedPrincipal());
				cashFlowVo.setInterestAmt(periodSchedule2.getAmortizedInterest());
				cashFlowVo.setManageFee(periodSchedule2.getPeriodCharge());
				cashFlowVo.setReturnDate(periodSchedule2.getRepayDate());
				list.add(cashFlowVo);
			}
			matchBorrowReqVo.getCashflowList().addAll(list);//还款计划
		}
		EmployeeDTO employeeDTO = smpWSUtil.getEmployeeDTO(selectOrgams.get("LOAN_OFFICER_ID").toString());
		Properties properties = PropertiesUtil.loadProperties("spring/pdf/pdf.properties");

		//******还款计划end*********************
		//*********
		MapConvertor mapConvertor = new MapConvertor();
		//产品种类
		MapEntry mapEntry = new MapEntry();
		mapEntry.setKey("productType");
		mapEntry.setValue(properties.getProperty("pdf.productbroadheading"));
		//城市网点
		MapEntry mapEntry2 = new MapEntry();
		mapEntry2.setKey("city");
		mapEntry2.setValue(employeeDTO.getDepartmentId().toString());
		//贷款产品
		MapEntry mapEntry3 = new MapEntry();
		mapEntry3.setKey("loanProduct");
		mapEntry3.setValue(contractRateForQYResult.getCatagoryId().toString());
		//产品期数
		MapEntry mapEntry4 = new MapEntry();
		mapEntry4.setKey("productPeriod");
		mapEntry4.setValue(selectOrgams.get("INSTALMENTS"));
		//循环贷标识
		MapEntry mapEntry5 = new MapEntry();
		mapEntry5.setKey("isCycle");
		mapEntry5.setValue(selectOrgams.get("DISCOUNT_FLAG"));
		//还款方式
		MapEntry mapEntry6 = new MapEntry();
		mapEntry6.setKey("isInterestonly");
		mapEntry6.setValue(selectOrgams.get("REPAYMENT_TYPE").toString());
		//客户银行
		MapEntry mapEntry7 = new MapEntry();
		mapEntry7.setKey("customerBank");
		mapEntry7.setValue(selectOrgams.get("CUSTOMERBANK"));
		//缴费方式
		MapEntry mapEntry8 = new MapEntry();
		mapEntry8.setKey("repaymentType");
		mapEntry8.setValue("0");
		//是否加急（1：是，0：否）
		MapEntry mapEntry9 = new MapEntry();
		mapEntry9.setKey("isEmergency");
		mapEntry9.setValue("0");
		//借款额度
		MapEntry mapEntry10 = new MapEntry();
		mapEntry10.setKey("borrowAmount");
		mapEntry10.setValue(new BigDecimal(amount.toString()));
		/*//时间限制（缺少）
		MapEntry mapEntry11 = new MapEntry();
		mapEntry11.setKey("timeLimit");
		mapEntry11.setValue(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size());*/
		//签约方式
		MapEntry mapEntry12 = new MapEntry();
		mapEntry12.setKey("signType");
		mapEntry12.setValue("1");
		mapConvertor.getEntries().add(mapEntry);
		mapConvertor.getEntries().add(mapEntry2);
		mapConvertor.getEntries().add(mapEntry3);
		mapConvertor.getEntries().add(mapEntry4);
		mapConvertor.getEntries().add(mapEntry5);
		mapConvertor.getEntries().add(mapEntry6);
		mapConvertor.getEntries().add(mapEntry7);
		mapConvertor.getEntries().add(mapEntry8);
		mapConvertor.getEntries().add(mapEntry9);
		mapConvertor.getEntries().add(mapEntry10);
		mapConvertor.getEntries().add(mapEntry12);
		
		
		matchBorrowReqVo.setExtParam(mapConvertor);
		borrowMatchingReq.setRequest(matchBorrowReqVo);
		return matchBorrowReqVo;
		
	}
	/**
	 * date转成XMLGregorianCalendar格式
	 */
	private XMLGregorianCalendar dateToXmlDate(Date date){  
       Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        DatatypeFactory dtf = null;  
         try {  
            dtf = DatatypeFactory.newInstance();  
        } catch (Exception e) { 
        	e.printStackTrace();
        }  
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();  
        dateType.setYear(cal.get(Calendar.YEAR));  
        //由于Calendar.MONTH取值范围为0~11,需要加1  
        dateType.setMonth(cal.get(Calendar.MONTH)+1);  
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
        dateType.setMinute(cal.get(Calendar.MINUTE));  
        dateType.setSecond(cal.get(Calendar.SECOND));  
        return dateType;  
	}

	 /** 
	 * 将XMLGregorianCalendar转换为Date 
	 * @param cal 
	* @return  
	*/  
	private Date xmlDate2Date(XMLGregorianCalendar cal){  
		return cal.toGregorianCalendar().getTime();  
	}  

	
	/**
	 * 借款撤销
	 */
	@Override
	public BaseResponse upMatching(Long creditapplicationId) {
		// TODO 创建接口需要的对象
		UnMatchingReqVo unMatchingReqVo = getUnMatchingReqVo(creditapplicationId);
		// 记Log日志
		log.info("**************matchWebService.unMatching(unMatchingReqVo); requestParam******************");
		log.info(JsonUtil.getJackson(unMatchingReqVo));
		//调用接口
		BaseResponse unMatching = matchWebService.unMatching(unMatchingReqVo);
		log.info("**************matchWebService.unMatching(unMatchingReqVo); responseParam******************");
		log.info(JsonUtil.getJackson(unMatching));
		return unMatching;
	}
	/**
	 * 借款撤销封装参数
	 * @param creditapplicationId
	 * @return
	 */
	private UnMatchingReqVo getUnMatchingReqVo(Long creditapplicationId){
		UnMatchingReqVo unMatchingReqVo = new UnMatchingReqVo();
		HashMap<String,String> selectMatchidByCreditapplicationId = borrowMatchingDAO.selectMatchidByCreditapplicationId(creditapplicationId);
		HashMap<Object,Object> selectOrgams = orgamsDAO.selectOrgams(creditapplicationId);
		unMatchingReqVo.setFinanceFormId(creditapplicationId.toString());//融资报单号
		unMatchingReqVo.setMatchId(selectMatchidByCreditapplicationId.get("MATCHID"));//撮合ID
		unMatchingReqVo.setSystemSign("07");//系统标识
		unMatchingReqVo.setOperationId(Long.parseLong((String) selectOrgams.get("LOAN_OFFICER_ID")));//操作人ID
		return unMatchingReqVo;
	}
	/**
	 * 借款确认
	 */
	@Override
	public BaseResponse matchConfirm(Long creditapplicationId) {
		// TODO 创建接口需要的对象
		MatchConfirmReq matchConfirmReq = getMatchConfirmReq(creditapplicationId);
		// 记Log日志
		log.info("**************matchWebService.matchConfirm(matchConfirmReq); requestParam******************");
		log.info(JsonUtil.getJackson(matchConfirmReq));
		//调用接口
		BaseResponse matchConfirm = matchWebService.matchConfirm(matchConfirmReq);
		log.info("**************matchWebService.matchConfirm(matchConfirmReq); responseParam******************");
		log.info(JsonUtil.getJackson(matchConfirm));
		return matchConfirm;
	}
	/**
	 * 
	 * 借款确认封装参数
	 * @param creditapplicationId
	 * @return
	 */
	private MatchConfirmReq getMatchConfirmReq(Long creditapplicationId){
		MatchConfirmReq matchConfirmReq = new MatchConfirmReq();
		HashMap<String,String> selectMatchidByCreditapplicationId = borrowMatchingDAO.selectMatchidByCreditapplicationId(creditapplicationId);
		HashMap<Object,Object> selectOrgams = orgamsDAO.selectOrgams(creditapplicationId);
		matchConfirmReq.setFinanceFormId(creditapplicationId.toString());//融资报单号
		matchConfirmReq.setMatchId(selectMatchidByCreditapplicationId.get("MATCHID"));//撮合ID
		matchConfirmReq.setSystemSign("07");//系统标识
		matchConfirmReq.setOperationId(Long.parseLong((String) selectOrgams.get("LOAN_OFFICER_ID")));//操作人ID
		return matchConfirmReq;
	}
	/**
	 * 放款状态同步
	 */
	@Override
	public ContractStateRes synBorrowContractState(
			Long creditapplicationId,String state) {
		// TODO 创建接口需要的对象
		List<ContractStateReq> listSynBorrowContractState = new ArrayList<ContractStateReq>();
		if(state.equals("3")){
			listSynBorrowContractState = getSynBorrowContractStateSuccess(creditapplicationId);
		}else{
			listSynBorrowContractState = getSynBorrowContractStateFail(creditapplicationId);
		}
		//List<ContractStateReq> listSynBorrowContractState = getSynBorrowContractState(creditapplicationId);
		// 记Log日志
		log.info("**************matchWebService.matchConfirm(matchConfirmReq); requestParam******************");
		log.info(JsonUtil.getJackson(listSynBorrowContractState));
		//调用接口
		ContractStateRes synBorrowContractState = icpWebService.synBorrowContractState(listSynBorrowContractState);
		log.info("**************matchWebService.matchConfirm(matchConfirmReq); responseParam******************");
		log.info(JsonUtil.getJackson(synBorrowContractState));
		return synBorrowContractState;
	}
	/**
	 * 封装放款状态同步参数(成功)
	 * @param creditapplicationId
	 * @return
	 */
	private List<ContractStateReq> getSynBorrowContractStateSuccess(Long creditapplicationId){
		List<ContractStateReq> list = new ArrayList<ContractStateReq>();
		ContractStateReq contractStateReq = new ContractStateReq();
		HashMap<String,String> selectMatchidByCreditapplicationId = borrowMatchingDAO.selectMatchidByCreditapplicationId(creditapplicationId);
		HashMap<Object,Object> selectOrgams = orgamsDAO.selectOrgams(creditapplicationId);
		contractStateReq.setApplyId(creditapplicationId.toString());//合同状态同步申请编号
		contractStateReq.setMatchId(selectMatchidByCreditapplicationId.get("MATCHID"));//撮合ID
		contractStateReq.setOperationId(Long.parseLong((String) selectOrgams.get("LOAN_OFFICER_ID")));//操作人ID
		contractStateReq.setBorrowState("3");//放款状态（3：成功4：失败）
		list.add(contractStateReq);
		return list;
	}
	/**
	 * 封装放款状态同步参数(失败)
	 * @param creditapplicationId
	 * @return
	 */
	private List<ContractStateReq> getSynBorrowContractStateFail(Long creditapplicationId){
		List<ContractStateReq> list = new ArrayList<ContractStateReq>();
		ContractStateReq contractStateReq = new ContractStateReq();
		HashMap<String,String> selectMatchidByCreditapplicationId = borrowMatchingDAO.selectMatchidByCreditapplicationId(creditapplicationId);
		HashMap<Object,Object> selectOrgams = orgamsDAO.selectOrgams(creditapplicationId);
		contractStateReq.setApplyId(creditapplicationId.toString());//合同状态同步申请编号
		contractStateReq.setMatchId(selectMatchidByCreditapplicationId.get("MATCHID"));//撮合ID
		contractStateReq.setOperationId(Long.parseLong((String) selectOrgams.get("LOAN_OFFICER_ID")));//操作人ID
		contractStateReq.setBorrowState("4");//放款状态（3：成功4：失败）
		list.add(contractStateReq);
		return list;
	}
	/**
	 * 合同同步
	 */
	@Override
	public BorrowContractRes synBorrowContract(
			Long creditapplicationId) {
		BorrowContractReq borrowContractReq = getBorrowContractReq(creditapplicationId);
		// 记Log日志
		log.info("**************icpWebService.synBorrowContract(borrowContractReq); requestParam******************");
		log.info(JsonUtil.getJackson(borrowContractReq));
		BorrowContractRes synBorrowContract = icpWebService.synBorrowContract(borrowContractReq);
		log.info("**************icpWebService.synBorrowContract(borrowContractReq); responseParam******************");
		log.info(JsonUtil.getJackson(synBorrowContract));
		return synBorrowContract;
	}
	/**
	 * 合同同步封装参数
	 * @param creditapplicationId
	 * @return
	 * @throws ParseException 
	 */
	private BorrowContractReq getBorrowContractReq(Long creditapplicationId){
		BorrowContractReq borrowContractReq = new BorrowContractReq();
		HashMap<Object,Object> selectBorrowContract = orgamsDAO.selectBorrowContract(creditapplicationId);
		borrowContractReq.setApplyId(creditapplicationId.toString());//审核申请编号（综合信贷UUID）
		borrowContractReq.setBsApplyId(creditapplicationId);//销售系统进件编号
		List<TradeDealForm> tradeDealFormResult = tradeDealFormDAO.selectTradeDealFormResultByCaId(Long.parseLong(creditapplicationId.toString()));
		borrowContractReq.setBsClientId(tradeDealFormResult.get(0).getPlancode()+(String) selectBorrowContract.get("BSCLIENTID"));//销售系统客户编号
		borrowContractReq.setCity((String) selectBorrowContract.get("CITY"));//所属城市
		borrowContractReq.setContractState("0");// 合同状态(待审核)
		borrowContractReq.setIsExpedited("0");//是否加急
		borrowContractReq.setPurposeCaption("");//借款用途说明
		borrowContractReq.setContractRemark((String) selectBorrowContract.get("REMARK"));// TODO 备注(缺少)
		borrowContractReq.setSubmitTime(dateToXmlDate(new Date()));//提交时间
		borrowContractReq.setClientName((String) selectBorrowContract.get("CLIENTNAME"));//客户姓名
		borrowContractReq.setClientId(selectBorrowContract.get("CLIENTID").toString());//客户编号
		borrowContractReq.setMobile((String) selectBorrowContract.get("MOBILE"));//手机号码
		borrowContractReq.setBorrowPurpose1((String) selectBorrowContract.get("BORROWPURPOSE1"));//借款用途1
		borrowContractReq.setBorrowPurpose2((String) selectBorrowContract.get("BORROWPURPOSE2"));//借款用途２
		borrowContractReq.setBorrowType("1");//借款类型
		borrowContractReq.setBorrowAmt(new BigDecimal(selectBorrowContract.get("BORROWAMT").toString()));//借款总额
		//borrowContractReq.setLoanAmt(new BigDecimal((String)selectBorrowContract.get("LOANAMT")));//客户实际获得金额
		
		borrowContractReq.setSystemSign("07");//系统标识
		//****************************调用产品中心接口start
		RepaymentPlanReq rateInfo = new RepaymentPlanReq();

		ContractRateForQYResult contractRateForQYResult = contractAndLoanDao.qyContractRateForQYResult(creditapplicationId);
		Properties properties = PropertiesUtil.loadProperties("spring/pdf/pdf.properties");
		borrowContractReq.setProductType(properties.getProperty("pdf.productbroadheading"));//产品类型
		borrowContractReq.setClientType(contractRateForQYResult.getProductName());//客户类型
		String getClientName = contractRateForQYResult.getClientName();
		String getBorrowUse = contractRateForQYResult.getBorrowUse();
		String getBorrowUseText = contractRateForQYResult.getBorrowUseText();
		String getProductName = contractRateForQYResult.getProductName();
		Long getDepartmentId = contractRateForQYResult.getDepartmentId();
		Long getCatagoryId = contractRateForQYResult.getCatagoryId();
		Date getAuditDate = contractRateForQYResult.getAuditDate();
		Date getReqDate = contractRateForQYResult.getReqDate();
		Long getProductInfoId = contractRateForQYResult.getProductInfoId();
		BigDecimal getContractMoney = contractRateForQYResult.getContractMoney();
		int getPeriodCount = contractRateForQYResult.getPeriodCount();
		Date getLenderDate = contractRateForQYResult.getLenderDate();
		String getPaymentTypeCode = contractRateForQYResult.getPaymentTypeCode();
		String getParamName = contractRateForQYResult.getParamName();
		BigDecimal getParamValue = contractRateForQYResult.getParamValue();

		rateInfo.setDepartmentId(11011196l);
		rateInfo.setCatagoryId(getCatagoryId);
		rateInfo.setAuditDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getAuditDate)));
		// rateInfo.setReqDate(getReqDate);
		rateInfo.setProductInfoId(getProductInfoId);
		rateInfo.setContractMoney(getContractMoney);
		rateInfo.setPeriodCount(getPeriodCount);
		rateInfo.setLenderDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getLenderDate)));
		// rateInfo.setPaymentTypeCode(getPaymentTypeCode);
		// rateInfo.setParamName(getParamName);
		// rateInfo.setParamValue(getParamValue);

		/*
		 * System.out.println("产品类型Id"+rateInfo.getCatagoryId());
		 * System.out.println("合同金额"+rateInfo.getContractMoney());
		 */
		List<CashFlowVo> list = new ArrayList<CashFlowVo>();
		RepaymentPlanReqResult repaymentPlanReqResult = borrowingProductsService.advanceReturnPlan(rateInfo);//调用产品中心接口
		
		//*************************调用产品中心接口end
		List<PeriodSchedule> periodScheduleList = repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule();
		List<ChargeInfo> chargeInfoList = repaymentPlanReqResult.getFrontChangeList().getChargeInfo();
		double sumManageFee = 0;
		double sumRCharge = 0;
		double sumInterest = 0;
		Date date1 = null;
		Date date2 = null;
		for (int i = 0; i < periodScheduleList.size(); i++) {
			PeriodSchedule periodSchedule = periodScheduleList.get(i);
					
					sumManageFee += periodSchedule.getPeriodCharge().doubleValue();//分期服务费总额
					sumInterest += periodSchedule.getAmortizedInterest().doubleValue();//总利息
			if(i==0){
				date1 = xmlDate2Date(periodSchedule.getRepayDate());
			}
			if(i==periodScheduleList.size()-1){
				date2 = xmlDate2Date(periodSchedule.getRepayDate());
			}
		}
		for (int i = 0; i < chargeInfoList.size(); i++) {
			ChargeInfo chargeInfo = chargeInfoList.get(i);
				
				sumRCharge += chargeInfo.getCharge().doubleValue();//前期金额总和
			
		}
		borrowContractReq.setAmortisation(new Long((long)periodScheduleList.size()));//分期数
		borrowContractReq.setLoanAmt(repaymentPlanReqResult.getRecvMoney());//客户实际获得金额
		//得到年利率
		Double annualizedRateById = iCreditApplicationDAO.getAnnualizedRateById(Integer.parseInt(creditapplicationId.toString()));
		borrowContractReq.setYearIrr(new BigDecimal(annualizedRateById.toString()));//年利率
		borrowContractReq.setManageFee(new BigDecimal(String.valueOf(sumManageFee)) );//管理费
		borrowContractReq.setRCharge(new BigDecimal(String.valueOf(sumRCharge)));//服务费
		borrowContractReq.setInterest(new BigDecimal(String.valueOf(sumInterest)));//利息
		borrowContractReq.setBankBook((String) selectBorrowContract.get("BORROWCONTRACTREQ"));//卡折
		borrowContractReq.setBankAccount((String) selectBorrowContract.get("BANKACCOUNT"));//卡号
		borrowContractReq.setAddress((String) selectBorrowContract.get("ADDRESS"));//收件方地址
		borrowContractReq.setBankName((String) selectBorrowContract.get("BANKNAME"));//开户行
		borrowContractReq.setBankNumber((String) selectBorrowContract.get("BANKNUMBER"));//开户行行号
		borrowContractReq.setReturnType((String) selectBorrowContract.get("PAY_WAY"));//支付方式
		borrowContractReq.setReturnStartDate(dateToXmlDate(date1));//还款开始时间
		borrowContractReq.setReturnEndDate(dateToXmlDate(date2));// 还款结束时间
		/*Date parse = null;
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			parse = dateFormat.parse(selectBorrowContract.get("LOAN_TIME").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		borrowContractReq.setRTime(dateToXmlDate((Date) selectBorrowContract.get("LOAN_TIME")));//要求放款时间
		borrowContractReq.setPlanId(Long.parseLong(selectBorrowContract.get("PLANID").toString()));//信托计划编号
		borrowContractReq.setPlanName((String) selectBorrowContract.get("PLANNAME"));// 信托计划名称
		if(selectBorrowContract.get("DISCOUNTRATE") !=null && Double.parseDouble(selectBorrowContract.get("DISCOUNTRATE").toString())>0){
			borrowContractReq.setDiscountRate(new BigDecimal(selectBorrowContract.get("DISCOUNTRATE").toString()));//折扣率
			borrowContractReq.setDiscountReason("老客户打折");//折扣原因
		}else{
			String str="0";
			borrowContractReq.setDiscountRate(new BigDecimal(str));//折扣率
			borrowContractReq.setDiscountReason("无打折");//折扣原因
		}
		
		borrowContractReq.setContractTime(dateToXmlDate((Date)selectBorrowContract.get("CONTRACTTIME")));//签约时间
		properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
		String cmUrl = properties.getProperty("cm.url");
		String downloadUrl =cmUrl.substring(0,cmUrl.lastIndexOf("/")+1)+"CreditCM";
		borrowContractReq.setDownLoadUrl(downloadUrl+"/zipDownload.do");//附件下载地址
		borrowContractReq.setCheckOffOper(selectBorrowContract.get("OPERATOR_ID").toString());//复核人
		borrowContractReq.setCheckOffTime(dateToXmlDate((Date)selectBorrowContract.get("CREATE_TIME")));//复核时间
		borrowContractReq.setBussTableName("9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529");//业务表名
		borrowContractReq.setFileType("CM_LOAN");//文件类型
		borrowContractReq.setSystemId("00003");//农商贷在CM中的系统标识00003
		borrowContractReq.setViewUrl(cmUrl+"/operation/transferParameter.action?clientId=");//附件查看地址
		String cmIp = properties.getProperty("cm.hostip");
		borrowContractReq.setCmIp(cmIp);//cmIp
		return borrowContractReq;
	}
	/**
	 * 批量合同同步
	 */
	@Override
	public SynBatchBorrowContractResponse synBatchBorrowContract(
			Long creditapplicationId) {
		SynBatchBorrowContract synBatchBorrowContract = getSynBatchBorrowContract(creditapplicationId);
		return null;
	}
	/**
	 * 批量合同同步封装参数
	 * @param creditapplicationId
	 * @return
	 */
	private SynBatchBorrowContract getSynBatchBorrowContract(Long creditapplicationId){
		
		return null;
	}
	
	
	

}

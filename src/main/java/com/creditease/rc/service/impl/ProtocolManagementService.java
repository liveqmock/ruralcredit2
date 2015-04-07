/**
 * Title:ProtocolManagementService.java
 * Description:
 */
package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.pdf.AgreementDownloadResult;
import com.creditease.rc.app.pdf.BankAccount;
import com.creditease.rc.app.pdf.BaseContractReqParam.CommBorrowerList;
import com.creditease.rc.app.pdf.BaseContractReqParam.CommGuarantorList;
import com.creditease.rc.app.pdf.BaseContractReqParam.ParamList;
import com.creditease.rc.app.pdf.BaseContractReqParam.PaymentTypeList;
import com.creditease.rc.app.pdf.BaseContractReqParam.PeriodChargeDiscountList;
import com.creditease.rc.app.pdf.CeBorrowingProductsWS;
import com.creditease.rc.app.pdf.ChargeDiscountInfo;
import com.creditease.rc.app.pdf.ContractPart;
import com.creditease.rc.app.pdf.ContractReqParam;
import com.creditease.rc.app.pdf.Param;
import com.creditease.rc.app.pdf.PaymentTypeConfig;
import com.creditease.rc.dao.IAccountInfoDAO;
import com.creditease.rc.dao.IAmountConfirmDAO;
import com.creditease.rc.dao.IApplicationDao;
import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.ICreditCoBorrowerDao;
import com.creditease.rc.dao.ICustomerConsultDAO;
import com.creditease.rc.dao.IDiscountConfigurationDao;
import com.creditease.rc.dao.IProtocolMappingDAO;
import com.creditease.rc.dao.IProtocolPrefixMappingDAO;
import com.creditease.rc.dao.IProtocolSuffixMappingDAO;
import com.creditease.rc.dao.TradeDealFormDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.CreditCoBorrower;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.domain.ProtocolPrefixMapping;
import com.creditease.rc.domain.ProtocolSuffixMapping;
import com.creditease.rc.domain.TradeDealForm;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.IProtocolManagementService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.CreditDiscountVo;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * Title:ProtocolManagementService.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-3-25
 */
@Service
public class ProtocolManagementService implements IProtocolManagementService {

	/**
	 * @Description 默认构造器
	 */
	public ProtocolManagementService() {
		// TODO Auto-generated constructor stub
	}

	private Logger log = Logger.getLogger(ProtocolManagementService.class);

	@Resource
	private IProtocolMappingDAO protocolMappingDAO;
	@Resource
	private IProtocolPrefixMappingDAO protocolPrefixMappingDAO;
	@Resource
	private IProtocolSuffixMappingDAO protocolSuffixMappingDAO;
	@Resource
	private CeBorrowingProductsWS ceBorrowingProductsWS;
	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;
	@Resource
	private IAccountInfoDAO accountInfoDAO;
	@Resource
	private IAmountConfirmDAO amountConfirmDAO;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private ICustomerConsultDAO customerConsultDAO;

	@Resource
	private IDiscountConfigurationDao discountConfigurationDao;

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;
	
	@Resource
	private TradeDealFormDAO dealFormDAO;
	
	@Resource
	private ICreditCoBorrowerDao creditCoBorrowerDao;
	// 借款申请
	@Resource
	private IApplicationDao applicationDao;

	/**
	 * 
	 * @author 韩大年
	 * @Description: 创建协议编号并保存,返回协议编号
	 * @version v1.0
	 *          2012-12-21
	 */
	public ProtocolMapping saveOrGetProtocolMapping(CreditApplication creditapplication) {
		String branchofficeId = "";
		String branchofficeName = "";
		branchofficeId = creditapplication.getCompanyId();
		branchofficeName = creditapplication.getCompanyName();
		if (CommonUtil.isEmpty(branchofficeId) && CommonUtil.isEmpty(branchofficeName)) {
			throw new BusinessException("小组申请中分公司id或分公司名称不存在");
		}
		// 查询小组是否已经存在协议编号,若存在,取出,不存在,则生成一个
		ProtocolMapping protocolMapping = new ProtocolMapping();
		if (null != creditapplication && null != creditapplication.getCreditapplicationId()) {
			Long creditapplicationId = Long.valueOf(creditapplication.getCreditapplicationId().toString());
			protocolMapping.setCreditapplicationId(creditapplicationId);
		} else {
			throw new AppBusinessException("creditapplication或creditapplicationId为空");
		}
		// 判断协议编号是否已经存在
		List<ProtocolMapping> protocolMappingList = this.protocolMappingDAO
				.selectProtocolMappingListSelective(protocolMapping);
		if (CommonUtil.isNotEmpty(protocolMappingList)) {
			if (protocolMappingList.size() == 1) {
				// 已经存在一个,则返回协议编号
				protocolMapping = protocolMappingList.get(0);
			} else {
				throw new AppBusinessException("业务单号=" + creditapplication.getGroupNumber() + "存在多条记录");
			}
		} else {
			// 不存在,则新增

			// 前缀初始化
			ProtocolPrefixMapping protocolPrefixMapping = new ProtocolPrefixMapping();
			protocolPrefixMapping.setBranchofficeId(branchofficeId);
			List<ProtocolPrefixMapping> prefixMappingList = this.protocolPrefixMappingDAO
					.selectProtocolPrefixMappingListSelective(protocolPrefixMapping);
			if (CommonUtil.isNotEmpty(prefixMappingList) && prefixMappingList.size() == 1) {
				protocolPrefixMapping = prefixMappingList.get(0);
			} else {
				throw new AppBusinessException(branchofficeName + "前缀未配置或多于一个");
			}
			if (!"Y".equals(protocolPrefixMapping.getOnOff())) {
				throw new AppBusinessException("协议编号生成规则未开启");
			}
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(protocolPrefixMapping.getPrefixNumber());
			String dateString = DateUtil.dateConvertString(new Date());
			String simpleYear = dateString.substring(2, 4);
			stringBuffer.append(simpleYear);
			// 编号初始化
			protocolMapping.setBranchofficeId(branchofficeId);
			protocolMapping.setBranchofficeName(branchofficeName);
			protocolMapping.setCreditapplicationId(Long.valueOf(creditapplication.getCreditapplicationId().toString()));
			protocolMapping.setProtocolNumber(stringBuffer.toString());
			try {

				protocolMapping.setOperator(SpringSecurityUtils.getCurrentUser().getName_zh());
			} catch (Exception e) {
				// TODO: handle exception
				protocolMapping.setOperator("");
			}
			protocolMapping.setOperateDate(new Date());
			// 后缀初始化,用于查询
			ProtocolSuffixMapping suffixMapping = new ProtocolSuffixMapping();
			suffixMapping.setBranchofficeId(branchofficeId);
			suffixMapping.setBranchofficeName(branchofficeName);
			suffixMapping.setSimpleYear(simpleYear);
			// 创建序列,并保存
			ProtocolManagementSingleton instance = ProtocolManagementSingleton.getInstance();
			protocolMapping = instance.saveNextval(suffixMapping, protocolMapping, protocolSuffixMappingDAO,
					protocolMappingDAO);

		}
		return protocolMapping;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 调用借款协议下载请求服务
	 * @param creditApplication
	 * @param amountConfirm
	 * @return
	 * @version v1.0
	 *          2013-3-26
	 */
	public String CeAgreementDownloadReq(CreditApplication creditApplication, AmountConfirm amountConfirm) {
		String fileUrl = "";
		ContractReqParam contractReqParam = contractReqParamConfig(creditApplication, amountConfirm);
		log.info("ceBorrowingProductsWS.ceAgreementDownloadReq(contractReqParam) request_params:******");
		log.info(JsonUtil.getJackson(contractReqParam));
		AgreementDownloadResult agreementDownloadResult = ceBorrowingProductsWS
				.ceAgreementDownloadReq(contractReqParam);
		if (null != agreementDownloadResult) {
			log.info("ceBorrowingProductsWS.ceAgreementDownloadReq(agreementDownloadResult) response_params:******");
			log.info(JsonUtil.getJackson(agreementDownloadResult));
			if ("0".equalsIgnoreCase(agreementDownloadResult.getResultCode())) {
				fileUrl = agreementDownloadResult.getRetFile();
			} else {
				throw new AppBusinessException("借款协议下载请求服务失败,原因:" + agreementDownloadResult.getResultMessage());
			}

		}
		return fileUrl;

	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 配置借款协议下载请求入参
	 * @param creditApplication
	 * @param amountConfirm
	 * @return
	 * @version v1.0
	 *          2013-3-26
	 */
	public ContractReqParam contractReqParamConfig(CreditApplication creditApplication, AmountConfirm amountConfirm) {
		List<BorrowerServiceApp> borrowerServiceAppList = borrowerServiceAppDAO
				.selectBorrowerServiceAppList(creditApplication.getCreditapplicationId());
		// 借款人
		BorrowerServiceApp borrower = null;
		// 担保人
		List<BorrowerServiceApp> guarantorList = new ArrayList<BorrowerServiceApp>();
		if (CommonUtil.isNotEmpty(borrowerServiceAppList)) {
			for (BorrowerServiceApp borrowerServiceApp : borrowerServiceAppList) {
				// 借款人
				if ("1".equalsIgnoreCase(borrowerServiceApp.getLeader())) {
					borrower = borrowerServiceApp;
				} else {
					guarantorList.add(borrowerServiceApp);
				}
			}

		}
		ContractReqParam contractReqParam = new ContractReqParam();

        /*
        1.更改：2014-06-04 合同签署日期 ---> 更新为用户自定义期望放款时间
        2.再次更改：2014-07-18 合同签署日期 ---> 更新为当前操作时间
         */
        try {
            GregorianCalendar gregory = new GregorianCalendar();
            gregory.setTime(new Date());
            XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
            contractReqParam.setSignDate(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        /*更改：2014-06-04 合同签署日期 ---> 更新为用户自定义期望放款时间
        contractReqParam.setSignDate(new XMLGregorianCalendarImpl(DateUtil .getGregorianCalendar(amountConfirm.getLoanTime())));
        */

		String contractNo = creditApplication.getContactNumber();
		Long departmentId = creditApplication.getDepartmentId() == null ? 0L : Long.valueOf(creditApplication
				.getDepartmentId());
		Long productInfoId = creditApplication.getRepaymentPlanId() == null ? 0L : Long.valueOf(creditApplication
				.getRepaymentPlanId().toString());
		BigDecimal contractMoney = new BigDecimal(amountConfirm.getAmount() == null ? "0" : amountConfirm.getAmount()
				.toString());
		Integer periodCount = CommonUtil.isEmpty(creditApplication.getInstalments()) ? 0 : Integer
				.valueOf(creditApplication.getInstalments().toString());
		//由于合同打印流程变化，所以借款期限开始日期  改为起息日期
		GregorianCalendar gregorianCalendar = DateUtil
		.getGregorianCalendar(amountConfirm.getBeginInterestTime() == null ? new Date() : amountConfirm.getBeginInterestTime());
		//原来的
		/*GregorianCalendar gregorianCalendar = DateUtil
				.getGregorianCalendar(amountConfirm.getLoanTime() == null ? new Date() : amountConfirm.getLoanTime());*/
		// lenderType String A:中行信托;B:个人
		String lenderType = "B";

		contractReqParam.setDepartmentId(departmentId);
		contractReqParam.setProductInfoId(productInfoId);
		contractReqParam.setContractMoney(contractMoney);
		contractReqParam.setLenderDate(new XMLGregorianCalendarImpl(gregorianCalendar));
		contractReqParam.setPeriodCount(periodCount);
		contractReqParam.setContractNo(contractNo);
		contractReqParam.setLenderType(lenderType);
		// smp中系统标示
// contractReqParam.setSystemSign("44");
		contractReqParam.setIsMultiGuartLetter(true);

		// 产品中心出借人信息 lender
		ContractPart contractPart_lender = new ContractPart();
// String lender_id="201";
		String lender_name = "唐宁";
		String lender_identNo = "12010119730703301X";
		String lender_address = "北京市朝阳区建国路88号SOHO现代城C座16层";
		String lender_telNo = "132123243434";
		String lender_zipCode = "100343";
// contractPart_lender.setId(Long.valueOf(lender_id));
		contractPart_lender.setName(lender_name);
		contractPart_lender.setIdentNo(lender_identNo);
		contractPart_lender.setAddress(lender_address);
		contractPart_lender.setTelNo(lender_telNo);
		contractPart_lender.setZipCode(lender_zipCode);
		contractReqParam.setLender(contractPart_lender);

		// 产品中心借款人信息 borrower
		ContractPart contractPart_borrowor = new ContractPart();
// String borrower_id="";
		String borrower_name = "";
		String borrower_identNo = "";
		String borrower_address = "";
		String borrower_telNo = "";
		String borrower_zipCode = "";

		if (null != borrower) {
// borrower_id=borrower.getBorrowerServiceAppId()==null?"0":borrower.getBorrowerServiceAppId().toString();
			borrower_name = borrower.getName();
			borrower_identNo = borrower.getCredentialsNumber();
			borrower_address = borrower.getHomeAddress();
			borrower_telNo = borrower.getMobilephone();
		}
// contractPart_borrowor.setId(Long.valueOf(borrower_id));
		contractPart_borrowor.setName(borrower_name);
		contractPart_borrowor.setIdentNo(borrower_identNo);
		contractPart_borrowor.setAddress(borrower_address);
		contractPart_borrowor.setTelNo(borrower_telNo);
		contractPart_borrowor.setZipCode(borrower_zipCode);
		contractReqParam.setBorrower(contractPart_borrowor);
        /*委托人信息*/
        ContractPart contractPart_entrustor = new ContractPart();
        AccountInfo info = accountInfoDAO.selectByCrediApplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
        if(null != info){
            contractPart_entrustor.setName(info.getAccountName());
            contractPart_entrustor.setIdentNo(info.getCredentialsNumber());
            contractReqParam.setEntrustor(contractPart_entrustor);
        } else {
            throw new AppBusinessException("打印合同：委托人信息获取失败--->>产品中心借款协议下载接口调用");
        }

        // 共同借款人 ==借款人配偶信息
		CommBorrowerList commBorrowerList = new CommBorrowerList();
		ContractPart contractPart_commBorrower = new ContractPart();
		Familymember familymember = this.borrowerServiceAppDAO.selectSpouseByBorrowerServiceAppId(borrower
				.getBorrowerServiceAppId());
		
// contractPart_commBorrower.setId(Long.valueOf(if()));
		//判断如果共借人是不是配偶
		if(familymember!=null){
		contractPart_commBorrower.setName(familymember.getName());
		contractPart_commBorrower.setIdentNo(familymember.getIdNumber());
		contractPart_commBorrower.setTelNo(familymember.getTelphone());
		}
		
		//获取共借人信息   ==不一定是配偶的共借人
		try {
			CreditCoBorrower creditCoBorrower=this.creditCoBorrowerDao.queryCreditCoBorrowerInfoByBorrowerServiceAppId(borrower
					.getBorrowerServiceAppId().toString());
			if(creditCoBorrower!=null){
				contractPart_commBorrower.setName(creditCoBorrower.getName());
				contractPart_commBorrower.setIdentNo(creditCoBorrower.getIdNumber());
				contractPart_commBorrower.setTelNo(creditCoBorrower.getTelphone());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contractPart_commBorrower.setAddress(borrower.getHomeAddress());
		contractPart_commBorrower.setZipCode("");
		commBorrowerList.getContractPart().add(contractPart_commBorrower);
		contractReqParam.setCommBorrowerList(commBorrowerList);

		// 担保人信息
		CommGuarantorList commGuarantorList = new CommGuarantorList();
		if (CommonUtil.isNotEmpty(guarantorList)) {
			for (BorrowerServiceApp borrowerServiceApp : guarantorList) {
				ContractPart contractPart_commGuarantor = new ContractPart();
				contractPart_commGuarantor.setName(borrowerServiceApp.getName());
				contractPart_commGuarantor.setIdentNo(borrowerServiceApp.getCredentialsNumber());
				contractPart_commGuarantor.setAddress(borrowerServiceApp.getHomeAddress());
				contractPart_commGuarantor.setTelNo(borrowerServiceApp.getMobilephone());
				commGuarantorList.getContractPart().add(contractPart_commGuarantor);
			}
		}
		;

		contractReqParam.setCommGuarantorList(commGuarantorList);

		// 还款方式配置列表
		PaymentTypeList paymentTypeList = new PaymentTypeList();
		PaymentTypeConfig paymentTypeConfig = new PaymentTypeConfig();
		String paymentTypeCode = CommonUtil.isEmpty(creditApplication.getRepaymentType()) ? "ZND" : creditApplication
				.getRepaymentType();
		paymentTypeConfig.setPaymentTypeCode(paymentTypeCode);
		paymentTypeList.getPaymentTypeConfig().add(paymentTypeConfig);
		contractReqParam.setPaymentTypeList(paymentTypeList);

		// 业务类型:0-分公司,1-个人
		String businessType = creditApplication.getBusinessType();
		// 银行卡信息-只有个人业务存在银行卡
		BankAccount bankAccount = new BankAccount();
		AccountInfo accountInfo = accountInfoDAO.selectByAccountID(creditApplication.getAccountInfoId());
		String accountName = "";
		String accountNum = "";
		String bankName = "";
		String accountIdentNo = "";
		if (null != accountInfo && "1".equals(businessType)) {
			accountName = accountInfo.getAccountName();
			accountNum = accountInfo.getAccount();
			bankName = accountInfo.getBankName();
			accountIdentNo = accountInfo.getCredentialsNumber();
		}
		bankAccount.setAccountName(accountName);
		bankAccount.setAccountNum(accountNum);
		bankAccount.setBankName(bankName);
//		bankAccount.setAccountIdentNo(accountIdentNo);
        /*借款人收款账户*/
		contractReqParam.setReceBankAccount(bankAccount);

        /*收款账户*/
        bankAccount = new BankAccount();
        accountInfo = accountInfoDAO.selectByAccountID(creditApplication.getReturnAccountInfoId());
        bankAccount.setAccountName(accountInfo.getAccountName());
        bankAccount.setAccountNum(accountInfo.getAccount());
        bankAccount.setBankName(accountInfo.getBankName());
//		bankAccount.setAccountIdentNo(accountIdentNo);
        /*借款人还款账户*/
        contractReqParam.setRepayBankAccount(bankAccount);

		// ParamDTO
		ParamList paramList = new ParamList();
		// 签约省:signProvince
		Param param_signProvince = new Param();
		// 签约城市:signCity
		Param param_signCity = new Param();
		// 签约地区:signPart
		Param param_signPart = new Param();
		// 签约年:signYear
		Param param_signYear = new Param();
		// 签约月:signMonth
		Param param_signMonth = new Param();
		// 签约日:signDay
		Param param_signDay = new Param();
		// paymentMethod 收款方式(1带表现金 2代表银行)
		Param param_paymentMethod = new Param();
		// 公司办公室电话
		Param param_companyTel = new Param();
		// accountManager 客户经理
		Param param_accountManager = new Param();

		param_signProvince.setParamName("signProvince");
		param_signCity.setParamName("signCity");
		param_signPart.setParamName("signPart");
		param_signYear.setParamName("signYear");
		param_signMonth.setParamName("signMonth");
		param_signDay.setParamName("signDay");
		param_paymentMethod.setParamName("paymentMethod");
		param_companyTel.setParamName("companyTel");
		param_accountManager.setParamName("accountManager");

		String homeAddress = "";
		homeAddress = borrower.getHomeAddress();
		if (CommonUtil.isNotEmpty(homeAddress)) {
			String addressArr[] = homeAddress.split("-");
			int len = addressArr.length;
			if (len >= 3) {
				param_signProvince.setParamValue(addressArr[0].substring(0, addressArr[0].length() - 1));
				param_signCity.setParamValue(addressArr[1].substring(0, addressArr[1].length() - 1));
				param_signPart.setParamValue(addressArr[2].substring(0, addressArr[2].length() - 1));
			}
		}
        /*签署地址*/
        contractReqParam.setSignAddress(param_signProvince.getParamValue() + "省" + param_signCity.getParamValue() + "市" + param_signPart.getParamValue() + "区");
		Date loanDate = amountConfirm.getLoanTime();
		if (null == loanDate) {
			loanDate = new Date();
		}
		if (null != loanDate) {
			String startRepaymentDate = DateUtil.dateConvertString(loanDate);
			param_signYear.setParamValue(startRepaymentDate.substring(0, 4));
			param_signMonth.setParamValue(startRepaymentDate.substring(5, 7));
			param_signDay.setParamValue(startRepaymentDate.substring(8));
		}

		// 对公=现金,对私=银行
		// 业务类型:0-分公司,1-个人
		// paymentMethod 收款方式(1带表现金 2代表银行)
		if (CommonUtil.isNotEmpty(businessType)) {
			if ("0".equals(businessType)) {
				param_paymentMethod.setParamValue("1");
			} else {
				param_paymentMethod.setParamValue("2");
			}
		}
		param_accountManager.setParamValue(creditApplication.getLoanOfficerName());
        /*客户经理*/
        contractReqParam.setCustomerManager(creditApplication.getLoanOfficerName());

		paramList.getParam().add(param_signProvince);
		paramList.getParam().add(param_signCity);
		paramList.getParam().add(param_signPart);
		paramList.getParam().add(param_signYear);
		paramList.getParam().add(param_signMonth);
		paramList.getParam().add(param_signDay);
		paramList.getParam().add(param_paymentMethod);
		paramList.getParam().add(param_accountManager);
		paramList.getParam().add(param_companyTel);
		contractReqParam.setParamList(paramList);

		/*之前的取法 现在修改为：// 借款详细用途
		String loanPurpose = "";
		String getBorrowUse = "";
            //1.从客户咨询获取
		List<CustomerConsult> customerConsultList = customerConsultDAO
				.queryCustomerConsultByCreditApplicationId(creditApplication.getCreditapplicationId());
            //2.从营销咨询获取
		List<CustomerConsultPool> customerConsultPools = customerConsultPoolService
				.queryCustomerConsultPoolByBorrowerServiceAppId(borrower.getBorrowerServiceAppId());

		if (CommonUtil.isNotEmpty(customerConsultList)) {
			CustomerConsult consult = customerConsultList.get(0);
			if (consult != null) {
				getBorrowUse = consult.getBorrowUse();
			}
		} else if (CommonUtil.isNotEmpty(customerConsultPools)) {
			CustomerConsultPool consultPool = customerConsultPools.get(0);
			getBorrowUse = consultPool.getBorrowUse();
		}
		if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
			Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
			String detailsLoansuseType = getBorrowUse;
			if (detailsLoansuseTypeMap.containsKey(detailsLoansuseType)) {
				loanPurpose = detailsLoansuseTypeMap.get(detailsLoansuseType);
			}
		}*/
		
		
		//现在的取法的取法 现在修改为： 借款详细用途 2014-12-25修改
		String loanPurpose = "";
		String getBorrowUse = "";
        //1.从申请单中获取
		List<Application> applicationsList = (List<Application>) applicationDao.queryList(
				"RL_APPLICATION.selectBorrowerServiceApp", Long.valueOf(borrower.getBorrowerServiceAppId()));
		if (CommonUtil.isNotEmpty(applicationsList)) {
			for(int i=0;i<applicationsList.size();i++){
				Application application = applicationsList.get(i);
				if (application != null) {
					getBorrowUse = application.getBorrowUse();
				}
				if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
					Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
					String detailsLoansuseType = getBorrowUse;
					if (detailsLoansuseTypeMap.containsKey(detailsLoansuseType)) {
						//追加借款用途
						loanPurpose += detailsLoansuseTypeMap.get(detailsLoansuseType)+",";
					}
				}
			}
			StringBuffer str = new StringBuffer(loanPurpose); 
			//截取
			StringBuffer loanPurpose2=str.delete(loanPurpose.lastIndexOf(","),loanPurpose.length());
			loanPurpose=loanPurpose2.toString();
		}
		
		System.out.println("--------------------------" + loanPurpose + "--------------------------");
		System.out.println("--------------------------" + loanPurpose + "--------------------------");
		System.out.println("--------------------------" + loanPurpose + "--------------------------");
		contractReqParam.setLoanPurpose(loanPurpose);

		/** 郝强提交循环贷打折begin **/
		CreditDiscountVo creditDiscountVo = discountConfigurationDao.checkDiscountConfiguration(creditApplication
				.getCreditapplicationId().longValue());
		Long getCreditapplicationId = creditDiscountVo.getCreditapplicationId();
		String getDiscountFlag = creditDiscountVo.getDiscountFlag();
		BigDecimal getDiscount = creditDiscountVo.getDiscount();
		if ("1".equals(getDiscountFlag)) {
			// 打折
			if (getDiscount == null) {
				// 符合打折标准却不需要打折
				discountConfigurationDao.updateCreditDiscountVo(getCreditapplicationId, "0", null);
			} else if (getDiscount.compareTo(new BigDecimal(1)) == 0) {
				// 符合打折标准却不需要打折
				discountConfigurationDao.updateCreditDiscountVo(getCreditapplicationId, "0", null);
			} else {
				// 正常
				PeriodChargeDiscountList periodChargeDiscountList = new PeriodChargeDiscountList();
				List<ChargeDiscountInfo> chargeDiscountInfos = periodChargeDiscountList.getChargeDiscountInfo();
				ChargeDiscountInfo chargeDiscountInfo = new ChargeDiscountInfo();
				chargeDiscountInfo.setChargeType("HJYCB");
				chargeDiscountInfo.setDiscount(getDiscount);
				chargeDiscountInfos.add(chargeDiscountInfo);
				contractReqParam.setPeriodChargeDiscountList(periodChargeDiscountList);
				discountConfigurationDao.updateCreditDiscountVo(getCreditapplicationId, "1", getDiscount);
			}
		} else {
			// 不打折
		}
		/** 郝强提交循环贷打折end **/

		return contractReqParam;
	}

	/**
	 * 协议下载
	 * 
	 * @author 韩大年
	 * @Description:
	 * @param creditApplication
	 * @param amountConfirm
	 * @return
	 * @version v1.0
	 *          2013-3-26
	 */
	public String saveAndDownProtocol(Integer creditapplicationId, Date loanDate) {
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCreditapplicationId(creditapplicationId);
		creditApplication = this.creditApplicationDAO.selectCreditApplicationById(creditApplication);

		// 获取协议编号
		ProtocolMapping protocolNumber = saveOrGetProtocolMapping(creditApplication);
		creditApplication.setContactNumber(protocolNumber.getProtocolNumber());

		AmountConfirm amountConfirm = amountConfirmDAO.selectNew(creditApplication.getCreditapplicationId());
		amountConfirm.setLoanTime(loanDate);
		// 调用产品中心下载合同
		String downLoadUrl = CeAgreementDownloadReq(creditApplication, amountConfirm);

		protocolNumber.setDownLoadUrl(downLoadUrl);
		this.creditApplicationDAO.updateCreditApplication(creditApplication);
		this.protocolMappingDAO.updateByPrimaryKeySelective(protocolNumber);
		return downLoadUrl;

	}

	@Override
	public Message searchAndDownProtocol(Integer creditapplicationId, HttpServletRequest request) {
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCreditapplicationId(creditapplicationId);
		creditApplication = this.creditApplicationDAO.selectCreditApplicationById(creditApplication);
		Message message = new Message();
		AmountConfirm amountConfirm = amountConfirmDAO.selectNew(creditApplication.getCreditapplicationId());
		if(null != amountConfirm){
			if("0".equals(amountConfirm.getLendingChannel())){
				amountConfirm.setLoanTime(creditApplication.getSignagreementDate());
				String url = CeAgreementDownloadReq(creditApplication, amountConfirm);
				message.setMsg(url);
				message.setSuccess(true);
			}else{
				//信托查询撮合结果
				List<TradeDealForm> tradeDealFormResultByCaId = dealFormDAO.selectTradeDealFormResultByCaId(Long.parseLong(creditapplicationId.toString()));
				TradeDealForm tradeDealForm = tradeDealFormResultByCaId.get(0);
				creditApplication.setTrustAccountName(tradeDealForm.getTrustAccountName());
				creditApplication.setAccountName(tradeDealForm.getAccountName());
				creditApplication.setSubAccountName(tradeDealForm.getSubAccountName());
				creditApplication.setAccountNo(tradeDealForm.getAccountNo());
				amountConfirm.setLoanTime(creditApplication.getSignagreementDate());
				String url = CeAgreementDownloadReqXinTuo(creditApplication, amountConfirm);
				message.setMsg(url);
				message.setSuccess(true);
			}
		}
		
		return message;
	}

	/**
	 * 协议下载
	 * 
	 * @author 罗红杰
	 * @Description:
	 * @param creditApplication
	 * @param amountConfirm
	 * @return
	 * @version v1.0
	 *          2014-9-11
	 *          
	 */
	@Override
	public String saveAndDownProtocolXinTuo(Integer creditapplicationId, Date loanDate) {
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCreditapplicationId(creditapplicationId);
		creditApplication = this.creditApplicationDAO.selectCreditApplicationById(creditApplication);

		// 获取协议编号
		ProtocolMapping protocolNumber = saveOrGetProtocolMapping(creditApplication);
		String plancode = "";
		//信托专户托管行名称 
		String accountName="";
		//信托专户托管行
		String subAccountName="";
		//信托专户账号
		String accountNo="";
		//信托账号开户名称
		String trustAccountName="";
		List<TradeDealForm> tradeDealFormResult = dealFormDAO.selectTradeDealFormResultByCaId(Long.parseLong(creditapplicationId.toString()));
		if(CommonUtil.isNotEmpty(tradeDealFormResult)){
			plancode = tradeDealFormResult.get(0).getPlancode();
			accountName=tradeDealFormResult.get(0).getAccountName();
			subAccountName = tradeDealFormResult.get(0).getSubAccountName();
			accountNo = tradeDealFormResult.get(0).getAccountNo();
			trustAccountName=tradeDealFormResult.get(0).getTrustAccountName();
		}
		creditApplication.setContactNumber(plancode+protocolNumber.getProtocolNumber());
		creditApplication.setTrustAccountName(trustAccountName);
		creditApplication.setAccountName(accountName);
		creditApplication.setAccountNo(accountNo);
		creditApplication.setSubAccountName(subAccountName);
		
		AmountConfirm amountConfirm = amountConfirmDAO.selectNew(creditApplication.getCreditapplicationId());
		amountConfirm.setLoanTime(loanDate);
		// 调用产品中心下载合同
		String downLoadUrl = CeAgreementDownloadReqXinTuo(creditApplication, amountConfirm);

		protocolNumber.setDownLoadUrl(downLoadUrl);
		this.creditApplicationDAO.updateCreditApplication(creditApplication);
		this.protocolMappingDAO.updateByPrimaryKeySelective(protocolNumber);
		return downLoadUrl;

	}
	/**
	 * 
	 * @author 罗红杰
	 * @Description: 调用借款协议下载请求服务
	 * @param creditApplication
	 * @param amountConfirm
	 * @return
	 * @version v1.0
	 *          2014-9-11
	 */
	@Override
	public String CeAgreementDownloadReqXinTuo(CreditApplication creditApplication, AmountConfirm amountConfirm) {
		String fileUrl = "";
		ContractReqParam contractReqParam = contractReqParamConfigXinTuo(creditApplication, amountConfirm);
		log.info("ceBorrowingProductsWS.ceAgreementDownloadReq(contractReqParam) request_params:******");
		log.info(JsonUtil.getJackson(contractReqParam));
		AgreementDownloadResult agreementDownloadResult = ceBorrowingProductsWS
				.ceAgreementDownloadReq(contractReqParam);
		if (null != agreementDownloadResult) {
			log.info("ceBorrowingProductsWS.ceAgreementDownloadReq(agreementDownloadResult) response_params:******");
			log.info(JsonUtil.getJackson(agreementDownloadResult));
			if ("0".equalsIgnoreCase(agreementDownloadResult.getResultCode())) {
				fileUrl = agreementDownloadResult.getRetFile();
			} else {
				throw new AppBusinessException("借款协议下载请求服务失败,原因:" + agreementDownloadResult.getResultMessage());
			}

		}
		return fileUrl;

	}
	/**
	 * 
	 * @author 罗红杰
	 * @Description: 配置借款协议下载请求入参 
	 * @param creditApplication
	 * @param amountConfirm
	 * @return
	 * @version v1.0
	 *          2014-9-11
	 */
	public ContractReqParam contractReqParamConfigXinTuo(CreditApplication creditApplication, AmountConfirm amountConfirm) {
		List<BorrowerServiceApp> borrowerServiceAppList = borrowerServiceAppDAO
				.selectBorrowerServiceAppList(creditApplication.getCreditapplicationId());
		//luohogjie 
		
		// 借款人
		BorrowerServiceApp borrower = null;
		// 担保人
		List<BorrowerServiceApp> guarantorList = new ArrayList<BorrowerServiceApp>();
		if (CommonUtil.isNotEmpty(borrowerServiceAppList)) {
			for (BorrowerServiceApp borrowerServiceApp : borrowerServiceAppList) {
				// 借款人
				if ("1".equalsIgnoreCase(borrowerServiceApp.getLeader())) {
					borrower = borrowerServiceApp;
				} else {
					guarantorList.add(borrowerServiceApp);
				}
			}

		}
		ContractReqParam contractReqParam = new ContractReqParam();
		
        /*
        1.更改：2014-06-04 合同签署日期 ---> 更新为用户自定义期望放款时间
        2.再次更改：2014-07-18 合同签署日期 ---> 更新为当前操作时间
         */
        try {
            GregorianCalendar gregory = new GregorianCalendar();
            gregory.setTime(new Date());
            XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
            contractReqParam.setSignDate(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        /*更改：2014-06-04 合同签署日期 ---> 更新为用户自定义期望放款时间
        contractReqParam.setSignDate(new XMLGregorianCalendarImpl(DateUtil .getGregorianCalendar(amountConfirm.getLoanTime())));
        */

		String contractNo = creditApplication.getContactNumber();
		Long departmentId = creditApplication.getDepartmentId() == null ? 0L : Long.valueOf(creditApplication
				.getDepartmentId());
		Long productInfoId = creditApplication.getRepaymentPlanId() == null ? 0L : Long.valueOf(creditApplication
				.getRepaymentPlanId().toString());
		BigDecimal contractMoney = new BigDecimal(amountConfirm.getAmount() == null ? "0" : amountConfirm.getAmount()
				.toString());
		Integer periodCount = CommonUtil.isEmpty(creditApplication.getInstalments()) ? 0 : Integer
				.valueOf(creditApplication.getInstalments().toString());
		//由于合同打印流程变化，所以借款期限开始日期  改为起息日期
		GregorianCalendar gregorianCalendar = DateUtil
		.getGregorianCalendar(amountConfirm.getBeginInterestTime() == null ? new Date() : amountConfirm.getBeginInterestTime());
		//原来的
		/*GregorianCalendar gregorianCalendar = DateUtil
				.getGregorianCalendar(amountConfirm.getLoanTime() == null ? new Date() : amountConfirm.getLoanTime());*/
		// lenderType String A:中行信托;B:个人
		String lenderType = "A";

		contractReqParam.setDepartmentId(departmentId);
		contractReqParam.setProductInfoId(productInfoId);
		contractReqParam.setContractMoney(contractMoney);
		contractReqParam.setLenderDate(new XMLGregorianCalendarImpl(gregorianCalendar));
		contractReqParam.setPeriodCount(periodCount);
		contractReqParam.setContractNo(contractNo);
		contractReqParam.setLenderType(lenderType);
		
		// smp中系统标示
// contractReqParam.setSystemSign("44");
		contractReqParam.setIsMultiGuartLetter(true);

		// 产品中心出借人信息 lender
		ContractPart contractPart_lender = new ContractPart();
// String lender_id="201";
		String lender_name = "唐宁";
		String lender_identNo = "12010119730703301X";
		String lender_address = "北京市朝阳区建国路88号SOHO现代城C座16层";
		String lender_telNo = "132123243434";
		String lender_zipCode = "100343";
// contractPart_lender.setId(Long.valueOf(lender_id));
		contractPart_lender.setName(lender_name);
		contractPart_lender.setIdentNo(lender_identNo);
		contractPart_lender.setAddress(lender_address);
		contractPart_lender.setTelNo(lender_telNo);
		contractPart_lender.setZipCode(lender_zipCode);
		contractReqParam.setLender(contractPart_lender);

		// 产品中心借款人信息 borrower
		ContractPart contractPart_borrowor = new ContractPart();
// String borrower_id="";
		String borrower_name = "";
		String borrower_identNo = "";
		String borrower_address = "";
		String borrower_telNo = "";
		String borrower_zipCode = "";

		if (null != borrower) {
// borrower_id=borrower.getBorrowerServiceAppId()==null?"0":borrower.getBorrowerServiceAppId().toString();
			borrower_name = borrower.getName();
			borrower_identNo = borrower.getCredentialsNumber();
			borrower_address = borrower.getHomeAddress();
			borrower_telNo = borrower.getMobilephone();
		}
// contractPart_borrowor.setId(Long.valueOf(borrower_id));
		contractPart_borrowor.setName(borrower_name);
		contractPart_borrowor.setIdentNo(borrower_identNo);
		contractPart_borrowor.setAddress(borrower_address);
		contractPart_borrowor.setTelNo(borrower_telNo);
		contractPart_borrowor.setZipCode(borrower_zipCode);
		contractReqParam.setBorrower(contractPart_borrowor);
        /*委托人信息*/
        ContractPart contractPart_entrustor = new ContractPart();
        AccountInfo info = accountInfoDAO.selectByCrediApplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
        if(null != info){
            contractPart_entrustor.setName(info.getAccountName());
            contractPart_entrustor.setIdentNo(info.getCredentialsNumber());
            contractReqParam.setEntrustor(contractPart_entrustor);
        } else {
            throw new AppBusinessException("打印合同：委托人信息获取失败--->>产品中心借款协议下载接口调用");
        }
     // 共同借款人 ==借款人配偶信息
		CommBorrowerList commBorrowerList = new CommBorrowerList();
		ContractPart contractPart_commBorrower = new ContractPart();
		Familymember familymember = this.borrowerServiceAppDAO.selectSpouseByBorrowerServiceAppId(borrower
				.getBorrowerServiceAppId());
		
// contractPart_commBorrower.setId(Long.valueOf(if()));
		//判断如果共借人是不是配偶
		if(familymember!=null){
		contractPart_commBorrower.setName(familymember.getName());
		contractPart_commBorrower.setIdentNo(familymember.getIdNumber());
		contractPart_commBorrower.setTelNo(familymember.getTelphone());
		}
		
		//获取共借人信息   ==不一定是配偶的共借人
		try {
			CreditCoBorrower creditCoBorrower=this.creditCoBorrowerDao.queryCreditCoBorrowerInfoByBorrowerServiceAppId(borrower
					.getBorrowerServiceAppId().toString());
			if(creditCoBorrower!=null){
				contractPart_commBorrower.setName(creditCoBorrower.getName());
				contractPart_commBorrower.setIdentNo(creditCoBorrower.getIdNumber());
				contractPart_commBorrower.setTelNo(creditCoBorrower.getTelphone());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contractPart_commBorrower.setAddress(borrower.getHomeAddress());
		contractPart_commBorrower.setZipCode("");
		commBorrowerList.getContractPart().add(contractPart_commBorrower);
		contractReqParam.setCommBorrowerList(commBorrowerList);
		// 担保人信息
		CommGuarantorList commGuarantorList = new CommGuarantorList();
		if (CommonUtil.isNotEmpty(guarantorList)) {
			for (BorrowerServiceApp borrowerServiceApp : guarantorList) {
				ContractPart contractPart_commGuarantor = new ContractPart();
				contractPart_commGuarantor.setName(borrowerServiceApp.getName());
				contractPart_commGuarantor.setIdentNo(borrowerServiceApp.getCredentialsNumber());
				contractPart_commGuarantor.setAddress(borrowerServiceApp.getHomeAddress());
				contractPart_commGuarantor.setTelNo(borrowerServiceApp.getMobilephone());
				commGuarantorList.getContractPart().add(contractPart_commGuarantor);
			}
		}
		;

		contractReqParam.setCommGuarantorList(commGuarantorList);

		// 还款方式配置列表
		PaymentTypeList paymentTypeList = new PaymentTypeList();
		PaymentTypeConfig paymentTypeConfig = new PaymentTypeConfig();
		String paymentTypeCode = CommonUtil.isEmpty(creditApplication.getRepaymentType()) ? "ZND" : creditApplication
				.getRepaymentType();
		paymentTypeConfig.setPaymentTypeCode(paymentTypeCode);
		paymentTypeList.getPaymentTypeConfig().add(paymentTypeConfig);
		contractReqParam.setPaymentTypeList(paymentTypeList);

		// 业务类型:0-分公司,1-个人
		String businessType = creditApplication.getBusinessType();
		// 银行卡信息-只有个人业务存在银行卡
		BankAccount bankAccount = new BankAccount();
		AccountInfo accountInfo = accountInfoDAO.selectByAccountID(creditApplication.getAccountInfoId());
		String accountName = "";
		String accountNum = "";
		String bankName = "";
		String accountIdentNo = "";
		if (null != accountInfo && "1".equals(businessType)) {
			accountName = accountInfo.getAccountName();
			accountNum = accountInfo.getAccount();
			bankName = accountInfo.getBankName();
			accountIdentNo = accountInfo.getCredentialsNumber();
		}
		bankAccount.setAccountName(accountName);
		bankAccount.setAccountNum(accountNum);
		bankAccount.setBankName(bankName);
//		bankAccount.setAccountIdentNo(accountIdentNo);
        /*借款人收款账户*/
		contractReqParam.setReceBankAccount(bankAccount);

        /*收款账户*/
        bankAccount = new BankAccount();
        accountInfo = accountInfoDAO.selectByAccountID(creditApplication.getReturnAccountInfoId());
        bankAccount.setAccountName(accountInfo.getAccountName());
        bankAccount.setAccountNum(accountInfo.getAccount());
        bankAccount.setBankName(accountInfo.getBankName());
//		bankAccount.setAccountIdentNo(accountIdentNo);
        /*借款人还款账户*/
        contractReqParam.setRepayBankAccount(bankAccount);

		// ParamDTO
		ParamList paramList = new ParamList();
		// 签约省:signProvince
		Param param_signProvince = new Param();
		// 签约城市:signCity
		Param param_signCity = new Param();
		// 签约地区:signPart
		Param param_signPart = new Param();
		// 签约年:signYear
		Param param_signYear = new Param();
		// 签约月:signMonth
		Param param_signMonth = new Param();
		// 签约日:signDay
		Param param_signDay = new Param();
		// paymentMethod 收款方式(1带表现金 2代表银行)
		Param param_paymentMethod = new Param();
		// 公司办公室电话
		Param param_companyTel = new Param();
		// accountManager 客户经理
		Param param_accountManager = new Param();
		//信托账号开户名称
		Param param_trustAccountName = new Param();
		//信托专户托管行名称 
		Param param_account_name = new Param();
		//信托专户托管行
		Param param_subAccountName=new Param();
		//信托专户账号
		Param param_accountNo=new Param();

		param_signProvince.setParamName("signProvince");
		param_signCity.setParamName("signCity");
		param_signPart.setParamName("signPart");
		param_signYear.setParamName("signYear");
		param_signMonth.setParamName("signMonth");
		param_signDay.setParamName("signDay");
		param_paymentMethod.setParamName("paymentMethod");
		param_companyTel.setParamName("companyTel");
		param_accountManager.setParamName("accountManager");
		param_trustAccountName.setParamName("trustAccountName");
		param_trustAccountName.setParamValue(creditApplication.getTrustAccountName());
		param_account_name.setParamName("account_name");
		param_account_name.setParamValue(creditApplication.getAccountName());
		param_subAccountName.setParamName("subAccountName");
		param_subAccountName.setParamValue(creditApplication.getSubAccountName());
		param_accountNo.setParamName("accountNo");
		param_accountNo.setParamValue(creditApplication.getAccountNo());
		
		String homeAddress = "";
		homeAddress = borrower.getHomeAddress();
		if (CommonUtil.isNotEmpty(homeAddress)) {
			String addressArr[] = homeAddress.split("-");
			int len = addressArr.length;
			if (len >= 3) {
				param_signProvince.setParamValue(addressArr[0].substring(0, addressArr[0].length() - 1));
				param_signCity.setParamValue(addressArr[1].substring(0, addressArr[1].length() - 1));
				param_signPart.setParamValue(addressArr[2].substring(0, addressArr[2].length() - 1));
			}
		}
        /*签署地址*/
        contractReqParam.setSignAddress(param_signProvince.getParamValue() + "省" + param_signCity.getParamValue() + "市" + param_signPart.getParamValue() + "区");
		Date loanDate = amountConfirm.getLoanTime();
		if (null == loanDate) {
			loanDate = new Date();
		}
		if (null != loanDate) {
			String startRepaymentDate = DateUtil.dateConvertString(loanDate);
			param_signYear.setParamValue(startRepaymentDate.substring(0, 4));
			param_signMonth.setParamValue(startRepaymentDate.substring(5, 7));
			param_signDay.setParamValue(startRepaymentDate.substring(8));
		}

		// 对公=现金,对私=银行
		// 业务类型:0-分公司,1-个人
		// paymentMethod 收款方式(1带表现金 2代表银行)
		if (CommonUtil.isNotEmpty(businessType)) {
			if ("0".equals(businessType)) {
				param_paymentMethod.setParamValue("1");
			} else {
				param_paymentMethod.setParamValue("2");
			}
		}
		param_accountManager.setParamValue(creditApplication.getLoanOfficerName());
        /*客户经理*/
        contractReqParam.setCustomerManager(creditApplication.getLoanOfficerName());

		paramList.getParam().add(param_signProvince);
		paramList.getParam().add(param_signCity);
		paramList.getParam().add(param_signPart);
		paramList.getParam().add(param_signYear);
		paramList.getParam().add(param_signMonth);
		paramList.getParam().add(param_signDay);
		paramList.getParam().add(param_paymentMethod);
		paramList.getParam().add(param_accountManager);
		paramList.getParam().add(param_companyTel);
		paramList.getParam().add(param_trustAccountName);
		paramList.getParam().add(param_account_name);
		paramList.getParam().add(param_subAccountName);
		paramList.getParam().add(param_accountNo);
		contractReqParam.setParamList(paramList);

		/*// 之前取得    借款详细用途
		String loanPurpose = "";
		String getBorrowUse = "";
            //1.从客户咨询获取
		List<CustomerConsult> customerConsultList = customerConsultDAO
				.queryCustomerConsultByCreditApplicationId(creditApplication.getCreditapplicationId());
            //2.从营销咨询获取
		List<CustomerConsultPool> customerConsultPools = customerConsultPoolService
				.queryCustomerConsultPoolByBorrowerServiceAppId(borrower.getBorrowerServiceAppId());

		if (CommonUtil.isNotEmpty(customerConsultList)) {
			CustomerConsult consult = customerConsultList.get(0);
			if (consult != null) {
				getBorrowUse = consult.getBorrowUse();
			}
		} else if (CommonUtil.isNotEmpty(customerConsultPools)) {
			CustomerConsultPool consultPool = customerConsultPools.get(0);
			getBorrowUse = consultPool.getBorrowUse();
		}
		if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
			Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
			String detailsLoansuseType = getBorrowUse;
			if (detailsLoansuseTypeMap.containsKey(detailsLoansuseType)) {
				loanPurpose = detailsLoansuseTypeMap.get(detailsLoansuseType);
			}
		}*/
		//现在的取法的取法 现在修改为： 借款详细用途 2014-12-25修改
		String loanPurpose = "";
		String getBorrowUse = "";
        //1.从申请单中获取
		List<Application> applicationsList = (List<Application>) applicationDao.queryList(
				"RL_APPLICATION.selectBorrowerServiceApp", Long.valueOf(borrower.getBorrowerServiceAppId()));
		if (CommonUtil.isNotEmpty(applicationsList)) {
			for(int i=0;i<applicationsList.size();i++){
				Application application = applicationsList.get(i);
				if (application != null) {
					getBorrowUse = application.getBorrowUse();
				}
				if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
					Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
					String detailsLoansuseType = getBorrowUse;
					if (detailsLoansuseTypeMap.containsKey(detailsLoansuseType)) {
						//追加借款用途
						loanPurpose += detailsLoansuseTypeMap.get(detailsLoansuseType)+",";
					}
				}
			}
			StringBuffer str = new StringBuffer(loanPurpose); 
			//截取
			StringBuffer loanPurpose2=str.delete(loanPurpose.lastIndexOf(","),loanPurpose.length());
			loanPurpose=loanPurpose2.toString();
		}
		System.out.println("--------------------------" + loanPurpose + "--------------------------");
		System.out.println("--------------------------" + loanPurpose + "--------------------------");
		System.out.println("--------------------------" + loanPurpose + "--------------------------");
		contractReqParam.setLoanPurpose(loanPurpose);

		/** 郝强提交循环贷打折begin **/
		CreditDiscountVo creditDiscountVo = discountConfigurationDao.checkDiscountConfiguration(creditApplication
				.getCreditapplicationId().longValue());
		Long getCreditapplicationId = creditDiscountVo.getCreditapplicationId();
		String getDiscountFlag = creditDiscountVo.getDiscountFlag();
		BigDecimal getDiscount = creditDiscountVo.getDiscount();
		if ("1".equals(getDiscountFlag)) {
			// 打折
			if (getDiscount == null) {
				// 符合打折标准却不需要打折
				discountConfigurationDao.updateCreditDiscountVo(getCreditapplicationId, "0", null);
			} else if (getDiscount.compareTo(new BigDecimal(1)) == 0) {
				// 符合打折标准却不需要打折
				discountConfigurationDao.updateCreditDiscountVo(getCreditapplicationId, "0", null);
			} else {
				// 正常
				PeriodChargeDiscountList periodChargeDiscountList = new PeriodChargeDiscountList();
				List<ChargeDiscountInfo> chargeDiscountInfos = periodChargeDiscountList.getChargeDiscountInfo();
				ChargeDiscountInfo chargeDiscountInfo = new ChargeDiscountInfo();
				chargeDiscountInfo.setChargeType("HJYCB");
				chargeDiscountInfo.setDiscount(getDiscount);
				chargeDiscountInfos.add(chargeDiscountInfo);
				contractReqParam.setPeriodChargeDiscountList(periodChargeDiscountList);
				discountConfigurationDao.updateCreditDiscountVo(getCreditapplicationId, "1", getDiscount);
			}
		} else {
			// 不打折
		}
		/** 郝强提交循环贷打折end **/

		return contractReqParam;
	}
}


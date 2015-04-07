package com.creditease.rc.service.impl;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.*;
import com.creditease.rc.domain.*;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.HouseServeyVo;
import com.creditease.rc.vo.IDInfoVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author zhangman
 * 
 */
@Service
public class BorrowerApplicationService implements IBorrowerApplicationService {

	// 个人申请
	@Resource
	private IBorrowerServiceDAO borrowerServiceDAO;
	// 家庭
	@Resource
	private IFamilymemberDao familymemberDao;

    // 家庭
    @Resource
    private IFamilymemberService familymemberService;
	// 工作
	@Resource
	private IJobInfoDAO jobInfoDAO;
	// 经营请款
	@Resource
	private ISurveybusinessinfoDao surveybusinessinfoDao;

	// 存款应收款
	@Resource
	private IDepositDao depositDao;

	// 借款申请
	@Resource
	private IApplicationDao applicationDao;

	// 账户
	@Resource
	private IAccountInfoService accountInfoService;

    // 共用借款人信息
    @Resource
    private ICreditCoBorrowerService creditCoBorrowerService;

	// 客户信息
	@Resource
	private ICustomerBasicInfoDao customerBasicInfoDAO;

	// 业务单
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	// 客户
	@Resource
	private ICustomerBasicInfoService customerBasicInfoService;

	@Resource
	private IOperateLogService operateLogService;

	@Resource
	private IBorrowerApplicationService borrowerApplicationService;

	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private ICreditInvestigationDAO creditInvestigationDAO;

	@Override
	public boolean addApplication(HouseServeyVo houseServeyVo, String tempSave) throws Exception{
		// 个人申请
		long borrowerServiceAppId;
		BorrowerService borrowerService = houseServeyVo.getBorrowerService();
		
		// this.updateCreditApplicationName(borrowerService);
		// 添加 或 修改 客户基本信息
		Long customerBasicId = this.addOrUpdateCustomer(borrowerService);
		borrowerService.setCustomerBasicId(customerBasicId);
		this.updateAccount(borrowerService);
// 申请提交 标记
		boolean flag = false;
		if (borrowerService.getBorrowerServiceAppId() != null) {
			borrowerService.setFirstFlag(tempSave);
			borrowerServiceDAO.update("RL_BORROWER_SERVICE_APP.updateBorroerServiceApp", borrowerService);
			
			borrowerServiceAppId = borrowerService.getBorrowerServiceAppId();
			flag = true;
		} else {
			borrowerServiceAppId = (Long) borrowerServiceDAO.insert(borrowerService);
		}
        // 添加共借人信息
        CreditCoBorrower coBorrower =  houseServeyVo.getCreditCoBorrower();
        coBorrower.setBorrowerServiceAppId(borrowerServiceAppId);
        try{
            creditCoBorrowerService.insertOrUpdateCreditCoBorrowerInfo(coBorrower);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("更新共用借款人信息出错....");
        }
		//familymemberDao.delete("familymember.deleteByBorrowerServiceAppId", borrowerServiceAppId);
        familymemberDao.delete("familymember.deleteByBorrowerServiceAppIdExceptMate", borrowerServiceAppId);
		jobInfoDAO.deleteJobInfo(Long.valueOf(borrowerServiceAppId).intValue());
		surveybusinessinfoDao.delete("surveybusinessinfo.deleteByBorrowerServiceAppId", borrowerServiceAppId);
		depositDao.delete("RL_DEPOSIT.deleteByBorrowerServiceAppId", borrowerServiceAppId);
		applicationDao.delete("RL_APPLICATION.deleteByBSAId", borrowerServiceAppId);


		//修改信用和背景调查表中的借款人姓名name luohongjie
		CreditInvestigation creditInvestigation=new CreditInvestigation();
		creditInvestigation.setCreditapplicationId(borrowerService.getCreditapplicationId());
		creditInvestigation.setName(borrowerService.getName());

        if(coBorrower !=null && CommonUtil.isNotEmpty(coBorrower.getBorrowerreRationship()) && "2".equals(coBorrower.getBorrowerreRationship()) ){
            creditInvestigation.setSpouseName(coBorrower.getName());
        }

		List<Familymember> familymembers = houseServeyVo.getFamilymemberList();
		List<Familymember> familymembersTemp = new ArrayList<Familymember>();
		for (Familymember familymember : familymembers) {
			familymember.setBorrowerServiceAppId(borrowerServiceAppId);
            if ((familymember.getName() != null && !"".equals(familymember.getName()))
                    || (familymember.getBorrowerreRationship() != null && !"".equals(familymember
                    .getBorrowerreRationship()))
                    || (familymember.getAge() != null && !"".equals(familymember.getAge()))
                    || (familymember.getProfession() != null && !"".equals(familymember.getProfession()))
                    || (familymember.getProfessionDetail() != null && !""
                    .equals(familymember.getProfessionDetail()))
                    || (familymember.getTelphone() != null && !"".equals(familymember.getTelphone()))) {
                familymembersTemp.add(familymember);
            }
		}

		//修改信用和背景调查表中的借款人姓名name luohongjie  2014-10-13
		creditInvestigationDAO.updateCreditInvestigationName(creditInvestigation);
		// 家庭成员
		familymemberDao.batchInsertfamilymemberList(familymembersTemp);
		List<JobInfo> jobInfos = houseServeyVo.getJobInfoList();
		List<JobInfo> jobInfosTemp = new ArrayList<JobInfo>();
		for (JobInfo jobInfo : jobInfos) {
			jobInfo.setBorrowerServiceAppId(Long.valueOf(borrowerServiceAppId));
			if ((!"".equals(jobInfo.getCompany()) && jobInfo.getCompany() != null)
					|| (!"".equals(jobInfo.getWorkunitAddress()) && jobInfo.getWorkunitAddress() != null)
					|| (!"".equals(jobInfo.getYears()) && jobInfo.getYears() != null)
					|| (!"".equals(jobInfo.getPost()) && jobInfo.getPost() != null)
					|| (!"".equals(jobInfo.getIncome()) && jobInfo.getIncome() != null)) {
				jobInfosTemp.add(jobInfo);
			}
		}

		// 工作
		jobInfoDAO.bathInsertJobInfo(jobInfosTemp);

		// 经营情况
		List<Surveybusinessinfo> surveybusinessinfos = houseServeyVo.getSurveybusinessinfoList();
		List<Surveybusinessinfo> surveybusinessinfosTemp = new ArrayList<Surveybusinessinfo>();
		for (Surveybusinessinfo surveybusinessinfo : surveybusinessinfos) {
			surveybusinessinfo.setBorrowerServiceAppId(borrowerServiceAppId);
			if (surveybusinessinfo.getOperatingItemsDetail() != null
					&& !"".equals(surveybusinessinfo.getOperatingItemsDetail())
					|| (surveybusinessinfo.getOperatingItems() != null && !"".equals(surveybusinessinfo
							.getOperatingItems()))
					|| (surveybusinessinfo.getStartingDate() != null && !""
							.equals(surveybusinessinfo.getStartingDate()))
					|| (surveybusinessinfo.getState() != null && !"".equals(surveybusinessinfo.getState()))
					|| (surveybusinessinfo.getBusinessLicense() != null && !"".equals(surveybusinessinfo
							.getBusinessLicense()))
					|| (surveybusinessinfo.getTaxRegistrationCertificate() != null && !"".equals(surveybusinessinfo
							.getTaxRegistrationCertificate()))) {
				surveybusinessinfosTemp.add(surveybusinessinfo);
			}
		}
		// 经营
		surveybusinessinfoDao.batchInsert("surveybusinessinfo.insertsurveybusinessinfo", surveybusinessinfosTemp);

		// 存款情况
		List<Deposit> deposits = houseServeyVo.getDepositList();
		List<Deposit> depositsTemp = new ArrayList<Deposit>();
		for (Deposit deposit : deposits) {
			deposit.setBorrowerServiceAppId(Long.valueOf(borrowerServiceAppId));
			if ((deposit.getDepositOrganization() != null && !"".equals(deposit.getDepositOrganization()))
					|| (deposit.getDepositOrganizationDetail() != null && !"".equals(deposit
							.getDepositOrganizationDetail()))
					|| (deposit.getAddress() != null && !"".equals(deposit.getAddress()))
					|| (deposit.getTelephone() != null && !"".equals(deposit.getTelephone()))
					|| (deposit.getBalance() != null && !"".equals(deposit.getBalance()))
					|| (deposit.getProveDocument() != null && !"".equals(deposit.getProveDocument()))) {
				depositsTemp.add(deposit);
			}
		}
		// 存款
		depositDao.batchInsert("RL_DEPOSIT.insert", depositsTemp);

		// 借款计划
		List<Application> applications = houseServeyVo.getApplicationList();
		List<Application> applicationsTemp = new ArrayList<Application>();
		if (applications != null) {
			for (Application application : applications) {
				application.setBorrowerServiceAppId(Long.valueOf(borrowerServiceAppId));
				if ((application.getBorrowUse() != null && !"".equals(application.getBorrowUse()))
						|| (application.getBorrowUseDetail() != null && !"".equals(application.getBorrowUseDetail()))
						|| (application.getQuantity() != null && !"".equals(application.getQuantity()))
						|| (application.getUnitPrice() != null && !"".equals(application.getUnitPrice()))
						|| (application.getTotalAmount() != null && !"".equals(application.getTotalAmount()))
						|| (application.getProveDocument() != null && !"".equals(application.getProveDocument()))) {
					applicationsTemp.add(application);
				}
			}
		}
		// 借款计划
		applicationDao.batchInsert("RL_APPLICATION.abatorgenerated_insert", applicationsTemp);
		if (flag) {
			/******** 日志 ********/
			OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
			operateLog.setCreditapplicationId(Long.valueOf(borrowerService.getCreditapplicationId()));
			operateLog.setFunctionCode("100040");
			operateLog.setResult("申请人信息提交，申请单提交");
			operateLog.setRemark("姓名：" + borrowerService.getName() + ";身份证：" + borrowerService.getCredentialsNumber()
					+ ";申请金额：" + borrowerService.getApplyLimit() + "共借人姓名：" + coBorrower.getName() + "共借人身份证："
					+ coBorrower.getIdNumber());
			operateLogService.insert(operateLog);
		}
		return true;
	}

	@Override
	public HouseServeyVo selectApplication(Long borrowerServiceAppId) {
		HouseServeyVo houseServeyVo = new HouseServeyVo();
		try {
			BorrowerService borrowerService = borrowerServiceDAO.selectByPrimaryKey(borrowerServiceAppId);

			// 家庭
			List<Familymember> familymembers = (List<Familymember>) familymemberDao.queryList(
					"familymember.selectfamilymemberByBorrowerServiceAppId", borrowerServiceAppId);
			List<JobInfo> jobInfos = (List<JobInfo>) jobInfoDAO.queryList("JOB.selectJob", borrowerServiceAppId);
			List<Surveybusinessinfo> surveybusinessinfos = (List<Surveybusinessinfo>) surveybusinessinfoDao.queryList(
					"surveybusinessinfo.selectSurveybusinessinfo", borrowerServiceAppId);
			List<Deposit> deposits = (List<Deposit>) depositDao.queryList("RL_DEPOSIT.selectByBorrowerServiceAppId",
					borrowerServiceAppId);
			List<Application> applications = (List<Application>) applicationDao.queryList(
					"RL_APPLICATION.selectBorrowerServiceApp", borrowerServiceAppId);

			Familymember f = new Familymember();
			for (Familymember familymember : familymembers) {
				if ("2".equals(familymember.getBorrowerreRationship())) {
					f = familymember;
				}
			}
			if (f.getSeq() != null && !"".equals(f.getSeq())) {
// Familymember[] familymembers2 = new Familymember[5];
// for (Familymember familymember : familymembers) {
// if(familymember.getSeq() != null){
// familymembers2[Integer.valueOf(familymember.getSeq())-1] = familymember;
// }
// }
// familymembers = Arrays.asList(familymembers2);
// //工作
//
// JobInfo[] infos = new JobInfo[3];
// for (JobInfo jobInfo : jobInfos) {
// if(jobInfo.getSeq()!= null){
// infos[Integer.valueOf(jobInfo.getSeq())-1] = jobInfo;
// }
// }
// jobInfos = Arrays.asList(infos);
//
//
// //经营
//
// Surveybusinessinfo[] surveybusinessinfos2 = new Surveybusinessinfo[3];
// for (Surveybusinessinfo surveybusinessinfo : surveybusinessinfos) {
// if(surveybusinessinfo.getSeq()!= null){
// surveybusinessinfos2[Integer.valueOf(surveybusinessinfo.getSeq())-1] = surveybusinessinfo;
// }
// }
// surveybusinessinfos = Arrays.asList(surveybusinessinfos2);
				// 存款情况

				Deposit[] deposit2 = new Deposit[3];
				for (Deposit deposit : deposits) {
					if (deposit.getSeq() != null) {
						deposit2[Integer.valueOf(deposit.getSeq()) - 1] = deposit;
					}
				}
				deposits = Arrays.asList(deposit2);
				// 借款计划

				Application[] applications2 = new Application[3];
				for (Application application : applications) {
					if (application.getSeq() != null) {
						applications2[Integer.valueOf(application.getSeq()) - 1] = application;
					}
				}
				applications = Arrays.asList(applications2);
			}

			CreditApplication creditApplicationEntity = null;
			if (null != borrowerService) {
				creditApplicationEntity = this.creditApplicationDAO
						.selectById(borrowerService.getCreditapplicationId());
				if (null != creditApplicationEntity) {
					houseServeyVo.setCreditApplication(creditApplicationEntity);
				}
			}

			houseServeyVo.setBorrowerService(borrowerService);
			houseServeyVo.setFamilymemberList(familymembers);
			houseServeyVo.setJobInfoList(jobInfos);
			houseServeyVo.setSurveybusinessinfoList(surveybusinessinfos);
			houseServeyVo.setDepositList(deposits);
			houseServeyVo.setApplicationList(applications);
			return houseServeyVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    /**
     *  查询借款人 信贷申请表信息
     * @param borrowerServiceAppId
     * @return
     */
    @Override
    public HouseServeyVo queryApplicationVOInfo(Long borrowerServiceAppId) {
        HouseServeyVo houseServeyVo = new HouseServeyVo();
        try {
            BorrowerService borrowerService = borrowerServiceDAO.selectByPrimaryKey(borrowerServiceAppId);

            //共用借款人信息
            CreditCoBorrower creditCoBorrower =  creditCoBorrowerService.queryCreditCoBorrowerInfoByBorrowerServiceAppId(String.valueOf(borrowerServiceAppId));
            // 家庭
//            List<Familymember> familymembers = (List<Familymember>) familymemberDao.queryList(
//                    "familymember.selectfamilymemberByBorrowerServiceAppId", borrowerServiceAppId);
            List<Familymember> familymembers = (List<Familymember>) familymemberService.queryFamilymembers(borrowerServiceAppId);
            //job info
            List<JobInfo> jobInfos = (List<JobInfo>) jobInfoDAO.queryList("JOB.selectJob", borrowerServiceAppId);
            // 经营情况（包括当前和历史的经营情况）
            List<Surveybusinessinfo> surveybusinessinfos = (List<Surveybusinessinfo>) surveybusinessinfoDao.queryList(
                    "surveybusinessinfo.selectSurveybusinessinfo", borrowerServiceAppId);
            //存款和应收现金
            List<Deposit> deposits = (List<Deposit>) depositDao.queryList("RL_DEPOSIT.selectByBorrowerServiceAppId",
                    borrowerServiceAppId);
               if(deposits.size()>0){
                   Collections.reverse(deposits);
               }
            //借款申请
            List<Application> applications = (List<Application>) applicationDao.queryList(
                    "RL_APPLICATION.selectBorrowerServiceApp", borrowerServiceAppId);
               if(applications.size()>0){
                   Collections.reverse(applications);
               }
            // 信贷申请信息
            CreditApplication creditApplicationEntity = null;
            if (null != borrowerService) {
                creditApplicationEntity = this.creditApplicationDAO
                        .selectById(borrowerService.getCreditapplicationId());
                if (null != creditApplicationEntity) {
                    houseServeyVo.setCreditApplication(creditApplicationEntity);
                }
            }
            houseServeyVo.setCreditCoBorrower(creditCoBorrower);
            houseServeyVo.setBorrowerService(borrowerService);
            houseServeyVo.setFamilymemberList(familymembers);
            houseServeyVo.setJobInfoList(jobInfos);
            houseServeyVo.setSurveybusinessinfoList(surveybusinessinfos);
            houseServeyVo.setDepositList(deposits);
            houseServeyVo.setApplicationList(applications);
            return houseServeyVo;
        } catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    /**
     *  根据业务编号查询借款人身份证号 担保人身份证号 共借人身份证号 信息
     * @param businessNumber
     * @return
     */
    @Override
    public IDInfoVo queryIDInfoVo(String businessNumber) {
        IDInfoVo idInfoVo = new IDInfoVo();
        List<BorrowerService> borrowerServices =  borrowerServiceDAO.queryGuarantyListInfoByBussinessNumber(businessNumber);
        BorrowerService borrowerService = borrowerServiceDAO.queryBorrowerServiceInfoByBussinessNumber(businessNumber);
        CreditCoBorrower creditCoBorrower = borrowerServiceDAO.queryCreditCoBorrowerInfoByBussinessNumber(businessNumber);
        idInfoVo.setCb(creditCoBorrower);
        idInfoVo.setBorrowerInfo(borrowerService);
        idInfoVo.setGuarantListInfo(borrowerServices);
        return idInfoVo;
    }
    /**
     *
     *保存借款人身份证号 担保人身份证号 共借人身份证号信息
     * @param idInfoVo
     * @return
     */
    @Override
    public boolean updateIdInfo(IDInfoVo idInfoVo) {
       //借款人信息
        BorrowerService borrowerService = idInfoVo.getBorrowerInfo();
        // 担保人信息
        List<BorrowerService> borrowerServices = idInfoVo.getGuarantListInfo();
        //共借人信息
        CreditCoBorrower creditCoBorrower = idInfoVo.getCb();
        //  客户基本信息
        List<CustomerBasicInfo> customerBasicInfos  = new ArrayList<CustomerBasicInfo>();
        //共借人信息
        if(creditCoBorrower!= null)   {
            borrowerServiceDAO.updateCreditCoBorrowerIdInfo(creditCoBorrower);
        }

        //借款人信息
        if(borrowerService != null)  {
            borrowerServiceDAO.updateBorrowerServiceIdInfo(borrowerService);
            if( borrowerService.getCustomerBasicId() != null)    {
                CustomerBasicInfo customerBasicInfo = new CustomerBasicInfo();
                customerBasicInfo.setCredentialsNumber(borrowerService.getCredentialsNumber());
                customerBasicInfo.setCustomerBasicId(borrowerService.getCustomerBasicId().intValue());
                customerBasicInfos.add(customerBasicInfo);
            }
        }
        // 担保人信息
        if(borrowerServices !=null && borrowerServices.size()>0){
            borrowerServiceDAO.updateGuarantyBorrowerServiceIdInfo(borrowerServices);
            for(BorrowerService borrowerService1:borrowerServices){
                if(borrowerService1.getCustomerBasicId() != null){
                    CustomerBasicInfo customerBasicInfo = new CustomerBasicInfo();
                    customerBasicInfo.setCredentialsNumber(borrowerService1.getCredentialsNumber());
                    customerBasicInfo.setCustomerBasicId(borrowerService1.getCustomerBasicId().intValue());
                    customerBasicInfos.add(customerBasicInfo);
                }
            }
        }
        //  客户基本信息
        if(customerBasicInfos.size()>0){
            borrowerServiceDAO.updateCutomerIdListInfo(customerBasicInfos);
        }
        return  true;
    }

    @Override
	public boolean updateApplication(HouseServeyVo houseServeyVo, String firstFlag) {
		long borrowerServiceAppId;
		BorrowerService borrowerService = houseServeyVo.getBorrowerService();
		borrowerService.setFirstFlag(firstFlag);
		this.updateCreditApplicationName(borrowerService);
		this.addOrUpdateCustomer(borrowerService);
		this.updateAccount(borrowerService);
		borrowerServiceDAO.update("RL_BORROWER_SERVICE_APP.updateBorroerServiceApp", borrowerService);
		borrowerServiceAppId = borrowerService.getBorrowerServiceAppId();
		List<Familymember> familymembers = houseServeyVo.getFamilymemberList();
		// 家庭成员
		familymemberDao.batchUpdateFamilyMemberList(familymembers);
		List<JobInfo> jobInfos = houseServeyVo.getJobInfoList();
		// 工作
		jobInfoDAO.batchUpdate("JOB.updateJob", jobInfos);
		List<Surveybusinessinfo> surveybusinessinfos = houseServeyVo.getSurveybusinessinfoList();
		// 经营
		surveybusinessinfoDao.batchUpdate("surveybusinessinfo.updateSurveybusinessinfo", surveybusinessinfos);
		List<Deposit> deposits = houseServeyVo.getDepositList();
		// 存款
		depositDao.batchUpdate("RL_DEPOSIT.updateDeposit", deposits);
		List<Application> applications = houseServeyVo.getApplicationList();
		// 借款计划
		applicationDao.batchUpdate("RL_APPLICATION.updateApplication", applications);
		/******** 日志 ********/
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
		operateLog.setCreditapplicationId(Long.valueOf(borrowerService.getCreditapplicationId()));
		operateLog.setFunctionCode("100040");
		operateLog.setResult("申请人信息提交(修改)，申请单提交");
		operateLog.setRemark("姓名：" + borrowerService.getName() + ";身份证：" + borrowerService.getCredentialsNumber()
				+ ";申请金额：" + borrowerService.getApplyLimit());
		operateLogService.insert(operateLog);
		return true;
	}

	/**
	 * 借款人姓名
	 * 
	 * @param borrowerServiceApp
	 */
	public void updateCreditApplicationName(BorrowerService borrowerServiceApp) {

		CreditApplication creditApplication = new CreditApplication();
		creditApplication = creditApplicationDAO.selectById(borrowerServiceApp.getCreditapplicationId());
		if ((creditApplication.getGroupName() == null)
				|| !(creditApplication.getGroupName()).equals(borrowerServiceApp.getName())) {
			creditApplication.setGroupName(borrowerServiceApp.getName());
			creditApplicationDAO.updateCreditApplication(creditApplication);
		}

	}

	// 填写 入户申请时 判断客户基本信息是否添加
	/**
	 * @param borrowerServiceApp
	 *            信贷申请对象
	 * @return 客户基本信息id
	 */
	public long addOrUpdateCustomer(BorrowerService borrowerServiceApp) {
		CustomerBasicInfo customer = new CustomerBasicInfo();
		if (borrowerServiceApp.getCredentialsNumber() != null && !"".equals(borrowerServiceApp.getCredentialsNumber())) {
			customer.setCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
			CustomerBasicInfo customerResult = customerBasicInfoDAO.selectByNumber(borrowerServiceApp
					.getCredentialsNumber());
			// 如果不存在则 添加客户基本信息
			if (customerResult == null) {
				if (borrowerServiceApp.getVillageId() != null)
					customer.setVallageId(borrowerServiceApp.getVillageId().intValue());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getNationality()))
					customer.setNational(borrowerServiceApp.getNationality());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getMaritalStatus()))
					customer.setMaritalStatus(borrowerServiceApp.getMaritalStatus());
				customer.setCredentialsType("1");
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getName()))
					customer.setName(borrowerServiceApp.getName());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getFormer()))
					customer.setFormer(borrowerServiceApp.getFormer());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getGender()))
					customer.setGender(borrowerServiceApp.getGender());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getHourseholdAddress()))
					customer.setResidenceAddress(borrowerServiceApp.getHourseholdAddress());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getHomeAddress()))
					customer.setPresentAddress(borrowerServiceApp.getHomeAddress());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getMobilephone()))
					customer.setMobilephone(borrowerServiceApp.getMobilephone());
				customer.setOperateName(SpringSecurityUtils.getCurrentUser().getName_zh());
				customer.setOperateId(Integer.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
				customer.setCustomerType("0");
				return customerBasicInfoDAO.insert(customer);

			} else {
				if (borrowerServiceApp.getVillageId() != null)
					customerResult.setVallageId(borrowerServiceApp.getVillageId().intValue());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getNationality()))
					customerResult.setNational(borrowerServiceApp.getNationality());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getMaritalStatus()))
					customerResult.setMaritalStatus(borrowerServiceApp.getMaritalStatus());
				customerResult.setCredentialsType("1");
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getName()))
					customerResult.setName(borrowerServiceApp.getName());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getFormer()))
					customerResult.setFormer(borrowerServiceApp.getFormer());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getGender()))
					customerResult.setGender(borrowerServiceApp.getGender());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getHourseholdAddress()))
					customerResult.setResidenceAddress(borrowerServiceApp.getHourseholdAddress());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getHomeAddress()))
					customerResult.setPresentAddress(borrowerServiceApp.getHomeAddress());
				if (CommonUtil.isNotEmpty(borrowerServiceApp.getMobilephone()))
					customerResult.setMobilephone(borrowerServiceApp.getMobilephone());
				customerResult.setCustomerType("0");
				customerResult.setOperateName(SpringSecurityUtils.getCurrentUser().getName_zh());
				customerResult.setOperateId(Integer.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
				customerBasicInfoService.updateCustomerBasicInfo(customerResult);
				return customerResult.getCustomerBasicId();
			}
		}
		return 0;
	}

	/**
	 * 账户信息
	 * 
	 * @param borrowerServiceApp
	 */
	public void updateAccount(BorrowerService borrowerServiceApp) {

		// 修改付款账户账户
		AccountInfo accountInfo = new AccountInfo();
		accountInfo = accountInfoService.selectByCrediApplicationId(borrowerServiceApp.getCreditapplicationId());
		if (accountInfo != null) {
			accountInfo.setBorrowerName(borrowerServiceApp.getName());
			accountInfo.setBorrowerCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
			accountInfoService.updateAccountInfo(accountInfo);
		}

		// 修改付款账户账户
		AccountInfo accountInfoReturn = new AccountInfo();
		accountInfoReturn = accountInfoService.selectReturnAccount(borrowerServiceApp.getCreditapplicationId());
		if (accountInfoReturn != null) {
			accountInfoReturn.setBorrowerName(borrowerServiceApp.getName());
			accountInfoReturn.setBorrowerCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
			accountInfoService.updateAccountInfo(accountInfoReturn);
		}
	}

	@Override
	public BorrowerService selectByPrimaryKey(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return borrowerServiceDAO.selectByPrimaryKey(borrowerServiceAppId);
	}

	@Override
	public boolean addApplicationIncludeRevolvingAndDiscount(HouseServeyVo houseServeyVo, String doSaveStatus) throws Exception{
		borrowerApplicationService.addApplication(houseServeyVo, doSaveStatus);
		creditApplicationService
				.updateRevolving(houseServeyVo.getBorrowerService().getCreditapplicationId().intValue());
		return true;
	}

    /**
     * 是否试点营业部
     * @param depId
     * @return
     */
    @Override
    public boolean isSpecDep(Long depId) {
        return borrowerServiceDAO.isSpecDep(depId);
    }
}

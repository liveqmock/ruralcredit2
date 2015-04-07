package com.creditease.rc.service.impl;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.*;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * 
 * @author zhangman
 * 
 */
@Service
public class BorrowerServiceAppService implements IBorrowerServiceAppService {
    @Resource
    private SmpWSUtil smpWSUtil;
    @Resource
    private ICreditCoBorrowerService creditCoBorrowerService;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;
	@Resource
	private ICustomerBasicInfoService customerBasicInfoService;
	@Resource
	private ICustomerBasicInfoDao customerBasicInfoDAO;
	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private IBorrowerServiceDAO borrowerServiceDAO;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	@Resource
	private ICashStreamService cashStreamService;
	// 家庭
	@Resource
	private IFamilymemberDao familymemberDao;
	// 工作
	@Resource
	private IJobInfoDAO jobInfoDAO;
	// 经营请款
	@Resource
	private ISurveybusinessinfoDao surveybusinessinfoDao;

	// 存款应收款
	@Resource
	private IDepositDao depositDao;
	@Resource
	private IBorrowerApplicationService borrowerApplicationService;

	@Override
	public int addBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
		BorrowerServiceApp borrowerService = borrowerServiceAppDAO
				.selectBorrowerLeaderByCreditApplicationId(borrowerServiceApp.getCreditapplicationId());
// synchronized 关键字
// System.out.println("---测试---borrowerService="+borrowerService);
// try {
// Thread.currentThread().sleep(20000);
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
		// 判断客户基本信息是否存在表单中的 个人资料信息
		if (borrowerServiceApp.getLivingRent() == null || "".equals(borrowerServiceApp.getLivingRent())) {
			borrowerServiceApp.setLivingDate(null);
		}
		// 如果借款人存在
// if (borrowerServiceApp.getBorrowerServiceAppId() != null
// && !"".equals(borrowerServiceApp.getBorrowerServiceAppId())) {
// this.updateAccount(borrowerServiceApp);
// // 添加或修改借款人
// int customerBasicId = this.addOrUpdateCustomer(borrowerServiceApp, Constants.CUSTOMER_TYPE1);
// borrowerServiceApp.setCustomerBasicId(customerBasicId);
// int count = borrowerServiceAppDAO.updateBorrowerServiceApp(borrowerServiceApp);
//
// CreditApplication creditApplication = new CreditApplication();
// creditApplication = creditApplicationDAO.selectById(Long.valueOf(borrowerServiceApp
// .getCreditapplicationId()));
// if ((creditApplication.getGroupName() == null)
// || !(creditApplication.getGroupName()).equals(borrowerServiceApp.getName())) {
// creditApplication.setGroupName(borrowerServiceApp.getName());
// creditApplicationDAO.updateCreditApplication(creditApplication);
// }
//
// if (count > 0) {
// return borrowerServiceApp.getBorrowerServiceAppId();
// } else {
// return 0;
// }
// } else {
		if (borrowerService != null) {
			return -1;
		} else {
			this.updateAccount(borrowerServiceApp);
			// 如果客户id为空添加或修改 借款人
			if (borrowerServiceApp.getCustomerBasicId() == null) {

				// 查询 之前的借款人 申请单的相关信息 （配偶，家庭，工作，经营，存款应收现金，借款申请）
				int customerBasicId = this.addOrUpdateCustomer(borrowerServiceApp, Constants.CUSTOMER_TYPE1);
				borrowerServiceApp.setCustomerBasicId(customerBasicId);
			}
			return borrowerServiceAppDAO.addBorrowerServiceApp(borrowerServiceApp);
		}
// }
	}

	@Override
	public List<BorrowerServiceApp> selectBorrowerServiceAppList(int creditapplicationId) {
		return borrowerServiceAppDAO.selectBorrowerServiceAppList(creditapplicationId);
	}

	@Override
	public BorrowerServiceApp selectByBorrowerServiceAppId(int borrowerServiceAppId) {
		return borrowerServiceAppDAO.selectByBorrowerServiceAppId(borrowerServiceAppId);
	}

	@Override
	public BorrowerServiceApp selectByCustomerId(int customerBasicId) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO.selectByCustomerId(customerBasicId);
	}

	@Override
	public List<BorrowerInfoVo> selectBorrowSerivceApp(int creditapplicationId) {
		return borrowerServiceAppDAO.selectBorrowerInfoVoList(creditapplicationId);
	}

	@Override
	public void choseLeader(Integer borrowerServiceAppId, Integer creditApplicationId) {
		List<BorrowerServiceApp> borrowServiceAppList = borrowerServiceAppDAO.selectBorrowerServiceAppList(Integer
				.valueOf(creditApplicationId));
		for (BorrowerServiceApp borrowerServiceApp : borrowServiceAppList) {
			if (borrowerServiceAppId.equals(String.valueOf(borrowerServiceApp.getBorrowerServiceAppId()))) {
				// 标记组长
				borrowerServiceApp.setLeader("1");
				CreditApplication creditApplication = new CreditApplication();
				creditApplication.setCreditapplicationId(Integer.valueOf(creditApplicationId));
				creditApplication = creditApplicationDAO.selectCreditApplicationById(creditApplication);
				// 为小组添加组长
				creditApplication.setGroupName(borrowerServiceApp.getName());
				creditApplicationDAO.updateCreditApplication(creditApplication);
			} else {
				borrowerServiceApp.setLeader("0");
			}
			borrowerServiceAppDAO.updateBorrowerServiceApp(borrowerServiceApp);
		}
// return selectBorrowSerivceApp(Integer.valueOf(creditApplicationId));
	}

	// 填写申请时 判断客户基本信息是否添加
	/**
	 * @param borrowerServiceApp 信贷申请对象
	 * @param type
	 * @return 客户基本信息id
	 */
	@Override
	public int addOrUpdateCustomer(BorrowerServiceApp borrowerServiceApp, String type) {
		CustomerBasicInfo customer = new CustomerBasicInfo();
		if (borrowerServiceApp.getCredentialsNumber() != null && !"".equals(borrowerServiceApp.getCredentialsNumber())) {
			customer.setCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
			CustomerBasicInfo customerResult = customerBasicInfoDAO.selectByNumber(customer.getCredentialsNumber());
			// 如果不存在则 添加客户基本信息
			if (customerResult == null) {
				customer.setCredentialsType("1");
				customer.setName(borrowerServiceApp.getName());
				customer.setFormer(borrowerServiceApp.getFormer());
				customer.setGender(borrowerServiceApp.getGender());
				customer.setResidenceAddress(borrowerServiceApp.getHourseholdAddress());
				customer.setPresentAddress(borrowerServiceApp.getHomeAddress());
				customer.setNational(borrowerServiceApp.getNational());
				customer.setMaritalStatus(borrowerServiceApp.getMaritalStatus());
				customer.setMobilephone(borrowerServiceApp.getMobilephone());
				customer.setVallageId(borrowerServiceApp.getVillageId());
				customer.setCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
				customer.setOperateName(SpringSecurityUtils.getCurrentUser().getName_zh());
				customer.setOperateId(Integer.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
				if (type.equals(Constants.CUSTOMER_TYPE1)) {
					customer.setCustomerType(type);
				}
				if (type.equals(Constants.CUSTOMER_TYPE2)) {
					customer.setGuaranor("0");
				}
				return customerBasicInfoDAO.insert(customer);

			} else {
				customerResult.setCredentialsType("1");
				customerResult.setName(borrowerServiceApp.getName());
				customerResult.setFormer(borrowerServiceApp.getFormer());
				customerResult.setGender(borrowerServiceApp.getGender());
				customerResult.setResidenceAddress(borrowerServiceApp.getHourseholdAddress());
				customerResult.setPresentAddress(borrowerServiceApp.getHomeAddress());
				customerResult.setNational(borrowerServiceApp.getNational());
				customerResult.setMaritalStatus(borrowerServiceApp.getMaritalStatus());
				customerResult.setMobilephone(borrowerServiceApp.getMobilephone());
				customerResult.setVallageId(borrowerServiceApp.getVillageId());
				customerResult.setCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
				customerResult.setOperateName(SpringSecurityUtils.getCurrentUser().getName_zh());
				customerResult.setOperateId(Integer.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
				if (type.equals(Constants.CUSTOMER_TYPE1)) {
					customerResult.setCustomerType(type);
				}
				if (type.equals(Constants.CUSTOMER_TYPE2)) {
					customerResult.setGuaranor("0");
				}
				customerBasicInfoService.updateCustomerBasicInfo(customerResult);
				return customerResult.getCustomerBasicId();
			}
		}
		return 0;
	}

	@Override
	public boolean updateValidStateOfBorrowerServiceApp(Integer borrowerServiceAppId) {
		BorrowerServiceApp borrowerServiceApp = borrowerServiceAppDAO
				.selectByBorrowerServiceAppId(borrowerServiceAppId);
		if (borrowerServiceApp != null) {
			borrowerServiceApp.setBorrowerServiceAppId(borrowerServiceAppId);
			borrowerServiceApp.setValidState("0");
			borrowerServiceApp.setLeader("");
			CreditApplication creditApplication = new CreditApplication();
			creditApplication.setCreditapplicationId(borrowerServiceApp.getCreditapplicationId());
			// 删除借款人姓名
			creditApplicationDAO.updateName(creditApplication);
			cashStreamService.delAllByBelongId(borrowerServiceApp.getCreditapplicationId());
			borrowerServiceAppDAO.update("BORROWERSERVICE.updateValidState", borrowerServiceApp);
			return true;
		} else {
			return true;
		}
	}

	@Override
	public String selectByNumber(String credentialsNumber) {
		return borrowerServiceAppDAO.selectByNumber(credentialsNumber);
	}

	@Override
	public List<BorrowerServiceApp> selectByBorrowerInfoVo(BorrowerInfoVo borrowerInfoVo) {
		// TODO Auto-generated method stub
		List<BorrowerServiceApp> borrowerInfoVos = borrowerServiceAppDAO.selectByBorrowerInfoVo(borrowerInfoVo);
		for (BorrowerServiceApp bsa : borrowerInfoVos) {
			try {
				DESPlus desPlus = new DESPlus();
				String desId = desPlus.encrypt(String.valueOf(bsa.getBorrowerServiceAppId()));
				bsa.setBorrowerServiceAppDESId(desId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
// // 1-借款人
// if (borrowerInfoVo.getLeader() == "1") {
// // 设置紧急联系人信息
// for (BorrowerInfoVo borrowerInfoVoTemp : borrowerInfoVos) {
// Contact contact = new Contact();
// contact.setBorrowerServiceAppId(borrowerInfoVoTemp.getBorrowerServiceAppId());
// // 3-紧急联系人
// contact.setContactType("3");
// Contact contactTemp = contactService.selectOneContact(contact);
// borrowerInfoVoTemp.setEmergencyContact(contactTemp.getName());
// borrowerInfoVoTemp.setEmergencyContactTel(contactTemp.getTelphone());
// try {
// DESPlus desPlus = new DESPlus();
// String desId = desPlus.encrypt(String.valueOf(borrowerInfoVoTemp.getBorrowerServiceAppId()));
// borrowerInfoVoTemp.setBorrowerServiceAppDESId(desId);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
		return borrowerInfoVos;
	}

	@Override
	public List<BorrowerServiceVo> quertyGuaranorList(Long creditApplicationId) {
		// TODO Auto-generated method stub
		return (List<BorrowerServiceVo>) borrowerServiceAppDAO.queryList("RL_BORROWER_SERVICE_APP.quertyGuaranorList",
				creditApplicationId);
	}

	/**
	 * @author 郝强
	 * @param borrowerService
	 * @return boolean
	 */
	@Override
	public boolean addNewGuaranor(BorrowerService borrowerService) {
		// TODO Auto-generated method stub
		borrowerService.setFirstFlag("0");
		borrowerService.setSecondFlag("0");
		borrowerService.setThirdFlag("0");
		borrowerService.setValidState("1");
		borrowerService.setLeader("0");

		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		borrowerServiceApp.setName(borrowerService.getName());
		borrowerServiceApp.setFormer(borrowerService.getFormer());
		borrowerServiceApp.setGender(borrowerService.getGender());
		borrowerServiceApp.setHomeAddress(borrowerService.getHomeAddress());
		borrowerServiceApp.setResidenceAddress(borrowerService.getResidenceAddress());
		borrowerServiceApp.setMobilephone(borrowerService.getMobilephone());
		borrowerServiceApp.setCredentialsNumber(borrowerService.getCredentialsNumber());
		borrowerServiceApp.setNational(borrowerService.getNationality());
		borrowerServiceApp.setMaritalStatus(borrowerService.getMaritalStatus());

		if (borrowerService.getVillageId() != null && !"".equals(borrowerService.getVillageId())) {
			borrowerServiceApp.setVillageId(borrowerService.getVillageId().intValue());
		}
		if (borrowerService.getCustomerBasicId() != null && !"".equals(borrowerService.getCustomerBasicId())) {
			borrowerServiceApp.setCustomerBasicId(borrowerService.getCustomerBasicId().intValue());
		}

		Integer getCustomerBasicId = borrowerServiceAppService.addOrUpdateCustomer(borrowerServiceApp,
				Constants.CUSTOMER_TYPE2);
		borrowerService.setCustomerBasicId(getCustomerBasicId.longValue());
		borrowerServiceDAO.insert("RL_BORROWER_SERVICE_APP.abatorgenerated_insert", borrowerService);
		return true;

	}

	@Override
	public Familymember selectPartner(Familymember familymember) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO.selectPartner(familymember);
	}

	/**
	 * 修改账户
	 * 
	 * @param borrowerServiceApp 借款人
	 */
	public void updateAccount(BorrowerServiceApp borrowerServiceApp) {
		// 修改付款账户账户
		AccountInfo accountInfo = new AccountInfo();
		accountInfo = accountInfoService.selectByCrediApplicationId(Long.valueOf(borrowerServiceApp
				.getCreditapplicationId()));
		if (accountInfo != null) {
			accountInfo.setBorrowerName(borrowerServiceApp.getName());
			accountInfo.setBorrowerCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
			accountInfoService.updateAccountInfo(accountInfo);
		}

		// 修改付款账户账户
		AccountInfo accountInfoReturn = new AccountInfo();
		accountInfoReturn = accountInfoService.selectReturnAccount(Long.valueOf(borrowerServiceApp
				.getCreditapplicationId()));
		if (accountInfoReturn != null) {
			accountInfoReturn.setBorrowerName(borrowerServiceApp.getName());
			accountInfoReturn.setBorrowerCredentialsNumber(borrowerServiceApp.getCredentialsNumber());
			accountInfoService.updateAccountInfo(accountInfoReturn);
		}
	}

	/**
	 * 导出借款人 (配偶)详细信息
	 * 
	 * @return list
	 */
	@Override
	public List<HashMap> exportExcelBorrower() {
		// TODO Auto-generated method stub
		return (List<HashMap>) borrowerServiceAppDAO.queryList("BORROWERSERVICE.selectExportExcelBorrower");
	}

	// 查询 客户信息导出列表
	@Override
	public Pagination exportCustomerInformationtList(Map<String, Object> map, Pagination pagination) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO.exportCustomerInformationtList(map, pagination);
	}

	// 导出 客户信息导出列表
	@Override
	public List<Map> exportCustomerInformationt(Map map) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO.exportCustomerInformationt(map);
	}

	@Override
	public List<Map> select(BorrowerService borrowerService) {
		// TODO Auto-generated method stub
		return borrowerServiceDAO.select(borrowerService);
	}

	@Override
	public List<BorrowerServiceApp> selectByBorrowerInfo(BorrowerInfoVo borrowerInfoVo) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO.selectByBorrowerInfoVo(borrowerInfoVo);
	}

	@Override
	public Message updateborrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
		// TODO Auto-generated method stub
		Message message = new Message();
		int returnResult = borrowerServiceAppDAO.updateBorrowerServiceAppForSistem(borrowerServiceApp);
		if (returnResult > 0) {
			message.setSuccess(true);
			message.setMsg("更新了" + returnResult + "条记录");
		} else {
			message.setSuccess(false);
			message.setMsg("更新了" + returnResult + "条记录");
		}
		return message;
	}

	@Override
	public Message addNewBorrower(String credentialsNumber, String creditapplicationId,
			BorrowerServiceApp borrowerServiceApp, Long borrowerServiceAppId) throws Exception{
		Message message = new Message();
		BorrowerServiceApp borrowerService = borrowerServiceAppDAO
				.selectBorrowerLeaderByCreditApplicationId(borrowerServiceApp.getCreditapplicationId());
		// 判断客户基本信息是否存在表单中的 个人资料信息
		if (borrowerServiceApp.getLivingRent() == null || "".equals(borrowerServiceApp.getLivingRent())) {
			borrowerServiceApp.setLivingDate(null);
		}
		if (borrowerService != null) {
			message.setMsg("借款人已经存在，将为您刷新");
			message.setSuccess(false);
		} else {
			this.updateAccount(borrowerServiceApp);
			// 如果客户id为空添加或修改 借款人
			if (borrowerServiceApp.getCustomerBasicId() == null) {
				int customerBasicId = this.addOrUpdateCustomer(borrowerServiceApp, Constants.CUSTOMER_TYPE1);
				borrowerServiceApp.setCustomerBasicId(customerBasicId);
			}

// 查询历史借款人信息 和 历史申请单 如果有则存入当前申请单信息中
			if (borrowerServiceAppId != null) {

// 配偶信息
				List<Familymember> familymemberList = new ArrayList<Familymember>();
				List<JobInfo> jobInfoList = new ArrayList<JobInfo>();
				List<Surveybusinessinfo> surveybusinessinfoList = new ArrayList<Surveybusinessinfo>();
				List<Deposit> depositList = new ArrayList<Deposit>();

				BorrowerService borrowerService2 = borrowerServiceDAO.selectByPrimaryKey(borrowerServiceAppId);
				if ("1".equals(borrowerService2.getLeader())) {
					familymemberList = familymemberDao.querySelectFamilymember(borrowerServiceAppId);
					// 工作信息
					try {
						jobInfoList = jobInfoDAO.selectJobInfo(borrowerServiceAppId.intValue());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 经营情况
					surveybusinessinfoList = surveybusinessinfoDao.selectByBorrowerServiceAppId(borrowerServiceAppId);
					depositList = depositDao.selectByBorrowerServiceAppId(borrowerServiceAppId);
				}

				borrowerService2.setBorrowerServiceAppId(null);
				borrowerService2.setApplyDate(null); // 去掉 申请金额
				borrowerService2.setFirstFlag(""); // 申请资料 未保存的状态
				borrowerService2.setSecondFlag(""); // 担保人 未保存的状态
				borrowerService2.setThirdFlag(""); // 未保存的状态
				borrowerService2.setApplyLimit(null); // 申请金额
				borrowerService2.setName(borrowerServiceApp.getName());
				borrowerService2.setMobilephone(borrowerServiceApp.getMobilephone());
				borrowerService2.setCreditapplicationId(borrowerServiceApp.getCreditapplicationId().longValue()); // 设置 关联的申请编号
				borrowerService2.setLeader("1");
				borrowerService2.setCustomerBasicId(borrowerServiceApp.getCustomerBasicId().longValue());

				HouseServeyVo houseServeyVo = new HouseServeyVo();
				houseServeyVo.setBorrowerService(borrowerService2);
				houseServeyVo.setFamilymemberList(familymemberList);
				houseServeyVo.setJobInfoList(jobInfoList);
				houseServeyVo.setDepositList(depositList);
				houseServeyVo.setSurveybusinessinfoList(surveybusinessinfoList);
				boolean b = borrowerApplicationService.addApplication(houseServeyVo, Constants.DO_SAVE_STATUS);
				if (b) {
					message.setSuccess(true);
				} else {
					message.setSuccess(false);
					message.setMsg("保存失败");
				}
			} else {
				borrowerServiceApp.setLeader("1");
				int r = borrowerServiceAppDAO.addBorrowerServiceApp(borrowerServiceApp);
				if (r > 0) {
					message.setSuccess(true);
				} else {
					message.setSuccess(false);
					message.setMsg("保存失败");
				}
			}
		}
		return message;
	}

    @Override
    public Message addNewBorrower(BorrowerServiceApp borrowerServiceApp, BorrowerInfo borrowerInfo)throws Exception{
        Message message = new Message();
        // 判断客户基本信息是否存在表单中的 个人资料信息
        if (borrowerServiceApp.getLivingRent() == null || "".equals(borrowerServiceApp.getLivingRent())) {
            borrowerServiceApp.setLivingDate(null);
        }
        //更新账户信息
        this.updateAccount(borrowerServiceApp);
        //更新借款人基本信息信息
        int customerBasicId = this.addOrUpdateCustomer(borrowerServiceApp, Constants.CUSTOMER_TYPE1);
        borrowerServiceApp.setCustomerBasicId(customerBasicId);
        //历史借款人      更新历史申请单到新申请单上
        if("true".equals(borrowerInfo.getOldUser())) {
            //添加历史申请单信息到新的申请单上
              return this.mergerHistoryCreditAppInfoToNewCreditApp(borrowerServiceApp,borrowerInfo);
        }else{
            borrowerServiceApp.setLeader("1");
            //生成新的申请单
            int r = borrowerServiceAppDAO.addBorrowerServiceApp(borrowerServiceApp);
            borrowerInfo.setBorrowerServiceAppId(String.valueOf(r));
            //此处添加新增借款人配偶添加的工作未完成工作 r         返回的是borrowerServiceAppId
            addMateInfo(borrowerInfo);
            //  添加共借人信息
            addCreditCoBorrowerInfo(borrowerInfo);
            if (r > 0) {
                message.setSuccess(true);
            } else {
                message.setSuccess(false);
                message.setMsg("保存失败");
            }
        }
        return message;
    }

    private void addMateInfo(BorrowerInfo borrowerInfo){
        Familymember familymember = new Familymember();
        familymember.setBorrowerreRationship("2");
        familymember.setSeq("1");
        int  currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
        familymember.setAge( currentYear-Integer.valueOf(borrowerInfo.getMateIdNumber().substring(6,10)));
        familymember.setIdNumber(borrowerInfo.getMateIdNumber());
        familymember.setBorrowerServiceAppId(Long.valueOf(borrowerInfo.getBorrowerServiceAppId()));
        familymemberDao.insert("familymember.insertfamilymember",familymember);
    }

    /**
     * 添加共借人信息
     * @param borrowerInfo
     * @throws Exception
     */
    private void addCreditCoBorrowerInfo(BorrowerInfo borrowerInfo) throws Exception{
        CreditCoBorrower  creditCoBorrower = new CreditCoBorrower();
        creditCoBorrower.setBorrowerreRationship("2");
        creditCoBorrower.setSeq("1");
        int  currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
        creditCoBorrower.setAge( currentYear-Integer.valueOf(borrowerInfo.getMateIdNumber().substring(6,10)));
        creditCoBorrower.setIdNumber(borrowerInfo.getMateIdNumber());
        creditCoBorrower.setBorrowerServiceAppId(Long.valueOf(borrowerInfo.getBorrowerServiceAppId()));
        creditCoBorrowerService.insertOrUpdateCreditCoBorrowerInfo(creditCoBorrower);
    }
   /**
	 * 查询与该电话有关的作为担保人的借款结清次数
	 * 
	 * @author luohongjie
	 */
	@Override
	public List<CreditApplicationSearch> selectBorrowerServiceByTel(String telphone) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO.selectBorrowerServiceByTel(telphone);
	}

	/**
	 * 查询与该电话有关的借款人的借款结清次数
	 * 
	 * @author luohongjie
	 */
	@Override
	public List<CreditApplicationSearch> selectBorrowerAuditListByTel(String telPhone) {
		return borrowerServiceAppDAO.selectBorrowerAuditListByTel(telPhone);
	}

	@Override
	public Message checkBorrowerSave(Long creditapplicationId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		List<BorrowerServiceApp> borrowerServiceApps = borrowerServiceAppDAO.checkBorrowerSave(creditapplicationId);
		if (CommonUtil.isEmpty(borrowerServiceApps)) {
			message.setSuccess(false);
			message.setMsg("借款人申请信息未提交，请先提交借款人申请！");
			return message;
		} else {
			message.setSuccess(true);
			return message;
		}

	}
   private List<Familymember> getRightFamilyMembers(BorrowerService borrowerService,BorrowerInfo borrowerInfo){
       List<Familymember> changedFamilyMembers = new ArrayList<Familymember>();
       List<Familymember> familymemberList = new ArrayList<Familymember>();
       familymemberList = familymemberDao.querySelectFamilymember(borrowerService.getBorrowerServiceAppId());
      /* String wifeChanged = "false";
       //处理家庭成员中配偶更换的情况
       for(Familymember familymember:familymemberList){
           if("2".equals(familymember.getBorrowerreRationship()) && !borrowerInfo.getMateIdNumber().equals(familymember.getIdNumber())){
               wifeChanged = "true";
               Familymember wife = new Familymember();
               wife.setBorrowerreRationship("2");
               wife.setSeq("1");
               wife.setIdNumber(borrowerInfo.getMateIdNumber());
               int  currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
               familymember.setAge( currentYear-Integer.valueOf(borrowerInfo.getMateIdNumber().substring(6,10)));
               wife.setBorrowerServiceAppId(Long.valueOf(borrowerInfo.getBorrowerServiceAppId()));
               changedFamilyMembers.add(wife);
               break;
           }
       }
       if(wifeChanged.equals("true")){
          return changedFamilyMembers;
       }*/
       return familymemberList;
   }



   private Message mergerHistoryCreditAppInfoToNewCreditApp(BorrowerServiceApp borrowerServiceApp, BorrowerInfo borrowerInfo)throws Exception{
       Message msg = new Message();
       List<Familymember> familymemberList = new ArrayList<Familymember>();
       List<JobInfo> jobInfoList = new ArrayList<JobInfo>();
       List<Surveybusinessinfo> surveybusinessinfoList = new ArrayList<Surveybusinessinfo>();
       List<Deposit> depositList = new ArrayList<Deposit>();
       BorrowerService borrowerService = borrowerServiceDAO.selectByPrimaryKey(Long.valueOf(borrowerInfo.getOldBorrowerServiceAppId()));
       //家庭成员
       familymemberList = getRightFamilyMembers(borrowerService,borrowerInfo);

       //  查询历史 共借人信息
       CreditCoBorrower creditCoBorrowerOld = new CreditCoBorrower();
       CreditCoBorrower creditCoBorrower = new CreditCoBorrower();
       creditCoBorrowerOld = creditCoBorrowerService.queryCreditCoBorrowerInfoByBorrowerServiceAppId(borrowerService.getBorrowerServiceAppId().toString());
       //比较共借人配偶是否更换
       if(creditCoBorrowerOld == null  || (creditCoBorrowerOld != null && creditCoBorrowerOld.getIdNumber() != borrowerInfo.getMateIdNumber()) )  {
           creditCoBorrower.setBorrowerreRationship("2");
           creditCoBorrower.setSeq("1");
           int  currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
           creditCoBorrower.setAge( currentYear-Integer.valueOf(borrowerInfo.getMateIdNumber().substring(6,10)));
           creditCoBorrower.setIdNumber(borrowerInfo.getMateIdNumber());
           creditCoBorrower.setBorrowerServiceAppId(Long.valueOf(borrowerService.getBorrowerServiceAppId()));
       }else{
           creditCoBorrower =   creditCoBorrowerOld;
       }

       //借款人工作情况信息
       jobInfoList = jobInfoDAO.selectJobInfo(borrowerService.getBorrowerServiceAppId().intValue());
       //借款人 经营情况
       surveybusinessinfoList = surveybusinessinfoDao.selectByBorrowerServiceAppId(borrowerService.getBorrowerServiceAppId());
       if("1".equals(borrowerService.getLeader())){ //已存借款人
          //借款人存款与应收现金情况
           depositList = depositDao.selectByBorrowerServiceAppId(borrowerService.getBorrowerServiceAppId());
       }
       borrowerService.setBorrowerServiceAppId(null);
       borrowerService.setApplyDate(null); // 去掉 申请金额
       borrowerService.setFirstFlag(""); // 申请资料 未保存的状态
       borrowerService.setSecondFlag(""); // 担保人 未保存的状态
       borrowerService.setThirdFlag(""); // 未保存的状态
       borrowerService.setApplyLimit(null); // 申请金额
       borrowerService.setName(borrowerServiceApp.getName());
       borrowerService.setMobilephone(borrowerServiceApp.getMobilephone());
       borrowerService.setCreditapplicationId(borrowerServiceApp.getCreditapplicationId().longValue()); // 设置 关联的申请编号
       borrowerService.setLeader("1");
       borrowerService.setCustomerBasicId(borrowerServiceApp.getCustomerBasicId().longValue());

       HouseServeyVo houseServeyVo = new HouseServeyVo();
       houseServeyVo.setBorrowerService(borrowerService);
       houseServeyVo.setFamilymemberList(familymemberList);
       houseServeyVo.setJobInfoList(jobInfoList);
       houseServeyVo.setCreditCoBorrower(creditCoBorrower);
       if("1".equals(borrowerService.getLeader()))  {
           houseServeyVo.setDepositList(depositList);
       }
       houseServeyVo.setSurveybusinessinfoList(surveybusinessinfoList);
       boolean b = borrowerApplicationService.addApplication(houseServeyVo, Constants.DO_SAVE_STATUS);
       if (b) {
           msg.setSuccess(true);
       } else {
           msg.setSuccess(false);
           msg.setMsg("保存失败");
       }
       return msg;
   }

@Override
public List<BorrowerServiceAppVo2> queryBorrowerInfo(Long creditapplicationId) {
	return borrowerServiceAppDAO.queryBorrowerInfo(creditapplicationId);
}
}

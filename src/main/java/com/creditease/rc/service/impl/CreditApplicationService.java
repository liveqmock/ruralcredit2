package com.creditease.rc.service.impl;

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.app.cm.CreditCMClientWS;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.common.BusinessSerialNumber;
import com.creditease.rc.common.Constants;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.dao.*;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * @author zhangman
 * 
 */
@Service
public class CreditApplicationService implements ICreditApplicationService {
    /**
     *    //根据信贷申请id查询借款人身份证号 等信息       编辑回显使用
     * @param creditApplicationId
     * @return
     */
    @Override
    public CreditApplication getIndentityInfoByCreditApplicationId(Integer creditApplicationId) {
        return creditApplicationDAO.getIndentityInfoByCreditApplicationId(creditApplicationId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Resource
	private ICustomerConsultPoolService customerConsultPoolService; //咨询池
	
	@Resource
	private ICustomerConsultPoolLogService customerConsultPoolLogService;//咨询池日志

	@Resource
	private ICreditApplicationDAO creditApplicationDAO;

	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;
	@Resource
	private IRlApplyAuditTableService rlApplyAuditTableService;
	@Resource
	private CreditCMClientWS cmClientWSPortType;
	@Resource
	private ICustomerConsultDAO customerConsultDAO;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private IDataDictionaryDao dataDictionaryDao;
	@Resource
	private BusinessSerialNumber serialNumber;
	@Resource
	private ICashStreamService cashStreamService;
	@Resource
	private ICreditInvestigationService creditInvestigationService;
	@Resource
	private IAmountConfirmService amountConfirmService;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IDiscountConfigurationService discountConfigurationService;

	@Resource
	private ICustomerConsultPoolDao customerConsultPoolDao;
    @Resource
    private ICustomerBasicInfoService customerService;
	// 循环贷
	@Resource
	private ARevolvingCreditJudgeTemplateMethod aRevolvingCreditJudgeTemplateMethod;

	private Logger logger = Logger.getLogger(CreditApplicationService.class);

	@Override
	public int addCreditApplication(CreditApplication creditApplication) {
		return creditApplicationDAO.addCreditApplication(creditApplication);
	}

	@Override
	public Message updateCreditApplicationForAccount(CreditApplication creditApplication) {
		creditApplicationDAO.updateCreditApplication(creditApplication);
		Message message = new Message();
		message.setSuccess(true);
		return message;
	}

	/**
	 * 关单注销（咨询）
	 * 
	 * @param creditApplication
	 * @return 消息
	 */
	@Override
	public Message updateClose(CreditApplication creditApplication) {
		Message message = new Message();
		message.setSuccess(false);
		if (creditApplication.getCreditapplicationId() != null) {
			CreditApplication creditApplicationSelect = creditApplicationDAO.selectById(Long.valueOf(creditApplication
					.getCreditapplicationId()));
			if (creditApplicationSelect != null) {
				if ((Constants.STATE_0.equals(creditApplicationSelect.getAuditStatus())
						|| Constants.STATE_1.equals(creditApplicationSelect.getAuditStatus())
						|| Constants.STATE_24.equals(creditApplicationSelect.getAuditStatus())
						|| Constants.STATE_25.equals(creditApplicationSelect.getAuditStatus())
						|| Constants.STATE_4.equals(creditApplicationSelect.getAuditStatus())
						|| Constants.STATE_7.equals(creditApplicationSelect.getAuditStatus())
                        || Constants.STATE_18.equals(creditApplicationSelect.getAuditStatus())
                        || Constants.STATE_31.equals(creditApplicationSelect.getAuditStatus())
                        || Constants.STATE_32.equals(creditApplicationSelect.getAuditStatus()))
						|| ((Constants.STATE_21.equals(creditApplicationSelect.getAuditStatus())) && "1".equals(creditApplicationSelect.getBusinessType()))) {
					/******** 日志 ********/
					OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
					operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
					operateLog.setFunctionCode("1000**");
					operateLog.setResult("申请注销(关单)");
					operateLogService.insert(operateLog);
					creditApplication.setAuditStatus(Constants.STATE_26);
					creditApplicationDAO.submitAuditing(creditApplication);
					// //咨询 借款标记 改为 0 - 未借款
					// CustomerConsult customerConsult =
					// customerConsultDAO.selectCustomerConsultById(creditApplicationSelect.getCustomerConsultId());
					// customerConsult.setStatus(Constants.CONSULT_STATUS_0);
					// customerConsultDAO.updateCustomerConsult(customerConsult);

					message.setSuccess(true);

				} else {
					message.setSuccess(false);
					message.setMsg("该业务状态下，不允许关单");
				}
			} else {
				message.setMsg("查询不到该条数据");
			}
		} else {
			message.setMsg("查询不到该条数据");
		}
		return message;
	}

	@Override
	public Message updateCreditApplication(CreditApplication creditApplication) {
		Message message = new Message();
		message.setSuccess(false);
		if (creditApplication.getCreditapplicationId() != null) {
			CreditApplication creditApplicationSelect = creditApplicationDAO.selectById(Long.valueOf(creditApplication
					.getCreditapplicationId()));
			if (creditApplicationSelect != null) {
				if (creditApplicationSelect.getAuditStatus() != null
						&& !Constants.STATE_1.equals(creditApplicationSelect.getAuditStatus())) {
					message.setSuccess(false);
					message.setMsg("该业务状态已经更改，不是待审核，不能进行撤回操作");
				} else {
					/******** 日志 ********/
					OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
					operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
					operateLog.setFunctionCode("10002*");
					operateLog.setResult("申请提交撤回");
					operateLogService.insert(operateLog);
					rlApplyAuditTableService.updateApplyAuditByCreditapplicationId(creditApplication
							.getCreditapplicationId());
					int result = creditApplicationDAO.update("CREDITAPPLICATION.updateChehui", creditApplication);
					if (result > 0) {
						message.setSuccess(true);
					}
				}
			} else {
				message.setMsg("为查询到该条数据");
			}
		} else {
			message.setMsg("为查询到该条数据");
		}
		return message;
	}

	@Override
	public CreditApplication addOrUpdateCreditApplication(CreditApplication creditApplication, AccountInfo accountInfo,
			AccountInfo returnAccount) throws Exception{
		// 修改
		if (creditApplication.getCreditapplicationId() != null) {
			// 公司账户 并且账户存在 则删除
			// if( "0".equals(creditApplication.getBusinessType()) ){
			// //accountInfoDAO.delete("ACCOUNTINFO.delete",
			// accountInfo.getAccountInfoId());
			// creditApplication.setAccountInfoId(null);
			// }else if( "1".equals(creditApplication.getBusinessType()) &&
			// accountInfo.getAccountInfoId() != null){
			// accountInfoService.updateAccountInfo(accountInfo);
			// creditApplication.setAccountInfoId(accountInfo.getAccountInfoId());
			// }else if( "1".equals(creditApplication.getBusinessType()) &&
			// accountInfo.getAccountInfoId() == null){
			// int accountInfoId= accountInfoService.addAccount(accountInfo);
			// creditApplication.setAccountInfoId(accountInfoId);
			// }else{
			// 设置 departmentid 从smp 获取
			creditApplication.setDepartmentId(String.valueOf(SpringSecurityUtils.getCurrentUser().getDepartmentId()));
			// }

			// //公司账户 并且账户存在 则删除
			// if( "1".equals(creditApplication.getDefaultReturnmentWay())){
			// //accountInfoService.delete("ACCOUNTINFO.delete",
			// returnAccount.getAccountInfoId());
			// creditApplication.setReturnAccountInfoId(null);
			// }else if( "0".equals(creditApplication.getDefaultReturnmentWay())
			// && returnAccount.getAccountInfoId() != null){
			// accountInfoService.updateAccountInfo(returnAccount);
			// creditApplication.setReturnAccountInfoId(returnAccount.getAccountInfoId());
			// }else if( "0".equals(creditApplication.getDefaultReturnmentWay())
			// && returnAccount.getAccountInfoId() == null){
			// int accountInfoId= accountInfoService.addAccount(returnAccount);
			// creditApplication.setReturnAccountInfoId(accountInfoId);
			// }
			creditApplicationDAO.updateCreditApplication(creditApplication);

		} else {
			// //公司账户 不添加账户信息
			// if("1".equals(creditApplication.getBusinessType())){
			// int accountInfoId= accountInfoService.addAccount(accountInfo);
			// creditApplication.setAccountInfoId(accountInfoId);
			// }
			// if( "0".equals(creditApplication.getDefaultReturnmentWay())){
			// int accountInfoId= accountInfoService.addAccount(returnAccount);
			// creditApplication.setReturnAccountInfoId(accountInfoId);
			// }

            int creditapplicationId = creditApplicationDAO.addCreditApplication(creditApplication);

            BorrowerInfo borrowerInfo = new BorrowerInfo();
            borrowerInfo.setCredentialsNumber(creditApplication.getCredentialsNumber());
            borrowerInfo.setMateIdNumber(creditApplication.getMateIdNumber());
            borrowerInfo.setCreditApplicationId(String.valueOf(creditapplicationId));
			/******** 日志 ********/
			OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
			operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
			operateLog.setFunctionCode("100010");
			operateLog.setResult("创建申请");
			operateLog.setRemark("业务单号：" + creditApplication.getGroupNumber());
			operateLogService.insert(operateLog);
			
			/*********咨询池日志***********/
			if(creditApplication.getConsultPoolId()!=null){
				CustomerConsultPool customerconsultPool=new CustomerConsultPool();
				CustomerConsultPoolLog customerConsultPoolLog=new CustomerConsultPoolLog();
			customerConsultPoolLog.setOptDate(new Date());//操作时间
			customerConsultPoolLog.setConnectionId(creditApplication.getConsultPoolId());  //咨询池Id
			customerConsultPoolLog.setConnectionCetegory("0");			//关联id分类 0-营销咨询log
			customerConsultPoolLog.setOptModule("b00011");				//操作模块，即保存日志所涉及模块，如批量导入，
			//调用查询受理咨询列表方法
			customerconsultPool=customerConsultPoolService.queryCustomerConsultPool(creditApplication.getConsultPoolId());
			customerConsultPoolLog.setCurrStatus(customerconsultPool.getAcceptConsultState()); //操作前状态
			customerConsultPoolLog.setCurrConnectionDicSection("acceptConsultState");			//操作前状态的section
			
			// 关联咨询池。。将受理咨询状态改为  "已申请"  hongjieluo
			if (creditApplication.getConsultPoolId() != null
					&& !("".equals(creditApplication.getConsultPoolId()))) {
				CustomerConsultPool customerConsultPool = new CustomerConsultPool();
				customerConsultPool.setConsultPoolId(creditApplication.getConsultPoolId());
				customerConsultPool.setAcceptConsultState("6");
                borrowerInfo.setConsultPoolId(creditApplication.getConsultPoolId().toString());
				customerConsultPoolDao.updateByPrimaryKey_(customerConsultPool);
			}
			Map<String, String> contentMap=new HashMap<String, String>();
			//*************咨询池日志*****************
			/**
			 * 查询出  客户姓名    加到Map中
			 */
			contentMap.put("客户姓名",customerconsultPool.getCustomerName());
			customerConsultPoolLog.setNextStatus(customerconsultPool.getAcceptConsultState());   //操作后状态
			customerConsultPoolLog.setNextConnectionDicSection("acceptConsultState");		//操作后状态的section
			//调用添加日志方法
			customerConsultPoolLogService.insert(customerConsultPoolLog, contentMap);
			}

			try {
				DESPlus des = new DESPlus();
				String desId = des.encrypt(String.valueOf(creditapplicationId));
				creditApplication.setCreditapplicationId(creditapplicationId);
				creditApplication.setCreditapplicationDESId(desId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 关联客户咨询。。将咨询改为 借款状态
			if (creditApplication.getCustomerConsultId() != null
					&& !("".equals(creditApplication.getCustomerConsultId()))) {
				CustomerConsult customerConsult = new CustomerConsult();
				customerConsult.setCustomerConsultId(creditApplication.getCustomerConsultId());
				customerConsult.setStatus(Constants.CONSULT_STATUS_1);
                borrowerInfo.setCustomerConsultId(creditApplication.getCustomerConsultId().toString());
				customerConsultDAO.updateCustomerConsult(customerConsult);
			}
            customerService.addNewBorrowerToCreditAppplication(borrowerInfo);
		}

		return creditApplication;
	}

	@Override
	public Pagination selectCreditApplicationList(CreditApplication creditApplication, String fuzzyValue,
			String auditStatusArray, Pagination pagination, HttpSession session) {
		Map searchValue = new HashMap();
		searchValue = this.searchValues(creditApplication, fuzzyValue, auditStatusArray, session);
		Pagination paginationResult = new Pagination();
		paginationResult = creditApplicationDAO.select(pagination, searchValue);
		return paginationResult;
	}

	@Override
	public Pagination selectCreditApplicationForLoan(CreditApplication creditApplication, String fuzzyValue,
			Pagination pagination, HttpSession session, String sort, String order) {
		Map searchValue = new HashMap();
		searchValue = this.searchValues(creditApplication, fuzzyValue, null, session);
		searchValue.put("sort", sort);
		searchValue.put("order", order);
		Pagination paginationResult = new Pagination();
		paginationResult = creditApplicationDAO.selectForLoan(pagination, searchValue);
		List<CreditApplication> items = paginationResult.getRows();
		for (CreditApplication c : items) {
			if (c.getCreditapplicationId() != null) {
				try {
					DESPlus des = new DESPlus();
					StringBuffer sb = new StringBuffer(String.valueOf(c.getCreditapplicationId()));
					sb.append(Constants.CM_LOAN);
					String creditapplicationDESId = des.encrypt(sb.toString());
					// 查看申请单附件的 id （与定义的属性 意义不同）
					c.setLaonDESId((des.encrypt(String.valueOf(c.getCreditapplicationId()))));
					// 查看代后资料的 id （与定义的属性 意义不同）（LaonDESId 与 CreditapplicationDESId 的意义对调了 ）
					c.setCreditapplicationDESId(creditapplicationDESId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return paginationResult;
	}

	@Override
	public Pagination selectCreditApplicationForFinanceLoan(CreditApplication creditApplication, String fuzzyValue,
			Pagination pagination, HttpSession session) {
		Map searchValue = new HashMap();
		searchValue = this.searchValues(creditApplication, fuzzyValue, null, session);

		Pagination paginationResult = new Pagination();
		List<CreditApplication> creditApplicationVoListTemp = new ArrayList<CreditApplication>();
		paginationResult = creditApplicationDAO.selectForFinanceLoan(paginationResult, searchValue);
		List<CreditApplication> creditApplicationVoList = paginationResult.getRows();
		paginationResult.setItems(creditApplicationVoListTemp);
		return paginationResult;
	}

	@Override
	public CreditApplication selectCreditApplication(CreditApplication creditapplication) {
		CreditApplication creditApplication = creditApplicationDAO.selectCreditApplicationById(creditapplication);
		return creditApplication;
	}

	@Override
	public CreditApplication selectCreditApplicationIsTrue(CreditApplication creditapplication) {
		// TODO Auto-generated method stub
		CreditApplication creditApplication = creditApplicationDAO.selectCreditApplicationByIdIsTrue(creditapplication);
		return creditApplication;
	}

	@Override
	public CreditApplication selectCreditApplicationForConfirm(CreditApplication creditapplication) {
		CreditApplication creditApplication = creditApplicationDAO.selectCreditApplicationById(creditapplication);
		// 获取审批的放款金额
		RlApplyAuditTable applyAuditTable = rlApplyAuditTableService.selectExamAmount(creditapplication
				.getCreditapplicationId());
		double groupAppTotal = creditApplicationDAO.getGroupAppTotal(creditApplication.getCreditapplicationId());
		if (groupAppTotal > 0 && !("".equals(groupAppTotal))) {
			creditApplication.setGroupAppTotal(groupAppTotal);
		}
		if (applyAuditTable != null) {
			double loanAmount = applyAuditTable.getExamAmount();
			if (loanAmount > 0 && !("".equals(loanAmount))) {
				creditApplication.setLoanAmount(loanAmount);
			}
		}
		return creditApplication;
	}

	@Override
	public CreditApplication selectById(int creditapplicationId) {
		CreditApplication creditApplication = creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
		return creditApplication;
	}

	/**
	 * 删除
	 * 
	 * @param creditapplicationId
	 * @return int
	 */
	@Override
	public int deleteById(int creditapplicationId) {
		CreditApplication creditApplication = creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
		// 咨询 借款标记 改为 0 - 未借款
		// CustomerConsult customerConsult =
		// customerConsultDAO.selectCustomerConsultById(creditApplication.getCustomerConsultId());
		// customerConsult.setStatus(Constants.CONSULT_STATUS_0);
		// customerConsultDAO.updateCustomerConsult(customerConsult);
		return creditApplicationDAO.deleteById(creditapplicationId);
	}

	@Override
	public CreditApplication selectByGroupNumber(String groupNumber) {
		return creditApplicationDAO.selectByGroupNumber(groupNumber);
	}

	/**
	 * 删除
	 * 
	 * @param creditapplicationId
	 * @return boolean
	 */
	@Override
	public boolean updateValidStateOfCreditApplication(Integer creditapplicationId) {
		boolean isSuccess = false;
		List<BorrowerServiceApp> borrowerServiceApps = new ArrayList<BorrowerServiceApp>();
		// 查询该小组下的个人申请
		borrowerServiceApps = (List<BorrowerServiceApp>) borrowerServiceAppDAO.queryList(
				"BORROWERSERVICE.selectByCreditapplicationId", creditapplicationId);
		// 判断个人申请List是否为空
		if (CommonUtil.isNotEmpty(borrowerServiceApps)) {// 个人申请不为空
			// 标识为删除的个人申请List
			List<BorrowerServiceApp> bsaValiStateIs0 = new ArrayList<BorrowerServiceApp>();
			// 标识为有效的个人申请List
			List<BorrowerServiceApp> bsaValiStateIs1 = new ArrayList<BorrowerServiceApp>();
			// 循环取出来判断
			for (int i = 0; i < borrowerServiceApps.size(); i++) {
				String validState = borrowerServiceApps.get(i).getValidState();
				if ("1".equals(validState)) {
					bsaValiStateIs1.add(borrowerServiceApps.get(i));
				} else if ("0".equals(validState)) {
					bsaValiStateIs0.add(borrowerServiceApps.get(i));
				}
			}
			// 如果有效的个人申请List不为空

			if (CommonUtil.isNotEmpty(bsaValiStateIs1)) {
				isSuccess = false;
			}
			// 如果删除的个人申请List不为空

			if (CommonUtil.isNotEmpty(bsaValiStateIs0)) {
				// 如果删除的个人申请List中的对象与个申请的对象个数相同
				if (bsaValiStateIs0.size() == borrowerServiceApps.size()) {
					try {
						CreditApplication creditApplication = new CreditApplication();
						creditApplication.setCreditapplicationId(creditapplicationId);
						creditApplication.setValidState("0");
						// 把这个小组的删除标识状态标记为“0”
						creditApplicationDAO.update("CREDITAPPLICATION.updateValidState", creditApplication);

						creditApplication = creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
						isSuccess = true;
					} catch (Exception e) {
						isSuccess = false;
					}
				} else {
					isSuccess = false;
				}
			}
		} else {
			// 小组下没有个申请，真删除这个小组
			try {
				creditApplicationDAO.delete("CREDITAPPLICATION.deleteById", creditapplicationId);

				CreditApplication creditApplication = creditApplicationDAO
						.selectById(Long.valueOf(creditapplicationId));
				isSuccess = true;
			} catch (Exception e) {
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	@Override
	public Message updateCancel(Integer creditapplicationId) {
		Message message = new Message();
		message.setSuccess(false);
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCreditapplicationId(creditapplicationId);
		creditApplication.setValidState("0");
		// 把这个小组的删除标识状态标记为“0”
		int result = creditApplicationDAO.update("CREDITAPPLICATION.updateValidState", creditApplication);
		creditApplication = creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
		// 咨询 借款标记 改为 0 - 未借款
		// CustomerConsult customerConsult =
		// customerConsultDAO.selectCustomerConsultById(creditApplication.getCustomerConsultId());
		// customerConsult.setStatus(Constants.CONSULT_STATUS_0);
		// customerConsultDAO.updateCustomerConsult(customerConsult);

		if (result > 0) {
			message.setSuccess(true);
		}
		return message;

	}

	@Override
	public BorrowerServiceApp queryBspForUploadFile(Map searchMap) {
		return creditApplicationDAO.queryBspForUploadFile(searchMap);
	}

	@Override
	public void addOtherFileUpload(Integer caId, String[] uploadFileList, String fileType, String[] uploadFileTypeList,
			String[] originalFileList) throws Exception {
		List<OtherFileUpload> listTemp = packagingOtherFileUpload(caId, uploadFileList, fileType, uploadFileTypeList,
				originalFileList);
		if (CommonUtil.isNotEmpty(listTemp)) {
			creditApplicationDAO.addOtherFileUpload(listTemp);
		}
	}

	/**
	 * 
	 * @param caId
	 * @param uploadFileList
	 * @param uploadFileTypeList
	 * @param originalFileList
	 * @param fileType
	 * @return List
	 */
	private List<OtherFileUpload> packagingOtherFileUpload(Integer caId, String[] uploadFileList, String fileType,
			String[] uploadFileTypeList, String[] originalFileList) {
		List<OtherFileUpload> listTemp = new ArrayList<OtherFileUpload>();
		if (null != uploadFileList && null != uploadFileTypeList && originalFileList != null) {
			int len = uploadFileList.length;
			for (int i = 0; i < len; i++) {
				OtherFileUpload otherFileUpload = new OtherFileUpload();
				otherFileUpload.setUploadFileName(uploadFileList[i]);
				otherFileUpload.setFileType(fileType);
				otherFileUpload.setUploadDependClass(Constants.BORROWER_SERVICE_APP_CLASS);
				otherFileUpload.setFileSubType(uploadFileTypeList[i]);
				otherFileUpload.setUploadDependId(caId);
				// 将新数据添加到原来的set集合里,存储数据用
				listTemp.add(otherFileUpload);
			}
		}
		return listTemp;
	}

	/**
	 * 
	 * @param caId
	 * @param uploadFileList
	 * @param uploadFileTypeList
	 * @param originalFileList
	 * @return List
	 */
	private List<OtherFileUpload> packagingOtherFileUploadHis(Integer caId, String[] uploadFileList,
			String[] uploadFileTypeList, String[] originalFileList) {
		List<OtherFileUpload> listTemp = new ArrayList<OtherFileUpload>();
		if (null != uploadFileList && null != uploadFileTypeList) {
			String[] nameArr = uploadFileList;// UUID之后的名字
			String[] originalName = originalFileList;// 原来的名字
			// 根据名字划分小组内各自的文件及共同文件
			int len = nameArr.length;
			for (int i = 0; i < len; i++) {
				// originalName中取出标识、身份证
				if (CommonUtil.isNotEmpty(originalName[i])) {
					String[] s = StringUtils.split(originalName[i], ".");
					String type = s[0];
					if (CommonUtil.isNotEmpty(type)) {
						String[] filename = StringUtils.split(type, "_");
						String identifier = filename[0];
						String myType = filename[filename.length - 1];
						if (CommonUtil.isNotEmpty(myType) && "G".equals(myType)) {
							// TODO 如果为小组，与小组关联
							OtherFileUpload otherFileUpload = new OtherFileUpload();
							otherFileUpload.setUploadFileName(nameArr[i]);
							otherFileUpload.setFileType(Constants.FILE_TYPE_GROUP_FILES);
							otherFileUpload.setUploadDependClass(Constants.CREDIT_APPLICATION_CLASS);
							otherFileUpload.setFileSubType(uploadFileTypeList[i]);
							otherFileUpload.setUploadDependId(caId);
							// 将新数据添加到原来的set集合里,存储数据用
							listTemp.add(otherFileUpload);
						} else if (CommonUtil.isNotEmpty(identifier)) {
							Map searchMap = new HashMap();
							searchMap.put("credentialsNumber", identifier);
							searchMap.put("creditapplicationId", caId);
							// TODO 根据身份证查出个人，与个人资料关联
							BorrowerServiceApp bsp = queryBspForUploadFile(searchMap);
							if (bsp != null) {
								OtherFileUpload otherFileUpload = new OtherFileUpload();
								otherFileUpload.setUploadFileName(nameArr[i]);
								otherFileUpload.setFileType(Constants.FILE_TYPE_BORROWER_OTHERS);
								otherFileUpload.setUploadDependClass(Constants.BORROWER_SERVICE_APP_CLASS);
								otherFileUpload.setFileSubType(uploadFileTypeList[i]);
								otherFileUpload.setUploadDependId(bsp.getBorrowerServiceAppId());
								// 将新数据添加到原来的set集合里,存储数据用
								listTemp.add(otherFileUpload);
							}

						}
					}
				}
			}
		}
		return listTemp;
	}

	@Override
	public Pagination queryUploadFileList(Integer caId, Pagination pagination) {
		Pagination pagination2 = creditApplicationDAO.queryUploadFileList(caId, pagination);
		List<OtherFileUpload> list = new ArrayList<OtherFileUpload>();
		if (pagination2 != null) {
			list = pagination2.getRows();
		}
		if (CommonUtil.isNotEmpty(list)) {
			for (OtherFileUpload o : list) {
				o.setFileSubType(convertSubType(o.getFileSubType()));
			}
		}
		return pagination2;
	}

	@Override
	public List<OtherFileUpload> queryUploadFiles(Integer caId, String fileType) {
		// TODO Auto-generated method stub
		List<OtherFileUpload> otherFileUploads = new ArrayList<OtherFileUpload>();
		otherFileUploads = creditApplicationDAO.queryUploadFiles(caId, fileType);
		if (CommonUtil.isNotEmpty(otherFileUploads)) {
			for (OtherFileUpload o : otherFileUploads) {
				o.setFileSubType(convertSubType(o.getFileSubType()));
			}
		}
		return otherFileUploads;
	}

	/**
	 * 
	 * @author zhangman
	 * @param subType
	 *            subType
	 * @return 2012-12-24下午04:06:28
	 */
	public String convertSubType(String subType) {
		String value = "";
		if (subType != null) {
			if (subType.equalsIgnoreCase("1")) {
				value = "媒体文件";
			} else if (subType.equalsIgnoreCase("2")) {
				value = "征信委托书";
			} else if (subType.equalsIgnoreCase("3")) {
				value = "表格";
			} else if (subType.equalsIgnoreCase("4")) {
				value = "证件";
			} else if (subType.equalsIgnoreCase("5")) {
				value = "贷后文件";
			} else if (subType.equalsIgnoreCase("6")) {
				value = "免罚申请单";
			} else if (subType.equalsIgnoreCase("7")) {
				value = "分析报告";
			}

		}
		return value;

	}

	@Override
	public boolean delUploadFile(File file) {
		try {
			if (file != null) {
				String uploadFileName = file.getName();
				if (uploadFileName != null) {
					creditApplicationDAO.deleteOtherFileUpLoadByUploadFileName(uploadFileName);
				}
				file.delete();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> queryUploadFileList(Integer caId) {
		List<String> fileList = new ArrayList<String>();
		fileList = creditApplicationDAO.queryUploadFileList(caId);
		return fileList;
	}

	// * 更改申请状态（咨询）（还款完成 - 16。提前还款完成 - 20）
	@Override
	public boolean changeAuditing(CreditApplication creditApplication, int creditapplicationId, String auditStatus) {
		if (creditApplication != null) {
			CreditApplication creditApplication2 = creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
			if (Constants.STATE_16.equals(auditStatus) // 还款完成
					|| Constants.STATE_20.equals(auditStatus) // 提前还款完成
					|| Constants.STATE_6.equals(auditStatus) // 失败登记
					|| Constants.STATE_26.equals(auditStatus) // 注销
			) {
			}

			creditApplication.setAuditStatus(auditStatus);
			BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
			borrowerServiceApp.setCreditapplicationId(creditApplication.getCreditapplicationId());
			borrowerServiceApp.setAuditStatus(auditStatus);
			if (creditApplication.getCreditapplicationId() == null
					|| "".equals(creditApplication.getCreditapplicationId())) {
				creditApplication.setCreditapplicationId(creditapplicationId);
				borrowerServiceApp.setCreditapplicationId(creditapplicationId);
			}
			if (auditStatus.equals(Constants.STATE_1)) {

				creditApplication.setSubmitDate(new Date());
				if (creditApplication2.getFirstSubmitDate() != null) {
				} else {
					creditApplication.setFirstSubmitDate(new Date());
				}
				creditApplicationDAO.submitAuditing(creditApplication);
				borrowerServiceAppDAO.submitAuditing(borrowerServiceApp);
				/******** 日志 ********/
				OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
				operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
				operateLog.setFunctionCode("100020");
				operateLog.setResult("提交申请");
				operateLogService.insert(operateLog);
			} else {
				creditApplicationDAO.submitAuditing(creditApplication);
				borrowerServiceAppDAO.submitAuditing(borrowerServiceApp);
			}
			if (auditStatus.equals(Constants.STATE_7)) {
				/******** 日志 ********/
				OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
				operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
				operateLog.setFunctionCode("10002*");
				operateLog.setResult("申请撤回");
				operateLogService.insert(operateLog);
			}
		}
		return true;
	}

	// * 失败登记（咨询）
	@Override
	public Message loanregistRevocation(CreditApplication creditApplication, int creditapplicationId, String auditStatus) {
		Message message = new Message();
		message.setSuccess(false);
		if (creditApplication != null) {
			creditApplication.setCreditapplicationId(creditapplicationId);
			CreditApplication creditApplication2 = creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
			if ("21".equals(creditApplication2.getAuditStatus()) || "10".equals(creditApplication2.getAuditStatus())
					|| "14".equals(creditApplication2.getAuditStatus())) {
				creditApplication.setAuditStatus(Constants.STATE_6);

				BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
				borrowerServiceApp.setCreditapplicationId(creditApplication.getCreditapplicationId());
				borrowerServiceApp.setAuditStatus(Constants.STATE_6);
				borrowerServiceApp.setCreditapplicationId(creditapplicationId);
				creditApplicationDAO.submitAuditing(creditApplication);
				borrowerServiceAppDAO.submitAuditing(borrowerServiceApp);
				/******** 日志 ********/
				OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
				operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
				operateLog.setFunctionCode("300050");
				operateLog.setResult("放款失败登记");
				operateLog.setRemark("原因：" + creditApplication.getRevokeReason());
				operateLogService.insert(operateLog);
				message.setSuccess(true);
			}
		}
		return message;
	}

	@Override
	public void submitCreditApplication(int creditapplicationId, String auditStatus) {
		// CreditApplication creditApplication =
		// creditApplicationDAO.selectById(Long.valueOf(creditapplicationId));
		// if(creditApplication != null){
		// creditApplication.setAuditStatus(auditStatus);
		// BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		// borrowerServiceApp.setCreditapplicationId(creditApplication.getCreditapplicationId());
		// borrowerServiceApp.setAuditStatus(auditStatus);
		// if(creditApplication.getCreditapplicationId() == null ||
		// "".equals(creditApplication.getCreditapplicationId() )){
		// creditApplication.setCreditapplicationId(creditapplicationId);
		// borrowerServiceApp.setCreditapplicationId(creditapplicationId);
		// }
		// if(auditStatus.equals(Constants.STATE_1)){
		//
		// }
		// }
	}

	/**
	 * 
	 * @param creditApplication
	 *            信贷带向
	 * @param fuzzyValue
	 *            模糊查询条件
	 * @param session
	 *            Session对象
	 * @return Map对象
	 */
	public Map searchValues(CreditApplication creditApplication, String fuzzyValue, String auditStatusArray,
			HttpSession session) {
		// 添加权限查询
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		String sqlsid = "";
		if (authList.size() > 0) {
			for (String e : authList) {
				if (sqlsid != null) {
					sqlsid = sqlsid + "'" + e + "'" + ",";
				} else {
					sqlsid = "'" + e + "'" + ",";
				}
			}
			sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
		}

		Map searchValue = new HashMap();
		searchValue.put("groupNumber", creditApplication.getGroupNumber());
		searchValue.put("groupName", creditApplication.getGroupName());
		searchValue.put("auditStatus", creditApplication.getAuditStatus());
		searchValue.put("address", creditApplication.getAddress());
		searchValue.put("countyId", creditApplication.getCountyId());
		searchValue.put("townId", creditApplication.getTownId());
		searchValue.put("villageId", creditApplication.getVillageId());
		searchValue.put("beginSubmitDate", creditApplication.getBeginSubmitDate());
		searchValue.put("endSubmitDate", creditApplication.getEndSubmitDate());
		searchValue.put("beginExpectLoanDate", creditApplication.getBeginExpectLoanDate());
		searchValue.put("endExpectLoanDate", creditApplication.getEndExpectLoanDate());
		searchValue.put("beginLoanDate", creditApplication.getBeginLoanDate());
		searchValue.put("endLoanDate", creditApplication.getEndLoanDate());
		searchValue.put("creditapplicationId", creditApplication.getCreditapplicationId());
		searchValue.put("defaultReturnmentWay", creditApplication.getDefaultReturnmentWay());
		searchValue.put("businessType", creditApplication.getBusinessType());
		searchValue.put("customerConsultId", creditApplication.getCustomerConsultId());
		searchValue.put("validState", creditApplication.getValidState());
		searchValue.put("fuzzyValue", fuzzyValue);
		searchValue.put("authList", sqlsid);
		searchValue.put("companyName", creditApplication.getCompanyName());
		searchValue.put("companyId", creditApplication.getCompanyId());
		searchValue.put("role", creditApplication.getRole());
		searchValue.put("ids", creditApplication.getIds());
		searchValue.put("loanOfficerId", creditApplication.getLoanOfficerId());
		searchValue.put("loanOfficerName", creditApplication.getLoanOfficerName());
		searchValue.put("repaymentPlanName", creditApplication.getRepaymentPlanName());
		searchValue.put("consultPoolId", creditApplication.getConsultPoolId());
		searchValue.put("loanConfirmDateBegin", creditApplication.getLoanConfirmDateBegin());
		searchValue.put("loanConfirmDateEnd", creditApplication.getLoanConfirmDateEnd());
		searchValue.put("fxB", creditApplication.getFxB());
		searchValue.put("fxB", creditApplication.getFxB());
		searchValue.put("participateType", creditApplication.getParticipateType());//参审角色
		if (auditStatusArray != null) {
			searchValue.put("auditStatusArray", auditStatusArray.toString());
		}
		return searchValue;
	}

	@Override
	public Pagination selectFinanceLoan(Pagination pagination, CreditApplication creditApplication) {
		return creditApplicationDAO.selectFinanceLoan(pagination, creditApplication);
	}

	@Override
	public CreditApplication selectCreditApplicationByReceiveRecordId(Integer receiveRecordId) {
		return creditApplicationDAO.selectCreditApplicationByReceiveRecordId(receiveRecordId);
	}

	@Override
	public CreditApplication selectAmount(int creditapplicationId) {
		return creditApplicationDAO.selectAmount(creditapplicationId);
	}

	@Override
	public int getImgAmount(String clientId) throws Exception {
		logger.info("调用cm接口cmClientWSPortType.getImgAmount 入参：clientId=" + clientId + ";bussTableName="
				+ Constants.CM_U_YINONGDAI2_YW);
		String amount = cmClientWSPortType.getImgAmount(clientId, Constants.CM_U_YINONGDAI2_YW);
		logger.info("调用cm接口cmClientWSPortType.getImgAmount 出参：" + amount);
		// count:2[] count:0[]
		int imgAmount = 0;
		if (CommonUtil.isNotEmpty(amount)) {
			if ("failure".equalsIgnoreCase(amount)) {
				throw new BusinessException("调用cm接口方法cmClientWSPortType.getImgAmount返回值异常,请联系cm人员,返回值=" + imgAmount);
			}
			imgAmount = Integer.valueOf(String.valueOf(amount.charAt(6)));
		}
		return imgAmount;
	}

	@Override
	public String createGroupNumber(CreditApplication creditApplication) throws Exception {
		String result = "";
		// String repaymentName = creditApplication.getRepaymentPlanName();
		// String department = creditApplication.getCompanyName();
		String departmentName = SpringSecurityUtils.getCurrentUser().getAreaDepartmentName();
		// 1查找分公司和产品对应的编码
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(Constants.DEPARTMENTSN);
		dictionaryVo.setCodeValue(departmentName);
		dictionaryVo.setSelectType("V");
		// DataDictionaryVo dictionaryVo1 = new DataDictionaryVo();
		// dictionaryVo1.setSection(Constants.PRODUCTSN);
		// dictionaryVo1.setCodeValue(repaymentName);
		// dictionaryVo1.setSelectType("V");
		List<CodeTable> departmentNums = dataDictionaryDao.getCodeTableBySection(dictionaryVo);
		// List<CodeTable> productNums =
		// dataDictionaryDao.getCodeTableBySection(dictionaryVo1);
		if (CommonUtil.isEmpty(departmentNums)) {
			return Constants.DEPARTMENTSN_NO;
		}
		// if(CommonUtil.isEmpty(productNums)){
		// return Constants.PRODUCTSN_NO;
		// }

		String groupNumber = serialNumber.getSerialNumber();
		if (CommonUtil.isNotEmpty(departmentNums) && CommonUtil.isNotEmpty(groupNumber)) {
			CodeTable codeTable1 = departmentNums.get(0);
			// CodeTable codeTable2 = productNums.get(0);
			String departmentNum = codeTable1.getCodeKey();
			// String productNum = codeTable2.getCodeKey();
			String year = groupNumber.substring(0, 2);
			String end = groupNumber.substring(2);
			result = year + departmentNum + end;
		}
		return result;
	}

	@Override
	public String updateGroupNumber(CreditApplication creditApplication) {
		String groupNum = creditApplication.getGroupNumber();
		String repaymentName = creditApplication.getRepaymentPlanName();
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(Constants.PRODUCTSN);
		dictionaryVo.setCodeValue(repaymentName);
		dictionaryVo.setSelectType("V");
		List<CodeTable> productNums = dataDictionaryDao.getCodeTableBySection(dictionaryVo);
		if (CommonUtil.isNotEmpty(productNums)) {
			CodeTable codeTable2 = productNums.get(0);
			String productNum = codeTable2.getCodeKey();
			String newGroupNum = CommonUtil.replace(groupNum, productNum);
			// creditApplication.setGroupNumber(newGroupNum);
			// creditApplicationDAO.updateCreditApplication(creditApplication);
			return newGroupNum;
		} else {
			return Constants.PRODUCTSN_NO;
		}
	}

	@Override
	public CreditApplication selectCreditByCustomerId(Integer customerBasicId) {
		// TODO Auto-generated method stub
		return creditApplicationDAO.selectCreditByCustomerId(customerBasicId);
	}

	@Override
	public Message updateSubmit(CreditApplication creditApplication, Integer creditapplicationId, String auditStatus) {
		Message message = new Message();
		List<CreditInvestigationVo> creditInvestigationVoList = creditInvestigationService
				.selectCreditInvestigationVoList(Long.valueOf(creditapplicationId));
		if (creditInvestigationVoList == null) {
			message.setSuccess(false);
			message.setMsg("信用及背景调查报告还没提交，先请风险经理提交信用及背景调查报告！");
		} else if (creditInvestigationVoList.size() == 0) {
			message.setSuccess(false);
			message.setMsg("信用及背景调查报告还没提交，先请风险经理提交信用及背景调查报告！");
		} else if (creditInvestigationVoList.size() > 1) {
			message.setSuccess(false);
			message.setMsg("信用及背景调查报告,返回多条数据");
		} else {
			CreditInvestigationVo creditInvestigationVo = creditInvestigationVoList.get(0);
			if (creditInvestigationVo != null && (creditInvestigationVo.getIsSubmit() != null)
					&& ("1".equals(creditInvestigationVo.getIsSubmit()))) {
				// 用于进行现金流必填控制
				Double result = cashStreamService.getCalValByCreditId(creditapplicationId);
				if (result != null && result != 0) {
					boolean flag = this.changeAuditing(creditApplication, creditapplicationId, auditStatus);
					// 这个提前到了添加借款人哪里
					// this.updateRevolving(creditapplicationId);
					message.setSuccess(flag);
					if (!flag) {
						message.setMsg("提交失败");
					}
				} else {
					message.setSuccess(false);
					message.setMsg("现金流入和流出表 没有提交");
				}

			} else {
				message.setSuccess(false);
				message.setMsg("信用及背景调查报告还没提交，先请风险经理提交信用及背景调查报告！");
			}

		}

		return message;
	}

	@Override
	public List<CreditApplication> selectCreditByconsult(Long customerConsultId) {
		// TODO Auto-generated method stub
		return creditApplicationDAO.selectCreditByconsult(customerConsultId);
	}

	/**
	 * @author hongjieluo
	 * 根据咨询池查询 借款申请
	 */
	@Override
	public List<CreditApplication> selectCreditByconsultPool(Long consultPoolId) {
		// TODO Auto-generated method stub
		return creditApplicationDAO.selectCreditByconsultPool(consultPoolId);
	}
	
	@Override
	public List<BorrowerServiceVo> selectBorrowerFamily(int creditapplicationId) {
		return creditApplicationDAO.selectBorrowerFamily(creditapplicationId);
	}

	@Override
	public List<BorrowerServiceVo> selectBorrowerFamilyByID(String credentialsNumber) {
		return creditApplicationDAO.selectBorrowerFamilyByID(credentialsNumber);
	}

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param accountInfoId
	 *            账号id
	 * @return 姓名 身份证map
	 */
	@Override
	public List<BorrowerServiceVo> selectBorrowerFamilyByAccount(int accountInfoId) {
		List<BorrowerServiceVo> borrowerServiceVos = new ArrayList<BorrowerServiceVo>();
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setAccountInfoId(accountInfoId);
		List<CreditApplication> creditApplications = creditApplicationDAO.selectCreditApp(creditApplication);
		if (creditApplications.size() > 0) {
			creditApplication = creditApplications.get(0);
			borrowerServiceVos = this.selectBorrowerFamily(creditApplication.getCreditapplicationId());
		} else {
		}
		return borrowerServiceVos;
	}

	/**
	 * 定时调度，已审批但从入户起已满30天未放款的业务，重回入户调查环节 liuli 2013-05-09
	 */
	@Override
	public void updateToInvestigate() {
		// System.out.println("定时器updateToInvestigate----");
		// 取入户起已满30天未放款的且能执行回退到入户的业务
		List<CreditApplication> creditApplications = creditApplicationDAO.selectUnPayOver30Days();
		int num = creditApplications.size();
		for (int i = 0; i < num; i++) {
			CreditApplication c = creditApplications.get(i);
			// 状态21,14对私能回退，对公不能 21额度确认以后才有对公对私的区分
			// if(c.getAuditStatus().equals("14") ||
			// c.getAuditStatus().equals("21")){
			if (c.getAuditStatus().equals("21")) {
				// 对私的话才允许回退
				if (c.getBusinessType().equals("1")) {
					// 回退操作
					updateCaToInvestigate(c);
				}
			} else {
				// 回退操作
				updateCaToInvestigate(c);
			}
		}
	}

	// * 根据相应的creditApplication对象回退状态到入户调查环节 liuli 2013-05-10
	@Override
	public void updateCaToInvestigate(CreditApplication creditApplication) {
		// 调接口将审批记做历史
		rlApplyAuditTableService.updateApplyAuditByCreditapplicationId(creditApplication.getCreditapplicationId());
		// 如果是14放款确认回退和21 额度确认需要进行额度确认回退等操作
		// if(creditApplication.getAuditStatus().equals("14") ||
		// creditApplication.getAuditStatus().equals("21")){
		if (creditApplication.getAuditStatus().equals("21")) {
			//
			amountConfirmService.updateAmountConfirmHistory(creditApplication.getCreditapplicationId());
		}
		// 置状态为00未提交 入户调查环节
		creditApplication.setAuditStatus(Constants.STATE_0);
		creditApplicationDAO.submitAuditing(creditApplication);
		System.out.println("更新为回退状态" + creditApplication.getCreditapplicationId());
	}

	// * smp同步
	@Override
	public Message updateSmp(CreditApplication application) {
		Message message = new Message();

		EmployeeDTO employeeDTO = smpWSUtil.getEmployeeDTO(application.getLoanOfficerId());
		if (employeeDTO != null) {
			if (employeeDTO.getAreaDepartmentName() != null) {
				String areaDepartmentName = employeeDTO.getAreaDepartmentName();
				application.setCompanyName(areaDepartmentName);
			} else {
				System.out.println(employeeDTO.getAreaDepartmentName() + "-----smp区域部门名称是null");
			}
			if (employeeDTO.getAreaDepartmentId() != null) {
				String areaDepartmentId = employeeDTO.getAreaDepartmentId().toString();
				application.setCompanyId(areaDepartmentId);
			} else {
				System.out.println(employeeDTO.getAreaDepartmentId() + "-----smp区域部门id是null");
			}
			if (employeeDTO.getDepartmentId() != null) {
				String departmentId = employeeDTO.getDepartmentId().toString();
				application.setDepartmentId(departmentId);
			} else {
				System.out.println(employeeDTO.getAreaDepartmentName() + "-----smp DepartmentId 是null");
			}
		} else {
			System.out.println(employeeDTO + "employeeDTO 为空");
		}

		int count = 0;
		if (application.getCompanyId() != null && !"".equals(application.getCompanyId())
				&& application.getCompanyId() != null && !"".equals(application.getCompanyId())
				&& application.getDepartmentId() != null && !"".equals(application.getDepartmentId())) {
			count = creditApplicationDAO.updateCreditApplication(application);
		}
		if (count > 0) {
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public Message updateSmpAll() {
		Message message = new Message();
		int result = 0;
		List<CreditApplication> creditApplications = creditApplicationDAO.selectGroupByLoanOperaterId();
		for (CreditApplication creditApplication : creditApplications) {
			EmployeeDTO employeeDTO = smpWSUtil.getEmployeeDTO(creditApplication.getLoanOfficerId());
			if (employeeDTO != null) {
				if (employeeDTO.getAreaDepartmentName() != null) {
					String areaDepartmentName = employeeDTO.getAreaDepartmentName();
					creditApplication.setCompanyName(areaDepartmentName);
				} else {
					System.out.println(employeeDTO.getAreaDepartmentName() + "-----smp区域部门名称是null");
				}
				if (employeeDTO.getAreaDepartmentId() != null) {
					String areaDepartmentId = employeeDTO.getAreaDepartmentId().toString();
					creditApplication.setCompanyId(areaDepartmentId);
				} else {
					System.out.println(employeeDTO.getAreaDepartmentId() + "-----smp区域部门id是null");
				}
				if (employeeDTO.getDepartmentId() != null) {
					String departmentId = employeeDTO.getDepartmentId().toString();
					creditApplication.setDepartmentId(departmentId);
				} else {
					System.out.println(employeeDTO.getAreaDepartmentName() + "-----smp DepartmentId 是null");
				}
			} else {
				System.out.println(employeeDTO + "employeeDTO 为空");
			}
			if (creditApplication.getCompanyId() != null && !"".equals(creditApplication.getCompanyId())
					&& creditApplication.getCompanyId() != null && !"".equals(creditApplication.getCompanyId())
					&& creditApplication.getDepartmentId() != null && !"".equals(creditApplication.getDepartmentId())) {
				int count = creditApplicationDAO.updateByLoanOperaterId(creditApplication);
				result = result + count;
			}
		}

		if (result > 0) {
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public boolean updateRefuse(Integer creditapplicationId, String flag) throws Exception {
		boolean rltFlag = false;
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCreditapplicationId(creditapplicationId);
		// creditApplication.setValidState("0");
		creditApplication.setAuditStatus(flag);
		int result = creditApplicationDAO.update("CREDITAPPLICATION.updateRefuse", creditApplication);
		if (result == 1) {
			rltFlag = true;
		}
		return rltFlag;
	}

	@Override
	public Message updateRevolving(Integer creditapplicationId) {
		Message message = new Message();
		boolean b = false;
// 查询借款人
		BorrowerServiceApp borrowerServiceApp = borrowerServiceAppDAO
				.selectBorrowerLeaderByCreditApplicationId(creditapplicationId);
		if (borrowerServiceApp != null) {
			// 查询借款人配偶
			Familymember familymember = borrowerServiceAppDAO.selectSpouseByBorrowerServiceAppId(borrowerServiceApp
					.getBorrowerServiceAppId());
			if (familymember != null) {
				if (aRevolvingCreditJudgeTemplateMethod.isRelovingCreditTemplateMethod(
						borrowerServiceApp.getCredentialsNumber(), familymember.getIdNumber())) {
					b = true;// 如果之前有过借款
				}
			}
		}
		if (b) {
			int result = creditApplicationDAO.updateRevolvingCredit(creditapplicationId);
			// 如果满足循环贷 则进行打折规则校验
			Message msg = discountConfigurationService.discountRule(creditapplicationId.longValue());
		} else {
			int result = creditApplicationDAO.updateRevolvingCreditFalse(creditapplicationId);
			discountConfigurationService.updateCreditDiscountVo(creditapplicationId.longValue(), "0", null);
		}
		message.setSuccess(true);
		return message;

	}

	@Override
	public Message updateRevolvingForImport(String borrowerCredentialsNumber, String mateCredentialsNumber) {
// 按照借款人 相匹配查询
		List<Map> mapsList = new ArrayList<Map>();
		mapsList = creditApplicationDAO.selectCreditApplicationIdByBorrower(borrowerCredentialsNumber);
// 按借款人配偶 以及借款人都匹配查询
		Map<String, String> map = new HashMap<String, String>();
		if (borrowerCredentialsNumber != null) {
			map.put("credentialsNumber", mateCredentialsNumber);
		}
		if (mateCredentialsNumber != null) {
			map.put("idNumber", borrowerCredentialsNumber);
		}
		List<Map> map2List = new ArrayList<Map>();
		map2List = creditApplicationDAO.selectCreditApplicationIdByBoth(map);
// 两个list合并
		mapsList.addAll(map2List);
		Message message = new Message();
		if (mapsList.size() > 0) {
			String creditApplicationIds = "";
			Iterator<Map> it = mapsList.iterator();
			while (it.hasNext()) {
				creditApplicationIds = creditApplicationIds + "," + it.next().get("CREDITAPPLICATION_ID");
			}
			creditApplicationIds = creditApplicationIds.substring(1, creditApplicationIds.length());
			creditApplicationDAO.updateRevolvingCreditBatch(creditApplicationIds);
			message.setSuccess(true);
		} else {
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public Pagination selectCreditApplicationSearch(Pagination pagination, String name, String identityNumber) {
		Map map = new HashMap();
		if (name != null && !"".equals(name)) {
			map.put("name", name);
		}
		if (identityNumber != null && !"".equals(identityNumber)) {
			map.put("identityNumber", identityNumber);
		}

		return creditApplicationDAO.creditApplicationSearch(pagination, map);
	}
	/**
     * 根据信贷申请ID获取年化利率
     * @param creditApplicationId
     * @return
     */
	 @Override
	    public Double getAnnualizedRateById(int creditApplicationId){
	        return creditApplicationDAO.getAnnualizedRateById(creditApplicationId);
	    };

    @Override
    public List selectProInfoAll(String authList) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("authList",authList);
        return creditApplicationDAO.selectProInfoAll(map);
    }

    @Override
    public Message saveParticipateApprove(CreditApplication application) {
        Message message = new Message(false, "参审失败，您已参审！");
        if (!application.getIsCityParticipate().equals("1")) {
            Map m = new HashMap();
            User u = SpringSecurityUtils.getCurrentUser();
            m.put("participator_id", u.getUserId());
            m.put("participator_name", u.getUsername());
			m.put("participate_date", new Date());
            m.put("creditapplication_id", application.getCreditapplicationId());
			m.put("participate_type", getParticipateType());
            Long ret = creditApplicationDAO.saveParticipateApprove(m);
            if (ret > 0) {
                /*更新信贷申请城市参审状态*/
                creditApplicationDAO.updateCityParticipate(application.getCreditapplicationId(),"1");
				//添加参审日志
				/******** 日志 ********/
				OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
				operateLog.setCreditapplicationId(Long.valueOf(application.getCreditapplicationId()));
				operateLog.setFunctionCode("100111");
				operateLog.setResult("参审");
				DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
				operateLog.setRemark("操作人姓名:" + u.getUsername() + ";操作人参审角色:" +	getParticipateType() + ";操作时间："+df.format(new Date()));
				operateLogService.insert(operateLog);
                message.setSuccess(true);
                message.setMsg("参审成功");
            }
        }
        return message;
    }

	/**
	 * 此方法不能作为通用方法被调用
	 * 前台限制他能做参审操作
	 * 他的角色也就固定了
	 * @return
	 */
	public String getParticipateType(){
		String rs = new String("角色名称获取失败");
		Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
		if(userAuthoritiesSet.size() > 0 ){
			if (userAuthoritiesSet.contains("ROLE_QVYUJINGLI")) {// 城市经理
				rs = "城市经理";
			}else
			if ((userAuthoritiesSet.contains("ROLE_QUYUFENGXIANJINGLI"))){// 城市风险经理
				rs = "城市风险经理";
			}else
			if ((userAuthoritiesSet.contains("ROLE_ZBRY"))){// 总部人员
				rs = "总部人员";
			}
		}
		return rs;
	}
    @Override
    public Integer updateCityParticipate(Integer creditApplicationId, String yesOrNo) {
        return creditApplicationDAO.updateCityParticipate(creditApplicationId, yesOrNo);
    }

	@Override
	public Integer updateSignagreementDate(CreditApplication creditApplication) {
		
		return creditApplicationDAO.updateSignagreementDate(creditApplication);
	}

    @Override
    public Integer updateDiscountFlagById(Integer creditApplicationId, String discountFlag) {
        return creditApplicationDAO.updateDiscountFlagById(creditApplicationId, discountFlag);
    }

    @Override
    public List<String> getProductIdList() {
        return creditApplicationDAO.getProductIdList();
    }

	@Override
	public Integer updateCityParticipatePerman(Integer creditApplicationId,String yesOrNo){
		return creditApplicationDAO.updateCityParticipatePerman(creditApplicationId, yesOrNo);
	}
	@Override
	public String queryStatus(Long creditApplicationId) {
		return creditApplicationDAO.queryStatus(creditApplicationId);
	}
    @Override
    public int queryCountByBussinessNumber(String businessNumber) {
        return creditApplicationDAO.queryCountByBussinessNumber(businessNumber);
    }
  


}

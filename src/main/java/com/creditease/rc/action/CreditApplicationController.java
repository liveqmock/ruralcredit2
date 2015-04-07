package com.creditease.rc.action;

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.pdf.*;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInfoList;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInstList;
import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.*;
import com.creditease.rc.util.*;
import com.creditease.rc.vo.AmountConfirmVo;
import com.creditease.rc.vo.BorrowerServiceVo;
import com.creditease.rc.vo.CreditApplicationSearch;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;

/**
 * 
 * @author zhangman
 * 
 */
@Controller
@RequestMapping("creditgroup")
public class CreditApplicationController {
	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @Resource
    private ICreditCoBorrowerService creditCoBorrowerService;
    @Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IRlApplyAuditTableService rlApplyAuditTableService;
	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	@Resource
	private IAmountConfirmService amountConfirmService;
	@Resource
	private CeBorrowingProductsWS ceBorrowingProductsWS;
	@Resource
	private IDataDictionaryService dataDictionaryService;
	@Resource
	private ICashStreamService cashStreamService;

	private Logger log = Logger.getLogger(CreditApplicationController.class);

	/**
	 * 新增 或 修改 小组信息
	 * 
	 * @param returnAccountInfo 账户信息
	 * @param accountInfo 账户信息
	 * @param group 申请信息
	 * @return CreditApplication 申请信息
	 * @throws Exception 异常
	 */
	@RequestMapping(value = "addOrUpdate")
	public @ResponseBody
	CreditApplication addOrUpdateCreditApplication(String returnAccountInfo, String accountInfo, String group)
			throws Exception {
		CreditApplication creditApplication = new CreditApplication();
		AccountInfo account = new AccountInfo();
		AccountInfo accountReturn = new AccountInfo();
		account = (AccountInfo) JsonUtil.getObject(accountInfo, AccountInfo.class);
		accountReturn = (AccountInfo) JsonUtil.getObject(returnAccountInfo, AccountInfo.class);
		creditApplication = (CreditApplication) JsonUtil.getObject(group, CreditApplication.class);
		// 设置业务单号
		if (creditApplication != null && CommonUtil.isEmpty(creditApplication.getGroupNumber())) {
			if (CommonUtil.isEmpty(creditApplication.getCompanyId())) {// 当区域级别部门(分公司名称)取不到时不让业务单号
				return creditApplication;
			}
			String groupNum = creditApplicationService.createGroupNumber(creditApplication);
			creditApplication.setGroupNumber(groupNum);
			if (Constants.DEPARTMENTSN_NO.equals(groupNum) || CommonUtil.isEmpty(groupNum)) {
				return creditApplication;
			}
            String  credentialsNumber  =creditApplication.getCredentialsNumber();
            String  mateIdNumber       = creditApplication.getMateIdNumber();
            String  customerConsultId  = String.valueOf(creditApplication.getCustomerConsultId());
            String  consultPoolId      = String.valueOf(creditApplication.getConsultPoolId());
          /*  //consultPoolId =  null;
            System.out.println("-------------credentialsNumber--------------"+credentialsNumber);
            System.out.println("-------------mateIdNumber--------------"+mateIdNumber);
            System.out.println("-------------customerConsultId--------------"+customerConsultId);
            System.out.println("-------------consultPoolId--------------"+consultPoolId);*/
            if(CommonUtil.isEmpty(credentialsNumber) || CommonUtil.isEmpty(mateIdNumber) || ((CommonUtil.isEmpty(customerConsultId)||"null".equals(customerConsultId)) && (CommonUtil.isEmpty(consultPoolId)||"null".equals(consultPoolId)) )){
                return creditApplication;
            }
        }
			creditApplication = creditApplicationService.addOrUpdateCreditApplication(creditApplication, account,
					accountReturn);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (creditApplication != null && creditApplication.getCreditapplicationId() != null) {
			String creditapplicationId = String.valueOf(creditApplication.getCreditapplicationId());
			DESPlus desPlus = new DESPlus();
			creditApplication.setCreditapplicationDESId(desPlus.encrypt(creditapplicationId));
		}
		return creditApplication;
	}

	/**
	 * 关单撤回
	 * 
	 * @param creditApplication
	 *            小组信息对象
	 * @return 小组申请对象
	 */
	@RequestMapping(value = "updateClose")
	public @ResponseBody
	Message updateClose(CreditApplication creditApplication) {
		creditApplication.setAuditStatus(Constants.STATE_26);
		return creditApplicationService.updateClose(creditApplication);
	}

	/**
	 * 注销
	 * 
	 * @param creditApplication 申请
	 * @return Message 消息
	 */
	@RequestMapping(value = "update")
	public @ResponseBody
	Message updateCreditApplication(CreditApplication creditApplication) {
		creditApplication.setAuditStatus(Constants.STATE_7);
		return creditApplicationService.updateCreditApplication(creditApplication);
	}

	/**
	 * 撤回
	 * 
	 * @param creditApplication
	 *            小组信息对象
	 * @return 小组申请对象
	 */
	@RequestMapping(value = "updateCreditApplication")
	public @ResponseBody
	Message updateCreditApplicationById(CreditApplication creditApplication) {
		return creditApplicationService.updateCreditApplicationForAccount(creditApplication);
	}

	/**
	 * 按 小组申请id查询
	 * 
	 * @param creditapplicationId
	 *            小组申请id
	 * @param request
	 *            HttpServletRequest对象
	 * @return 一条小组申请记录
	 * @throws IOException
	 *             输入输出异常
	 */
	@RequestMapping(value = "selectCreditApplication")
	public String selectCreditApplication(@RequestParam(required = false) String creditapplicationId,
			HttpServletRequest request) throws IOException {
		CreditApplication creditApplication = new CreditApplication();
		if (creditapplicationId != null && !"".equals(creditapplicationId)) {
			int creditapplicationIdInt = Integer.valueOf(creditapplicationId);
			creditApplication.setCreditapplicationId(creditapplicationIdInt);
			creditApplication = creditApplicationService.selectCreditApplication(creditApplication);
          //根据信贷申请id查询借款人身份证号 等信息       编辑回显使用
            CreditApplication other = new CreditApplication();
            other = creditApplicationService.getIndentityInfoByCreditApplicationId(creditapplicationIdInt);
            creditApplication.setMateIdNumber(other.getMateIdNumber());
            creditApplication.setCredentialsNumber(other.getCredentialsNumber());
            try {
				DESPlus desPlus = new DESPlus();
				creditApplication.setCreditapplicationDESId(desPlus.encrypt(creditapplicationId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("creditApplicationReturn", creditApplication);
		return "/jsp/rc/business/loanApply.jsp";
	};

	/**
	 * 按 小组申请id查询
	 * 
	 * @param creditapplicationId
	 *            小组申请id
	 * @return 一条小组申请记录
	 * @throws IOException 输入输出异常
	 */
	@RequestMapping(value = "select")
	public @ResponseBody
	CreditApplication select(@RequestParam(required = false) Integer creditapplicationId) throws IOException {
		CreditApplication creditApplication = new CreditApplication();
        if(creditapplicationId != null ){
            creditApplication.setCreditapplicationId(creditapplicationId);
            creditApplication = creditApplicationService.selectCreditApplication(creditApplication);
            //根据信贷申请id查询借款人身份证号 等信息       编辑回显使用
            CreditApplication other = new CreditApplication();
            other = creditApplicationService.getIndentityInfoByCreditApplicationId(creditapplicationId);
            creditApplication.setMateIdNumber(other.getMateIdNumber());
            creditApplication.setCredentialsNumber(other.getCredentialsNumber());
        }
		return creditApplication;
	};

	/**
	 * 按 小组申请id查询
	 * 
	 * @param creditapplicationId
	 *            小组申请id
	 * @return 一条小组申请记录
	 * @throws IOException 输入输出异常
	 */
	@RequestMapping(value = "selectCreditApplicationForConfirm")
	public @ResponseBody
	CreditApplication selectCreditApplication(@RequestParam(required = false) Integer creditapplicationId)
			throws IOException {
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCreditapplicationId(Integer.valueOf(creditapplicationId));
		creditApplication = creditApplicationService.selectCreditApplicationForConfirm(creditApplication);
		return creditApplication;
	};

	/**
	 * 模糊查询小组列表
	 * 
	 * @param creditApplication 小组申请对象
	 * @param fuzzyValue 模糊查询条件
	 * @param page 第几页
	 * @param rows 每页多少行
	 * @param request http请求
	 * @return 小组申请分页列表
	 */
	@RequestMapping(value = "selectCreditApplicationList")
	public @ResponseBody
	Pagination selectCreditApplicationList(CreditApplication creditApplication, String fuzzyValue,
			@RequestParam(required = false) String page, String auditStatusArray,
			@RequestParam(required = false) String rows, HttpServletRequest request) {
		/** 获取到是不是从首页传进来的角色代码24代表营业部，25代表风险经理 **/
		String getRole = creditApplication.getRole();
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Pagination pagination1 = new Pagination();

		String auditStr = "";
		if (auditStatusArray != null && !"".equals(auditStatusArray)) {
			String[] audit = auditStatusArray.split(",");
			for (String string : audit) {
				auditStr = auditStr + "'" + string + "',";
			}
			auditStr = auditStr.substring(0, (auditStr.length() - 1));
		}

		pagination1 = creditApplicationService.selectCreditApplicationList(creditApplication, fuzzyValue, auditStr,
				pagination, request.getSession());
		// 判断业务经理审批还是风险经理审批
		if (CommonUtil.isNotEmpty(getRole)) {// 如果 传入的getRole不是空就证明他是从首页点进来的
			List list = pagination1.getRows();
			for (int i = 0; i < list.size(); i++) {
				CreditApplication c = (CreditApplication) list.get(i);
				if ("24".equals(c.getAuditStatus())) {
					int creditapplicationId = c.getCreditapplicationId();
					List<RlApplyAuditTable> applyList = rlApplyAuditTableService
							.selectApplyAuditTable(creditapplicationId);
					for (int j = 0; j < applyList.size(); j++) {
						RlApplyAuditTable applyAuditTable = applyList.get(j);
						String auditor = applyAuditTable.getExamPaerson();
						String role = applyAuditTable.getAuditType();
						if (!Constants.AUDIT_TYPE_BS.equals(role)) {
							c.setOperateFlag("25");
						}
						if (Constants.AUDIT_TYPE_BS.equals(role)) {
							if ("24".equals(getRole)) {
								list.remove(i);
								i--;
							}
						} else if (Constants.AUDIT_TYPE_FX.equals(role)) {
							if ("25".equals(getRole)) {
								list.remove(i);
								i--;
							}
						}
					}
				}
			}
		} else {
			List list = pagination1.getRows();
			for (int i = 0; i < list.size(); i++) {
				CreditApplication c = (CreditApplication) list.get(i);
				if ("24".equals(c.getAuditStatus())) {
					int creditapplicationId = c.getCreditapplicationId();
					List<RlApplyAuditTable> applyList = rlApplyAuditTableService
							.selectApplyAuditTable(creditapplicationId);
					for (int j = 0; j < applyList.size(); j++) {
						RlApplyAuditTable applyAuditTable = applyList.get(j);
						String auditor = applyAuditTable.getExamPaerson();
						String role = applyAuditTable.getAuditType();
						if (!Constants.AUDIT_TYPE_BS.equals(role)) {
							c.setOperateFlag("25");
						}
					}
				}
			}
		}

		return pagination1;
	};

	/**
	 * 模糊查询小组列表 放款列表
	 * 
	 * @param creditApplication 小组申请对象
	 * @param fuzzyValue 模糊查询条件
	 * @param page 第几页
	 * @param rows 每页多少行
	 * @param request http请求
	 * @param sort 排序字段
	 * @param order 升序降序
	 * @return 小组申请分页列表
	 */
	@RequestMapping(value = "selectCreditApplicationForLoan")
	public @ResponseBody
	Pagination selectCreditApplicationForLoan(CreditApplication creditApplication, String fuzzyValue,
			@RequestParam(required = false) String page, @RequestParam(required = false) String rows,
			HttpServletRequest request, String sort, String order) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Pagination pagination1 = new Pagination();
		pagination1 = creditApplicationService.selectCreditApplicationForLoan(creditApplication, fuzzyValue,
				pagination, request.getSession(), sort, order);
		return pagination1;
	};

	/**
	 * wyf测试放款列表普通Excel导出，获取的数据格式是List<JavaBean>
	 * 
	 * @param creditApplication 申请
	 * @param fuzzyValue 模糊条件
	 * @param request 请求
	 * @param response 回应
	 * @throws Exception 异常
	 */
	@RequestMapping(value = "exportLoanExcel")
	public void exportExcel(CreditApplication creditApplication, String fuzzyValue, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/msexcel;charset=UTF-8");
		Pagination pagination = new Pagination();
		pagination = creditApplicationService.selectCreditApplicationForLoan(creditApplication, fuzzyValue, pagination,
				request.getSession(), "", "");

		List<CreditApplication> list = pagination.getRows();// 获取数据

		String title = "放款列表";
		String[] hearders = new String[] { "业务单号", "借款人", "产品类型 ", "放款金额(元)", "服务费(元)", "实发金额(元)", "放款日期", "客户经理",
				"申请状态", "业务类型" };// 表头数组
		String[] fields = new String[] { "groupNumber", "groupName", "repaymentPlanName", "loanAmount",
				"serviceCharge", "realAmount", "signagreementDate", "loanOfficerName", "auditStatus", "businessType" };// 对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, "当前用户", td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, "当前用户", td);
    }

	/**
	 * 
	 * @param creditapplicationId 小组申请id
	 * @return true or false
	 */
	@RequestMapping(value = "delete")
	public @ResponseBody
	boolean deleteById(Integer creditapplicationId) {
		return creditApplicationService.updateValidStateOfCreditApplication(creditapplicationId);
	}

	/**
	 * (作废申请功能)删除小组申请（即更新删除小组标识） 自动判断能不能删除，如若删除是完全删除还是更新标识位
	 * 
	 * @author zhangman
	 * @param creditapplicationId
	 *            小组信贷申请id
	 * @return message
	 */
	@RequestMapping(value = "updateCancel")
	public @ResponseBody
	Message updateCancel(Integer creditapplicationId) {
		return creditApplicationService.updateCancel(creditapplicationId);
	};

	/**
	 * 拒绝或客户放弃
	 * 
	 * @author w y f
	 *            小组信贷申请id
	 * @return message
	 */
	@RequestMapping(value = "updateRefuse")
	public @ResponseBody
	Message updateRefuse(Integer caId, String flag) {
		Message msg = new Message();
		boolean result;
		try {
			result = creditApplicationService.updateRefuse(caId, flag);
			if (result) {
				msg.setMsg("操作成功");
				msg.setSuccess(true);
			} else {
				msg.setMsg("操作失败");
				msg.setSuccess(false);
			}
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};

	/**
	 * 按 小组编号查询
	 * 
	 * @param creditapplicationId
	 *            小组申请id
	 * @param groupNumber 小组编号
	 * @return message 对象
	 */
	@RequestMapping(value = "selectByGroupNumber")
	public @ResponseBody
	Message selectByGroupNumber(@RequestParam(required = false) String groupNumber, Integer creditapplicationId) {
		Message message = new Message();
		message.setSuccess(false);
		CreditApplication creditApplication = creditApplicationService.selectByGroupNumber(groupNumber);
		if (creditApplication != null && creditapplicationId != null) {
			if (creditapplicationId.equals(Integer.valueOf(creditApplication.getCreditapplicationId()))) {
				message.setSuccess(false);
			} else {
				message.setSuccess(true);
			}
		} else if (creditApplication != null && creditapplicationId == null) {
			message.setSuccess(true);
		}
		return message;
	};

	/**
	 * 验证担保人文件和申请单文件是否上传
	 * 
	 * @author wyf
	 * @Description:
	 * @param creditAppId
	 * @return Map
	 */
	@RequestMapping(value = "getImgAmountByGuaranor")
	@ResponseBody
	public Map getImgAmountByGuaranor(String creditAppId) {
		Map map = new HashMap();
		List<BorrowerServiceVo> borrowerServiceVoList = new ArrayList<BorrowerServiceVo>();
		boolean guaranorImg = true;
		try {
			if (StringUtils.isNotEmpty(creditAppId)) {
				borrowerServiceVoList = borrowerServiceAppService.quertyGuaranorList(Long.valueOf(creditAppId));
				for (BorrowerServiceVo bs : borrowerServiceVoList) {
					int count = creditApplicationService.getImgAmount(String.valueOf(bs.getBorrowerServiceAppId()));
					if (count <= 0) {
						guaranorImg = false;
					}
				}
				map.put("guaranorImg", guaranorImg);
				int imgAmount = creditApplicationService.getImgAmount(creditAppId);
				map.put("creditAppImg", imgAmount);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 上传文件数量
	 * 
	 * @author wyf
	 * @Description:
	 * @param: creditAppId
	 * @return: int int
	 */
	@RequestMapping(value = "getImgAmount")
	@ResponseBody
	public int getImgAmount(String creditAppId) {
		int imgAmount = 0;
		try {
			imgAmount = creditApplicationService.getImgAmount(creditAppId);
			return imgAmount;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 保存上传文件
	 * 
	 * @author yifengwang
	 * @param caId 小组ID
	 * @param uploadFileList 上传文件列表
	 * @param uploadFileTypeList 上传文件类型列表
	 * @param originalFileList 文件真名列表
	 * @return 2012-12-24下午02:12:41
	 */
	@RequestMapping(value = "groupAllSave")
	@ResponseBody
	public String groupAllSave(Integer caId, String[] uploadFileList, String[] uploadFileTypeList,
			String[] originalFileList) {
		String result = null;
		try {
			if (caId != null) {
				creditApplicationService.addOtherFileUpload(caId, uploadFileList, Constants.FILE_TYPE_GROUP_FILES,
						uploadFileTypeList, originalFileList);
				result = Constants.UPLOAD_STATE_SUCCESS;
			} else {
				result = Constants.UPLOAD_STATE_FAIL_NOID;
			}
			return result;
		} catch (Exception e) {
			result = Constants.UPLOAD_STATE_FAIL_ERROR;
			e.printStackTrace();
			return result;
		}
	}

	/**
	 * 查询上传文件列表
	 * 
	 * @author yifengwang
	 * @param page 页码
	 * @param rows pageSize
	 * @param caId 小组ID
	 * @return 2012-12-24下午02:14:30
	 */
	@RequestMapping(value = "queryUploadFileList")
	public @ResponseBody
	Pagination queryUploadFileList(String page, String rows, @RequestParam(required = false) Integer caId) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}

		return creditApplicationService.queryUploadFileList(caId, pagination);
	}

	/**
	 * 查询上传文件列表
	 * 
	 * @author zhangman
	 * @param caId 所属类ID
	 * @param fileType 类型
	 * @return List<OtherFileUpload> 列表
	 */
	@RequestMapping(value = "queryUploadFiles")
	public @ResponseBody
	List<OtherFileUpload> queryUploadFiles(Integer caId, String fileType) {
		return creditApplicationService.queryUploadFiles(caId, fileType);
	}

	/**
	 * 删除上传文件
	 * 
	 * @author yifengwang
	 * @param request 请求
	 * @param uploadFileName 文件名
	 * @return 2012-12-24下午02:26:21
	 */
	@RequestMapping(value = "DelUploadFile")
	@ResponseBody
	public Message delUploadFile(HttpServletRequest request, String uploadFileName) {
		Message message = new Message();
		try {

			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/config");
			String filePath = PropertiesUtil.getVlaue(realPath + "/" + Constants.PICTURECONFIG,
					Constants.PICTURESTORAGEDIRECTORY) + "/" + uploadFileName;
			String fileName = null;
			if (CommonUtil.isNotEmpty(uploadFileName)) {
				fileName = uploadFileName.substring(uploadFileName.lastIndexOf("_") + 1);
			}
			File file = new File(filePath);
			if (file.exists()) {
				boolean result = creditApplicationService.delUploadFile(file);
				if (result) {
					message.setMsg(fileName + "文件删除成功");
					message.setSuccess(true);
					message.setCode(uploadFileName);
				} else {
					message.setMsg(fileName + "文件删除失败");
					message.setSuccess(false);
					message.setCode(uploadFileName);
				}
			} else {
				message.setMsg(fileName + "文件不存在");
				message.setSuccess(false);
				message.setCode(uploadFileName);
			}
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			message.setMsg("文件删除异常");
			message.setSuccess(false);
			message.setCode(uploadFileName);
			return message;
		}
	}

	/**
	 * 下载
	 * 
	 * @author yifengwang
	 * @param request 请求
	 * @param response 响应
	 * @param fileDownName 文件名
	 * @param realName 文件实名
	 * @return null
	 * @throws Exception 2012-12-24下午02:28:18
	 */
	@RequestMapping(value = "fileDownload")
	public String download(HttpServletRequest request, HttpServletResponse response, String fileDownName,
			String realName) throws Exception {
		String contentType = "application/octet-stream";
		realName = "测试.jpg";
		UploadUtil.download(request, response, fileDownName, contentType, realName);
		return null;
	}

	/**
	 * 提交小组准备审核
	 * 
	 * @param creditApplication 小组
	 * @param creditapplicationId 小组id
	 * @param auditStatus 审核标记
	 * @return message对象
	 */
	@RequestMapping(value = "changgeAuditing")
	public @ResponseBody
	Message submitAuditing(CreditApplication creditApplication, Integer creditapplicationId, String auditStatus) {
		Message message = validateCapitalLimit(creditApplication);
        if (message.isSuccess()) {
            CreditApplication application_database = creditApplicationService.selectCreditApplication(creditApplication);
            message = creditApplicationService.updateSubmit(creditApplication, creditapplicationId, auditStatus);
            if (message.isSuccess()) {
                if (application_database.getAuditStatus().equals(Constants.STATE_18)) {
                    /*对于审批拒绝状态申请单再次编辑提交时，需要清除城市参审状态。*/
                    creditApplicationService.updateCityParticipate(Integer.valueOf(creditapplicationId), "0");
					creditApplicationService.updateCityParticipatePerman(creditapplicationId, "T");
                }
            }
        }

		return message;
	}

	/**
	 * 提交小组准备审核
	 * 
	 * @param creditApplication 小组
	 * @param creditapplicationId 小组id
	 * @param auditStatus 审核标记
	 * @return message对象
	 */
	@RequestMapping(value = "changgeCreditAuditing")
	public @ResponseBody
	Message changgeCreditAuditing(CreditApplication creditApplication, Integer creditapplicationId, String auditStatus) {
		Message message = new Message();
		boolean flag = creditApplicationService.changeAuditing(creditApplication, creditapplicationId, auditStatus);
		message.setSuccess(flag);
		if (!flag) {
			message.setMsg("提交失败");
		}
		return message;
	}

	/**
	 * 放款失败登记
	 * 
	 * @param creditApplication 小组
	 * @param creditapplicationId 小组id
	 * @param auditStatus 审核标记
	 * @return message对象
	 */
	@RequestMapping(value = "loanregistRevocation")
	public @ResponseBody
	Message loanregistRevocation(CreditApplication creditApplication, Integer creditapplicationId, String auditStatus) {
		Message message = new Message();
		message = creditApplicationService.loanregistRevocation(creditApplication, creditapplicationId, auditStatus);
		return message;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 验证借款额度是否在产品的额度范围内
	 * @return
	 * @version v1.0
	 *          2013-3-22
	 */
	public Message validateCapitalLimit(CreditApplication creditApplicationVO) {
		Message message = new Message();
		message.setSuccess(false);
		if (null != creditApplicationVO) {
			if (null != creditApplicationVO.getCreditapplicationId()) {
				CreditApplication entityApplication = this.creditApplicationService
						.selectCreditApplication(creditApplicationVO);
				if (null != entityApplication) {
					Double upperLimit = entityApplication.getCapitalUpperLimit();
					Double lowerLimit = entityApplication.getCapitalLowerLimit();
					List<BorrowerServiceApp> borrowerServiceAppList = borrowerServiceAppService
							.selectBorrowerServiceAppList(Integer.valueOf(entityApplication.getCreditapplicationId()
									.toString()));
					if (CommonUtil.isNotEmpty(borrowerServiceAppList)) {
						for (BorrowerServiceApp borrowerServiceApp : borrowerServiceAppList) {
							if ("1".equalsIgnoreCase(borrowerServiceApp.getLeader())) {
								// 申请金额
								Double applyLimit = borrowerServiceApp.getApplyLimit();
								if (null != applyLimit) {
									if (null != upperLimit && null != lowerLimit) {
										if (applyLimit >= lowerLimit && applyLimit <= upperLimit) {
											message.setSuccess(true);
										} else {
											message.setMsg("借款金额与产品不符合，产品中额度上限=" + upperLimit + ";额度下限=" + lowerLimit
													+ ";申请额度=" + applyLimit);
										}
									}

								} else {
									message.setMsg("借款申请单中申请金额为空");
								}
							}

						}

					} else {
						message.setMsg("未查询出借款申请单");
					}
				}
			} else {
				message.setMsg("入参对象属性主键为null");
			}
		} else {
			message.setMsg("入参对象为null");
		}

		return message;
	}

	/**
	 * 查询小组列表 财务放款列表
	 * 
	 * @param creditApplication 查询条件
	 * @param page 第几页
	 * @param rows 每页多少行
	 * @param session http请求
	 * @return 小组申请分页列表
	 */
	@RequestMapping(value = "selectFinanceLoan")
	public @ResponseBody
	Pagination selectCreditApplicationForFinanceLoan(CreditApplication creditApplication,
			@RequestParam(required = false) String page, @RequestParam(required = false) String rows,
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
		creditApplication.setAuthList(sqlsid);
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Pagination pagination1 = creditApplicationService.selectFinanceLoan(pagination, creditApplication);
// List<CreditApplication>list=pagination1.getRows();
// for(CreditApplication c:list){
// List<RepaymentPlan> listRepaymentPlan=repaymentPlanService.returnComboboxRepayment(c.getRepaymentPlanId(), null);
// AmountConfirm amountConfirm=amountConfirmService.selectNew(c.getCreditapplicationId());
// if(listRepaymentPlan.size()>0){
// Double examAmount=0.00;
// if(null!=amountConfirm){
// examAmount=amountConfirm.getAmount();
// }
// c.setGroupAppTotal(examAmount);
// Double fiserServiceCharge=CurrencyUtil.mul(examAmount, listRepaymentPlan.get(0).getFirstServiceFree());
// c.setServiceCharge(fiserServiceCharge);
// Double actualLoanAmount=CurrencyUtil.sub(examAmount, fiserServiceCharge);
// c.setRealAmount(actualLoanAmount);
// }
// }
		return pagination1;
	};

	/**
	 * 跳转查看页
	 * 
	 * @param creditApplicationId 信贷id
	 * @param request 请求
	 * @return 查看页面
	 */
	@RequestMapping(value = "searchLook")
	public String searchLook(Integer creditApplicationId, HttpServletRequest request) {
		//用于解决查看现金流表没有客户姓名
		cashStreamService.updateBorrowName(Long.parseLong(creditApplicationId.toString()));
		// 获取信用背景调查id liuli 2013-05-03
		String creditInvestigationId = (String) request.getParameter("creditInvestigatioId");
		String loanRegistDESId = null;
		String loanRegistUDESId = null;
		String approvalDESId = null;
		String approvalUDESId = null;
		String creditapplicationUDESId = null;
		CreditApplication creditApplication = new CreditApplication();
		RlApplyAuditTable applyAuditTable = new RlApplyAuditTable();
		AccountInfo accountInfo = new AccountInfo();
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		List<BorrowerServiceApp> borrowerServiceApps = new ArrayList<BorrowerServiceApp>();
		creditApplication = creditApplicationService.selectById(creditApplicationId);
		if (CommonUtil.isNotEmpty(creditApplication.getAuditStatus())) {
			String auditStatusShow = dataDictionaryService.getCodeValueByKey("auditStatus",
					creditApplication.getAuditStatus());
			creditApplication.setAuditStatusShow(auditStatusShow);
		}
		try {
			DESPlus desPlus = new DESPlus();
			if (creditApplicationId != null) {
				creditApplication.setCreditapplicationDESId(desPlus.encrypt(String.valueOf(creditApplicationId)));
				loanRegistDESId = desPlus.encrypt(String.valueOf(creditApplicationId) + Constants.CM_LOAN);
				loanRegistUDESId = String.valueOf(creditApplicationId) + Constants.CM_LOAN;
				approvalDESId = desPlus.encrypt(String.valueOf(creditApplicationId) + Constants.CM_EXAM);
				approvalUDESId = String.valueOf(creditApplicationId) + Constants.CM_EXAM;
				creditapplicationUDESId = String.valueOf(creditApplicationId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (creditApplication.getAccountInfoId() != null) {
			accountInfo = accountInfoService.selectByAccountID(creditApplication.getAccountInfoId());
		}
		applyAuditTable = rlApplyAuditTableService.selectLastApplyAudit(creditApplicationId);
		if (applyAuditTable != null) {
			if ("04".equals(applyAuditTable.getExamResult()) || "00".equals(applyAuditTable.getExamResult())) {
				if (CommonUtil.isNotEmpty(creditApplication.getAuditStatus())) {
					if ("04".equals(creditApplication.getAuditStatus())) {
						applyAuditTable.setExamResult("审批通过");
					} else if ("07".equals(creditApplication.getAuditStatus())
							|| "14".equals(creditApplication.getAuditStatus())) {
						applyAuditTable.setExamResult("");
					} else {
						applyAuditTable.setExamResult("");
					}
				}
			} else if ("18".equals(applyAuditTable.getExamResult()) || "11".equals(applyAuditTable.getExamResult())) {
				applyAuditTable.setExamResult("审批拒绝");
			}
		}
		borrowerServiceApps = borrowerServiceAppService.selectBorrowerServiceAppList(creditApplicationId);
		if (borrowerServiceApps.size() > 0) {
			borrowerServiceApp = borrowerServiceApps.get(0);
		}
		AmountConfirm amountConfirm = amountConfirmService.selectNew(creditApplicationId);
		request.setAttribute("creditApplicationId", creditApplicationId);
		request.setAttribute("creditapplicationUDESId", creditapplicationUDESId);
		request.setAttribute("loanRegistDESId", loanRegistDESId);
		request.setAttribute("loanRegistUDESId", loanRegistUDESId);
		request.setAttribute("approvalDESId", approvalDESId);
		request.setAttribute("approvalUDESId", approvalUDESId);
		request.setAttribute("creditApplication", creditApplication);
		request.setAttribute("accountInfo", accountInfo);
		request.setAttribute("applyAuditTable", applyAuditTable);
		request.setAttribute("borrowerServiceApp", borrowerServiceApp);
		request.setAttribute("amountConfirm", amountConfirm);
		// 增加信用背景调查id liuli 2013-05-03
		request.setAttribute("creditInvestigationId", creditInvestigationId);
		return "/jsp/rc/business/copycreditApplicationDetail.jsp";
	}

	/**
	 * 查询还款计划
	 * 
	 * @param returnPlan 还款计划类
	 * @return 还款计划列表
	 */
	@RequestMapping(value = "selectReturnPlan")
	public @ResponseBody
	List<ReturnPlan> selectReturnPlan(ReturnPlan returnPlan) {
		return returnPlanService.selectList(returnPlan);
	}

	/**
	 * 金额确认
	 * 
	 * @param amountConfirm 放款金额对象
	 * @param accountInfo accountInfo
	 * @return 消息
	 */
	@RequestMapping(value = "confirmAmount")
	public @ResponseBody
	Message confirmAmount(AmountConfirm amountConfirm, AccountInfo accountInfo) {

		/** 对最终额度确认加了 账号内存锁 **/
		Message message = new Message();

		Integer accountInfoId = accountInfo.getAccountInfoId();
		// 判断对公对私
		if (accountInfoId == null) {// 对私

			message = amountConfirmService.addAmountConfirm(amountConfirm, accountInfo);
		} else {// 对公
			// 后台通过主键查账号

			AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
			String account = accountInfoSelect.getAccount();
			if (CommonUtil.isNotEmpty(account)) {
				// 判断是否存在锁
				Boolean b = BankCardNOLock.lockBankCardNO(account);
				if (b) {
					// 获取银行卡号。加锁
					try {
						message = amountConfirmService.addAmountConfirm(amountConfirm, accountInfo);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
					} finally {
						// 解锁
						BankCardNOLock.unLockBankCardNO(account);
					}
				} else {
					throw new BusinessException("卡号：" + account + "已锁住，请等待或解锁");
				}

			}

		}

//
// if(amountConfirmResult != null){
// message.setMsg("保存成功!");
// message.setSuccess(true);
// }else{
// message.setMsg("保存失败!");
// message.setSuccess(false);
// }
		return message;
	}

	/**
	 * 查询产品列表
	 * 
	 * @return 查询产品列表
	 */
	@RequestMapping(value = "selectProInfo")
	public @ResponseBody
	List<Map> selectProInfo() {
		QueryProByDepartResult queryProByDepartResult = null;
		List<Map> list = new ArrayList<Map>();
		List<ProInfo> proInfos = null;
		List<ProductInst> productInsts = null;
		// 登录人的部门id
		int departMentId = 0;
		if (SpringSecurityUtils.getCurrentUser().getDepartmentId() != null) {
			departMentId = SpringSecurityUtils.getCurrentUser().getDepartmentId();
		} else {
			System.out.println("SpringSecurityUtils.getCurrentUser().getDepartmentId():"
					+ SpringSecurityUtils.getCurrentUser().getDepartmentId() + "当前登录人部门id是null");
		}
		log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) request_params:******");
		log.info(JsonUtil.getJackson(departMentId));
		ProListParam proListParam = new ProListParam();
		proListParam.setDepartmentId(departMentId);
		queryProByDepartResult = ceBorrowingProductsWS.queryProByDepart(proListParam);
		if (null != queryProByDepartResult) {
			log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) response_params:******");
			log.info(JsonUtil.getJackson(queryProByDepartResult));
			if ("0".equals(queryProByDepartResult.getResultCode())) {
				ProInfoList proInfoList = queryProByDepartResult.getProInfoList();
				ProInstList proInstList = queryProByDepartResult.getProInstList();

				if (null != proInfoList) {
					proInfos = proInfoList.getProInfo();
					if (proInfos.size() == 0) {
						System.out.println("proInfoList中的List<ProInfo> 长度是 0：proInfoList.getProInfoList():"
								+ proInfoList.getProInfo());
					}
					productInsts = proInstList.getProductInst();
					if (productInsts.size() == 0) {
						System.out.println("proInfoList中的List<ProductInst> 长度是 0：proInstList.getProductInst():"
								+ proInstList.getProductInst());
					}
                    //
                    Map<String,String> cateGoryIds = new HashMap<String,String>();
                    //存储产品类型大类
                    Map<Long,Map> catas = new HashMap<Long, Map>();
                    //循环产品类型大类
                    for (int i = 0; i < proInfos.size(); i++) {
                        ProInfo proInfo =  proInfos.get(i);
                        //循环产品
						for (int j = 0; j < productInsts.size(); j++) {
                              //循环产品 把产品的还款方式添加到对应的产品大类上
							if (proInfos.get(i).getProductInfoId().equals(productInsts.get(j).getProductInfoId())) {
                                if(catas.containsKey(proInfo.getProductInfoId())){
                                    //   大类已经包含 循环拼接出期数   还款方式
                                    Map map = catas.get(proInfo.getProductInfoId());
                                    String   repaymentTypeStr = (String)map.get("repaymentType");
                                    repaymentTypeStr += "," +  productInsts.get(j).getRepaymentType();
                                    map.remove("repaymentType");
                                    map.put("repaymentType",repaymentTypeStr);

                                    catas.remove(proInfo.getProductInfoId())  ;
                                    catas.put(proInfo.getProductInfoId(),map);
                                }else{
                                    //大类未包含 存入数据里面
                                    Map product = new HashMap();
                                    product.put("productInfoId", proInfo.getProductInfoId());
                                    product.put("productName", proInfo.getProductName());
                                    product.put("instalments", proInfo.getInstalments());
                                    product.put("producttypeid", proInfo.getProductCategoryId()); // 产品分类编号
                                    product.put("repaymentType", productInsts.get(j).getDefaultRepaymentType());
                                    product.put("capitalUpperLimit", convertBigDecimal(proInfo
                                            .getCapitalUpperLimit()));
                                    product.put("capitalLowerLimit", convertBigDecimal(proInfo
                                            .getCapitalLowerLimit()));
                                    catas.put(proInfo.getProductInfoId(),product);
                                    //list.add(product);
                                }
							}
						}
					}
                    //循环产品大类 放入返回列表
                    for(Map.Entry<Long,Map> entry:catas.entrySet()) {
                        list.add(entry.getValue());
                    }
                  //  System.out.println(list.toString());
				} else {
					System.out.println("proInfoList 是 null：proInfoList:" + proInfoList);
				}
			} else {
				System.out.println("产品返回接口返回结果失败：queryProByDepartResult.getResultCode():"
						+ queryProByDepartResult.getResultCode());
			}
		} else {
			System.out.println("产品返回接口返回数据是null：ceBorrowingProductsWS.queryProByDepart:"
                    + ceBorrowingProductsWS.queryProByDepart(proListParam));
		}
		return list;
	};

	/**
	 * 
	 * @author 韩大年
	 * @Description: BigDecimal转换字符串
	 * @param decimal
	 * @return String
	 * @version v1.0
	 *          2013-3-22
	 */
	public String convertBigDecimal(BigDecimal decimal) {
		StringBuffer value = new StringBuffer();
		if (null == decimal) {
			value.append("0");
		} else {
			value.append(decimal.toString());
		}
		return value.toString();
	}

	/**
	 * 查询放款确认金额
	 * 
	 * @param creditapplicationId 信贷申请单id
	 * @return AmountConfirmVo
	 */
	@RequestMapping(value = "selectAmount")
	public @ResponseBody
	AmountConfirmVo selectAmountConfirm(Integer creditapplicationId) {
		return amountConfirmService.selectAmount(creditapplicationId);
	}

	/**
	 * @author manzhang
	 * @param familymember 家庭成员
	 * @return 配偶信息
	 */
	@RequestMapping(value = "selectPartner")
	public @ResponseBody
	Familymember selectPartner(Familymember familymember) {
		return borrowerServiceAppService.selectPartner(familymember);
	}
    /**
     * @author manzhang
     * @return 配偶信息
     */
    @RequestMapping(value = "selectCoBorrower")
    public @ResponseBody
    CreditCoBorrower selectCoBorrower(CreditCoBorrower creditCoBorrower  ) throws Exception{
        return     (CreditCoBorrower)creditCoBorrowerService.queryCreditCoBorrowerInfoByBorrowerServiceAppId(String.valueOf(creditCoBorrower.getBorrowerServiceAppId()))  ;
    }
	/**
	 * 按客户咨询查询信贷申请
	 * 
	 * @param customerConsultId 咨询id
	 * @return 申请列表
	 */
	@RequestMapping(value = "selectCreditByconsult")
	public @ResponseBody
	List<CreditApplication> selectCreditByconsult(Long customerConsultId) {
		return creditApplicationService.selectCreditByconsult(customerConsultId);
	};

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param creditapplicationId 申请id
	 * @return 姓名 身份证map
	 */
	@RequestMapping(value = "selectBorrowerFamily")
	public @ResponseBody
	List<BorrowerServiceVo> selectBorrowerFamily(Integer creditapplicationId) {
		List<BorrowerServiceVo> borrowerServiceVos = new ArrayList<BorrowerServiceVo>();
		if (creditapplicationId != null) {
			borrowerServiceVos = creditApplicationService.selectBorrowerFamily(creditapplicationId);
		}
		return borrowerServiceVos;
	};

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param accountInfoId 申请id
	 * @return 姓名 身份证map
	 */
	@RequestMapping(value = "selectBorrowerFamilyByAccount")
	public @ResponseBody
	List<BorrowerServiceVo> selectBorrowerFamilyByAccount(Integer accountInfoId) {
		List<BorrowerServiceVo> borrowerServiceVos = new ArrayList<BorrowerServiceVo>();
		if (accountInfoId != null) {
			borrowerServiceVos = creditApplicationService.selectBorrowerFamilyByAccount(accountInfoId);
		}
		return borrowerServiceVos;
	};

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param credentialsNumber 身份证
	 * @return 姓名 身份证map
	 */
	@RequestMapping(value = "selectBorrowerFamilyByID")
	public @ResponseBody
	List<BorrowerServiceVo> selectBorrowerFamilyByID(String credentialsNumber) {
		List<BorrowerServiceVo> borrowerServiceVos = new ArrayList<BorrowerServiceVo>();
		if (credentialsNumber != null) {
			borrowerServiceVos = creditApplicationService.selectBorrowerFamilyByID(credentialsNumber);
		}
		return borrowerServiceVos;
	};

	/**
	 * SMP同步
	 * 
	 * @param application CreditApplication
	 * @return Message
	 */
	@RequestMapping(value = "updateSmp")
	public @ResponseBody
	Message updateSmp(CreditApplication application) {
		return creditApplicationService.updateSmp(application);
	}

	/**
	 * SMP同步
	 * 
	 * @return Message
	 */
	@RequestMapping(value = "updateSmpAll")
	public @ResponseBody
	Message updateSmpAll() {
		return creditApplicationService.updateSmpAll();
	}

	@RequestMapping(value = "selectCreditValidByconsult")
	public @ResponseBody
	Message selectCreditValidByconsult(Long customerConsultId) {
		Message message = new Message();
		List<CreditApplication> creditApplications = creditApplicationService.selectCreditByconsult(customerConsultId);
		if (creditApplications.size() > 0) {
			String flag = creditApplications.get(0).getValidState();
			if ("0".equals(flag)) {
				message.setMsg("申请单已删除或被作废");

			} else {
				message.setSuccess(true);
			}
		} else {
			message.setMsg("申请单已经不存在");
		}
		return message;
	}

	/**
	 * 按咨询池查询信贷申请
	 * @author hongjieluo
	 * @param consultPoolId 咨询池id
	 * @return 申请列表
	 */
	@RequestMapping(value = "selectCreditValidByconsultPool")
	public @ResponseBody
	Message selectCreditValidByconsultPool(Long consultPoolId) {
		Message message = new Message();
		List<CreditApplication> creditApplications = creditApplicationService.selectCreditByconsultPool(consultPoolId);
		if (creditApplications.size() > 0) {
			String flag = creditApplications.get(0).getValidState();
			if ("0".equals(flag)) {
				message.setMsg("申请单已删除或被作废");

			} else {
				message.setSuccess(true);
			}
		} else {
			message.setMsg("申请单已经不存在");
		}
		return message;
	}
	
	@RequestMapping(value = "creditApplicationSearch")
	public @ResponseBody
	Pagination creditApplicationSearch(String name, String identityNumber, String page, String rows, String telNumber,
			String sign) {
		Pagination pagination = new Pagination();
		int startRow = 0;
		int pageSize = 0;
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
			startRow = (Integer.valueOf(page) - 1) * Integer.valueOf(rows) + 1;
			pageSize = Integer.valueOf(page) * Integer.valueOf(rows);
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		List<Map> list = new ArrayList<Map>();
// sign 是‘1’ 担保人
		if (telNumber != null && !"".equals(telNumber) && sign != null && "1".equals(sign)) {
			List<CreditApplicationSearch> applicationSearchs = borrowerServiceAppService
					.selectBorrowerServiceByTel(telNumber);
			List<CreditApplicationSearch> applicationSearchs2 = new ArrayList<CreditApplicationSearch>();
			if (pageSize != 0) {
				for (int i = startRow; i <= pageSize; i++) {
					if (applicationSearchs.size() >= i) {
						applicationSearchs2.add(applicationSearchs.get(i - 1));
					}
				}
			} else {
				applicationSearchs2 = applicationSearchs;
			}
			pagination.setItems(applicationSearchs2);
			pagination.setPageSize(applicationSearchs.size());
			pagination.setTotal(applicationSearchs.size());
		}
// sign 是‘0’ 借款人
		if (telNumber != null && !"".equals(telNumber) && sign != null && "0".equals(sign)) {
			List<CreditApplicationSearch> applicationSearchs = borrowerServiceAppService
					.selectBorrowerAuditListByTel(telNumber);
			List<CreditApplicationSearch> applicationSearchs2 = new ArrayList<CreditApplicationSearch>();
			if (pageSize != 0) {
				for (int i = startRow; i <= pageSize; i++) {
					if (applicationSearchs.size() >= i) {
						applicationSearchs2.add(applicationSearchs.get(i - 1));
					}
				}
			} else {
				applicationSearchs2 = applicationSearchs;
			}
			pagination.setItems(applicationSearchs2);
			pagination.setPageSize(applicationSearchs.size());
			pagination.setTotal(applicationSearchs.size());
		}
		if ((name != null && !"".equals(name.trim())) || (identityNumber != null && !"".equals(identityNumber.trim()))) {
			pagination = creditApplicationService.selectCreditApplicationSearch(pagination, name.trim(),
					identityNumber.trim());
		}
		return pagination;
	}

	/**
	 * 查询小组列表 财务放款列表 导出excel
	 * 
	 * @param creditApplication
	 *            查询条件
	 *            第几页
	 *            每页多少行
	 * @param session
	 *            http请求
	 * @return 小组申请分页列表
	 */
	@RequestMapping(value = "exportCreditApplicationForFinanceLoan")
	public void exportCreditApplicationForFinanceLoan(CreditApplication creditApplication, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

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
		creditApplication.setAuthList(sqlsid);
		Pagination pagination = new Pagination();
		pagination.setPageSize(0);
		pagination = creditApplicationService.selectFinanceLoan(pagination, creditApplication);

		List<CreditApplication> list = pagination.getRows();// 获取数据
		String title = "财务付款报表";
		String[] hearders = new String[] { "业务单号", "借款人姓名", "所在分公司", "借款金额", "服务费", "实发金额", "放款日期", "客户经理", "订单号",
				"财务状态" };// 表头数组
		String[] fields = new String[] { "groupNumber", "groupName", "companyName", "groupAppTotal", "serviceCharge",
				"realAmount", "signagreementDate", "loanOfficerName", "bizId", "loansStatusShow" };// 对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
    };

	@RequestMapping(value = "checkBorrowerSave")
	public @ResponseBody Message checkBorrowerSave(Long creditapplicationId) {
		Message message = new Message();
		message = borrowerServiceAppService.checkBorrowerSave(creditapplicationId);
		return message;

	}

    /**
     * 获取全部产品列表，无角色限制。
     *
     * @return 查询产品列表
     */
    @RequestMapping(value = "selectProInfoAll")
    public
    @ResponseBody
    List<Map> selectProInfoAll(HttpSession session) {
        List<Map> list = new ArrayList<Map>();
        Map<String, String> productFinal;
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
        List productList = creditApplicationService.selectProInfoAll(sqlsid);
        if (!productList.isEmpty()) {
            for (Iterator iterator1 = productList.iterator(); iterator1.hasNext(); ) {
                Map<String, String> productMap = (Map<String, String>) iterator1.next();
                Set<Map.Entry<String, String>> set = productMap.entrySet();
                for (Iterator<Map.Entry<String, String>> iterator2 = set.iterator(); iterator2.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator2.next();
                    productFinal = new HashMap<String, String>();
                    productFinal.put("productName", entry.getValue());
                    list.add(productFinal);
                }
            }
        } else {
            productFinal = new HashMap<String, String>();
            productFinal.put("productName", "产品类型初始化发生错误！");
            list.add(productFinal);
        }
        return list;
    }
}

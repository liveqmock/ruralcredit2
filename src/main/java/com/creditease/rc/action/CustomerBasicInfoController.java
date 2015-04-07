package com.creditease.rc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.CustomerInfoVO;
import com.creditease.rc.vo.EasyUITree;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.common.Constants;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.ICustomerBasicInfoService;
import com.creditease.rc.service.IFirstSystemService;
import com.creditease.rc.vo.CustomerBasicInfoVo;

/**
 * @author: zhangman
 * @version: v2.0
 */
@Controller
@RequestMapping("customer")
public class CustomerBasicInfoController {
	@Resource
	private ICustomerBasicInfoService customerService;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	@Resource
	private IFirstSystemService firstSystemService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;
	/**
	 * * @param customer
	 * 客户基本信息类
	 * 
	 * @param page
	 *            第几页
	 * @param rows
	 *            每页显示几行
	 * 
	 * @param fuzzyValue 模糊条件
	 * @return 客户基本信息分页显示
	 */
	@RequestMapping(value = "list")
	public @ResponseBody
	Pagination listCustomers(CustomerBasicInfo customer, String fuzzyValue,
			@RequestParam(required = false) String page, @RequestParam(required = false) String rows) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}

		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Pagination customerss = new Pagination();
		customerss = customerService.listCustomerBasicInfos(customer, fuzzyValue, pagination);
		return customerss;
	}

	/**
	 * 
	 * @param q
	 *            身份证号码 easyui——combobox 的idfield值
	 * @param creditapplicationId 申请id
	 * @return 客户基本信息vo类列表
	 */
	@RequestMapping(value = "listForCombo")
	public @ResponseBody
	List<CustomerBasicInfoVo> listCustomers(String q, Long creditapplicationId) {
		List<CustomerBasicInfoVo> customers = new ArrayList<CustomerBasicInfoVo>();
		if (q != null) {
			q = q.trim();
			// wyf检查一期是否you借款
			try {
				Message undone = firstSystemService.queryByUndone(q.trim());
				if (!undone.isSuccess()) {
					CustomerBasicInfoVo firstBasicInfoVo = new CustomerBasicInfoVo();
					firstBasicInfoVo.setCustomerType(Constants.CUSTOME_RTYPE_FIRSTDO);
					firstBasicInfoVo.setAuditStatusShow(undone.getMsg());
					customers.add(firstBasicInfoVo);
					return customers;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (q.trim().length() == 18) {
				q = q.toUpperCase();
				;
				customers = customerService.selectCustomerBasicInfo(q.trim(), creditapplicationId);
			}
		}
		return customers;
	}

    /**
     *信贷申请前身份证验证
     *     根据身份证id查询身份信息
     *     *
     * @param identityNumber   身份证号码
     *
     * @return 客户基本信息vo类列表
     */
    @RequestMapping(value = "getIdentityListInfo")
    public @ResponseBody
    List<CustomerBasicInfoVo> getIdentityListInfo(String identityNumber) throws Exception{
        List<CustomerBasicInfoVo> customers = new ArrayList<CustomerBasicInfoVo>();
        if (identityNumber != null && !"".equals(identityNumber)) {
            customers = customerService.getIdentityListInfo(identityNumber.trim());
        }
        return customers;
    }

    /**
     *根据身份证号查询担保人身份信息
     *   2014-09-25   add by ygx
     * @param q
     * @return  List<CustomerBasicInfoVo>
     *
     */
    @RequestMapping(value = "getGuarnorIdentityListInfo")
    public @ResponseBody
    List<CustomerBasicInfoVo> guaranorComboGrid(String q) throws Exception{
        List<CustomerBasicInfoVo> customers = new ArrayList<CustomerBasicInfoVo>();
        String tempQ = null;
        String identityNumber = null;
        if (q != null && !"".equals(q)) {
            identityNumber = q.trim();
            if(identityNumber != null && !"".equals(identityNumber) && identityNumber.length() == 18) {
                customers = customerService.getIdentityListInfo(identityNumber.trim());
            }
        }
        return customers;
    }
	@RequestMapping(value = "getCustomerInfoListByCreditApplicationID")
	public @ResponseBody
	List<EasyUITree> getCustomerInfoListByCreditApplicationID(Long creditapplicationId) throws Exception{
		List<CustomerInfoVO> customers = new ArrayList<CustomerInfoVO>();
		if (creditapplicationId != null && !"".equals(creditapplicationId)) {
			customers = customerService.getCustomerInfoListByCreditApplicationID(creditapplicationId);
		}
		List<EasyUITree> trees = new ArrayList<EasyUITree>();
		for(CustomerInfoVO customerInfoVO:customers){
			EasyUITree node = new EasyUITree();
			node.setId(customerInfoVO.getType());
			node.setText(customerInfoVO.getName());
			trees.add(node);
		}
		return trees;
	}



    /**身份验证通过后跳转到信贷申请页面
     *
     * * @author ygx 2014-09-16
     * @return 身份证验证页面
     */
    @RequestMapping(value="goToCreditApplication")
    public String toCreditApplication(Long customerConsultId,HttpServletRequest request,Long consultPoolId,String borrowerIdNumber,String mateIdNumber){
        CreditApplication creditApplicationReturn = new CreditApplication();
        creditApplicationReturn.setCustomerConsultId(customerConsultId);
        creditApplicationReturn.setConsultPoolId(consultPoolId);
        creditApplicationReturn.setCredentialsNumber(borrowerIdNumber);
        creditApplicationReturn.setMateIdNumber(mateIdNumber);
        request.setAttribute("creditApplicationReturn", creditApplicationReturn);
        return "/jsp/rc/business/loanApply.jsp";
    }
    /**
     * 跳转到身份证验证页面
     * @author ygx 2014-09-16
     * @return 身份证验证页面
     */
    @RequestMapping(value="validateIndentityNumber")
    public String validateIndentityNumber(String customerConsultId,String consultPoolId,HttpServletRequest request){
        request.setAttribute("customerConsultId",customerConsultId);   //客户咨询id
        request.setAttribute("consultPoolId",consultPoolId);           //营销咨询id
        return "/jsp/rc/business/validateIndentityNumber.jsp";
    }

	/**
	 * 
	 * @param customer
	 *            客户基本信息类
	 * @return message 信息类
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	Message addCustomer(CustomerBasicInfo customer) {
		boolean isSuccess = customerService.addCustomerBasicInfo(customer);
		Message message = new Message();
		message.setSuccess(isSuccess);
		return message;

	}

	/**
	 * 
	 * @param customer
	 *            客户基本信息类
	 * @return message信息类对象
	 */
	@RequestMapping(value = "update")
	public @ResponseBody
	Message updateCustomer(CustomerBasicInfo customer) {
		boolean isSuccess = customerService.updateCustomerBasicInfo(customer);
		Message message = new Message();
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * 
	 * @param customerIds
	 *            客户基本信息id
	 * @return message对象
	 */

	@RequestMapping(value = "delete")
	public @ResponseBody
	Message deleteCustomer(@RequestParam(required = false) String customerIds) {
		String customerid[] = customerIds.split(",");
		boolean isSuccess = true;
		for (int i = 0; i < customerid.length; i++) {
			isSuccess = customerService.deleteCustomerBasicInfoById(Integer.valueOf(customerid[i]));
		}
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
			message.setMsg("正在使用中，不能删除！");
		}
		return message;
	}

	/**
	 * 按身份证号 和 姓名查询该客户是否存在
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param name
	 *            姓名
	 * @return message对象
	 */
	@RequestMapping(value = "selectByCredentials")
	public @ResponseBody
	Message selectByCredentialsNumber(@RequestParam(required = false) String credentialsNumber, String name) {
		Message message = new Message();
		CustomerBasicInfo cunstomerBasicInfo = new CustomerBasicInfo();
		cunstomerBasicInfo.setCredentialsNumber(credentialsNumber);
		CustomerBasicInfo cuntomerResult = customerService.selectCustomerBasicInfo(cunstomerBasicInfo);
		if (cuntomerResult != null) {
			if (name.equals(cuntomerResult.getName())) {
				message.setSuccess(true);
			} else {
				message.setSuccess(false);
			}
		} else {
			message.setSuccess(true);
		}
		return message;
	}

	/**
	 * 按身份证号 和 姓名查询该客户是否存在
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param creditapplicationId
	 *            小组申请id
	 * @param request
	 *            HttpServletRequest对象
	 * @return 入户调查一页面
	 */
	@RequestMapping(value = "selectByCredentialsNumber")
	public @ResponseBody
	int selectByCredentialsNumber1(String credentialsNumber, String creditapplicationId, HttpServletRequest request) {
		CustomerBasicInfo cuntomerResult = customerService.selectByNumber(credentialsNumber);
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		if (cuntomerResult != null) {

			borrowerServiceApp.setName(cuntomerResult.getName());
			borrowerServiceApp.setGender(cuntomerResult.getGender());
			borrowerServiceApp.setMobilephone(cuntomerResult.getTelephone());
			borrowerServiceApp.setFormer(cuntomerResult.getFormer());
			borrowerServiceApp.setCredentialsNumber(cuntomerResult.getCredentialsNumber());
			borrowerServiceApp.setResidenceAddress(cuntomerResult.getResidenceAddress());
			borrowerServiceApp.setCustomerBasicId(cuntomerResult.getCustomerBasicId());
			borrowerServiceApp.setNational(cuntomerResult.getNational());
		}
		// 里面有判断账户、客户信息、填借款人姓名
		int borrowerServiceAppId = borrowerServiceAppService.addBorrowerServiceApp(borrowerServiceApp);

		return borrowerServiceAppId;
	}

	/**
	 * 新增担保人信息
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param creditapplicationId
	 *            信贷申请Id
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "addNewGuaranor")
	public @ResponseBody
	boolean addNewGuaranor(String credentialsNumber, Long creditapplicationId) {
		CustomerBasicInfo cuntomerResult = customerService.selectByNumber(credentialsNumber);
		BorrowerService borrowerService = new BorrowerService();
		borrowerService.setCreditapplicationId(creditapplicationId);
		borrowerService.setCredentialsNumber(credentialsNumber);
		String birthday = credentialsNumber.substring(6, 10) + "-" + credentialsNumber.substring(10, 12) + "-"
				+ credentialsNumber.substring(12, 14);
		borrowerService.setBirthday(birthday);

		if (cuntomerResult != null) {
			borrowerService.setName(cuntomerResult.getName());
			borrowerService.setGender(cuntomerResult.getGender());
			borrowerService.setNationality(cuntomerResult.getNational());
			borrowerService.setMobilephone(cuntomerResult.getMobilephone());
			borrowerService.setOfficePhone(cuntomerResult.getTelephone());
			borrowerService.setFormer(cuntomerResult.getFormer());
			borrowerService.setMaritalStatus(cuntomerResult.getMaritalStatus());
			borrowerService.setCredentialsNumber(cuntomerResult.getCredentialsNumber());
			borrowerService.setHomeAddress(cuntomerResult.getPresentAddress());

			if (cuntomerResult.getVallageId() != null && !"".equals(cuntomerResult.getVallageId())) {
				borrowerService.setVillageId(cuntomerResult.getVallageId().longValue());
			}
			if (cuntomerResult.getCustomerBasicId() != null && !"".equals(cuntomerResult.getCustomerBasicId())) {
				borrowerService.setCustomerBasicId(cuntomerResult.getCustomerBasicId().longValue());
			}
		}

		int l = credentialsNumber.length();
		if (l == 15) {
			if (Integer.parseInt(credentialsNumber.substring(14, 15)) % 2 == 0) {
				// 女
				// return '1';
				borrowerService.setGender("1");
			} else {
				// 男
				// return '0';
				borrowerService.setGender("0");
			}
		} else if (l == 18) {
			if (Integer.parseInt(credentialsNumber.substring(16, 17)) % 2 == 0) {
				// 女
				// return '1';
				borrowerService.setGender("1");
			} else {
				// 男
				// return '0';
				borrowerService.setGender("0");
			}
		}

		return borrowerServiceAppService.addNewGuaranor(borrowerService);

	}

	/**
	 * 按身份证号 和 姓名查询该客户是否存在
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param creditapplicationId
	 *            小组申请id
	 * @param customerConsultId
	 *            借款人咨询id
	 * @return null
	 * 
	 * 
	 */
	@RequestMapping(value = "selectByNumberNew")
	public @ResponseBody
	Message selectByNumberNew(String credentialsNumber, String creditapplicationId,Long consultPoolId, Long customerConsultId,
			Long borrowerServiceAppId) throws Exception{
		// 郝强将addNewBorrower方法封装在addNewBorrowerIncludeRevolvingAndDiscount
		// 2014.5.7又修改回去了
		//罗红杰加了一个consultPoolId参数  2014.5.26
		return customerService.addNewBorrower(credentialsNumber, creditapplicationId,consultPoolId, customerConsultId,
				borrowerServiceAppId);
// return customerService.addNewBorrowerIncludeRevolvingAndDiscount(credentialsNumber, creditapplicationId, customerConsultId,
// borrowerServiceAppId);
	}

	/**
	 * 验证担保人配偶的身份证号码
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param creditapplicationId
	 *            小组申请id
	 */
	@RequestMapping(value = "checkForCredentialsNumber")
	public @ResponseBody
	Message checkForCredentialsNumber(String credentialsNumber, Long creditapplicationId) {
		return customerService.checkForCredentialsNumber(credentialsNumber, creditapplicationId);
	}

	/**
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param customerBasicId
	 *            客户基本信息id
	 * @return 客户基本信息类
	 */
	@RequestMapping(value = "validateCredentialsNumber")
	public @ResponseBody
	CustomerBasicInfo validateCredentialsNumber(String credentialsNumber, Integer customerBasicId) {
		CustomerBasicInfo customerBasicInfo = customerService.selectByNumber(credentialsNumber);
		if (customerBasicInfo != null && customerBasicId != null) {
			if (customerBasicInfo.getCustomerBasicId().equals(customerBasicId)) {
				return null;
			} else {
				return customerBasicInfo;
			}
		} else {
			return customerBasicInfo;
		}
	}

	/**
	 * /**
	 * 导出借款人详细信息
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @throws Exception 异常
	 */
	@RequestMapping(value = "exportExcelBorrower")
	public void exportExcelBorrower(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/msexcel;charset=UTF-8");
		List<HashMap> list = borrowerServiceAppService.exportExcelBorrower();
		String title = "客户基本信息列表";
		String[] hearder = new String[] { "客户基本信息", "配偶信息" };
		String[][] hearders = new String[][] {
				{ " 业务编号  ", "所属公司", " 申请日期  ", "  民族      ", "申请金额(元)", "  身份证号       ", "姓名", "曾用名", "出生日期", "性别",
						"婚姻状况", "就业状况", "家庭住址分类-自有商品房", "家庭住址分类-自有宅基地", "家庭住址分类-租住", "家庭住址分类-亲戚住房", "家庭住址分类-其他 ",
						"家庭住址分类-租期", "  家庭住址      ", "  户籍地址       ", "家庭住址面积（m²）", "家庭住址居住年限", "经营场所分类-自有商品房",
						"经营场所分类-自有宅基地", "经营场所分类-租住 ", "经营场所分类-亲戚住房", "经营场所分类-其他", "经营场所分类-租期", "  经营场所住址       ",
						"经营场所面积（m²）", "经营场所使用年限", }, { " 姓名   ", " 身份证号  ", "年龄", "  职业     " } };// 表头数组
		String[] fields = new String[] { "BUSINESS_NUMBER", "COMPANY_NAME", "APPLY_DATE", "NATIONALITY", "APPLY_LIMIT",
				"CREDENTIALS_NUMBER", "NAME", "FORMER", "BIRTHDAY", "GENDER", "MARITAL_STATUS", "JOB_STATUS",
				"LIVING_COMMERCIAL", "LIVING_SELF", "LIVING_RENT", "LIVING_RELATIVE", "LIVING_OTHER", "LIVING_DATE",
				"HOME_ADDRESS", "HOUSEHOLD_ADDRESS", "LIVING_AREA", "LIVING_YEAR", "BUSINESS_COMMERCIAL",
				"BUSINESS_SELF", "BUSINESS_RENT", "BUSINESS_RELATIVE", "BUSINESS_OTHER", "BUSINESS_DATE",
				"RESIDENCE_ADDRESS", "BUSINESS_AREA", "BUSINESS_YEAR", "FNAME", "IDNUMBER", "FAGE", "PROFESSION" };// 对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearder, hearders), fields);
		//JsGridReportBase report = new JsGridReportBase(request, response);
		//report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);


	}
}

package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.BorrowerServiceAppVo;
import com.creditease.rc.vo.BorrowerServiceVo;
import com.creditease.rc.vo.CreditApplicationSearch;

/**
 * 
 * @author zhangman
 * 
 */
@Controller
@RequestMapping(value = "borrowerServiceApp")
public class BorrowerServiceAppController {
	/**
	 * 
	 * @param binder
	 *            格式转化类
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private ICreditApplicationService creditApplicationService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @Resource
	private IBorrowerServiceAppService borrowerServiceAppService;

	/**
	 * 添加或修改
	 * 
	 * @param borrowerServiceAppVo
	 * @return 借款服务申请id
	 */
	@RequestMapping(value = "addOrUpdate")
	public @ResponseBody
	int addOrUpdateBorrowerServiceApp(BorrowerServiceAppVo borrowerServiceAppVo) {
		int borrowerServiceAppId = borrowerServiceAppService.addBorrowerServiceApp(borrowerServiceAppVo
				.getBorrowerServiceApp());
		return borrowerServiceAppId;
	};

	/**
	 * 按小组申请id 查询该组 借款人列表 (废弃方法)
	 * 
	 * @param creditapplicationId
	 *            小组申请id
	 * @return 借款人列表
	 */
	@RequestMapping(value = "selectByCreditId")
	public @ResponseBody
	List<BorrowerServiceApp> selectBorrowerServiceAppList(Integer creditapplicationId) {
		return borrowerServiceAppService.selectBorrowerServiceAppList(creditapplicationId);

	};

	/**
	 * 按借款服务申请 id 查询
	 * 
	 * @param borrowerServiceAppId
	 *            借款服务申请 id
	 * @return 借款服务申请记录
	 */
	@RequestMapping(value = "select")
	public @ResponseBody
	BorrowerServiceAppVo selectByBorrowerServiceAppId(@RequestParam(required = false) String borrowerServiceAppId) {
		BorrowerServiceAppVo borrowerServiceAppVo = new BorrowerServiceAppVo();
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		int borrowerServiceAppIdInt = 0;
		if (borrowerServiceAppId != null && !"".equals(borrowerServiceAppId)) {
			borrowerServiceAppIdInt = Integer.valueOf(borrowerServiceAppId);
			borrowerServiceApp = borrowerServiceAppService.selectByBorrowerServiceAppId(borrowerServiceAppIdInt);
			borrowerServiceAppVo.setBorrowerServiceApp(borrowerServiceApp);
			return borrowerServiceAppVo;
		} else {
			return null;
		}

	};

	/**
	 * 按id 删除
	 * 
	 * @param borrowerServiceAppId
	 * @return message
	 */
	@RequestMapping(value = "deleteById")
	public @ResponseBody
	Message deleteBorrowerServiceApp(Integer borrowerServiceAppId) {
		boolean flag = false;
		flag = borrowerServiceAppService.updateValidStateOfBorrowerServiceApp(borrowerServiceAppId);
		Message message = new Message();
		message.setSuccess(flag);
		return message;
	};

	/**
	 * 跳到入户一
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param creditapplicationId
	 *            小组id
	 * @param request
	 *            HttpServletRequest对象
	 * @return 入户调查表一
	 */
	@RequestMapping(value = "selectByCredentialsNumber")
	public String gotoHouseholdSurveyFirst(@RequestParam(required = false) String credentialsNumber,
			String creditapplicationId, HttpServletRequest request) {
		request.setAttribute("creditapplicationId", creditapplicationId);
		return "/jsp/rc/business/householdSurveyFirst.jsp";
	}

	/**
	 * 
	 * @param borrowerServiceAppId
	 *            借款服务id
	 * @param creditapplicationId
	 *            小组
	 * @param request
	 *            HttpServletRequest对象
	 * @return 入户调查一
	 */
	@RequestMapping(value = "showUpdate")
	public String showUpdate(String borrowerServiceAppId, String creditapplicationId, HttpServletRequest request) {
		CreditApplication creditApplication = new CreditApplication();
		if (creditapplicationId != null && !"".equals(creditapplicationId)) {
			int creditapplicationIdInt = Integer.valueOf(creditapplicationId);
			creditApplication = creditApplicationService.selectById(creditapplicationIdInt);
		}
		// 根据村编号查找乡镇村信息

		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		borrowerServiceApp.setBorrowerServiceAppId(Integer.valueOf(borrowerServiceAppId));
		request.setAttribute("creditApplication", creditApplication);
		request.setAttribute("borrowerServiceApp", borrowerServiceApp);
		return "/jsp/rc/business/householdSurveyFirstShow.jsp";
	}

	/**
	 * 跳转到联系人修改页面
	 * 
	 * @param borrowerServiceAppId
	 *            借款服务申请id
	 * @param creditApplicationId
	 *            小组申请id
	 * @param request
	 *            HttpServletRequest对象
	 * @return 调查人页面
	 */
	@RequestMapping(value = "showApplicationUpdate3")
	public String showApplicationUpdate3(String borrowerServiceAppId, String creditApplicationId,
			HttpServletRequest request) {
		request.setAttribute("borrowerServiceAppId", borrowerServiceAppId);
		request.setAttribute("creditApplicationId", creditApplicationId);
		return "/jsp/rc/business/contactSurvey.jsp";
	}

	/**
	 * 跳转到入户二 添加页面
	 * 
	 * @param borrowerServiceAppId
	 *            借款服务申请id
	 * @param creditApplicationId
	 *            小组申请id
	 * @param request
	 *            HttpServletRequest对象
	 * @return 入户调查二
	 */
	@RequestMapping(value = "showApplicationUpdate2")
	public String showApplicationAdd2(String borrowerServiceAppId, String creditApplicationId,
			HttpServletRequest request) {
		request.setAttribute("borrowerServiceAppId", borrowerServiceAppId);
		request.setAttribute("creditApplicationId", creditApplicationId);
		return "/jsp/rc/business/householdSurveySecond.jsp";
	}

	/**
	 * 
	 * @param creditapplicationId
	 *            小组申请id
	 * @return 借款情况list
	 */
	@RequestMapping(value = "borrowServiceAppList")
	public @ResponseBody
	List<BorrowerInfoVo> selectBorrowerServiceApp(Integer creditapplicationId) {
		List<BorrowerInfoVo> borrowerInfoVoList = new ArrayList<BorrowerInfoVo>();
		if (creditapplicationId != null) {
			borrowerInfoVoList = borrowerServiceAppService.selectBorrowSerivceApp(creditapplicationId);
		}
		return borrowerInfoVoList;
	}

	/**
	 * 担保人列表
	 * 
	 * @param borrowerServiceVo
	 * @return List<BorrowerServiceVo>
	 * @throws Exception
	 */
	@RequestMapping(value = "guaranorList")
	public @ResponseBody
	List<BorrowerServiceVo> guaranorList(BorrowerServiceVo borrowerServiceVo) throws Exception {
		Long creditapplicationId = borrowerServiceVo.getCreditapplicationId();
		List<BorrowerServiceVo> borrowerServiceVoList = new ArrayList<BorrowerServiceVo>();
		if (creditapplicationId != null) {
			borrowerServiceVoList = borrowerServiceAppService.quertyGuaranorList(creditapplicationId);
			if (CommonUtil.isNotEmpty(borrowerServiceVoList)) {
				for (BorrowerServiceVo bsv : borrowerServiceVoList) {
					DESPlus desPlus = new DESPlus();
					String desId = desPlus.encrypt(String.valueOf(bsv.getBorrowerServiceAppId()));
					bsv.setBorrowerServiceAppDESId(desId);
				}
			}
		}
		return borrowerServiceVoList;
	}

	/**
	 * 
	 * @param borrowerServiceAppId
	 *            借款服务申请id
	 * @param creditApplicationId
	 *            小组申请 id
	 * @return message对象
	 */
	@RequestMapping(value = "choseLeader")
	public @ResponseBody
	Message choseLeader(Integer borrowerServiceAppId, Integer creditApplicationId) {
		Message message = new Message();
		try {
			borrowerServiceAppService.choseLeader(borrowerServiceAppId, creditApplicationId);
			message.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 按条件查找
	 * 
	 * @param borrowerInfoVo
	 *            借款人vo
	 * @return 几款人vo列表
	 */
	@RequestMapping(value = "selectByBorrowerInfoVo")
	public @ResponseBody
	List<BorrowerServiceApp> selectByBorrowerServiceApp(BorrowerInfoVo borrowerInfoVo) {
		return borrowerServiceAppService.selectByBorrowerInfoVo(borrowerInfoVo);
	}

	// /**
	// *
	// * @return
	// */
	// @RequestMapping(value = "selectCreditByIDnumber")
	// public CreditApplication selectCreditByIDnumber(String idnumber){
	//
	// }

	/**
	 * 跳到借款人页面
	 * 
	 * @return 入户调查一
	 */
	@RequestMapping(value = "toBorrowerJSP")
	public ModelAndView toBorrowerJSP() {
		ModelAndView model = new ModelAndView("/jsp/rc/business/borrowerServiceAppBak.jsp");
		return model;
	}

	/**
	 * 客户信息报表
	 * 
	 * @return 入户调查一
	 */
	@RequestMapping(value = "exportCustomerInformationt")
	public void exportCustomerInformationt(CreditApplication creditApplication, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/msexcel;charset=UTF-8");
		List<Map> maps = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("creditApplication", creditApplication);
		maps = borrowerServiceAppService.exportCustomerInformationt(map);

		String title = "宜农贷回访接听记录表  -- 客户基本信息表" + "";
		String[] hearders = new String[] { "客户姓名", "城市", "性别 ", "身份证号", "年龄", "联系方式", "借款金额", "财务付款额", "放款时间", "还款时间",
				"家庭住址", "借款用途", "营业部", "产品名称", "客户经理" };// 表头数组
		String[] fields = new String[] { "客户姓名", "城市", "性别", "身份证号", "年龄", "联系方式", "借款金额", "财务付款额", "放款时间", "还款时间",
				"家庭住址", "借款用途", "营业部", "产品名称", "客户经理" };// 对象属性数组
		TableData td = ExcelUtils.createTableData(maps, ExcelUtils.createTableHeader(hearders), fields);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

    }

	/**
	 * 客户信息报表
	 * 
	 * @return 入户调查一
	 */
	@RequestMapping(value = "exportCustomerInformationtList")
	public @ResponseBody
	Pagination exportCustomerInformationtList(CreditApplication creditApplication, String page, String rows)
			throws Exception {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		Map map = new HashMap();
		map.put("creditApplication", creditApplication);
		return borrowerServiceAppService.exportCustomerInformationtList(map, pagination);
	}

	/**
	 * 查询与该电话有关担保人的借款结清次数
	 * 
	 * @author luohongjie
	 */
	@RequestMapping(value = "selectBorrowerListByTel")
	public @ResponseBody
	List<CreditApplicationSearch> selectBorrowerServiceByTel(String telphone) {
		return borrowerServiceAppService.selectBorrowerServiceByTel(telphone);
	}

	/**
	 * 根据电话号码查询借款人的结清次数
	 */
	@RequestMapping(value = "selectBorrowerAuditListByTel")
	@ResponseBody
	public List<CreditApplicationSearch> selectBorrowerAuditListByTel(String telphone) {
		return borrowerServiceAppService.selectBorrowerAuditListByTel(telphone);
	}

	/** 高艳红提交用于修改查看附件中的担保人begin **/
	@RequestMapping(value = "returnFileViewAndLoadJSP")
	public ModelAndView returnFileViewAndLoadJSP(Integer creditapplicationId) {
		ModelAndView modelAndView = new ModelAndView();
		BorrowerInfoVo borrowerInfoVo = new BorrowerInfoVo();
		borrowerInfoVo.setCreditapplicationId(creditapplicationId);
		borrowerInfoVo.setLeader("0");
		List<BorrowerServiceApp> borrowerServiceApps = borrowerServiceAppService.selectByBorrowerInfoVo(borrowerInfoVo);
		modelAndView.setViewName("/jsp/rc/business/fileViewAndLoad.jsp");
		modelAndView.addObject("borrowerServiceApps", borrowerServiceApps);
		return modelAndView;
	}

	/** 高艳红提交用于修改查看附件中的担保人end **/
	/**
	 * 郝强提交用于验证CM有没有上传影像begin
	 * 
	 * @throws Exception
	 **/
	@RequestMapping(value = "checkCM")
	public @ResponseBody
	Message checkCM(String clientId) throws Exception {
		Message message = new Message();
		message.setSuccess(false);
		message.setMsg("没有附件可以下载！");
		int i = creditApplicationService.getImgAmount(clientId);
		if (i > 0) {
			message.setSuccess(true);
			message.setMsg("可以下载！");
		}
		return message;
	}
	/** 郝强提交用于验证CM有没有上传影像end **/

}

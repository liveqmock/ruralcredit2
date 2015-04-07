package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.ICustomerReturnVisitService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.CustomerReturnVisitVo;
import com.creditease.rc.vo.ReturnPlanForShowVo;

@Controller
@RequestMapping(value = "CustomerReturnVisitController")
public class CustomerReturnVisitController {
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
	private ICustomerReturnVisitService customerReturnVisitService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @RequestMapping(value = "selectPagination")
	public @ResponseBody
	Pagination selectPagination(CustomerReturnVisitVo customerReturnVisitVo, String page, String rows, String selectflag,HttpServletRequest request) {

		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		// 添加权限查询
		List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
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
		customerReturnVisitVo.setSqlsid(sqlsid);
		if (selectflag != null) {
			pagination = customerReturnVisitService.selectPagination(customerReturnVisitVo, pagination);
		}
		return pagination;
	}

	@RequestMapping(value = "editReturnVisit")
	public @ResponseBody
	ModelAndView editReturnVisit(String creditapplicationId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		CustomerReturnVisitVo customerReturnVisitVo = new CustomerReturnVisitVo();
		customerReturnVisitVo.setCreditapplicationId(Long.valueOf(creditapplicationId));
		try {
			customerReturnVisitVo.setPresentDate(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date())));
			customerReturnVisitVo.setValidateStatus("1");//这里只要有值就行
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

		System.out.println(sqlsid);
		System.out.println(sqlsid);
		System.out.println(sqlsid);
		System.out.println(sqlsid);
		customerReturnVisitVo.setSqlsid(sqlsid);
		// 查询当前还款日
		customerReturnVisitVo = customerReturnVisitService.selectPresent(customerReturnVisitVo);
		modelAndView.addObject("customerReturnVisitVo", customerReturnVisitVo);
		//modelAndView.setViewName("/jsp/rc/rtnvisits/editReturnVisit.jsp");
		modelAndView.setViewName("/jsp/rc/rtnvisits/editReturnVisitInfo.jsp");
		return modelAndView;
	};

	@RequestMapping(value = "update")
	public @ResponseBody
	Message update(CustomerReturnVisit customerReturnVisit) {
		customerReturnVisit.setValidateStatus("0");
		return customerReturnVisitService.update(customerReturnVisit);
	}

	@RequestMapping(value = "selectWarn")
	public Map selectWarn(HttpServletRequest request, int days) {
		Map<String, List<Map>> conditions = new HashMap<String, List<Map>>();
		conditions.put("customer", customerReturnVisitService.selectWarn(request, days));

		return conditions;
	}

	@RequestMapping(value = "exportExcel")
	public void exportExcel(CustomerReturnVisitVo customerReturnVisitVo, String selectflag, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/msexcel;charset=UTF-8");
        // 添加权限查询
        List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
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
        customerReturnVisitVo.setSqlsid(sqlsid);
        customerReturnVisitVo.setNowDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<CustomerReturnVisitVo> list = customerReturnVisitService.selectAllList(customerReturnVisitVo);
		String title = "客户回访列表";
		/*String[] hearders = new String[] { "业务单号", "借款人姓名", "客户经理 ", "回访日期", "还款日期", "回访时长", "回访方式", "借款用途变更", "新借款用途",
				"家庭收入来源变更", "新家庭收入来源", "经营收入来源变更", "新经营收入来源",  "新大项开支","大项开支", "联系方式变更", "新联系方式", "新增债权债务", "债权债额金额",
				 "家庭成员变化", "其他影响客户还款能力或意愿的情况" ,"是否借款用途一致","借款用途不一致原因","客户还款态度",
		         "对待客户经理态度","是否高危客户标记","高危客户类型"};// 表头数组
		String[] fields = new String[] { "businessNumber", "borrowerName", "loanOfficerName", "visitDate",
				"repaymentDate", "visitDurationTimes", "visitWayShow", "loanUseShow", "newLoanUseShow", "familyIncomeShow",
				"newFamilyIncomeShow", "incomeShow", "newIncomeShow", "spendingShow","newSpending",  "contactWayShow",
				"newContactWay", "newDebtShow", "newDebtMoney",  "familyNumberCondition",
				"otherFactor" ,"isPurposeConsistency","reasonNotConsistency","attitudeForRepayment",
				"attitudeForCutomermanager","highDangered","highDangerReason"};// 对象属性数组
		*/
		String[] hearders = new String[] { "业务单号", "借款人姓名", "客户经理 ", "回访日期",
				"还款日期", "回访时长", "回访方式", "借款用途变更", "新借款用途",
				"收入来源","收入来源变更内容",  "新大项开支","大项开支",
				"借款人联系方式","共借人联系方式","担保人1联系方式","担保人2联系方式",
				 "新增债权债务", "债权债额金额",	"家庭成员变化",
				"其他影响客户还款能力或意愿的情况" ,"是否借款用途一致","借款用途不一致原因","客户还款态度",
				"对待客户经理态度","是否高危客户标记","高危客户类型","照片是否齐全","照片不齐全原因"};// 表头数组
		String[] fields = new String[] { "businessNumber", "borrowerName", "loanOfficerName", "visitDate",
				"repaymentDate", "visitDurationTimes", "visitWayShow", "loanUseShow", "newLoanUseShow",
				"sourceIncomeChangedType","sourceIncomeChangedContentStr", "spendingShow","newSpending",
				"changeBorrowerPhone","changeCoborrowerPhone","changeGuaranteeFirstPhone","changeGuaranteeSecondPhone",
				 "newDebtShow", "newDebtMoney",  "familyNumberCondition",
				"otherFactor" ,"isPurposeConsistency","reasonNotConsistency","attitudeForRepayment",
				"attitudeForCutomermanager","highDangered","highDangerReason","isComplete","reasonForIncomplete"};// 对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
    }

	/**
	 * 批量生成回访计划
	 * 根据固定日期查询
	 * Date repaymentDates
	 * 
	 * @return
	 */
	@RequestMapping(value = "bulkGenerateVisitPlan")
	public @ResponseBody
	Message bulkGenerateVisitPlan(Date repaymentDates) {

		// 这里取数据字典中的VisitprivilegeDays来计算优惠天数

		String VisitprivilegeDays = DicUtil.convertCodeKeyToCodeValue("VisitprivilegeDays", "VisitprivilegeDays");
		if (CommonUtil.isEmpty(VisitprivilegeDays)) {
			Message message = new Message();
			message.setSuccess(false);
			message.setMsg("VisitprivilegeDays为空！添加字典值！");
			return message;
		}
		int days = Integer.parseInt(VisitprivilegeDays);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		repaymentDates = calendar.getTime();
		List<ReturnPlan> returnPlanList = new ArrayList<ReturnPlan>();
		returnPlanList = customerReturnVisitService.selectReturnPlanList(repaymentDates);
		// 生成回访计划 //优先 判断是否有回访计划
		List<CustomerReturnVisit> customerReturnVisits = new ArrayList<CustomerReturnVisit>();
		if (CommonUtil.isNotEmpty(returnPlanList)) {

			for (ReturnPlan returnPlan : returnPlanList) {
				CustomerReturnVisit customerReturnVisit = new CustomerReturnVisit();
				customerReturnVisit.setCreditapplicationId(returnPlan.getCreditapplicationId());
				System.out.println(returnPlan.getCreditapplicationId());
				customerReturnVisit.setStatus("0");
				customerReturnVisit.setRepaymentDate(returnPlan.getRepaymentDate());
				customerReturnVisits.add(customerReturnVisit);
			}
		}
		Message message2 = customerReturnVisitService.insertBatch(customerReturnVisits);
		Message message = new Message();
		if (message2.isSuccess()) {
			message.setSuccess(true);
			message.setMsg("生成回访计划成功！");
		} else {
			message.setSuccess(false);
			message.setMsg(message2.getMsg());
		}
		System.out.println(message.getMsg());
		return message;
	}

	/** 郝强提交旧数据生成回访计划begin **/
	@RequestMapping(value = "haveNoVisitPlanList")
	public @ResponseBody
	Pagination haveNoVisitPlanList(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Map<String, String> queryMap = new HashMap<String, String>();
		return customerReturnVisitService.haveNoVisitPlanList(pagination, queryMap);
	}
	/** 郝强提交旧数据生成回访计划end **/
}

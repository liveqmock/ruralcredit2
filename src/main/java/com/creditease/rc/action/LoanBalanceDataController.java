package com.creditease.rc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IEasyUIService;
import com.creditease.rc.service.ILaonBalanceDataService;
import com.creditease.rc.service.IStatisticalinfoService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.vo.LoanTableData;
import com.creditease.rc.vo.LoanTableDataVo;

@Controller
@RequestMapping("loanBalanceDataController")
public class LoanBalanceDataController {
	@Resource
	private ILaonBalanceDataService laonBalanceDataService;
	@Resource
	private IStatisticalinfoService statisticalinfoService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @RequestMapping(value = "returnLoanBalanceDataJSP")
	public ModelAndView returnLoanBalanceDataJSP(String areaDepartmentIds, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/loanBalanceData.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "returnLoanBalanceDataTable")
	public ModelAndView returnLoanBalanceDataTable(String areaDepartmentIds, HttpSession session) {
		System.out.println(areaDepartmentIds);
		ModelAndView modelAndView = new ModelAndView();
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		if ("null".equals(areaDepartmentIds)) {
			areaDepartmentIds = null;
		}
		if (CommonUtil.isEmpty(areaDepartmentIds)) {
			areaDepartmentIds = "";
			Set<String> keySet = DepartmentUtil.getDepartmentMapByAuthority().keySet();

			for (String departmentId : keySet) {
				areaDepartmentIds = areaDepartmentIds + departmentId + ",";
			}
			areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
		}
		System.out.println("-----------------");
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println("+++++++++++++++++");
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("authList", sqlsid);
		queryMap.put("areaDepartmentIds", areaDepartmentIds);
		List<LoanTableData> loanTableDatas = laonBalanceDataService.queryLoanTableDatas(queryMap);
		modelAndView.addObject("loanTableDatas", loanTableDatas);
		modelAndView.setViewName("/jsp/rc/sales/loanBalanceDataTable.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "putDateInSession")
	public @ResponseBody
	boolean putDateInSession(LoanTableDataVo loanTableDataVo, HttpSession session) {
		List<LoanTableData> loanTableDatas = loanTableDataVo.getLoanTableDatas();
		session.setAttribute("loanTableDatas", loanTableDatas);
		return true;
	}

	@RequestMapping(value = "exportExcel")
	public @ResponseBody
	void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		List<LoanTableData> loanTableDatas = (List<LoanTableData>) session.getAttribute("loanTableDatas");
// if (CommonUtil.isNotEmpty(loanTableDatas)) {
// for (LoanTableData loanTableData : loanTableDatas) {
// String getCustomerCountRateShow = loanTableData.getCustomerCountRateShow();
// getCustomerCountRateShow = getCustomerCountRateShow.substring(0, getCustomerCountRateShow.length() - 1);
// loanTableData.setCustomerCountRateShow(getCustomerCountRateShow);
//
// String getDefaultValueRateShow = loanTableData.getDefaultValueRateShow();
// getDefaultValueRateShow = getDefaultValueRateShow.substring(0, getDefaultValueRateShow.length() - 1);
// loanTableData.setDefaultValueRateShow(getDefaultValueRateShow);
//
// String getLoanBalanceRateShow = loanTableData.getLoanBalanceRateShow();
// getLoanBalanceRateShow = getLoanBalanceRateShow.substring(0, getLoanBalanceRateShow.length() - 1);
// loanTableData.setLoanBalanceRateShow(getLoanBalanceRateShow);
// }
// }
		response.setContentType("application/msexcel;charset=UTF-8");

		String title = "业务质量报表";
		String[] hearder0 = new String[] { "类型", "客户数", "客户数%", "违约金额", "违约金额%", "贷款余额", "贷款余额%", "描述" };
		String[] fields0 = new String[] { "type", "customerCount", "customerCountRateShow", "defaultValue",
				"defaultValueRateShow", "loanBalance", "loanBalanceRateShow", "description" };
		TableData td0 = ExcelUtils.createTableData(loanTableDatas, ExcelUtils.createTableHeader(hearder0), fields0);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td0);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td0);
    }
}

package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Employee;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.EmployeeService;

@Controller
@RequestMapping("employeeController")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;


    /**
	 * 员工列表查询
	 * @param employee
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("selectAllEmployee")
	public @ResponseBody Pagination selectAllCollection(Employee employee,String page,String rows,HttpServletRequest request,
			HttpSession session){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));
		}
		if(!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
		return employeeService.selectAllCollection(pagination, employee, page, rows, request, session);
	}
	
	@RequestMapping("synchronizationData")
	public @ResponseBody Message synchronizationData(String flag){
		Message message = new Message();
		try {
			message = employeeService.synchronizationData(flag);
			message.setMsg("同步成功");
			message.setSuccess(true);
		} catch (Exception e) {
			message.setMsg("同步失败");
			message.setSuccess(false);
			e.printStackTrace();
		}
		
		return message;
	}
	
	@RequestMapping("downloandExcel")
	public void downloandExcel(Employee employee,String page,String rows,HttpServletRequest request,
			HttpSession session,HttpServletResponse response){
		Pagination pagination = new Pagination();
		pagination = employeeService.downloadExcel(pagination, employee, page, rows, request, session);
		List<Employee> emplist = pagination.getRows();
		String title = "员工列表";
		String[] hearders = new String[]{"员工编号","用户名","姓名","在职状态","角色","部门名称","所属营业部",
										"所属分中心 ","开通权限时间","关闭权限时间","入职时间","邮箱"};
		String[] fields = new String[]{"comempno","account","name","activestatus","roledesc","departmentname","areadepartmentname",
										"citydepartmentname","openauthoritydate","closeauthoritydate","entrytime","emailaddr"};
		TableData td = ExcelUtils.createTableData(emplist, ExcelUtils.createTableHeader(hearders),fields);
		try {
			/*JsGridReportBase report = new JsGridReportBase(request, response);
			report.exportToExcel(title, "", td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, "", td);

        } catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}

package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.rc.dao.EmployeeChangeDAO;
import com.creditease.rc.domain.EmployeeChange;

@Controller
@RequestMapping("employeeChangeController")
public class EmployeeChangeController {

	@Resource
	private EmployeeChangeDAO employeeChangeDAO;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@RequestMapping("toHistoryChange")
	public @ResponseBody ModelAndView toHistoryChange(String comEmpId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comEmpId",comEmpId);
		modelAndView.setViewName("/jsp/rc/basicInfo/employeeHistory.jsp");
		return modelAndView;
	}
	
	@RequestMapping("queryHistoryChange")
	public @ResponseBody List<EmployeeChange> queryHistoryChange(String comEmpId){
		List<EmployeeChange> employeeChangeList = new ArrayList<EmployeeChange>();
		employeeChangeList =employeeChangeDAO.queryHistoryChange(Integer.parseInt(comEmpId));
		return employeeChangeList;
	}
}

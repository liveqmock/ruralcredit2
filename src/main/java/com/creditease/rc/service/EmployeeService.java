package com.creditease.rc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.Employee;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;



public interface EmployeeService {
	
	/**
	 * 查询员工列表
	 * @param pagination
	 * @param collectionVO
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	public Pagination selectAllCollection(Pagination pagination,
			Employee employee,String page,String rows,
			HttpServletRequest request,HttpSession session);
	
	/**
	 * 同步smp数据
	 * @return
	 */
	public Message synchronizationData(String flag) throws Exception;
	
	/**
	 * 查询需要导出的数据
	 * @param pagination
	 * @param employee
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	public Pagination downloadExcel(Pagination pagination,
			Employee employee,String page,String rows,
			HttpServletRequest request,HttpSession session);
	
	/**
	 * 每晚12点定时同步smp员工信息
	 * @throws Exception
	 */
	public void dispatcher() throws Exception;

}

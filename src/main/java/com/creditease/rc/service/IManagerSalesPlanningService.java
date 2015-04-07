package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.ManagerSalesPlanning;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;

public interface IManagerSalesPlanningService {
	Long insert(ManagerSalesPlanning record);
	
	/**
	 * 批量添加销售计划的放款量
	 * @param managerSalesPlanningList
	 * @return 
	 */
	Message savaOrUpdateInsert(List<ManagerSalesPlanning> managerSalesPlanningList,HttpSession session,String year,String areaDepartmentId);
	/**
	 * 查询所有信贷员
	 * @param pagination
	 * @param employee
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	public Pagination selectManagerSalesPlanningLoanSum(Pagination pagination,
			ManagerSalesPlanning managerSalesPlanning,String page,String rows,
			HttpServletRequest request,HttpSession session,String paramJsonMap,String year);
	/**
	 * 查询所有信贷员
	 * @param pagination
	 * @param employee
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @param year 
	 * @return
	 */
	public Pagination selectManagerSalesPlanningContractMoney(Pagination pagination,
			ManagerSalesPlanning managerSalesPlanning,String page,String rows,
			HttpServletRequest request,HttpSession session,String paramJsonMap, String year);

	/**
	 * 批量添加销售计划的合同金额
	 * @param managerSalesPlanningList
	 * @return 
	 */
	Message saveOrUpdateInsertContractMoney(List<ManagerSalesPlanning> managerSalesPlanningList,HttpSession session,String year,String areaDepartmentId);

}

package com.creditease.rc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.ManagerSalesPlanning;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IManagerSalesPlanningService;
import com.creditease.rc.util.SmpWSUtil;

@Controller
@RequestMapping("managerSalesPlanningController")
public class ManagerSalesPlanningController {
	@Resource
	private IManagerSalesPlanningService managerSalesPlanningService;
	@Resource
	private SmpWSUtil smpWSUtil;
	
	@RequestMapping(value = "managerSalesPlanningSave")
	public @ResponseBody
	Message managerSalesPlanningSave(HttpServletRequest request,HttpSession session,String year,String areaDepartmentId) {
		System.out.println(year);
		List<ManagerSalesPlanning> list = com.creditease.core.utils.JsonUtil.getListFromJsonArrStr(request.getParameter("data"), ManagerSalesPlanning.class);
		//System.out.println("===================================4444444444444444444444444545==>>>>>>>>"+list);
		Message message=new Message();
		//批量添加
		message=managerSalesPlanningService.savaOrUpdateInsert(list,session,year,areaDepartmentId);
		//managerSalesPlanningService.insert(listManagerSalesPlanning);
		return message;
	}
	@RequestMapping(value = "managerSalesPlanningSaveContractMoney")
	public @ResponseBody
	Message managerSalesPlanningSaveContractMoney(HttpServletRequest request,HttpSession session,String year,String areaDepartmentId) {
		List<ManagerSalesPlanning> list = com.creditease.core.utils.JsonUtil.getListFromJsonArrStr(request.getParameter("data"), ManagerSalesPlanning.class);
		
		//System.out.println("===================================4444444444444444444444444545==>>>>>>>>"+list);
		Message message=new Message();
		//批量添加
		message=managerSalesPlanningService.saveOrUpdateInsertContractMoney(list,session,year,areaDepartmentId);
		return message;
	}
	/**
	 * 查询所有信贷员的放款量的销售计划信息
	 * @param employee
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("selectManagerSalesPlanningLoanSum")
	public @ResponseBody Pagination selectManagerSalesPlanningLoanSum(ManagerSalesPlanning managerSalesPlanning,String page,String rows,HttpServletRequest request,
			HttpSession session,String paramJsonMap,String year,String me){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));
		}
		if(!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination= managerSalesPlanningService.selectManagerSalesPlanningLoanSum(pagination, managerSalesPlanning, page, rows, request, session,paramJsonMap,year);
		Map<String, String> map = new HashMap<String, String>();
		/*根据当前登录人获取不同的菜单数据*/
		Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
		if (userAuthoritiesSet.contains("ROLE_SALES_ADMIN")) {
			me = "admin";
		}
        map.put("me", me);
        pagination.getRows().add(map);
        //以为页面的datagrid中动态删除一行
        pagination.setTotal(pagination.getTotal()+1);
		return 	pagination;

	}
	
	/**
	 * 查询所有信贷员的合同金额销售计划信息
	 * @param employee
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("selectManagerSalesPlanningContractMoney")
	public @ResponseBody Pagination selectManagerSalesPlanningContractMoney(ManagerSalesPlanning managerSalesPlanning,String page,String rows,HttpServletRequest request,
			HttpSession session,String type,String paramJsonMap,String year,String me){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));
		}
		if(!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = managerSalesPlanningService.selectManagerSalesPlanningContractMoney(pagination, managerSalesPlanning, page, rows, request, session, paramJsonMap, year);
		Map<String, String> map = new HashMap<String, String>();
		/*根据当前登录人获取不同的菜单数据*/
		Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
		if (userAuthoritiesSet.contains("ROLE_SALES_ADMIN")) {
			me = "admin";
		}
        map.put("me", me);
        pagination.getRows().add(map);
      //以为页面的datagrid中动态删除一行
        pagination.setTotal(pagination.getTotal()+1);
		return 	pagination;
	}
}

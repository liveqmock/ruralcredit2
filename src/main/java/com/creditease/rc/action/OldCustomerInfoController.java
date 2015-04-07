/**
 * Title:OldCustomerInfoController.java  
 * Description:  
 */
package com.creditease.rc.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IOldCustomerInfoService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.SmpWSUtil;

/**
 * Title:OldCustomerInfoController.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-6-7 
 */
@Controller
@RequestMapping("oldCustomerInfoController")
public class OldCustomerInfoController {

	/**
	 * @Description 默认构造器 
	 */
	public OldCustomerInfoController() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IOldCustomerInfoService oldCustomerInfoService;
	@Resource
	private SmpWSUtil smpWSUtil;
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  分页查询农贷一期产品列表
	 * @param idCard
	 * @param page
	 * @param rows
	 * @return 
	 * @version v1.0 
	 * 2013-6-7
	 */
	@RequestMapping(value="queryProductListByidCard")
	@ResponseBody
	public Pagination queryProductListByidCard(String idCard,String defaultQuery,@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows ){
		Pagination pagination = new Pagination();
		if (CommonUtil.isNotEmpty(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (CommonUtil.isNotEmpty(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		//初始化datagrid不需要查询
		if(null==defaultQuery){
			pagination =oldCustomerInfoService.queryProductListByidCard(pagination,idCard);
		}
		return pagination;
		
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  分页查询农贷一期审批记录
	 * @param idCard
	 * @param page
	 * @param rows
	 * @return 
	 * @version v1.0 
	 * 2013-6-7
	 */
	@RequestMapping(value="queryApplyAuditTableListByidCard")
	@ResponseBody
	public Pagination queryApplyAuditTableListByidCard(String idCard,String defaultQuery,@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows ){
		Pagination pagination = new Pagination();
		if (CommonUtil.isNotEmpty(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (CommonUtil.isNotEmpty(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		//初始化datagrid不需要查询
		if(null==defaultQuery){
			
			pagination =oldCustomerInfoService.queryApplyAuditTableListByidCard(pagination,idCard);
			/*List<Map<String,String>> list =pagination.getRows();
			if(CommonUtil.isNotEmpty(list)){
				for(Map<String,String> map:list){
					String exam_paerson_id=(String) map.get("EXAM_PAERSON");
					if(CommonUtil.isNotEmpty(exam_paerson_id)){
						//查询一期UC中审查人和审批人，农商贷一期系统标识符=8
						EmployeeDTO employeeDTO = smpWSUtil.getEmployee(exam_paerson_id,"8");
						if(null!=employeeDTO){
							map.put("EXAM_PAERSON", employeeDTO.getName());
						}
						
					}
					String auditor_id=(String) map.get("AUDITOR");
					if(CommonUtil.isNotEmpty(auditor_id)){
						//查询一期UC中审查人和审批人，农商贷一期系统标识符=8
						EmployeeDTO employeeDTO = smpWSUtil.getEmployee(auditor_id,"8");
						if(null!=employeeDTO){
							map.put("AUDITOR", employeeDTO.getName());
						}
						
					}
				}
				
			}*/
			
		}
		return pagination;
		
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询还款情况
	 * @param idCard
	 * @param page
	 * @param rows
	 * @return 
	 * @version v1.0 
	 * 2013-6-7
	 */
	@RequestMapping(value="queryFinanceListByidCard")
	@ResponseBody
	public Pagination queryFinanceListByidCard(String idCard,String defaultQuery,@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows ){
		Pagination pagination = new Pagination();
		if (CommonUtil.isNotEmpty(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (CommonUtil.isNotEmpty(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		//初始化datagrid不需要查询
		if(null==defaultQuery){
			
			pagination =oldCustomerInfoService.queryFinanceListByidCard(pagination,idCard);
			
		}
		return pagination;
		
	}

}

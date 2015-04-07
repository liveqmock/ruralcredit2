/**
 * Title:OldCustomerInfoService.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IOldCustomerInfoDAO;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IOldCustomerInfoService;

/**
 * Title:OldCustomerInfoService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-6-5 
 */
@Service
public class OldCustomerInfoService implements IOldCustomerInfoService {

	/**
	 * @Description 默认构造器 
	 */
	public OldCustomerInfoService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IOldCustomerInfoDAO oldCustomerInfoDAO;
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询产品列表
	 * @param idCard
	 * @return 
	 * @version v1.0 
	 * 2013-6-6
	 */
	public Pagination queryProductListByidCard(Pagination pagination,String idCard){
		return this.oldCustomerInfoDAO.queryProductListByidCard(pagination, idCard);
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询审查审批记录
	 * @param idCard
	 * @return 
	 * @version v1.0 
	 * 2013-6-6
	 */
	public Pagination queryApplyAuditTableListByidCard(Pagination pagination,String idCard){
		return this.oldCustomerInfoDAO.queryApplyAuditTableListByidCard(pagination, idCard);
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询还款情况
	 * @param idCard
	 * @return 
	 * @version v1.0 
	 * 2013-6-6
	 */
	public Pagination queryFinanceListByidCard(Pagination pagination,String idCard){
		return this.oldCustomerInfoDAO.queryFinanceListByidCard(pagination, idCard);
	}

}

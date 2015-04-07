/**
 * Title:IOldCustomerInfoDAO.java  
 * Description:  
 */
package com.creditease.rc.dao;

import com.creditease.rc.framework.pager.Pagination;

/**
 * Title:IOldCustomerInfoDAO.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-6-7 
 */
public interface IOldCustomerInfoDAO {

	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询产品列表
	 * @param idCard
	 * @return 
	 * @version v1.0 
	 * 2013-6-6
	 */
	public Pagination queryProductListByidCard(Pagination pagination,String idCard);
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询审查审批记录
	 * @param idCard
	 * @return 
	 * @version v1.0 
	 * 2013-6-6
	 */
	public Pagination queryApplyAuditTableListByidCard(Pagination pagination,String idCard);
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人省份证号查询还款情况
	 * @param idCard
	 * @return 
	 * @version v1.0 
	 * 2013-6-6
	 */
	public Pagination queryFinanceListByidCard(Pagination pagination,String idCard);
	
	
	
	
	
}

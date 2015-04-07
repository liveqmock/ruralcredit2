/**
 * Title:OldCustomerInfoDAO.java  
 * Description:  
 */
package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IOldCustomerInfoDAO;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

/**
 * Title:OldCustomerInfoDAO.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-6-7 
 */
@Repository
public class OldCustomerInfoDAO implements IOldCustomerInfoDAO {

	/**
	 * @Description 默认构造器 
	 */
	public OldCustomerInfoDAO() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IBaseDao  ruralOneBaseDao;
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
		return this.ruralOneBaseDao.queryForPaginatedList("oldCustomerInfo.queryProductListByidCard", "oldCustomerInfo.queryProductListCountByidCardCount",
				idCard, pagination.getStartResult(), pagination.getPageSize());
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
		return this.ruralOneBaseDao.queryForPaginatedList("oldCustomerInfo.queryApplyAuditTableListByidCard", "oldCustomerInfo.queryApplyAuditTableListCountByidCard",
				idCard, pagination.getStartResult(), pagination.getPageSize());
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
		return this.ruralOneBaseDao.queryForPaginatedList("oldCustomerInfo.queryFinanceListByidCard", "oldCustomerInfo.queryFinanceListCountByidCard",
				idCard, pagination.getStartResult(), pagination.getPageSize());
	}

}

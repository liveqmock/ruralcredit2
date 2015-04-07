/**
 * Title:IDataBestowalService.java
 * Description:
 */
package com.creditease.rc.service;

import java.util.HashMap;
import java.util.List;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.DataBestowal;
import com.creditease.rc.domain.DataBestowalDetail;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;

/**
 * Title:IDataBestowalService.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-5-31
 */
public interface IDataBestowalService {
	
	
	/**
	 * 根据营业部更新所有信贷员的权限
	 * @param objectIds
	 * @param departmentId
	 * @return
	 */
	public Message updatesynchroAurhorityByDepartment(List<Integer> customerIdsList,List<Integer> creditApplicationIdsList,String departmentId);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 插入数据赠与表
	 * @param record
	 * @version v1.0
	 *          2013-5-31
	 */
	public Message insertDataBestowalAndDataBestowalDetail(DataBestowal dataBestowal, Long[] creditApplicationIds);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询数据赠与表
	 * @param record
	 * @return
	 * @version v1.0
	 *          2013-5-31
	 */
	public List<DataBestowal> selectByDataBestowalSelective(DataBestowal record);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据赠与主表id查询赠与明细
	 * @param DataBestowalId
	 * @return
	 * @version v1.0
	 *          2013-5-31
	 */
	public List<DataBestowalDetail> selectDataBestowalDetailByDataBestowalId(Long DataBestowalId);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 分页查询可赠与的申请单
	 * @param newLoanOfficerId
	 * @return
	 * @version v1.0
	 *          2013-6-3
	 */
	public Pagination selectBestowalCreditApplicationByPagination(CreditApplication creditApplication,
			Pagination pagination);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询系统中所有的客户经理
	 * @param creditApplication
	 * @return
	 * @version v1.0
	 *          2013-6-5
	 */
	public List querySysloanOfficerList(CreditApplication creditApplication);

	/**
	 * 分页查询赠与列表
	 * 
	 * @param record 赠与对象
	 * @param pagination 分页条件
	 * @return 分页列表
	 */
	public Pagination selectBestowalPagination(DataBestowal record, Pagination pagination, String businessNumber);

	/**
	 * zhangman
	 * 
	 * @param DataBestowalId 主表id
	 * @return bestowalDetailsMap
	 */
	public List<HashMap> selectCredit(Long DataBestowalId);

	/**
	 * zhangman
	 * 
	 * @param DataBestowalId 主表id
	 * @return bestowalDetailsMap
	 */
	public List<HashMap> selectCustomerConsult(Long DataBestowalId);

	/**
	 * 更新申请权限
	 * 
	 * @param objectId
	 * @param oldLoanOfficerId
	 * @param loanOfficerId
	 * @return
	 */
	public Message updateCreditApplicationAuthorization(List<Integer> objectIds, int loanOfficerId);

	/**
	 * 更新咨询权限
	 * 
	 * @param objectId
	 * @param oldLoanOfficerId
	 * @param loanOfficerId
	 * @return
	 */
	public Message updateCustomerConsultAuthorization(List<Integer> objectIds, int loanOfficerId);

	public Message updateCustomerConsultAurhority(DataBestowal dataBestowal, Long[] idsLong);
}

package com.creditease.rc.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerConsultPoolVo;
import com.creditease.rc.vo.DistributeDepartmentVO;
import com.creditease.rc.vo.SaleInquireSearchInfoVO;

public interface ISaleInquireService {

	long saveInquire(CustomerConsultPool customerConsultPool);

	Pagination searchSaleInquireInfo(SaleInquireSearchInfoVO saleInquireSearchInfoVO, Pagination pagination,
			String fuzzyValue, String sort, String order, HttpSession session);

	Map<String, Object> updateDistributeDepartment(DistributeDepartmentVO distributeDepartmentVO, Long[] consultPoolIds, String[] consultStates,String[] dateArr);

	CustomerConsultPool selectCustomerPoolById(long consultPoolId);

	boolean updateConsulePool(CustomerConsultPool customerConsultPool, String oldStatus, String newStatus);

	/**
	 * 用于营销咨询列表 - 查看
	 * 
	 * @param consultPoolId
	 * @return
	 * @author manzhang
	 */
	CustomerConsultPoolVo registerInquireView(Long consultPoolId);

	Message updateAutoDistributeDepartment();

	Message updateDistributeOrders(CustomerConsultPool customerConsultPool, Long[] consultPoolIds);

	public String selectValueByStatus(Map recond);

	boolean updateScrapConsult(Long consultPoolId);

	Pagination searchSaleInquireInfoMenu(Pagination pagination, String sort, String order);

	/**
	 * 营销咨询自动分配
	 * 
	 * @author 郝强
	 * @return
	 */
	Message updateAutoDistributeSaleInquire();

	/**
	 * 根据电话号码或主键ID查看详情
	 * 
	 * @param type 判断标识
	 * @return
	 */
	Pagination selectDetailByPhoneOrId(Pagination pagination, String sort, String order, String objId, String type);

	/**
	 * 分件确认
	 * 
	 * @author 郝强
	 * @param customerConsultPool
	 * @param idsLong
	 * @return
	 */
	Message updateDistributeConfirm(CustomerConsultPool customerConsultPool, Long[] consultPoolIds);

	/**
	 * 查分配的营业部中有没有没有行政助理的
	 * 
	 * @return
	 */
	Message checkROLELocalFin();
}

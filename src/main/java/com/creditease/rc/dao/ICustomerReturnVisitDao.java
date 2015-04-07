package com.creditease.rc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerReturnVisitVo;

public interface ICustomerReturnVisitDao extends IBaseDao{
	/**
	 * 分页查询
	 * @param customerReturnVisitVo
	 * @param pagination
	 * @return
	 */
	Pagination selectPagination(CustomerReturnVisitVo customerReturnVisitVo, Pagination pagination);

	/**
	 * 
	 * @param customerReturnVisitVo
	 * @return
	 */
	List<CustomerReturnVisitVo> selectPresent(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 查询首次（没有还款的两个月份合并成一个月）
	 * @param customerReturnVisitVo
	 * @return
	 */
	List<CustomerReturnVisitVo> selectPresentAgain(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 
	 * @param customerReturnVisit
	 * @return
	 */
	long insert(CustomerReturnVisit customerReturnVisit);

	int update(CustomerReturnVisit customerReturnVisit);

	void insertBatch(List<CustomerReturnVisit> customerReturnVisitList);
	
	public CustomerReturnVisitVo selectByID(CustomerReturnVisitVo customerReturnVisitVo);

	List<CustomerReturnVisitVo> selectList(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 查询提醒
	 * @param conditions
	 * @return
	 */
	List<Map> selectWarn(Map conditions);

	/**
	 * 查询上一期没有做过回访的信息
	 * @param conditions
	 * @return
	 */
	public List<Map> selectDontCallBackOfThisMonth(Map conditions);
	/**
	 * 查询列表
	 * @param customerReturnVisitVo
	 * @return
	 */
	public List<CustomerReturnVisitVo> selectAllList(CustomerReturnVisitVo customerReturnVisitVo);
	/**
	 * 查询今天是不是还款日（回访计划中的）
	 * @author manzhang
	 * @param customerReturnVisitVo
	 */
	List<CustomerReturnVisit> selectToday(
			CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 查询还款计划表中  LUO
	 */
	public List<ReturnPlan> selectReturnPlanList(Date repaymentDates);

	Pagination haveNoVisitPlanList(Pagination pagination, Map<String, String> queryMap);

	boolean updateCusReViVSByCreIdNDat(Map<String, String> map);
}

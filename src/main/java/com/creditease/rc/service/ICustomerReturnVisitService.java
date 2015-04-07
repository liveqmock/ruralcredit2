package com.creditease.rc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerReturnVisitVo;
import com.creditease.rc.vo.ReturnPlanForShowVo;

public interface ICustomerReturnVisitService {

	/**
	 * 分页查询
	 * 
	 * @param customerReturnVisitVo
	 * @param pagination
	 * @return
	 */
	Pagination selectPagination(CustomerReturnVisitVo customerReturnVisitVo, Pagination pagination);

	/**
	 * 查询当前
	 * 
	 * @param customerReturnVisitVo
	 * @return
	 */
	CustomerReturnVisitVo selectPresent(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 新增
	 * 
	 * @param customerReturnVisit
	 * @return
	 */
	public Message insert(CustomerReturnVisit customerReturnVisit);

	/**
	 * 更新
	 * 
	 * @param customerReturnVisit
	 * @return
	 */
	public Message update(CustomerReturnVisit customerReturnVisit);

	/**
	 * 新增
	 * 
	 * @param customerReturnVisitList
	 * @return
	 */
	public Message insertBatch(List<CustomerReturnVisit> customerReturnVisitList);

	/**
	 * 查列表
	 * 
	 * @param customerReturnVisitVo
	 * @return
	 */
	List<CustomerReturnVisitVo> selectList(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 查询过期的回访记录
	 * 
	 * @return
	 */
	List<Object> selectOverdue();

	/**
	 * 查询提醒（）
	 * 
	 * @param request
	 *            获取 权限
	 * @param days
	 *            几日内
	 * @return
	 */
	List<Map> selectWarn(HttpServletRequest request, int days);
	/**
	 * 查询本月没有做过回访的信息
	 * @param request
	 * @return
	 */
	public List<Map> selectDontCallBackOfThisMonth(HttpServletRequest request);
	/**
	 * 、 查询列表
	 * 
	 * @param customerReturnVisitVo
	 * @return
	 */
	List<CustomerReturnVisitVo> selectAllList(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 查询今天是不是还款日（回访计划中的）
	 * 
	 * @author manzhang
	 * @param customerReturnVisitVo
	 */
	List<CustomerReturnVisit> selectToday(CustomerReturnVisitVo customerReturnVisitVo);

	/**
	 * 查询还款列表
	 * 
	 * @param ReturnPlanForShowVo
	 * @return
	 */
	List<ReturnPlan> selectReturnPlanList(Date repaymentDates);

	Pagination haveNoVisitPlanList(Pagination pagination, Map<String, String> queryMap);

	/**
	 * 根据申请ID和日期来改变之后的有无效状态
	 * 
	 * @param map
	 * @return
	 */
	Message updateCusReViVSByCreIdNDat(Map<String, String> map);
}

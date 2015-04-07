package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 还款计划服务
 * 
 * @author zhangman
 * 
 */
public interface IReturnPlanService {
	/**
	 * 批量增加还款计划
	 * 
	 * @param returnPlans 还款计划列表
	 */
	public void addBatchReturnPlan(List<ReturnPlan> returnPlans);

	/**
	 * 生成还款计划
	 * 
	 * @param creditApplicationId 信贷申请id
	 * @return 结果标记
	 */
	public boolean addForCreditApplication(Integer creditApplicationId);

	/**
	 * 查询列表
	 * 
	 * @param returnPlan 还款计划类
	 * @return 还款计划列表
	 */
	public List<ReturnPlan> selectList(ReturnPlan returnPlan);

	/**
	 * 查询所有的还款计划
	 * 
	 * @param returnPlan 还款计划类
	 * @return 还款计划列表
	 */
	public List<ReturnPlan> queryList(ReturnPlan returnPlan);

	/**
	 * 删除还款计划
	 * 
	 * @param creditApplicationId 信贷申请单ID
	 * @return int 删除数据的条数
	 */
	public int deleteReturnPlan(Integer creditApplicationId);

	/**
	 * 查询还款计划DATAGRID 从贷后！
	 * 
	 * @author 郝强
	 * @param creditApplicationId 信贷申请单ID
	 * @param pagination 分页对象
	 * @return 分页列表
	 */
	Pagination queryReturnPlanDataGrid(Long creditApplicationId, Pagination pagination);

	/**
	 * 查询产品id对应的name 返回map
	 * 
	 * @return ProductMap
	 */
	public Map<String, String> queryProductMap();

	/**
	 * 这里新增了一个方法就是用来记录调用贷后webservice中的接受借款人信息来记录操作日志的
	 * 
	 * @author 郝强
	 * @param creditapplicationId
	 * @return
	 */
	public Message clientApply(Long creditapplicationId);

	public Message updateClientMission();

	public Message insertBatchReturnPlans();

	public Pagination queryHaveNoReturnPlanList(Pagination pagination, Map<String, String> queryMap);

	public boolean insertReturnPlanFromIocalReturnSchemeResponses(
			List<LocalReturnSchemeResponse> localReturnSchemeResponses, Long creditAppliationId);

}

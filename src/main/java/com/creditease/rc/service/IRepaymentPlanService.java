package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.domain.RepaymentPlanItem;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IRepaymentPlanService {
	/**
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @return 布尔类型
	 */
	boolean insertRepaymentPlan(RepaymentPlan repaymentPlan);

	/**
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @param pagination 分页列表
	 * @param searchFlag 搜索标识
	 * @param fuzzyQueryValue 模糊搜索值
	 * @return 分页列表
	 */
	Pagination queryRepaymentPlanDataGrid(RepaymentPlan repaymentPlan, Pagination pagination, String fuzzyQueryValue,
			Integer searchFlag);

	/**
	 * 
	 * @param passRepaymentPlanItem 还款方案明细List
	 * @return 布尔
	 */
	boolean insertRepaymentPlanItem(List<RepaymentPlanItem> passRepaymentPlanItem);

	/**
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @return 还款方案主键
	 */
	Integer insertRepaymentPlanReturnId(RepaymentPlan repaymentPlan);

	/**
	 * 
	 * @param repaymentPlanId 还款方案主键
	 * @param pagination 分页列表
	 * @return 分页列表
	 */
	Pagination queryRepaymentPlanItemDataGrid(Integer repaymentPlanId, Pagination pagination);

	/**
	 * 
	 * @param repaymentPlanId 还款方案主键
	 * @return 还款计划对象
	 */
	RepaymentPlan searchRepaymentPlanByRepaymentPlanId(Integer repaymentPlanId);

	/**
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @return 布尔类型
	 */
	boolean updateRepaymentPlan(RepaymentPlan repaymentPlan);

	/**
	 * 
	 * @param repaymentPlanItem 还款方案明细对象
	 * @return 布尔类型
	 */
	boolean updateRepaymentPlanItem(RepaymentPlanItem repaymentPlanItem);

	/**
	 * 
	 * @param passRepaymentPlan 还款计划对象
	 * @return 数量
	 */
	Integer searchRepaymentPlan(RepaymentPlan passRepaymentPlan);

	/**
	 * 
	 * @param passRepaymentPlan 还款方案对象
	 * @param passRepaymentPlanItems 还款方案明细对象List
	 * @return 布尔
	 */
	boolean insertRepaymentPlanAndItem(RepaymentPlan passRepaymentPlan, List<RepaymentPlanItem> passRepaymentPlanItems);

	/**
	 * 
	 * @param passRepaymentPlan 还款方案对象
	 * @param passRepaymentPlanItems 还款方案明细对象List
	 * @return 布尔
	 */
	boolean updateRepaymentPlanAndItem(RepaymentPlan passRepaymentPlan, List<RepaymentPlanItem> passRepaymentPlanItems);

	/**
	 * 这个是用来显示combobox的
	 * 
	 * @param id 还款方案ID
	 * @param q 还款方案名称
	 * @return List<RepaymentPlan>
	 */
	List<RepaymentPlan> returnComboboxRepayment(Integer id, String q);

	/**
	 * 
	 * @param creditapplicationId 信贷申请单ID
	 * @return 字符串类型 提前还款方式
	 */
	String getEarlyType(Integer creditapplicationId);

	/**
	 * 
	 * @param repaymentPlanId 还款计划ID
	 * @return 还款计划
	 */
	RepaymentPlan returnRepaymentPlan(Integer repaymentPlanId);
	
	/**
	 * 按照id查询还款方案明细列表
	 * @author zhangman
	 * @param repaymentPlanId 还款方案id
	 * @return 还款方案明细列表
	 */
	List<RepaymentPlanItem> searchRepaymentPlanItems(Integer repaymentPlanId);
}

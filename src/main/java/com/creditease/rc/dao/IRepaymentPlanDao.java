package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.domain.RepaymentPlanItem;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IRepaymentPlanDao extends IBaseDao {
	/**
	 * 
	 * @param repaymentPlan 还款方案
	 * @param pagination 列表
	 * @return 列表
	 */
	Pagination queryForPaginatedRepaymentList(RepaymentPlan repaymentPlan, Pagination pagination);

	/**
	 * 
	 * @param fuzzyQueryValue 模糊查询条件
	 * @param pagination 列表
	 * @return 列表
	 */
	Pagination fuzzyQueryForPaginatedRepaymentList(String fuzzyQueryValue, Pagination pagination);

	/**
	 * 
	 * @param repaymentPlanId 还款方案主键
	 * @return 还款方案
	 */
	RepaymentPlan queryUniqueRepayment(Integer repaymentPlanId);

	/**
	 * 
	 * @param repaymentPlan 还款计划
	 * @return 还款计划ID
	 */
	Integer insertRepaymentPlanObjectReturnId(RepaymentPlan repaymentPlan);

	/**
	 * 
	 * @param repaymentPlan 还款计划
	 */
	void updateRepaymentPlan(RepaymentPlan repaymentPlan);

	/**
	 * 
	 * @param repaymentPlanId 主键
	 * @param pagination 列表
	 * @return Pagination
	 */
	Pagination queryRepaymentPlanItemDataGrid(Integer repaymentPlanId, Pagination pagination);

	/**
	 * 
	 * @param passRepaymentPlanItems 还款方案明细List
	 */
	void batchInsertRepaymentPlanItemList(List<RepaymentPlanItem> passRepaymentPlanItems);

	/**
	 * 
	 * @param passRepaymentPlanItems 还款方案明细List
	 */
	void batchUpdateRepaymentPlanItemList(List<RepaymentPlanItem> passRepaymentPlanItems);

}

package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IRepaymentPlanDao;
import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.domain.RepaymentPlanItem;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IRepaymentPlanService;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class RepaymentPlanService implements IRepaymentPlanService {

	@Resource
	private IRepaymentPlanDao repaymentPlanDao;

	@Override
	public boolean insertRepaymentPlan(RepaymentPlan repaymentPlan) {
		// TODO Auto-generated method stub

		repaymentPlanDao.insert("REPAYMENTPLAN.insertIntorepaymentPlan", repaymentPlan);
		return true;

	}

	@Override
	public Integer insertRepaymentPlanReturnId(RepaymentPlan repaymentPlan) {
		// TODO Auto-generated method stub
		Integer repaymentPlanId = (Integer) repaymentPlanDao.insertObject("REPAYMENTPLAN.insertIntorepaymentPlan",
				repaymentPlan);
		return repaymentPlanId;
	}

	@Override
	public boolean insertRepaymentPlanItem(List<RepaymentPlanItem> passRepaymentPlanItem) {
		// TODO Auto-generated method stub

		repaymentPlanDao.batchInsert("REPAYMENTPLANITEM.insertIntorepaymentPlanItem", passRepaymentPlanItem);
		return true;

	}

	@Override
	public Pagination queryRepaymentPlanItemDataGrid(Integer repaymentPlanId, Pagination pagination) {
		// TODO Auto-generated method stub
// return repaymentPlanDao.queryForPaginatedList("REPAYMENTPLANITEM.selectRepaymentPlanItem",
// "REPAYMENTPLANITEM.countRepaymentPlanItem", repaymentPlanId, pagination.getStartResult(),
// pagination.getPageSize());
		return repaymentPlanDao.queryRepaymentPlanItemDataGrid(repaymentPlanId, pagination);
	}

	@Override
	public RepaymentPlan searchRepaymentPlanByRepaymentPlanId(Integer repaymentPlanId) {
		// TODO Auto-generated method stub
		return (RepaymentPlan) repaymentPlanDao.queryUniqueRepayment(repaymentPlanId);
	}

	@Override
	public boolean updateRepaymentPlan(RepaymentPlan repaymentPlan) {
		// TODO Auto-generated method stub

		repaymentPlanDao.update("REPAYMENTPLAN.updateRepaymentPlanByrepaymentPlanId", repaymentPlan);
		return true;

	}

	@Override
	public boolean updateRepaymentPlanItem(RepaymentPlanItem repaymentPlanItem) {
		// TODO Auto-generated method stub

		repaymentPlanDao.update("REPAYMENTPLANITEM.updateRepaymentPlanItemByrepaymentPlanItemId", repaymentPlanItem);
		return true;

	}

	@Override
	public Integer searchRepaymentPlan(RepaymentPlan passRepaymentPlan) {
		// TODO Auto-generated method stub
		return (Integer) repaymentPlanDao.queryUnique("REPAYMENTPLAN.countRepaymentPlan", passRepaymentPlan);
	}

	@Override
	public Pagination queryRepaymentPlanDataGrid(RepaymentPlan repaymentPlan, Pagination pagination,
			String fuzzyQueryValue, Integer searchFlag) {
		// TODO Auto-generated method stub
		Pagination resultpagination = new Pagination();
		if (searchFlag == null) {
			// 初始化查询
			resultpagination = repaymentPlanDao.queryForPaginatedRepaymentList(repaymentPlan, pagination);
			// repaymentPlan
		} else if (searchFlag == 0) {
			// 模糊查询
			resultpagination = repaymentPlanDao.fuzzyQueryForPaginatedRepaymentList(fuzzyQueryValue, pagination);
		} else if (searchFlag == 1) {
			// 高级查询
			resultpagination = repaymentPlanDao.queryForPaginatedRepaymentList(repaymentPlan, pagination);
		} else {
			resultpagination = repaymentPlanDao.queryForPaginatedRepaymentList(repaymentPlan, pagination);
		}
		return resultpagination;
	}

	@Override
	public boolean insertRepaymentPlanAndItem(RepaymentPlan passRepaymentPlan,
			List<RepaymentPlanItem> passRepaymentPlanItems) {
		// TODO Auto-generated method stub
		Integer returnRepaymentPlanId = null;
		returnRepaymentPlanId = (Integer) repaymentPlanDao.insertRepaymentPlanObjectReturnId(passRepaymentPlan);
		if (returnRepaymentPlanId != null) {
			for (int i = 0; i < passRepaymentPlanItems.size(); i++) {
				passRepaymentPlanItems.get(i).setRepaymentPlanId(returnRepaymentPlanId);
			}
			repaymentPlanDao.batchInsertRepaymentPlanItemList(passRepaymentPlanItems);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateRepaymentPlanAndItem(RepaymentPlan passRepaymentPlan,
			List<RepaymentPlanItem> passRepaymentPlanItems) {
		// TODO Auto-generated method stub
		repaymentPlanDao.updateRepaymentPlan(passRepaymentPlan);
		repaymentPlanDao.batchUpdateRepaymentPlanItemList(passRepaymentPlanItems);
		return true;
	}

	@Override
	public List<RepaymentPlan> returnComboboxRepayment(Integer id, String q) {
		// TODO Auto-generated method stub
		RepaymentPlan passRepaymentPlan = new RepaymentPlan();
		passRepaymentPlan.setRepaymentPlanId(id);
		passRepaymentPlan.setRepaymentPlanName(q);
		return (List<RepaymentPlan>) repaymentPlanDao.queryList("REPAYMENTPLAN.selectRepaymentPlanByIdOrName",
				passRepaymentPlan);
	}

	@Override
	public String getEarlyType(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return (String) repaymentPlanDao.queryUnique("REPAYMENTPLAN.selectEarlyTypeByCAId", creditapplicationId);
	}

	@Override
	public RepaymentPlan returnRepaymentPlan(Integer repaymentPlanId) {
		// TODO Auto-generated method stub
		RepaymentPlan repaymentPlan = new RepaymentPlan();
		repaymentPlan.setRepaymentPlanId(repaymentPlanId);
		return (RepaymentPlan) repaymentPlanDao.queryUnique("REPAYMENTPLAN.selectRepaymentPlanByIdOrName",
				repaymentPlan);
	}

	@Override
	public List<RepaymentPlanItem> searchRepaymentPlanItems(Integer repaymentPlanId) {
		// TODO Auto-generated method stub
		return (List<RepaymentPlanItem>) repaymentPlanDao.queryList("REPAYMENTPLANITEM.selectRepaymentPlanItem", repaymentPlanId);
	}
}

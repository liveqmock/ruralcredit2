package com.creditease.rc.dao.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRepaymentPlanDao;
import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.domain.RepaymentPlanItem;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class RepaymentPlanDao extends BaseDao implements IRepaymentPlanDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public Pagination queryForPaginatedRepaymentList(RepaymentPlan repaymentPlan, Pagination pagination) {
		// TODO Auto-generated method stub
		Pagination resultPagination = new Pagination();
		repaymentPlan.setNominalInterestRate(decimalFormat4(repaymentPlan.getNominalInterestRate()));
		repaymentPlan.setFirstServiceFree(decimalFormat4(repaymentPlan.getFirstServiceFree()));
		repaymentPlan.setFollowupServiceFree(decimalFormat4(repaymentPlan.getFollowupServiceFree()));
		resultPagination = baseDao.queryForPaginatedList("REPAYMENTPLAN.selectRepaymentPlan",
				"REPAYMENTPLAN.countRepaymentPlan", repaymentPlan, pagination.getStartResult(),
				pagination.getPageSize());
		List<RepaymentPlan> tempRepaymentPlanList = resultPagination.getRows();
		// List<RepaymentPlan> resultRepaymentPlanList = new ArrayList<RepaymentPlan>();
		for (int i = 0; i < tempRepaymentPlanList.size(); i++) {
			RepaymentPlan tempRepaymentPlan = new RepaymentPlan();
			tempRepaymentPlan = tempRepaymentPlanList.get(i);
			Double getNominalInterestRate = decimalFormat2(tempRepaymentPlan.getNominalInterestRate());
			Double getFirstServiceFree = decimalFormat2(tempRepaymentPlan.getFirstServiceFree());
			Double getFollowupServiceFree = decimalFormat2(tempRepaymentPlan.getFollowupServiceFree());
			tempRepaymentPlan.setNominalInterestRate(getNominalInterestRate);
			tempRepaymentPlan.setFirstServiceFree(getFirstServiceFree);
			tempRepaymentPlan.setFollowupServiceFree(getFollowupServiceFree);

		}
		return resultPagination;
	}

	@Override
	public Pagination fuzzyQueryForPaginatedRepaymentList(String fuzzyQueryValue, Pagination pagination) {
		// TODO Auto-generated method stub
		Pagination resultPagination = new Pagination();
		resultPagination = baseDao.queryForPaginatedList("REPAYMENTPLAN.selectRepaymentPlanOnFuzzy",
				"REPAYMENTPLAN.countRepaymentPlanOnFuzzy", fuzzyQueryValue, pagination.getStartResult(),
				pagination.getPageSize());
		List<RepaymentPlan> tempRepaymentPlanList = resultPagination.getRows();
		// List<RepaymentPlan> resultRepaymentPlanList = new ArrayList<RepaymentPlan>();
		for (int i = 0; i < tempRepaymentPlanList.size(); i++) {
			RepaymentPlan tempRepaymentPlan = new RepaymentPlan();
			tempRepaymentPlan = tempRepaymentPlanList.get(i);
			Double getNominalInterestRate = decimalFormat2(tempRepaymentPlan.getNominalInterestRate());
			Double getFirstServiceFree = decimalFormat2(tempRepaymentPlan.getFirstServiceFree());
			Double getFollowupServiceFree = decimalFormat2(tempRepaymentPlan.getFollowupServiceFree());
			tempRepaymentPlan.setNominalInterestRate(getNominalInterestRate);
			tempRepaymentPlan.setFirstServiceFree(getFirstServiceFree);
			tempRepaymentPlan.setFollowupServiceFree(getFollowupServiceFree);

		}
		return resultPagination;
	}

	@Override
	public RepaymentPlan queryUniqueRepayment(Integer repaymentPlanId) {
		// TODO Auto-generated method stub

		RepaymentPlan repaymentPlan = (RepaymentPlan) baseDao.queryUnique(
				"REPAYMENTPLAN.selectRepaymentPlanByrepaymentPlanId", repaymentPlanId);
		repaymentPlan.setNominalInterestRate(decimalFormat2(repaymentPlan.getNominalInterestRate()));
		repaymentPlan.setFirstServiceFree(decimalFormat2(repaymentPlan.getFirstServiceFree()));
		repaymentPlan.setFollowupServiceFree(decimalFormat2(repaymentPlan.getFollowupServiceFree()));
		return repaymentPlan;
	}

	@Override
	public Integer insertRepaymentPlanObjectReturnId(RepaymentPlan repaymentPlan) {
		// TODO Auto-generated method stub
		repaymentPlan.setNominalInterestRate(decimalFormat4(repaymentPlan.getNominalInterestRate()));
		repaymentPlan.setFirstServiceFree(decimalFormat4(repaymentPlan.getFirstServiceFree()));
		repaymentPlan.setFollowupServiceFree(decimalFormat4(repaymentPlan.getFollowupServiceFree()));
		return (Integer) baseDao.insertObject("REPAYMENTPLAN.insertIntorepaymentPlan", repaymentPlan);
	}

	@Override
	public void updateRepaymentPlan(RepaymentPlan repaymentPlan) {
		// TODO Auto-generated method stub
		repaymentPlan.setNominalInterestRate(decimalFormat4(repaymentPlan.getNominalInterestRate()));
		repaymentPlan.setFirstServiceFree(decimalFormat4(repaymentPlan.getFirstServiceFree()));
		repaymentPlan.setFollowupServiceFree(decimalFormat4(repaymentPlan.getFollowupServiceFree()));
		baseDao.update("REPAYMENTPLAN.updateRepaymentPlanByrepaymentPlanId", repaymentPlan);
	}

	@Override
	public Pagination queryRepaymentPlanItemDataGrid(Integer repaymentPlanId, Pagination pagination) {
		// TODO Auto-generated method stub

		Pagination resultPagination = new Pagination();
		resultPagination = baseDao.queryForPaginatedList("REPAYMENTPLANITEM.selectRepaymentPlanItem",
				"REPAYMENTPLANITEM.countRepaymentPlanItem", repaymentPlanId, pagination.getStartResult(),
				pagination.getPageSize());
		List<RepaymentPlanItem> tempRepaymentPlanItemList = resultPagination.getRows();
		// List<RepaymentPlanItem> resultRepaymentPlanItemList = new ArrayList<RepaymentPlanItem>();
		for (int i = 0; i < tempRepaymentPlanItemList.size(); i++) {
			RepaymentPlanItem tempRepaymentPlanItem = new RepaymentPlanItem();
			tempRepaymentPlanItem = tempRepaymentPlanItemList.get(i);
			Double getPrincipal = decimalFormat2(tempRepaymentPlanItem.getPrincipal());
			Double getInterest = decimalFormat2(tempRepaymentPlanItem.getInterest());
			Double getServiceFree = decimalFormat2(tempRepaymentPlanItem.getServiceFree());
			tempRepaymentPlanItem.setPrincipal(getPrincipal);
			tempRepaymentPlanItem.setInterest(getInterest);
			tempRepaymentPlanItem.setServiceFree(getServiceFree);

		}
		resultPagination.setPageSize(20);
		return resultPagination;
	}

	@Override
	public void batchInsertRepaymentPlanItemList(List<RepaymentPlanItem> passRepaymentPlanItems) {
		// TODO Auto-generated method stub

		for (int i = 0; i < passRepaymentPlanItems.size(); i++) {
			RepaymentPlanItem tempRepaymentPlanItem = passRepaymentPlanItems.get(i);
			Double getPrincipal = decimalFormat4(tempRepaymentPlanItem.getPrincipal());
			Double getInterest = decimalFormat4(tempRepaymentPlanItem.getInterest());
			Double getServiceFree = decimalFormat4(tempRepaymentPlanItem.getServiceFree());
			tempRepaymentPlanItem.setPrincipal(getPrincipal);
			tempRepaymentPlanItem.setInterest(getInterest);
			tempRepaymentPlanItem.setServiceFree(getServiceFree);
		}
		baseDao.batchInsert("REPAYMENTPLANITEM.insertIntorepaymentPlanItem", passRepaymentPlanItems);
	}

	@Override
	public void batchUpdateRepaymentPlanItemList(List<RepaymentPlanItem> passRepaymentPlanItems) {
		// TODO Auto-generated method stub
		for (int i = 0; i < passRepaymentPlanItems.size(); i++) {
			RepaymentPlanItem tempRepaymentPlanItem = passRepaymentPlanItems.get(i);
			Double getPrincipal = decimalFormat4(tempRepaymentPlanItem.getPrincipal());
			Double getInterest = decimalFormat4(tempRepaymentPlanItem.getInterest());
			Double getServiceFree = decimalFormat4(tempRepaymentPlanItem.getServiceFree());
			tempRepaymentPlanItem.setPrincipal(getPrincipal);
			tempRepaymentPlanItem.setInterest(getInterest);
			tempRepaymentPlanItem.setServiceFree(getServiceFree);
		}
		baseDao.batchUpdate("REPAYMENTPLANITEM.updateRepaymentPlanItemByrepaymentPlanItemId", passRepaymentPlanItems);
	}

	/**
	 * 
	 * @param param Double类型的数据
	 * @return 乘以100保留两位小数后的数据
	 */
	private Double decimalFormat2(Double param) {
		DecimalFormat decimalFormat2 = new DecimalFormat("#.00");
		if (param != null) {
			return Double.parseDouble(decimalFormat2.format(param * 100));
		} else {
			return param;
		}
	}

	/**
	 * 
	 * @param param Double类型的数据
	 * @return 除以100保留四位小数后的数据
	 */
	private Double decimalFormat4(Double param) {
		DecimalFormat decimalFormat4 = new DecimalFormat("#.0000");
		if (param != null) {
			return Double.parseDouble(decimalFormat4.format(param / 100));
		} else {
			return param;
		}
	}

}

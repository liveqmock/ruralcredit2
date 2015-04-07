package com.creditease.rc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.dao.IStatisticalinfoDao;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.LoanRealAndPlanning;
import com.creditease.rc.vo.OverDueListVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class StatisticalinfoDao implements IStatisticalinfoDao {
	@Resource
	private IBaseDao baseDao;

	@Override
	public Pagination queryLoanPortfolioList(Map<String, String> map, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("STATISTICALINFO.queryLoanPortfolioList",
				"STATISTICALINFO.countLoanPortfolioList", map, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public Pagination queryOverdueInfoList(Map<String, String> map, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("STATISTICALINFO.queryCreditApplicationList",
				"STATISTICALINFO.countCreditApplicationList", map, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public List<HashMap<String, Object>> queryListForLoanPort(Map<String, String> map) {
		// TODO Auto-generated method stub
		return (List<HashMap<String, Object>>) baseDao.queryList("STATISTICALINFO.queryLoanPortfolioList", map);
	}

	@Override
	public Pagination queryNewReceivablesDataGrid(Map<String, String> pramMap, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("STATISTICALINFO.queryReceivablesList",
				"STATISTICALINFO.countReceivablesList", pramMap, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public List<LoanRealAndPlanning> queryLoanRealAndPlanningList(Map<String, String> quertyMap) {
		// TODO Auto-generated method stub
		return (List<LoanRealAndPlanning>) baseDao.queryList("STATISTICALINFO.queryRealAndPlanning", quertyMap);
	}

	@Override
	public List<OverDueListVo> queryForOverDueListEX(Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return (List<OverDueListVo>) baseDao.queryList("STATISTICALINFO.queryCreditApplicationList", pramMap);
	}

	@Override
	public List<DepartmentEntity> queryDepartmentEntities(Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return (List<DepartmentEntity>) baseDao.queryList("STATISTICALINFO.queryDepartmentEntities", pramMap);
	}
}

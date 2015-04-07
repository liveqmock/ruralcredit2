package com.creditease.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.dao.IStatisticalinfoDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IStatisticalinfoService;
import com.creditease.rc.vo.LoanRealAndPlanning;
import com.creditease.rc.vo.OverDueListVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class StatisticalinfoService implements IStatisticalinfoService {

	@Resource
	private IStatisticalinfoDao statisticalinfoDao;

	@Override
	public Pagination queryLoanPortfolioList(Map<String, String> map, Pagination pagination) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryLoanPortfolioList(map, pagination);
	}

	@Override
	public Pagination queryOverdueInfoList(Map<String, String> map, Pagination pagination) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryOverdueInfoList(map, pagination);
	}

	@Override
	public List<HashMap<String, Object>> queryListForLoanPort(Map<String, String> map) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryListForLoanPort(map);
	}

	@Override
	public Pagination queryNewReceivablesDataGrid(Map<String, String> pramMap, Pagination pagination) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryNewReceivablesDataGrid(pramMap, pagination);
	}

	@Override
	public List<LoanRealAndPlanning> queryLoanRealAndPlanningList(Map<String, String> quertyMap) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryLoanRealAndPlanningList(quertyMap);
	}

	@Override
	public List<OverDueListVo> queryForOverDueListEX(Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryForOverDueListEX(pramMap);
	}

	@Override
	public List<DepartmentEntity> queryDepartmentEntities(Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return statisticalinfoDao.queryDepartmentEntities(pramMap);
	}
}

package com.creditease.rc.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IContractCompoundNucleusHistoryDao;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

@Repository
public class ContractCompoundNucleusHistoryDao implements
		IContractCompoundNucleusHistoryDao {
	@Resource
	private IBaseDao baseDao;
	@Override
	public Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination) {
		return baseDao.queryForPaginatedList("CONTRACTANDLOAN.selectContractHistoryList", "CONTRACTANDLOAN.countContractHistoryList", queryMap,
				pagination.getStartResult(), pagination.getPageSize());
	}
}

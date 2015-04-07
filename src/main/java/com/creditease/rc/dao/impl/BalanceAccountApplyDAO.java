package com.creditease.rc.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.dao.IBalanceAccountApplyDAO;
import com.creditease.rc.domain.BalanceAccountApply;
import com.creditease.rc.domain.BalanceAccountApplyVo;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
@Repository
public class BalanceAccountApplyDAO implements IBalanceAccountApplyDAO {

	@Resource
	private BaseDao baseDao;

   
    public Long insert(BalanceAccountApply record) {
    	return (Long)baseDao.insertObject("BALANCEACCOUNTAPPLY.insert", record);
    }
    @Override
	public Pagination accountApplyHistoryDateGrid(Map<String, String> queryMap, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("BALANCEACCOUNTAPPLY.accountApplyHistoryDateGrid", "BALANCEACCOUNTAPPLY.countAccountApplyHistoryDateGrid", queryMap, pagination.getStartResult(), pagination.getPageSize());
	}
	@Override
	public BalanceAccountApplyVo queryBalanceAccountApplyByPrimaryKey(
			Long balanceAccountApplyId) {
		// TODO Auto-generated method stub
		return (BalanceAccountApplyVo) baseDao.queryUnique("BALANCEACCOUNTAPPLY.queryBalanceAccountApplyByPrimaryKey",balanceAccountApplyId);
	}
	@Override
	public Pagination queryBalanceAccountApply(
			Map<String, String> queryMap, Pagination pagination) {
		return baseDao.queryForPaginatedList("BALANCEACCOUNTAPPLY.selectPrepareCreditApplication", "BALANCEACCOUNTAPPLY.countByselectPrepareCreditApplication",
				queryMap,pagination.getStartResult(), pagination.getPageSize());
	}
	@Override
	public int dynamicUpdate(BalanceAccountApplyVo balanceAccountApplyVo) {
		if (balanceAccountApplyVo.getBalanceAccountApplyId() != null) {
			return baseDao.update("BALANCEACCOUNTAPPLY.dynamicUpdate", balanceAccountApplyVo);
		}
		return 0;
	}
}
package com.creditease.rc.dao.impl;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFinanceStateDao;
import com.creditease.rc.domain.FinanceState;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public class FinanceStateDao extends BaseDao implements IFinanceStateDao {

	@Override
	public Integer insertFinanceState(FinanceState financeState) {
		return (Integer) this.insertObject("FINANCESTATE.insertFinanceState", financeState);
	}

	@Override
	public Integer updateFinanceState(FinanceState financeState) {
		return this.update("FINANCESTATE.updateFinanceState", financeState);
	}

	@Override
	public FinanceState selectFinanceState(FinanceState financeState) {
		return (FinanceState) this.queryUnique("FINANCESTATE.selectFinanceState", financeState);
	}

	@Override
	public Integer getBizId() {
		return (Integer) this.queryUnique("FINANCESTATE.getBizId");
	}

}

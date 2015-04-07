package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.FinanceState;
/**
 * 财务状态   无用
 * @author xubingzhao
 *
 */
public interface IFinanceStateDao {
	/**
	 * 财务状态
	 * @param financeState 财务状态VO
	 * @return FinanceState
	 */
	public Integer insertFinanceState(FinanceState financeState);
	/**
	 * 
	 * @param financeState 财务状态VO
	 * @return Integer
	 */
	public Integer updateFinanceState(FinanceState financeState);
	/**
	 * 
	 * @param financeState 财务状态VO
	 * @return FinanceState
	 */
	public FinanceState selectFinanceState(FinanceState financeState);
	/**
	 * 
	 * @return  Integer
	 */
	public Integer getBizId();
}

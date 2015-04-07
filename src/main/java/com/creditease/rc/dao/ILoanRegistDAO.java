package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.LoanRegist;

/**
 *	放款登记详细
 * @author zhangman
 *
 */
public interface ILoanRegistDAO {
	/**
	 * 批量增加放款登记详细
	 * @param loanRegistList 放款登记详细LIST
	 */
	public void addBatch(List<LoanRegist> loanRegistList);
	/**
	 * 查询放款登记明细
	 * @param groupLoanRegistId 放款登记id
	 * @return 放款登记明细List
	 */
	public List<LoanRegist> selectLoanRegistList(int groupLoanRegistId);
	/**
	 * 拼凑放款登记明细(展示)
	 * @param groupLoanRegistId 放款登记id
	 * @return 放款登记明细List
	 */
	public List<LoanRegist> selectLoanRegists(int groupLoanRegistId);
}

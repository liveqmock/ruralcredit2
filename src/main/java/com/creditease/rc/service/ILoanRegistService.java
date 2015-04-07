package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.LoanRegist;


/**
 * 放款 登记详细service
 * @author zhangman
 *
 */
public interface ILoanRegistService {

	/**
	 * 批量插入放款登记详细
	 * @param loanRegistList 放款登记详细对象
	 */
	public void addBatchLoanRegist(List<LoanRegist> loanRegistList);
	/**
	 * 查询放款登记明细
	 * @param groupLoanRegistId 放款登记id
	 * @return 放款登记明细List
	 */
	public List<LoanRegist> selectLoanRegistList(int groupLoanRegistId);
	
	/**
	 * 拼凑放款登记明细(展示)
	 * @param creditApplicationId 申请id
	 * @return 放款登记明细List
	 */
	public List<LoanRegist> selectLoanRegists(int creditApplicationId);
}

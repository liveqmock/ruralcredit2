package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.GroupLoanRegistDetail;

/**
 *	放款登记详细
 * @author zhangman
 *
 */
public interface IGroupLoanRegistDetailDAO {
	/**
	 * 批量增加放款登记详细
	 * @param groupLoanRegistDetailList 放款登记详细LIST
	 */
	public void addBatch(List<GroupLoanRegistDetail> groupLoanRegistDetailList);
}

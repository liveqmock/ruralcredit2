package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.GroupLoanRegistDetail;

/**
 * 放款 登记详细service
 * @author zhangman
 *
 */
public interface IGroupLoanRegistDetailService {

	/**
	 * 批量插入放款登记详细
	 * @param groupLoanRegistDetailList 放款登记详细对象
	 */
	public void addBatchGroupLoanDetail(List<GroupLoanRegistDetail> groupLoanRegistDetailList);
}

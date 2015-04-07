package com.creditease.rc.service.impl;

import java.util.Map;

import com.creditease.rc.domain.BalanceAccountApply;
import com.creditease.rc.domain.BalanceAccountApplyVo;
import com.creditease.rc.framework.pager.Pagination;

public interface IBalanceAccountApplyService {
	//添加对账申请信息
	public boolean insertAccountApply(BalanceAccountApply record);
	//申请过的历史数据
	Pagination accountApplyHistoryDateGrid(Map<String, String> queryMap,
			Pagination pagination);
	//根据balanceAccountApplyId查询数据
	BalanceAccountApplyVo queryBalanceAccountApplyByPrimaryKey(
			Long balanceAccountApplyId);
	//查询要申请的数据
	public Pagination accountApplyDateGrid(Map<String, String> queryMap, Pagination pagination);
	//审批
	public boolean dynamicUpdate(BalanceAccountApplyVo balanceAccountApplyVo);
}

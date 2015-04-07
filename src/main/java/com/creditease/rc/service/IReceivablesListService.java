package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.OverDueListVo;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IReceivablesListService {
	/**
	 * 
	 * @param pagination 分页对象
	 * @param creditapplication 信贷申请对象
	 * @return 分页对象
	 */
	Pagination receivablesDataGrid(Pagination pagination, CreditApplication creditapplication);

	/**
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @param param 默认还款方式
	 * @param p 撤销标识
	 * @param accountInfoId 卡信息主键
	 * @return 是否更改成功
	 */
	boolean updateDefaultReturnmentWay(Integer creditapplicationId, String param, String p, Integer accountInfoId);

	int checkForEarlyReturn(Integer creditapplicationId);

	Pagination queryOverdueGrid(Map<String, String> pramMap, Pagination pagination);

}

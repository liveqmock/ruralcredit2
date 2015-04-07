package com.creditease.rc.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 合同复核历史
 * 
 * @author luohongjie
 *
 */
public interface IContractCompoundNucleusHistoryService {

	/**
	 * 
	* @param creditApplication
	 *            小组信贷申请对象
	 * @param fuzzyValue
	 *            模糊查询条件
	 * @param pagination
	 *            分页条件
	 * @param session HttpSession
	 * 
	 * @param sort 排序列
	 * @param order 顺序
	 * @return 合同复核历史 分页列表
	 */
	Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination);
}

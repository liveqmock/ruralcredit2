package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RefuseReason;
import com.creditease.rc.framework.pager.Pagination;

public interface IRefuseReasonService {
	public Message insert(RefuseReason  refuseReason);
	/**
	 * 据贷 
	 * @param refuseReason
	 * @return
	 */
	public Message updateRefuse(Long creditapplicationId,String section,String refuseReasons,String auditStatus);
	/**
	 * @author hongjieluo
	 * @param pagination
	 * @param pramMap
	 * 客户放弃列表
	 * @return
	 */
	Pagination customerGiveUpList(Pagination pagination, Map<String, String> pramMap);
	/**
	 * @author hongjieluo
	 * @param pagination
	 * @param pramMap
	 * 拒贷列表
	 * @return
	 */
	Pagination deniedLoansList(Pagination pagination,Map<String, String> pramMap);

    /**
     * 依据信贷申请ID查询变更原因
     */
    List<RefuseReason> selectRefuseReasonById(Long creditApplicationId);
}

package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.RlAuditDetail;

/**
 * 
 * @author xubingzhao
 *
 */
public interface IRlAuditDetailService {
	/**
	 * 根据信贷审批id查询借款人记录表的审批金额
	 * @param id 信贷审批id
	 * @return list
	 */
	public List<RlAuditDetail> selectChgMount(Integer id);

}

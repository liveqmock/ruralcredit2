package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.RlAuditDetail;

/**
 * 组员Dao层
 * @author xubingzhao
 *
 */
public interface IRlAuditDetailDao {
	/**
	 * 
	 * @param list 组员列表
	 */
	public void insert(List<RlAuditDetail>list);
	/**
	 * 根据信贷审批id查询借款人记录表的审批金额
	 * @param id 信贷审批id
	 * @return list
	 */
	public List<RlAuditDetail> selectChgMount(Integer id);
}

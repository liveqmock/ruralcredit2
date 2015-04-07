package com.creditease.rc.service;

import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.exception.AppBusinessException;

/**
 * 财务撤销接口
 * @author Administrator
 *
 */
public interface IFinanceMoneySingleUndoService{
	/**
	 * 财务撤销方法
	 * @param bizId 业务单号
	 * @param creditapplicationId 信贷申请单ID
	 * @throws AppBusinessException 业务异常
	 * @return boolean
	 */
	public Message singleUndo(Long bizId,Integer creditapplicationId)throws AppBusinessException;
}

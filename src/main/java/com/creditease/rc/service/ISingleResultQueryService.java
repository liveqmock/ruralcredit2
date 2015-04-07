package com.creditease.rc.service;

import com.creditease.rc.framework.exception.AppBusinessException;


/**
 * 单笔结果查询
 * @author Administrator
 *
 */
public interface ISingleResultQueryService {
	/**
	 * 单笔收款结果查询
	 * @param bizId  
	 * @throws AppBusinessException  
	 * @return boolean 
	 */
	public boolean singleReceiptResultQuery(String bizId)throws AppBusinessException;
	/**
	 * 单笔付款结果查询
	 * @param bizId  
	 * @throws AppBusinessException  
	 * @return boolean  
	 */
	public boolean singlePaymentResultQuery(String bizId)throws AppBusinessException;

}

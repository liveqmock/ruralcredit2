package com.creditease.rc.service;

import com.creditease.rc.domain.PayInfo;

/**
 * 
 * @author xubingzhao
 *
 */
public interface IPayInfoService {
	/**
	 * 持久化payInfo
	 * @param payInfo 要持久化的payInfo
	 * @return 持久化后的payInfo
	 */
	public Integer insertPayInfo(PayInfo payInfo);
	/**
	 * 查询汇款表
	 * @param payInfo 查询条件
	 * @return 查询结果
	 */
	public PayInfo selectPayInfo(PayInfo payInfo);
}

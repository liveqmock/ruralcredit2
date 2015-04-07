package com.creditease.rc.service;

import com.creditease.rc.domain.CustomerHistoryInfo;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author zhangman
 *
 */
public interface ICustomerHistoryInfoService {

	/**
	 * 
	 * @param customerHistoryInfo 客户历史信息
	 * @return  true：成功 false：失败
	 */
	public boolean addCustomerHistoryInfo(CustomerHistoryInfo customerHistoryInfo);
	
}

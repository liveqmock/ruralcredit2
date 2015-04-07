package com.creditease.rc.dao;

import com.creditease.rc.domain.CustomerHistoryInfo;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * @author zhangman
 *
 */
public interface ICustomerHistoryInfoDao {
	
 /**
  * 
  * @param customerHistoryInfo 客户历史信息对象 
  */
  public  void insert(CustomerHistoryInfo customerHistoryInfo);

	public int delete(int customerBasicInfoId);

}
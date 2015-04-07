package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICustomerHistoryInfoDao;
import com.creditease.rc.domain.CustomerHistoryInfo;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class CustomerHistoryInfoDao implements ICustomerHistoryInfoDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public void insert(CustomerHistoryInfo record) {
		baseDao.insert("CUSTOMERHISTORY.insert", record);
	}

	@Override
	public int delete(int customerBasicInfoId) {
		return baseDao.delete("CUSTOMERHISTORY.deleteBycustomerId", customerBasicInfoId);
	}
}
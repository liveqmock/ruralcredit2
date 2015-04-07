package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ICustomerHistoryInfoDao;
import com.creditease.rc.domain.CustomerHistoryInfo;
import com.creditease.rc.service.ICustomerHistoryInfoService;

 /**
  * 
  * @author zhangman
  *
  */
@Service
public class CustomerHistoryInfoService implements ICustomerHistoryInfoService {

	@Resource
	private ICustomerHistoryInfoDao customerHistoryInfoDao;

	@Override
	public boolean addCustomerHistoryInfo(CustomerHistoryInfo customerHistoryInfo) {
		customerHistoryInfoDao.insert(customerHistoryInfo);
		return true;
	}
}

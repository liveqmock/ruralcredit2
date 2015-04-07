package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IBorrowerserviceapptestDao;
import com.creditease.rc.domain.Borrowerserviceapptest;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class BorrowerserviceapptestDao implements IBorrowerserviceapptestDao {
	@Resource
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int borrowerServiceAppIdTest() {
		// TODO Auto-generated method stub
		return (Integer) baseDao.queryUnique("borrowerserviceapptest.borrowerServiceAppIdTest");
	}

	@Override
	public Borrowerserviceapptest selecetBorrowerappNameandId(int borrowerServiceAppIdTest) {
		// TODO Auto-generated method stub
		return (Borrowerserviceapptest) baseDao.queryUnique("borrowerserviceapptest.selecetBorrowerappNameandId",
				borrowerServiceAppIdTest);
	}

	@Override
	public int borrowersurveyIdTest(int borrowerServiceAppIdTest) {
		// TODO Auto-generated method stub
		return (Integer) baseDao.queryUnique("borrowerserviceapptest.borrowersurveyIdTest", borrowerServiceAppIdTest);
	}
}

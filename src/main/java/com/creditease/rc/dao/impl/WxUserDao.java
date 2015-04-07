package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWxUserDao;
import com.creditease.rc.domain.WxUser;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class WxUserDao implements IWxUserDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public WxUser queryWxUserByUserCode(String userCode) {
		return (WxUser) baseDao.queryUnique("WXUSER.queryWxUserByUserCode", userCode);
	}

	@Override
	public boolean inserWxUser(WxUser wxUser) {
		baseDao.insert("WXUSER.abatorgenerated_insert", wxUser);
		return true;
	}

	@Override
	public boolean updateWxUser(WxUser wxUser) {
		baseDao.update("WXUSER.abatorgenerated_updateByPrimaryKeySelective", wxUser);
		return true;
	}

	@Override
	public WxUser qyChangedGetPrizeUser(String userCode) {
		return (WxUser) baseDao.queryUnique("WXUSER.qyChangedGetPrizeUser",userCode);
	}
}
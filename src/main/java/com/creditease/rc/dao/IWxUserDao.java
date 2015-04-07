package com.creditease.rc.dao;

import com.creditease.rc.domain.WxUser;

public interface IWxUserDao {

	WxUser queryWxUserByUserCode(String userCode);

	boolean inserWxUser(WxUser wxUser);

	boolean updateWxUser(WxUser wxUser);
	
	WxUser qyChangedGetPrizeUser(String userCode);
}
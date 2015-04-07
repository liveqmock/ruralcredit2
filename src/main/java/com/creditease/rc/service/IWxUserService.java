package com.creditease.rc.service;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxUser;

public interface IWxUserService {

	Message saveWxUserByUserCode(WxUser wxUser);

	WxUser queryWxUserByUserCode(String userCode);

	Message inserWxUser(WxUser wxUser);
	
	Message updateWxUser(WxUser wxUser);
	
	WxUser qyChangedGetPrizeUser(String userCode);

}

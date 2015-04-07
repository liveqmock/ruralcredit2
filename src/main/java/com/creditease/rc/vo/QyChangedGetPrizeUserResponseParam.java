package com.creditease.rc.vo;

import com.creditease.rc.domain.WxUser;

public class QyChangedGetPrizeUserResponseParam extends WebServiceMessage {
	private WxUser wXUser; //用户信息

	public WxUser getwXUser() {
		return wXUser;
	}

	public void setwXUser(WxUser wXUser) {
		this.wXUser = wXUser;
	}
}

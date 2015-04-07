package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWxUserDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxUser;
import com.creditease.rc.service.IWxUserService;

@Service
public class WxUserService implements IWxUserService {

	@Resource
	private IWxUserDao wxUserDao;

	@Override
	public Message saveWxUserByUserCode(WxUser wxUser) {
		Message message = new Message();
		String userCode = wxUser.getUserCode();
		WxUser rUser = this.queryWxUserByUserCode(userCode);

		if (rUser == null) {
			wxUser.setAggregateScore(0l);
			wxUser.setUsedScore(0l);
			wxUser.setScore(0l);
			message = this.inserWxUser(wxUser);
		} else {
			wxUser.setWxUserId(rUser.getWxUserId());
			message = this.updateWxUser(wxUser);
		}

		return message;
	}

	@Override
	public WxUser queryWxUserByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return wxUserDao.queryWxUserByUserCode(userCode);
	}

	@Override
	public Message inserWxUser(WxUser wxUser) {
		Message message = new Message();
		boolean s = wxUserDao.inserWxUser(wxUser);
		message.setSuccess(s);
		return message;
	}

	@Override
	public Message updateWxUser(WxUser wxUser) {
		Message message = new Message();
		boolean s = wxUserDao.updateWxUser(wxUser);
		message.setSuccess(s);
		return message;
	}

	@Override
	public WxUser qyChangedGetPrizeUser(String userCode) {
		
		return wxUserDao.qyChangedGetPrizeUser(userCode);
	}

}

package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWxPrizeDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.service.IWxPrizeService;
import com.creditease.rc.vo.Prize;

@Service
public class WxPrizeService implements IWxPrizeService {

	@Resource
	private IWxPrizeDao wxPrizeDao;

	@Override
	public Message batchInsetPrizeList(List<WxPrize> prizes) {
		Message message = new Message();
		boolean s = wxPrizeDao.batchInsetPrizeList(prizes);
		message.setSuccess(s);
		return message;
	}

}

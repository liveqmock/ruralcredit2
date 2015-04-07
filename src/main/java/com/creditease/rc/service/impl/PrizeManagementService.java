package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IPrizeManagementService;
import com.creditease.rc.service.IWxPrizeService;
import com.creditease.rc.service.IWxupriRecordService;

@Service
public class PrizeManagementService implements IPrizeManagementService {

	@Resource
	private IWxPrizeService wxPrizeService;
	@Resource
	private IWxupriRecordService wxupriRecordService;

	@Override
	public Message constructPrizeTemp(List<WxPrize> prizes) {
		return wxPrizeService.batchInsetPrizeList(prizes);
	}

	@Override
	public Pagination queryUpriRecordList(Map<String, String> queryMap, Pagination pagination) {
		return wxupriRecordService.queryUpriRecordList(queryMap, pagination);
	}
}

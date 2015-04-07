package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWxupriRecordDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxupriRecord;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IWxupriRecordService;

@Service
public class WxupriRecordService implements IWxupriRecordService {
	@Resource
	private IWxupriRecordDao wxupriRecordDao;

	@Override
	public Message insertWxupriRecord(WxupriRecord wxupriRecord) {
		Message message = new Message();
		boolean s = wxupriRecordDao.insertWxupriRecord(wxupriRecord);
		message.setSuccess(s);
		return message;
	}

	@Override
	public Message updateWxupriRecord(WxupriRecord wxupriRecord) {
		Message message = new Message();
		boolean s = wxupriRecordDao.updateWxupriRecord(wxupriRecord);
		message.setSuccess(s);
		return message;
	}

	@Override
	public List<WxupriRecord> queryWxupriRecordList(Map<String, String> queryMap) {
		return wxupriRecordDao.queryWxupriRecordList(queryMap);
	}

	@Override
	public Pagination queryUpriRecordList(Map<String, String> queryMap, Pagination pagination) {
		return wxupriRecordDao.queryUpriRecordList(queryMap, pagination);
	}

	@Override
	public Message saveWxupriRecord(WxupriRecord wxupriRecord) {
		Message message = new Message();
		Long getWxUpriRecordId = wxupriRecord.getWxUpriRecordId();
		if (getWxUpriRecordId == null) {
			message = this.insertWxupriRecord(wxupriRecord);
		} else {
			message = this.updateWxupriRecord(wxupriRecord);
		}

		return message;
	}

}

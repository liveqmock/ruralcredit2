package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWxAnswersRecordDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxAnswersRecord;
import com.creditease.rc.service.IWxAnswersRecordService;

@Service
public class WxAnswersRecordService implements IWxAnswersRecordService {

	@Resource
	private IWxAnswersRecordDao wxAnswersRecordDao;

	@Override
	public Message batchInsertAnswersRecords(List<WxAnswersRecord> answersRecords) {
		Message message = new Message();
		boolean s = wxAnswersRecordDao.batchInsertAnswersRecords(answersRecords);
		message.setSuccess(s);
		return message;
	}
}

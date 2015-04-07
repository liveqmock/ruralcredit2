package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxAnswersRecord;

public interface IWxAnswersRecordService {

	Message batchInsertAnswersRecords(List<WxAnswersRecord> answersRecords);

}

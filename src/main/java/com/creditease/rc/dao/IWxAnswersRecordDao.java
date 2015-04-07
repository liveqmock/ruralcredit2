package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.WxAnswersRecord;


public interface IWxAnswersRecordDao {

	boolean batchInsertAnswersRecords(List<WxAnswersRecord> answersRecords);
}
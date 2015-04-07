package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWxAnswersRecordDao;
import com.creditease.rc.domain.WxAnswersRecord;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class WxAnswersRecordDao implements IWxAnswersRecordDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean batchInsertAnswersRecords(List<WxAnswersRecord> answersRecords) {
		baseDao.batchInsert("WXANSWERSRECORD.abatorgenerated_insert", answersRecords);
		return true;
	}
}
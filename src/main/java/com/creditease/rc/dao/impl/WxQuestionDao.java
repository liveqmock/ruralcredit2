package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWxQuestionDao;
import com.creditease.rc.domain.WxQuestion;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class WxQuestionDao implements IWxQuestionDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean batchInsertQuestionList(List<WxQuestion> insertQList) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("WXQUESTION.abatorgenerated_insert", insertQList);
		return true;
	}

	@Override
	public List<WxQuestion> queryAll() {
		// TODO Auto-generated method stub
		return (List<WxQuestion>) baseDao.queryList("WXQUESTION.queryAll");
	}
}
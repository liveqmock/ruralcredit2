package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWxAnswerDao;
import com.creditease.rc.domain.WxAnswer;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class WxAnswerDao implements IWxAnswerDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean batchInsertAnswerList(List<WxAnswer> insertAList) {
		baseDao.batchInsert("WXANSWER.abatorgenerated_insert", insertAList);
		return true;
	}

	@Override
	public List<WxAnswer> queryAll() {
		// TODO Auto-generated method stub
		return (List<WxAnswer>) baseDao.queryList("WXANSWER.queryAll");
	}
}
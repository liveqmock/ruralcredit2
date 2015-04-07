package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWxupriRecordDao;
import com.creditease.rc.domain.WxupriRecord;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

@Repository
public class WxupriRecordDao implements IWxupriRecordDao {
	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean insertWxupriRecord(WxupriRecord wxupriRecord) {
		baseDao.insert("WXUPRIRECORD.abatorgenerated_insert", wxupriRecord);
		return true;
	}

	@Override
	public boolean updateWxupriRecord(WxupriRecord wxupriRecord) {
		baseDao.update("WXUPRIRECORD.abatorgenerated_updateByPrimaryKeySelective", wxupriRecord);
		return true;
	}

	@Override
	public List<WxupriRecord> queryWxupriRecordList(Map<String, String> queryMap) {
		return (List<WxupriRecord>) baseDao.queryList("WXUPRIRECORD.queryWxupriRecordList", queryMap);
	}

	@Override
	public Pagination queryUpriRecordList(Map<String, String> queryMap, Pagination pagination) {
		return baseDao.queryForPaginatedList("WXUPRIRECORD.queryUpriRecordList", "WXUPRIRECORD.countUpriRecordList", queryMap, pagination.getStartResult(), pagination.getPageSize());
	}

}
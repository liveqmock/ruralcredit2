package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.domain.WxupriRecord;
import com.creditease.rc.framework.pager.Pagination;

@Repository
public interface IWxupriRecordDao {

	boolean insertWxupriRecord(WxupriRecord wxupriRecord);

	boolean updateWxupriRecord(WxupriRecord wxupriRecord);

	List<WxupriRecord> queryWxupriRecordList(Map<String, String> queryMap);

	Pagination queryUpriRecordList(Map<String, String> queryMap, Pagination pagination);

}
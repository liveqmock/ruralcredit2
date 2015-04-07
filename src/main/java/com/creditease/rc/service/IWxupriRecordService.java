package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxupriRecord;
import com.creditease.rc.framework.pager.Pagination;

public interface IWxupriRecordService {

	Message insertWxupriRecord(WxupriRecord wxupriRecord);
	
	Message updateWxupriRecord(WxupriRecord wxupriRecord);

	List<WxupriRecord> queryWxupriRecordList(Map<String, String> queryMap);

	Pagination queryUpriRecordList(Map<String, String> queryMap, Pagination pagination);

	Message saveWxupriRecord(WxupriRecord wxupriRecord);

}

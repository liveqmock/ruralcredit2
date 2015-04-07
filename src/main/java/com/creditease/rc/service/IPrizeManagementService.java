package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.framework.pager.Pagination;

public interface IPrizeManagementService {

	Message constructPrizeTemp(List<WxPrize> prizes);

	Pagination queryUpriRecordList(Map<String, String> queryMap, Pagination pagination);

}

package com.creditease.rc.service;

import java.util.Map;

import com.creditease.rc.domain.Blacklist;
import com.creditease.rc.framework.pager.Pagination;
import org.apache.log4j.Logger;

public interface IBlacklistService {
    static Logger logger = Logger.getLogger(IBusinessStatusChangeService.class);
	public boolean dynamicInsert(Blacklist blacklist);

	public int dynamicUpdate(Blacklist blacklist);

	public Pagination queryBlicklistList(Map<String, String> queryMap, Pagination pagination);

	public boolean insertBlacklist(Blacklist blacklist);

	public boolean updateBlacklist(Blacklist blacklist);

	public int checkIdNumber(String credentialsNumber);

	public boolean updateOrInsertCustomer(Blacklist blacklist);

    /**
     * 定时跑批
     插入逾期信息到数据库
     */
    public void insertBlackListJob();

}

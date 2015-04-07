package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Blacklist;
import com.creditease.rc.framework.pager.Pagination;

public interface IBlacklistDao {

	public boolean dynamicInsert(Blacklist blacklist);

	public int dynamicUpdate(Blacklist blacklist);

	public Pagination queryBlicklistList(Map<String, String> map, Pagination pagination);

	public int checkIdNumber(String credentialsNumber);

	public int updateCustomer(Blacklist blacklist);

    /**
     * 定时跑批
     插入逾期信息到数据库
     * @param blacklistList
     */
    public void insertNewBlackListList(List<Blacklist> blacklistList);
}

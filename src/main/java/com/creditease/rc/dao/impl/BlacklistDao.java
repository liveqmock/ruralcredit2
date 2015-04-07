package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IBlacklistDao;
import com.creditease.rc.domain.Blacklist;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class BlacklistDao implements IBlacklistDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean dynamicInsert(Blacklist blacklist) {
		// TODO Auto-generated method stub
		baseDao.insert("BLACKLIST.dynamicInsert", blacklist);
		return true;
	}

	@Override
	public int dynamicUpdate(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return baseDao.update("BLACKLIST.dynamicUpdate", blacklist);
	}

	@Override
	public Pagination queryBlicklistList(Map<String, String> map, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("BLACKLIST.queryBlacklistVoList", "BLACKLIST.countBlacklistVoList", map,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public int checkIdNumber(String credentialsNumber) {
		// TODO Auto-generated method stub
		return (Integer) baseDao.queryUnique("BLACKLIST.checkIdNumber", credentialsNumber);
	}

	@Override
	public int updateCustomer(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return baseDao.update("BLACKLIST.updateCustomer", blacklist);
	}

    /**  定时跑批
     插入逾期信息到数据库
     *  批量插入黑名单信息
     * @param blacklistList
     */
    @Override
    public void   insertNewBlackListList(List<Blacklist> blacklistList) {
         baseDao.batchInsert("BLACKLIST.dynamicInsert",blacklistList);
    }
}

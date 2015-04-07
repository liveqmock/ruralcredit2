package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICustomerConsultDAO;
import com.creditease.rc.dao.ISystemInfoDAO;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.SystemAnnouncement;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * @author liuli
 * 2013-05-14
 *
 */
@Repository
public class SystemInfoDAO extends BaseDao implements ISystemInfoDAO {

	@Resource
	private BaseDao baseDao;
	
	@Override
	public Pagination searchByFuzzyValueMap(Map searchValue, Pagination pagination) {
		return queryForPaginatedList("SYSTEMANNOUNCEMENT.selectMap", "SYSTEMANNOUNCEMENT.countMap", searchValue,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public int addSystemAnnouncement(SystemAnnouncement systemAnnouncement) {
		return (Integer) baseDao.insertObject("SYSTEMANNOUNCEMENT.insert", systemAnnouncement);
	}

	@Override
	public List<SystemAnnouncement> getSystemAnnouncementById(int id) {
		List l = baseDao.queryList("SYSTEMANNOUNCEMENT.selectById", id);
		return l;
	}

	@Override
	public int updateSystemAnnouncement(SystemAnnouncement systemAnnouncement) {
		int i = baseDao.update("SYSTEMANNOUNCEMENT.updateSystemAnnouncement", systemAnnouncement);
		return i;
	}

	@Override
	public int deleteSystemAnnouncement(int saId) {
		return baseDao.delete("SYSTEMANNOUNCEMENT.delSystemAnnouncementById", saId);
	}

}

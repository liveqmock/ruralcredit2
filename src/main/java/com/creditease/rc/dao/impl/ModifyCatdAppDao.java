package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IModifyCatdAppDao;
import com.creditease.rc.domain.ModifyCatdApp;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

@Repository
public class ModifyCatdAppDao implements IModifyCatdAppDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean saveModifyCatdApp(ModifyCatdApp modifyCatdApp) {
		// TODO Auto-generated method stub
		baseDao.insert("MODIFYCATDAPP.insert", modifyCatdApp);
		return true;
	}

	@Override
	public Pagination queryAccountInformationChangeList(Pagination pagination, Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("MODIFYCATDAPP.accountInformationChangeEntity",
				"MODIFYCATDAPP.accountInformationChangeCount", pramMap, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public ModifyCatdApp queryModifyCatdAppByPrimarKey(Long modifyCatdAppId) {
		// TODO Auto-generated method stub
		return (ModifyCatdApp) baseDao.queryUnique("MODIFYCATDAPP.queryModifyCatdAppByPrimarKey", modifyCatdAppId);
	}

	@Override
	public boolean updateModifyCatdApp(ModifyCatdApp modifyCatdApp) {
		// TODO Auto-generated method stub
		baseDao.update("MODIFYCATDAPP.dynamicUpdate", modifyCatdApp);
		return true;
	}

	@Override
	public List<Long> queryModifyCatdAppInSHENQINGZHONG(Long applicationId) {
		// TODO Auto-generated method stub
		return (List<Long>) baseDao.queryList("MODIFYCATDAPP.queryModifyCatdAppInSHENQINGZHONG", applicationId);
	}

}

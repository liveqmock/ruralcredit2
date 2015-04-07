package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IClientApplyHistoryDao;
import com.creditease.rc.domain.ClientApplyHistory;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class ClientApplyHistoryDao implements IClientApplyHistoryDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean dynamicInsert(ClientApplyHistory clientApplyHistory) {
		// TODO Auto-generated method stub
		baseDao.insert("CLIENTAPPLYHISTORY.dynamicInsert", clientApplyHistory);
		return true;
	}

	@Override
	public boolean dynamicUpdate(ClientApplyHistory clientApplyHistory) {
		// TODO Auto-generated method stub
		baseDao.update("CLIENTAPPLYHISTORY.dynamicUpdate", clientApplyHistory);
		return true;
	}

	@Override
	public boolean dynamicDelete(ClientApplyHistory clientApplyHistory) {
		// TODO Auto-generated method stub
		baseDao.delete("CLIENTAPPLYHISTORY.dynamicDelete", clientApplyHistory);
		return true;
	}

	@Override
	public List<ClientApplyHistory> dynamicSelect(ClientApplyHistory queryClientApplyHistory) {
		// TODO Auto-generated method stub
		return (List<ClientApplyHistory>) baseDao
				.queryList("CLIENTAPPLYHISTORY.dynamicSelect", queryClientApplyHistory);
	}

}

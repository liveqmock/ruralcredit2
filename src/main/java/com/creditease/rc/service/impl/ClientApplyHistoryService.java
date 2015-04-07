package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.dao.IClientApplyHistoryDao;
import com.creditease.rc.domain.ClientApplyHistory;
import com.creditease.rc.service.IClientApplyHistoryService;

@Service
public class ClientApplyHistoryService implements IClientApplyHistoryService {

	@Resource
	private IClientApplyHistoryDao clientApplyHistoryDao;

	@Override
	public boolean dynamicInsert(ClientApplyHistory clientApplyHistory) {
		// TODO Auto-generated method stub
		return clientApplyHistoryDao.dynamicInsert(clientApplyHistory);
	}

	@Override
	public boolean dynamicUpdate(ClientApplyHistory clientApplyHistory) {
		// TODO Auto-generated method stub
		return clientApplyHistoryDao.dynamicUpdate(clientApplyHistory);
	}

	@Override
	public boolean dynamicDelete(ClientApplyHistory clientApplyHistory) {
		// TODO Auto-generated method stub
		return clientApplyHistoryDao.dynamicDelete(clientApplyHistory);
	}

	@Override
	public List<ClientApplyHistory> dynamicSelect(ClientApplyHistory queryClientApplyHistory) {
		// TODO Auto-generated method stub
		return clientApplyHistoryDao.dynamicSelect(queryClientApplyHistory);
	}

}

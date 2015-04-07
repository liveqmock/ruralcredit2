package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.ClientApplyHistory;

public interface IClientApplyHistoryService {

	boolean dynamicInsert(ClientApplyHistory clientApplyHistory);

	boolean dynamicUpdate(ClientApplyHistory clientApplyHistory);

	boolean dynamicDelete(ClientApplyHistory clientApplyHistory);

	List<ClientApplyHistory> dynamicSelect(ClientApplyHistory queryClientApplyHistory);
}

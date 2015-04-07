package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.ClientApplyHistory;

public interface IClientApplyHistoryDao {

	boolean dynamicInsert(ClientApplyHistory clientApplyHistory);

	boolean dynamicUpdate(ClientApplyHistory clientApplyHistory);

	boolean dynamicDelete(ClientApplyHistory clientApplyHistory);

	List<ClientApplyHistory> dynamicSelect(ClientApplyHistory queryClientApplyHistory);

}

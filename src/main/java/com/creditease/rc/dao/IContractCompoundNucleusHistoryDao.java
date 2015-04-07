package com.creditease.rc.dao;

import java.util.Map;

import com.creditease.rc.framework.pager.Pagination;

public interface IContractCompoundNucleusHistoryDao {

	Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination);
}

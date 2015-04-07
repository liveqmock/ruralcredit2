package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.ModifyCatdApp;
import com.creditease.rc.framework.pager.Pagination;

public interface IModifyCatdAppDao {

	boolean saveModifyCatdApp(ModifyCatdApp modifyCatdApp);

	Pagination queryAccountInformationChangeList(Pagination pagination, Map<String, String> pramMap);

	ModifyCatdApp queryModifyCatdAppByPrimarKey(Long modifyCatdAppId);

	boolean updateModifyCatdApp(ModifyCatdApp modifyCatdApp);

	List<Long> queryModifyCatdAppInSHENQINGZHONG(Long applicationId);

}

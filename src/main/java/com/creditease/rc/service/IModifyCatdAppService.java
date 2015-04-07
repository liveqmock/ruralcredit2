package com.creditease.rc.service;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ModifyCatdApp;
import com.creditease.rc.framework.pager.Pagination;

import java.util.List;
import java.util.Map;

public interface IModifyCatdAppService {

	Message saveModifyCatdApp(ModifyCatdApp modifyCatdApp);

	Pagination queryAccountInformationChangeList(Pagination pagination, Map<String, String> pramMap);

	ModifyCatdApp queryModifyCatdAppByPrimarKey(Long modifyCatdAppId);

	Message approvalModifyCatdApp(Long modifyCatdAppId, Long accountInfoId, String type, Long creditapplicationId);

	/*保存审批信息*/
	Message approvalModifyCatdAppBasedPre(ModifyCatdApp app);

	List<Long> queryModifyCatdAppInSHENQINGZHONG(Integer creditApplicationId);

}

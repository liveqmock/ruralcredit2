package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Urge;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.UrgeAndLinkmanVo;
import com.creditease.rc.vo.UrgeVo;

public interface IUrgeService {

	boolean insert(Urge urge);

	boolean dynamicUpdate(Urge urge);

	Message saveUrge(Urge urge);

	Pagination queryUrgeList(Pagination pagination, Map<String, String> pramMap);

	Message saveUrgeAndLinkmanVo(UrgeAndLinkmanVo urgeAndLinkmanVo);

	UrgeVo queryUrgeListByUrgeId(Long urgeId);

	UrgeVo selectUrgeByCreditApplicationId(Long creditApplicationId);

	List<UrgeVo> querUrgeCountList();

	List<Urge> queryUrgeBycreditapplicationIds(String creditapplicationIds);

	Message InsertCreateUrgeHistory();

	Message insertCreateUrge();

}

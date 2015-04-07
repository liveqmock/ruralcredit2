package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Urge;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.UrgeAndLinkmanVo;
import com.creditease.rc.vo.UrgeVo;

public interface IUrgeDao {

	boolean insert(Urge urge);

	boolean dynamicUpdate(Urge urge);

	Pagination queryUrgeList(Pagination pagination, Map<String, String> pramMap);

	UrgeVo queryUrgeListByUrgeId(Long urgeId);

	UrgeVo selectUrgeByCreditApplicationId(Long creditApplicationId);

	List<UrgeVo> querUrgeCountList();

	List<Urge> queryUrgeBycreditapplicationIds(String creditapplicationIds);

	List<Long> queryInThePayment();

	List<Urge> queryUrgeListByMap(Map<String, String> queryMap);

	boolean deleteUrgeForSave(Urge urgeforquery);

}

package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IUrgeDao;
import com.creditease.rc.domain.Urge;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.UrgeAndLinkmanVo;
import com.creditease.rc.vo.UrgeVo;

@Repository
public class UrgeDao implements IUrgeDao {
	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean insert(Urge urge) {
		// TODO Auto-generated method stub
		baseDao.insert("URGE.insert", urge);
		return true;
	}

	@Override
	public boolean dynamicUpdate(Urge urge) {
		// TODO Auto-generated method stub
		baseDao.update("URGE.dynamicUpdate", urge);
		return true;
	}

	@Override
	public Pagination queryUrgeList(Pagination pagination, Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("URGE.queryUrgeListEntity", "URGE.queryUrgeListCount", pramMap,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public UrgeVo queryUrgeListByUrgeId(Long urgeId) {
		return (UrgeVo) baseDao.queryUnique("URGE.queryUrgeListByUrgeId", urgeId);
	}

	@Override
	public UrgeVo selectUrgeByCreditApplicationId(Long creditApplicationId) {
		return (UrgeVo) baseDao.queryUnique("URGE.selectUrgeByCreditApplicationId", creditApplicationId);
	}

	@Override
	public List<UrgeVo> querUrgeCountList() {
		return (List<UrgeVo>) baseDao.queryList("URGE.querUrgeCountList");
	}

	@Override
	public List<Urge> queryUrgeBycreditapplicationIds(String creditapplicationIds) {
		// TODO Auto-generated method stub
		return (List<Urge>) baseDao.queryList("URGE.queryUrgeBycreditapplicationIds", creditapplicationIds);
	}

	@Override
	public List<Long> queryInThePayment() {
		// TODO Auto-generated method stub
		return (List<Long>) baseDao.queryList("URGE.queryInThePayment");
	}

	@Override
	public List<Urge> queryUrgeListByMap(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<Urge>) baseDao.queryList("URGE.queryUrgeListByMap", queryMap);
	}

	@Override
	public boolean deleteUrgeForSave(Urge urgeforquery) {
		// TODO Auto-generated method stub
		baseDao.delete("URGE.deleteUrgeForSave", urgeforquery);
		return true;
	}

}

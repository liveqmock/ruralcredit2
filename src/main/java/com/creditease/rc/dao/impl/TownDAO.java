package com.creditease.rc.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ITownDAO;
import com.creditease.rc.domain.Town;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.Area;
import com.creditease.rc.vo.QueryCheckTownsVo;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class TownDAO extends BaseDao implements ITownDAO {

	@Resource
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Town select(Town town) {
		return (Town) baseDao.queryUnique("TOWNINFO.select", town);
	}

	@Override
	public Pagination selectAllTown(Area townlist, Pagination pagination) {
		return baseDao.queryForPaginatedList("TOWNINFO.selectAll", "TOWNINFO.countByPage", townlist,
				pagination.getStartResult(), pagination.getPageSize());
	};

	@Override
	public List<Town> selectTown(Town town) {
		return (List<Town>) baseDao.queryList("TOWNINFO.selectByparentid", town);
	}

	/*
	 * 添加数据
	 * 
	 * @see
	 * com.creditease.rc.dao.ITownDAO#addTown(com.creditease.rc.domain.Town)
	 */
	@Override
	public Long addTown(Town town) {
		return (Long) baseDao.insertObject("TOWNINFO.insert", town);
	}

	@Override
	public void addTownList(List<Town> townList) {
		baseDao.batchInsert("TOWNINFO.insert", townList);
	}

	@Override
	public void updateTown(Town town) {
		baseDao.update("TOWNINFO.update", town);
	}

	@Override
	public List<Town> queryTownshipInfo(String type, Long townshipinfoid) {
		List<Town> towns = new ArrayList<Town>();
		if ("1".equals(type)) {
			towns = (List<Town>) baseDao.queryList("TOWNINFO.selectByTown", townshipinfoid);
		} else {
			towns = (List<Town>) baseDao.queryList("TOWNINFO.selectByVillage", townshipinfoid);
		}
		return towns;
	};

	@Override
	public Map<String, Integer> queryCountryTownByVillageId(Long townshipinfoid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map = (Map<String, Integer>) baseDao.queryUnique("TOWNINFO.queryCountryTownByVillageId", townshipinfoid);
		return map;
	}

	@Override
	public Pagination selectFuzzy(String fuzzy, Pagination pagination) {
		return baseDao.queryForPaginatedList("TOWNINFO.selectFuzzy", "TOWNINFO.countFuzzy", fuzzy,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public Map searchNscByVillageId(Long townshipinfoid) {
		return (Map) queryUnique("TOWNINFO.selectNscByVillageId", townshipinfoid);
	}

	@Override
	public List<QueryCheckTownsVo> queryCheckTowns(long parentId) {
		// TODO Auto-generated method stub
		return (List<QueryCheckTownsVo>) baseDao.queryList("TOWNINFO.queryCheckTowns", parentId);
	}

}

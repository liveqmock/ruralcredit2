package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ISalesPlanningDao;
import com.creditease.rc.domain.SalesPlanning;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.SalesPlanningTable;

@Repository
public class SalesPlanningDao implements ISalesPlanningDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean insert(SalesPlanning salesPlanning) {
		// TODO Auto-generated method stub
		baseDao.insert("SALESPLANNING.dynamicInsert", salesPlanning);
		return true;
	}

	@Override
	public boolean update(SalesPlanning salesPlanning) {
		// TODO Auto-generated method stub
		baseDao.insert("SALESPLANNING.dynamicUpdate", salesPlanning);
		return true;
	}

	@Override
	public List<SalesPlanningTable> querySalesPlanningTable(Map<String, String> map) {
		// TODO Auto-generated method stub
		return (List<SalesPlanningTable>) baseDao.queryList("SALESPLANNING.querySalesPlanningTable", map);
	}

	@Override
	public boolean batchInsert(List<SalesPlanning> salesPlannings) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("SALESPLANNING.dynamicInsert", salesPlannings);
		return true;
	}

	@Override
	public boolean insertSalesPlanningList(List<SalesPlanning> insertList) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("SALESPLANNING.dynamicInsert", insertList);
		return true;
	}

	@Override
	public boolean updateSalesPlanningList(List<SalesPlanning> updateList) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("SALESPLANNING.dynamicUpdate", updateList);
		return true;
	}

    @Override
    public List<Integer> searchSalesPlanningDel(Map m) {
        return (List<Integer>)baseDao.queryList("SALESPLANNING.searchSalesPlanningDel", m);
    }

    @Override
    public boolean saveSalesPlannings(List<SalesPlanning> list) {
        baseDao.batchInsert("SALESPLANNING.saveSalesPlanning", list);
        return true;
    }

    @Override
    public void deleteSalesPlannings(List<Integer> ids) {
        baseDao.batchDelete("SALESPLANNING.deleteSalesPlanning", ids);
    }

    @Override
    public List<SalesPlanning> searchSalesPlanningByCase(Map<String, Object> map) {
        return (List<SalesPlanning>) baseDao.queryList("SALESPLANNING.searchSalesPlanningByCase", map);
    }
}

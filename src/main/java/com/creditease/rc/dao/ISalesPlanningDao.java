package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.SalesPlanning;
import com.creditease.rc.vo.SalesPlanningTable;

public interface ISalesPlanningDao {

	public boolean insert(SalesPlanning salesPlanning);

	public boolean update(SalesPlanning salesPlanning);

	public List<SalesPlanningTable> querySalesPlanningTable(Map<String, String> map);

	public boolean batchInsert(List<SalesPlanning> salesPlannings);

	public boolean insertSalesPlanningList(List<SalesPlanning> insertList);

	public boolean updateSalesPlanningList(List<SalesPlanning> updateList);

    public List<Integer> searchSalesPlanningDel(Map m);

    public boolean saveSalesPlannings(List<SalesPlanning> list);

    public void deleteSalesPlannings(List<Integer> ids);

    public List<SalesPlanning> searchSalesPlanningByCase(Map<String, Object> map);

}

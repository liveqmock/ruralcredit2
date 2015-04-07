package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.SalesPlanning;
import com.creditease.rc.vo.SalesPlanningTable;
import com.creditease.rc.vo.SalesPlanningTableVo;

public interface ISalesPlanningService {

	public boolean insert(SalesPlanning salesPlanning);

	public boolean update(SalesPlanning salesPlanning);

	public Map<String, SalesPlanningTable> querySalesPlanningTable(String areaDepartmentIds, String year);

	public boolean batchInsert(List<SalesPlanning> salesPlannings);

	public Message saveSalesPlanningTableVo(SalesPlanningTableVo salesPlanningTableVo);

	boolean insertSalesPlanningList(List<SalesPlanning> insertList);

	boolean updateSalesPlanningList(List<SalesPlanning> updateList);

    public Message saveSalesPlanning();

    public Map<String, SalesPlanningTable> searchSalesPlanningByCase(SalesPlanning salesPlanning);

}

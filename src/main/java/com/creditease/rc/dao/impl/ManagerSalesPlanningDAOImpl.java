package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.dao.IManagerSalesPlanningDAO;
import com.creditease.rc.domain.ManagerSalesPlanning;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;

import org.springframework.stereotype.Repository;
@Repository
public class ManagerSalesPlanningDAOImpl implements IManagerSalesPlanningDAO {

	@Resource
	private BaseDao baseDao;

    @Override
	public Pagination selectManagerSalesPlanningLoanSum(Pagination pagination,
			Map<String, Object> map) {
    	Pagination pagination1=baseDao.queryForPaginatedList("RL_EMPLOYEE.selectManagerSalesPlanningLoanSum", "RL_EMPLOYEE.selectManagerSalesPlanningAllCount", map, pagination.getStartResult(), pagination.getPageSize());
		if(pagination1.getTotal()>0){
			pagination=pagination1;
		}else{
			pagination=baseDao.queryForPaginatedList("RL_EMPLOYEE.selectManagerSalesPlanningLoanSum2", "RL_EMPLOYEE.selectManagerSalesPlanningAllCount2", map, pagination.getStartResult(), pagination.getPageSize());
		}
	 return pagination;
    }
    @Override
	public Pagination selectManagerSalesPlanningContractMoney(Pagination pagination,
			Map<String, Object> map) {
    	Pagination pagination1=baseDao.queryForPaginatedList("RL_EMPLOYEE.selectManagerSalesPlanningContractMoney", "RL_EMPLOYEE.selectManagerSalesPlanningContractListCount", map, pagination.getStartResult(), pagination.getPageSize());
		if(pagination1.getTotal()>0){
			pagination=pagination1;
		}else{
			pagination=baseDao.queryForPaginatedList("RL_EMPLOYEE.selectManagerSalesPlanningContractMoney2", "RL_EMPLOYEE.selectManagerSalesPlanningContractListCount2", map, pagination.getStartResult(), pagination.getPageSize());
		}
		return pagination;
	}
	@Override
	public Long insert(ManagerSalesPlanning record) {
		return (Long)baseDao.insertObject("MANAGERSALESPLANNING.batchInsert", record);
	}
	@Override
	public void batchInsert(List entityList) {
		baseDao.batchInsert("MANAGERSALESPLANNING.batchInsert", entityList);
	}
	@Override
	public List<ManagerSalesPlanning> selectManagerSales(Map<String, Object> map){
		return (List<ManagerSalesPlanning>) baseDao.queryList("RL_EMPLOYEE.selectManagerSales",map);
	}
	@Override
	public void batchUpdate(List<ManagerSalesPlanning> managerSalesPlanningList) {
		baseDao.batchUpdate("MANAGERSALESPLANNING.batchUpdate", managerSalesPlanningList);
	}
	@Override
	public void batchInsertContractMoney(
			List<ManagerSalesPlanning> managerSalesPlanningList) {
		baseDao.batchInsert("MANAGERSALESPLANNING.batchInsert", managerSalesPlanningList);
	}
	@Override
	public void batchUpdateContractMoney(List<ManagerSalesPlanning> managerSalesPlanningList) {
		baseDao.batchUpdate("MANAGERSALESPLANNING.batchUpdate", managerSalesPlanningList);
	}
	@Override
	public List<ManagerSalesPlanning> selectManagerSalesContractMoney(Map<String, Object> map) {
		return (List<ManagerSalesPlanning>) baseDao.queryList("RL_EMPLOYEE.selectManagerSalesContractMoney",map);
	}
}
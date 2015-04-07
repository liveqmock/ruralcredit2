package com.creditease.rc.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICustomerReturnVisitDao;
import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerReturnVisitVo;
import com.creditease.rc.vo.ReturnPlanForShowVo;

@Repository
public class CustomerReturnVisitDao extends BaseDao implements ICustomerReturnVisitDao {

	@Override
	public Pagination selectPagination(CustomerReturnVisitVo customerReturnVisitVo, Pagination pagination) {
		// TODO Auto-generated method stub
		customerReturnVisitVo.setNowDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return queryForPaginatedList("RL_CUSTOMER_RETURN_VISIT.selectClass", "RL_CUSTOMER_RETURN_VISIT.countselectClass", customerReturnVisitVo, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public List<CustomerReturnVisitVo> selectPresent(CustomerReturnVisitVo customerReturnVisitVo) {
		// TODO Auto-generated method stub
		return (List<CustomerReturnVisitVo>) queryList("RL_CUSTOMER_RETURN_VISIT.selectPresent", customerReturnVisitVo);
	}

	@Override
	public List<CustomerReturnVisitVo> selectPresentAgain(CustomerReturnVisitVo customerReturnVisitVo) {
		// TODO Auto-generated method stub
		return (List<CustomerReturnVisitVo>) queryList("RL_CUSTOMER_RETURN_VISIT.selectPresentAgain", customerReturnVisitVo);
	}

	@Override
	public long insert(CustomerReturnVisit customerReturnVisit) {
		// TODO Auto-generated method stub
		return (Long) insertObject("RL_CUSTOMER_RETURN_VISIT.insert", customerReturnVisit);
	}

	@Override
	public int update(CustomerReturnVisit customerReturnVisit) {
		return (Integer) update("RL_CUSTOMER_RETURN_VISIT.update", customerReturnVisit);
	}

	@Override
	public void insertBatch(List<CustomerReturnVisit> customerReturnVisitList) {
		batchInsert("RL_CUSTOMER_RETURN_VISIT.insert", customerReturnVisitList);
	}

	@Override
	public CustomerReturnVisitVo selectByID(CustomerReturnVisitVo customerReturnVisitVo) {
		return (CustomerReturnVisitVo) queryUnique("RL_CUSTOMER_RETURN_VISIT.selectClass", customerReturnVisitVo);
	}

	@Override
	public List<CustomerReturnVisitVo> selectList(CustomerReturnVisitVo customerReturnVisitVo) {
		// TODO Auto-generated method stub
		return (List<CustomerReturnVisitVo>) queryList("RL_CUSTOMER_RETURN_VISIT.selectPresent", customerReturnVisitVo);
	}

	@Override
	public List<Map> selectWarn(Map conditions) {
		// TODO Auto-generated method stub
		return (List<Map>) queryList("RL_CUSTOMER_RETURN_VISIT.selectWarn", conditions);
	}

	@Override
	public List<Map> selectDontCallBackOfThisMonth(Map conditions) {
		// TODO Auto-generated method stub
		return (List<Map>) queryList("RL_CUSTOMER_RETURN_VISIT.selectDontCallBackOfThisMonth", conditions);
	}

	@Override
	public List<CustomerReturnVisitVo> selectAllList(CustomerReturnVisitVo customerReturnVisitVo) {
		return (List<CustomerReturnVisitVo>) queryList("RL_CUSTOMER_RETURN_VISIT.selectClass", customerReturnVisitVo);
	}

	@Override
	public List<CustomerReturnVisit> selectToday(CustomerReturnVisitVo customerReturnVisitVo) {
		return (List<CustomerReturnVisit>) queryList("RL_CUSTOMER_RETURN_VISIT.selectToday", customerReturnVisitVo);
	}

	@Override
	public List<ReturnPlan> selectReturnPlanList(Date repaymentDates) {
		return (List<ReturnPlan>) queryList("RL_CUSTOMER_RETURN_VISIT.selectReturnPlan", repaymentDates);
	}

	@Override
	public Pagination haveNoVisitPlanList(Pagination pagination, Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return queryForPaginatedList("RL_CUSTOMER_RETURN_VISIT.haveNoVisitPlanListEntity", "RL_CUSTOMER_RETURN_VISIT.haveNoVisitPlanListCount", queryMap, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public boolean updateCusReViVSByCreIdNDat(Map<String, String> map) {
		update("RL_CUSTOMER_RETURN_VISIT.updateCusReViVSByCreIdNDat", map);
		return true;
	}
}

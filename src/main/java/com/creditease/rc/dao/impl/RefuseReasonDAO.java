package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRefuseReasonDAO;
import com.creditease.rc.domain.RefuseReason;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

@Repository
public class RefuseReasonDAO implements IRefuseReasonDAO {

	@Resource
	private IBaseDao baseDao;
	@Override
	public long insert(RefuseReason refuseReason) {
		return (Long) baseDao.insertObject("RefuseReason.insert", refuseReason);
	}

	@Override
	public void batchInsert(List<RefuseReason> refuseReasonList) {
		// TODO Auto-generated method stub
		  baseDao.batchInsert("RefuseReason.insert", refuseReasonList);
	}

	/**
	 * @author hongjieluo
	 * @param pagination
	 * @param pramMap  
	 * 客户放弃分页查询
	 */
	@Override
	public Pagination customerGiveUpList(Pagination pagination, Map<String, String> pramMap) {
		return baseDao.queryForPaginatedList("RefuseReason.queryCustomerGiveUpListEntity", "RefuseReason.queryCustomerGiveUpListCount",
				pramMap, pagination.getStartResult(), pagination.getPageSize());
	}
	/**
	 * @author hongjieluo
	 * @param pagination
	 * @param pramMap 
	 * 拒贷分页查询
	 */
	@Override
	public Pagination deniedLoansList(Pagination pagination, Map<String, String> pramMap) {
		return baseDao.queryForPaginatedList("RefuseReason.queryDenideLoansListEntity", "RefuseReason.queryDenideLoansListCount",
				pramMap, pagination.getStartResult(), pagination.getPageSize());
	}

    @Override
    public List<RefuseReason> selectRefuseReasonById(Long creditApplicationId) {
        return (List<RefuseReason>) baseDao.queryList("RefuseReason.selectRefuseReasonById", creditApplicationId);
    }
}

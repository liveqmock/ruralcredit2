package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICancelContractReasonDao;
import com.creditease.rc.domain.CancelContractReason;
import com.creditease.rc.framework.dao.impl.BaseDao;
@Repository
public class CancelContractReasonDao implements ICancelContractReasonDao {

	@Resource
	private BaseDao baseDao;
	
	@Override
	public Long saveCancelReason(CancelContractReason cancelContractReason) {
		return (Long)baseDao.insertObject("CANCELCONTRACTREASON.insert", cancelContractReason);
	}

	@Override
	public int updateCancelReasonHistory(Long creditapplicationId) {
		CancelContractReason key=new CancelContractReason();
		key.setCreditapplicationId(creditapplicationId);
		return baseDao.update("CANCELCONTRACTREASON.updateHistory", key);
	}
	@Override
	public CancelContractReason selectReason(Long creditapplicationId){
		return (CancelContractReason) baseDao.queryUnique("CANCELCONTRACTREASON.selectReason",creditapplicationId);
	}
}

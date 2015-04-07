package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ILaonBalanceDataDao;
import com.creditease.rc.domain.LaonBalanceData;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.LoanBalanceQuertData;

@Repository
public class LaonBalanceDataDao implements ILaonBalanceDataDao {
	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean dynamicInsert(LaonBalanceData balanceData) {
		// TODO Auto-generated method stub
		baseDao.insert("LAONBALANCEDATA.dynamicInsert", balanceData);
		return true;
	}

	@Override
	public boolean dynamicUpdate(LaonBalanceData balanceData) {
		// TODO Auto-generated method stub
		baseDao.update("LAONBALANCEDATA.dynamicUpdate", balanceData);
		return true;
	}

	@Override
	public boolean saveLaonBalanceDatas(List<LaonBalanceData> laonBalanceDatas) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("LAONBALANCEDATA.dynamicInsert", laonBalanceDatas);
		return true;
	}

	@Override
	public boolean deleteLaonBalanceData() {
		// TODO Auto-generated method stub
		baseDao.delete("LAONBALANCEDATA.deleteAll", 0l);
		return true;
	}

	@Override
	public List<LoanBalanceQuertData> queryLoanTableDatas(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<LoanBalanceQuertData>) baseDao.queryList("LAONBALANCEDATA.queryLoanTableDatas", queryMap);
	}

}

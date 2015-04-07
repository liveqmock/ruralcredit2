package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFamilydepositdebtDao;
import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class FamilydepositdebtDao extends BaseDao implements IFamilydepositdebtDao {

	@Resource
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void batchFamilydepositdebtList(List<Familydepositdebt> addfamilydepositdebts) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familydepositdebt.insertIntofamilydepositdebt", addfamilydepositdebts);

	}

	@Override
	public List<Familydepositdebt> querySelectFamilydepositdebt(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familydepositdebt> familydepositdebts = (List<Familydepositdebt>) baseDao.queryList(
				"familydepositdebt.selectfamilydepositdebtByPrimaryKey", borrowerSurveyId);
		return familydepositdebts;
	}

	@Override
	public void batchUpdateFamilyDepositDebtsList(List<Familydepositdebt> updatefamilydepositdebts) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("familydepositdebt.updatefamilydepositdebtByPrimaryKey", updatefamilydepositdebts);

	}

	@Override
	public void batchInsertfamilydepositdebtList(List<Familydepositdebt> addfamilydepositdebts) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familydepositdebt.insertIntofamilydepositdebt", addfamilydepositdebts);
	}
}

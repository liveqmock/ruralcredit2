package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFamilytotalcostDao;
import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class FamilytotalcostDao extends BaseDao implements IFamilytotalcostDao {

	@Resource
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void batchFamilytotalcostList(List<Familytotalcost> addfamilytotalcosts) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familytotalcost.insertIntofamilytotalcost", addfamilytotalcosts);

	}

	@Override
	public List<Familytotalcost> querySelectFamilytotalcost(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familytotalcost> familytotalcosts = (List<Familytotalcost>) baseDao.queryList(
				"familytotalcost.selectfamilytotalcostByPrimaryKey", borrowerSurveyId);
		return familytotalcosts;
	}

	@Override
	public void batchUpdateFamilytotalcostList(List<Familytotalcost> updatefamilytotalcosts) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("familytotalcost.updatefamilytotalcostByPrimaryKey", updatefamilytotalcosts);
	}

	@Override
	public void batchInsertfamilytotalcostList(List<Familytotalcost> addfamilytotalcosts) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familytotalcost.insertIntofamilytotalcost", addfamilytotalcosts);
	}
}

package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IHouseholdassertsDao;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class HouseholdassertsDao extends BaseDao implements IHouseholdassertsDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public void batchInsertToHoldasserts(List<Householdasserts> addhouseholdassertss) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("householdasserts.insertIntohouseholdasserts", addhouseholdassertss);
	}

	@Override
	public List<Householdasserts> querySelectHouseholdasserts(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Householdasserts> householdassertss = (List<Householdasserts>) baseDao.queryList(
				"householdasserts.selecthouseholdassertsByPrimaryKey", borrowerSurveyId);
		return householdassertss;
	}

	@Override
	public void batchUpdateHouseholdAssertsList(List<Householdasserts> updatehouseholdassertss) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("householdasserts.updatehouseholdassertsByPrimaryKey", updatehouseholdassertss);
	}

}

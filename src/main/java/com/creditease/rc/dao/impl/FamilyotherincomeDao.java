package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFamilyotherincomeDao;
import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class FamilyotherincomeDao extends BaseDao implements IFamilyotherincomeDao {

	@Resource
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * @author haoqiang
	 */
	public FamilyotherincomeDao() {
		super();
	}

	@Override
	public void batchFamilyotherincomeList(List<Familyotherincome> addfamilyotherincomes) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familyotherincome.insertIntofamilyotherincome", addfamilyotherincomes);

	}

	@Override
	public List<Familyotherincome> querySelectFamilyotherincome(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familyotherincome> familyotherincomes = (List<Familyotherincome>) baseDao.queryList(
				"familyotherincome.selectfamilyotherincomeByPrimaryKey", borrowerSurveyId);
		return familyotherincomes;
	}

	@Override
	public void batchUpdateFamilyOtherIncomeList(List<Familyotherincome> updatefamilyotherincomes) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("familyotherincome.updatefamilyotherincomeByPrimaryKey", updatefamilyotherincomes);
	}

	@Override
	public void batchInsertfamilyotherincomeList(List<Familyotherincome> addfamilyotherincomes) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familyotherincome.insertIntofamilyotherincome", addfamilyotherincomes);
	}

}

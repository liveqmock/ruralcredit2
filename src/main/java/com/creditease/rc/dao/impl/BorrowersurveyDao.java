package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IBorrowersurveyDao;
import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class BorrowersurveyDao extends BaseDao implements IBorrowersurveyDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public int updateBorrowersurvey(Borrowersurvey borrowersurvey) {
		// TODO Auto-generated method stub

		int rows = baseDao.update("borrowersurvey.updateborrowersurveyByPrimaryKey", borrowersurvey);
		return rows;
	}

	@Override
	public Borrowersurvey querySelectBorrowersurvey(int borrowerSurveyId) {
		// TODO Auto-generated method stub
// Borrowersurvey borrowersurvey = (Borrowersurvey) baseDao.queryUnique("borrowersurvey.selectborrowersurveyByPrimaryKey", borrowerSurveyId);
// return borrowersurvey;
		return null;
	}

	@Override
	public int searchBorrowerSurveyId(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		/*
		 * int borrowerSurveyId = (Integer) baseDao.queryUnique(
		 * "borrowersurvey.selectBorrowerSurveyIdByAppId",
		 * borrowerServiceAppId);
		 */
		Object borrowerSurveyIdByAppId = baseDao.queryUnique("borrowersurvey.selectBorrowerSurveyIdByAppId",
				borrowerServiceAppId);
		if (borrowerSurveyIdByAppId == null) {
			return 0;

		} else {
			return (Integer) borrowerSurveyIdByAppId;
		}
	}

	/**
	 * 添加
	 * 
	 * @param borrowerSurvey 上述合计
	 */
	@Override
	public void addBorrowerSurvey(Borrowersurvey borrowerSurvey) {
		baseDao.insert("borrowersurvey.insert", borrowerSurvey);
	}

}

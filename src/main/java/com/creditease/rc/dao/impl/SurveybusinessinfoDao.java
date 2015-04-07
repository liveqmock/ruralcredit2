package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ISurveybusinessinfoDao;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class SurveybusinessinfoDao extends BaseDao implements ISurveybusinessinfoDao {
	@Resource
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void insersurveybusinessinfo(Surveybusinessinfo surveybusinessinfo) {
		// TODO Auto-generated method stub
		baseDao.insert("surveybusinessinfo.insertIntosurveybusinessinfo", surveybusinessinfo);

	}

	@Override
	public Surveybusinessinfo querySelectSurveybusinessinfo(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		Surveybusinessinfo surveybusinessinfo = (Surveybusinessinfo) baseDao.queryUnique(
				"surveybusinessinfo.selectsurveybusinessinfoByPrimaryKey", borrowerSurveyId);
		return surveybusinessinfo;
	}

	@Override
	public void batchUpdateSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo) {
		// TODO Auto-generated method stub
		baseDao.update("surveybusinessinfo.updatesurveybusinessinfoByPrimaryKey", surveybusinessinfo);

	}
	
	@Override
	public List<Surveybusinessinfo> selectByBorrowerServiceAppId(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return (List<Surveybusinessinfo>) baseDao.queryList("surveybusinessinfo.selectSurveybusinessinfo", borrowerServiceAppId);
	}

}

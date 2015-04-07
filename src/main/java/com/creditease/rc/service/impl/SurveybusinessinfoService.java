package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ISurveybusinessinfoDao;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.service.ISurveybusinessinfoService;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class SurveybusinessinfoService implements ISurveybusinessinfoService {

	@Resource
	private ISurveybusinessinfoDao surveybusinessinfoDao;

	public ISurveybusinessinfoDao getSurveybusinessinfoDao() {
		return surveybusinessinfoDao;
	}

	public void setSurveybusinessinfoDao(ISurveybusinessinfoDao surveybusinessinfoDao) {
		this.surveybusinessinfoDao = surveybusinessinfoDao;
	}

	@Override
	public boolean insersurveybusinessinfo(Surveybusinessinfo surveybusinessinfo) {
		// TODO Auto-generated method stub
		String organizationName = surveybusinessinfo.getOrganizationName();
		if (organizationName != null && !"".equals(organizationName.trim())) {
			surveybusinessinfoDao.insersurveybusinessinfo(surveybusinessinfo);
		}
		return true;
	}

	@Override
	public boolean updateSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo) {
		// TODO Auto-generated method stub
		surveybusinessinfoDao.batchUpdateSurveyBusinessInfo(surveybusinessinfo);
		return true;
	}

	@Override
	public boolean addSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo) {
		// TODO Auto-generated method stub
		String organizationName = surveybusinessinfo.getOrganizationName();
		if (organizationName != null && !"".equals(organizationName.trim())) {
			surveybusinessinfoDao.insersurveybusinessinfo(surveybusinessinfo);
		}
		return true;
	}

	@Override
	public boolean insertSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo) {
		// TODO Auto-generated method stub
		surveybusinessinfoDao.insersurveybusinessinfo(surveybusinessinfo);
		return true;
	}

	// 保存经营情况的操作
	@Override
	public Surveybusinessinfo saveSurveybusinessinfo(Surveybusinessinfo passSurveybusinessinfo) {
		// TODO Auto-generated method stub
//		Integer borrowerServiceAppId = passSurveybusinessinfo.getBorrowerServiceAppId();
//		Surveybusinessinfo returnSurveybusinessinfo = new Surveybusinessinfo();
//		Integer surveyBusinessInfoId = passSurveybusinessinfo.getSurveyBusinessInfoId();
//		if (surveyBusinessInfoId == null) {
//			surveybusinessinfoDao.insert("surveybusinessinfo.insertsurveybusinessinfo", passSurveybusinessinfo);
//		} else {
//			surveybusinessinfoDao.update("surveybusinessinfo.updatesurveybusinessinfoByPrimaryKey",
//					passSurveybusinessinfo);
//		}
//		returnSurveybusinessinfo = searchSurveybusinessinfo(borrowerServiceAppId);
		return passSurveybusinessinfo;
	}

	// 查询经营情况
	@Override
	public Surveybusinessinfo searchSurveybusinessinfo(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		Surveybusinessinfo returnSurveybusinessinfo = new Surveybusinessinfo();
//		returnSurveybusinessinfo.setBorrowerServiceAppId(borrowerServiceAppId);
//		Surveybusinessinfo getSurveybusinessinfo = (Surveybusinessinfo) surveybusinessinfoDao.queryUnique(
//				"surveybusinessinfo.selectSurveybusinessinfoByBorrowerServiceAppId", borrowerServiceAppId);
//		if (getSurveybusinessinfo != null) {
//			return getSurveybusinessinfo;
//		} else {
//			return returnSurveybusinessinfo;
//		}
		return returnSurveybusinessinfo;
	}
}

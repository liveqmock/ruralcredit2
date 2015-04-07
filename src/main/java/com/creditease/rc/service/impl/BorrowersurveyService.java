package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.IBorrowersurveyDao;
import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.service.IBorrowersurveyService;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class BorrowersurveyService implements IBorrowersurveyService {

	@Resource
	private IBorrowersurveyDao borrowersurveyDao;

	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;

	@Override
	public boolean updateBorrowersurvey(Borrowersurvey borrowersurvey) {
		// TODO Auto-generated method stub
		Double totalMoney = borrowersurvey.getTotalMoney();
		if (totalMoney != null) {
			int rows = borrowersurveyDao.updateBorrowersurvey(borrowersurvey);
		}

		return true;
	}

	@Override
	public int searchBorrowerSurveyId(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return borrowersurveyDao.searchBorrowerSurveyId(borrowerServiceAppId);
	}

	@Override
	public boolean updateContactSurveyAlt(Borrowersurvey borrowersurvey) {
		// TODO Auto-generated method stub
		borrowersurveyDao.updateBorrowersurvey(borrowersurvey);
		return true;
	}

	// 回显Borrowersurvey
	@Override
	public Borrowersurvey searchBorrowersurvey(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		Borrowersurvey returnBorrowersurvey = new Borrowersurvey();
		returnBorrowersurvey.setBorrowerServiceAppId(borrowerServiceAppId);
		Borrowersurvey getborrowersurvey = (Borrowersurvey) borrowersurveyDao.queryUnique(
				"borrowersurvey.selectborrowersurveyByBorrowerServiceAppId", borrowerServiceAppId);
		if (getborrowersurvey != null) {
			return getborrowersurvey;
		} else {
			return returnBorrowersurvey;
		}
	}

	// 保存对上述合计的操作
	@Override
	public Borrowersurvey saveBorrowersurvey(Borrowersurvey passborrowersurvey) {
		// TODO Auto-generated method stub
		Integer borrowerServiceAppId = passborrowersurvey.getBorrowerServiceAppId();
		Borrowersurvey returnBorrowersurvey = new Borrowersurvey();
		Integer borrowerSurveyId = passborrowersurvey.getBorrowerSurveyId();
		if (borrowerSurveyId == null) {
			borrowersurveyDao.insert("borrowersurvey.insertborrowersurvey", passborrowersurvey);
		} else {
			borrowersurveyDao.update("borrowersurvey.updateborrowersurveyByPrimaryKey", passborrowersurvey);
		}
		returnBorrowersurvey = searchBorrowersurvey(borrowerServiceAppId);
		return returnBorrowersurvey;
	}

	@Override
	public void updateFlag3th(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		borrowerServiceAppDAO.updateThirdFlag(borrowerServiceAppId, "1");

	}
}

package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.IBorrowersurveyDao;
import com.creditease.rc.dao.IFamilydepositdebtDao;
import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.dao.IFamilyotherincomeDao;
import com.creditease.rc.dao.IFamilytotalcostDao;
import com.creditease.rc.dao.IHouseholdassertsDao;
import com.creditease.rc.dao.ISurveybusinessinfoDao;
import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.service.ISurveySecondService;
import com.creditease.rc.service.IBorrowersurveyService;
import com.creditease.rc.service.IFamilydepositdebtService;
import com.creditease.rc.service.IFamilymemberService;
import com.creditease.rc.service.IFamilyotherincomeService;
import com.creditease.rc.service.IFamilytotalcostService;
import com.creditease.rc.service.IHouseholdassertsService;
import com.creditease.rc.service.ISurveybusinessinfoService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.SurveySecondVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class SurveySecondService implements ISurveySecondService {

	@Resource
	private IFamilymemberService familymemberService;
	@Resource
	private IFamilyotherincomeService familyotherincomeService;
	@Resource
	private ISurveybusinessinfoService surveybusinessinfoService;
	@Resource
	private IFamilytotalcostService familytotalcostService;
	@Resource
	private IFamilydepositdebtService familydepositdebtService;
	@Resource
	private IHouseholdassertsService householdassertsService;

	@Resource
	private IBorrowersurveyService borrowersurveyService;

	@Resource
	private IFamilymemberDao familymemberDao;
	@Resource
	private IFamilyotherincomeDao familyotherincomeDao;

	@Resource
	private ISurveybusinessinfoDao surveybusinessinfoDao;
	@Resource
	private IFamilytotalcostDao familytotalcostDao;

	@Resource
	private IFamilydepositdebtDao familydepositdebtDao;

	@Resource
	private IHouseholdassertsDao householdassertsDao;

	@Resource
	private IBorrowersurveyDao borrowersurveyDao;
	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;

	@Override
	public boolean saveSurveySecondTable(SurveySecondVo surveySecondVo) {
		// TODO Auto-generated method stub
		List<Familymember> familymembers = surveySecondVo.getFamilymembers();
		List<Familyotherincome> familyotherincomes = surveySecondVo.getFamilyotherincomes();
		Surveybusinessinfo surveybusinessinfo = surveySecondVo.getSurveybusinessinfo();
		List<Familytotalcost> familytotalcosts = surveySecondVo.getFamilytotalcosts();
		List<Familydepositdebt> familydepositdebts = surveySecondVo.getFamilydepositdebts();
		List<Householdasserts> householdassertss = surveySecondVo.getHouseholdassertss();
		Borrowersurvey borrowersurvey = surveySecondVo.getBorrowersurvey();
		if (familymembers != null) {
			boolean success = familymemberService.addFamilyMemberList(familymembers);
		}
		if (familyotherincomes != null) {
			boolean success = familyotherincomeService.addFamilyOtherIncomeList(familyotherincomes);
		}
		if (surveybusinessinfo != null) {
			boolean success = surveybusinessinfoService.addSurveyBusinessInfo(surveybusinessinfo);
		}
		if (familytotalcosts != null) {
			boolean success = familytotalcostService.addFamilyTotalCostList(familytotalcosts);
		}
		if (familydepositdebts != null) {
			boolean success = familydepositdebtService.addFamilyDepositDebtsList(familydepositdebts);
		}
		if (householdassertss != null) {
			boolean success = householdassertsService.addHouseholdAssertsList(householdassertss);
		}
		if (borrowersurvey != null) {
			boolean success = borrowersurveyService.updateBorrowersurvey(borrowersurvey);
		}

		return true;
	}

	@Override
	public boolean insertSurveySecondVo(SurveySecondVo surveySecondVo) {
		// TODO Auto-generated method stub
		List<Familymember> familymembers = surveySecondVo.getFamilymembers();
		List<Familyotherincome> familyotherincomes = surveySecondVo.getFamilyotherincomes();
		Surveybusinessinfo surveybusinessinfo = surveySecondVo.getSurveybusinessinfo();
		List<Familytotalcost> familytotalcosts = surveySecondVo.getFamilytotalcosts();
		List<Familydepositdebt> familydepositdebts = surveySecondVo.getFamilydepositdebts();
		List<Householdasserts> householdassertss = surveySecondVo.getHouseholdassertss();
		Borrowersurvey borrowersurvey = surveySecondVo.getBorrowersurvey();
		try {
			if (familymembers != null) {
				boolean success = familymemberService.insertFamilyMemberList(familymembers);
			}
			if (familyotherincomes != null) {
				boolean success = familyotherincomeService.insertFamilyOtherIncomeList(familyotherincomes);
			}
			if (surveybusinessinfo != null) {
				boolean success = surveybusinessinfoService.insertSurveyBusinessInfo(surveybusinessinfo);
			}
			if (familytotalcosts != null) {
				boolean success = familytotalcostService.insertFamilyTotalCostList(familytotalcosts);
			}
			if (familydepositdebts != null) {
				boolean success = familydepositdebtService.insertFamilyDepositDebtsList(familydepositdebts);
			}
			if (householdassertss != null) {
				boolean success = householdassertsService.insertHouseholdAssertsList(householdassertss);
			}
			if (borrowersurvey != null) {
				boolean success = borrowersurveyService.updateBorrowersurvey(borrowersurvey);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SurveySecondVo searchSurveySecondTable(int borrowerSurveyId) {
		// TODO Auto-generated method stub

		SurveySecondVo application2Vo = new SurveySecondVo();
		List<Familymember> familymembers = familymemberService.searchfamilymemberList(Long.valueOf(borrowerSurveyId));
		List<Familyotherincome> familyotherincomes = familyotherincomeService.searchfamilyotherincomeList(borrowerSurveyId);
		Surveybusinessinfo surveybusinessinfo = surveybusinessinfoService.searchSurveybusinessinfo(Long.valueOf(borrowerSurveyId));
		List<Familytotalcost> familytotalcosts = familytotalcostService.searchfamilytotalcostList(borrowerSurveyId);
		List<Familydepositdebt> familydepositdebts = familydepositdebtService.searchfamilydepositdebtList(borrowerSurveyId);
		List<Householdasserts> householdassertss = householdassertsService.searchhouseholdassertsList(borrowerSurveyId);
		Borrowersurvey borrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerSurveyId);
		application2Vo.setFamilymembers(familymembers);
		application2Vo.setFamilyotherincomes(familyotherincomes);
		application2Vo.setSurveybusinessinfo(surveybusinessinfo);
		application2Vo.setFamilytotalcosts(familytotalcosts);
		application2Vo.setFamilydepositdebts(familydepositdebts);
		application2Vo.setHouseholdassertss(householdassertss);
		application2Vo.setBorrowersurvey(borrowersurvey);
		return application2Vo;
	}

	@Override
	public boolean updateSurveySecondTable(SurveySecondVo surveySecondVo) {
		// TODO Auto-generated method stub
		List<Familymember> familymembers = surveySecondVo.getFamilymembers();
		List<Familyotherincome> familyotherincomes = surveySecondVo.getFamilyotherincomes();
		Surveybusinessinfo surveybusinessinfo = surveySecondVo.getSurveybusinessinfo();
		List<Familytotalcost> familytotalcosts = surveySecondVo.getFamilytotalcosts();
		List<Familydepositdebt> familydepositdebts = surveySecondVo.getFamilydepositdebts();
		List<Householdasserts> householdassertss = surveySecondVo.getHouseholdassertss();
		Borrowersurvey borrowersurvey = surveySecondVo.getBorrowersurvey();
		try {
			if (familymembers != null) {
				boolean success = familymemberService.updateFamilyMemberList(familymembers);
			}
			if (familyotherincomes != null) {
				boolean success = familyotherincomeService.updateFamilyOtherIncomeList(familyotherincomes);
			}
			if (surveybusinessinfo != null) {
				boolean success = surveybusinessinfoService.updateSurveyBusinessInfo(surveybusinessinfo);
			}
			if (familytotalcosts != null) {
				boolean success = familytotalcostService.updateFamilyTotalCostList(familytotalcosts);
			}
			if (familydepositdebts != null) {
				boolean success = familydepositdebtService.updateFamilyDepositDebtsList(familydepositdebts);
			}
			if (householdassertss != null) {
				boolean success = householdassertsService.updateHouseholdAssertsList(householdassertss);
			}
			if (borrowersurvey != null) {
				boolean success = borrowersurveyService.updateBorrowersurvey(borrowersurvey);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 保存入户2
	@Override
	public boolean saveSurveySecondVo(SurveySecondVo surveySecondVo) {
		// TODO Auto-generated method stub
		List<Familymember> familymembers = surveySecondVo.getFamilymembers();
		List<Familyotherincome> familyotherincomes = surveySecondVo.getFamilyotherincomes();
		Surveybusinessinfo surveybusinessinfo = surveySecondVo.getSurveybusinessinfo();
		List<Familytotalcost> familytotalcosts = surveySecondVo.getFamilytotalcosts();
		List<Familydepositdebt> familydepositdebts = surveySecondVo.getFamilydepositdebts();
		List<Householdasserts> householdassertss = surveySecondVo.getHouseholdassertss();
		Borrowersurvey borrowersurvey = surveySecondVo.getBorrowersurvey();
		try {
			if (familymembers != null) {
				List<Familymember> success = familymemberService.saveFamilymembers(familymembers);
			}
			if (familyotherincomes != null) {
				List<Familyotherincome> success = familyotherincomeService.saveFamilyotherincomes(familyotherincomes);
			}
			if (surveybusinessinfo != null) {
				Surveybusinessinfo success = surveybusinessinfoService.saveSurveybusinessinfo(surveybusinessinfo);
			}
			if (familytotalcosts != null) {
				List<Familytotalcost> success = familytotalcostService.saveFamilytotalcosts(familytotalcosts);
			}
			if (familydepositdebts != null) {
				List<Familydepositdebt> success = familydepositdebtService.saveFamilydepositdebts(familydepositdebts);
			}
			if (householdassertss != null) {
				List<Householdasserts> success = householdassertsService.saveHouseholdassertss(householdassertss);
			}
			if (borrowersurvey != null) {
				Borrowersurvey success = borrowersurveyService.saveBorrowersurvey(borrowersurvey);
			}
			Integer borrowerServiceAppId = borrowersurvey.getBorrowerServiceAppId();
			borrowerServiceAppDAO.updateSecondFlag(borrowerServiceAppId, "1");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除入户调查表2和3的信息
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 布尔类型
	 */
	@Override
	public boolean deleteSurveySecondVo(Integer borrowerServiceAppId) {
		// 家庭成员及收入
		List<Integer> deleteFamilymemberList = new ArrayList<Integer>();
		List<Familymember> getFamilymembers = (List<Familymember>) familymemberDao.queryList(
				"familymember.selectfamilymemberByBorrowerServiceAppId", borrowerServiceAppId);
		// 家庭其他收入
		List<Integer> deleteFamilyotherincomeList = new ArrayList<Integer>();
		List<Familyotherincome> getFamilyotherincomes = (List<Familyotherincome>) familyotherincomeDao.queryList(
				"familyotherincome.selectfamilyotherincomeByBorrowerServiceAppId", borrowerServiceAppId);
		// 经营信息
		Integer surveyBusinessInfoId = null;
		Surveybusinessinfo getSurveybusinessinfo = (Surveybusinessinfo) surveybusinessinfoDao.queryUnique(
				"surveybusinessinfo.selectSurveybusinessinfo", borrowerServiceAppId.longValue());
		// 日常总开支
		List<Integer> deleteFamilytotalcostList = new ArrayList<Integer>();
		List<Familytotalcost> getFamilytotalcosts = (List<Familytotalcost>) familytotalcostDao.queryList(
				"familytotalcost.selectfamilytotalcostByBorrowerServiceAppId", borrowerServiceAppId);
		// 储蓄债权债务
		List<Integer> deleteFamilydepositdebtList = new ArrayList<Integer>();
		List<Familydepositdebt> getFamilydepositdebts = (List<Familydepositdebt>) familydepositdebtDao.queryList(
				"familydepositdebt.selectfamilydepositdebtByBorrowerServiceAppId", borrowerServiceAppId);
		// 家庭资产
		List<Integer> deleteHouseholdassertsList = new ArrayList<Integer>();
		List<Householdasserts> getHouseholdassertss = (List<Householdasserts>) householdassertsDao.queryList(
				"householdasserts.selecthouseholdassertsByBorrowerServiceAppId", borrowerServiceAppId);
		// 上述合计
		Integer borrowerSurveyId = null;
		Borrowersurvey getborrowersurvey = (Borrowersurvey) borrowersurveyDao.queryUnique(
				"borrowersurvey.selectborrowersurveyByBorrowerServiceAppId", borrowerServiceAppId);
		// 删除家庭成员及收入
		if (CommonUtil.isNotEmpty(getFamilymembers)) {
//			for (int i = 0; i < getFamilymembers.size(); i++) {
//				Integer tempFamilyMemberId = getFamilymembers.get(i).getFamilyMemberId();
//				deleteFamilymemberList.add(tempFamilyMemberId);
//			}
			if (CommonUtil.isNotEmpty(deleteFamilymemberList)) {
				familymemberDao.batchDelete("familymember.deletefamilymemberByPrimaryKey", deleteFamilymemberList);
			}
		}
		// 删除家庭其他收入
		if (CommonUtil.isNotEmpty(getFamilyotherincomes)) {
			for (int i = 0; i < getFamilyotherincomes.size(); i++) {
				Integer tempFamilyOtherIncomeId = getFamilyotherincomes.get(i).getFamilyOtherIncomeId();
				deleteFamilyotherincomeList.add(tempFamilyOtherIncomeId);
			}
			if (CommonUtil.isNotEmpty(deleteFamilyotherincomeList)) {
				familyotherincomeDao.batchDelete("familyotherincome.deletefamilyotherincomeByPrimaryKey",
						deleteFamilyotherincomeList);
			}
		}
		// 删除经营信息
		if (getSurveybusinessinfo != null) {
			surveyBusinessInfoId = getSurveybusinessinfo.getSurveyBusinessInfoId();
			if (surveyBusinessInfoId != null) {
				surveybusinessinfoDao.delete("surveybusinessinfo.deletesurveybusinessinfoByPrimaryKey", surveyBusinessInfoId);
			}
		}
		// 删除日常总开支
		if (CommonUtil.isNotEmpty(getFamilytotalcosts)) {
			for (int i = 0; i < getFamilytotalcosts.size(); i++) {
				Integer tempFamilyTotalCostId = getFamilytotalcosts.get(i).getFamilyTotalCostId();
				deleteFamilytotalcostList.add(tempFamilyTotalCostId);
			}
			if (CommonUtil.isNotEmpty(deleteFamilytotalcostList)) {
				familytotalcostDao.batchDelete("familytotalcost.deleteFamilytotalcostByPrimaryKey", deleteFamilytotalcostList);
			}
		}
		// 删除储蓄债权债务
		if (CommonUtil.isNotEmpty(getFamilydepositdebts)) {
			for (int i = 0; i < getFamilydepositdebts.size(); i++) {
				Integer tempFamilyDepositDebtId = getFamilydepositdebts.get(i).getFamilyDepositDebtId();
				deleteFamilydepositdebtList.add(tempFamilyDepositDebtId);
			}
			if (CommonUtil.isNotEmpty(deleteFamilydepositdebtList)) {
				familydepositdebtDao.batchDelete("familydepositdebt.deletefamilydepositdebtByPrimaryKey",
						deleteFamilydepositdebtList);
			}
		}
		// 删除家庭资产
		if (CommonUtil.isNotEmpty(getHouseholdassertss)) {
			for (int i = 0; i < getHouseholdassertss.size(); i++) {
				Integer tempHouseholdAssertsId = getHouseholdassertss.get(i).getHouseholdAssertsId();
				deleteHouseholdassertsList.add(tempHouseholdAssertsId);
			}
			if (CommonUtil.isNotEmpty(deleteHouseholdassertsList)) {
				householdassertsDao
						.batchDelete("householdasserts.deletehouseholdassertsByPrimaryKey", deleteHouseholdassertsList);
			}
		}
		// 上述合计
		if (getborrowersurvey != null) {
			borrowerSurveyId = getborrowersurvey.getBorrowerSurveyId();
			if (borrowerSurveyId != null) {
				borrowersurveyDao.delete("borrowersurvey.deleteborrowersurveyByPrimaryKey", borrowerSurveyId);
			}
		}
		return true;
	}

	@Override
	public SurveySecondVo searchSurveySecondVo(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		SurveySecondVo returnSurveySecondVo = new SurveySecondVo();
		// 家庭成员
		List<Familymember> returnFamilymembers = familymemberService.searchFamilymembers(Long.valueOf(borrowerServiceAppId));
		// returnFamilymemberVo.setFamilymembers(returnFamilymembers);
		returnSurveySecondVo.setFamilymembers(returnFamilymembers);
		// 其他收入
		List<Familyotherincome> returnFamilyotherincomes = familyotherincomeService
				.searchFamilyotherincomes(borrowerServiceAppId);
		returnSurveySecondVo.setFamilyotherincomes(returnFamilyotherincomes);
		// 经营情况
		Surveybusinessinfo returnSurveybusinessinfo = surveybusinessinfoService
				.searchSurveybusinessinfo(Long.valueOf(borrowerServiceAppId));
		returnSurveySecondVo.setSurveybusinessinfo(returnSurveybusinessinfo);
		// 日常总开支
		List<Familytotalcost> returnFamilytotalcosts = familytotalcostService
				.searchFamilytotalcosts(borrowerServiceAppId);
		returnSurveySecondVo.setFamilytotalcosts(returnFamilytotalcosts);
		// 储蓄债权债务
		List<Familydepositdebt> returnFamilydepositdebts = familydepositdebtService
				.searchFamilydepositdebts(borrowerServiceAppId);
		returnSurveySecondVo.setFamilydepositdebts(returnFamilydepositdebts);
		// 家庭资产
		List<Householdasserts> returnHouseholdassertss = householdassertsService
				.searchHouseholdassertss(borrowerServiceAppId);
		returnSurveySecondVo.setHouseholdassertss(returnHouseholdassertss);
		// 上述合计
		Borrowersurvey returnBorrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerServiceAppId);
		returnSurveySecondVo.setBorrowersurvey(returnBorrowersurvey);
		return returnSurveySecondVo;
	}
}

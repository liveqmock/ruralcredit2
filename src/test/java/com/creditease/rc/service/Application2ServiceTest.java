package com.creditease.rc.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.vo.SurveySecondVo;

/**
 * 
 * @author haoqiang
 * 
 */
public class Application2ServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private ISurveySecondService application2Service;

	/**
	 * 
	 * @throws Exception 错误
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 * @throws Exception 错误
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * searchApplication2Table
	 */
	// @Test
	void searchApplication2Table() {
		Integer borrowerSurveyId = null;
		SurveySecondVo application2Vo = application2Service.searchSurveySecondTable(borrowerSurveyId);
		Borrowersurvey borrowersurvey = application2Vo.getBorrowersurvey();
		List<Familydepositdebt> familydepositdebts = application2Vo.getFamilydepositdebts();
		List<Familyotherincome> familyotherincomes = application2Vo.getFamilyotherincomes();
		List<Familytotalcost> familytotalcosts = application2Vo.getFamilytotalcosts();
		List<Householdasserts> householdassertss = application2Vo.getHouseholdassertss();
		Surveybusinessinfo surveybusinessinfo = application2Vo.getSurveybusinessinfo();
	}

	/**
	 * 
	 * @param application2Vo 入户2
	 * @return 布尔
	 */
	// @Test
	boolean updateApplication2Table(SurveySecondVo application2Vo) {
		return false;
	}

	/**
	 * 
	 * @param application2Vo 入户2
	 * @return 布尔
	 */
	// @Test
	boolean insertApplication2Vo(SurveySecondVo application2Vo) {
		return false;
	}

}

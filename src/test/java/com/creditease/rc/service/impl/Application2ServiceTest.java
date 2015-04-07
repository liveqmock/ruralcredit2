package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.service.IFamilydepositdebtService;
import com.creditease.rc.service.ISurveybusinessinfoService;

/**
 * 
 * @author haoqiang
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml",
									"classpath:config/spring/rc/applicationContext-ws-service.xml",
									"classpath:config/spring/settle/applicationContext-settle.xml",
									"classpath:config/spring/pdf/applicationContext-pdf.xml",
									"classpath:config/spring/smp/applicationContext-acl.xml",
									"classpath:config/spring/smp/applicationContext-security.xml",
									"classpath:config/spring/smp/applicationContext-ws-client.xml"
					})
public class Application2ServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
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

	@Resource
	private IFamilydepositdebtService familydepositdebtService;

	@Resource
	private ISurveybusinessinfoService surveybusinessinfoService;

	/**
	 * testSaveFamilydepositdebts
	 */
	// @Test
	// @Rollback(false)
	public void testSaveFamilydepositdebts() {
		System.out.println("testSaveFamilydepositdebts" + "Test开始!!!");
		List<Familydepositdebt> familydepositdebtList = new ArrayList<Familydepositdebt>();

		Familydepositdebt tempFamilydepositdebt0 = new Familydepositdebt();
		tempFamilydepositdebt0.setBorrowerServiceAppId(1543);
		tempFamilydepositdebt0.setDepositDebtType("5");
		tempFamilydepositdebt0.setBank("不是的");
		tempFamilydepositdebt0.setAmount(4000.0);
		tempFamilydepositdebt0.setFamilyDepositDebtId(1657);

		Familydepositdebt tempFamilydepositdebt1 = new Familydepositdebt();
		tempFamilydepositdebt1.setBorrowerServiceAppId(1543);
		tempFamilydepositdebt1.setDepositDebtType("4");
		tempFamilydepositdebt1.setBank(null);
		tempFamilydepositdebt1.setAmount(null);
		tempFamilydepositdebt1.setFamilyDepositDebtId(1662);

		Familydepositdebt tempFamilydepositdebt2 = new Familydepositdebt();
		tempFamilydepositdebt2.setBorrowerServiceAppId(1543);
		tempFamilydepositdebt2.setDepositDebtType("6");
		tempFamilydepositdebt2.setBank("就是的");
		tempFamilydepositdebt2.setAmount(3000.0);
		tempFamilydepositdebt2.setFamilyDepositDebtId(1663);

		familydepositdebtList.add(tempFamilydepositdebt0);
		familydepositdebtList.add(tempFamilydepositdebt1);
		familydepositdebtList.add(tempFamilydepositdebt2);

		List<Familydepositdebt> returnFamilydepositdebtList = new ArrayList<Familydepositdebt>();

		returnFamilydepositdebtList = familydepositdebtService.saveFamilydepositdebts(familydepositdebtList);

		for (int i = 0; i < returnFamilydepositdebtList.size(); i++) {
			Familydepositdebt tempFamilydepositdebt = new Familydepositdebt();
			tempFamilydepositdebt = returnFamilydepositdebtList.get(i);
			Integer tempBorrowerServiceAppId = tempFamilydepositdebt.getBorrowerServiceAppId();
			String tempDepositDebtType = tempFamilydepositdebt.getDepositDebtType();
			String tempBank = tempFamilydepositdebt.getBank();
			Double tempAmount = tempFamilydepositdebt.getAmount();
			Integer tempFamilyDepositDebtId = tempFamilydepositdebt.getFamilyDepositDebtId();
			System.out.println("tempFamilydepositdebt==" + tempFamilydepositdebt.getClass() + ":::::" + i);
			System.out.println("tempBorrowerServiceAppId==" + tempBorrowerServiceAppId + ":::::" + i);
			System.out.println("tempDepositDebtType==" + tempDepositDebtType + ":::::" + i);
			System.out.println("tempBank==" + tempBank + ":::::" + i);
			System.out.println("tempAmount==" + tempAmount + ":::::" + i);
			System.out.println("tempFamilyDepositDebtId==" + tempFamilyDepositDebtId + ":::::" + i);
			System.out.println("tempBorrowerServiceAppId==" + tempBorrowerServiceAppId + ":::::" + i);

		}
		System.out.println("testSaveFamilydepositdebts" + "Test结束!!!");
	}

	/**
	 * saveSurveybusinessinfo
	 */
	// @Test
	// @Rollback(false)
	public void saveSurveybusinessinfo() {
		Surveybusinessinfo passSurveybusinessinfo = new Surveybusinessinfo();
		//passSurveybusinessinfo.setBorrowerServiceAppId(1543);
		passSurveybusinessinfo.setSurveyBusinessInfoId(1591);
		passSurveybusinessinfo.setOrganizationName("你的");
		Surveybusinessinfo returnSurveybusinessinfo = new Surveybusinessinfo();
		returnSurveybusinessinfo = surveybusinessinfoService.saveSurveybusinessinfo(passSurveybusinessinfo);

		//Integer tempBorrowerServiceAppId = returnSurveybusinessinfo.getBorrowerServiceAppId();
		Integer tempSurveyBusinessInfoId = returnSurveybusinessinfo.getSurveyBusinessInfoId();
		String tempOrganizationName = returnSurveybusinessinfo.getOrganizationName();
		Date tempStartBusinessTime = returnSurveybusinessinfo.getStartBusinessTime();
		Integer tempEmployeeNumber = returnSurveybusinessinfo.getEmployeeNumber();
		String tempSiteArea = returnSurveybusinessinfo.getSiteArea();
		String tempBusinessScope = returnSurveybusinessinfo.getBusinessScope();
		Double tempTotalIncome = returnSurveybusinessinfo.getTotalIncome();
		String tempBusinessAddress = returnSurveybusinessinfo.getBusinessAddress();
		String tempBusinessPlaceType = returnSurveybusinessinfo.getBusinessPlaceType();
		Date tempBusinessPlaceDate = returnSurveybusinessinfo.getBusinessPlaceDate();
		String tempBusinessPlaceOther = returnSurveybusinessinfo.getBusinessPlaceOther();
		String tempBusinessPermitNo = returnSurveybusinessinfo.getBusinessPermitNo();
		String tempTaxRegisterNo = returnSurveybusinessinfo.getTaxRegisterNo();

		System.out.println("returnSurveybusinessinfo==" + returnSurveybusinessinfo.getClass());
		//System.out.println("tempBorrowerServiceAppId==" + tempBorrowerServiceAppId);
		System.out.println("tempSurveyBusinessInfoId==" + tempSurveyBusinessInfoId);
		System.out.println("tempOrganizationName==" + tempOrganizationName);
		System.out.println("tempStartBusinessTime==" + tempStartBusinessTime);
		System.out.println("tempEmployeeNumber==" + tempEmployeeNumber);
		System.out.println("tempSiteArea==" + tempSiteArea);
		System.out.println("tempBusinessScope==" + tempBusinessScope);
		System.out.println("tempTotalIncome==" + tempTotalIncome);
		System.out.println("tempBusinessAddress==" + tempBusinessAddress);
		System.out.println("tempBusinessPlaceType==" + tempBusinessPlaceType);
		System.out.println("tempBusinessPlaceDate==" + tempBusinessPlaceDate);
		System.out.println("tempBusinessPlaceOther==" + tempBusinessPlaceOther);
		System.out.println("tempBusinessPermitNo==" + tempBusinessPermitNo);
		System.out.println("tempTaxRegisterNo==" + tempTaxRegisterNo);

	}

}

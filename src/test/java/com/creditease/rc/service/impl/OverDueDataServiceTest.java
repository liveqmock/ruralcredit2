package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.OverDueData;
import com.creditease.rc.service.IOverDueDataService;

/**
 * 销售计划单元测试
 * 
 * @author 郝强
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml",
		"classpath:config/spring/rc/applicationContext-ws-service.xml",
		"classpath:config/spring/settle/applicationContext-settle.xml",
		"classpath:config/spring/pdf/applicationContext-pdf.xml",
		"classpath:config/spring/cm/applicationContext-cm.xml",
		"classpath:config/spring/smp/applicationContext-acl.xml",
		"classpath:config/spring/smp/applicationContext-security.xml",
		"classpath:config/spring/smp/applicationContext-ws-client.xml" })
public class OverDueDataServiceTest {

	@Resource
	private IOverDueDataService overDueDataService;

	/**
	 * 
	 * @throws Exception
	 *             异常
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 * @throws Exception
	 *             异常
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testS() {
		System.out.println("2222222");
	}

	@Test
	public void dynamicInsert() {
		OverDueData overDueData = new OverDueData();
		overDueData.setOverdueDataId(43540l);
		overDueData.setLaonBalanceDataId(0l);
		overDueData.setOverdueDays(1);
		overDueData.setOverduestart(new Date());
		overDueData.setOdCorpus(new BigDecimal(2));
		overDueData.setOdInterest(new BigDecimal(3));
		overDueData.setOdOverhead(new BigDecimal(4));
		overDueData.setOdCharges(new BigDecimal(5));
		overDueData.setOdForfeit(new BigDecimal(6));
		overDueData.setOdLatefee(new BigDecimal(7));
		overDueData.setOdDamages(new BigDecimal(8));
		overDueData.setOfCorpus(new BigDecimal(9));
		overDueData.setOfInterest(new BigDecimal(10));
		overDueData.setOfOverhead(new BigDecimal(11));
		overDueData.setOfCharges(new BigDecimal(12));
		overDueData.setOfForfeit(new BigDecimal(13));
		overDueData.setOfLatefee(new BigDecimal(14));
		overDueData.setOfDamages(new BigDecimal(15));
		overDueData.setOdfCorpus(new BigDecimal(16));
		overDueData.setOdfInterest(new BigDecimal(17));
		overDueData.setOdfOverhead(new BigDecimal(18));
		overDueData.setOdfCharges(new BigDecimal(19));
		overDueData.setOdfForfeit(new BigDecimal(20));
		overDueData.setOdfLatefee(new BigDecimal(21));
		overDueData.setOdfDamages(new BigDecimal(22));

		boolean s = overDueDataService.dynamicInsert(overDueData);
		System.out.println(s);
	}
	
	
	@Test
	public void dynamicUpdate() {
		OverDueData overDueData = new OverDueData();
		overDueData.setOverdueDataId(43540l);
		overDueData.setLaonBalanceDataId(0l);
		overDueData.setOverdueDays(1);
		overDueData.setOverduestart(new Date());
		overDueData.setOdCorpus(new BigDecimal(2));
		overDueData.setOdInterest(new BigDecimal(3));
		overDueData.setOdOverhead(new BigDecimal(4));
		overDueData.setOdCharges(new BigDecimal(5));
		overDueData.setOdForfeit(new BigDecimal(6));
		overDueData.setOdLatefee(new BigDecimal(7));
		overDueData.setOdDamages(new BigDecimal(8));
		overDueData.setOfCorpus(new BigDecimal(9));
		overDueData.setOfInterest(new BigDecimal(10));
		overDueData.setOfOverhead(new BigDecimal(11));
		overDueData.setOfCharges(new BigDecimal(12));
		overDueData.setOfForfeit(new BigDecimal(13));
		overDueData.setOfLatefee(new BigDecimal(14));
		overDueData.setOfDamages(new BigDecimal(15));
		overDueData.setOdfCorpus(new BigDecimal(16));
		overDueData.setOdfInterest(new BigDecimal(17));
		overDueData.setOdfOverhead(new BigDecimal(18));
		overDueData.setOdfCharges(new BigDecimal(19));
		overDueData.setOdfForfeit(new BigDecimal(20));
		overDueData.setOdfLatefee(new BigDecimal(21));
		overDueData.setOdfDamages(new BigDecimal(22));

		boolean s = overDueDataService.dynamicUpdate(overDueData);
		System.out.println(s);
	}
}

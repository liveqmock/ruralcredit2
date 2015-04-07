package com.creditease.rc.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.LaonBalanceData;
import com.creditease.rc.service.ILaonBalanceDataService;

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
		"classpath:config/spring/smp/applicationContext-ws-client.xml" ,
		"classpath:config/spring/credit/applicationContext-credit.xml" })
public class LaonBalanceDataServiceTest {

	@Resource
	private ILaonBalanceDataService laonBalanceDataService;

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
		LaonBalanceData balanceData = new LaonBalanceData();
		balanceData.setLaonBalanceDataId(43537l);
		balanceData.setSysGuid("0");
		balanceData.setdCorpus(new BigDecimal(1));
		balanceData.setdInterest(new BigDecimal(2));
		balanceData.setdOverhead(new BigDecimal(3));
		balanceData.setdCharges(new BigDecimal(4));
		balanceData.setfCorpus(new BigDecimal(5));
		balanceData.setfInterest(new BigDecimal(6));
		balanceData.setfOverhead(new BigDecimal(7));
		balanceData.setfCharges(new BigDecimal(8));
		balanceData.setDfCorpus(new BigDecimal(9));
		balanceData.setDfInterest(new BigDecimal(10));
		balanceData.setDfOverhead(new BigDecimal(11));
		balanceData.setDfCharges(new BigDecimal(12));
		boolean s = laonBalanceDataService.dynamicInsert(balanceData);
		System.out.println(s);
	}
	
	@Test
	public void dynamicUpdate() {
		LaonBalanceData balanceData = new LaonBalanceData();
		balanceData.setLaonBalanceDataId(43540l);
		balanceData.setSysGuid("0");
		balanceData.setdCorpus(new BigDecimal(1));
		balanceData.setdInterest(new BigDecimal(2));
		balanceData.setdOverhead(new BigDecimal(3));
		balanceData.setdCharges(new BigDecimal(4));
		balanceData.setfCorpus(new BigDecimal(5));
		balanceData.setfInterest(new BigDecimal(6));
		balanceData.setfOverhead(new BigDecimal(7));
		balanceData.setfCharges(new BigDecimal(8));
		balanceData.setDfCorpus(new BigDecimal(9));
		balanceData.setDfInterest(new BigDecimal(10));
		balanceData.setDfOverhead(new BigDecimal(11));
		balanceData.setDfCharges(new BigDecimal(12));
		boolean s = laonBalanceDataService.dynamicUpdate(balanceData);
		System.out.println(s);
	}
}

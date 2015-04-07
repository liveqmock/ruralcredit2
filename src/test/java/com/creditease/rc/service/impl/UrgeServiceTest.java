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

import com.creditease.rc.domain.Urge;
import com.creditease.rc.service.IUrgeService;

/**
 * 滚动预测单元测试
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
public class UrgeServiceTest {

	@Resource
	private IUrgeService urgeService;

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
	public void testSuccess() {

		System.out.println("564654");

	}

	@Test
	public void insertTest() {
		Urge urge = new Urge();

		urge.setUrgeId(1l);
		urge.setCreditapplicationId(1l);
		urge.setOperatorName("1");
		urge.setOperatorId(1l);
		urge.setOperateTime(new Date());
		urge.setRepaymentCycle("1");
		urge.setUrgeSummarize("1");
		urge.setUrgeWay("1");
		urge.setReginMoney(new BigDecimal(1));
		urge.setMoneySource("1");
		urge.setUrgeState("1");
		urge.setUrgeDate(new Date());
		urge.setRefundDate(new Date());
		// urge.setUrgeLongTime(new Date());
		urge.setUrgeRemark("1");
		urge.setBigWarning("1");
		urge.setUrgeDescribe("1");
		urge.setYnPromise("1");
		urge.setPreviousPromiseMoney(new BigDecimal(1));
		urge.setPreviousPromiseTime(new Date());
		urge.setCurrentPromiseMoney(new BigDecimal(1));
		urge.setCurentPromiseTime(new Date());

		boolean s = urgeService.insert(urge);

	}

	@Test
	public void dynamicUpdate() {

		Urge urge = new Urge();

		urge.setUrgeId(44324l);
		urge.setCreditapplicationId(2l);
		urge.setOperatorName("2");
		urge.setOperatorId(2l);
		urge.setOperateTime(new Date());
		urge.setRepaymentCycle("2");
		urge.setUrgeSummarize("2");
		urge.setUrgeWay("2");
		urge.setReginMoney(new BigDecimal(2));
		urge.setMoneySource("2");
		urge.setUrgeState("2");
		urge.setUrgeDate(new Date());
		urge.setRefundDate(new Date());
		// urge.setUrgeLongTime(new Date());
		urge.setUrgeRemark("2");
		urge.setBigWarning("2");
		urge.setUrgeDescribe("2");
		urge.setYnPromise("2");
		urge.setPreviousPromiseMoney(new BigDecimal(2));
		urge.setPreviousPromiseTime(new Date());
		urge.setCurrentPromiseMoney(new BigDecimal(2));
		urge.setCurentPromiseTime(new Date());

		boolean s = urgeService.dynamicUpdate(urge);

		System.out.println(s);

	}
}

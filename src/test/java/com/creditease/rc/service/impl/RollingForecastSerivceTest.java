package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.RollingForecast;
import com.creditease.rc.service.IRollingForecastSerivce;

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
public class RollingForecastSerivceTest {

	@Resource
	private IRollingForecastSerivce rollingForecastSerivce;

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

	// @Test
	public void testSuccess() {

		System.out.println("8927348132894710847081234");

	}

	 @Test
	 @Rollback(false)
	public void testInsert() {
		RollingForecast rollingForecast = new RollingForecast();
		rollingForecast.setRollingForecastId(123132123l);
		rollingForecast.setYear(2113);
		rollingForecast.setMonth(12);
		rollingForecast.setType("1");
		rollingForecast.setAreaDepartmentId(1231313123l);
		rollingForecast.setAreaDepartmentName("buiemni");
		rollingForecast.setFirstMonth(new BigDecimal(123132));
		rollingForecast.setSecondMonth(new BigDecimal(123132));
		rollingForecast.setThirdMonth(new BigDecimal(123132));
		rollingForecast.setOperateTime(new Date());
		rollingForecast.setOperatorId("13123123");
		rollingForecast.setOperatorName("好奇哎那个");
		boolean s = rollingForecastSerivce.dynamicInsert(rollingForecast);
		System.out.println(s);

	}

	@Test
	@Rollback(false)
	public void testUpdate() {
		RollingForecast rollingForecast = new RollingForecast();
		rollingForecast.setRollingForecastId(43324l);
		rollingForecast.setYear(1111);
		rollingForecast.setMonth(11);
		rollingForecast.setType("0");
		rollingForecast.setAreaDepartmentId(111111l);
		rollingForecast.setAreaDepartmentName("nininini");
		rollingForecast.setFirstMonth(new BigDecimal(1111));
		rollingForecast.setSecondMonth(new BigDecimal(1111));
		rollingForecast.setThirdMonth(new BigDecimal(1111));
		rollingForecast.setOperateTime(new Date());
		rollingForecast.setOperatorId("1111");
		rollingForecast.setOperatorName("hhhhhhhhhhh");
		rollingForecast.setHistoryFlag("F");
		boolean s = rollingForecastSerivce.dynamicUpdate(rollingForecast);
		System.out.println(s);

	}

}

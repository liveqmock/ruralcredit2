package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.UrgeLinkman;
import com.creditease.rc.service.IUrgeLinkmanService;
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
public class UrgeLinkmanServiceTest {
	@Resource
	private IUrgeLinkmanService urgeLinkmanService;

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
	public void testInsert() {
		UrgeLinkman urgeLinkman = new UrgeLinkman();

		urgeLinkman.setUrgeId(1l);
		urgeLinkman.setUrgeLinkName("1");
		urgeLinkman.setLinkTelephone("1");
		urgeLinkman.setBorrowerRelation("1");

		boolean s = urgeLinkmanService.insert(urgeLinkman);

	}

	@Test
	public void testUpdate() {
		UrgeLinkman urgeLinkman = new UrgeLinkman();

		urgeLinkman.setUrgeLinkmanId(53908l);
		urgeLinkman.setUrgeId(2l);
		urgeLinkman.setUrgeLinkName("2");
		urgeLinkman.setLinkTelephone("2");
		urgeLinkman.setBorrowerRelation("2");

		boolean s = urgeLinkmanService.dynamicUpdate(urgeLinkman);

	}

}

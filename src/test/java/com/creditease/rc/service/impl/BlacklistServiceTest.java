package com.creditease.rc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.Blacklist;
import com.creditease.rc.service.IBlacklistService;

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
		"classpath:config/spring/smp/applicationContext-ws-client.xml",
		"classpath:config/spring/credit/applicationContext-credit.xml" })
public class BlacklistServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IBlacklistService blacklistService;

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

	// @Test
	public void test() {
		System.out.println("testSuccess");
	}

	@Test
	@Rollback(false)
	public void dynamicInsertTest() {
		Blacklist blacklist = new Blacklist();
		blacklist.setName("11");
		blacklist.setCredentialsNumber("12");
		blacklist.setBlacklistedTime(new Date());
		blacklist.setBlacklistedOperatorName("13");
		blacklist.setBlacklistedOperatorId(14l);
		blacklist.setBlacklistedReason("15");
		blacklist.setRemoveTime(new Date());
		blacklist.setRemoveOperatorName("16");
		blacklist.setRemoveOperatorId(17l);
		blacklist.setRemoveReason("18");
		blacklist.setHistoryFlag("19");
		boolean s = blacklistService.dynamicInsert(blacklist);
		System.out.println(s);
	}

	@Test
	@Rollback(false)
	public void dynamicUpdate() {
		Blacklist blacklist = new Blacklist();
		
		blacklist.setBlacklistId(37812l);
		blacklist.setName("21");
		blacklist.setCredentialsNumber("22");
		blacklist.setBlacklistedTime(new Date());
		blacklist.setBlacklistedOperatorName("23");
		blacklist.setBlacklistedOperatorId(24l);
		blacklist.setBlacklistedReason("25");
		blacklist.setRemoveTime(new Date());
		blacklist.setRemoveOperatorName("26");
		blacklist.setRemoveOperatorId(27l);
		blacklist.setRemoveReason("28");
		blacklist.setHistoryFlag("29");
		int s = blacklistService.dynamicUpdate(blacklist);
		System.out.println(s);
	}
}

package com.creditease.rc.service.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.util.SmpWSUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml",
		"classpath:config/spring/rc/applicationContext-ws-service.xml",
		"classpath:config/spring/settle/applicationContext-settle.xml",
		"classpath:config/spring/pdf/applicationContext-pdf.xml",
		"classpath:config/spring/smp/applicationContext-acl.xml",
		"classpath:config/spring/smp/applicationContext-security.xml",
		"classpath:config/spring/smp/applicationContext-ws-client.xml",
		"classpath:config/spring/credit/applicationContext-credit.xml",
		"classpath:config/spring/rc/applicationContext-quartz-job.xml",
		"classpath:config/spring/comprehensive/applicationContext-comprehensive.xml",
		"classpath:config/spring/rc/applicationContext-quartz-job.xml",
		"classpath:config/spring/cm/applicationContext-cm.xml",
		"classpath:config/spring/orgams/applicationContext-orgams.xml",
		"classpath:config/spring/orgams2/applicationContext-orgams2.xml"})
public class OrgamsServiceImplTest {

	@Resource
	private IorgamsService iorgamsService;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Test
	public void testBorrowMatchingReq() {
		Long l = 365359l;//344180
		iorgamsService.borrowMatchingReq(l,15.00,18000d);
		System.out.println("11111");
		
	}
	

	@Test
	public void testUpMatching() {
		Long l = 341613l;
		iorgamsService.upMatching(l);
		System.out.println("11111");
	}

	@Test
	public void testMatchConfirm() {
		Long l = 341613l;
		iorgamsService.matchConfirm(l);
		System.out.println("aaa");
	}

	@Test
	public void testSynBorrowContractState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSynBorrowContract() {
		iorgamsService.synBorrowContract(365359l);
		System.out.println("22222222222222");
	}

	@Test
	public void testSynBatchBorrowContract() {
		fail("Not yet implemented");
	}

}

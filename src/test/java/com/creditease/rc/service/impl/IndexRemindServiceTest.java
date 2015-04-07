package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.service.IIndexRemindService;
import com.creditease.rc.vo.IndexRemindVo;
/**
 * 
 * cs
 * Title:IndexRemindServiceTest.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:23:41
 * @author wyf
 * @version v2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/spring/rc/applicationContext.xml",
		"classpath:config/spring/rc/applicationContext-ws-service.xml",
		"classpath:config/spring/settle/applicationContext-settle.xml",
		"classpath:config/spring/pdf/applicationContext-pdf.xml",
		"classpath:config/spring/smp/applicationContext-acl.xml",
		"classpath:config/spring/smp/applicationContext-security.xml",
		"classpath:config/spring/smp/applicationContext-ws-client.xml" })
public class IndexRemindServiceTest {
	@Resource
	private IIndexRemindService iIndexRemindService;
	/**
	 * 
	* checkstyle
	* @author wyf   
	* void
	 */
	@Test
	public void testSelectRemind(){
		CreditApplication creditApplication=new CreditApplication();
		creditApplication.setRole("24");
		IndexRemindVo indexRemind=iIndexRemindService.selectRemind(creditApplication);
		System.out.println(indexRemind.getAuditRemindFX().getAuditCount());
		System.out.println(indexRemind.getAuditRemindBS().getAuditCount());
	}
}

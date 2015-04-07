package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.service.IRlApplyAuditTableService;
/**
 * 
 * Title:RlApplyAduitTableServiceTest.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:21:37
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
		"classpath:config/spring/smp/applicationContext-ws-client.xml",
		"classpath*:config/spring/rc/applicationContext-quartz-job.xml",
		"classpath*:config/spring/cm/applicationContext-cm.xml" })
public class RlApplyAduitTableServiceTest {
	@Resource
	private IRlApplyAuditTableService rlApplyAuditTableService;

	/**
	 * 
	* checkstyle
	* @author wyf   
	* void
	 */
	@Test
	public void testFinancialpayment() {
		int creditapplicationId = 4632;
		List<RlApplyAuditTable> list  = rlApplyAuditTableService.selectApplyAuditTable(creditapplicationId);
		for(int i = 0; i < list.size(); i++){
			int id = list.get(i).getApplyaudittableId();
			System.out.println(id);
		}
	}
}

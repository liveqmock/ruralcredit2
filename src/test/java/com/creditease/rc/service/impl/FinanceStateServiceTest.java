package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.dao.IFinanceStateDao;
import com.creditease.rc.service.IFinancePaymentService;
/**
 * 
 * cs
 * Title:FinanceStateServiceTest.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:23:53
 * @author wyf
 * @version v2.0
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
public class FinanceStateServiceTest {
	@Resource
	private IFinancePaymentService financeStateService;
	@Resource 
	private IFinanceStateDao financeStateDao; 
	/**
	 * 
	* checkstyle
	* @author wyf   
	* void
	 */
	@Test
	public void testFinancialpayment(){
		/*FinanceState financeState=new FinanceState();
		financeState.setAssociationId(123);
		financeStateDao.insertFinanceState(financeState);*/
		System.err.println("322");
	}

}

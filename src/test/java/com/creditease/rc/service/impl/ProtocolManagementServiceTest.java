/**
 * Title:ProtocolManagementServiceTest.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.service.IProtocolManagementService;

/**
 * Title:ProtocolManagementServiceTest.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-25 
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
public class ProtocolManagementServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	/**
	 * @author 韩大年  
	 * @Description:  
	 * @throws java.lang.Exception 
	 * @version v1.0 
	 * 2013-3-25  
	 */
	@Before
	public void setUp() throws Exception {
	}
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private IProtocolManagementService protocolManagementService;

	/**
	 * @author 韩大年  
	 * @Description:  
	 * @throws java.lang.Exception 
	 * @version v1.0 
	 * 2013-3-25  
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	* checkstyle
	* @author wyf   
	* void
	 */
	/*@Test
	public void testSaveProrocolNumber() {
		CreditApplication creditApplication= new CreditApplication();
		creditApplication.setCreditapplicationId(9021);
		creditApplication=creditApplicationDAO.selectCreditApplicationById(creditApplication);
		String ss=protocolManagementService.saveProrocolNumber(creditApplication);
		//creditApplication.setGroupNumber("20130315001");
		//fail("Not yet implemented");
		System.out.println(ss);
	}*/
	/**
	 * 
	 */
	@Test
	public void testDownProtocol(){
		//11232
		Integer creditapplicationId =10787;
		String ss=protocolManagementService.saveAndDownProtocol(creditapplicationId,new Date());
		System.err.println(ss);
		
	}

}

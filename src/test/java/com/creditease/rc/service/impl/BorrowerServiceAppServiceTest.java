package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.service.IBorrowerServiceAppService;

/**
 * 个人申请单元测试
 * @author zhangman
 *
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
public class BorrowerServiceAppServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	/*@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddOrUpdateBorrowerServiceApp() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBorrowerServiceAppList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByBorrowerServiceAppId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBorrowSerivceApp() {
		fail("Not yet implemented");
	}

	@Test
	public void testChoseLeader() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOrUpdateCustomer() {
		fail("Not yet implemented");
	}
*/
	/**
	 * 
	 */
	@Test
	public void testUpdateValidStateOfBorrowerServiceApp() {
		int borrowerServiceAppId = 6834; 
		boolean flag=borrowerServiceAppService.updateValidStateOfBorrowerServiceApp(borrowerServiceAppId);
		System.out.println(flag);
	}

}

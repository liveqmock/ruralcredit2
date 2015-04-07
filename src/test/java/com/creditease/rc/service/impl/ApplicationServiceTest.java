package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.ICreditApplicationService;
/**
 * 
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
public class ApplicationServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * 
	 * @throws Exception 异常
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 * @throws Exception 异常
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;

	@Resource
	private ICreditApplicationService creditApplicationService;

	/*@Test
	public void testAddApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBorrowSerivceApp() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBorrowerServiceApp() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByBorrowerServiceAppId() {
		fail("Not yet implemented");
	}

	@Test
	public void testChoseLeader() {
		fail("Not yet implemented");
	}*/
	/**
	 * @author qianghao
	 */
	//@Test
	//@Rollback(false)
	public void testupdateValidStateOfBorrowerServiceApp() {
		Integer borrowerServiceAppId = 837;
		boolean isSuccess = borrowerServiceAppService.updateValidStateOfBorrowerServiceApp(borrowerServiceAppId);
		System.out.println("updateValidStateOfBorrowerServiceApp删除个人申请（及更改validState状态）的返回结果为：" + "====" + isSuccess);
	}

}

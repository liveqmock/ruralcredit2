package com.creditease.rc.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.GroupLoanRegist;
/**
 * 放款单元测试
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
public class GroupLoanRegistServiceTest {

	//@Resource
	//private IGroupLoanRegistService groupLoanRegistService;
	/**
	 * 测试插入放款登记
	 */
	@Test
	public void testAddGroupLoanRegist() {
		 GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
	}
	
	/**
	 * 测试查询历史
	 */
	@Test
	public void testSearchHistory() {
		 GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
	}
	
	/**
	 *  从审批结果中查询放款登记详细
	 */
	@Test
	public void testSelectLoanDetail() {
		 GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
	}
	/**
	 * 从放款登记查询放款登记详细
	 */
	@Test
	public void testSelectLoanRegist() {
		 GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
	}
	/**
	 * 查询最新的放款登记记录
	 */
	@Test
	public void testSelectDengji() {
		 GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
	}

	/**
	 * 查询  或 修改放款登记
	 */
	@Test
	public void testAddOrUpdateGroupLoanRegist() {
		 GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
	}

}

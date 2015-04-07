package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.SalesPlanning;
import com.creditease.rc.service.ISalesPlanningService;

/**
 * 销售计划单元测试
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
public class SalesPlanningServiceTest {
	@Resource
	private ISalesPlanningService salesPlanningService;

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
 @Rollback(true)
	public void insertTest() {
		List<SalesPlanning> salesPlannings1 = new ArrayList<SalesPlanning>();
		for (int i = 1; i <= 12; i++) {
			SalesPlanning salesPlanning = new SalesPlanning();
			salesPlanning.setAreaDepartmentId(11009481l);
			salesPlanning.setAreaDepartmentName("崇信县分公司");
			salesPlanning.setMonth(i);
			salesPlanning.setType("1");
			salesPlanning.setValue(new BigDecimal(10));
			salesPlanning.setYear(2013);
			salesPlannings1.add(salesPlanning);
		}
		boolean s = salesPlanningService.batchInsert(salesPlannings1);
		System.out.println("-----------" + s);
	}

//	@Test
//	@Rollback(true)
	public void updateTest() {
		// 41556
		SalesPlanning salesPlanning = new SalesPlanning();
		salesPlanning.setSalesPlanningId(41556l);
		salesPlanning.setAreaDepartmentId(000l);
		salesPlanning.setAreaDepartmentName("000");
		salesPlanning.setMonth(0);
		salesPlanning.setType("0");
		salesPlanning.setValue(new BigDecimal(0));
		salesPlanning.setYear(0);
		boolean s = salesPlanningService.update(salesPlanning);
		System.out.println("-----------" + s);
	}
}

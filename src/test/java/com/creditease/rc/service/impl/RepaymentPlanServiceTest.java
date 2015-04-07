package com.creditease.rc.service.impl;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.dao.IRepaymentPlanDao;
import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.service.IRepaymentPlanService;

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
									"classpath:config/spring/smp/applicationContext-ws-client.xml"
					})
public class RepaymentPlanServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

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

	@Resource
	private IRepaymentPlanService repaymentPlanService;
	private IRepaymentPlanDao repaymentPlanDao;

	/**
	 * 测试
	 */
// @Test
// @Rollback(false)
	public void testtest() {
		System.out.println("还款方案插入操作测试开始！");
		System.out.println("本次测试主要是是否能实现拓展实体类是否能插入成功！");
		// Integer insertRepaymentPlanReturnId(RepaymentPlan repaymentPlan);
		RepaymentPlan passRepaymentPlan = new RepaymentPlan();
		passRepaymentPlan.setRepaymentPlanName("测试Vo");
		passRepaymentPlan.setLoanPeriod(6);
		passRepaymentPlan.setHeightLoanAmount(12.0);
		passRepaymentPlan.setUseFlag("1");
		passRepaymentPlan.setRepaymentWay("0");
		passRepaymentPlan.setFirstServiceFree(100.0);
		passRepaymentPlan.setFollowupServiceFree(100.0);

		Double getNominalInterestRate = passRepaymentPlan.getNominalInterestRate();

		System.out.println("getNominalInterestRate========" + getNominalInterestRate);

		Integer iD = repaymentPlanService.insertRepaymentPlanReturnId(passRepaymentPlan);
		System.out.println("iD=====" + iD);
		RepaymentPlan resultRepaymentPlan = repaymentPlanService.searchRepaymentPlanByRepaymentPlanId(iD);

		System.out.println("getNominalInterestRate====" + resultRepaymentPlan.getNominalInterestRate());

	}

	/**
	 * sd
	 * 
	 */
	@Test
	@Rollback(false)
	public void doubletest() {
		DecimalFormat decimalFormat = new DecimalFormat("#.0000");
		double a = 0.0017;
		// System.out.println(df.format(a / 100));
		System.out.println(a * 100);

		Double b = Double.parseDouble(decimalFormat.format(0.17 / 100));
		System.out.println(b);
	}
}

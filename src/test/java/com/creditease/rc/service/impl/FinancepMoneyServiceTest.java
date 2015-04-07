package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IReturnPlanService;
/**
 * 
 * cs
 * Title:FinancepMoneyServiceTest.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:24:16
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
									"classpath:config/spring/smp/applicationContext-ws-client.xml",
						            "classpath*:config/spring/rc/applicationContext-quartz-job.xml",
						            "classpath*:config/spring/cm/applicationContext-cm.xml"
					})
public class FinancepMoneyServiceTest {
	@Resource 
	private IFinanceMoneyService financeMoneyService; 
	@Resource 
	private IReturnPlanService returnPlanService ; 
//	@Test
//	public void testFinancialpayment(){
//		FinanceMoney parameter=new FinanceMoney();
//		parameter.setAssociationId(10791);
//		FinanceMoney result=financeMoneyService.selectFinanceMoneyBack(parameter);
//		System.out.println(result.getBizId());
//	}
	
//	@Test
//	@Rollback(true)
//	public void testFinanceBack(){
//		List<Integer>list=new ArrayList<Integer>();
//		list.add(13346);
//		Message message=financeReceiveService.financialReceive(list, null);
//		System.out.println(message.getMsg());
//	}
//	@Test
//	public void testSelectOnline(){
//		FinanceMoney financeMoney = new FinanceMoney();
//		financeMoney.setHistoryFlag("T");
//		financeMoneyService.insertFinanceMoney(financeMoney);
//	}
	/**
	 * 
	 */
	@Test
	@Rollback(true)
	public void testDeleteReturnPlan(){
		int creditApplicationId = 3153;
		int i=returnPlanService.deleteReturnPlan(creditApplicationId);
		System.out.println(i);
	}

}

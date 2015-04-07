package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.ActualPeriodScheduleDTO;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.PaymentAmountReqCommon;
import com.creditease.rc.domain.PaymentAmountResultInfo;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.service.ICreditApplicationService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * 
 * cs
 * Title:BorrowingProductServiceTest.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:24:51
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
									"classpath:config/spring/cm/applicationContext-cm.xml"
					})
public class BorrowingProductServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
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
	private IBorrowingProductsService service;
	
	@Resource
	private ICreditApplicationService creditApplicationService;
/**
 * 
* checkstyle
* @author wyf   
* @throws Exception
* void
 */
	@Test
	public void testSeriaNum() throws Exception {
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setCompanyName("静宁县分公司");
		creditApplication.setRepaymentPlanName("助农贷6月");
		creditApplicationService.createGroupNumber(creditApplication);
	}
	/**
	 * testSaveFamilydepositdebts
	 */
	@Test
	public void testPaymentAmountCalculate() {
		PaymentAmountReqCommon paymentAmountReq = new PaymentAmountReqCommon();
		paymentAmountReq.setaLLAhead(true);
		paymentAmountReq.setAppointmentDate("2013-07-16");
        paymentAmountReq.setContractMoney(new BigDecimal(20000));
        paymentAmountReq.setDepartmentId(11009485L);
        paymentAmountReq.setLenderDate("2013-04-25");//new GregorianCalendar(2013, 03, 15)
        paymentAmountReq.setPeriodCount(6);
        paymentAmountReq.setProductInfoId(483L);
        paymentAmountReq.setReqSysDate("2013-07-15");//请求系统时间
		List<ActualPeriodScheduleDTO> apsDtos = new ArrayList<ActualPeriodScheduleDTO>();
		ActualPeriodScheduleDTO aps = new ActualPeriodScheduleDTO();
		aps.setOverdue(false);
		aps.setPeriod(1);
		aps.setReceivableInterest(new BigDecimal(0));
		aps.setReceivableMoney(new BigDecimal(0));
		aps.setReceivablePrincipal(new BigDecimal(0));
		aps.setReceivedInterest(new BigDecimal(0));
		aps.setReceivedMoney(new BigDecimal(0));
		aps.setReceivedPrincipal(new BigDecimal(0));
		aps.setRepayDate("2013-05-25");
		ActualPeriodScheduleDTO aps1 = new ActualPeriodScheduleDTO();
		aps1.setOverdue(false);
		aps1.setPeriod(2);
		aps1.setReceivableInterest(new BigDecimal(0));
		aps1.setReceivableMoney(new BigDecimal(0));
		aps1.setReceivablePrincipal(new BigDecimal(0));
		aps1.setReceivedInterest(new BigDecimal(0));
		aps1.setReceivedMoney(new BigDecimal(0));
		aps1.setReceivedPrincipal(new BigDecimal(0));
		aps1.setRepayDate("2013-06-25");
		ActualPeriodScheduleDTO aps2 = new ActualPeriodScheduleDTO();
		aps2.setOverdue(false);
		aps2.setPeriod(3);
		aps2.setReceivableInterest(new BigDecimal(115));
		aps2.setReceivableMoney(new BigDecimal(3000));
		aps2.setReceivablePrincipal(new BigDecimal(2800));
		aps2.setReceivedInterest(new BigDecimal(0));
		aps2.setReceivedMoney(new BigDecimal(0));
		aps2.setReceivedPrincipal(new BigDecimal(0));
		aps2.setRepayDate("2013-07-25");
		aps2.setPeriodFQFWFReceivableCharge(new BigDecimal(85));
		aps2.setPeriodFQFWFReceivedCharge(new BigDecimal(0));
		
		ActualPeriodScheduleDTO aps4 = new ActualPeriodScheduleDTO();
		ActualPeriodScheduleDTO aps5 = new ActualPeriodScheduleDTO();
		ActualPeriodScheduleDTO aps6 = new ActualPeriodScheduleDTO();
		aps4.setPeriodFQFWFReceivableCharge(new BigDecimal(25));
		aps4.setPeriodFQFWFReceivedCharge(new BigDecimal(25));
		aps4.setOverdueFxReceivableCharge(new BigDecimal(0));
		aps4.setOverdueFxReceivedCharge(new BigDecimal(0));
		aps4.setOverdueZnjReceivableCharge(new BigDecimal(0));
		aps4.setOverdueZnjReceivedCharge(new BigDecimal(0));
		aps4.setOverdue(false);
		aps4.setPeriod(4);
		aps4.setReceivableInterest(new BigDecimal(75));
		aps4.setReceivableMoney(new BigDecimal(2600));
		aps4.setReceivablePrincipal(new BigDecimal(2500));
		aps4.setReceivedInterest(new BigDecimal(0));
		aps4.setReceivedMoney(new BigDecimal(0));
		aps4.setReceivedPrincipal(new BigDecimal(0));
		aps4.setRepayDate("2013-08-25");
		
		aps5.setPeriodFQFWFReceivableCharge(new BigDecimal(25));
		aps5.setPeriodFQFWFReceivedCharge(new BigDecimal(25));
		aps5.setOverdue(false);
		aps5.setPeriod(5);
		aps5.setReceivableInterest(new BigDecimal(75));
		aps5.setReceivableMoney(new BigDecimal(2600));
		aps5.setReceivablePrincipal(new BigDecimal(2500));
		aps5.setReceivedInterest(new BigDecimal(75));
		aps5.setReceivedMoney(new BigDecimal(2600));
		aps5.setReceivedPrincipal(new BigDecimal(2500));
		aps5.setRepayDate("2013-09-25");
		
		aps6.setPeriodFQFWFReceivableCharge(new BigDecimal(25));
		aps6.setPeriodFQFWFReceivedCharge(new BigDecimal(25));
		aps6.setOverdue(false);
		aps6.setPeriod(6);
		aps6.setReceivableInterest(new BigDecimal(75));
		aps6.setReceivableMoney(new BigDecimal(2600));
		aps6.setReceivablePrincipal(new BigDecimal(2500));
		aps6.setReceivedInterest(new BigDecimal(75));
		aps6.setReceivedMoney(new BigDecimal(2600));
		aps6.setReceivedPrincipal(new BigDecimal(2500));
		aps6.setRepayDate("2013-10-25");
		
		apsDtos.add(aps);
		apsDtos.add(aps1);
		apsDtos.add(aps2);
		apsDtos.add(aps4);
		apsDtos.add(aps5);
		apsDtos.add(aps6);
		PaymentAmountResultInfo info = service.paymentAmountCalculate(paymentAmountReq, apsDtos);
		System.out.println(info.getResultCode()+"**"+info.getResultMsg());
	}
}

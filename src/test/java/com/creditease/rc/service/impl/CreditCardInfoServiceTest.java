package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.domain.CreditOrganization;
import com.creditease.rc.service.ICreditCardInfoService;
import com.creditease.rc.vo.CreditCardInfoVo;

/**
 * 信用卡单元测试
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
public class CreditCardInfoServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private ICreditCardInfoService creditCardInfoServiceImpl;
	
	/*@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}*/

	/**
	 * 测试添加修改方法
	 */
	@Test
	public void testAddOrUpdateCreditCardInfo() {
		CreditCardInfoVo creditCardInfoVo = new CreditCardInfoVo();
		CreditCardInfo creditCardInfo =new  CreditCardInfo();
		 List<CreditOrganization> creditOrganizations = new ArrayList<CreditOrganization>();
		 creditCardInfo.setCreditBorrowCondition("2");
		 creditCardInfo.setCreditBorrowConditionTime("10");
		 creditCardInfo.setCreditCardChargeTotal(90.12);
		 creditCardInfo.setCreditCondition("1");
		 creditCardInfo.setLoanChargeTotal(4000.00);
		 creditCardInfo.setCreditCondition("3");
		 creditCardInfo.setLoanChargeTotal(9000.0);
		 creditCardInfo.setLoanGuaranteesOther(45.3);
		  
		 CreditOrganization creditOrganization = new CreditOrganization();
		 creditOrganization.setCreditCardInfoId(45);
		 creditOrganization.setCreditCardIssue("中国银行");
		 creditOrganization.setCreditLimit(45.3);
		 creditOrganization.setCreditOrgId(0);
		 creditOrganization.setCreditUsedAmount(54.0);
		 creditOrganization.setOpeningDate(new Date());
		 
		 creditOrganizations.add(creditOrganization);
		 
		 creditCardInfoVo.setCreditCardInfo(creditCardInfo);
		 creditCardInfoVo.setCreditOrganization(creditOrganizations);
		  
		 CreditCardInfoVo creditCardInfoVo1 = creditCardInfoServiceImpl.addOrUpdateCreditCardInfo(creditCardInfoVo);
		 System.out.println(creditCardInfoVo1.getCreditCardInfo().getCreditCondition()+"-------借款情况");
		 System.out.println(creditCardInfoVo1.getCreditCardInfo().getLoanGuaranteesOther()+"-------为他人担保");
		 System.out.println(creditCardInfoVo1.getCreditCardInfo().getCreditCardInfoId());
		 System.out.println(creditCardInfoVo1.getCreditOrganization().get(0).getCreditCardInfoId());
	}

	/*@Test
	public void testSelectCreditCardInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCreditCradInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByBorrowerServiceAppId() {
		fail("Not yet implemented");
	}*/

}

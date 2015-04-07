package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.vo.ReserveReturnRequestVo;

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
		"classpath:config/spring/smp/applicationContext-ws-client.xml",
		"classpath:config/spring/credit/applicationContext-credit.xml",
		"classpath:config/spring/rc/applicationContext-quartz-job.xml",
		"classpath:config/spring/comprehensive/applicationContext-comprehensive.xml",
		"classpath:config/spring/rc/applicationContext-quartz-job.xml",
		"classpath:config/spring/cm/applicationContext-cm.xml",
		"classpath:config/spring/orgams/applicationContext-orgams.xml",
		"classpath:config/spring/orgams2/applicationContext-orgams2.xml"})
public class Rural2CreditServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private IRural2CreditService rural2CreditService;

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

	@Test
	public void test() {
		System.out.println("testSuccess");
	}

	@Test
	public void testClientApply() {
		Long creditapplicationId = 357943L;
		System.out.println("测试1：1.1 接收借款信息。");
		System.out.println("=============开始==========");
		Message s = rural2CreditService.clientApply(creditapplicationId);
		System.out.println("测试1：1.1 接收借款信息的结果是-------------------" + s);
		System.out.println("=============结束==========");
	}

	@Test
	public void testReturnScheme() {
		Long creditapplicationId = 15235L;
		System.out.println("测试2：1.2 查询还款计划。");
		System.out.println("=============开始==========");
		List<LocalReturnSchemeResponse> s = rural2CreditService.returnScheme(creditapplicationId);
		System.out.println("=============结束==========");
	}

	@Test
	public void testOverdueInfo() {
		System.out.println("测试3：1.3 查询逾期合同。");
		List<LocalOfficeIdDTO> localOfficeIdListDTOs = new ArrayList<LocalOfficeIdDTO>();
		LocalOfficeIdDTO officeIdDTO = new LocalOfficeIdDTO();

		officeIdDTO.setOfficeId("32767");
		localOfficeIdListDTOs.add(officeIdDTO);
		System.out.println("=============开始==========");
		LocalOverdueInfoResponse s = rural2CreditService.overdueInfo(localOfficeIdListDTOs);
		System.out.println("=============结束==========");
	}

	@Test
	public void testReserveReturn() {
		System.out.println("测试5：1.5 预约还款。");
		Long receivedRecordId = 15235L;
		List<Long> receivedRecordIds = new ArrayList<Long>();
		receivedRecordIds.add(receivedRecordId);
		String remark = "test";
		System.out.println("=============开始==========");
		ReserveReturnRequestVo requestVo = rural2CreditService.reserveReturn(receivedRecordIds, new Date(), remark);

		System.out.println("=============结束==========");
	}

	@Test
	public void testQyReserveSearch() {
		System.out.println("测试8：1.8 查询划扣结果。");
		String receivedRecordId = "15235";
		List<String> receivedRecordIds = new ArrayList<String>();
		receivedRecordIds.add(receivedRecordId);
		System.out.println("=============开始==========");
		rural2CreditService.qyReserveSearch(receivedRecordIds, null);
		System.out.println("=============结束==========");
	}
}

package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.domain.RuralReturnDis;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IReceivablesRegistrationService;

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
public class ReceivablesRegistrationServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private IReceivablesRegistrationService receivablesRegistrationService;

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

	/**
	 * test
	 */
	// @Test
	public void test() {
		System.out.println("成功！");
	}

	// ★━━━━━━━━━━━━━━━━━━━收款记录配相关━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 测试插入
	 */
// @Test
// @Rollback(false)
	public void insertReceivedRecordTest() {
		ReceivedRecord passReceivedRecord = new ReceivedRecord();
		passReceivedRecord.setBorrowerServiceAppId(2509);
		passReceivedRecord.setOperatorId(1);
		passReceivedRecord.setOperatorName("Nokia");
		passReceivedRecord.setReceivedAmount(2400.00);
//		boolean success = receivablesRegistrationService.insertReceivedRecord(passReceivedRecord);
//		System.out.println(success);
	}

	/**
	 * 测试修改
	 */
// @Test
// @Rollback(false)
	public void updateReceivedStatusTest() {
		Integer count = null;
		count = receivablesRegistrationService.updateReceivedStatus(4101);
		System.out.println(count);
	}

	// ★━━━━━━━━━━━━━━━━━━━收款记录配相关END━━━━━━━━━━━━━━━━━━━━━★

	// ★━━━━━━━━━━━━━━━━━━━━还款计划相关━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 测试插入方法
	 */
	@Test
	@Rollback(false)
	public void batchInsertReceivablesHistoryTest() {
		List<ReturnPlan> passReturnPlanList = new ArrayList<ReturnPlan>();
		for (int i = 0; i < 2; i++) {
//			ReturnPlan tempReturnPlan = new ReturnPlan();
//			tempReturnPlan.setCreditapplicationId(2497);
//			tempReturnPlan.setContractNo("123456");
//			tempReturnPlan.setCurrMonPrincipal(1000.00);
//			tempReturnPlan.setCurrMonInterest(100.00);
//			tempReturnPlan.setCurrMonManageFree(100.00);
//			tempReturnPlan.setCurrMonLaterFree(0.00);
//			tempReturnPlan.setCurrAccountTotal(1200.00);
//			tempReturnPlan.setCurrPaindinCapital(0.00);
//			tempReturnPlan.setCurrPaindinInterest(0.00);
//			tempReturnPlan.setCurrPaindinManageFree(0.00);
//			tempReturnPlan.setCurrLateFreePaid(0.00);
//			tempReturnPlan.setCurrMonPaidPenalty(0.00);
//			tempReturnPlan.setCurrMonPayTotal(0.00);
//			tempReturnPlan.setCollectionStatus("0");
//			tempReturnPlan.setPreRegistFlag(null);
//			passReturnPlanList.add(tempReturnPlan);
		}
		boolean isSuccess = false;
		isSuccess = receivablesRegistrationService.batchInsertReceivablesHistory(passReturnPlanList);
		System.out.println(isSuccess);
	}

	/**
	 * 测试修改方法
	 */
// @Test
// @Rollback(false)
	public void batchUpdateReceivablesHistoryTest() {

		List<ReturnPlan> passReturnPlanList = new ArrayList<ReturnPlan>();

		ReturnPlan tempReturnPlan = new ReturnPlan();

		//tempReturnPlan.setReturnPlanId(3572);
		passReturnPlanList.add(tempReturnPlan);

		int isSuccess = 0;
		isSuccess = receivablesRegistrationService.batchUpdateReceivablesHistory(passReturnPlanList);
		System.out.println(isSuccess);
	}

	/**
	 * 测试列表查询
	 */
	// @Test
	public void queryReceivablesHistoryDataGridTest() {

		Pagination resultPagination = new Pagination();
		Pagination passPagination = new Pagination();
		passPagination.setPage(1);
		passPagination.setPageSize(20);

		resultPagination = receivablesRegistrationService.queryReceivablesHistoryDataGrid(passPagination, 2509);
		System.out.println(resultPagination.getRows());
	}

	/**
	 * 测试删除方法
	 */
// @Test
// @Rollback(false)
	public void deleteReceivablesHistoryTest() {
		Integer count = receivablesRegistrationService.deleteReceivablesHistory(2509);
		System.out.println(count);
	}

	// ★━━━━━━━━━━━━━━━━━━还款计划相关END━━━━━━━━━━━━━━━━━━━━━━★

	// ★━━━━━━━━━━━━━━━━━━━实收分配相关━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 测试批量插入方法
	 */
// @Test
// @Rollback(false)
	public void batchInsertDistributionTest() {
		List<RuralReturnDis> passRuralReturnDisList = new ArrayList<RuralReturnDis>();
		RuralReturnDis tempRuralReturnDis = new RuralReturnDis();
		tempRuralReturnDis.setReturnPlanId(3574);
		tempRuralReturnDis.setDateCharge(getDateFromString("1981-1-1"));
		tempRuralReturnDis.setConfimRepayTime(getDateFromString("1981-1-1"));
		passRuralReturnDisList.add(tempRuralReturnDis);
		boolean isSuccess = false;
		isSuccess = receivablesRegistrationService.batchInsertDistribution(passRuralReturnDisList);
		System.out.println(isSuccess);
	}

	/**
	 * 测试批量更新
	 */
// @Test
// @Rollback(false)
	public void batchUpdateDistributionTest() {
		List<RuralReturnDis> passRuralReturnDisList = new ArrayList<RuralReturnDis>();
		RuralReturnDis tempRuralReturnDis = new RuralReturnDis();
		tempRuralReturnDis.setDistributionId(3579);
		tempRuralReturnDis.setReturnPlanId(3574);
		tempRuralReturnDis.setDateCharge(getDateFromString("1981-1-2"));
		tempRuralReturnDis.setConfimRepayTime(getDateFromString("1981-1-2"));
		passRuralReturnDisList.add(tempRuralReturnDis);
		Integer count = receivablesRegistrationService.batchUpdateDistribution(passRuralReturnDisList);
		System.out.println(count);
	}

	/**
	 * 测试列表查询
	 */
	// @Test
	public void queryDistributionDataGridTest() {
		Pagination resultPagination = new Pagination();
		Pagination passPagination = new Pagination();
		passPagination.setPage(1);
		passPagination.setPageSize(20);
		resultPagination = receivablesRegistrationService.queryDistributionDataGrid(passPagination, 3574);
		System.out.println(resultPagination.getRows());
	}

	/**
	 * 测试删除方法
	 */
// @Test
// @Rollback(false)
	public void deleteDistributionTest() {
		Integer count = receivablesRegistrationService.deleteDistribution(3574);
		System.out.println(count);
	}

	// ★━━━━━━━━━━━━━━━━━━━实收分配相关END━━━━━━━━━━━━━━━━━━━━━━━━★
	// ★━━━━━━━━━━━━━━━━━━━━分配算法相关━━━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 测试分配方法
	 */
// @Test
// @Rollback(false)
	public void distributionTest() {
		Integer rusult = receivablesRegistrationService.saveDistribution();
		System.out.println(rusult);
		if (rusult < 0) {
			System.out.println("分配时出现问题！");
		} else if (rusult == 0) {
			System.out.println("没有收款状态为成功的登记！");
		} else {
			System.out.println("全部的收款登记已经分配完成！");
		}
	}

	/**
	 * 测试事后调整方法
	 */
	@Test
	public void adjustmentTest() {
		ReceivedRecord paramReceivedRecord = new ReceivedRecord();
		paramReceivedRecord.setBorrowerServiceAppId(2509);
		paramReceivedRecord.setOperatorId(1);
		paramReceivedRecord.setOperatorName("后调整");
		paramReceivedRecord.setReceivedAmount(0.00);

		ReturnPlan paramReturnPlan = new ReturnPlan();
		List<ReturnPlan> adjustReturnPlanList = new ArrayList<ReturnPlan>();
//		paramReturnPlan.setReturnPlanId(4131);
//		paramReturnPlan.setCurrReduceLaterFree(-100.00);
//		paramReturnPlan.setCurrReduceTotal(-100.00);
		adjustReturnPlanList.add(paramReturnPlan);
		boolean sss = receivablesRegistrationService.adjustment(paramReceivedRecord, adjustReturnPlanList);
		System.out.println(sss);
	}

	// ★━━━━━━━━━━━━━━━━━━━分配算法相关END━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 
	 * @param param 字符串
	 * @return 日期
	 */
	private Date getDateFromString(String param) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		return d;
	}
}

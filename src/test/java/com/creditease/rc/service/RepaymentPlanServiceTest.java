package com.creditease.rc.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.domain.RepaymentPlanItem;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author haoqiang
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml" })
public class RepaymentPlanServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IRepaymentPlanService repaymentPlanService;

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
	 * testinsertRepaymentPlanReturnId
	 */
	// @Test
	public void testinsertRepaymentPlanReturnId() {
		RepaymentPlan repaymentPlan = new RepaymentPlan();
		repaymentPlan.setFirstServiceFree(12.0);
		repaymentPlan.setFollowupServiceFree(12.0);
		repaymentPlan.setLoanPeriod(12);
		repaymentPlan.setNominalInterestRate(12.0);
		repaymentPlan.setRepaymentInstallments(null);
		repaymentPlan.setRepaymentPlanName("test");
		repaymentPlan.setRepaymentWay("1");
		repaymentPlan.setUseFlag("1");
		Integer repaymentPlanId = repaymentPlanService.insertRepaymentPlanReturnId(repaymentPlan);
		System.out.println("testinsertRepaymentPlanReturnId插入成功后返回repaymentPlanId=" + repaymentPlanId);
	}

	/**
	 * public void testinsertRepaymentPlanItem
	 */
	// @Test
	public void testinsertRepaymentPlanItem() {
		RepaymentPlanItem repaymentItem = new RepaymentPlanItem();
		repaymentItem.setChargeServiceMethod("1");
		repaymentItem.setInterest(2.88);
		repaymentItem.setIntertestRepayment("1");
		repaymentItem.setMonths(6);
		repaymentItem.setPrincipal(20.0);
		repaymentItem.setPrincipalRepayment("1");
		repaymentItem.setRepaymentPlanId(10);
		repaymentItem.setRepaymentPlanItemId(null);
		repaymentItem.setServiceFree(30.0);
		List<RepaymentPlanItem> passRepaymentPlanItem = new ArrayList<RepaymentPlanItem>();
		passRepaymentPlanItem.add(repaymentItem);
		boolean result = repaymentPlanService.insertRepaymentPlanItem(passRepaymentPlanItem);
		System.out.println("testinsertRepaymentPlanItem返回的插入结果为：" + result);

	}

	/**
	 * testupdateRepaymentPlan
	 */
	// @Test
	public void testupdateRepaymentPlan() {
		RepaymentPlan repaymentPlan = new RepaymentPlan();
		repaymentPlan.setNominalInterestRate(24.0);
		repaymentPlan.setFollowupServiceFree(24.0);
		repaymentPlan.setFirstServiceFree(60.0);
		repaymentPlan.setRepaymentPlanId(null);
		boolean result = repaymentPlanService.updateRepaymentPlan(repaymentPlan);
		System.out.println("testupdateRepaymentPlan返回的修改结果为：" + result);
	}

	/**
	 * testupdateRepaymentPlanItem
	 */
	// @Test
	public void testupdateRepaymentPlanItem() {
		RepaymentPlanItem repaymentPlanItem = new RepaymentPlanItem();
		repaymentPlanItem.setPrincipal(null);
		repaymentPlanItem.setRepaymentPlanItemId(null);
		repaymentPlanItem.setInterest(null);
		repaymentPlanItem.setServiceFree(null);
		boolean result = repaymentPlanService.updateRepaymentPlanItem(repaymentPlanItem);
		System.out.println("testupdateRepaymentPlanItem返回的修改结果为：" + result);

	}

	/**
	 * testsearchRepaymentPlan
	 */
	// @Test
	public void testsearchRepaymentPlan() {
		Integer repaymentPlanId = null;
		RepaymentPlan repaymentPlan = repaymentPlanService.searchRepaymentPlanByRepaymentPlanId(repaymentPlanId);
		System.out.println("还款方案名称RepaymentPlanName:" + repaymentPlan.getRepaymentPlanName());
		System.out.println("还款方式RepaymentWay:" + repaymentPlan.getRepaymentWay());
		System.out.println("时候上线UseFlag:" + repaymentPlan.getUseFlag());
		System.out.println("首期服务费FirstServiceFree:" + repaymentPlan.getFirstServiceFree());
		System.out.println("后期服务费FollowupServiceFree:" + repaymentPlan.getFollowupServiceFree());
		System.out.println("期数LoanPeriod:" + repaymentPlan.getLoanPeriod());
		System.out.println("名义年利率NominalInterestRate:" + repaymentPlan.getNominalInterestRate());
		System.out.println("还款方案主键RepaymentPlanId:" + repaymentPlan.getRepaymentPlanId());
	}

	/**
	 * testqueryRepaymentPlanDataGrid
	 */
	// @Test
	public void testqueryRepaymentPlanDataGrid() {
		RepaymentPlan repaymentPlanForSearch = new RepaymentPlan();
		Pagination paginationForSearch = new Pagination();

		repaymentPlanForSearch.setFirstServiceFree(null);
		repaymentPlanForSearch.setFollowupServiceFree(null);
		repaymentPlanForSearch.setLoanPeriod(null);
		repaymentPlanForSearch.setNominalInterestRate(null);
		repaymentPlanForSearch.setRepaymentPlanId(null);
		repaymentPlanForSearch.setRepaymentPlanName(null);
		repaymentPlanForSearch.setRepaymentWay(null);
		repaymentPlanForSearch.setUseFlag(null);

		paginationForSearch.setCurrentPage(0);
		paginationForSearch.setPageSize(10);
		String fuzzyQueryValue = null;
		Integer searchFlag = 1;
		Pagination paginationForShow = repaymentPlanService.queryRepaymentPlanDataGrid(repaymentPlanForSearch,
				paginationForSearch, fuzzyQueryValue, searchFlag);
		System.out.println(paginationForShow.getCurrentPage());
		System.out.println(paginationForShow.getPageSize());
		System.out.println(paginationForShow.getTotal());

		List<RepaymentPlan> repaymentPlanList = paginationForShow.getRows();
		for (RepaymentPlan repaymentPlanForShow : repaymentPlanList) {
			System.out.println("还款方案名称-------------" + repaymentPlanForShow.getRepaymentPlanName());
			System.out.println("还款方式-----------------" + repaymentPlanForShow.getRepaymentWay());
			System.out.println("是否启用-----------------" + repaymentPlanForShow.getUseFlag());
			System.out.println("首期服务费---------------" + repaymentPlanForShow.getFirstServiceFree());
			System.out.println("后续服务费---------------" + repaymentPlanForShow.getFollowupServiceFree());
			System.out.println("期数---------------------" + repaymentPlanForShow.getLoanPeriod());
			System.out.println("名义年利率---------------" + repaymentPlanForShow.getNominalInterestRate());
			System.out.println("还款方案主键-------------" + repaymentPlanForShow.getRepaymentPlanId());
			System.out.println("=========================================================");
		}

	}

	/**
	 * queryRepaymentPlanItemDataGrid
	 */
	// @Test
	public void queryRepaymentPlanItemDataGrid() {
		Integer repaymentPlanId = null;
		Pagination paginationForSearch = new Pagination();

		paginationForSearch.setCurrentPage(0);
		paginationForSearch.setPageSize(10);

		Pagination paginationForShow = repaymentPlanService.queryRepaymentPlanItemDataGrid(repaymentPlanId,
				paginationForSearch);

		System.out.println(paginationForShow.getCurrentPage());
		System.out.println(paginationForShow.getPageSize());
		System.out.println(paginationForShow.getTotal());

		List<RepaymentPlanItem> repaymentPlanItemList = paginationForShow.getRows();
		for (RepaymentPlanItem repaymentPlanItemForShow : repaymentPlanItemList) {
			System.out.println("服务费还款方式--------------" + repaymentPlanItemForShow.getChargeServiceMethod());
			System.out.println("利息还款方式----------------" + repaymentPlanItemForShow.getIntertestRepayment());
			System.out.println("本金还款方式----------------" + repaymentPlanItemForShow.getPrincipalRepayment());
			System.out.println("利息------------------------" + repaymentPlanItemForShow.getInterest());
			System.out.println("月份------------------------" + repaymentPlanItemForShow.getMonths());
			System.out.println("本金------------------------" + repaymentPlanItemForShow.getPrincipal());
			System.out.println("还款方案主键-----------------" + repaymentPlanItemForShow.getRepaymentPlanId());
			System.out.println("还款方案明细主键-------------" + repaymentPlanItemForShow.getRepaymentPlanItemId());
			System.out.println("服务费----------------------" + repaymentPlanItemForShow.getServiceFree());
			System.out.println("=========================================================");
		}

	}

}

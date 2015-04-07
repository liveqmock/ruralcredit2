package com.creditease.rc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;

/**
 * 信贷申请单元 测试
 * @author zhangman
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
									"classpath:config/spring/smp/applicationContext-ws-client.xml"
					})
public class CreditApplicationServiceTest extends  AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private ICreditApplicationService creditApplicationService;

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

	/**
	 * 
	 */
//	 @Test
	public void testAddCreditApplication() {
		CreditApplication creditApplication = new CreditApplication();
		// 地址
		creditApplication.setAddress("1");
		// 撤回原因
		creditApplication.setApplyRevokePerson("1");
		// 申请状态
		creditApplication.setAuditStatus("1");
		// 业务类型
		creditApplication.setBusinessType("1");
		// 合同编号
		creditApplication.setContactNumber("23");
		// 组员个数
		creditApplication.setGroupMemberCount(2);
		// 组长姓名
		creditApplication.setGroupName("王五");
		// 小组编号
		creditApplication.setGroupNumber("00000");
		// 操作人编号
		creditApplication.setLoanOfficerId("999");
		// 操作人姓名
		creditApplication.setLoanOfficerName("测试");
		// 放款状态
		creditApplication.setLoansStatus("1");
		// 撤回原因
		creditApplication.setReasonType("0");
		// 上传文件
		creditApplication.setUploadFile("");
		// 公司财务账号id
		creditApplication.setAccountInfoId(1006);
		// 申请撤回时间
		creditApplication.setApplyRevokeTime(new Date());
		// 县id
		creditApplication.setCountyId(888);
		// 创建时间
		// creditApplication.setCreateDate(new Date());
		// 申请id-主键
		creditApplication.setCreditapplicationId(999);
		// 期望放款时间
		creditApplication.setExpectLoanDate(new Date());
		// 小组申请总金额
		creditApplication.setGroupAppTotal(333.00);
		// 还款方案编码
		// creditApplication.setRepaymentPlanId();
		// 实际放款时间
		creditApplication.setSignagreementDate(new Date());
		// 提交日期
		creditApplication.setSubmitDate(new Date());
		// 镇id
		creditApplication.setTownId(22);
		// 村id
		creditApplication.setVillageId(44);
		int result = creditApplicationService.addCreditApplication(creditApplication);
		System.out.println(result);
	}

	// //@Test
	// public void testAddOrUpdateCreditApplication() {
	// fail("Not yet implemented");
	// }
	//
	// //@Test
	// public void testSelectRepaymentPlan() {
	// fail("Not yet implemented");
	// }

	/**
	 * 
	 */
	// @Test
	public void testSelectCreditApplicationList() {

		CreditApplication creditApplication = new CreditApplication();
		Pagination pagination = new Pagination();
		String fuzzyValue = null;

		creditApplication.setAddress("");
		creditApplication.setAuditStatus("");
		creditApplication.setGroupName("");
		creditApplication.setGroupNumber("3");
		creditApplication.setCountyId(null);
		creditApplication.setTownId(null);
		creditApplication.setVillageId(null);
		creditApplication.setBeginExpectLoanDate(null);
		creditApplication.setEndExpectLoanDate(new Date());
		creditApplication.setBeginLoanDate(null);
		creditApplication.setEndLoanDate(new Date());
		creditApplication.setBeginSubmitDate(null);
		creditApplication.setEndSubmitDate(new Date());

		pagination.setCurrentPage(0);
		pagination.setPageSize(10);
		HttpSession session = new MockHttpSession();
		Pagination pagination1 = creditApplicationService.selectCreditApplicationList(creditApplication, fuzzyValue,null,
				pagination, session);

		System.out.println(pagination1.getCurrentPage());
		System.out.println(pagination1.getPageSize());
		System.out.println(pagination1.getTotal());
		List<CreditApplication> CreditApplicationList = pagination1.getRows();
		for (CreditApplication creditApplication1 : CreditApplicationList) {
			System.out.println("地址-------------" + creditApplication1.getAddress());
			System.out.println("申请撤回人---------" + creditApplication1.getApplyRevokePerson());
			System.out.println("审核状态----------" + creditApplication1.getAuditStatus());
			System.out.println("业务类型----------" + creditApplication1.getBusinessType());
			System.out.println("合同编号----------" + creditApplication1.getContactNumber());
			System.out.println("组员个数----------" + creditApplication1.getGroupMemberCount());
			System.out.println("组长姓名----------" + creditApplication1.getGroupName());
			System.out.println("小组编号----------" + creditApplication1.getGroupNumber());
			System.out.println("操作人编号---------" + creditApplication1.getLoanOfficerId());
			System.out.println("操作人姓名---------" + creditApplication1.getLoanOfficerName());
			System.out.println("放款状态----------" + creditApplication1.getLoansStatus());
			System.out.println("撤回原因----------" + creditApplication1.getReasonType());
			System.out.println("上传文件----------" + creditApplication1.getUploadFile());
			System.out.println("公司财务账号id------" + creditApplication1.getAccountInfoId());
			System.out.println("申请撤回时间--------" + creditApplication1.getApplyRevokeTime());
			System.out.println("县id------------" + creditApplication1.getCountyId());
			System.out.println("创建时间----------" + creditApplication1.getCreateDate());
			System.out.println("申请id-主键-------" + creditApplication1.getCreditapplicationId());
			System.out.println("期望放款时间--------" + creditApplication1.getExpectLoanDate());
			System.out.println("小组申请总金额-------" + creditApplication1.getGroupAppTotal());
			System.out.println("还款方案编码--------" + creditApplication1.getRepaymentPlanId());
			System.out.println("实际放款时间--------" + creditApplication1.getSignagreementDate());
			System.out.println("提交日期-----------" + creditApplication1.getSubmitDate());
			System.out.println("镇id------------" + creditApplication1.getTownId());
			System.out.println("村id------------" + creditApplication1.getVillageId());
			System.out.println("=========================================================");
		}

	}

	//
	// //@Test
	// public void testSelectCreditApplication() {
	// fail("Not yet implemented");
	// }

	/**
	 * @author qianghao
	 */
	// @Test
	// @Rollback(false)
	public void testupdateValidStateOfCreditApplication() {
		Integer creditapplicationId = 2990;
		boolean isSuccess = creditApplicationService.updateValidStateOfCreditApplication(creditapplicationId);
		System.out.println("testupdateValidStateOfCreditApplication删除小组申请（及更改validState状态）的返回结果为：" + "====" + isSuccess);
	}

	/**
	 * 测试审核状态修改
	 * 
	 * @author zhangman
	 */
	// @Test
	public void testChangeAuditing() {
		Integer creditapplicationId = 2990;
		CreditApplication creditApplication = new CreditApplication();
		boolean isSuccess = creditApplicationService.changeAuditing(creditApplication, 2990, "05");
		System.out.println("changeAuditing修改信贷状态的返回结果为：" + "====" + isSuccess);
	}

}

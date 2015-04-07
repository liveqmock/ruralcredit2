package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.app.credit.ContractObj;
import com.creditease.rc.app.credit.ContractObj.QyOverdueList;
import com.creditease.rc.app.credit.LoanBalanceDataResponse;
import com.creditease.rc.app.credit.LoanBalanceDataResult;
import com.creditease.rc.app.credit.LoanBalanceDataResult.ContractList;
import com.creditease.rc.app.credit.QyOverdueListObj;
import com.creditease.rc.domain.ClientApplyHistory;
import com.creditease.rc.service.IClientApplyHistoryService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;

/**
 * 消息重发单元测试
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
public class ClientApplyHistoryServiceTest {
	@Resource
	private IClientApplyHistoryService clientApplyHistoryService;

	@Resource
	private IRural2CreditService rural2CreditService;

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
	public void testInsert() {
		ClientApplyHistory clientApplyHistory = new ClientApplyHistory();
		clientApplyHistory.setCreditapplicationId(13123123l);
		clientApplyHistory.setIsSuccess("1");
		clientApplyHistory.setMessage("123123123");
		clientApplyHistory.setSysGuid("dfasdfadsfkdfsjdfllfjasdf");
		boolean s = clientApplyHistoryService.dynamicInsert(clientApplyHistory);
		System.out.println(s);
	}

	@Test
	public void testUpdate() {
		ClientApplyHistory clientApplyHistory = new ClientApplyHistory();
		clientApplyHistory.setClientApplyHistoryId(43535l);
		clientApplyHistory.setCreditapplicationId(111111111l);
		clientApplyHistory.setIsSuccess("0");
		clientApplyHistory.setMessage("222222222");
		clientApplyHistory.setSysGuid("333333333");
		boolean s = clientApplyHistoryService.dynamicUpdate(clientApplyHistory);
		System.out.println(s);
	}

	@Test
	public void testtesttest() {
		LoanBalanceDataResponse loanBalanceDataResponse = rural2CreditService.loanBalanceData(null, null, null);
		LoanBalanceDataResult loanBalanceDataResult = loanBalanceDataResponse.getLoanBalanceDataResult();
		System.out.println(loanBalanceDataResult.getCode());
		System.out.println(loanBalanceDataResult.getMessage());
		ContractList contractList = loanBalanceDataResult.getContractList();
		List<ContractObj> contractObjs = contractList.getContractObj();
		int i = 0;
		if (CommonUtil.isNotEmpty(contractObjs)) {
			for (ContractObj contractObj : contractObjs) {
				System.out.println("==========" + contractObjs + i + "==========BEGIN");
				System.out.println();
				System.out.println(contractObj.getApplyId());
				System.out.println(contractObj.getDCharges());
				System.out.println(contractObj.getDCorpus());
				System.out.println(contractObj.getDfCharges());
				System.out.println(contractObj.getDfCorpus());
				System.out.println(contractObj.getDfInterest());
				System.out.println(contractObj.getDfOverhead());
				System.out.println(contractObj.getFCharges());
				System.out.println(contractObj.getFCorpus());
				System.out.println(contractObj.getFInterest());
				System.out.println(contractObj.getFOverhead());
				QyOverdueList overdueList = contractObj.getQyOverdueList();
				if (overdueList != null) {
					List<QyOverdueListObj> qyOverdueListObjs = overdueList.getQyOverdueListObj();
					int j = 0;
					if (CommonUtil.isNotEmpty(qyOverdueListObjs)) {
						for (QyOverdueListObj qyOverdueListObj : qyOverdueListObjs) {
							System.out.println("==========" + qyOverdueListObjs + j + "==========BEGIN");
							System.out.println(qyOverdueListObj.getOdCharges());
							System.out.println(qyOverdueListObj.getOdCorpus());
							System.out.println(qyOverdueListObj.getOdDamages());
							System.out.println(qyOverdueListObj.getOdfCharges());
							System.out.println(qyOverdueListObj.getOdfCorpus());
							System.out.println(qyOverdueListObj.getOdfDamages());
							System.out.println(qyOverdueListObj.getOdfForfeit());
							System.out.println(qyOverdueListObj.getOdfInterest());
							System.out.println(qyOverdueListObj.getOdfLatefee());
							System.out.println(qyOverdueListObj.getOdForfeit());
							System.out.println(qyOverdueListObj.getOdfOverhead());
							System.out.println(qyOverdueListObj.getOfCharges());
							System.out.println(qyOverdueListObj.getOfCorpus());
							System.out.println(qyOverdueListObj.getOfDamages());
							System.out.println(qyOverdueListObj.getOfForfeit());
							System.out.println(qyOverdueListObj.getOfInterest());
							System.out.println(qyOverdueListObj.getOfLatefee());
							System.out.println(qyOverdueListObj.getOfOverhead());
							System.out.println(qyOverdueListObj.getOverdueDays());
							System.out.println(qyOverdueListObj.getOverdueStart());
							System.out.println("==========" + qyOverdueListObjs + j + "==========END");
						}
					}
				}
				System.out.println("==========" + contractObjs + i + "==========END");
			}
		}
	}
}

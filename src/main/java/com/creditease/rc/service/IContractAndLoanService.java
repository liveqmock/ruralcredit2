package com.creditease.rc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.TradeDealForm;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ContractRate;
import com.creditease.rc.vo.ContractRateForQYResult;
import com.creditease.rc.vo.LoanConfirmMessageVo;

public interface IContractAndLoanService {

	Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination);

	ContractRate CalculateByCreditapplicationId(Long creditapplicationId);
	
	ContractRate CalculateByCreditapplicationId(Long creditapplicationId,BigDecimal amount);

	//打印合同时更新业务状态
	boolean updatePrintContractState(Long creditapplicationId);

	//作废合同更新业务状态
	boolean updateFailContractState(Long creditapplicationId);

	//上传合同资料更新业务状态
	boolean updateUploadingContractState(Long creditapplicationId);

	//放款确认  查询基本信息
	LoanConfirmMessageVo searchForConfirm(Long creditapplicationId);
	//查询营业部ID和电话号码
	AccountInfo searchForCompanyNameAndMobilePhone(Long creditapplicationId);

	AmountConfirm searchForConfirmAmount(Long creditapplicationId);

	ContractRateForQYResult qyContractRateForQYResult(Long creditapplicationId);

	//查询业务状态
	CreditApplication searchForCreditApplicationStates(Long creditapplicationId);

	List<Long> queryCreditIdListLoan();


	
	//查询是否配置过放款渠道
	LendingConfiguration searchConfigureChannel(Long creditapplicationId);

	//调用机构资产的撮合接口
	Message addTtrustprintContract(AmountConfirm amountConfirm,
			AccountInfo accountInfo, GroupLoanRegist groupLoadRegist,CreditApplication creditApplication,String type);
	
	/**
	 * 根据信贷申请id查询放款渠道
	 * @param creditapplicationId
	 * @return
	 */
	String selectByCaIdLf(Long creditapplicationId);

	/**
	 * 撤销撮合接口
	 * @param creditApplication
	 * @return
	 */
	Message revokeMatchmaking(CreditApplication creditApplication);

	//通知贷后放款前更改业务状态为 等待放款
	boolean updateWaitLoanContractState(Long creditapplicationId);

	//信托复核  未通过时 更改业务状态为：等待合同重新复核
	boolean updateWaitContractAgainCheck(Long creditapplicationId);

	/**
	 * 变更放款渠道
	 * @param creditapplicationId
	 * @param request
	 * @return
	 */
	boolean updateChangeLoanChannelXinTuo(Long creditapplicationId);

	/**
	 * 查询信托计划名称
	 * 
	 */
	List<TradeDealForm> selectTradeDealFormResultByCaId(Long creditapplicationId);

	/**
	 * 客户放弃，拒贷的时候  撤销撮合
	 * @param creditapplicationId
	 * @param section
	 * @param refuseReasons
	 * @param auditStatus
	 * @return
	 */
	Message revokeMatchmaking(Long creditapplicationId, String section,
			String refuseReasons, String auditStatus);
	
	//信托复核  未通过时 更改业务状态为：等待合同重新复核（不更新历史状态）
	public boolean updateWaitContractAgainCheckOther(Long creditapplicationId);

}

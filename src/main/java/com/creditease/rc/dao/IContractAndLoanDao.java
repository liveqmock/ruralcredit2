package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ContractRateForQYResult;
import com.creditease.rc.vo.LoanConfirmMessageVo;

public interface IContractAndLoanDao {

	Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination);

	ContractRateForQYResult qyContractRateForQYResult(Long creditapplicationId);

	//打印合同时更新 业务状态
	int updatePrintContractState(Long creditapplicationId);

	//作废合同更新业务状态
	int updateFailContractState(Long creditapplicationId);

	//上传合同资料更新业务状态
	int updateUploadingContractState(Long creditapplicationId);

	//放款确认  查询基本信息
	LoanConfirmMessageVo searchForConfirm(Long creditapplicationId);

	//查询借款总额
	AmountConfirm searchForConfirmAmount(Long creditapplicationId);

	//查询业务状态
	CreditApplication searchForCreditApplicationStates(Long creditapplicationId);

	List<Long> queryCreditIdListLoan();

	//查询是否配置过放款渠道
	LendingConfiguration searchConfigureChannel(Long creditapplicationId);

	//查询电话，营业部名称
	AccountInfo searchForCompanyNameAndMobilePhone(Long creditapplicationId);
	
	/**
	 * 根据信贷申请id查询放款渠道
	 * @param creditapplicationId
	 * @return
	 */
	String selectByCaIdLf(Long creditapplicationId);

	//通知贷后放款前 更改业务状态为等待放款
	int updateWaitLoanContractState(Long creditapplicationId);
	//在放款确认时 更改放款登记表里的数据为历史数据
	int updateSupplementaryDataContractStateHistryFlag(Long creditapplicationId);

	//信托复核 未通过时 更改业务状态为等待合同重新复核
	int updateWaitContractAgainCheckState(Long creditapplicationId);

	//变更放款渠道
	int updateChangeLoanChannelXinTuo(Long creditapplicationId);

}

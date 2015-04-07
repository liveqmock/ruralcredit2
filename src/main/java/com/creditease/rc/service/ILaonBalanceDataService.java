package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.LaonBalanceData;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.LoanTableData;

public interface ILaonBalanceDataService {

	boolean dynamicInsert(LaonBalanceData balanceData);

	boolean dynamicUpdate(LaonBalanceData balanceData);

	Message insertSynchronizationLoan(Long creditapplicationId, String officeId, String sellerId);

	void insertSynchronization();

	boolean saveLaonBalanceDatas(List<LaonBalanceData> laonBalanceDatas);

	boolean deleteLaonBalanceData();

	List<LoanTableData> queryLoanTableDatas(Map<String, String> queryMap);

}

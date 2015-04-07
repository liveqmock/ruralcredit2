package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.LaonBalanceData;
import com.creditease.rc.vo.LoanBalanceQuertData;

public interface ILaonBalanceDataDao {

	boolean dynamicInsert(LaonBalanceData balanceData);

	boolean dynamicUpdate(LaonBalanceData balanceData);

	boolean saveLaonBalanceDatas(List<LaonBalanceData> laonBalanceDatas);

	boolean deleteLaonBalanceData();

	List<LoanBalanceQuertData> queryLoanTableDatas(Map<String, String> queryMap);

}

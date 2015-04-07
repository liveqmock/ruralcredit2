package com.creditease.rc.service;

import com.creditease.rc.domain.CancelContractReason;
import com.creditease.rc.domain.Message;

public interface ICancelContractReasonService {
	 //添加作废合同原因
	void saveCancelReason(CancelContractReason cancelContractReason);
	//修改作废合同原因的   history 标识
	void updateCancelReasonHistory(Long creditapplicationId);
	
	
	 //添加作废合同原因 撤销撮合
	Message saveCancelReasonRepeal(CancelContractReason cancelContractReason);
	//修改作废合同原因的   history 标识 撤销撮合
	Message updateCancelReasonHistoryRepeal(Long creditapplicationId);
	
	//查询作废合同原因(是否是第一次作废合同)
	CancelContractReason selectReason(Long creditapplicationId);
}

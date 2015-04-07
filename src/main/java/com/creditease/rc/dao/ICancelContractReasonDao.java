package com.creditease.rc.dao;

import com.creditease.rc.domain.CancelContractReason;

public interface ICancelContractReasonDao {
	//添加作废合同原因
	Long saveCancelReason(CancelContractReason cancelContractReason);
	//修改作废合同原因的   history 标识
	 int updateCancelReasonHistory(Long creditapplicationId);
	 //查询作废合同原因(是否是第一次作废合同)
	CancelContractReason selectReason(Long creditapplicationId);
}

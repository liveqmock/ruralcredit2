package com.creditease.rc.service;

import com.creditease.rc.app.orgams.BaseResponse;
import com.creditease.rc.app.orgams.MatchBorrowResVo;
import com.creditease.rc.app.orgams2.BorrowContractRes;
import com.creditease.rc.app.orgams2.ContractStateRes;
import com.creditease.rc.app.orgams2.SynBatchBorrowContractResponse;

public interface IorgamsService {

	/**
	 * 借款请求
	 * @param borrowMatchingReq
	 * @return
	 */
	public MatchBorrowResVo borrowMatchingReq(Long creditapplicationId,Double irr,Double amount);
	
	/**
	 * 借款撤销
	 * @param unMatching
	 * @return
	 */
	public BaseResponse upMatching(Long creditapplicationId);
	
	/**
	 * 借款确认
	 * @param matchConfirm
	 * @return
	 */
	public BaseResponse  matchConfirm(Long creditapplicationId);
	
	
	/**
	 * 放款状态同步
	 * @param synBorrowContractState
	 * @return
	 */
	public ContractStateRes synBorrowContractState(Long creditapplicationId,String state);
	
	/**
	 * 合同同步
	 * @param borrowContractReq
	 * @return
	 */
	public BorrowContractRes synBorrowContract(Long creditapplicationId);
	
	/**
	 * 批量合同同步
	 * @param borrowContractReq
	 * @return
	 */
	public SynBatchBorrowContractResponse synBatchBorrowContract(Long creditapplicationId);
	
}

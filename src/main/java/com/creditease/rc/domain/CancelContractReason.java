package com.creditease.rc.domain;

public class CancelContractReason {
	private Long cancelContractReasonId;			//作废合同原因表ID
	private String cancelContractReasonContent;		//作废合同原因内容
	private Long creditapplicationId;				//信贷申请单id
	private String historyFlag;						//历史标识 T历史   F非历史
	
	
	
	public String getHistoryFlag() {
		return historyFlag;
	}
	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public Long getCancelContractReasonId() {
		return cancelContractReasonId;
	}
	public void setCancelContractReasonId(Long cancelContractReasonId) {
		this.cancelContractReasonId = cancelContractReasonId;
	}
	public String getCancelContractReasonContent() {
		return cancelContractReasonContent;
	}
	public void setCancelContractReasonContent(String cancelContractReasonContent) {
		this.cancelContractReasonContent = cancelContractReasonContent;
	}
	
	
}

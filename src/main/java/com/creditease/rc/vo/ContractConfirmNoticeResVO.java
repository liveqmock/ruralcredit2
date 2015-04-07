package com.creditease.rc.vo;

import java.util.Date;

public class ContractConfirmNoticeResVO {

	private String transactionCode;//交易响应码(0:成功，-1：失败)
	private String transactionMsg;//交易响应信息
	private Date transactionCompleteTime;//交易完成时间
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getTransactionMsg() {
		return transactionMsg;
	}
	public void setTransactionMsg(String transactionMsg) {
		this.transactionMsg = transactionMsg;
	}
	public Date getTransactionCompleteTime() {
		return transactionCompleteTime;
	}
	public void setTransactionCompleteTime(Date transactionCompleteTime) {
		this.transactionCompleteTime = transactionCompleteTime;
	}
	
}

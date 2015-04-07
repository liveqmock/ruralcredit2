package com.creditease.rc.vo;

import com.creditease.rc.domain.CalculatedBorrowingRateInfo;

public class ContractRateForQYResult extends CalculatedBorrowingRateInfo{

	private String 			clientName				;              //客户姓名
	private Long			borrowerServiceAppId		;			   //borrowerAppID
	private String 			borrowUse				;        	   //借款用途
	private String 			borrowUseText			;              //借款用途
	private String 			productName				;              //产品名称
	private String 			auditStatus				;			   //业务状态
	
	
	public Long getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}
	public void setBorrowerServiceAppId(Long borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getBorrowUse() {
		return borrowUse;
	}
	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBorrowUseText() {
		return borrowUseText;
	}
	public void setBorrowUseText(String borrowUseText) {
		this.borrowUseText = borrowUseText;
	}
	
	
}

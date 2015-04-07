package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 放款明细类
 * @author zhangman
 *
 */
public class GroupLoanRegistDetail implements Serializable {
	private Integer groupLoanDetailLogId;//小组放款登记明细日志主键
	private Integer groupLoanRegistId;		//	小组放款登记id
	private Integer borrowerId;//借款人编号
	private String borrowerName;//借款人姓名
	private String credentialsNumber;//借款人身份证号
	private Double loanAmount;//放款金额
	private Double realAmount;//实发金额
	private Integer creditapplicationId;//小组信贷申请单主键
	
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getCredentialsNumber() {
		return credentialsNumber;
	}
	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}
	public Integer getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}
	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public Integer getGroupLoanDetailLogId() {
		return groupLoanDetailLogId;
	}
	public void setGroupLoanDetailLogId(Integer groupLoanDetailLogId) {
		this.groupLoanDetailLogId = groupLoanDetailLogId;
	}
	public Integer getGroupLoanRegistId() {
		return groupLoanRegistId;
	}
	public void setGroupLoanRegistId(Integer groupLoanRegistId) {
		this.groupLoanRegistId = groupLoanRegistId;
	}
}

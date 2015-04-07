package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 放款明细类
 * @author zhangman
 *
 */
public class LoanRegist implements Serializable {
	private Integer LoanregistId;//小组放款登记明细日志主键
	private Integer groupLoanRegistId;		//	小组放款登记id
	private String borrowerName;//借款人姓名
	private Double loanAmount;//放款金额
	private Double realAmount;//实发金额
	
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
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
	public Integer getGroupLoanRegistId() {
		return groupLoanRegistId;
	}
	public void setGroupLoanRegistId(Integer groupLoanRegistId) {
		this.groupLoanRegistId = groupLoanRegistId;
	}
	public Integer getLoanregistId() {
		return LoanregistId;
	}
	public void setLoanregistId(Integer loanregistId) {
		LoanregistId = loanregistId;
	}
}

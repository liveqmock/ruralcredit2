package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author zhangman
 *
 */
public class CreditCardInfo implements Serializable {
	private Integer creditCardInfoId;
	private Integer borrowerServiceAppId;
	private String creditCondition;
	private Double creditCardChargeTotal;
	private Double loanGuaranteesOther;
	private String creditBorrowCondition;
	private String creditBorrowConditionTime;
	private Double loanChargeTotal;

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getCreditBorrowCondition() {
		return creditBorrowCondition;
	}

	public void setCreditBorrowCondition(String creditBorrowCondition) {
		this.creditBorrowCondition = creditBorrowCondition;
	}

	public String getCreditBorrowConditionTime() {
		return creditBorrowConditionTime;
	}

	public void setCreditBorrowConditionTime(String creditBorrowConditionTime) {
		this.creditBorrowConditionTime = creditBorrowConditionTime;
	}

	public Integer getCreditCardInfoId() {
		return creditCardInfoId;
	}

	public void setCreditCardInfoId(Integer creditCardInfoId) {
		this.creditCardInfoId = creditCardInfoId;
	}

	public String getCreditCondition() {
		return creditCondition;
	}

	public void setCreditCondition(String creditCondition) {
		this.creditCondition = creditCondition;
	}



	public Double getLoanGuaranteesOther() {
		return loanGuaranteesOther;
	}

	public void setLoanGuaranteesOther(Double loanGuaranteesOther) {
		this.loanGuaranteesOther = loanGuaranteesOther;
	}

	public Double getLoanChargeTotal() {
		return loanChargeTotal;
	}

	public void setLoanChargeTotal(Double loanChargeTotal) {
		this.loanChargeTotal = loanChargeTotal;
	}

	public Double getCreditCardChargeTotal() {
		return creditCardChargeTotal;
	}

	public void setCreditCardChargeTotal(Double creditCardChargeTotal) {
		this.creditCardChargeTotal = creditCardChargeTotal;
	}

}

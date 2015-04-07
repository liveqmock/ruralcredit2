package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 
 * @author haoqiang
 * 
 */
public class Familydepositdebt implements Serializable {

	private Integer familyDepositDebtId;
	private Integer borrowerServiceAppId;
	private String depositDebtType;
	private String bank;
	private Double amount;

	public Integer getFamilyDepositDebtId() {
		return familyDepositDebtId;
	}

	public void setFamilyDepositDebtId(Integer familyDepositDebtId) {
		this.familyDepositDebtId = familyDepositDebtId;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getDepositDebtType() {
		return depositDebtType;
	}

	public void setDepositDebtType(String depositDebtType) {
		this.depositDebtType = depositDebtType;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}

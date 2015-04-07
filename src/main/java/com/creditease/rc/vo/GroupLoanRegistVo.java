package com.creditease.rc.vo;

import com.creditease.rc.domain.GroupLoanRegist;

public class GroupLoanRegistVo extends GroupLoanRegist {
	private String credentialsNumber;
	private String bankAccountNumber;

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
}

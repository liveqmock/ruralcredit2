package com.creditease.rc.vo;

import java.math.BigDecimal;

public class LoanBalanceQuertData {
	
	private BigDecimal balance          ;
	private BigDecimal defaultValue     ;
	private int        overdueDays      ;
	private int        customerCount    ;
	
	
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(BigDecimal defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}
	public int getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}

	
	

}

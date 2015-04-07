package com.creditease.rc.vo;

import java.math.BigDecimal;

public class LoanTableData {
	private int index;
	private int customerCount;
	private BigDecimal customerCountRate;
	private String customerCountRateShow;
	private BigDecimal defaultValue;
	private BigDecimal defaultValueRate;
	private String defaultValueRateShow;
	private BigDecimal loanBalance;
	private BigDecimal loanBalanceRate;
	private String loanBalanceRateShow;
	private String description;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}

	public BigDecimal getCustomerCountRate() {
		return customerCountRate;
	}

	public void setCustomerCountRate(BigDecimal customerCountRate) {
		this.customerCountRate = customerCountRate;
	}

	public BigDecimal getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(BigDecimal defaultValue) {
		this.defaultValue = defaultValue;
	}

	public BigDecimal getDefaultValueRate() {
		return defaultValueRate;
	}

	public void setDefaultValueRate(BigDecimal defaultValueRate) {
		this.defaultValueRate = defaultValueRate;
	}

	public BigDecimal getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}

	public BigDecimal getLoanBalanceRate() {
		return loanBalanceRate;
	}

	public void setLoanBalanceRate(BigDecimal loanBalanceRate) {
		this.loanBalanceRate = loanBalanceRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomerCountRateShow() {
		return customerCountRateShow;
	}

	public void setCustomerCountRateShow(String customerCountRateShow) {
		this.customerCountRateShow = customerCountRateShow;
	}

	public String getDefaultValueRateShow() {
		return defaultValueRateShow;
	}

	public void setDefaultValueRateShow(String defaultValueRateShow) {
		this.defaultValueRateShow = defaultValueRateShow;
	}

	public String getLoanBalanceRateShow() {
		return loanBalanceRateShow;
	}

	public void setLoanBalanceRateShow(String loanBalanceRateShow) {
		this.loanBalanceRateShow = loanBalanceRateShow;
	}

}

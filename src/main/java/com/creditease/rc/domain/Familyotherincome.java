package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 
 * @author haoqiang
 * 
 */
public class Familyotherincome implements Serializable {
	private Integer familyOtherIncomeId;
	private Integer borrowerServiceAppId;
	private String otherIncomeType;
	private Double amount;
	private String qulitily;
	private String price;

	public Integer getFamilyOtherIncomeId() {
		return familyOtherIncomeId;
	}

	public void setFamilyOtherIncomeId(Integer familyOtherIncomeId) {
		this.familyOtherIncomeId = familyOtherIncomeId;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getOtherIncomeType() {
		return otherIncomeType;
	}

	public void setOtherIncomeType(String otherIncomeType) {
		this.otherIncomeType = otherIncomeType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getQulitily() {
		return qulitily;
	}

	public void setQulitily(String qulitily) {
		this.qulitily = qulitily;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}

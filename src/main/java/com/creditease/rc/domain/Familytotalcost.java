package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 
 * @author haoqiang
 * 
 */
public class Familytotalcost implements Serializable {

	private Integer familyTotalCostId;
	private Integer borrowerServiceAppId;
	private String totalCostType;
	private Double amount;

	public Integer getFamilyTotalCostId() {
		return familyTotalCostId;
	}

	public void setFamilyTotalCostId(Integer familyTotalCostId) {
		this.familyTotalCostId = familyTotalCostId;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getTotalCostType() {
		return totalCostType;
	}

	public void setTotalCostType(String totalCostType) {
		this.totalCostType = totalCostType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}

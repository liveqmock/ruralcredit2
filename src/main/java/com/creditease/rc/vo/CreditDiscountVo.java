package com.creditease.rc.vo;

import java.math.BigDecimal;

public class CreditDiscountVo {
	
	private Long creditapplicationId;
	private String discountFlag;
	private BigDecimal discount;
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getDiscountFlag() {
		return discountFlag;
	}
	public void setDiscountFlag(String discountFlag) {
		this.discountFlag = discountFlag;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	
}

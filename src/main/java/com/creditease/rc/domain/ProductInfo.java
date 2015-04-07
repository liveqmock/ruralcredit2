package com.creditease.rc.domain;

public class ProductInfo {
	private String productCategoryId;
	private String instalments;
	private String productName;
	private String paymentTypeCode;
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}
	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}
	public String getInstalments() {
		return instalments;
	}
	public void setInstalments(String instalments) {
		this.instalments = instalments;
	}
}

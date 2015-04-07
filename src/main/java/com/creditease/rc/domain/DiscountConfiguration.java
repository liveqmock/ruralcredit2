package com.creditease.rc.domain;

import java.math.BigDecimal;

public class DiscountConfiguration {
	private Long 		discountConfigurationId      ;   //打折配置主键
	private Long 		areaDepartmentId				;            //营业部ID
	private String 		areaDepartmentName			;        //营业部名称
	private Long 		productinfoId					;               //产品版本编号
	private Long 		productCategoryId				;           //产品分类编号
	private BigDecimal 	discountRate				;          //折扣率
	private String      productName					;		//产品名称
	
	
	
	
	
	public Long getDiscountConfigurationId() {
		return discountConfigurationId;
	}
	public void setDiscountConfigurationId(Long discountConfigurationId) {
		this.discountConfigurationId = discountConfigurationId;
	}
	public Long getAreaDepartmentId() {
		return areaDepartmentId;
	}
	public void setAreaDepartmentId(Long areaDepartmentId) {
		this.areaDepartmentId = areaDepartmentId;
	}
	public String getAreaDepartmentName() {
		return areaDepartmentName;
	}
	public void setAreaDepartmentName(String areaDepartmentName) {
		this.areaDepartmentName = areaDepartmentName;
	}
	public Long getProductinfoId() {
		return productinfoId;
	}
	public void setProductinfoId(Long productinfoId) {
		this.productinfoId = productinfoId;
	}
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	
}

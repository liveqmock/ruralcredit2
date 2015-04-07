package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 
 * @author haoqiang
 * 
 */
public class Householdasserts implements Serializable {

	private Integer householdAssertsId;
	private Integer borrowerServiceAppId;
	private String householdAssertsType;
	private String description1;
	private String description2;
	private Double value;
	private String unit;		//单位
	private String buyDate;		//购买年份
	private String buyPrice;	//购买价格
	private String photo;		//有无照片

	public Integer getHouseholdAssertsId() {
		return householdAssertsId;
	}

	public void setHouseholdAssertsId(Integer householdAssertsId) {
		this.householdAssertsId = householdAssertsId;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getHouseholdAssertsType() {
		return householdAssertsType;
	}

	public void setHouseholdAssertsType(String householdAssertsType) {
		this.householdAssertsType = householdAssertsType;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}

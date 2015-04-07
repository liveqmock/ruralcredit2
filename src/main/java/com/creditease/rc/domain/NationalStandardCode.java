package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author zhangman
 *
 */
public class NationalStandardCode implements Serializable{
	private Integer nationalStandardCodeId;
	private Integer cityId;
	private String cityName;
	private Integer parentId;
	private Integer cityCode;
	private String onlineFlag;
	private Integer cityTelCode;
	public Integer getNationalStandardCodeId() {
		return nationalStandardCodeId;
	}
	public void setNationalStandardCodeId(Integer nationalStandardCodeId) {
		this.nationalStandardCodeId = nationalStandardCodeId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	public String getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	public Integer getCityTelCode() {
		return cityTelCode;
	}
	public void setCityTelCode(Integer cityTelCode) {
		this.cityTelCode = cityTelCode;
	}

}

package com.creditease.rc.vo;

import java.util.Date;

public class ConvertPrizeHistory {

	
	 private String 		upriRecordCode		; //兑奖编码
	 private String 		recpriDate			; //兑奖日期
	 private String 		location            ;   //地址
	 private String 		pictureAddress	    ;  //奖品图片地址
	 private String  		status              ;  //状态0：未兑奖1：已兑奖
	 
	 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpriRecordCode() {
		return upriRecordCode;
	}
	public void setUpriRecordCode(String upriRecordCode) {
		this.upriRecordCode = upriRecordCode;
	}
	
	public String getRecpriDate() {
		return recpriDate;
	}
	public void setRecpriDate(String recpriDate) {
		this.recpriDate = recpriDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}
	 
	 
}

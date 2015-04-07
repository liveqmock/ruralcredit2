package com.creditease.rc.vo;

/**
 * getOrderInfo 入参（for综合信贷）
 * 
 * @author haoqiang
 * 
 */
public class OrderInfoRequestParam {

	private String businessNumber; // 业务单号
	private String clientName; // 客户姓名
	private String clientIDNumber; // 客户身份证号
	private String uuid; // uuid

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientIDNumber() {
		return clientIDNumber;
	}

	public void setClientIDNumber(String clientIDNumber) {
		this.clientIDNumber = clientIDNumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}

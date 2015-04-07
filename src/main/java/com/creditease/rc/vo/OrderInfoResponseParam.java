package com.creditease.rc.vo;

import java.util.List;

/**
 * getOrderInfo 出参（for综合信贷）
 * 
 * @author haoqiang
 * 
 */
public class OrderInfoResponseParam {

	private String codeInfo; // 响应结果
	private String message; // 响应消息
	private List<OrderInfoList> orderInfoList;

	public String getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(String codeInfo) {
		this.codeInfo = codeInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderInfoList> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfoList> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

}

package com.creditease.rc.vo;
/**
 * 
 * cs
 * Title:DepartmentCountInfo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:25:34
 * @author wyf
 * @version v2.0
 */
public class DepartmentCountInfo {
	private int paymentBusinessCount = 0;
	private Double paymentBusinessSum = 0.0;
	private String monthName;

	public int getPaymentBusinessCount() {
		return paymentBusinessCount;
	}

	public void setPaymentBusinessCount(int paymentBusinessCount) {
		this.paymentBusinessCount = paymentBusinessCount;
	}

	public Double getPaymentBusinessSum() {
		return paymentBusinessSum;
	}

	public void setPaymentBusinessSum(Double paymentBusinessSum) {
		this.paymentBusinessSum = paymentBusinessSum;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

}

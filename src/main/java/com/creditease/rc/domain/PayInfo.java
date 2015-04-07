package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 放款确认表
 * 
 * @author xubingzhao
 * 
 */
public class PayInfo {
	private Integer payInfoId;			//id
	private Integer creditapplicationId;//信贷申请id
	private String importBank;
	private String importAccountName;
	private String importAccount;
	private String exportAccount;
	private String payWay;
	private Double payMoney;
	private Date payDate;
	private Date payConfimDate;
	private String payConfimPerson;
	private String payOperator;
	private String payRemark;
	public Integer getPayInfoId() {
		return payInfoId;
	}
	public void setPayInfoId(Integer payInfoId) {
		this.payInfoId = payInfoId;
	}
	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getImportBank() {
		return importBank;
	}
	public void setImportBank(String importBank) {
		this.importBank = importBank;
	}
	public String getImportAccountName() {
		return importAccountName;
	}
	public void setImportAccountName(String importAccountName) {
		this.importAccountName = importAccountName;
	}
	public String getImportAccount() {
		return importAccount;
	}
	public void setImportAccount(String importAccount) {
		this.importAccount = importAccount;
	}
	public String getExportAccount() {
		return exportAccount;
	}
	public void setExportAccount(String exportAccount) {
		this.exportAccount = exportAccount;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getPayConfimDate() {
		return payConfimDate;
	}
	public void setPayConfimDate(Date payConfimDate) {
		this.payConfimDate = payConfimDate;
	}
	public String getPayConfimPerson() {
		return payConfimPerson;
	}
	public void setPayConfimPerson(String payConfimPerson) {
		this.payConfimPerson = payConfimPerson;
	}
	public String getPayOperator() {
		return payOperator;
	}
	public void setPayOperator(String payOperator) {
		this.payOperator = payOperator;
	}
	public String getPayRemark() {
		return payRemark;
	}
	public void setPayRemark(String payRemark) {
		this.payRemark = payRemark;
	}
	
}
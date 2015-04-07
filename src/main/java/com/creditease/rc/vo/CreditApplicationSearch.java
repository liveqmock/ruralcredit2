package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class CreditApplicationSearch {

	private String businessnumber;
	private String  borrower;
	private String   auditstatus;
	private String   auditstatusShow;
	private String   company;
	private String  loanofficername;
	private String  createloanofficername;
	private String  borrowmoney;
	private Date signagreementdate;
	private String partner;
	private String guarantor;
	public String getBusinessnumber() {
		return businessnumber;
	}
	public void setBusinessnumber(String businessnumber) {
		this.businessnumber = businessnumber;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLoanofficername() {
		return loanofficername;
	}
	public void setLoanofficername(String loanofficername) {
		this.loanofficername = loanofficername;
	}
	public String getBorrowmoney() {
		return borrowmoney;
	}
	public void setBorrowmoney(String borrowmoney) {
		this.borrowmoney = borrowmoney;
	}
	public String getGuarantor() {
		return guarantor;
	}
	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}
	public String getAuditstatusShow() {
		return auditstatusShow;
	}
	public void setAuditstatusShow(String auditstatusShow) {
		this.auditstatusShow = auditstatusShow;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	public String getCreateloanofficername() {
		return createloanofficername;
	}
	public void setCreateloanofficername(String createloanofficername) {
		this.createloanofficername = createloanofficername;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getSignagreementdate() {
		return signagreementdate;
	}
	public void setSignagreementdate(Date signagreementdate) {
		this.signagreementdate = signagreementdate;
	}
}

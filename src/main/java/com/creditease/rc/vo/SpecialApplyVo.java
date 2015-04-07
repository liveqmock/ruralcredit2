package com.creditease.rc.vo;

import com.creditease.rc.domain.SpecialApply;

public class SpecialApplyVo extends SpecialApply {
	private String businessNumber;   //编号
	private String name;			 //借款人姓名
	private String companyId;		//营业部ID
	private String companyName;		//公司名称
	private String loanOfficerName; //客户经理姓名
	private String auditStatus;		//业务的审批状态
	private String auditStatusShow;		//业务的审批状态显示
	private String specialApplyTypeShow;
	private String specialApplyStateShow; 
	private String auditSpecialApplyStateShow;
	
	
	
	
	public String getSpecialApplyTypeShow() {
		return specialApplyTypeShow;
	}
	public void setSpecialApplyTypeShow(String specialApplyTypeShow) {
		this.specialApplyTypeShow = specialApplyTypeShow;
	}
	public String getSpecialApplyStateShow() {
		return specialApplyStateShow;
	}
	public void setSpecialApplyStateShow(String specialApplyStateShow) {
		this.specialApplyStateShow = specialApplyStateShow;
	}
	public String getAuditSpecialApplyStateShow() {
		return auditSpecialApplyStateShow;
	}
	public void setAuditSpecialApplyStateShow(String auditSpecialApplyStateShow) {
		this.auditSpecialApplyStateShow = auditSpecialApplyStateShow;
	}
	public String getAuditStatusShow() {
		return auditStatusShow;
	}
	public void setAuditStatusShow(String auditStatusShow) {
		this.auditStatusShow = auditStatusShow;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLoanOfficerName() {
		return loanOfficerName;
	}
	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}
	
}

package com.creditease.rc.domain;

public class BalanceAccountApplyVo  extends BalanceAccountApply{
	private String businessNumber;   //业务单号
	private String credentialsNumber;//身份证号
	private String name;		 //借款人姓名（客户姓名）
	private String earlyRepaymentShow;//显示是否是提前还款
	private String auditResultShow;		//审批状态

	public String getAuditResultShow() {
		return auditResultShow;
	}

	public void setAuditResultShow(String auditResultShow) {
		this.auditResultShow = auditResultShow;
	}

	public String getEarlyRepaymentShow() {
		return earlyRepaymentShow;
	}

	public void setEarlyRepaymentShow(String earlyRepaymentShow) {
		this.earlyRepaymentShow = earlyRepaymentShow;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

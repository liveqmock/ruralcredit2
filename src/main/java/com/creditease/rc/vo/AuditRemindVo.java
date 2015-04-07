package com.creditease.rc.vo;

/**
 * 审批提醒
 * 
 * @author Administrator
 * 
 */
public class AuditRemindVo {
	private int auditCount;
	private String auditStatus;
	private String creditapplicationIds;
	public int getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(int aduitCount) {
		this.auditCount = aduitCount;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String aduit) {
		this.auditStatus = aduit;
	}

	public String getCreditapplicationIds() {
		return creditapplicationIds;
	}

	public void setCreditapplicationIds(String creditapplicationIds) {
		this.creditapplicationIds = creditapplicationIds;
	}
	

}

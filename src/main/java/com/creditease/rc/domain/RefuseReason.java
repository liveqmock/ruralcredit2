package com.creditease.rc.domain;

public class RefuseReason {

	private Long refuseReasonId;
	private Long creditapplicationId;
	private String codeKey;
	private String codeSection;
	
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public String getCodeSection() {
		return codeSection;
	}
	public void setCodeSection(String codeSection) {
		this.codeSection = codeSection;
	}
	public Long getRefuseReasonId() {
		return refuseReasonId;
	}
	public void setRefuseReasonId(Long refuseReasonId) {
		this.refuseReasonId = refuseReasonId;
	}
	
	
}

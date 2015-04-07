package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 小组类
 * 
 * @author xubingzhao
 * 
 */
public class RlApplyAuditTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9067078739639975919L;
	private Integer applyaudittableId;
	private Integer creditapplicationId;
	private String examResult;
	private Double examAmount;
	private String examPaerson;
	private String examDate;
	private String examNote;
	private String loanTime;
	private String revokeStatus;
	private String borrRemark;
	private String auditType;
	private String historyFlag;

	public Integer getApplyaudittableId() {
		return applyaudittableId;
	}

	public void setApplyaudittableId(Integer applyaudittableId) {
		this.applyaudittableId = applyaudittableId;
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public Double getExamAmount() {
		return examAmount;
	}

	public void setExamAmount(Double examAmount) {
		this.examAmount = examAmount;
	}

	public String getExamPaerson() {
		return examPaerson;
	}

	public void setExamPaerson(String examPaerson) {
		this.examPaerson = examPaerson;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getExamNote() {
		return examNote;
	}

	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}

	public String getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}

	public String getRevokeStatus() {
		return revokeStatus;
	}

	public void setRevokeStatus(String revokeStatus) {
		this.revokeStatus = revokeStatus;
	}

	public String getBorrRemark() {
		return borrRemark;
	}

	public void setBorrRemark(String borrRemark) {
		this.borrRemark = borrRemark;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	public String getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}

}
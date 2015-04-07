package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 组员类
 * 
 * @author xubingzhao
 * 
 */
public class RlAuditDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2993102444133435933L;
	private Integer rlAuditDetailId;
	private Integer applyaudittableId;
	private Integer borrowerAppId;
	private String borrowerName;
	private String auditStatus;
	private Double amount;
	private String auditFlag;

	public Integer getRlAuditDetailId() {
		return rlAuditDetailId;
	}

	public void setRlAuditDetailId(Integer rlAuditDetailId) {
		this.rlAuditDetailId = rlAuditDetailId;
	}

	public Integer getApplyaudittableId() {
		return applyaudittableId;
	}

	public void setApplyaudittableId(Integer applyaudittableId) {
		this.applyaudittableId = applyaudittableId;
	}

	public Integer getBorrowerAppId() {
		return borrowerAppId;
	}

	public void setBorrowerAppId(Integer borrowerAppId) {
		this.borrowerAppId = borrowerAppId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RlAuditDetail [rlAuditDetailId=" + rlAuditDetailId
				+ ", applyaudittableId=" + applyaudittableId
				+ ", borrowerAppId=" + borrowerAppId + ", borrowerName="
				+ borrowerName + ", auditStatus=" + auditStatus + ", amount="
				+ amount + ", auditFlag=" + auditFlag + "]";
	}

}
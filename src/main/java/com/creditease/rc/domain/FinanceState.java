package com.creditease.rc.domain;

/**
 * 
 * @author xubingzhao
 * 
 */
public class FinanceState {
	private Integer financeStateId;
	private String fsStatus;
	private Double fsMoney;
	private String fsType;
	private Integer associationId;
	private String fsRemark;
	private String fsGroupflag;
	public Integer getFinanceStateId() {
		return financeStateId;
	}
	public void setFinanceStateId(Integer financeStateId) {
		this.financeStateId = financeStateId;
	}
	public String getFsStatus() {
		return fsStatus;
	}
	public void setFsStatus(String fsStatus) {
		this.fsStatus = fsStatus;
	}
	public Double getFsMoney() {
		return fsMoney;
	}
	public void setFsMoney(Double fsMoney) {
		this.fsMoney = fsMoney;
	}
	public String getFsType() {
		return fsType;
	}
	public void setFsType(String fsType) {
		this.fsType = fsType;
	}
	public Integer getAssociationId() {
		return associationId;
	}
	public void setAssociationId(Integer associationId) {
		this.associationId = associationId;
	}
	public String getFsRemark() {
		return fsRemark;
	}
	public void setFsRemark(String fsRemark) {
		this.fsRemark = fsRemark;
	}
	public String getFsGroupflag() {
		return fsGroupflag;
	}
	public void setFsGroupflag(String fsGroupflag) {
		this.fsGroupflag = fsGroupflag;
	}
	

}
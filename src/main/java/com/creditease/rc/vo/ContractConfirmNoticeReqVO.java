package com.creditease.rc.vo;

import java.util.Date;

public class ContractConfirmNoticeReqVO {

	private String signInfo;//系统标识（农贷07）
	private Long transportId;//进件编号（对应农贷业务编号）
	private Date confirmDate;//确认日期
	private Boolean isPass;//是否通过（true:通过；false:不通过）
	private String operatorName;//操作人姓名
	
	public String getSignInfo() {
		return signInfo;
	}
	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}
	
	public Long getTransportId() {
		return transportId;
	}
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public Boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
}

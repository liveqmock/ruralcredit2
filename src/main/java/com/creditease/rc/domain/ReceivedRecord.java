package com.creditease.rc.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author haoqiang
 * 
 */
public class ReceivedRecord implements Serializable {
	private Integer receivedRecordId;
	private Integer creditapplicationId;
	private Integer borrowerServiceAppId;
	private Date receivedTime;
	private Double receivedAmount;
	private String receivedType;
	private Integer operatorId;
	private String operatorName;
	private String receivedStatus;
	private String capitalSource;
	private Date operateDate;
	private String historyFlag;
	private Integer accountInfoId;
	private String remark;
	private Date revocationTime;
	private Integer revocationOperatorId;
	private String place;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRevocationTime() {
		return revocationTime;
	}

	public void setRevocationTime(Date revocationTime) {
		this.revocationTime = revocationTime;
	}

	public Integer getRevocationOperatorId() {
		return revocationOperatorId;
	}

	public void setRevocationOperatorId(Integer revocationOperatorId) {
		this.revocationOperatorId = revocationOperatorId;
	}

	public Integer getAccountInfoId() {
		return accountInfoId;
	}

	public void setAccountInfoId(Integer accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

	public Integer getReceivedRecordId() {
		return receivedRecordId;
	}

	public void setReceivedRecordId(Integer receivedRecordId) {
		this.receivedRecordId = receivedRecordId;
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public Double getReceivedAmount() {
		return receivedAmount;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public void setReceivedAmount(Double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getReceivedType() {
		return receivedType;
	}

	public void setReceivedType(String receivedType) {
		this.receivedType = receivedType;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getReceivedStatus() {
		return receivedStatus;
	}

	public void setReceivedStatus(String receivedStatus) {
		this.receivedStatus = receivedStatus;
	}

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

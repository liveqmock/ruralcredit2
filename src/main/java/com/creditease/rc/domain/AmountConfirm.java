package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.common.JsonYMDHmsDateSerializer;

/**
 * 
 * @author zhangman
 *
 */
public class AmountConfirm {
	private Long amountConfirmId;
	private Integer creditapplicationId; // 还款申请单ID
	private String historyFlag;			//历史标记 0-非历史，1-历史
	private String operator;			//操作人
	private Integer operatorId;			//操作人id
	private Date createTime;			//操作时间
	private Date loanTime;				//确认放款时间
	private Double amount;				//确认金额
	private Double serviceCharge;	//服务费
	private Double realAmount;		//实际放款金额
	
	private Date lastDownloadContractTime;     //最后一次下载合同时间
 	private Date beginInterestTime;				//起息日期
 	private String lendingChannel;//放款渠道
 	
	
	
 	public String getLendingChannel() {
		return lendingChannel;
	}

	public void setLendingChannel(String lendingChannel) {
		this.lendingChannel = lendingChannel;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
 	public Date getBeginInterestTime() {
		return beginInterestTime;
	}

	public void setBeginInterestTime(Date beginInterestTime) {
		this.beginInterestTime = beginInterestTime;
	}

	@JsonSerialize(using = JsonYMDHmsDateSerializer.class)
	public Date getLastDownloadContractTime() {
		return lastDownloadContractTime;
	}

	public void setLastDownloadContractTime(Date lastDownloadContractTime) {
		this.lastDownloadContractTime = lastDownloadContractTime;
	}
 	
	public Long getAmountConfirmId() {
		return amountConfirmId;
	}
	public void setAmountConfirmId(Long amountConfirmId) {
		this.amountConfirmId = amountConfirmId;
	}
	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getHistoryFlag() {
		return historyFlag;
	}
	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonSerialize(using = JsonYMDHmsDateSerializer.class)
	public Date getLoanTime() {
		return loanTime;
	}
	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}
	public Double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public Double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}
	
	
}

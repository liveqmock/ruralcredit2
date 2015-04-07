package com.creditease.rc.vo;

import java.io.Serializable;

import com.creditease.rc.domain.AmountConfirm;

/**
 * 放款金额确认 vo类
 * @author zhangman
 *
 */
public class AmountConfirmVo extends AmountConfirm implements Serializable {
	private String groupName; // 借款人姓名
	private String groupNumber; // 业务编号
	private Double groupAppTotal; // 小组申请总金额
	private Double loanAmount;	//审批金额
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public Double getGroupAppTotal() {
		return groupAppTotal;
	}
	public void setGroupAppTotal(Double groupAppTotal) {
		this.groupAppTotal = groupAppTotal;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
}

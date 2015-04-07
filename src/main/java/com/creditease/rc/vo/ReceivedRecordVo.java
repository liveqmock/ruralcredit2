package com.creditease.rc.vo;

import com.creditease.rc.domain.ReceivedRecord;

/**
 * 
 * @author haoqiang
 * 
 */
public class ReceivedRecordVo extends ReceivedRecord {

	private String contactNumber; // 合同编号
	private String businessNumber; // 业务单号
	private String borrowerName; // 借款人姓名
	private String address; // 地址
	private String defaultReturnmentWay; // 默认还款方式
	private String branchName; // 分公司名称
	private String accountName;// 账户名称
	private String account; // 账户
	private String bankName; // 银行名称
	private String accountType;// 卡类型
	private String authList;// 角色权限
	private String fuzzyValue;// 模糊查询字段

	public String getFuzzyValue() {
		return fuzzyValue;
	}

	public void setFuzzyValue(String fuzzyValue) {
		this.fuzzyValue = fuzzyValue;
	}

	public String getAuthList() {
		return authList;
	}

	public void setAuthList(String authList) {
		this.authList = authList;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDefaultReturnmentWay() {
		return defaultReturnmentWay;
	}

	public void setDefaultReturnmentWay(String defaultReturnmentWay) {
		this.defaultReturnmentWay = defaultReturnmentWay;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}

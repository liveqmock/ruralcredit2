package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;

public class ModifyCatdApp {

	private Long modifyCatdAppId;
	private Long accountInfoId;
	private String branchName;
	private String bankPrefectureNum;
	private String bankNum;
	private String bankRankName;
	private String bankName;
	private String accountName;
	private String account;
	private String onUsed;
	private String cardFlag;
	private String mobilephone;
	private String useType;
	private String payWay;
	private String payBranchno;
	private String credentialsNumber;
	private Long provinceId;
	private Long cityId;
	private Long districtId;
	private String accountType;
	private String companyId;
	private String borrowerName;
	private String borrowerCredentialsNumber;
	private String operaterName;
	private String operaterId;
	private String departmentId;
	private Long proposerId;
	private String proposerName;
	private Date applicationTime;
	private Long approvalId;
	private String approvalName;
	private Date examineTime;
	private String status;
	private String remark;
	private Long creditapplicationId;
	private String refusecause;
	

	public String getRefusecause() {
		return refusecause;
	}

	public void setRefusecause(String refusecause) {
		this.refusecause = refusecause;
	}

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getModifyCatdAppId() {
		return modifyCatdAppId;
	}

	public void setModifyCatdAppId(Long modifyCatdAppId) {
		this.modifyCatdAppId = modifyCatdAppId;
	}

	public Long getAccountInfoId() {
		return accountInfoId;
	}

	public void setAccountInfoId(Long accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankPrefectureNum() {
		return bankPrefectureNum;
	}

	public void setBankPrefectureNum(String bankPrefectureNum) {
		this.bankPrefectureNum = bankPrefectureNum;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getBankRankName() {
		return bankRankName;
	}

	public void setBankRankName(String bankRankName) {
		this.bankRankName = bankRankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getOnUsed() {
		return onUsed;
	}

	public void setOnUsed(String onUsed) {
		this.onUsed = onUsed;
	}

	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayBranchno() {
		return payBranchno;
	}

	public void setPayBranchno(String payBranchno) {
		this.payBranchno = payBranchno;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerCredentialsNumber() {
		return borrowerCredentialsNumber;
	}

	public void setBorrowerCredentialsNumber(String borrowerCredentialsNumber) {
		this.borrowerCredentialsNumber = borrowerCredentialsNumber;
	}

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Long getProposerId() {
		return proposerId;
	}

	public void setProposerId(Long proposerId) {
		this.proposerId = proposerId;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Long getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
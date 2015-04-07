package com.creditease.rc.vo;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.GroupLoanRegist;

/**
 * 放款确认时查询的基本信息
 * @author lhj
 *
 */
public class LoanConfirmMessageVo {

	private String creditapplicationId; //申请ID
	private String borrowName;			//借款人姓名
	private String famName;				//共借人姓名
	private String credentialsNumber;	//借款人身份证号
	private String idNumber;			//共借人身份证号
	private String businessNumber;		//业务单号
	private String repaymentPlanName;   //产品类型
	private String repaymentPlanId;		//产品类型Id
	private String loanOfficerId;		//客户经理Id
	private String loanOfficerName;		//客户经理姓名
	private String contactNumber;		//合同编号
	private String guarantorName;		//担保人姓名
	private String companyId;			//营业Id
	private String companyName;			//营业名称
	
	private AccountInfo accountInfo;	//公司财务账号表
	private AmountConfirm amountConfirm;//放款确认表
	private ContractRateForQYResult contractRateForQYResult;//查询借款用途
	private GroupLoanRegist groupLoanRegist;				//放款登记表
	private ContractRate contractRate;			//产品中心返回结果
	//private List<TradeDealForm> tradeDealFormList;//信托计划
	
	private String planName;//信托计划名称
	
	
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public ContractRate getContractRate() {
		return contractRate;
	}
	public void setContractRate(ContractRate contractRate) {
		this.contractRate = contractRate;
	}
	public GroupLoanRegist getGroupLoanRegist() {
		return groupLoanRegist;
	}
	public void setGroupLoanRegist(GroupLoanRegist groupLoanRegist) {
		this.groupLoanRegist = groupLoanRegist;
	}
	public ContractRateForQYResult getContractRateForQYResult() {
		return contractRateForQYResult;
	}
	public void setContractRateForQYResult(ContractRateForQYResult contractRateForQYResult) {
		this.contractRateForQYResult = contractRateForQYResult;
	}
	public AmountConfirm getAmountConfirm() {
		return amountConfirm;
	}
	public void setAmountConfirm(AmountConfirm amountConfirm) {
		this.amountConfirm = amountConfirm;
	}
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(String creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public String getFamName() {
		return famName;
	}
	public void setFamName(String famName) {
		this.famName = famName;
	}
	public String getCredentialsNumber() {
		return credentialsNumber;
	}
	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}
	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}
	public String getRepaymentPlanId() {
		return repaymentPlanId;
	}
	public void setRepaymentPlanId(String repaymentPlanId) {
		this.repaymentPlanId = repaymentPlanId;
	}
	public String getLoanOfficerId() {
		return loanOfficerId;
	}
	public void setLoanOfficerId(String loanOfficerId) {
		this.loanOfficerId = loanOfficerId;
	}
	public String getLoanOfficerName() {
		return loanOfficerName;
	}
	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getGuarantorName() {
		return guarantorName;
	}
	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}
	
	
}

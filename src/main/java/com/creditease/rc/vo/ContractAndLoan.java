package com.creditease.rc.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.common.JsonYMDHMDDateSerializer;

public class ContractAndLoan {

	private Long creditapplicationId; // 信贷申请主键
	private String creditapplicationDESId; // 还款申请单ID加密后
	private Long accountInfoId; //放款卡ID
	private String businessNumber; // 业务单号
	private String name; // 借款人姓名
	private String productTypeId; // 产品类型
	private String productName; // 产品名称
	private BigDecimal loanAmount; // 放款金额
	private BigDecimal serviceCharge; // 服务费
	private BigDecimal rLoanAmount; // 实发金额
	private Date loanDate; // 放款日期
	private Date submitTime; // 提交时间（上传合同时间）
	private Date loanConfirmDate; // 放款确认日期
	private String customerManager; // 客户经理
	private String status; // 申请状态
	private String auditStatusShow;//申请状态显示
	
	private String upContract;//判断是否是从首页的“上传合同提醒”点进来的
	private String lendingConfiguration;//放款渠道
	private String lendingConfigurationShow;//显示单条的放款渠道
	
	
	
	public String getLendingConfigurationShow() {
		return lendingConfigurationShow;
	}

	public void setLendingConfigurationShow(String lendingConfigurationShow) {
		this.lendingConfigurationShow = lendingConfigurationShow;
	}

	public String getLendingConfiguration() {
		return lendingConfiguration;
	}

	public void setLendingConfiguration(String lendingConfiguration) {
		this.lendingConfiguration = lendingConfiguration;
	}

	public String getUpContract() {
		return upContract;
	}

	public void setUpContract(String upContract) {
		this.upContract = upContract;
	}
	
	
	public String getAuditStatusShow() {
		return auditStatusShow;
	}

	public void setAuditStatusShow(String auditStatusShow) {
		this.auditStatusShow = auditStatusShow;
	}

	//加密标识
	private String laonDESId; // 放款登记协议
	
	
	public String getLaonDESId() {
		return laonDESId;
	}

	public void setLaonDESId(String laonDESId) {
		this.laonDESId = laonDESId;
	}

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	
	
	public String getCreditapplicationDESId() {
		return creditapplicationDESId;
	}

	public void setCreditapplicationDESId(String creditapplicationDESId) {
		this.creditapplicationDESId = creditapplicationDESId;
	}

	public Long getAccountInfoId() {
		return accountInfoId;
	}

	public void setAccountInfoId(Long accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public BigDecimal getrLoanAmount() {
		return rLoanAmount;
	}

	public void setrLoanAmount(BigDecimal rLoanAmount) {
		this.rLoanAmount = rLoanAmount;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getLoanConfirmDate() {
		return loanConfirmDate;
	}

	public void setLoanConfirmDate(Date loanConfirmDate) {
		this.loanConfirmDate = loanConfirmDate;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

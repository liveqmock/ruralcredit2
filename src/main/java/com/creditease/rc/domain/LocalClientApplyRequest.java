package com.creditease.rc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author haoqiang
 * 
 */
public class LocalClientApplyRequest implements Serializable {

	/** 绝对必填项 **/
	private String applyId; // 申请编号
	private String returnType; // 还款方式
	/* 放款卡相关 */
	private String loanType; // 放款方式
	private String loanBankAccount; // 放款银行账号
	private String loanBankClientName; // 放款人银行户名
	private String loanBankProvince; // 放款银行所在省
	private String loanBankCity; // 放款银行所在市
	private String loanBankName; // 放款开户行名称
	private BigDecimal applyAmount; // 合同金额
	private Long productId; // 产品编号
	private String amortisation; // 分期数
	private String borrowPurpose; // 借款用途
	private String sellId; // 信贷员编号
	private String sellName; // 信贷员名字
	private String officeId; // 分公司编号
	private String officeName;// 营业部名称
	private String clientName; // 客户姓名
	private String idNumber; // 身份证号
	private String mobilePhone; // 手机号码

	/** (｡-ω-｡)--------------------ｷﾘﾄﾘ線--------------------(｡-ω-｡) **/

	/** 新加的必填项 **/
	private String signInfo;// 摘要信息
	private BigDecimal loanAmount;// 放款金额
	private Date loanTime;// 放款时间
	private String productTypeId;// 产品分类编号

	/** (｡-ω-｡)--------------------ｷﾘﾄﾘ線--------------------(｡-ω-｡) **/

	/** 卡信息选填项 **/
	/* 收款卡相关 */
	private String bankBook; // 卡折标志
	private String bankAccount; // 还款银行账号
	private String bankClientName; // 还款人银行户名
	private String bankName; // 还款开户行名称
	private Date contractTime;//合同签订时间
	private String bankNumber;//银行号
	
	

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	/** (｡-ω-｡)--------------------ｷﾘﾄﾘ線--------------------(｡-ω-｡) **/
	/**
	 * @return String
	 */
	public String getApplyId() {

		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getBankBook() {
		return bankBook;
	}

	public void setBankBook(String bankBook) {
		this.bankBook = bankBook;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getAmortisation() {
		return amortisation;
	}

	public void setAmortisation(String amortisation) {
		this.amortisation = amortisation;
	}

	public String getBorrowPurpose() {
		return borrowPurpose;
	}

	public void setBorrowPurpose(String borrowPurpose) {
		this.borrowPurpose = borrowPurpose;
	}

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankClientName() {
		return bankClientName;
	}

	public void setBankClientName(String bankClientName) {
		this.bankClientName = bankClientName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanBankAccount() {
		return loanBankAccount;
	}

	public void setLoanBankAccount(String loanBankAccount) {
		this.loanBankAccount = loanBankAccount;
	}

	public String getLoanBankClientName() {
		return loanBankClientName;
	}

	public void setLoanBankClientName(String loanBankClientName) {
		this.loanBankClientName = loanBankClientName;
	}

	public String getLoanBankProvince() {
		return loanBankProvince;
	}

	public void setLoanBankProvince(String loanBankProvince) {
		this.loanBankProvince = loanBankProvince;
	}

	public String getLoanBankCity() {
		return loanBankCity;
	}

	public void setLoanBankCity(String loanBankCity) {
		this.loanBankCity = loanBankCity;
	}

	public String getLoanBankName() {
		return loanBankName;
	}

	public void setLoanBankName(String loanBankName) {
		this.loanBankName = loanBankName;

	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

}

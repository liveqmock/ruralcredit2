package com.creditease.rc.vo;

import java.math.BigDecimal;

public class ContractRate {

	private Long creditapplicationId ;   //信贷申请主键
	private String 			contractNo				;              //合同编号
	private String 			clientName				;              //客户姓名
	private BigDecimal 		contractMoney			;     //借款总额
	private String 			borrowUse				;        //借款用途
	private String 			borrowUseText				;        //借款用途
	private String 			productName				;             //产品名称
	private int 			periodCount				;                //分期数
	private BigDecimal 		toAmount				;            //到手金额
	private BigDecimal 		serviceCharge				;       //服务费
	private BigDecimal 		managementFee				;       //管理费
	private BigDecimal 		iRR								;                 //年利率
	private BigDecimal		monthlyPayments				;     //每月还款额
	private BigDecimal		prepayments				;         //提前一次性还款金额

	
	
	
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getBorrowUseText() {
		return borrowUseText;
	}

	public void setBorrowUseText(String borrowUseText) {
		this.borrowUseText = borrowUseText;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public BigDecimal getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(BigDecimal contractMoney) {
		this.contractMoney = contractMoney;
	}

	

	public String getBorrowUse() {
		return borrowUse;
	}

	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(int periodCount) {
		this.periodCount = periodCount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public BigDecimal getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}

	public BigDecimal getiRR() {
		return iRR;
	}

	public void setiRR(BigDecimal iRR) {
		this.iRR = iRR;
	}

	public BigDecimal getMonthlyPayments() {
		return monthlyPayments;
	}

	public void setMonthlyPayments(BigDecimal monthlyPayments) {
		this.monthlyPayments = monthlyPayments;
	}

	public BigDecimal getPrepayments() {
		return prepayments;
	}

	public void setPrepayments(BigDecimal prepayments) {
		this.prepayments = prepayments;
	}

}

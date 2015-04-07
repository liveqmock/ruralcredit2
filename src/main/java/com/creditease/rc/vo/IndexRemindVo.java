package com.creditease.rc.vo;

import java.util.List;

/**
 * 首页提醒
 * 
 * @author Administrator
 * 
 */
public class IndexRemindVo {
	private AuditRemindVo auditRemindFX;
	private AuditRemindVo auditRemindBS;
	private AuditRemindVo amountConfirm;
	private AuditRemindVo financePaymentRemind;
	private AuditRemindVo financeReceiveRemindFail = new AuditRemindVo();
	private AuditRemindVo financeReceiveRemindWill = new AuditRemindVo();
	private AuditRemindVo loanRegister;
	private AuditRemindVo receiptRegister1 = new AuditRemindVo();
	private AuditRemindVo receiptRegister3 = new AuditRemindVo();
	private AuditRemindVo receiptRegister7 = new AuditRemindVo();
	private InvestigationVo investigation;
	private AuditRemindVo loanConfirmBack;
	private CountInfo receiptCountInfo;
	private CountInfo loanSumAmount;
	private CountInfo currentLoanSumAmount;
	private CountInfo allCountInfo;
	private List<DepartmentCountInfo> departmentCountInfoList;
	private CountInfo consultToApply;
	


	public CountInfo getConsultToApply() {
		return consultToApply;
	}

	public void setConsultToApply(CountInfo consultToApply) {
		this.consultToApply = consultToApply;
	}

	public AuditRemindVo getAuditRemindFX() {
		return auditRemindFX;
	}

	public void setAuditRemindFX(AuditRemindVo auditRemindFX) {
		this.auditRemindFX = auditRemindFX;
	}

	public AuditRemindVo getAuditRemindBS() {
		return auditRemindBS;
	}

	public void setAuditRemindBS(AuditRemindVo auditRemindBS) {
		this.auditRemindBS = auditRemindBS;
	}

	public AuditRemindVo getAmountConfirm() {
		return amountConfirm;
	}

	public void setAmountConfirm(AuditRemindVo amountConfirm) {
		this.amountConfirm = amountConfirm;
	}

	public AuditRemindVo getFinancePaymentRemind() {
		return financePaymentRemind;
	}

	public void setFinancePaymentRemind(AuditRemindVo financePaymentRemind) {
		this.financePaymentRemind = financePaymentRemind;
	}

	public AuditRemindVo getFinanceReceiveRemindFail() {
		return financeReceiveRemindFail;
	}

	public void setFinanceReceiveRemindFail(AuditRemindVo financeReceiveRemindFail) {
		this.financeReceiveRemindFail = financeReceiveRemindFail;
	}

	public AuditRemindVo getFinanceReceiveRemindWill() {
		return financeReceiveRemindWill;
	}

	public void setFinanceReceiveRemindWill(AuditRemindVo financeReceiveRemindWill) {
		this.financeReceiveRemindWill = financeReceiveRemindWill;
	}

	public AuditRemindVo getLoanRegister() {
		return loanRegister;
	}

	public void setLoanRegister(AuditRemindVo loanRegister) {
		this.loanRegister = loanRegister;
	}

	public AuditRemindVo getReceiptRegister1() {
		return receiptRegister1;
	}

	public void setReceiptRegister1(AuditRemindVo receiptRegister1) {
		this.receiptRegister1 = receiptRegister1;
	}

	public AuditRemindVo getReceiptRegister3() {
		return receiptRegister3;
	}

	public void setReceiptRegister3(AuditRemindVo receiptRegister3) {
		this.receiptRegister3 = receiptRegister3;
	}

	public AuditRemindVo getReceiptRegister7() {
		return receiptRegister7;
	}

	public void setReceiptRegister7(AuditRemindVo receiptRegister7) {
		this.receiptRegister7 = receiptRegister7;
	}

	public InvestigationVo getInvestigation() {
		return investigation;
	}

	public void setInvestigation(InvestigationVo investigation) {
		this.investigation = investigation;
	}

	public AuditRemindVo getLoanConfirmBack() {
		return loanConfirmBack;
	}

	public void setLoanConfirmBack(AuditRemindVo loanConfirmBack) {
		this.loanConfirmBack = loanConfirmBack;
	}

	public CountInfo getReceiptCountInfo() {
		return receiptCountInfo;
	}

	public void setReceiptCountInfo(CountInfo receiptCountInfo) {
		this.receiptCountInfo = receiptCountInfo;
	}

	public CountInfo getLoanSumAmount() {
		return loanSumAmount;
	}

	public void setLoanSumAmount(CountInfo loanSumAmount) {
		this.loanSumAmount = loanSumAmount;
	}

	public CountInfo getCurrentLoanSumAmount() {
		return currentLoanSumAmount;
	}

	public void setCurrentLoanSumAmount(CountInfo currentLoanSumAmount) {
		this.currentLoanSumAmount = currentLoanSumAmount;
	}

	public CountInfo getAllCountInfo() {
		return allCountInfo;
	}

	public void setAllCountInfo(CountInfo allCountInfo) {
		this.allCountInfo = allCountInfo;
	}

	public List<DepartmentCountInfo> getDepartmentCountInfoList() {
		return departmentCountInfoList;
	}

	public void setDepartmentCountInfoList(List<DepartmentCountInfo> departmentCountInfoList) {
		this.departmentCountInfoList = departmentCountInfoList;
	}

}

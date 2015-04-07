package com.creditease.rc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author haoqiang
 * 
 */
public class LocalReturnAmountResult extends LocalWebServiceResponse implements Serializable {

	private BigDecimal totalReceivableMoney; // 应收总金额
	private BigDecimal totalReceivablePrincipal; // 应收本金
	private BigDecimal totalReceivableInterest; // 应收利息

	private BigDecimal totalFQFWFReceivableCharge; // 应收分期服务费
	private BigDecimal totalFXReceivableCharge; // 应收罚息
	private BigDecimal totalZNJReceivableCharge; // 应收滞纳金
                                                     
	private BigDecimal totalPrincipal;               //逾期本金合计
	private BigDecimal totalInterest;                //逾期利息合计
	private BigDecimal totalOverdueFines;            //逾期分期费用
	private BigDecimal totalPeriodCharge;            //逾期滞纳金合计
	private BigDecimal totalOverdueInterest;         //逾期罚息合计
	private BigDecimal currentPrincipal;             //当期本金
	private BigDecimal currentInterest;              //当期利息
	private BigDecimal currentPeriodCharge;          //当期分期费用
	private BigDecimal surplusPrincipal;             //剩余本金
	private BigDecimal behindInterests;              //未来利息
	private BigDecimal charge;                       //服务费
	private BigDecimal penalbond;                    //提前还款违约金
	private BigDecimal total;                        //还款合计

	public BigDecimal getTotalReceivableMoney() {
		return totalReceivableMoney;
	}

	public void setTotalReceivableMoney(BigDecimal totalReceivableMoney) {
		this.totalReceivableMoney = totalReceivableMoney;
	}

	public BigDecimal getTotalReceivablePrincipal() {
		return totalReceivablePrincipal;
	}

	public void setTotalReceivablePrincipal(BigDecimal totalReceivablePrincipal) {
		this.totalReceivablePrincipal = totalReceivablePrincipal;
	}

	public BigDecimal getTotalReceivableInterest() {
		return totalReceivableInterest;
	}

	public void setTotalReceivableInterest(BigDecimal totalReceivableInterest) {
		this.totalReceivableInterest = totalReceivableInterest;
	}

	public BigDecimal getTotalFQFWFReceivableCharge() {
		return totalFQFWFReceivableCharge;
	}

	public void setTotalFQFWFReceivableCharge(BigDecimal totalFQFWFReceivableCharge) {
		this.totalFQFWFReceivableCharge = totalFQFWFReceivableCharge;
	}

	public BigDecimal getTotalFXReceivableCharge() {
		return totalFXReceivableCharge;
	}

	public void setTotalFXReceivableCharge(BigDecimal totalFXReceivableCharge) {
		this.totalFXReceivableCharge = totalFXReceivableCharge;
	}

	public BigDecimal getTotalZNJReceivableCharge() {
		return totalZNJReceivableCharge;
	}

	public void setTotalZNJReceivableCharge(BigDecimal totalZNJReceivableCharge) {
		this.totalZNJReceivableCharge = totalZNJReceivableCharge;
	}

	public BigDecimal getTotalPrincipal() {
		return totalPrincipal;
	}

	public void setTotalPrincipal(BigDecimal totalPrincipal) {
		this.totalPrincipal = totalPrincipal;
	}

	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}

	public BigDecimal getTotalOverdueFines() {
		return totalOverdueFines;
	}

	public void setTotalOverdueFines(BigDecimal totalOverdueFines) {
		this.totalOverdueFines = totalOverdueFines;
	}

	public BigDecimal getTotalPeriodCharge() {
		return totalPeriodCharge;
	}

	public void setTotalPeriodCharge(BigDecimal totalPeriodCharge) {
		this.totalPeriodCharge = totalPeriodCharge;
	}

	public BigDecimal getTotalOverdueInterest() {
		return totalOverdueInterest;
	}

	public void setTotalOverdueInterest(BigDecimal totalOverdueInterest) {
		this.totalOverdueInterest = totalOverdueInterest;
	}

	public BigDecimal getCurrentPrincipal() {
		return currentPrincipal;
	}

	public void setCurrentPrincipal(BigDecimal currentPrincipal) {
		this.currentPrincipal = currentPrincipal;
	}

	public BigDecimal getCurrentInterest() {
		return currentInterest;
	}

	public void setCurrentInterest(BigDecimal currentInterest) {
		this.currentInterest = currentInterest;
	}

	public BigDecimal getCurrentPeriodCharge() {
		return currentPeriodCharge;
	}

	public void setCurrentPeriodCharge(BigDecimal currentPeriodCharge) {
		this.currentPeriodCharge = currentPeriodCharge;
	}

	public BigDecimal getSurplusPrincipal() {
		return surplusPrincipal;
	}

	public void setSurplusPrincipal(BigDecimal surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}

	public BigDecimal getBehindInterests() {
		return behindInterests;
	}

	public void setBehindInterests(BigDecimal behindInterests) {
		this.behindInterests = behindInterests;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getPenalbond() {
		return penalbond;
	}

	public void setPenalbond(BigDecimal penalbond) {
		this.penalbond = penalbond;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
	
}

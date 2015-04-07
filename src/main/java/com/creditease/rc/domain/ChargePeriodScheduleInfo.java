package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Title:ChargePeriodScheduleInfo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-2-27上午11:12:51
 * @author 王毅峰
 * @version v2.0
 */
public class ChargePeriodScheduleInfo {
	private BigDecimal recvMoney;
	private String eChargeType;
	private String eChargeName;
	private String eChargeFormula;
	private BigDecimal eCharge;

	private Long phase;
	private Long period;
	private Date repayDate;
	private BigDecimal irr;
	private BigDecimal amortizedPrincipal;
	private BigDecimal amortizedInterest;
	private BigDecimal periodCharge;
	private BigDecimal periodMoney;

	private String sChargeType;
	private String sChargeName;
	private String sChargeFormula;
	private BigDecimal sCharge;
	
	public BigDecimal getRecvMoney() {
		return recvMoney;
	}
	public void setRecvMoney(BigDecimal recvMoney) {
		this.recvMoney = recvMoney;
	}
	public String geteChargeType() {
		return eChargeType;
	}
	public void seteChargeType(String eChargeType) {
		this.eChargeType = eChargeType;
	}
	public String geteChargeName() {
		return eChargeName;
	}
	public void seteChargeName(String eChargeName) {
		this.eChargeName = eChargeName;
	}
	public String geteChargeFormula() {
		return eChargeFormula;
	}
	public void seteChargeFormula(String eChargeFormula) {
		this.eChargeFormula = eChargeFormula;
	}
	public BigDecimal geteCharge() {
		return eCharge;
	}
	public void seteCharge(BigDecimal eCharge) {
		this.eCharge = eCharge;
	}
	public Long getPhase() {
		return phase;
	}
	public void setPhase(Long phase) {
		this.phase = phase;
	}
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public BigDecimal getIrr() {
		return irr;
	}
	public void setIrr(BigDecimal irr) {
		this.irr = irr;
	}
	public BigDecimal getAmortizedPrincipal() {
		return amortizedPrincipal;
	}
	public void setAmortizedPrincipal(BigDecimal amortizedPrincipal) {
		this.amortizedPrincipal = amortizedPrincipal;
	}
	public BigDecimal getAmortizedInterest() {
		return amortizedInterest;
	}
	public void setAmortizedInterest(BigDecimal amortizedInterest) {
		this.amortizedInterest = amortizedInterest;
	}
	public BigDecimal getPeriodCharge() {
		return periodCharge;
	}
	public void setPeriodCharge(BigDecimal periodCharge) {
		this.periodCharge = periodCharge;
	}
	public BigDecimal getPeriodMoney() {
		return periodMoney;
	}
	public void setPeriodMoney(BigDecimal periodMoney) {
		this.periodMoney = periodMoney;
	}
	public String getsChargeType() {
		return sChargeType;
	}
	public void setsChargeType(String sChargeType) {
		this.sChargeType = sChargeType;
	}
	public String getsChargeName() {
		return sChargeName;
	}
	public void setsChargeName(String sChargeName) {
		this.sChargeName = sChargeName;
	}
	public String getsChargeFormula() {
		return sChargeFormula;
	}
	public void setsChargeFormula(String sChargeFormula) {
		this.sChargeFormula = sChargeFormula;
	}
	public BigDecimal getsCharge() {
		return sCharge;
	}
	public void setsCharge(BigDecimal sCharge) {
		this.sCharge = sCharge;
	}

}

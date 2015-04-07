package com.creditease.rc.domain;

import java.math.BigDecimal;
/**
 * 预约还款分期还款计划
 * Title:ApsListInfo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-3-19下午03:23:05
 * @author 王毅峰
 * @version v2.0
 */
public class ApsListInfo {
	private int period;
	private BigDecimal receivablePrincipal;
	private BigDecimal receivableInterest;
	private BigDecimal receivableMoney;
	
	private BigDecimal periodReceivableChargeFQFWF;
	private BigDecimal overdueReceivableChargeFX;
	private BigDecimal overdueReceivableChargeZNJ;
	
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public BigDecimal getReceivablePrincipal() {
		return receivablePrincipal;
	}
	public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
		this.receivablePrincipal = receivablePrincipal;
	}
	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}
	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}
	public BigDecimal getReceivableMoney() {
		return receivableMoney;
	}
	public void setReceivableMoney(BigDecimal receivableMoney) {
		this.receivableMoney = receivableMoney;
	}
	public BigDecimal getPeriodReceivableChargeFQFWF() {
		return periodReceivableChargeFQFWF;
	}
	public void setPeriodReceivableChargeFQFWF(
			BigDecimal periodReceivableChargeFQFWF) {
		this.periodReceivableChargeFQFWF = periodReceivableChargeFQFWF;
	}
	public BigDecimal getOverdueReceivableChargeFX() {
		return overdueReceivableChargeFX;
	}
	public void setOverdueReceivableChargeFX(BigDecimal overdueReceivableChargeFX) {
		this.overdueReceivableChargeFX = overdueReceivableChargeFX;
	}
	public BigDecimal getOverdueReceivableChargeZNJ() {
		return overdueReceivableChargeZNJ;
	}
	public void setOverdueReceivableChargeZNJ(BigDecimal overdueReceivableChargeZNJ) {
		this.overdueReceivableChargeZNJ = overdueReceivableChargeZNJ;
	}
	
	
}

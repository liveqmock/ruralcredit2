package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.List;
/**
 * 预约还款计划
 * Title:AppointScheduleInfo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-3-19下午03:22:04
 * @author 王毅峰
 * @version v2.0
 */
public class AppointScheduleInfo {
	
	private BigDecimal totalReceivablePrincipal;
	private BigDecimal totalReceivableInterest;
	private BigDecimal totalPeriodReceivableChargeFQFWF;
	private BigDecimal totalOverdueReceivableChargeFX;
	private BigDecimal totalOverdueReceivableChargeZNJ;
	private BigDecimal totalReceivableMoney;
	private List<ApsListInfo> ApsListInfoList;
	
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
	public BigDecimal getTotalPeriodReceivableChargeFQFWF() {
		return totalPeriodReceivableChargeFQFWF;
	}
	public void setTotalPeriodReceivableChargeFQFWF(
			BigDecimal totalPeriodReceivableChargeFQFWF) {
		this.totalPeriodReceivableChargeFQFWF = totalPeriodReceivableChargeFQFWF;
	}
	public BigDecimal getTotalOverdueReceivableChargeFX() {
		return totalOverdueReceivableChargeFX;
	}
	public void setTotalOverdueReceivableChargeFX(
			BigDecimal totalOverdueReceivableChargeFX) {
		this.totalOverdueReceivableChargeFX = totalOverdueReceivableChargeFX;
	}
	public BigDecimal getTotalOverdueReceivableChargeZNJ() {
		return totalOverdueReceivableChargeZNJ;
	}
	public void setTotalOverdueReceivableChargeZNJ(
			BigDecimal totalOverdueReceivableChargeZNJ) {
		this.totalOverdueReceivableChargeZNJ = totalOverdueReceivableChargeZNJ;
	}
	public BigDecimal getTotalReceivableMoney() {
		return totalReceivableMoney;
	}
	public void setTotalReceivableMoney(BigDecimal totalReceivableMoney) {
		this.totalReceivableMoney = totalReceivableMoney;
	}
	public List<ApsListInfo> getApsListInfoList() {
		return ApsListInfoList;
	}
	public void setApsListInfoList(List<ApsListInfo> apsListInfoList) {
		ApsListInfoList = apsListInfoList;
	}
	
}

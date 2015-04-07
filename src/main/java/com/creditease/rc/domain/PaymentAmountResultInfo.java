package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.List;

import com.creditease.rc.app.pdf.AllAheadSchedule;

public class PaymentAmountResultInfo {
	
	private String resultCode;
	private String resultMsg;
	private AllAheadSchedule aheadSchedule; 
	
	private BigDecimal totalReceivablePrincipal;
	private BigDecimal totalReceivableInterest;
	private BigDecimal totalPeriodReceivableChargeFQFWF;
	private BigDecimal totalOverdueReceivableChargeFX;
	private BigDecimal totalOverdueReceivableChargeZNJ;
	private BigDecimal totalReceivableMoney;
	private List<ApsListInfo> ApsListInfoList;
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public AllAheadSchedule getAheadSchedule() {
		return aheadSchedule;
	}
	public void setAheadSchedule(AllAheadSchedule aheadSchedule) {
		this.aheadSchedule = aheadSchedule;
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

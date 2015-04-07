package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 各分期实际还款记录
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-3-22下午06:49:02
 * @author 王毅峰
 * @version v2.0
 */
public class ActualPeriodScheduleDTO {
	private boolean overdue;//是否逾期
	private BigDecimal overdueFxReceivableCharge;//逾期应收罚息
	private BigDecimal overdueFxReceivedCharge;//逾期实收罚息
	private BigDecimal overdueZnjReceivableCharge;//逾期应收滞纳
	private BigDecimal overdueZnjReceivedCharge;//逾期实收滞纳
	private BigDecimal overdueFQFWFReceivableCharge;//逾期应收分期服务费
	private BigDecimal overdueFQFWFReceivedCharge;//逾期实收分期服务费
	private int period;//期数
	private BigDecimal periodFQFWFReceivableCharge;//应收分期服务费
	private BigDecimal periodFQFWFReceivedCharge;//实收分期服务费 
	private String repayDate;//实收日期
	private BigDecimal receivableInterest;//应收利息
	private BigDecimal receivableMoney;//应收金额
	private BigDecimal receivablePrincipal;//应收本金
	private BigDecimal receivedInterest;//实收利息
	private BigDecimal receivedMoney;//实收金额
	private BigDecimal receivedPrincipal;//实收本金
	
	public boolean isOverdue() {
		return overdue;
	}
	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}
	public BigDecimal getOverdueFxReceivableCharge() {
		return overdueFxReceivableCharge;
	}
	public void setOverdueFxReceivableCharge(BigDecimal overdueFxReceivableCharge) {
		this.overdueFxReceivableCharge = overdueFxReceivableCharge;
	}
	public BigDecimal getOverdueFxReceivedCharge() {
		return overdueFxReceivedCharge;
	}
	public void setOverdueFxReceivedCharge(BigDecimal overdueFxReceivedCharge) {
		this.overdueFxReceivedCharge = overdueFxReceivedCharge;
	}
	public BigDecimal getOverdueZnjReceivableCharge() {
		return overdueZnjReceivableCharge;
	}
	public void setOverdueZnjReceivableCharge(BigDecimal overdueZnjReceivableCharge) {
		this.overdueZnjReceivableCharge = overdueZnjReceivableCharge;
	}
	public BigDecimal getOverdueZnjReceivedCharge() {
		return overdueZnjReceivedCharge;
	}
	public void setOverdueZnjReceivedCharge(BigDecimal overdueZnjReceivedCharge) {
		this.overdueZnjReceivedCharge = overdueZnjReceivedCharge;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public BigDecimal getPeriodFQFWFReceivableCharge() {
		return periodFQFWFReceivableCharge;
	}
	public void setPeriodFQFWFReceivableCharge(
			BigDecimal periodFQFWFReceivableCharge) {
		this.periodFQFWFReceivableCharge = periodFQFWFReceivableCharge;
	}
	public BigDecimal getPeriodFQFWFReceivedCharge() {
		return periodFQFWFReceivedCharge;
	}
	public void setPeriodFQFWFReceivedCharge(BigDecimal periodFQFWFReceivedCharge) {
		this.periodFQFWFReceivedCharge = periodFQFWFReceivedCharge;
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
	public BigDecimal getReceivablePrincipal() {
		return receivablePrincipal;
	}
	public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
		this.receivablePrincipal = receivablePrincipal;
	}
	public BigDecimal getReceivedInterest() {
		return receivedInterest;
	}
	public void setReceivedInterest(BigDecimal receivedInterest) {
		this.receivedInterest = receivedInterest;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public BigDecimal getReceivedMoney() {
		return receivedMoney;
	}
	public void setReceivedMoney(BigDecimal receivedMoney) {
		this.receivedMoney = receivedMoney;
	}
	public BigDecimal getReceivedPrincipal() {
		return receivedPrincipal;
	}
	public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
		this.receivedPrincipal = receivedPrincipal;
	}
	public BigDecimal getOverdueFQFWFReceivableCharge() {
		return overdueFQFWFReceivableCharge;
	}
	public void setOverdueFQFWFReceivableCharge(
			BigDecimal overdueFQFWFReceivableCharge) {
		this.overdueFQFWFReceivableCharge = overdueFQFWFReceivableCharge;
	}
	public BigDecimal getOverdueFQFWFReceivedCharge() {
		return overdueFQFWFReceivedCharge;
	}
	public void setOverdueFQFWFReceivedCharge(BigDecimal overdueFQFWFReceivedCharge) {
		this.overdueFQFWFReceivedCharge = overdueFQFWFReceivedCharge;
	}
}

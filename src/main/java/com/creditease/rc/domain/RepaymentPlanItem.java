package com.creditease.rc.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * 
 * @author haoqiang
 * 
 */
public class RepaymentPlanItem implements Serializable {
	private Integer repaymentPlanItemId; // 还款方案明细ID
	private Integer repaymentPlanId; // 还款方案编码
	private Integer months; // 月数
	private String principalRepayment; // 本金还款方式
	private Double principal; // 本金
	private String intertestRepayment; // 利息还款方式
	private Double interest; // 利息
	private String chargeServiceMethod; // 服务费收取方式
	private Double serviceFree; // 服务费

	// private Double principalVo;
	// private Double interestVo;
	// private Double serviceFreeVo;
	// private DecimalFormat decimalFormat4 = new DecimalFormat("#.0000");
	// private DecimalFormat decimalFormat2 = new DecimalFormat("#.00");

	public Integer getRepaymentPlanItemId() {
		return repaymentPlanItemId;
	}

	public void setRepaymentPlanItemId(Integer repaymentPlanItemId) {
		this.repaymentPlanItemId = repaymentPlanItemId;
	}

	public Integer getRepaymentPlanId() {
		return repaymentPlanId;
	}

	public void setRepaymentPlanId(Integer repaymentPlanId) {
		this.repaymentPlanId = repaymentPlanId;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public String getPrincipalRepayment() {
		return principalRepayment;
	}

	public void setPrincipalRepayment(String principalRepayment) {
		this.principalRepayment = principalRepayment;
	}

	/**
	 * 
	 * @return principal
	 */
	public Double getPrincipal() {
		/*
		 * if (principal != null) { return
		 * Double.parseDouble(decimalFormat2.format(principal * 100)); } else {
		 * return principal; }
		 */
		return principal;
	}

	/**
	 * 
	 * @param principal
	 *            本金
	 */
	public void setPrincipal(Double principal) {
		// setPrincipalVo(principal);
		this.principal = principal;
	}

	/*
	 * public Double getPrincipalVo() { return principalVo; }
	 */

	/**
	 * 
	 * @param principal
	 *            本金
	 */
	/*
	 * public void setPrincipalVo(Double principal) { if (principal != null) {
	 * this.principalVo = Double.parseDouble(decimalFormat4.format(principal /
	 * 100)); } else { this.principalVo = principal; } }
	 */

	public String getIntertestRepayment() {
		return intertestRepayment;
	}

	public void setIntertestRepayment(String intertestRepayment) {
		this.intertestRepayment = intertestRepayment;
	}

	/**
	 * 
	 * @return interest
	 */
	public Double getInterest() {
		/*
		 * if (interest != null) { return
		 * Double.parseDouble(decimalFormat2.format(interest * 100)); } else {
		 * return interest; }
		 */
		return interest;
	}

	/**
	 * 
	 * @param interest
	 *            利息
	 */
	public void setInterest(Double interest) {
		// setInterestVo(interest);
		this.interest = interest;
	}

	/*
	 * public Double getInterestVo() { return interestVo; }
	 */

	/**
	 * 
	 * @param interest
	 *            利息
	 */
	/*
	 * public void setInterestVo(Double interest) { if (interest != null) {
	 * System.out.println("传入的interest为====" + interest); this.interestVo =
	 * Double.parseDouble(decimalFormat4.format(interest / 100)); Double aaa =
	 * Double.parseDouble(decimalFormat4.format(interest / 100));
	 * System.out.println("计算后的利息为===" + aaa); } else { this.interestVo =
	 * interest; } }
	 */

	public String getChargeServiceMethod() {
		return chargeServiceMethod;
	}

	public void setChargeServiceMethod(String chargeServiceMethod) {
		this.chargeServiceMethod = chargeServiceMethod;
	}

	/**
	 * 
	 * @return serviceFree
	 */
	public Double getServiceFree() {
		/*
		 * if (serviceFree != null) { return
		 * Double.parseDouble(decimalFormat2.format(serviceFree * 100)); } else
		 * { return serviceFree; }
		 */
		return serviceFree;
	}

	/**
	 * 
	 * @param serviceFree
	 *            服务费
	 */
	public void setServiceFree(Double serviceFree) {
		// setServiceFreeVo(serviceFree);
		this.serviceFree = serviceFree;
	}

	/*
	 * public Double getServiceFreeVo() { return serviceFreeVo; }
	 */

	/**
	 * 
	 * @param serviceFree
	 *            服务费
	 */
	/*
	 * public void setServiceFreeVo(Double serviceFree) { if (serviceFree !=
	 * null) { this.serviceFreeVo =
	 * Double.parseDouble(decimalFormat4.format(serviceFree / 100)); } else {
	 * this.serviceFreeVo = serviceFree; } }
	 */

}

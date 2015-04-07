package com.creditease.rc.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * 
 * @author haoqiang
 * 
 */
public class RepaymentPlan implements Serializable {
	private Integer repaymentPlanId; // 还款方案编码
	private String repaymentPlanName; // 还款方案名称
	private Double nominalInterestRate; // 名义年利率
	private Double firstServiceFree; // 首期服务费
	private Double followupServiceFree; // 后续服务费
	private String repaymentWay; // 还款方式
	private Integer loanPeriod; // 借款期限,以月为单位
	private Integer repaymentInstallments; // 还款期数，数字
	private Integer eachInterval; // 每期间隔，以月为单位，可设置1/3/6等
	private Integer repaymentStartMonth; // 还款开始月份，表示第一次还款时间是借款日期后的第n个月
	private String useFlag; // 使用状态0:使用;1:停用
	private Double heightLoanAmount; // 最高借款金额
	private String earlyType; // 提前还款类型：0-利息与服务费之和的50%，1-借款总金额的1.5%

	// private Double nominalInterestRateVo;
	// private Double firstServiceFreeVo;
	// private Double followupServiceFreeVo;
	// private DecimalFormat decimalFormat4 = new DecimalFormat("#.0000");
	// private DecimalFormat decimalFormat2 = new DecimalFormat("#.00");

	public Double getHeightLoanAmount() {
		return heightLoanAmount;
	}

	public void setHeightLoanAmount(Double heightLoanAmount) {
		this.heightLoanAmount = heightLoanAmount;
	}

	public Integer getRepaymentPlanId() {
		return repaymentPlanId;
	}

	public void setRepaymentPlanId(Integer repaymentPlanId) {
		this.repaymentPlanId = repaymentPlanId;
	}

	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}

	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}

	/**
	 * ★━━━━━━━━━━━━━━━━━━nominalInterestRate及Vo的get和set方法━━━━━━━━━━━━━━━━━━━━━━
	 * ★
	 */
	/**
	 * nominalInterestRate的get方法
	 * 
	 * @return nominalInterestRate
	 */
	public Double getNominalInterestRate() {
		/*
		 * if (nominalInterestRate != null) { return
		 * Double.parseDouble(decimalFormat2.format(nominalInterestRate * 100));
		 * } else { return nominalInterestRate; }
		 */
		return nominalInterestRate;
	}

	/**
	 * nominalInterestRate 的set方法
	 * 
	 * @param nominalInterestRate
	 *            利息
	 */
	public void setNominalInterestRate(Double nominalInterestRate) {
		// setNominalInterestRateVo(nominalInterestRate);
		this.nominalInterestRate = nominalInterestRate;

	}

	/*
	 * public Double getNominalInterestRateVo() { return nominalInterestRateVo;
	 * }
	 */

	/**
	 * nominalInterestRateVo 的set方法
	 * 
	 * @param nominalInterestRate
	 *            利息
	 */
	/*
	 * public void setNominalInterestRateVo(Double nominalInterestRate) { if
	 * (nominalInterestRate != null) { this.nominalInterestRateVo =
	 * Double.parseDouble(decimalFormat4.format(nominalInterestRate / 100)); }
	 * else { this.nominalInterestRateVo = nominalInterestRate; } }
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━nominalInterestRate及Vo的get和set方法END━━━━━━━━━━━━━━━━━━━
	 * ━━━★
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━firstServiceFree及Vo的get和set方法━━━━━━━━━━━━━━━━━━━━━━★
	 */

	/**
	 * 
	 * @return firstServiceFree
	 */
	public Double getFirstServiceFree() {
		/*
		 * if (firstServiceFree != null) { return
		 * Double.parseDouble(decimalFormat2.format(firstServiceFree * 100)); }
		 * else { return firstServiceFree; }
		 */
		return firstServiceFree;
	}

	/**
	 * 
	 * @param firstServiceFree
	 *            首期服务费
	 */
	public void setFirstServiceFree(Double firstServiceFree) {
		// setFirstServiceFreeVo(firstServiceFree);
		this.firstServiceFree = firstServiceFree;
	}

	/*
	 * public Double getFirstServiceFreeVo() { return firstServiceFreeVo; }
	 */

	/**
	 * 
	 * @param firstServiceFree
	 *            首期服务费
	 */
	/*
	 * public void setFirstServiceFreeVo(Double firstServiceFree) { if
	 * (firstServiceFree != null) { firstServiceFreeVo =
	 * Double.parseDouble(decimalFormat4.format(firstServiceFree / 100)); } else
	 * { this.firstServiceFreeVo = firstServiceFree; } }
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━firstServiceFree及Vo的get和set方法END━━━━━━━━━━━━━━━━━━━━━━
	 * ★
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━followupServiceFree及Vo的get和set方法━━━━━━━━━━━━━━━━━━━━━━
	 * ★
	 */

	/**
	 * @return followupServiceFree
	 */
	public Double getFollowupServiceFree() {
		/*
		 * if (followupServiceFree != null) { return
		 * Double.parseDouble(decimalFormat2.format(followupServiceFree * 100));
		 * } else { return followupServiceFree; }
		 */
		return followupServiceFree;
	}

	/**
	 * 
	 * @param followupServiceFree
	 *            后期服务费
	 */
	public void setFollowupServiceFree(Double followupServiceFree) {
		// setFollowupServiceFreeVo(followupServiceFree);
		this.followupServiceFree = followupServiceFree;
	}

	/*
	 * public Double getFollowupServiceFreeVo() { return followupServiceFreeVo;
	 * }
	 */

	/**
	 * 
	 * @param followupServiceFree
	 *            后期服务费
	 */
	/*
	 * public void setFollowupServiceFreeVo(Double followupServiceFree) { if
	 * (followupServiceFree != null) { this.followupServiceFreeVo =
	 * Double.parseDouble(decimalFormat4.format(followupServiceFree / 100)); }
	 * else { this.followupServiceFreeVo = followupServiceFree; } }
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━followupServiceFree及Vo的get和set方法END━━━━━━━━━━━━━━━━━━━
	 * ━━━★
	 */
	/**
	 * @return repaymentWay
	 */
	public String getRepaymentWay() {
		return repaymentWay;
	}

	public void setRepaymentWay(String repaymentWay) {
		this.repaymentWay = repaymentWay;
	}

	public Integer getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public Integer getRepaymentInstallments() {
		return repaymentInstallments;
	}

	public void setRepaymentInstallments(Integer repaymentInstallments) {
		this.repaymentInstallments = repaymentInstallments;
	}

	public Integer getEachInterval() {
		return eachInterval;
	}

	public void setEachInterval(Integer eachInterval) {
		this.eachInterval = eachInterval;
	}

	public Integer getRepaymentStartMonth() {
		return repaymentStartMonth;
	}

	public void setRepaymentStartMonth(Integer repaymentStartMonth) {
		this.repaymentStartMonth = repaymentStartMonth;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getEarlyType() {
		return earlyType;
	}

	public void setEarlyType(String earlyType) {
		this.earlyType = earlyType;
	}

}

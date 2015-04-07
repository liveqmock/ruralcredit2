package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 
 * @author haoqiang
 * 
 */
public class ReturnPlanDTO implements Serializable {
	private Integer returnPlanId;// 主键
	private Integer creditapplicationId;// 外键
	private String contractNo;// 合同编号
	private Double currMonPrincipal;// 应还本金
	private Double currMonInterest;// 应还利息
	private Double currMonManageFree;// 应还服务费
	private Double currMonLaterFree;// 应还滞纳金
	private Double currMonPenalty;// 应还罚息
	private Double currAccountTotal;// 应还总额
	private Double currPaindinCapital;// 实收本金
	private Double currPaindinInterest;// 实收利息
	private Double currPaindinManageFree;// 实收服务费
	private Double currLateFreePaid;// 实收滞纳金
	private Double currMonPaidPenalty;// 实收罚息
	private Double currMonPayTotal;// 实收总金额
	private String collectionStatus;// 收款状态
	private String preRegistFlag;// 提前收款标识
	private Double currReducePrincipal;// 减免本金
	private Double currReduceInterest;// 减免利息
	private Double currReduceManageFree;// 减免服务费
	private Double currReducePenalty;// 减免罚息
	private Double currReduceLaterFree;// 减免滞纳金
	private Double currReduceTotal;// 减免总额
	private String overdueFlag;// 逾期标识
	private Double receEarlyDamages;// 应收提前还款违约金
	private Double paidEarlyDamages;// 实收提前还款违约金
	private String remark;// 备注
	private String repaymentDate;// 当期还款日期
	private String autoSwitch;// 自动开关
	private  String period;//分期数
	private Double allHeadMoney; //全部提前还款金额

	public Integer getReturnPlanId() {
		return returnPlanId;
	}

	public void setReturnPlanId(Integer returnPlanId) {
		this.returnPlanId = returnPlanId;
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Double getCurrMonPrincipal() {
		return currMonPrincipal;
	}

	public void setCurrMonPrincipal(Double currMonPrincipal) {
		this.currMonPrincipal = currMonPrincipal;
	}

	public Double getCurrMonInterest() {
		return currMonInterest;
	}

	public void setCurrMonInterest(Double currMonInterest) {
		this.currMonInterest = currMonInterest;
	}

	public Double getCurrMonManageFree() {
		return currMonManageFree;
	}

	public void setCurrMonManageFree(Double currMonManageFree) {
		this.currMonManageFree = currMonManageFree;
	}

	public Double getCurrMonLaterFree() {
		return currMonLaterFree;
	}

	public void setCurrMonLaterFree(Double currMonLaterFree) {
		this.currMonLaterFree = currMonLaterFree;
	}

	public Double getCurrMonPenalty() {
		return currMonPenalty;
	}

	public void setCurrMonPenalty(Double currMonPenalty) {
		this.currMonPenalty = currMonPenalty;
	}

	public Double getCurrAccountTotal() {
		return currAccountTotal;
	}

	public void setCurrAccountTotal(Double currAccountTotal) {
		this.currAccountTotal = currAccountTotal;
	}

	public Double getCurrPaindinCapital() {
		return currPaindinCapital;
	}

	public void setCurrPaindinCapital(Double currPaindinCapital) {
		this.currPaindinCapital = currPaindinCapital;
	}

	public Double getCurrPaindinInterest() {
		return currPaindinInterest;
	}

	public void setCurrPaindinInterest(Double currPaindinInterest) {
		this.currPaindinInterest = currPaindinInterest;
	}

	public Double getCurrPaindinManageFree() {
		return currPaindinManageFree;
	}

	public void setCurrPaindinManageFree(Double currPaindinManageFree) {
		this.currPaindinManageFree = currPaindinManageFree;
	}

	public Double getCurrLateFreePaid() {
		return currLateFreePaid;
	}

	public void setCurrLateFreePaid(Double currLateFreePaid) {
		this.currLateFreePaid = currLateFreePaid;
	}

	public Double getCurrMonPaidPenalty() {
		return currMonPaidPenalty;
	}

	public void setCurrMonPaidPenalty(Double currMonPaidPenalty) {
		this.currMonPaidPenalty = currMonPaidPenalty;
	}

	public Double getCurrMonPayTotal() {
		return currMonPayTotal;
	}

	public void setCurrMonPayTotal(Double currMonPayTotal) {
		this.currMonPayTotal = currMonPayTotal;
	}

	public String getCollectionStatus() {
		return collectionStatus;
	}

	public void setCollectionStatus(String collectionStatus) {
		this.collectionStatus = collectionStatus;
	}

	public String getPreRegistFlag() {
		return preRegistFlag;
	}

	public void setPreRegistFlag(String preRegistFlag) {
		this.preRegistFlag = preRegistFlag;
	}

	public Double getCurrReducePrincipal() {
		return currReducePrincipal;
	}

	public void setCurrReducePrincipal(Double currReducePrincipal) {
		this.currReducePrincipal = currReducePrincipal;
	}

	public Double getCurrReduceInterest() {
		return currReduceInterest;
	}

	public void setCurrReduceInterest(Double currReduceInterest) {
		this.currReduceInterest = currReduceInterest;
	}

	public Double getCurrReduceManageFree() {
		return currReduceManageFree;
	}

	public void setCurrReduceManageFree(Double currReduceManageFree) {
		this.currReduceManageFree = currReduceManageFree;
	}

	public Double getCurrReducePenalty() {
		return currReducePenalty;
	}

	public void setCurrReducePenalty(Double currReducePenalty) {
		this.currReducePenalty = currReducePenalty;
	}

	public Double getCurrReduceLaterFree() {
		return currReduceLaterFree;
	}

	public void setCurrReduceLaterFree(Double currReduceLaterFree) {
		this.currReduceLaterFree = currReduceLaterFree;
	}

	public Double getCurrReduceTotal() {
		return currReduceTotal;
	}

	public void setCurrReduceTotal(Double currReduceTotal) {
		this.currReduceTotal = currReduceTotal;
	}

	public String getOverdueFlag() {
		return overdueFlag;
	}

	public void setOverdueFlag(String overdueFlag) {
		this.overdueFlag = overdueFlag;
	}

	public Double getReceEarlyDamages() {
		return receEarlyDamages;
	}

	public void setReceEarlyDamages(Double receEarlyDamages) {
		this.receEarlyDamages = receEarlyDamages;
	}

	public Double getPaidEarlyDamages() {
		return paidEarlyDamages;
	}

	public void setPaidEarlyDamages(Double paidEarlyDamages) {
		this.paidEarlyDamages = paidEarlyDamages;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getAutoSwitch() {
		return autoSwitch;
	}

	public void setAutoSwitch(String autoSwitch) {
		this.autoSwitch = autoSwitch;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Double getAllHeadMoney() {
		return allHeadMoney;
	}

	public void setAllHeadMoney(Double allHeadMoney) {
		this.allHeadMoney = allHeadMoney;
	}

}
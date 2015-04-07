package com.creditease.rc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author haoqiang
 * 
 */
public class ReturnPlan implements Serializable {
	private Long returnPlanId;// 主键
	private Long creditapplicationId;// 外键
	private String contractNo;// 合同编号
	private BigDecimal currMonPrincipal;// 应还本金
	private BigDecimal currMonInterest;// 应还利息
	private BigDecimal currMonManageFree;// 应还服务费
	private BigDecimal currMonLaterFree;// 应还滞纳金
	private BigDecimal currMonPenalty;// 应还罚息
	private BigDecimal currAccountTotal;// 应还总额
	private BigDecimal currPaindinCapital;// 实收本金
	private BigDecimal currPaindinInterest;// 实收利息
	private BigDecimal currPaindinManageFree;// 实收服务费
	private BigDecimal currLateFreePaid;// 实收滞纳金
	private BigDecimal currMonPaidPenalty;// 实收罚息
	private BigDecimal currMonPayTotal;// 实收总金额
	private String collectionStatus;// 收款状态
	private String preRegistFlag;// 提前收款标识
	private BigDecimal currReducePrincipal;// 减免本金
	private BigDecimal currReduceInterest;// 减免利息
	private BigDecimal currReduceManageFree;// 减免服务费
	private BigDecimal currReducePenalty;// 减免罚息
	private BigDecimal currReduceLaterFree;// 减免滞纳金
	private BigDecimal currReduceTotal;// 减免总额
	private String overdueFlag;// 逾期标识
	private BigDecimal receEarlyDamages;// 应收提前还款违约金
	private BigDecimal paidEarlyDamages;// 实收提前还款违约金
	private String remark;// 备注
	private Date repaymentDate;// 当期还款日期
	private String autoSwitch;// 自动开关
	private String period;// 分期数
	private BigDecimal allHeadMoney; // 全部提前还款金额

	public Long getReturnPlanId() {
		return returnPlanId;
	}

	public void setReturnPlanId(Long returnPlanId) {
		this.returnPlanId = returnPlanId;
	}

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getCurrMonPrincipal() {
		return currMonPrincipal;
	}

	public void setCurrMonPrincipal(BigDecimal currMonPrincipal) {
		this.currMonPrincipal = currMonPrincipal;
	}

	public BigDecimal getCurrMonInterest() {
		return currMonInterest;
	}

	public void setCurrMonInterest(BigDecimal currMonInterest) {
		this.currMonInterest = currMonInterest;
	}

	public BigDecimal getCurrMonManageFree() {
		return currMonManageFree;
	}

	public void setCurrMonManageFree(BigDecimal currMonManageFree) {
		this.currMonManageFree = currMonManageFree;
	}

	public BigDecimal getCurrMonLaterFree() {
		return currMonLaterFree;
	}

	public void setCurrMonLaterFree(BigDecimal currMonLaterFree) {
		this.currMonLaterFree = currMonLaterFree;
	}

	public BigDecimal getCurrMonPenalty() {
		return currMonPenalty;
	}

	public void setCurrMonPenalty(BigDecimal currMonPenalty) {
		this.currMonPenalty = currMonPenalty;
	}

	public BigDecimal getCurrAccountTotal() {
		return currAccountTotal;
	}

	public void setCurrAccountTotal(BigDecimal currAccountTotal) {
		this.currAccountTotal = currAccountTotal;
	}

	public BigDecimal getCurrPaindinCapital() {
		return currPaindinCapital;
	}

	public void setCurrPaindinCapital(BigDecimal currPaindinCapital) {
		this.currPaindinCapital = currPaindinCapital;
	}

	public BigDecimal getCurrPaindinInterest() {
		return currPaindinInterest;
	}

	public void setCurrPaindinInterest(BigDecimal currPaindinInterest) {
		this.currPaindinInterest = currPaindinInterest;
	}

	public BigDecimal getCurrPaindinManageFree() {
		return currPaindinManageFree;
	}

	public void setCurrPaindinManageFree(BigDecimal currPaindinManageFree) {
		this.currPaindinManageFree = currPaindinManageFree;
	}

	public BigDecimal getCurrLateFreePaid() {
		return currLateFreePaid;
	}

	public void setCurrLateFreePaid(BigDecimal currLateFreePaid) {
		this.currLateFreePaid = currLateFreePaid;
	}

	public BigDecimal getCurrMonPaidPenalty() {
		return currMonPaidPenalty;
	}

	public void setCurrMonPaidPenalty(BigDecimal currMonPaidPenalty) {
		this.currMonPaidPenalty = currMonPaidPenalty;
	}

	public BigDecimal getCurrMonPayTotal() {
		return currMonPayTotal;
	}

	public void setCurrMonPayTotal(BigDecimal currMonPayTotal) {
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

	public BigDecimal getCurrReducePrincipal() {
		return currReducePrincipal;
	}

	public void setCurrReducePrincipal(BigDecimal currReducePrincipal) {
		this.currReducePrincipal = currReducePrincipal;
	}

	public BigDecimal getCurrReduceInterest() {
		return currReduceInterest;
	}

	public void setCurrReduceInterest(BigDecimal currReduceInterest) {
		this.currReduceInterest = currReduceInterest;
	}

	public BigDecimal getCurrReduceManageFree() {
		return currReduceManageFree;
	}

	public void setCurrReduceManageFree(BigDecimal currReduceManageFree) {
		this.currReduceManageFree = currReduceManageFree;
	}

	public BigDecimal getCurrReducePenalty() {
		return currReducePenalty;
	}

	public void setCurrReducePenalty(BigDecimal currReducePenalty) {
		this.currReducePenalty = currReducePenalty;
	}

	public BigDecimal getCurrReduceLaterFree() {
		return currReduceLaterFree;
	}

	public void setCurrReduceLaterFree(BigDecimal currReduceLaterFree) {
		this.currReduceLaterFree = currReduceLaterFree;
	}

	public BigDecimal getCurrReduceTotal() {
		return currReduceTotal;
	}

	public void setCurrReduceTotal(BigDecimal currReduceTotal) {
		this.currReduceTotal = currReduceTotal;
	}

	public String getOverdueFlag() {
		return overdueFlag;
	}

	public void setOverdueFlag(String overdueFlag) {
		this.overdueFlag = overdueFlag;
	}

	public BigDecimal getReceEarlyDamages() {
		return receEarlyDamages;
	}

	public void setReceEarlyDamages(BigDecimal receEarlyDamages) {
		this.receEarlyDamages = receEarlyDamages;
	}

	public BigDecimal getPaidEarlyDamages() {
		return paidEarlyDamages;
	}

	public void setPaidEarlyDamages(BigDecimal paidEarlyDamages) {
		this.paidEarlyDamages = paidEarlyDamages;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Date repaymentDate) {
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

	public BigDecimal getAllHeadMoney() {
		return allHeadMoney;
	}

	public void setAllHeadMoney(BigDecimal allHeadMoney) {
		this.allHeadMoney = allHeadMoney;
	}

}
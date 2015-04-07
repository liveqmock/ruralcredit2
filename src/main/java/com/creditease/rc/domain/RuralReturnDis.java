package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author haoqiang
 * 
 */
public class RuralReturnDis implements Serializable {
	private Integer distributionId;
	private Integer returnPlanId;
	private String contractNo;
	private Double currRecievePrincipal;
	private Double currRecieveInterest;
	private Double currRecieveManage;
	private Double currRecieveLatefree;
	private Double currRecievePenalty;
	private Double currRecieveTotal;
	private Double actPaidinCapital;
	private Double actPaidInterest;
	private Double actPaidManageFree;
	private Double actPaidFine;
	private Double actPaidPanalty;
	private Double currPaidTotal;
	private String oneTimeRepayment;
	private Date dateCharge;
	private String respectiveMonRepay;
	private Date confimRepayTime;
	private String borrowerName;
	private String returnCompanyFlag;
	private String clearBreachFlag;
	private String breachFlag;
	private Integer impunityPaidFine;
	private Integer impunityPaidPanalty;
	private Double currReducePrincipal;
	private Double currReduceInterest;
	private Double currReduceManageFree;
	private Double currReducePenalty;
	private Double currReduceLaterFree;
	private Double currReduceTotal;
	private Integer receivedRecordId;
	private Integer borrowerServiceAppId;
	private Double receEarlyDamages;
	private Double paidEarlyDamages;
	private Date repaymentDate;

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Integer getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(Integer distributionId) {
		this.distributionId = distributionId;
	}

	public Integer getReturnPlanId() {
		return returnPlanId;
	}

	public void setReturnPlanId(Integer returnPlanId) {
		this.returnPlanId = returnPlanId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Double getCurrRecievePrincipal() {
		return currRecievePrincipal;
	}

	public void setCurrRecievePrincipal(Double currRecievePrincipal) {
		this.currRecievePrincipal = currRecievePrincipal;
	}

	public Double getCurrRecieveInterest() {
		return currRecieveInterest;
	}

	public void setCurrRecieveInterest(Double currRecieveInterest) {
		this.currRecieveInterest = currRecieveInterest;
	}

	public Double getCurrRecieveManage() {
		return currRecieveManage;
	}

	public void setCurrRecieveManage(Double currRecieveManage) {
		this.currRecieveManage = currRecieveManage;
	}

	public Double getCurrRecieveLatefree() {
		return currRecieveLatefree;
	}

	public void setCurrRecieveLatefree(Double currRecieveLatefree) {
		this.currRecieveLatefree = currRecieveLatefree;
	}

	public Double getCurrRecievePenalty() {
		return currRecievePenalty;
	}

	public void setCurrRecievePenalty(Double currRecievePenalty) {
		this.currRecievePenalty = currRecievePenalty;
	}

	public Double getCurrRecieveTotal() {
		return currRecieveTotal;
	}

	public void setCurrRecieveTotal(Double currRecieveTotal) {
		this.currRecieveTotal = currRecieveTotal;
	}

	public Double getActPaidinCapital() {
		return actPaidinCapital;
	}

	public void setActPaidinCapital(Double actPaidinCapital) {
		this.actPaidinCapital = actPaidinCapital;
	}

	public Double getActPaidInterest() {
		return actPaidInterest;
	}

	public void setActPaidInterest(Double actPaidInterest) {
		this.actPaidInterest = actPaidInterest;
	}

	public Double getActPaidManageFree() {
		return actPaidManageFree;
	}

	public void setActPaidManageFree(Double actPaidManageFree) {
		this.actPaidManageFree = actPaidManageFree;
	}

	public Double getActPaidFine() {
		return actPaidFine;
	}

	public void setActPaidFine(Double actPaidFine) {
		this.actPaidFine = actPaidFine;
	}

	public Double getActPaidPanalty() {
		return actPaidPanalty;
	}

	public void setActPaidPanalty(Double actPaidPanalty) {
		this.actPaidPanalty = actPaidPanalty;
	}

	public Double getCurrPaidTotal() {
		return currPaidTotal;
	}

	public void setCurrPaidTotal(Double currPaidTotal) {
		this.currPaidTotal = currPaidTotal;
	}

	public String getOneTimeRepayment() {
		return oneTimeRepayment;
	}

	public void setOneTimeRepayment(String oneTimeRepayment) {
		this.oneTimeRepayment = oneTimeRepayment;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getDateCharge() {
		return dateCharge;
	}

	public void setDateCharge(Date dateCharge) {
		this.dateCharge = dateCharge;
	}

	public String getRespectiveMonRepay() {
		return respectiveMonRepay;
	}

	public void setRespectiveMonRepay(String respectiveMonRepay) {
		this.respectiveMonRepay = respectiveMonRepay;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getConfimRepayTime() {
		return confimRepayTime;
	}

	public void setConfimRepayTime(Date confimRepayTime) {
		this.confimRepayTime = confimRepayTime;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getReturnCompanyFlag() {
		return returnCompanyFlag;
	}

	public void setReturnCompanyFlag(String returnCompanyFlag) {
		this.returnCompanyFlag = returnCompanyFlag;
	}

	public String getClearBreachFlag() {
		return clearBreachFlag;
	}

	public void setClearBreachFlag(String clearBreachFlag) {
		this.clearBreachFlag = clearBreachFlag;
	}

	public String getBreachFlag() {
		return breachFlag;
	}

	public void setBreachFlag(String breachFlag) {
		this.breachFlag = breachFlag;
	}

	public Integer getImpunityPaidFine() {
		return impunityPaidFine;
	}

	public void setImpunityPaidFine(Integer impunityPaidFine) {
		this.impunityPaidFine = impunityPaidFine;
	}

	public Integer getImpunityPaidPanalty() {
		return impunityPaidPanalty;
	}

	public void setImpunityPaidPanalty(Integer impunityPaidPanalty) {
		this.impunityPaidPanalty = impunityPaidPanalty;
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

	public Integer getReceivedRecordId() {
		return receivedRecordId;
	}

	public void setReceivedRecordId(Integer receivedRecordId) {
		this.receivedRecordId = receivedRecordId;
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

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

}
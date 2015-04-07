package com.creditease.rc.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class OverDueListVo {

	private Long creditapplicationId;
	private String groupNumber;
	private String groupName;
	private String loanOfficerName;
	private String businessType;
	private String companyId;
	private String companyName;
	private String defaultReturnmentWay;
	private String repaymentPlanName;
	private Date signagreementDate;
	private Date aOverdueStart;
	private BigDecimal aOverdueMoney;
	private Integer aOverdueCount;
	private Integer overdueDayCount;
	private Integer historyMaxOverDays;   //历史逾期最大天数

	public Integer getHistoryMaxOverDays() {
		return historyMaxOverDays;
	}

	public void setHistoryMaxOverDays(Integer historyMaxOverDays) {
		this.historyMaxOverDays = historyMaxOverDays;
	}

	public Integer getOverdueDayCount() {
		return overdueDayCount;
	}

	public void setOverdueDayCount(Integer overdueDayCount) {
		this.overdueDayCount = overdueDayCount;
	}

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLoanOfficerName() {
		return loanOfficerName;
	}

	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDefaultReturnmentWay() {
		return defaultReturnmentWay;
	}

	public void setDefaultReturnmentWay(String defaultReturnmentWay) {
		this.defaultReturnmentWay = defaultReturnmentWay;
	}

	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}

	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getSignagreementDate() {
		return signagreementDate;
	}

	public void setSignagreementDate(Date signagreementDate) {
		this.signagreementDate = signagreementDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getaOverdueStart() {
		return aOverdueStart;
	}

	public void setaOverdueStart(Date aOverdueStart) {
		this.aOverdueStart = aOverdueStart;
	}

	public BigDecimal getaOverdueMoney() {
		return aOverdueMoney;
	}

	public void setaOverdueMoney(BigDecimal aOverdueMoney) {
		this.aOverdueMoney = aOverdueMoney;
	}

	public Integer getaOverdueCount() {
		return aOverdueCount;
	}

	public void setaOverdueCount(Integer aOverdueCount) {
		this.aOverdueCount = aOverdueCount;
	}

}

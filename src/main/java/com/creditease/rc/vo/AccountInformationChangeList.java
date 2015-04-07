package com.creditease.rc.vo;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.common.JsonYMDHMDDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class AccountInformationChangeList {

	private Long modifyCatdAppId;
	private Long accountInfoId;
	private Long creditapplicationId;
	private String businessNumber;
	private String name;
	private String status;
	private String companyName;
	private String proposerName;
	private Date applicationTime;
	private Date signagreementDate;
	private String refusecause;

	public String getRefusecause() {
		return refusecause;
	}

	public void setRefusecause(String refusecause) {
		this.refusecause = refusecause;
	}

	public Long getModifyCatdAppId() {
		return modifyCatdAppId;
	}

	public void setModifyCatdAppId(Long modifyCatdAppId) {
		this.modifyCatdAppId = modifyCatdAppId;
	}

	public Long getAccountInfoId() {
		return accountInfoId;
	}

	public void setAccountInfoId(Long accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getSignagreementDate() {
		return signagreementDate;
	}

	public void setSignagreementDate(Date signagreementDate) {
		this.signagreementDate = signagreementDate;
	}

}

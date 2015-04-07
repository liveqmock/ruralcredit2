package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
/**
 * 
 * @author zhangman
 *
 */
public class CreditOrganization implements Serializable{
	
	private Integer creditOrgId;
	private Integer creditCardInfoId;
	private String creditCardIssue;
	private Date openingDate;
	private Double creditLimit;
	private Double creditUsedAmount;
	public Integer getCreditOrgId() {
		return creditOrgId;
	}
	public void setCreditOrgId(Integer creditOrgId) {
		this.creditOrgId = creditOrgId;
	}
	public Integer getCreditCardInfoId() {
		return creditCardInfoId;
	}
	public void setCreditCardInfoId(Integer creditCardInfoId) {
		this.creditCardInfoId = creditCardInfoId;
	}
	public String getCreditCardIssue() {
		return creditCardIssue;
	}
	public void setCreditCardIssue(String creditCardIssue) {
		this.creditCardIssue = creditCardIssue;
	}
	@JsonSerialize(using=JsonYMDDateSerializer.class) 
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public Double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public Double getCreditUsedAmount() {
		return creditUsedAmount;
	}
	public void setCreditUsedAmount(Double creditUsedAmount) {
		this.creditUsedAmount = creditUsedAmount;
	}

}

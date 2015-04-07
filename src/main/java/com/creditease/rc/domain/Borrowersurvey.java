package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author haoqiang
 * 
 */
public class Borrowersurvey implements Serializable {

	private Integer borrowerSurveyId;
	private Integer borrowerServiceAppId;
	private Double familyWageincome;
	private Double otherIncome;
	private Double liabilities;
	private Double claims;
	private String borrowerrepaymentAbility;
	private String repaymentWill;
	private String badrecordsAndHobbies;
	private String otherSupplementary;
	private Double bankSave;
	private Double familyExpenditure;
	private Double bankLiabilitites;
	private Double totalMoney;

	public Integer getBorrowerSurveyId() {
		return borrowerSurveyId;
	}

	public void setBorrowerSurveyId(Integer borrowerSurveyId) {
		this.borrowerSurveyId = borrowerSurveyId;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public Double getFamilyWageincome() {
		return familyWageincome;
	}

	public void setFamilyWageincome(Double familyWageincome) {
		this.familyWageincome = familyWageincome;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Double getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(Double liabilities) {
		this.liabilities = liabilities;
	}

	public Double getClaims() {
		return claims;
	}

	public void setClaims(Double claims) {
		this.claims = claims;
	}

	public String getBorrowerrepaymentAbility() {
		return borrowerrepaymentAbility;
	}

	public void setBorrowerrepaymentAbility(String borrowerrepaymentAbility) {
		this.borrowerrepaymentAbility = borrowerrepaymentAbility;
	}

	public String getRepaymentWill() {
		return repaymentWill;
	}

	public void setRepaymentWill(String repaymentWill) {
		this.repaymentWill = repaymentWill;
	}

	public String getBadrecordsAndHobbies() {
		return badrecordsAndHobbies;
	}

	public void setBadrecordsAndHobbies(String badrecordsAndHobbies) {
		this.badrecordsAndHobbies = badrecordsAndHobbies;
	}

	public String getOtherSupplementary() {
		return otherSupplementary;
	}

	public void setOtherSupplementary(String otherSupplementary) {
		this.otherSupplementary = otherSupplementary;
	}

	public Double getBankSave() {
		return bankSave;
	}

	public void setBankSave(Double bankSave) {
		this.bankSave = bankSave;
	}

	public Double getFamilyExpenditure() {
		return familyExpenditure;
	}

	public void setFamilyExpenditure(Double familyExpenditure) {
		this.familyExpenditure = familyExpenditure;
	}

	public Double getBankLiabilitites() {
		return bankLiabilitites;
	}

	public void setBankLiabilitites(Double bankLiabilitites) {
		this.bankLiabilitites = bankLiabilitites;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

}

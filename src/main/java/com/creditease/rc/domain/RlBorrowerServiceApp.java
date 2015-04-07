package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 组员类
 * 
 * @author xubingzhao
 * 
 */
public class RlBorrowerServiceApp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4279201180544955639L;
	private Integer borrowerServiceAppId;
	private Integer creditapplicationId;
	private Integer customerBasicId;
	private String name;
	private String leader;
	private String former;
	private String gender;
	private String homeAddress;
	private Integer age;
	private String residenceAddress;
	private String credentialsNumber;
	private String credentialsType;
	private String borrRemark;
	private Date applyDate;
	private String mobilephone;
	private String paymentSource;
	private Double applyLimit;
	private Double maxMonthlyPayment;
	private String detailsLoansUse;
	private String detailsLoansUseType;
	private String livingCommercial;
	private String livingSelf;
	private String livingRent;
	private String livingRelative;
	private String livingOther;
	private Date livingDate;
	private String livingArea;
	private String creditContract;
	private Double familyIncome;

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public Integer getCustomerBasicId() {
		return customerBasicId;
	}

	public void setCustomerBasicId(Integer customerBasicId) {
		this.customerBasicId = customerBasicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getFormer() {
		return former;
	}

	public void setFormer(String former) {
		this.former = former;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getBorrRemark() {
		return borrRemark;
	}

	public void setBorrRemark(String borrRemark) {
		this.borrRemark = borrRemark;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

	public Double getApplyLimit() {
		return applyLimit;
	}

	public void setApplyLimit(Double applyLimit) {
		this.applyLimit = applyLimit;
	}

	public Double getMaxMonthlyPayment() {
		return maxMonthlyPayment;
	}

	public void setMaxMonthlyPayment(Double maxMonthlyPayment) {
		this.maxMonthlyPayment = maxMonthlyPayment;
	}

	public String getDetailsLoansUse() {
		return detailsLoansUse;
	}

	public void setDetailsLoansUse(String detailsLoansUse) {
		this.detailsLoansUse = detailsLoansUse;
	}

	public String getDetailsLoansUseType() {
		return detailsLoansUseType;
	}

	public void setDetailsLoansUseType(String detailsLoansUseType) {
		this.detailsLoansUseType = detailsLoansUseType;
	}

	public String getLivingCommercial() {
		return livingCommercial;
	}

	public void setLivingCommercial(String livingCommercial) {
		this.livingCommercial = livingCommercial;
	}

	public String getLivingSelf() {
		return livingSelf;
	}

	public void setLivingSelf(String livingSelf) {
		this.livingSelf = livingSelf;
	}

	public String getLivingRent() {
		return livingRent;
	}

	public void setLivingRent(String livingRent) {
		this.livingRent = livingRent;
	}

	public String getLivingRelative() {
		return livingRelative;
	}

	public void setLivingRelative(String livingRelative) {
		this.livingRelative = livingRelative;
	}

	public String getLivingOther() {
		return livingOther;
	}

	public void setLivingOther(String livingOther) {
		this.livingOther = livingOther;
	}

	public Date getLivingDate() {
		return livingDate;
	}

	public void setLivingDate(Date livingDate) {
		this.livingDate = livingDate;
	}

	public String getLivingArea() {
		return livingArea;
	}

	public void setLivingArea(String livingArea) {
		this.livingArea = livingArea;
	}

	public String getCreditContract() {
		return creditContract;
	}

	public void setCreditContract(String creditContract) {
		this.creditContract = creditContract;
	}

	public Double getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(Double familyIncome) {
		this.familyIncome = familyIncome;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public String getConsulteSource() {
		return consulteSource;
	}

	public void setConsulteSource(String consulteSource) {
		this.consulteSource = consulteSource;
	}

	public String getDetailsConsulteSource() {
		return detailsConsulteSource;
	}

	public void setDetailsConsulteSource(String detailsConsulteSource) {
		this.detailsConsulteSource = detailsConsulteSource;
	}

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}

	public String getSecondFlag() {
		return secondFlag;
	}

	public void setSecondFlag(String secondFlag) {
		this.secondFlag = secondFlag;
	}

	public String getThirdFlag() {
		return thirdFlag;
	}

	public void setThirdFlag(String thirdFlag) {
		this.thirdFlag = thirdFlag;
	}

	public String getValidState() {
		return validState;
	}

	public void setValidState(String validState) {
		this.validState = validState;
	}

	private String auditStatus;
	private Integer countyId;
	private Integer townId;
	private Integer villageId;
	private String consulteSource;
	private String detailsConsulteSource;
	private String firstFlag;
	private String secondFlag;
	private String thirdFlag;
	private String validState;

}
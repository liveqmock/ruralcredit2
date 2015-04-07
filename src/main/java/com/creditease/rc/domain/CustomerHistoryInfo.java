package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author zhangman
 *
 */
public class CustomerHistoryInfo implements Serializable {

	public Integer getCustomerHistoryId() {
		return customerHistoryId;
	}

	public void setCustomerHistoryId(Integer customerHistoryId) {
		this.customerHistoryId = customerHistoryId;
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

	public String getFormer() {
		return former;
	}

	public void setFormer(String former) {
		this.former = former;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	private Integer customerHistoryId;
	private Integer customerBasicId;
	private String name;
	private String former;
	private String credentialsType;
	private String credentialsNumber;
	private String gender;
	private String national;
	private String maritalStatus;
	private String mobilephone;
	private String telephone;
	private Double personIncome;
	private String highestEducation;
	private String presentAddress;
	private String residenceAddress;
	private String remark;
	private Date changeTime;
	private String operateName;
	private Integer operateId;
	public Double getPersonIncome() {
		return personIncome;
	}

	public void setPersonIncome(Double personIncome) {
		this.personIncome = personIncome;
	}

}

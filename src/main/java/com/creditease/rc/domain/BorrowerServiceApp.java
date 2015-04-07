package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * @author zhangman
 *
 */
public class BorrowerServiceApp implements Serializable {
	private Integer borrowerServiceAppId; // 借款服务申请ID
	private String borrowerServiceAppDESId;//ID加密
	private Integer customerBasicId; //
	private Integer creditapplicationId; // 信贷申请单ID
	private String name; // 姓名
	private String leader = "0"; // 组长
	private String former; // 曾用名
	private String gender; // 性别
	private String homeAddress; // 家庭住址
	private Integer age; // 年龄
	private String residenceAddress; // 户籍地址
	private String credentialsNumber; // 身份证号码
	private String credentialsType; // 证件类型
	private String mobilephone; // 手机电话号码
	private String borrRemark; // 备注
	private Date applyDate; // 申请日期
	private String paymentSource; // 还款来源
	private Double applyLimit; // 申请借款额度
	private Double maxMonthlyPayment; // 最高月还款额
	private String detailsLoansUse;
	private String detailsLoansuseType;
	private String livingCommercial;
	private String livingSelf;
	private String livingRent;
	private String livingRelative;
	private String livingOther;
	private Date livingDate;
	private String livingArea;
	private String creditContract;
	private Double familyIncome;
	private String birthday;
	private String national;    //民族
	private String auditStatus;// 状态
	private Integer villageId;
	private Integer countyId;
	private Integer townId;
	private String consulteSource;// 咨询来源
	private String detailsConsulteSource;// 详细咨询来源

	private String firstFlag; // 入户一标识
	private String secondFlag; // 入户二标识
	private String thirdFlag; // 联系人调查标识
	private String validState;// 个人申请删除标识
	private String hourseholdAddress; //户籍地址
	private String maritalStatus;		//婚姻装款
	


	public String getValidState() {
		return validState;
	}

	public void setValidState(String validState) {
		this.validState = validState;
	}

	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
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

	// 身份证号 一致转为 大写 
	public void setCredentialsNumber(String credentialsNumber) {
		if(credentialsNumber != null){
			this.credentialsNumber = credentialsNumber.toUpperCase();
		}else{
			this.credentialsNumber = credentialsNumber;
		}
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getBorrRemark() {
		return borrRemark;
	}

	public void setBorrRemark(String borrRemark) {
		this.borrRemark = borrRemark;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}


	public String getDetailsLoansUse() {
		return detailsLoansUse;
	}

	public void setDetailsLoansUse(String detailsLoansUse) {
		this.detailsLoansUse = detailsLoansUse;
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

	public String getDetailsLoansuseType() {
		return detailsLoansuseType;
	}

	public void setDetailsLoansuseType(String detailsLoansuseType) {
		this.detailsLoansuseType = detailsLoansuseType;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getCustomerBasicId() {
		return customerBasicId;
	}

	public void setCustomerBasicId(Integer customerBasicId) {
		this.customerBasicId = customerBasicId;
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}


	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getLivingDate() {
		return livingDate;
	}

	public void setLivingDate(Date livingDate) {
		this.livingDate = livingDate;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
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

	public Double getApplyLimit() {
		return applyLimit;
	}

	public void setApplyLimit(Double applyLimit) {
		this.applyLimit = applyLimit;
	}

	public Double getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(Double familyIncome) {
		this.familyIncome = familyIncome;
	}

	public Double getMaxMonthlyPayment() {
		return maxMonthlyPayment;
	}

	public void setMaxMonthlyPayment(Double maxMonthlyPayment) {
		this.maxMonthlyPayment = maxMonthlyPayment;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHourseholdAddress() {
		return hourseholdAddress;
	}

	public void setHourseholdAddress(String hourseholdAddress) {
		this.hourseholdAddress = hourseholdAddress;
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

	public String getBorrowerServiceAppDESId() {
		return borrowerServiceAppDESId;
	}

	public void setBorrowerServiceAppDESId(String borrowerServiceAppDESId) {
		this.borrowerServiceAppDESId = borrowerServiceAppDESId;
	}

}

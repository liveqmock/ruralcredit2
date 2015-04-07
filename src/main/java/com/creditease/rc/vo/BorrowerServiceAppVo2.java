package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class BorrowerServiceAppVo2 {
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
	private Integer livingYear;//居住年限
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
	private String businessCommercial;//经营场所状况-自由商品房
	private String businessSelf;//经营场所状况-自有宅基地
	private String businessRent;//经营场所状况-租住
	private String businessRelative;//经营场所状况-亲属住房
	private String businessOther;//经营场所状况-其他
	private Date businessDate;//经营场所到期时间
	private String businessArea;//经营场所面积
	private Long businessYear;//经营场所年限
	private String officePhone;//办公电话号码
	private String jobStatus;//就业情况: 0-有工作,1-失业
	private String bondsmanBorrower;//担保人与申请人关系
	

	public String getBondsmanBorrower() {
		return bondsmanBorrower;
	}

	public void setBondsmanBorrower(String bondsmanBorrower) {
		this.bondsmanBorrower = bondsmanBorrower;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public Long getBusinessYear() {
		return businessYear;
	}

	public void setBusinessYear(Long businessYear) {
		this.businessYear = businessYear;
	}

	public String getBusinessOther() {
		return businessOther;
	}

	public void setBusinessOther(String businessOther) {
		this.businessOther = businessOther;
	}

	public String getBusinessRelative() {
		return businessRelative;
	}

	public void setBusinessRelative(String businessRelative) {
		this.businessRelative = businessRelative;
	}

	public String getBusinessRent() {
		return businessRent;
	}

	public void setBusinessRent(String businessRent) {
		this.businessRent = businessRent;
	}

	public String getBusinessSelf() {
		return businessSelf;
	}

	public void setBusinessSelf(String businessSelf) {
		this.businessSelf = businessSelf;
	}

	public String getBusinessCommercial() {
		return businessCommercial;
	}

	public void setBusinessCommercial(String businessCommercial) {
		this.businessCommercial = businessCommercial;
	}

	public Integer getLivingYear() {
		return livingYear;
	}

	public void setLivingYear(Integer livingYear) {
		this.livingYear = livingYear;
	}

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

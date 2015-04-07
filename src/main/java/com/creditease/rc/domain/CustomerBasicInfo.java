package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author zhangman
 *
 */
public class CustomerBasicInfo implements Serializable {


	private Integer customerBasicId;//主键
	private String name;			//姓名
	private String former;			//曾用名
	private String credentialsType;	//客户证件类型
	private String credentialsNumber;//客户证件号码
	private String gender;			//客户性别
	private String national;		//民族
	private String maritalStatus;	//婚姻状况
	private String mobilephone;		//手机电话号码
	private String telephone;		//住宅电话号码
	private Double personIncome;	//个人年收入
	private String highestEducation;//最高学历
	private String presentAddress;	//当前居住地址
	private String residenceAddress;//客户户籍地址
	private String remark;			//备注
	private Date createTime;//创建时间
	private String operateName;			//操作人姓名
	private Integer operateId;			//操作人ID
	private Integer vallageId;//村id
	private Integer countyId;
	private Integer townId;
	private String auditStatus; 		//状态
	private String customerType;		//类型
	private String operation;		//借款人类型
	private String guaranor;		//担保人
	
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
	/**
	 * 
	 * @param credentialsNumber 
	 */
	public void setCredentialsNumber(String credentialsNumber) {
		if(credentialsNumber != null){
			this.credentialsNumber = credentialsNumber.toUpperCase();
		}else{
			this.credentialsNumber = credentialsNumber;
		}
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
	public Integer getVallageId() {
		return vallageId;
	}
	public void setVallageId(Integer vallageId) {
		this.vallageId = vallageId;
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
	public Double getPersonIncome() {
		return personIncome;
	}
	public void setPersonIncome(Double personIncome) {
		this.personIncome = personIncome;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getGuaranor() {
		return guaranor;
	}
	public void setGuaranor(String guaranor) {
		this.guaranor = guaranor;
	}

}

package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for mortgagorRelatives complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorRelatives">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="alimony" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="generalDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasHouseProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasHousingLoan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housePropertyCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housingLoanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="income" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isAware" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isHealth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPayAlimony" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPresent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSameCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jobStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jobType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mortgagorRelativesId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizationTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relativeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repayment" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="telNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorRelatives", propOrder = { "age", "alimony", "amount",
		"contentType", "createDate", "creator", "generalDesc",
		"hasHouseProperty", "hasHousingLoan", "housePropertyCount",
		"housingLoanType", "income", "isAware", "isHealth", "isPayAlimony",
		"isPresent", "isSameCity", "jobStatus", "jobType", "memo",
		"mortgagorId", "mortgagorRelativesId", "nm", "operDate", "operator",
		"organization", "organizationTel", "relativeDesc", "repayment",
		"telNum" })
public class MortgagorRelatives {

	protected Integer age;
	protected BigDecimal alimony;
	protected Integer amount;
	protected String contentType;
	protected String createDate;
	protected Long creator;
	protected String generalDesc;
	protected String hasHouseProperty;
	protected String hasHousingLoan;
	protected String housePropertyCount;
	protected String housingLoanType;
	protected BigDecimal income;
	protected String isAware;
	protected String isHealth;
	protected String isPayAlimony;
	protected String isPresent;
	protected String isSameCity;
	protected String jobStatus;
	protected String jobType;
	protected String memo;
	protected Long mortgagorId;
	protected Long mortgagorRelativesId;
	protected String nm;
	protected String operDate;
	protected Long operator;
	protected String organization;
	protected String organizationTel;
	protected String relativeDesc;
	protected BigDecimal repayment;
	protected String telNum;

	/**
	 * Gets the value of the age property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the value of the age property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAge(Integer value) {
		this.age = value;
	}

	/**
	 * Gets the value of the alimony property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getAlimony() {
		return alimony;
	}

	/**
	 * Sets the value of the alimony property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setAlimony(BigDecimal value) {
		this.alimony = value;
	}

	/**
	 * Gets the value of the amount property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * Sets the value of the amount property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAmount(Integer value) {
		this.amount = value;
	}

	/**
	 * Gets the value of the contentType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Sets the value of the contentType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentType(String value) {
		this.contentType = value;
	}

	/**
	 * Gets the value of the createDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateDate(String value) {
		this.createDate = value;
	}

	/**
	 * Gets the value of the creator property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getCreator() {
		return creator;
	}

	/**
	 * Sets the value of the creator property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setCreator(Long value) {
		this.creator = value;
	}

	/**
	 * Gets the value of the generalDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGeneralDesc() {
		return generalDesc;
	}

	/**
	 * Sets the value of the generalDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGeneralDesc(String value) {
		this.generalDesc = value;
	}

	/**
	 * Gets the value of the hasHouseProperty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHasHouseProperty() {
		return hasHouseProperty;
	}

	/**
	 * Sets the value of the hasHouseProperty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHasHouseProperty(String value) {
		this.hasHouseProperty = value;
	}

	/**
	 * Gets the value of the hasHousingLoan property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHasHousingLoan() {
		return hasHousingLoan;
	}

	/**
	 * Sets the value of the hasHousingLoan property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHasHousingLoan(String value) {
		this.hasHousingLoan = value;
	}

	/**
	 * Gets the value of the housePropertyCount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHousePropertyCount() {
		return housePropertyCount;
	}

	/**
	 * Sets the value of the housePropertyCount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHousePropertyCount(String value) {
		this.housePropertyCount = value;
	}

	/**
	 * Gets the value of the housingLoanType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHousingLoanType() {
		return housingLoanType;
	}

	/**
	 * Sets the value of the housingLoanType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHousingLoanType(String value) {
		this.housingLoanType = value;
	}

	/**
	 * Gets the value of the income property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getIncome() {
		return income;
	}

	/**
	 * Sets the value of the income property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setIncome(BigDecimal value) {
		this.income = value;
	}

	/**
	 * Gets the value of the isAware property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsAware() {
		return isAware;
	}

	/**
	 * Sets the value of the isAware property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsAware(String value) {
		this.isAware = value;
	}

	/**
	 * Gets the value of the isHealth property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsHealth() {
		return isHealth;
	}

	/**
	 * Sets the value of the isHealth property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsHealth(String value) {
		this.isHealth = value;
	}

	/**
	 * Gets the value of the isPayAlimony property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsPayAlimony() {
		return isPayAlimony;
	}

	/**
	 * Sets the value of the isPayAlimony property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsPayAlimony(String value) {
		this.isPayAlimony = value;
	}

	/**
	 * Gets the value of the isPresent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsPresent() {
		return isPresent;
	}

	/**
	 * Sets the value of the isPresent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsPresent(String value) {
		this.isPresent = value;
	}

	/**
	 * Gets the value of the isSameCity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsSameCity() {
		return isSameCity;
	}

	/**
	 * Sets the value of the isSameCity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsSameCity(String value) {
		this.isSameCity = value;
	}

	/**
	 * Gets the value of the jobStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * Sets the value of the jobStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setJobStatus(String value) {
		this.jobStatus = value;
	}

	/**
	 * Gets the value of the jobType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * Sets the value of the jobType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setJobType(String value) {
		this.jobType = value;
	}

	/**
	 * Gets the value of the memo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the value of the memo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMemo(String value) {
		this.memo = value;
	}

	/**
	 * Gets the value of the mortgagorId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getMortgagorId() {
		return mortgagorId;
	}

	/**
	 * Sets the value of the mortgagorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setMortgagorId(Long value) {
		this.mortgagorId = value;
	}

	/**
	 * Gets the value of the mortgagorRelativesId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getMortgagorRelativesId() {
		return mortgagorRelativesId;
	}

	/**
	 * Sets the value of the mortgagorRelativesId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setMortgagorRelativesId(Long value) {
		this.mortgagorRelativesId = value;
	}

	/**
	 * Gets the value of the nm property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNm() {
		return nm;
	}

	/**
	 * Sets the value of the nm property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNm(String value) {
		this.nm = value;
	}

	/**
	 * Gets the value of the operDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOperDate() {
		return operDate;
	}

	/**
	 * Sets the value of the operDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOperDate(String value) {
		this.operDate = value;
	}

	/**
	 * Gets the value of the operator property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getOperator() {
		return operator;
	}

	/**
	 * Sets the value of the operator property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setOperator(Long value) {
		this.operator = value;
	}

	/**
	 * Gets the value of the organization property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * Sets the value of the organization property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganization(String value) {
		this.organization = value;
	}

	/**
	 * Gets the value of the organizationTel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganizationTel() {
		return organizationTel;
	}

	/**
	 * Sets the value of the organizationTel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganizationTel(String value) {
		this.organizationTel = value;
	}

	/**
	 * Gets the value of the relativeDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRelativeDesc() {
		return relativeDesc;
	}

	/**
	 * Sets the value of the relativeDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRelativeDesc(String value) {
		this.relativeDesc = value;
	}

	/**
	 * Gets the value of the repayment property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getRepayment() {
		return repayment;
	}

	/**
	 * Sets the value of the repayment property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setRepayment(BigDecimal value) {
		this.repayment = value;
	}

	/**
	 * Gets the value of the telNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * Sets the value of the telNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelNum(String value) {
		this.telNum = value;
	}

}

package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for mortgagorContact complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorContact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="contactId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contentSourceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="investigationRepId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="summaryDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telAreaCode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telDistNum2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telNum1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telNum2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telSourceDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telphoneType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorContact", propOrder = { "address", "age",
		"contactId", "contentSourceType", "contentType", "createDate",
		"creator", "gender", "investigationRepId", "memo", "mobile",
		"mortgagorId", "nm", "operDate", "operator", "organization",
		"relationship", "remark", "seqNo", "summaryDesc", "telAreaCode1",
		"telDistNum2", "telLoc", "telNum1", "telNum2", "telRemark",
		"telSource", "telSourceDesc", "telphoneType", "title", "workReference" })
public class MortgagorContact {

	protected String address;
	protected Integer age;
	protected Long contactId;
	protected String contentSourceType;
	protected String contentType;
	protected String createDate;
	protected Long creator;
	protected String gender;
	protected Long investigationRepId;
	protected String memo;
	protected String mobile;
	protected Long mortgagorId;
	protected String nm;
	protected String operDate;
	protected Long operator;
	protected String organization;
	protected String relationship;
	protected String remark;
	protected Integer seqNo;
	protected String summaryDesc;
	protected String telAreaCode1;
	protected String telDistNum2;
	protected String telLoc;
	protected String telNum1;
	protected String telNum2;
	protected String telRemark;
	protected String telSource;
	protected String telSourceDesc;
	protected String telphoneType;
	protected String title;
	protected String workReference;

	/**
	 * Gets the value of the address property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the value of the address property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddress(String value) {
		this.address = value;
	}

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
	 * Gets the value of the contactId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getContactId() {
		return contactId;
	}

	/**
	 * Sets the value of the contactId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setContactId(Long value) {
		this.contactId = value;
	}

	/**
	 * Gets the value of the contentSourceType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentSourceType() {
		return contentSourceType;
	}

	/**
	 * Sets the value of the contentSourceType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentSourceType(String value) {
		this.contentSourceType = value;
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
	 * Gets the value of the gender property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the value of the gender property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGender(String value) {
		this.gender = value;
	}

	/**
	 * Gets the value of the investigationRepId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getInvestigationRepId() {
		return investigationRepId;
	}

	/**
	 * Sets the value of the investigationRepId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setInvestigationRepId(Long value) {
		this.investigationRepId = value;
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
	 * Gets the value of the mobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobile(String value) {
		this.mobile = value;
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
	 * Gets the value of the relationship property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * Sets the value of the relationship property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRelationship(String value) {
		this.relationship = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemark(String value) {
		this.remark = value;
	}

	/**
	 * Gets the value of the seqNo property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSeqNo() {
		return seqNo;
	}

	/**
	 * Sets the value of the seqNo property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSeqNo(Integer value) {
		this.seqNo = value;
	}

	/**
	 * Gets the value of the summaryDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSummaryDesc() {
		return summaryDesc;
	}

	/**
	 * Sets the value of the summaryDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSummaryDesc(String value) {
		this.summaryDesc = value;
	}

	/**
	 * Gets the value of the telAreaCode1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelAreaCode1() {
		return telAreaCode1;
	}

	/**
	 * Sets the value of the telAreaCode1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelAreaCode1(String value) {
		this.telAreaCode1 = value;
	}

	/**
	 * Gets the value of the telDistNum2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelDistNum2() {
		return telDistNum2;
	}

	/**
	 * Sets the value of the telDistNum2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelDistNum2(String value) {
		this.telDistNum2 = value;
	}

	/**
	 * Gets the value of the telLoc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelLoc() {
		return telLoc;
	}

	/**
	 * Sets the value of the telLoc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelLoc(String value) {
		this.telLoc = value;
	}

	/**
	 * Gets the value of the telNum1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelNum1() {
		return telNum1;
	}

	/**
	 * Sets the value of the telNum1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelNum1(String value) {
		this.telNum1 = value;
	}

	/**
	 * Gets the value of the telNum2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelNum2() {
		return telNum2;
	}

	/**
	 * Sets the value of the telNum2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelNum2(String value) {
		this.telNum2 = value;
	}

	/**
	 * Gets the value of the telRemark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelRemark() {
		return telRemark;
	}

	/**
	 * Sets the value of the telRemark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelRemark(String value) {
		this.telRemark = value;
	}

	/**
	 * Gets the value of the telSource property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelSource() {
		return telSource;
	}

	/**
	 * Sets the value of the telSource property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelSource(String value) {
		this.telSource = value;
	}

	/**
	 * Gets the value of the telSourceDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelSourceDesc() {
		return telSourceDesc;
	}

	/**
	 * Sets the value of the telSourceDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelSourceDesc(String value) {
		this.telSourceDesc = value;
	}

	/**
	 * Gets the value of the telphoneType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelphoneType() {
		return telphoneType;
	}

	/**
	 * Sets the value of the telphoneType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelphoneType(String value) {
		this.telphoneType = value;
	}

	/**
	 * Gets the value of the title property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the value of the title property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * Gets the value of the workReference property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorkReference() {
		return workReference;
	}

	/**
	 * Sets the value of the workReference property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorkReference(String value) {
		this.workReference = value;
	}

}

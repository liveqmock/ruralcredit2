package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for borrowerInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="borrowerInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="birthday" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowerInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="domicileAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicileCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicileDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicileProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="historyFlag" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idValidityDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intoPiecesId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="marriage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxDiploma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="postCd1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postCd2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentTelAreacode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentTelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "borrowerInfo", propOrder = { "birthday", "borrowerInfoId",
		"clientId", "createDate", "creator", "customerId", "domicileAddr",
		"domicileCity", "domicileDistrict", "domicileProvince", "email",
		"formerName", "gender", "historyFlag", "idNumber", "idType",
		"idValidityDate", "imAccount", "intoPiecesId", "marriage",
		"maxDiploma", "memo", "mobile", "mortgagorName", "mortgagorType",
		"operDate", "operator", "postCd1", "postCd2", "residentAddr",
		"residentCity", "residentDistrict", "residentProvince",
		"residentTelAreacode", "residentTelNumber" })
public class BorrowerInfo {

	protected String birthday;
	protected Long borrowerInfoId;
	protected Long clientId;
	protected String createDate;
	protected Long creator;
	protected Long customerId;
	protected String domicileAddr;
	protected String domicileCity;
	protected String domicileDistrict;
	protected String domicileProvince;
	protected String email;
	protected String formerName;
	protected String gender;
	protected Short historyFlag;
	protected String idNumber;
	protected String idType;
	protected String idValidityDate;
	protected String imAccount;
	protected Long intoPiecesId;
	protected String marriage;
	protected String maxDiploma;
	protected String memo;
	protected String mobile;
	protected String mortgagorName;
	protected String mortgagorType;
	protected String operDate;
	protected Long operator;
	protected String postCd1;
	protected String postCd2;
	protected String residentAddr;
	protected String residentCity;
	protected String residentDistrict;
	protected String residentProvince;
	protected String residentTelAreacode;
	protected String residentTelNumber;

	/**
	 * Gets the value of the birthday property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Sets the value of the birthday property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBirthday(String value) {
		this.birthday = value;
	}

	/**
	 * Gets the value of the borrowerInfoId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}

	/**
	 * Sets the value of the borrowerInfoId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setBorrowerInfoId(Long value) {
		this.borrowerInfoId = value;
	}

	/**
	 * Gets the value of the clientId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * Sets the value of the clientId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setClientId(Long value) {
		this.clientId = value;
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
	 * Gets the value of the customerId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the value of the customerId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setCustomerId(Long value) {
		this.customerId = value;
	}

	/**
	 * Gets the value of the domicileAddr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDomicileAddr() {
		return domicileAddr;
	}

	/**
	 * Sets the value of the domicileAddr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDomicileAddr(String value) {
		this.domicileAddr = value;
	}

	/**
	 * Gets the value of the domicileCity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDomicileCity() {
		return domicileCity;
	}

	/**
	 * Sets the value of the domicileCity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDomicileCity(String value) {
		this.domicileCity = value;
	}

	/**
	 * Gets the value of the domicileDistrict property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDomicileDistrict() {
		return domicileDistrict;
	}

	/**
	 * Sets the value of the domicileDistrict property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDomicileDistrict(String value) {
		this.domicileDistrict = value;
	}

	/**
	 * Gets the value of the domicileProvince property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDomicileProvince() {
		return domicileProvince;
	}

	/**
	 * Sets the value of the domicileProvince property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDomicileProvince(String value) {
		this.domicileProvince = value;
	}

	/**
	 * Gets the value of the email property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the formerName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFormerName() {
		return formerName;
	}

	/**
	 * Sets the value of the formerName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFormerName(String value) {
		this.formerName = value;
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
	 * Gets the value of the historyFlag property.
	 * 
	 * @return possible object is {@link Short }
	 * 
	 */
	public Short getHistoryFlag() {
		return historyFlag;
	}

	/**
	 * Sets the value of the historyFlag property.
	 * 
	 * @param value
	 *            allowed object is {@link Short }
	 * 
	 */
	public void setHistoryFlag(Short value) {
		this.historyFlag = value;
	}

	/**
	 * Gets the value of the idNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * Sets the value of the idNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdNumber(String value) {
		this.idNumber = value;
	}

	/**
	 * Gets the value of the idType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * Sets the value of the idType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdType(String value) {
		this.idType = value;
	}

	/**
	 * Gets the value of the idValidityDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdValidityDate() {
		return idValidityDate;
	}

	/**
	 * Sets the value of the idValidityDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdValidityDate(String value) {
		this.idValidityDate = value;
	}

	/**
	 * Gets the value of the imAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImAccount() {
		return imAccount;
	}

	/**
	 * Sets the value of the imAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImAccount(String value) {
		this.imAccount = value;
	}

	/**
	 * Gets the value of the intoPiecesId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getIntoPiecesId() {
		return intoPiecesId;
	}

	/**
	 * Sets the value of the intoPiecesId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setIntoPiecesId(Long value) {
		this.intoPiecesId = value;
	}

	/**
	 * Gets the value of the marriage property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMarriage() {
		return marriage;
	}

	/**
	 * Sets the value of the marriage property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMarriage(String value) {
		this.marriage = value;
	}

	/**
	 * Gets the value of the maxDiploma property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMaxDiploma() {
		return maxDiploma;
	}

	/**
	 * Sets the value of the maxDiploma property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMaxDiploma(String value) {
		this.maxDiploma = value;
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
	 * Gets the value of the mortgagorName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMortgagorName() {
		return mortgagorName;
	}

	/**
	 * Sets the value of the mortgagorName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMortgagorName(String value) {
		this.mortgagorName = value;
	}

	/**
	 * Gets the value of the mortgagorType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMortgagorType() {
		return mortgagorType;
	}

	/**
	 * Sets the value of the mortgagorType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMortgagorType(String value) {
		this.mortgagorType = value;
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
	 * Gets the value of the postCd1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPostCd1() {
		return postCd1;
	}

	/**
	 * Sets the value of the postCd1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPostCd1(String value) {
		this.postCd1 = value;
	}

	/**
	 * Gets the value of the postCd2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPostCd2() {
		return postCd2;
	}

	/**
	 * Sets the value of the postCd2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPostCd2(String value) {
		this.postCd2 = value;
	}

	/**
	 * Gets the value of the residentAddr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResidentAddr() {
		return residentAddr;
	}

	/**
	 * Sets the value of the residentAddr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResidentAddr(String value) {
		this.residentAddr = value;
	}

	/**
	 * Gets the value of the residentCity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResidentCity() {
		return residentCity;
	}

	/**
	 * Sets the value of the residentCity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResidentCity(String value) {
		this.residentCity = value;
	}

	/**
	 * Gets the value of the residentDistrict property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResidentDistrict() {
		return residentDistrict;
	}

	/**
	 * Sets the value of the residentDistrict property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResidentDistrict(String value) {
		this.residentDistrict = value;
	}

	/**
	 * Gets the value of the residentProvince property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResidentProvince() {
		return residentProvince;
	}

	/**
	 * Sets the value of the residentProvince property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResidentProvince(String value) {
		this.residentProvince = value;
	}

	/**
	 * Gets the value of the residentTelAreacode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResidentTelAreacode() {
		return residentTelAreacode;
	}

	/**
	 * Sets the value of the residentTelAreacode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResidentTelAreacode(String value) {
		this.residentTelAreacode = value;
	}

	/**
	 * Gets the value of the residentTelNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResidentTelNumber() {
		return residentTelNumber;
	}

	/**
	 * Sets the value of the residentTelNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResidentTelNumber(String value) {
		this.residentTelNumber = value;
	}

}

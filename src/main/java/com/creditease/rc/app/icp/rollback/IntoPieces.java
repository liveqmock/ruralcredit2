package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for intoPieces complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="intoPieces">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyInstalments" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="directlyDeptNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="intoPiecesId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isExpress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="knownCeMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanCategory" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="loanPurposeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanPurposeOne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanPurposeTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="overAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repaymentMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="revolvCredit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellUserId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sellUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceUserId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="serviceUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitDeptNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="systemSourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "intoPieces", propOrder = { "applyInstalments", "createDate",
		"creator", "directlyDeptNo", "intoPiecesId", "isExpress", "isInterest",
		"knownCeMethod", "loanCategory", "loanPurposeDesc", "loanPurposeOne",
		"loanPurposeTwo", "loanType", "memo", "operDate", "operator",
		"overAmount", "productType", "repaymentMode", "revolvCredit",
		"sellUserId", "sellUserName", "serviceUserId", "serviceUserName",
		"submitDeptName", "submitDeptNo", "systemSourceName", "transportDate",
		"transportId" })
public class IntoPieces {

	protected Integer applyInstalments;
	protected String createDate;
	protected Long creator;
	protected Long directlyDeptNo;
	protected Long intoPiecesId;
	protected String isExpress;
	protected String isInterest;
	protected String knownCeMethod;
	protected Short loanCategory;
	protected String loanPurposeDesc;
	protected String loanPurposeOne;
	protected String loanPurposeTwo;
	protected String loanType;
	protected String memo;
	protected String operDate;
	protected Long operator;
	protected BigDecimal overAmount;
	protected String productType;
	protected String repaymentMode;
	protected String revolvCredit;
	protected Long sellUserId;
	protected String sellUserName;
	protected Long serviceUserId;
	protected String serviceUserName;
	protected String submitDeptName;
	protected Long submitDeptNo;
	protected String systemSourceName;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar transportDate;
	protected Long transportId;

	/**
	 * Gets the value of the applyInstalments property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getApplyInstalments() {
		return applyInstalments;
	}

	/**
	 * Sets the value of the applyInstalments property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setApplyInstalments(Integer value) {
		this.applyInstalments = value;
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
	 * Gets the value of the directlyDeptNo property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getDirectlyDeptNo() {
		return directlyDeptNo;
	}

	/**
	 * Sets the value of the directlyDeptNo property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setDirectlyDeptNo(Long value) {
		this.directlyDeptNo = value;
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
	 * Gets the value of the isExpress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsExpress() {
		return isExpress;
	}

	/**
	 * Sets the value of the isExpress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsExpress(String value) {
		this.isExpress = value;
	}

	/**
	 * Gets the value of the isInterest property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsInterest() {
		return isInterest;
	}

	/**
	 * Sets the value of the isInterest property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsInterest(String value) {
		this.isInterest = value;
	}

	/**
	 * Gets the value of the knownCeMethod property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKnownCeMethod() {
		return knownCeMethod;
	}

	/**
	 * Sets the value of the knownCeMethod property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKnownCeMethod(String value) {
		this.knownCeMethod = value;
	}

	/**
	 * Gets the value of the loanCategory property.
	 * 
	 * @return possible object is {@link Short }
	 * 
	 */
	public Short getLoanCategory() {
		return loanCategory;
	}

	/**
	 * Sets the value of the loanCategory property.
	 * 
	 * @param value
	 *            allowed object is {@link Short }
	 * 
	 */
	public void setLoanCategory(Short value) {
		this.loanCategory = value;
	}

	/**
	 * Gets the value of the loanPurposeDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoanPurposeDesc() {
		return loanPurposeDesc;
	}

	/**
	 * Sets the value of the loanPurposeDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoanPurposeDesc(String value) {
		this.loanPurposeDesc = value;
	}

	/**
	 * Gets the value of the loanPurposeOne property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoanPurposeOne() {
		return loanPurposeOne;
	}

	/**
	 * Sets the value of the loanPurposeOne property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoanPurposeOne(String value) {
		this.loanPurposeOne = value;
	}

	/**
	 * Gets the value of the loanPurposeTwo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoanPurposeTwo() {
		return loanPurposeTwo;
	}

	/**
	 * Sets the value of the loanPurposeTwo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoanPurposeTwo(String value) {
		this.loanPurposeTwo = value;
	}

	/**
	 * Gets the value of the loanType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoanType() {
		return loanType;
	}

	/**
	 * Sets the value of the loanType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoanType(String value) {
		this.loanType = value;
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
	 * Gets the value of the overAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getOverAmount() {
		return overAmount;
	}

	/**
	 * Sets the value of the overAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setOverAmount(BigDecimal value) {
		this.overAmount = value;
	}

	/**
	 * Gets the value of the productType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * Sets the value of the productType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductType(String value) {
		this.productType = value;
	}

	/**
	 * Gets the value of the repaymentMode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRepaymentMode() {
		return repaymentMode;
	}

	/**
	 * Sets the value of the repaymentMode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRepaymentMode(String value) {
		this.repaymentMode = value;
	}

	/**
	 * Gets the value of the revolvCredit property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRevolvCredit() {
		return revolvCredit;
	}

	/**
	 * Sets the value of the revolvCredit property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRevolvCredit(String value) {
		this.revolvCredit = value;
	}

	/**
	 * Gets the value of the sellUserId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSellUserId() {
		return sellUserId;
	}

	/**
	 * Sets the value of the sellUserId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSellUserId(Long value) {
		this.sellUserId = value;
	}

	/**
	 * Gets the value of the sellUserName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSellUserName() {
		return sellUserName;
	}

	/**
	 * Sets the value of the sellUserName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSellUserName(String value) {
		this.sellUserName = value;
	}

	/**
	 * Gets the value of the serviceUserId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getServiceUserId() {
		return serviceUserId;
	}

	/**
	 * Sets the value of the serviceUserId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setServiceUserId(Long value) {
		this.serviceUserId = value;
	}

	/**
	 * Gets the value of the serviceUserName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getServiceUserName() {
		return serviceUserName;
	}

	/**
	 * Sets the value of the serviceUserName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setServiceUserName(String value) {
		this.serviceUserName = value;
	}

	/**
	 * Gets the value of the submitDeptName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubmitDeptName() {
		return submitDeptName;
	}

	/**
	 * Sets the value of the submitDeptName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubmitDeptName(String value) {
		this.submitDeptName = value;
	}

	/**
	 * Gets the value of the submitDeptNo property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSubmitDeptNo() {
		return submitDeptNo;
	}

	/**
	 * Sets the value of the submitDeptNo property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSubmitDeptNo(Long value) {
		this.submitDeptNo = value;
	}

	/**
	 * Gets the value of the systemSourceName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSystemSourceName() {
		return systemSourceName;
	}

	/**
	 * Sets the value of the systemSourceName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSystemSourceName(String value) {
		this.systemSourceName = value;
	}

	/**
	 * Gets the value of the transportDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTransportDate() {
		return transportDate;
	}

	/**
	 * Sets the value of the transportDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTransportDate(XMLGregorianCalendar value) {
		this.transportDate = value;
	}

	/**
	 * Gets the value of the transportId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getTransportId() {
		return transportId;
	}

	/**
	 * Sets the value of the transportId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setTransportId(Long value) {
		this.transportId = value;
	}

}

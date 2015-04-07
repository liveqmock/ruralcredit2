package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for mortgagorPersonalty complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorPersonalty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="assestCate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeAgainstAssets" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="conversionRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPledge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isVerification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maturityDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="personaltyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pledgeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="pledgeExpireDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="providedContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorPersonalty", propOrder = { "assest", "assestCate",
		"assestType", "chargeAgainstAssets", "conversionRate", "createDate",
		"creator", "description", "isPledge", "isVerification", "maturityDate",
		"memo", "mortgagorId", "operDate", "operator", "personaltyId",
		"pledgeAmount", "pledgeExpireDate", "providedContent", "seqNo",
		"totalAmount" })
public class MortgagorPersonalty {

	protected BigDecimal assest;
	protected String assestCate;
	protected String assestType;
	protected BigDecimal chargeAgainstAssets;
	protected BigDecimal conversionRate;
	protected String createDate;
	protected Long creator;
	protected String description;
	protected String isPledge;
	protected String isVerification;
	protected String maturityDate;
	protected String memo;
	protected Long mortgagorId;
	protected String operDate;
	protected Long operator;
	protected Long personaltyId;
	protected BigDecimal pledgeAmount;
	protected String pledgeExpireDate;
	protected String providedContent;
	protected Integer seqNo;
	protected BigDecimal totalAmount;

	/**
	 * Gets the value of the assest property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getAssest() {
		return assest;
	}

	/**
	 * Sets the value of the assest property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setAssest(BigDecimal value) {
		this.assest = value;
	}

	/**
	 * Gets the value of the assestCate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssestCate() {
		return assestCate;
	}

	/**
	 * Sets the value of the assestCate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssestCate(String value) {
		this.assestCate = value;
	}

	/**
	 * Gets the value of the assestType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssestType() {
		return assestType;
	}

	/**
	 * Sets the value of the assestType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssestType(String value) {
		this.assestType = value;
	}

	/**
	 * Gets the value of the chargeAgainstAssets property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getChargeAgainstAssets() {
		return chargeAgainstAssets;
	}

	/**
	 * Sets the value of the chargeAgainstAssets property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setChargeAgainstAssets(BigDecimal value) {
		this.chargeAgainstAssets = value;
	}

	/**
	 * Gets the value of the conversionRate property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	/**
	 * Sets the value of the conversionRate property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setConversionRate(BigDecimal value) {
		this.conversionRate = value;
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
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the isPledge property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsPledge() {
		return isPledge;
	}

	/**
	 * Sets the value of the isPledge property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsPledge(String value) {
		this.isPledge = value;
	}

	/**
	 * Gets the value of the isVerification property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsVerification() {
		return isVerification;
	}

	/**
	 * Sets the value of the isVerification property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsVerification(String value) {
		this.isVerification = value;
	}

	/**
	 * Gets the value of the maturityDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMaturityDate() {
		return maturityDate;
	}

	/**
	 * Sets the value of the maturityDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMaturityDate(String value) {
		this.maturityDate = value;
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
	 * Gets the value of the personaltyId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getPersonaltyId() {
		return personaltyId;
	}

	/**
	 * Sets the value of the personaltyId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setPersonaltyId(Long value) {
		this.personaltyId = value;
	}

	/**
	 * Gets the value of the pledgeAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPledgeAmount() {
		return pledgeAmount;
	}

	/**
	 * Sets the value of the pledgeAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPledgeAmount(BigDecimal value) {
		this.pledgeAmount = value;
	}

	/**
	 * Gets the value of the pledgeExpireDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPledgeExpireDate() {
		return pledgeExpireDate;
	}

	/**
	 * Sets the value of the pledgeExpireDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPledgeExpireDate(String value) {
		this.pledgeExpireDate = value;
	}

	/**
	 * Gets the value of the providedContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProvidedContent() {
		return providedContent;
	}

	/**
	 * Sets the value of the providedContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProvidedContent(String value) {
		this.providedContent = value;
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
	 * Gets the value of the totalAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets the value of the totalAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setTotalAmount(BigDecimal value) {
		this.totalAmount = value;
	}

}

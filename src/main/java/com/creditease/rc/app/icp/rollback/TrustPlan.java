package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for trustPlan complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="trustPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="attachmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exchangeCycle" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="exchangeTerm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fullAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="iconClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="impelRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="initAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="initMatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="interestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="investEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="investStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="irr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isRound" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keepFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="keepRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="lawyerFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="minLastAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="organBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="organName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="planId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="planName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planTerm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="priorityScopeAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reAddScope" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="reAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnMatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scopeAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="secondScopeAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thirdNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verifyOper" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="verifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="yield" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trustPlan", propOrder = { "accountName", "accountNo",
		"attachmentId", "createTime", "creator", "displayName",
		"exchangeCycle", "exchangeTerm", "fullAmt", "iconClass", "impelRate",
		"initAmt", "initMatchId", "interest", "interestDate", "investEndDate",
		"investStartDate", "irr", "isAdd", "isReturn", "isRound", "keepFee",
		"keepRate", "lawyerFee", "minLastAmt", "modifyTime", "operator",
		"organBankName", "organId", "organName", "payRule", "planCode",
		"planCreateTime", "planId", "planName", "planTerm", "priorityScopeAmt",
		"productId", "productName", "reAddScope", "reAmt", "remark",
		"returnMatchId", "returnRule", "scopeAmt", "secondScopeAmt", "sort",
		"state", "subAccountName", "sysStatus", "thirdNo", "verifyOper",
		"verifyTime", "version", "yield" })
public class TrustPlan {

	protected String accountName;
	protected BigDecimal accountNo;
	protected String attachmentId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	protected BigDecimal creator;
	protected String displayName;
	protected Integer exchangeCycle;
	protected Integer exchangeTerm;
	protected BigDecimal fullAmt;
	protected String iconClass;
	protected BigDecimal impelRate;
	protected BigDecimal initAmt;
	protected String initMatchId;
	protected BigDecimal interest;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar interestDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar investEndDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar investStartDate;
	protected BigDecimal irr;
	protected String isAdd;
	protected String isReturn;
	protected String isRound;
	protected BigDecimal keepFee;
	protected BigDecimal keepRate;
	protected BigDecimal lawyerFee;
	protected BigDecimal minLastAmt;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modifyTime;
	protected BigDecimal operator;
	protected String organBankName;
	protected BigDecimal organId;
	protected String organName;
	protected String payRule;
	protected String planCode;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar planCreateTime;
	protected BigDecimal planId;
	protected String planName;
	protected Integer planTerm;
	protected BigDecimal priorityScopeAmt;
	protected BigDecimal productId;
	protected String productName;
	protected BigDecimal reAddScope;
	protected BigDecimal reAmt;
	protected String remark;
	protected String returnMatchId;
	protected String returnRule;
	protected BigDecimal scopeAmt;
	protected BigDecimal secondScopeAmt;
	protected BigDecimal sort;
	protected String state;
	protected String subAccountName;
	protected String sysStatus;
	protected String thirdNo;
	protected Long verifyOper;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar verifyTime;
	protected Long version;
	protected BigDecimal yield;

	/**
	 * Gets the value of the accountName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Sets the value of the accountName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountName(String value) {
		this.accountName = value;
	}

	/**
	 * Gets the value of the accountNo property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getAccountNo() {
		return accountNo;
	}

	/**
	 * Sets the value of the accountNo property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setAccountNo(BigDecimal value) {
		this.accountNo = value;
	}

	/**
	 * Gets the value of the attachmentId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentId() {
		return attachmentId;
	}

	/**
	 * Sets the value of the attachmentId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentId(String value) {
		this.attachmentId = value;
	}

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateTime(XMLGregorianCalendar value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the creator property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getCreator() {
		return creator;
	}

	/**
	 * Sets the value of the creator property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setCreator(BigDecimal value) {
		this.creator = value;
	}

	/**
	 * Gets the value of the displayName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the value of the displayName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDisplayName(String value) {
		this.displayName = value;
	}

	/**
	 * Gets the value of the exchangeCycle property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getExchangeCycle() {
		return exchangeCycle;
	}

	/**
	 * Sets the value of the exchangeCycle property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setExchangeCycle(Integer value) {
		this.exchangeCycle = value;
	}

	/**
	 * Gets the value of the exchangeTerm property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getExchangeTerm() {
		return exchangeTerm;
	}

	/**
	 * Sets the value of the exchangeTerm property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setExchangeTerm(Integer value) {
		this.exchangeTerm = value;
	}

	/**
	 * Gets the value of the fullAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getFullAmt() {
		return fullAmt;
	}

	/**
	 * Sets the value of the fullAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setFullAmt(BigDecimal value) {
		this.fullAmt = value;
	}

	/**
	 * Gets the value of the iconClass property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIconClass() {
		return iconClass;
	}

	/**
	 * Sets the value of the iconClass property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIconClass(String value) {
		this.iconClass = value;
	}

	/**
	 * Gets the value of the impelRate property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getImpelRate() {
		return impelRate;
	}

	/**
	 * Sets the value of the impelRate property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setImpelRate(BigDecimal value) {
		this.impelRate = value;
	}

	/**
	 * Gets the value of the initAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	/**
	 * Sets the value of the initAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setInitAmt(BigDecimal value) {
		this.initAmt = value;
	}

	/**
	 * Gets the value of the initMatchId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInitMatchId() {
		return initMatchId;
	}

	/**
	 * Sets the value of the initMatchId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInitMatchId(String value) {
		this.initMatchId = value;
	}

	/**
	 * Gets the value of the interest property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getInterest() {
		return interest;
	}

	/**
	 * Sets the value of the interest property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setInterest(BigDecimal value) {
		this.interest = value;
	}

	/**
	 * Gets the value of the interestDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getInterestDate() {
		return interestDate;
	}

	/**
	 * Sets the value of the interestDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setInterestDate(XMLGregorianCalendar value) {
		this.interestDate = value;
	}

	/**
	 * Gets the value of the investEndDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getInvestEndDate() {
		return investEndDate;
	}

	/**
	 * Sets the value of the investEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setInvestEndDate(XMLGregorianCalendar value) {
		this.investEndDate = value;
	}

	/**
	 * Gets the value of the investStartDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getInvestStartDate() {
		return investStartDate;
	}

	/**
	 * Sets the value of the investStartDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setInvestStartDate(XMLGregorianCalendar value) {
		this.investStartDate = value;
	}

	/**
	 * Gets the value of the irr property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getIrr() {
		return irr;
	}

	/**
	 * Sets the value of the irr property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setIrr(BigDecimal value) {
		this.irr = value;
	}

	/**
	 * Gets the value of the isAdd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsAdd() {
		return isAdd;
	}

	/**
	 * Sets the value of the isAdd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsAdd(String value) {
		this.isAdd = value;
	}

	/**
	 * Gets the value of the isReturn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsReturn() {
		return isReturn;
	}

	/**
	 * Sets the value of the isReturn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsReturn(String value) {
		this.isReturn = value;
	}

	/**
	 * Gets the value of the isRound property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsRound() {
		return isRound;
	}

	/**
	 * Sets the value of the isRound property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsRound(String value) {
		this.isRound = value;
	}

	/**
	 * Gets the value of the keepFee property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getKeepFee() {
		return keepFee;
	}

	/**
	 * Sets the value of the keepFee property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setKeepFee(BigDecimal value) {
		this.keepFee = value;
	}

	/**
	 * Gets the value of the keepRate property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getKeepRate() {
		return keepRate;
	}

	/**
	 * Sets the value of the keepRate property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setKeepRate(BigDecimal value) {
		this.keepRate = value;
	}

	/**
	 * Gets the value of the lawyerFee property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getLawyerFee() {
		return lawyerFee;
	}

	/**
	 * Sets the value of the lawyerFee property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setLawyerFee(BigDecimal value) {
		this.lawyerFee = value;
	}

	/**
	 * Gets the value of the minLastAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getMinLastAmt() {
		return minLastAmt;
	}

	/**
	 * Sets the value of the minLastAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setMinLastAmt(BigDecimal value) {
		this.minLastAmt = value;
	}

	/**
	 * Gets the value of the modifyTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getModifyTime() {
		return modifyTime;
	}

	/**
	 * Sets the value of the modifyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setModifyTime(XMLGregorianCalendar value) {
		this.modifyTime = value;
	}

	/**
	 * Gets the value of the operator property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getOperator() {
		return operator;
	}

	/**
	 * Sets the value of the operator property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setOperator(BigDecimal value) {
		this.operator = value;
	}

	/**
	 * Gets the value of the organBankName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganBankName() {
		return organBankName;
	}

	/**
	 * Sets the value of the organBankName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganBankName(String value) {
		this.organBankName = value;
	}

	/**
	 * Gets the value of the organId property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getOrganId() {
		return organId;
	}

	/**
	 * Sets the value of the organId property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setOrganId(BigDecimal value) {
		this.organId = value;
	}

	/**
	 * Gets the value of the organName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * Sets the value of the organName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganName(String value) {
		this.organName = value;
	}

	/**
	 * Gets the value of the payRule property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayRule() {
		return payRule;
	}

	/**
	 * Sets the value of the payRule property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayRule(String value) {
		this.payRule = value;
	}

	/**
	 * Gets the value of the planCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPlanCode() {
		return planCode;
	}

	/**
	 * Sets the value of the planCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlanCode(String value) {
		this.planCode = value;
	}

	/**
	 * Gets the value of the planCreateTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getPlanCreateTime() {
		return planCreateTime;
	}

	/**
	 * Sets the value of the planCreateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setPlanCreateTime(XMLGregorianCalendar value) {
		this.planCreateTime = value;
	}

	/**
	 * Gets the value of the planId property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPlanId() {
		return planId;
	}

	/**
	 * Sets the value of the planId property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPlanId(BigDecimal value) {
		this.planId = value;
	}

	/**
	 * Gets the value of the planName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * Sets the value of the planName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlanName(String value) {
		this.planName = value;
	}

	/**
	 * Gets the value of the planTerm property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPlanTerm() {
		return planTerm;
	}

	/**
	 * Sets the value of the planTerm property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPlanTerm(Integer value) {
		this.planTerm = value;
	}

	/**
	 * Gets the value of the priorityScopeAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPriorityScopeAmt() {
		return priorityScopeAmt;
	}

	/**
	 * Sets the value of the priorityScopeAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPriorityScopeAmt(BigDecimal value) {
		this.priorityScopeAmt = value;
	}

	/**
	 * Gets the value of the productId property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getProductId() {
		return productId;
	}

	/**
	 * Sets the value of the productId property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setProductId(BigDecimal value) {
		this.productId = value;
	}

	/**
	 * Gets the value of the productName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the value of the productName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductName(String value) {
		this.productName = value;
	}

	/**
	 * Gets the value of the reAddScope property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getReAddScope() {
		return reAddScope;
	}

	/**
	 * Sets the value of the reAddScope property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setReAddScope(BigDecimal value) {
		this.reAddScope = value;
	}

	/**
	 * Gets the value of the reAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getReAmt() {
		return reAmt;
	}

	/**
	 * Sets the value of the reAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setReAmt(BigDecimal value) {
		this.reAmt = value;
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
	 * Gets the value of the returnMatchId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnMatchId() {
		return returnMatchId;
	}

	/**
	 * Sets the value of the returnMatchId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnMatchId(String value) {
		this.returnMatchId = value;
	}

	/**
	 * Gets the value of the returnRule property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnRule() {
		return returnRule;
	}

	/**
	 * Sets the value of the returnRule property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnRule(String value) {
		this.returnRule = value;
	}

	/**
	 * Gets the value of the scopeAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getScopeAmt() {
		return scopeAmt;
	}

	/**
	 * Sets the value of the scopeAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setScopeAmt(BigDecimal value) {
		this.scopeAmt = value;
	}

	/**
	 * Gets the value of the secondScopeAmt property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getSecondScopeAmt() {
		return secondScopeAmt;
	}

	/**
	 * Sets the value of the secondScopeAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setSecondScopeAmt(BigDecimal value) {
		this.secondScopeAmt = value;
	}

	/**
	 * Gets the value of the sort property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getSort() {
		return sort;
	}

	/**
	 * Sets the value of the sort property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setSort(BigDecimal value) {
		this.sort = value;
	}

	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setState(String value) {
		this.state = value;
	}

	/**
	 * Gets the value of the subAccountName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubAccountName() {
		return subAccountName;
	}

	/**
	 * Sets the value of the subAccountName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubAccountName(String value) {
		this.subAccountName = value;
	}

	/**
	 * Gets the value of the sysStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSysStatus() {
		return sysStatus;
	}

	/**
	 * Sets the value of the sysStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSysStatus(String value) {
		this.sysStatus = value;
	}

	/**
	 * Gets the value of the thirdNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getThirdNo() {
		return thirdNo;
	}

	/**
	 * Sets the value of the thirdNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setThirdNo(String value) {
		this.thirdNo = value;
	}

	/**
	 * Gets the value of the verifyOper property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getVerifyOper() {
		return verifyOper;
	}

	/**
	 * Sets the value of the verifyOper property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setVerifyOper(Long value) {
		this.verifyOper = value;
	}

	/**
	 * Gets the value of the verifyTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getVerifyTime() {
		return verifyTime;
	}

	/**
	 * Sets the value of the verifyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setVerifyTime(XMLGregorianCalendar value) {
		this.verifyTime = value;
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setVersion(Long value) {
		this.version = value;
	}

	/**
	 * Gets the value of the yield property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getYield() {
		return yield;
	}

	/**
	 * Sets the value of the yield property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setYield(BigDecimal value) {
		this.yield = value;
	}

}

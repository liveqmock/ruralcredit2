package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for bsTransport complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="bsTransport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acceptMothRepay" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="applyMaxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="applyMinAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="assignFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brandModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bsApplyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="bsTransportId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="courseTerm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="destineTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="inspectionResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intoPiecesId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isChuding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isRepeatApply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isYizhi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lenssonStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="loanPlans" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="loanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longRepaymentTerm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="moneySource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherMoneySource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outletCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outletId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="outletName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overTimeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paidAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="passCondition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processNode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shortRepaymentTerm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sumTuitionFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="teamLeaderId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="teamLeaderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trainCampus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trainCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trainId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="trainName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trainTeacher" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trainTeacherPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urgentLeve" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="verifyPageVesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bsTransport", propOrder = { "acceptMothRepay",
		"applyMaxAmount", "applyMinAmount", "assignFlag", "brandModel",
		"bsApplyId", "bsTransportId", "contractTime", "courseTerm",
		"destineTime", "inspectionResult", "intoPiecesId", "isChuding",
		"isRepeatApply", "isYizhi", "lenssonStartDate", "loanCount",
		"loanPlans", "loanType", "longRepaymentTerm", "moneySource",
		"otherMoneySource", "outletCode", "outletId", "outletName", "overTime",
		"overTimeStatus", "paidAmount", "passCondition", "processNode",
		"shortRepaymentTerm", "subject", "sumTuitionFee", "teamLeaderId",
		"teamLeaderName", "trainCampus", "trainCode", "trainId", "trainName",
		"trainTeacher", "trainTeacherPhone", "urgentLeve", "verifyPageVesion" })
public class BsTransport {

	protected BigDecimal acceptMothRepay;
	protected BigDecimal applyMaxAmount;
	protected BigDecimal applyMinAmount;
	protected String assignFlag;
	protected String brandModel;
	protected Long bsApplyId;
	protected Long bsTransportId;
	protected String contractTime;
	protected Integer courseTerm;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar destineTime;
	protected String inspectionResult;
	protected Long intoPiecesId;
	protected String isChuding;
	protected String isRepeatApply;
	protected String isYizhi;
	protected String lenssonStartDate;
	protected Integer loanCount;
	protected Integer loanPlans;
	protected String loanType;
	protected Integer longRepaymentTerm;
	protected String moneySource;
	protected String otherMoneySource;
	protected String outletCode;
	protected Long outletId;
	protected String outletName;
	protected String overTime;
	protected String overTimeStatus;
	protected BigDecimal paidAmount;
	protected String passCondition;
	protected String processNode;
	protected Integer shortRepaymentTerm;
	protected String subject;
	protected BigDecimal sumTuitionFee;
	protected Long teamLeaderId;
	protected String teamLeaderName;
	protected String trainCampus;
	protected String trainCode;
	protected Long trainId;
	protected String trainName;
	protected String trainTeacher;
	protected String trainTeacherPhone;
	protected Short urgentLeve;
	protected String verifyPageVesion;

	/**
	 * Gets the value of the acceptMothRepay property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getAcceptMothRepay() {
		return acceptMothRepay;
	}

	/**
	 * Sets the value of the acceptMothRepay property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setAcceptMothRepay(BigDecimal value) {
		this.acceptMothRepay = value;
	}

	/**
	 * Gets the value of the applyMaxAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getApplyMaxAmount() {
		return applyMaxAmount;
	}

	/**
	 * Sets the value of the applyMaxAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setApplyMaxAmount(BigDecimal value) {
		this.applyMaxAmount = value;
	}

	/**
	 * Gets the value of the applyMinAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getApplyMinAmount() {
		return applyMinAmount;
	}

	/**
	 * Sets the value of the applyMinAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setApplyMinAmount(BigDecimal value) {
		this.applyMinAmount = value;
	}

	/**
	 * Gets the value of the assignFlag property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssignFlag() {
		return assignFlag;
	}

	/**
	 * Sets the value of the assignFlag property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssignFlag(String value) {
		this.assignFlag = value;
	}

	/**
	 * Gets the value of the brandModel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBrandModel() {
		return brandModel;
	}

	/**
	 * Sets the value of the brandModel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBrandModel(String value) {
		this.brandModel = value;
	}

	/**
	 * Gets the value of the bsApplyId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getBsApplyId() {
		return bsApplyId;
	}

	/**
	 * Sets the value of the bsApplyId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setBsApplyId(Long value) {
		this.bsApplyId = value;
	}

	/**
	 * Gets the value of the bsTransportId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getBsTransportId() {
		return bsTransportId;
	}

	/**
	 * Sets the value of the bsTransportId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setBsTransportId(Long value) {
		this.bsTransportId = value;
	}

	/**
	 * Gets the value of the contractTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContractTime() {
		return contractTime;
	}

	/**
	 * Sets the value of the contractTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContractTime(String value) {
		this.contractTime = value;
	}

	/**
	 * Gets the value of the courseTerm property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCourseTerm() {
		return courseTerm;
	}

	/**
	 * Sets the value of the courseTerm property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCourseTerm(Integer value) {
		this.courseTerm = value;
	}

	/**
	 * Gets the value of the destineTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDestineTime() {
		return destineTime;
	}

	/**
	 * Sets the value of the destineTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDestineTime(XMLGregorianCalendar value) {
		this.destineTime = value;
	}

	/**
	 * Gets the value of the inspectionResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInspectionResult() {
		return inspectionResult;
	}

	/**
	 * Sets the value of the inspectionResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInspectionResult(String value) {
		this.inspectionResult = value;
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
	 * Gets the value of the isChuding property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsChuding() {
		return isChuding;
	}

	/**
	 * Sets the value of the isChuding property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsChuding(String value) {
		this.isChuding = value;
	}

	/**
	 * Gets the value of the isRepeatApply property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsRepeatApply() {
		return isRepeatApply;
	}

	/**
	 * Sets the value of the isRepeatApply property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsRepeatApply(String value) {
		this.isRepeatApply = value;
	}

	/**
	 * Gets the value of the isYizhi property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsYizhi() {
		return isYizhi;
	}

	/**
	 * Sets the value of the isYizhi property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsYizhi(String value) {
		this.isYizhi = value;
	}

	/**
	 * Gets the value of the lenssonStartDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLenssonStartDate() {
		return lenssonStartDate;
	}

	/**
	 * Sets the value of the lenssonStartDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLenssonStartDate(String value) {
		this.lenssonStartDate = value;
	}

	/**
	 * Gets the value of the loanCount property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getLoanCount() {
		return loanCount;
	}

	/**
	 * Sets the value of the loanCount property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setLoanCount(Integer value) {
		this.loanCount = value;
	}

	/**
	 * Gets the value of the loanPlans property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getLoanPlans() {
		return loanPlans;
	}

	/**
	 * Sets the value of the loanPlans property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setLoanPlans(Integer value) {
		this.loanPlans = value;
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
	 * Gets the value of the longRepaymentTerm property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getLongRepaymentTerm() {
		return longRepaymentTerm;
	}

	/**
	 * Sets the value of the longRepaymentTerm property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setLongRepaymentTerm(Integer value) {
		this.longRepaymentTerm = value;
	}

	/**
	 * Gets the value of the moneySource property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMoneySource() {
		return moneySource;
	}

	/**
	 * Sets the value of the moneySource property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMoneySource(String value) {
		this.moneySource = value;
	}

	/**
	 * Gets the value of the otherMoneySource property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOtherMoneySource() {
		return otherMoneySource;
	}

	/**
	 * Sets the value of the otherMoneySource property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOtherMoneySource(String value) {
		this.otherMoneySource = value;
	}

	/**
	 * Gets the value of the outletCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutletCode() {
		return outletCode;
	}

	/**
	 * Sets the value of the outletCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutletCode(String value) {
		this.outletCode = value;
	}

	/**
	 * Gets the value of the outletId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getOutletId() {
		return outletId;
	}

	/**
	 * Sets the value of the outletId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setOutletId(Long value) {
		this.outletId = value;
	}

	/**
	 * Gets the value of the outletName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutletName() {
		return outletName;
	}

	/**
	 * Sets the value of the outletName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutletName(String value) {
		this.outletName = value;
	}

	/**
	 * Gets the value of the overTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOverTime() {
		return overTime;
	}

	/**
	 * Sets the value of the overTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOverTime(String value) {
		this.overTime = value;
	}

	/**
	 * Gets the value of the overTimeStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOverTimeStatus() {
		return overTimeStatus;
	}

	/**
	 * Sets the value of the overTimeStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOverTimeStatus(String value) {
		this.overTimeStatus = value;
	}

	/**
	 * Gets the value of the paidAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	/**
	 * Sets the value of the paidAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPaidAmount(BigDecimal value) {
		this.paidAmount = value;
	}

	/**
	 * Gets the value of the passCondition property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPassCondition() {
		return passCondition;
	}

	/**
	 * Sets the value of the passCondition property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPassCondition(String value) {
		this.passCondition = value;
	}

	/**
	 * Gets the value of the processNode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProcessNode() {
		return processNode;
	}

	/**
	 * Sets the value of the processNode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProcessNode(String value) {
		this.processNode = value;
	}

	/**
	 * Gets the value of the shortRepaymentTerm property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getShortRepaymentTerm() {
		return shortRepaymentTerm;
	}

	/**
	 * Sets the value of the shortRepaymentTerm property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setShortRepaymentTerm(Integer value) {
		this.shortRepaymentTerm = value;
	}

	/**
	 * Gets the value of the subject property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the value of the subject property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubject(String value) {
		this.subject = value;
	}

	/**
	 * Gets the value of the sumTuitionFee property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getSumTuitionFee() {
		return sumTuitionFee;
	}

	/**
	 * Sets the value of the sumTuitionFee property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setSumTuitionFee(BigDecimal value) {
		this.sumTuitionFee = value;
	}

	/**
	 * Gets the value of the teamLeaderId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getTeamLeaderId() {
		return teamLeaderId;
	}

	/**
	 * Sets the value of the teamLeaderId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setTeamLeaderId(Long value) {
		this.teamLeaderId = value;
	}

	/**
	 * Gets the value of the teamLeaderName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	/**
	 * Sets the value of the teamLeaderName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTeamLeaderName(String value) {
		this.teamLeaderName = value;
	}

	/**
	 * Gets the value of the trainCampus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrainCampus() {
		return trainCampus;
	}

	/**
	 * Sets the value of the trainCampus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrainCampus(String value) {
		this.trainCampus = value;
	}

	/**
	 * Gets the value of the trainCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrainCode() {
		return trainCode;
	}

	/**
	 * Sets the value of the trainCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrainCode(String value) {
		this.trainCode = value;
	}

	/**
	 * Gets the value of the trainId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getTrainId() {
		return trainId;
	}

	/**
	 * Sets the value of the trainId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setTrainId(Long value) {
		this.trainId = value;
	}

	/**
	 * Gets the value of the trainName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * Sets the value of the trainName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrainName(String value) {
		this.trainName = value;
	}

	/**
	 * Gets the value of the trainTeacher property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrainTeacher() {
		return trainTeacher;
	}

	/**
	 * Sets the value of the trainTeacher property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrainTeacher(String value) {
		this.trainTeacher = value;
	}

	/**
	 * Gets the value of the trainTeacherPhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrainTeacherPhone() {
		return trainTeacherPhone;
	}

	/**
	 * Sets the value of the trainTeacherPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrainTeacherPhone(String value) {
		this.trainTeacherPhone = value;
	}

	/**
	 * Gets the value of the urgentLeve property.
	 * 
	 * @return possible object is {@link Short }
	 * 
	 */
	public Short getUrgentLeve() {
		return urgentLeve;
	}

	/**
	 * Sets the value of the urgentLeve property.
	 * 
	 * @param value
	 *            allowed object is {@link Short }
	 * 
	 */
	public void setUrgentLeve(Short value) {
		this.urgentLeve = value;
	}

	/**
	 * Gets the value of the verifyPageVesion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVerifyPageVesion() {
		return verifyPageVesion;
	}

	/**
	 * Sets the value of the verifyPageVesion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVerifyPageVesion(String value) {
		this.verifyPageVesion = value;
	}

}

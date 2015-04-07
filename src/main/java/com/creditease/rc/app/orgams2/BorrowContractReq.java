
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for borrowContractReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="borrowContractReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amortisation" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBook" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="borrowId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="borrowPurpose1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowPurpose2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bsApplyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="bsClientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bussTableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkOffOper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkOffTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="contractVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="discountReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="downLoadUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="electricSignUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isExpedited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="manageFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="planName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purposeCaption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="repayAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="returnEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="returnStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="returnType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="systemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yearIrr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="rCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="rTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "borrowContractReq", propOrder = {
    "address",
    "amortisation",
    "applyId",
    "bankAccount",
    "bankBook",
    "bankName",
    "bankNumber",
    "borrowAmt",
    "borrowId",
    "borrowPurpose1",
    "borrowPurpose2",
    "borrowType",
    "bsApplyId",
    "bsClientId",
    "bussTableName",
    "checkOffOper",
    "checkOffTime",
    "city",
    "clientId",
    "clientName",
    "clientType",
    "cmIp",
    "contractId",
    "contractRemark",
    "contractState",
    "contractTime",
    "contractVersion",
    "discountRate",
    "discountReason",
    "downLoadUrl",
    "electricSignUrl",
    "fileType",
    "interest",
    "isExpedited",
    "loanAmt",
    "manageFee",
    "mobile",
    "planId",
    "planName",
    "productType",
    "purposeCaption",
    "rate",
    "repayAmt",
    "returnEndDate",
    "returnStartDate",
    "returnType",
    "submitTime",
    "systemId",
    "systemSign",
    "viewUrl",
    "yearIrr",
    "rCharge",
    "rTime"
})
public class BorrowContractReq {

    protected String address;
    protected Long amortisation;
    protected String applyId;
    protected String bankAccount;
    protected String bankBook;
    protected String bankName;
    protected String bankNumber;
    protected BigDecimal borrowAmt;
    protected Long borrowId;
    protected String borrowPurpose1;
    protected String borrowPurpose2;
    protected String borrowType;
    protected Long bsApplyId;
    protected String bsClientId;
    protected String bussTableName;
    protected String checkOffOper;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checkOffTime;
    protected String city;
    protected String clientId;
    protected String clientName;
    protected String clientType;
    protected String cmIp;
    protected Long contractId;
    protected String contractRemark;
    protected String contractState;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar contractTime;
    protected String contractVersion;
    protected BigDecimal discountRate;
    protected String discountReason;
    protected String downLoadUrl;
    protected String electricSignUrl;
    protected String fileType;
    protected BigDecimal interest;
    protected String isExpedited;
    protected BigDecimal loanAmt;
    protected BigDecimal manageFee;
    protected String mobile;
    protected Long planId;
    protected String planName;
    protected String productType;
    protected String purposeCaption;
    protected BigDecimal rate;
    protected BigDecimal repayAmt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar returnEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar returnStartDate;
    protected String returnType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submitTime;
    protected String systemId;
    protected String systemSign;
    protected String viewUrl;
    protected BigDecimal yearIrr;
    protected BigDecimal rCharge;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rTime;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the amortisation property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmortisation() {
        return amortisation;
    }

    /**
     * Sets the value of the amortisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmortisation(Long value) {
        this.amortisation = value;
    }

    /**
     * Gets the value of the applyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * Sets the value of the applyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyId(String value) {
        this.applyId = value;
    }

    /**
     * Gets the value of the bankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the value of the bankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccount(String value) {
        this.bankAccount = value;
    }

    /**
     * Gets the value of the bankBook property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBook() {
        return bankBook;
    }

    /**
     * Sets the value of the bankBook property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBook(String value) {
        this.bankBook = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the bankNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankNumber() {
        return bankNumber;
    }

    /**
     * Sets the value of the bankNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankNumber(String value) {
        this.bankNumber = value;
    }

    /**
     * Gets the value of the borrowAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBorrowAmt() {
        return borrowAmt;
    }

    /**
     * Sets the value of the borrowAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBorrowAmt(BigDecimal value) {
        this.borrowAmt = value;
    }

    /**
     * Gets the value of the borrowId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * Sets the value of the borrowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBorrowId(Long value) {
        this.borrowId = value;
    }

    /**
     * Gets the value of the borrowPurpose1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowPurpose1() {
        return borrowPurpose1;
    }

    /**
     * Sets the value of the borrowPurpose1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowPurpose1(String value) {
        this.borrowPurpose1 = value;
    }

    /**
     * Gets the value of the borrowPurpose2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowPurpose2() {
        return borrowPurpose2;
    }

    /**
     * Sets the value of the borrowPurpose2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowPurpose2(String value) {
        this.borrowPurpose2 = value;
    }

    /**
     * Gets the value of the borrowType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowType() {
        return borrowType;
    }

    /**
     * Sets the value of the borrowType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowType(String value) {
        this.borrowType = value;
    }

    /**
     * Gets the value of the bsApplyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBsApplyId() {
        return bsApplyId;
    }

    /**
     * Sets the value of the bsApplyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBsApplyId(Long value) {
        this.bsApplyId = value;
    }

    /**
     * Gets the value of the bsClientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBsClientId() {
        return bsClientId;
    }

    /**
     * Sets the value of the bsClientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBsClientId(String value) {
        this.bsClientId = value;
    }

    /**
     * Gets the value of the bussTableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBussTableName() {
        return bussTableName;
    }

    /**
     * Sets the value of the bussTableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBussTableName(String value) {
        this.bussTableName = value;
    }

    /**
     * Gets the value of the checkOffOper property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckOffOper() {
        return checkOffOper;
    }

    /**
     * Sets the value of the checkOffOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckOffOper(String value) {
        this.checkOffOper = value;
    }

    /**
     * Gets the value of the checkOffTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCheckOffTime() {
        return checkOffTime;
    }

    /**
     * Sets the value of the checkOffTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckOffTime(XMLGregorianCalendar value) {
        this.checkOffTime = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the clientType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * Sets the value of the clientType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientType(String value) {
        this.clientType = value;
    }

    /**
     * Gets the value of the cmIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmIp() {
        return cmIp;
    }

    /**
     * Sets the value of the cmIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmIp(String value) {
        this.cmIp = value;
    }

    /**
     * Gets the value of the contractId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContractId(Long value) {
        this.contractId = value;
    }

    /**
     * Gets the value of the contractRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractRemark() {
        return contractRemark;
    }

    /**
     * Sets the value of the contractRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractRemark(String value) {
        this.contractRemark = value;
    }

    /**
     * Gets the value of the contractState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractState() {
        return contractState;
    }

    /**
     * Sets the value of the contractState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractState(String value) {
        this.contractState = value;
    }

    /**
     * Gets the value of the contractTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractTime() {
        return contractTime;
    }

    /**
     * Sets the value of the contractTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractTime(XMLGregorianCalendar value) {
        this.contractTime = value;
    }

    /**
     * Gets the value of the contractVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractVersion() {
        return contractVersion;
    }

    /**
     * Sets the value of the contractVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractVersion(String value) {
        this.contractVersion = value;
    }

    /**
     * Gets the value of the discountRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * Sets the value of the discountRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountRate(BigDecimal value) {
        this.discountRate = value;
    }

    /**
     * Gets the value of the discountReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountReason() {
        return discountReason;
    }

    /**
     * Sets the value of the discountReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountReason(String value) {
        this.discountReason = value;
    }

    /**
     * Gets the value of the downLoadUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    /**
     * Sets the value of the downLoadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownLoadUrl(String value) {
        this.downLoadUrl = value;
    }

    /**
     * Gets the value of the electricSignUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElectricSignUrl() {
        return electricSignUrl;
    }

    /**
     * Sets the value of the electricSignUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElectricSignUrl(String value) {
        this.electricSignUrl = value;
    }

    /**
     * Gets the value of the fileType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the value of the fileType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileType(String value) {
        this.fileType = value;
    }

    /**
     * Gets the value of the interest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * Sets the value of the interest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInterest(BigDecimal value) {
        this.interest = value;
    }

    /**
     * Gets the value of the isExpedited property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsExpedited() {
        return isExpedited;
    }

    /**
     * Sets the value of the isExpedited property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsExpedited(String value) {
        this.isExpedited = value;
    }

    /**
     * Gets the value of the loanAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    /**
     * Sets the value of the loanAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLoanAmt(BigDecimal value) {
        this.loanAmt = value;
    }

    /**
     * Gets the value of the manageFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getManageFee() {
        return manageFee;
    }

    /**
     * Sets the value of the manageFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setManageFee(BigDecimal value) {
        this.manageFee = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the planId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlanId() {
        return planId;
    }

    /**
     * Sets the value of the planId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlanId(Long value) {
        this.planId = value;
    }

    /**
     * Gets the value of the planName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Sets the value of the planName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanName(String value) {
        this.planName = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the purposeCaption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeCaption() {
        return purposeCaption;
    }

    /**
     * Sets the value of the purposeCaption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeCaption(String value) {
        this.purposeCaption = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * Gets the value of the repayAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    /**
     * Sets the value of the repayAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRepayAmt(BigDecimal value) {
        this.repayAmt = value;
    }

    /**
     * Gets the value of the returnEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReturnEndDate() {
        return returnEndDate;
    }

    /**
     * Sets the value of the returnEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReturnEndDate(XMLGregorianCalendar value) {
        this.returnEndDate = value;
    }

    /**
     * Gets the value of the returnStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReturnStartDate() {
        return returnStartDate;
    }

    /**
     * Sets the value of the returnStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReturnStartDate(XMLGregorianCalendar value) {
        this.returnStartDate = value;
    }

    /**
     * Gets the value of the returnType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * Sets the value of the returnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnType(String value) {
        this.returnType = value;
    }

    /**
     * Gets the value of the submitTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmitTime() {
        return submitTime;
    }

    /**
     * Sets the value of the submitTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmitTime(XMLGregorianCalendar value) {
        this.submitTime = value;
    }

    /**
     * Gets the value of the systemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the value of the systemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * Gets the value of the systemSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemSign() {
        return systemSign;
    }

    /**
     * Sets the value of the systemSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemSign(String value) {
        this.systemSign = value;
    }

    /**
     * Gets the value of the viewUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewUrl() {
        return viewUrl;
    }

    /**
     * Sets the value of the viewUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewUrl(String value) {
        this.viewUrl = value;
    }

    /**
     * Gets the value of the yearIrr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getYearIrr() {
        return yearIrr;
    }

    /**
     * Sets the value of the yearIrr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setYearIrr(BigDecimal value) {
        this.yearIrr = value;
    }

    /**
     * Gets the value of the rCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRCharge() {
        return rCharge;
    }

    /**
     * Sets the value of the rCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRCharge(BigDecimal value) {
        this.rCharge = value;
    }

    /**
     * Gets the value of the rTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRTime() {
        return rTime;
    }

    /**
     * Sets the value of the rTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRTime(XMLGregorianCalendar value) {
        this.rTime = value;
    }

}

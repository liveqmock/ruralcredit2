
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClientApplyRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientApplyRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rural_borrow_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="department_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBook" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankClientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanBankAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanBankClientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanBankProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanBankCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applyAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstPayDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstInterestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amortisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowPurposeSon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeFixPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contactListDTO" type="{http://www.creditease.cn/RuralBusyService/}contactListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="frontChargeDiscountList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ChargeDiscountInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}ChargeDiscountInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodChargeDiscountList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ChargeDiscountInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}ChargeDiscountInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="paymentTypeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PaymentTypeConfigDTO" type="{http://www.creditease.cn/RuralBusyService/}PaymentTypeConfigDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientApplyRequest", propOrder = {
    "applyId",
    "ruralBorrowId",
    "departmentId",
    "returnType",
    "bankBook",
    "bankAccount",
    "bankClientName",
    "bankName",
    "loanType",
    "loanBankAccount",
    "loanBankClientName",
    "loanBankProvince",
    "loanBankCity",
    "loanBankName",
    "applyAmount",
    "loanAmount",
    "loanTime",
    "firstPayDateType",
    "firstInterestType",
    "productTypeId",
    "productId",
    "amortisation",
    "borrowPurpose",
    "borrowPurposeSon",
    "borrowType",
    "sellId",
    "sellName",
    "officeId",
    "officeName",
    "city",
    "sysId",
    "signInfo",
    "clientId",
    "clientType",
    "clientName",
    "idNumber",
    "homeCity",
    "homeProvince",
    "homeAddr",
    "mobilePhone",
    "homeFixPhone",
    "contractTime",
    "bankNumber",
    "accountid",
    "contactList",
    "frontChargeDiscountList",
    "periodChargeDiscountList",
    "paymentTypeList"
})
public class ClientApplyRequest {

    protected String applyId;
    @XmlElement(name = "rural_borrow_id")
    protected String ruralBorrowId;
    @XmlElement(name = "department_id")
    protected String departmentId;
    protected String returnType;
    protected String bankBook;
    protected String bankAccount;
    protected String bankClientName;
    protected String bankName;
    protected String loanType;
    protected String loanBankAccount;
    protected String loanBankClientName;
    protected String loanBankProvince;
    protected String loanBankCity;
    protected String loanBankName;
    protected String applyAmount;
    protected String loanAmount;
    protected String loanTime;
    protected String firstPayDateType;
    protected String firstInterestType;
    protected String productTypeId;
    protected String productId;
    protected String amortisation;
    protected String borrowPurpose;
    protected String borrowPurposeSon;
    protected String borrowType;
    protected String sellId;
    protected String sellName;
    protected String officeId;
    protected String officeName;
    protected String city;
    protected String sysId;
    protected String signInfo;
    protected String clientId;
    protected String clientType;
    protected String clientName;
    protected String idNumber;
    protected String homeCity;
    protected String homeProvince;
    protected String homeAddr;
    protected String mobilePhone;
    protected String homeFixPhone;
    protected String contractTime;
    protected String bankNumber;
    protected String accountid;
    protected ClientApplyRequest.ContactList contactList;
    protected ClientApplyRequest.FrontChargeDiscountList frontChargeDiscountList;
    protected ClientApplyRequest.PeriodChargeDiscountList periodChargeDiscountList;
    protected ClientApplyRequest.PaymentTypeList paymentTypeList;

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
     * Gets the value of the ruralBorrowId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralBorrowId() {
        return ruralBorrowId;
    }

    /**
     * Sets the value of the ruralBorrowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralBorrowId(String value) {
        this.ruralBorrowId = value;
    }

    /**
     * Gets the value of the departmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentId(String value) {
        this.departmentId = value;
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
     * Gets the value of the bankClientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankClientName() {
        return bankClientName;
    }

    /**
     * Sets the value of the bankClientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankClientName(String value) {
        this.bankClientName = value;
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
     * Gets the value of the loanType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * Sets the value of the loanType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanType(String value) {
        this.loanType = value;
    }

    /**
     * Gets the value of the loanBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanBankAccount() {
        return loanBankAccount;
    }

    /**
     * Sets the value of the loanBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanBankAccount(String value) {
        this.loanBankAccount = value;
    }

    /**
     * Gets the value of the loanBankClientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanBankClientName() {
        return loanBankClientName;
    }

    /**
     * Sets the value of the loanBankClientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanBankClientName(String value) {
        this.loanBankClientName = value;
    }

    /**
     * Gets the value of the loanBankProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanBankProvince() {
        return loanBankProvince;
    }

    /**
     * Sets the value of the loanBankProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanBankProvince(String value) {
        this.loanBankProvince = value;
    }

    /**
     * Gets the value of the loanBankCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanBankCity() {
        return loanBankCity;
    }

    /**
     * Sets the value of the loanBankCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanBankCity(String value) {
        this.loanBankCity = value;
    }

    /**
     * Gets the value of the loanBankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanBankName() {
        return loanBankName;
    }

    /**
     * Sets the value of the loanBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanBankName(String value) {
        this.loanBankName = value;
    }

    /**
     * Gets the value of the applyAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyAmount() {
        return applyAmount;
    }

    /**
     * Sets the value of the applyAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyAmount(String value) {
        this.applyAmount = value;
    }

    /**
     * Gets the value of the loanAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the value of the loanAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanAmount(String value) {
        this.loanAmount = value;
    }

    /**
     * Gets the value of the loanTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanTime() {
        return loanTime;
    }

    /**
     * Sets the value of the loanTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanTime(String value) {
        this.loanTime = value;
    }

    /**
     * Gets the value of the firstPayDateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPayDateType() {
        return firstPayDateType;
    }

    /**
     * Sets the value of the firstPayDateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPayDateType(String value) {
        this.firstPayDateType = value;
    }

    /**
     * Gets the value of the firstInterestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstInterestType() {
        return firstInterestType;
    }

    /**
     * Sets the value of the firstInterestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstInterestType(String value) {
        this.firstInterestType = value;
    }

    /**
     * Gets the value of the productTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeId() {
        return productTypeId;
    }

    /**
     * Sets the value of the productTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeId(String value) {
        this.productTypeId = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
    }

    /**
     * Gets the value of the amortisation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmortisation() {
        return amortisation;
    }

    /**
     * Sets the value of the amortisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmortisation(String value) {
        this.amortisation = value;
    }

    /**
     * Gets the value of the borrowPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowPurpose() {
        return borrowPurpose;
    }

    /**
     * Sets the value of the borrowPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowPurpose(String value) {
        this.borrowPurpose = value;
    }

    /**
     * Gets the value of the borrowPurposeSon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowPurposeSon() {
        return borrowPurposeSon;
    }

    /**
     * Sets the value of the borrowPurposeSon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowPurposeSon(String value) {
        this.borrowPurposeSon = value;
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
     * Gets the value of the sellId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellId() {
        return sellId;
    }

    /**
     * Sets the value of the sellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellId(String value) {
        this.sellId = value;
    }

    /**
     * Gets the value of the sellName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellName() {
        return sellName;
    }

    /**
     * Sets the value of the sellName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellName(String value) {
        this.sellName = value;
    }

    /**
     * Gets the value of the officeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * Sets the value of the officeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeId(String value) {
        this.officeId = value;
    }

    /**
     * Gets the value of the officeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * Sets the value of the officeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeName(String value) {
        this.officeName = value;
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
     * Gets the value of the sysId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * Sets the value of the sysId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysId(String value) {
        this.sysId = value;
    }

    /**
     * Gets the value of the signInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignInfo() {
        return signInfo;
    }

    /**
     * Sets the value of the signInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignInfo(String value) {
        this.signInfo = value;
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
     * Gets the value of the idNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the value of the idNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumber(String value) {
        this.idNumber = value;
    }

    /**
     * Gets the value of the homeCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * Sets the value of the homeCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCity(String value) {
        this.homeCity = value;
    }

    /**
     * Gets the value of the homeProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeProvince() {
        return homeProvince;
    }

    /**
     * Sets the value of the homeProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeProvince(String value) {
        this.homeProvince = value;
    }

    /**
     * Gets the value of the homeAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeAddr() {
        return homeAddr;
    }

    /**
     * Sets the value of the homeAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeAddr(String value) {
        this.homeAddr = value;
    }

    /**
     * Gets the value of the mobilePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Sets the value of the mobilePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilePhone(String value) {
        this.mobilePhone = value;
    }

    /**
     * Gets the value of the homeFixPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeFixPhone() {
        return homeFixPhone;
    }

    /**
     * Sets the value of the homeFixPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeFixPhone(String value) {
        this.homeFixPhone = value;
    }

    /**
     * Gets the value of the contractTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractTime() {
        return contractTime;
    }

    /**
     * Sets the value of the contractTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractTime(String value) {
        this.contractTime = value;
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
     * Gets the value of the accountid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountid() {
        return accountid;
    }

    /**
     * Sets the value of the accountid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountid(String value) {
        this.accountid = value;
    }

    /**
     * Gets the value of the contactList property.
     * 
     * @return
     *     possible object is
     *     {@link ClientApplyRequest.ContactList }
     *     
     */
    public ClientApplyRequest.ContactList getContactList() {
        return contactList;
    }

    /**
     * Sets the value of the contactList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientApplyRequest.ContactList }
     *     
     */
    public void setContactList(ClientApplyRequest.ContactList value) {
        this.contactList = value;
    }

    /**
     * Gets the value of the frontChargeDiscountList property.
     * 
     * @return
     *     possible object is
     *     {@link ClientApplyRequest.FrontChargeDiscountList }
     *     
     */
    public ClientApplyRequest.FrontChargeDiscountList getFrontChargeDiscountList() {
        return frontChargeDiscountList;
    }

    /**
     * Sets the value of the frontChargeDiscountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientApplyRequest.FrontChargeDiscountList }
     *     
     */
    public void setFrontChargeDiscountList(ClientApplyRequest.FrontChargeDiscountList value) {
        this.frontChargeDiscountList = value;
    }

    /**
     * Gets the value of the periodChargeDiscountList property.
     * 
     * @return
     *     possible object is
     *     {@link ClientApplyRequest.PeriodChargeDiscountList }
     *     
     */
    public ClientApplyRequest.PeriodChargeDiscountList getPeriodChargeDiscountList() {
        return periodChargeDiscountList;
    }

    /**
     * Sets the value of the periodChargeDiscountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientApplyRequest.PeriodChargeDiscountList }
     *     
     */
    public void setPeriodChargeDiscountList(ClientApplyRequest.PeriodChargeDiscountList value) {
        this.periodChargeDiscountList = value;
    }

    /**
     * Gets the value of the paymentTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link ClientApplyRequest.PaymentTypeList }
     *     
     */
    public ClientApplyRequest.PaymentTypeList getPaymentTypeList() {
        return paymentTypeList;
    }

    /**
     * Sets the value of the paymentTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientApplyRequest.PaymentTypeList }
     *     
     */
    public void setPaymentTypeList(ClientApplyRequest.PaymentTypeList value) {
        this.paymentTypeList = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="contactListDTO" type="{http://www.creditease.cn/RuralBusyService/}contactListDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "contactListDTO"
    })
    public static class ContactList {

        protected List<ContactListDTO> contactListDTO;

        /**
         * Gets the value of the contactListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contactListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContactListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContactListDTO }
         * 
         * 
         */
        public List<ContactListDTO> getContactListDTO() {
            if (contactListDTO == null) {
                contactListDTO = new ArrayList<ContactListDTO>();
            }
            return this.contactListDTO;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ChargeDiscountInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}ChargeDiscountInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "chargeDiscountInfoDTO"
    })
    public static class FrontChargeDiscountList {

        @XmlElement(name = "ChargeDiscountInfoDTO")
        protected List<ChargeDiscountInfoDTO> chargeDiscountInfoDTO;

        /**
         * Gets the value of the chargeDiscountInfoDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeDiscountInfoDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeDiscountInfoDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeDiscountInfoDTO }
         * 
         * 
         */
        public List<ChargeDiscountInfoDTO> getChargeDiscountInfoDTO() {
            if (chargeDiscountInfoDTO == null) {
                chargeDiscountInfoDTO = new ArrayList<ChargeDiscountInfoDTO>();
            }
            return this.chargeDiscountInfoDTO;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PaymentTypeConfigDTO" type="{http://www.creditease.cn/RuralBusyService/}PaymentTypeConfigDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "paymentTypeConfigDTO"
    })
    public static class PaymentTypeList {

        @XmlElement(name = "PaymentTypeConfigDTO")
        protected List<PaymentTypeConfigDTO> paymentTypeConfigDTO;

        /**
         * Gets the value of the paymentTypeConfigDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paymentTypeConfigDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPaymentTypeConfigDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PaymentTypeConfigDTO }
         * 
         * 
         */
        public List<PaymentTypeConfigDTO> getPaymentTypeConfigDTO() {
            if (paymentTypeConfigDTO == null) {
                paymentTypeConfigDTO = new ArrayList<PaymentTypeConfigDTO>();
            }
            return this.paymentTypeConfigDTO;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ChargeDiscountInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}ChargeDiscountInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "chargeDiscountInfoDTO"
    })
    public static class PeriodChargeDiscountList {

        @XmlElement(name = "ChargeDiscountInfoDTO")
        protected List<ChargeDiscountInfoDTO> chargeDiscountInfoDTO;

        /**
         * Gets the value of the chargeDiscountInfoDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeDiscountInfoDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeDiscountInfoDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeDiscountInfoDTO }
         * 
         * 
         */
        public List<ChargeDiscountInfoDTO> getChargeDiscountInfoDTO() {
            if (chargeDiscountInfoDTO == null) {
                chargeDiscountInfoDTO = new ArrayList<ChargeDiscountInfoDTO>();
            }
            return this.chargeDiscountInfoDTO;
        }

    }

}


package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applyInfoListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applyInfoListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="productTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amortisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowPurposeSon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="o_sellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="o_sellName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeFixPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactInfoList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contactInfoListDTO" type="{http://www.creditease.cn/RuralBusyService/}contactInfoListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "applyInfoListDTO", propOrder = {
    "applyId",
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
    "productTypeId",
    "productId",
    "amortisation",
    "borrowPurpose",
    "borrowPurposeSon",
    "borrowType",
    "sellId",
    "oSellId",
    "sellName",
    "oSellName",
    "officeId",
    "city",
    "clientName",
    "idNumber",
    "homeProvince",
    "homeCity",
    "homeAddr",
    "mobilePhone",
    "homeFixPhone",
    "contactInfoList"
})
public class ApplyInfoListDTO {

    protected String applyId;
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
    protected String productTypeId;
    protected String productId;
    protected String amortisation;
    protected String borrowPurpose;
    protected String borrowPurposeSon;
    protected String borrowType;
    protected String sellId;
    @XmlElement(name = "o_sellId")
    protected String oSellId;
    protected String sellName;
    @XmlElement(name = "o_sellName")
    protected String oSellName;
    protected String officeId;
    protected String city;
    protected String clientName;
    protected String idNumber;
    protected String homeProvince;
    protected String homeCity;
    protected String homeAddr;
    protected String mobilePhone;
    protected String homeFixPhone;
    protected ApplyInfoListDTO.ContactInfoList contactInfoList;

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
     * Gets the value of the oSellId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSellId() {
        return oSellId;
    }

    /**
     * Sets the value of the oSellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSellId(String value) {
        this.oSellId = value;
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
     * Gets the value of the oSellName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSellName() {
        return oSellName;
    }

    /**
     * Sets the value of the oSellName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSellName(String value) {
        this.oSellName = value;
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
     * Gets the value of the contactInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link ApplyInfoListDTO.ContactInfoList }
     *     
     */
    public ApplyInfoListDTO.ContactInfoList getContactInfoList() {
        return contactInfoList;
    }

    /**
     * Sets the value of the contactInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplyInfoListDTO.ContactInfoList }
     *     
     */
    public void setContactInfoList(ApplyInfoListDTO.ContactInfoList value) {
        this.contactInfoList = value;
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
     *         &lt;element name="contactInfoListDTO" type="{http://www.creditease.cn/RuralBusyService/}contactInfoListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "contactInfoListDTO"
    })
    public static class ContactInfoList {

        protected List<ContactInfoListDTO> contactInfoListDTO;

        /**
         * Gets the value of the contactInfoListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contactInfoListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContactInfoListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContactInfoListDTO }
         * 
         * 
         */
        public List<ContactInfoListDTO> getContactInfoListDTO() {
            if (contactInfoListDTO == null) {
                contactInfoListDTO = new ArrayList<ContactInfoListDTO>();
            }
            return this.contactInfoListDTO;
        }

    }

}


package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for returnListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reserveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destine_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destine_amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ifPayAhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBook" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankIdnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="singleRtReceipt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnListDTO", propOrder = {
    "reserveId",
    "applyId",
    "destineDate",
    "destineAmount",
    "ifPayAhead",
    "signInfo",
    "returnType",
    "bankBook",
    "bankAccountName",
    "bankAccount",
    "bankName",
    "bankIdnumber",
    "singleRtReceipt",
    "sellId"
})
public class ReturnListDTO {

    protected String reserveId;
    protected String applyId;
    @XmlElement(name = "destine_date")
    protected String destineDate;
    @XmlElement(name = "destine_amount")
    protected String destineAmount;
    protected String ifPayAhead;
    protected String signInfo;
    protected String returnType;
    protected String bankBook;
    protected String bankAccountName;
    protected String bankAccount;
    protected String bankName;
    protected String bankIdnumber;
    protected String singleRtReceipt;
    protected String sellId;

    /**
     * Gets the value of the reserveId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveId() {
        return reserveId;
    }

    /**
     * Sets the value of the reserveId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveId(String value) {
        this.reserveId = value;
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
     * Gets the value of the destineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestineDate() {
        return destineDate;
    }

    /**
     * Sets the value of the destineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestineDate(String value) {
        this.destineDate = value;
    }

    /**
     * Gets the value of the destineAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestineAmount() {
        return destineAmount;
    }

    /**
     * Sets the value of the destineAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestineAmount(String value) {
        this.destineAmount = value;
    }

    /**
     * Gets the value of the ifPayAhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfPayAhead() {
        return ifPayAhead;
    }

    /**
     * Sets the value of the ifPayAhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfPayAhead(String value) {
        this.ifPayAhead = value;
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
     * Gets the value of the bankAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     * Sets the value of the bankAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountName(String value) {
        this.bankAccountName = value;
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
     * Gets the value of the bankIdnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankIdnumber() {
        return bankIdnumber;
    }

    /**
     * Sets the value of the bankIdnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankIdnumber(String value) {
        this.bankIdnumber = value;
    }

    /**
     * Gets the value of the singleRtReceipt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleRtReceipt() {
        return singleRtReceipt;
    }

    /**
     * Sets the value of the singleRtReceipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleRtReceipt(String value) {
        this.singleRtReceipt = value;
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

}

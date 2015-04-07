
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mortgagorBank complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorBank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountBankCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountBankProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCardNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mortgagorBankId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorBank", propOrder = {
    "accountBankCity",
    "accountBankName",
    "accountBankProvince",
    "bankCardNum",
    "bankId",
    "createDate",
    "creator",
    "mortgagorBankId",
    "mortgagorId",
    "operDate",
    "operator"
})
public class MortgagorBank {

    protected String accountBankCity;
    protected String accountBankName;
    protected String accountBankProvince;
    protected String bankCardNum;
    protected String bankId;
    protected String createDate;
    protected Long creator;
    protected Long mortgagorBankId;
    protected Long mortgagorId;
    protected String operDate;
    protected Long operator;

    /**
     * Gets the value of the accountBankCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountBankCity() {
        return accountBankCity;
    }

    /**
     * Sets the value of the accountBankCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountBankCity(String value) {
        this.accountBankCity = value;
    }

    /**
     * Gets the value of the accountBankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountBankName() {
        return accountBankName;
    }

    /**
     * Sets the value of the accountBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountBankName(String value) {
        this.accountBankName = value;
    }

    /**
     * Gets the value of the accountBankProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountBankProvince() {
        return accountBankProvince;
    }

    /**
     * Sets the value of the accountBankProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountBankProvince(String value) {
        this.accountBankProvince = value;
    }

    /**
     * Gets the value of the bankCardNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCardNum() {
        return bankCardNum;
    }

    /**
     * Sets the value of the bankCardNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCardNum(String value) {
        this.bankCardNum = value;
    }

    /**
     * Gets the value of the bankId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * Sets the value of the bankId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankId(String value) {
        this.bankId = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateDate(String value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreator(Long value) {
        this.creator = value;
    }

    /**
     * Gets the value of the mortgagorBankId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMortgagorBankId() {
        return mortgagorBankId;
    }

    /**
     * Sets the value of the mortgagorBankId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMortgagorBankId(Long value) {
        this.mortgagorBankId = value;
    }

    /**
     * Gets the value of the mortgagorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMortgagorId() {
        return mortgagorId;
    }

    /**
     * Sets the value of the mortgagorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMortgagorId(Long value) {
        this.mortgagorId = value;
    }

    /**
     * Gets the value of the operDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperDate() {
        return operDate;
    }

    /**
     * Sets the value of the operDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperDate(String value) {
        this.operDate = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperator(Long value) {
        this.operator = value;
    }

}

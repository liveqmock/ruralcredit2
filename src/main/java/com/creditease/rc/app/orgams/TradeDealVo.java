
package com.creditease.rc.app.orgams;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tradeDealVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tradeDealVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="amountBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="borrowResId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="creditorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditorType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="financeFormId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="financeMatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="investFormId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="investMatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="irr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="matchStrategId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="organName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="subAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeDealId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeDealTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="trustAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tradeDealVo", propOrder = {
    "accountName",
    "accountNo",
    "amount",
    "amountBalance",
    "borrowResId",
    "creditorId",
    "creditorType",
    "financeFormId",
    "financeMatchId",
    "investFormId",
    "investMatchId",
    "irr",
    "matchStrategId",
    "organName",
    "planCode",
    "planId",
    "planName",
    "productId",
    "status",
    "subAccountName",
    "tradeDealId",
    "tradeDealTime",
    "trustAccountName"
})
public class TradeDealVo {

    protected String accountName;
    protected Long accountNo;
    protected BigDecimal amount;
    protected BigDecimal amountBalance;
    protected Long borrowResId;
    protected String creditorId;
    protected Long creditorType;
    protected String financeFormId;
    protected String financeMatchId;
    protected String investFormId;
    protected String investMatchId;
    protected BigDecimal irr;
    protected Long matchStrategId;
    protected String organName;
    protected String planCode;
    protected String planId;
    protected String planName;
    protected String productId;
    protected Long status;
    protected String subAccountName;
    protected String tradeDealId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tradeDealTime;
    protected String trustAccountName;

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the value of the accountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAccountNo(Long value) {
        this.accountNo = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the amountBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountBalance() {
        return amountBalance;
    }

    /**
     * Sets the value of the amountBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountBalance(BigDecimal value) {
        this.amountBalance = value;
    }

    /**
     * Gets the value of the borrowResId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBorrowResId() {
        return borrowResId;
    }

    /**
     * Sets the value of the borrowResId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBorrowResId(Long value) {
        this.borrowResId = value;
    }

    /**
     * Gets the value of the creditorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorId() {
        return creditorId;
    }

    /**
     * Sets the value of the creditorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorId(String value) {
        this.creditorId = value;
    }

    /**
     * Gets the value of the creditorType property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreditorType() {
        return creditorType;
    }

    /**
     * Sets the value of the creditorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreditorType(Long value) {
        this.creditorType = value;
    }

    /**
     * Gets the value of the financeFormId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinanceFormId() {
        return financeFormId;
    }

    /**
     * Sets the value of the financeFormId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinanceFormId(String value) {
        this.financeFormId = value;
    }

    /**
     * Gets the value of the financeMatchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinanceMatchId() {
        return financeMatchId;
    }

    /**
     * Sets the value of the financeMatchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinanceMatchId(String value) {
        this.financeMatchId = value;
    }

    /**
     * Gets the value of the investFormId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestFormId() {
        return investFormId;
    }

    /**
     * Sets the value of the investFormId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestFormId(String value) {
        this.investFormId = value;
    }

    /**
     * Gets the value of the investMatchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestMatchId() {
        return investMatchId;
    }

    /**
     * Sets the value of the investMatchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestMatchId(String value) {
        this.investMatchId = value;
    }

    /**
     * Gets the value of the irr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrr() {
        return irr;
    }

    /**
     * Sets the value of the irr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrr(BigDecimal value) {
        this.irr = value;
    }

    /**
     * Gets the value of the matchStrategId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMatchStrategId() {
        return matchStrategId;
    }

    /**
     * Sets the value of the matchStrategId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMatchStrategId(Long value) {
        this.matchStrategId = value;
    }

    /**
     * Gets the value of the organName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganName() {
        return organName;
    }

    /**
     * Sets the value of the organName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganName(String value) {
        this.organName = value;
    }

    /**
     * Gets the value of the planCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanCode() {
        return planCode;
    }

    /**
     * Sets the value of the planCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanCode(String value) {
        this.planCode = value;
    }

    /**
     * Gets the value of the planId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * Sets the value of the planId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanId(String value) {
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStatus(Long value) {
        this.status = value;
    }

    /**
     * Gets the value of the subAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubAccountName() {
        return subAccountName;
    }

    /**
     * Sets the value of the subAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubAccountName(String value) {
        this.subAccountName = value;
    }

    /**
     * Gets the value of the tradeDealId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeDealId() {
        return tradeDealId;
    }

    /**
     * Sets the value of the tradeDealId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeDealId(String value) {
        this.tradeDealId = value;
    }

    /**
     * Gets the value of the tradeDealTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTradeDealTime() {
        return tradeDealTime;
    }

    /**
     * Sets the value of the tradeDealTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTradeDealTime(XMLGregorianCalendar value) {
        this.tradeDealTime = value;
    }

    /**
     * Gets the value of the trustAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustAccountName() {
        return trustAccountName;
    }

    /**
     * Sets the value of the trustAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustAccountName(String value) {
        this.trustAccountName = value;
    }

}

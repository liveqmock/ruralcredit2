
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllAheadScheduleDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllAheadScheduleDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="totalPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalOverdueFines" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalPeriodCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalOverdueInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentPeriodCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surplusPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="behindInterests" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="penalbond" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllAheadScheduleDTO", propOrder = {
    "totalPrincipal",
    "totalInterest",
    "totalOverdueFines",
    "totalPeriodCharge",
    "totalOverdueInterest",
    "currentPrincipal",
    "currentInterest",
    "currentPeriodCharge",
    "surplusPrincipal",
    "behindInterests",
    "charge",
    "penalbond",
    "total"
})
public class AllAheadScheduleDTO {

    protected String totalPrincipal;
    protected String totalInterest;
    protected String totalOverdueFines;
    protected String totalPeriodCharge;
    protected String totalOverdueInterest;
    protected String currentPrincipal;
    protected String currentInterest;
    protected String currentPeriodCharge;
    protected String surplusPrincipal;
    protected String behindInterests;
    protected String charge;
    protected String penalbond;
    protected String total;

    /**
     * Gets the value of the totalPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPrincipal() {
        return totalPrincipal;
    }

    /**
     * Sets the value of the totalPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPrincipal(String value) {
        this.totalPrincipal = value;
    }

    /**
     * Gets the value of the totalInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalInterest() {
        return totalInterest;
    }

    /**
     * Sets the value of the totalInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalInterest(String value) {
        this.totalInterest = value;
    }

    /**
     * Gets the value of the totalOverdueFines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalOverdueFines() {
        return totalOverdueFines;
    }

    /**
     * Sets the value of the totalOverdueFines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalOverdueFines(String value) {
        this.totalOverdueFines = value;
    }

    /**
     * Gets the value of the totalPeriodCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPeriodCharge() {
        return totalPeriodCharge;
    }

    /**
     * Sets the value of the totalPeriodCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPeriodCharge(String value) {
        this.totalPeriodCharge = value;
    }

    /**
     * Gets the value of the totalOverdueInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalOverdueInterest() {
        return totalOverdueInterest;
    }

    /**
     * Sets the value of the totalOverdueInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalOverdueInterest(String value) {
        this.totalOverdueInterest = value;
    }

    /**
     * Gets the value of the currentPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentPrincipal() {
        return currentPrincipal;
    }

    /**
     * Sets the value of the currentPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentPrincipal(String value) {
        this.currentPrincipal = value;
    }

    /**
     * Gets the value of the currentInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentInterest() {
        return currentInterest;
    }

    /**
     * Sets the value of the currentInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentInterest(String value) {
        this.currentInterest = value;
    }

    /**
     * Gets the value of the currentPeriodCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentPeriodCharge() {
        return currentPeriodCharge;
    }

    /**
     * Sets the value of the currentPeriodCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentPeriodCharge(String value) {
        this.currentPeriodCharge = value;
    }

    /**
     * Gets the value of the surplusPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurplusPrincipal() {
        return surplusPrincipal;
    }

    /**
     * Sets the value of the surplusPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurplusPrincipal(String value) {
        this.surplusPrincipal = value;
    }

    /**
     * Gets the value of the behindInterests property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBehindInterests() {
        return behindInterests;
    }

    /**
     * Sets the value of the behindInterests property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBehindInterests(String value) {
        this.behindInterests = value;
    }

    /**
     * Gets the value of the charge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharge(String value) {
        this.charge = value;
    }

    /**
     * Gets the value of the penalbond property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenalbond() {
        return penalbond;
    }

    /**
     * Sets the value of the penalbond property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenalbond(String value) {
        this.penalbond = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

}

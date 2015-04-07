
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for allAheadSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="allAheadSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="behindInterests" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="charge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currentInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="currentPeriodCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="currentPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="penalbond" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="surplusPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="totalInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalOverdueFines" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalOverdueInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalPeriodCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "allAheadSchedule", propOrder = {
    "behindInterests",
    "charge",
    "currentInterest",
    "currentPeriodCharge",
    "currentPrincipal",
    "penalbond",
    "surplusPrincipal",
    "total",
    "totalInterest",
    "totalOverdueFines",
    "totalOverdueInterest",
    "totalPeriodCharge",
    "totalPrincipal"
})
public class AllAheadSchedule {

    @XmlElement(required = true)
    protected BigDecimal behindInterests;
    protected BigDecimal charge;
    @XmlElement(required = true)
    protected BigDecimal currentInterest;
    @XmlElement(required = true)
    protected BigDecimal currentPeriodCharge;
    @XmlElement(required = true)
    protected BigDecimal currentPrincipal;
    protected BigDecimal penalbond;
    @XmlElement(required = true)
    protected BigDecimal surplusPrincipal;
    protected BigDecimal total;
    @XmlElement(required = true)
    protected BigDecimal totalInterest;
    @XmlElement(required = true)
    protected BigDecimal totalOverdueFines;
    @XmlElement(required = true)
    protected BigDecimal totalOverdueInterest;
    @XmlElement(required = true)
    protected BigDecimal totalPeriodCharge;
    @XmlElement(required = true)
    protected BigDecimal totalPrincipal;

    /**
     * Gets the value of the behindInterests property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBehindInterests() {
        return behindInterests;
    }

    /**
     * Sets the value of the behindInterests property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBehindInterests(BigDecimal value) {
        this.behindInterests = value;
    }

    /**
     * Gets the value of the charge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCharge(BigDecimal value) {
        this.charge = value;
    }

    /**
     * Gets the value of the currentInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentInterest() {
        return currentInterest;
    }

    /**
     * Sets the value of the currentInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentInterest(BigDecimal value) {
        this.currentInterest = value;
    }

    /**
     * Gets the value of the currentPeriodCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentPeriodCharge() {
        return currentPeriodCharge;
    }

    /**
     * Sets the value of the currentPeriodCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentPeriodCharge(BigDecimal value) {
        this.currentPeriodCharge = value;
    }

    /**
     * Gets the value of the currentPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentPrincipal() {
        return currentPrincipal;
    }

    /**
     * Sets the value of the currentPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentPrincipal(BigDecimal value) {
        this.currentPrincipal = value;
    }

    /**
     * Gets the value of the penalbond property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPenalbond() {
        return penalbond;
    }

    /**
     * Sets the value of the penalbond property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPenalbond(BigDecimal value) {
        this.penalbond = value;
    }

    /**
     * Gets the value of the surplusPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSurplusPrincipal() {
        return surplusPrincipal;
    }

    /**
     * Sets the value of the surplusPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSurplusPrincipal(BigDecimal value) {
        this.surplusPrincipal = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotal(BigDecimal value) {
        this.total = value;
    }

    /**
     * Gets the value of the totalInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    /**
     * Sets the value of the totalInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalInterest(BigDecimal value) {
        this.totalInterest = value;
    }

    /**
     * Gets the value of the totalOverdueFines property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalOverdueFines() {
        return totalOverdueFines;
    }

    /**
     * Sets the value of the totalOverdueFines property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalOverdueFines(BigDecimal value) {
        this.totalOverdueFines = value;
    }

    /**
     * Gets the value of the totalOverdueInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalOverdueInterest() {
        return totalOverdueInterest;
    }

    /**
     * Sets the value of the totalOverdueInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalOverdueInterest(BigDecimal value) {
        this.totalOverdueInterest = value;
    }

    /**
     * Gets the value of the totalPeriodCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPeriodCharge() {
        return totalPeriodCharge;
    }

    /**
     * Sets the value of the totalPeriodCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPeriodCharge(BigDecimal value) {
        this.totalPeriodCharge = value;
    }

    /**
     * Gets the value of the totalPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPrincipal() {
        return totalPrincipal;
    }

    /**
     * Sets the value of the totalPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPrincipal(BigDecimal value) {
        this.totalPrincipal = value;
    }

}

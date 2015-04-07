
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for qyOverdueListObj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="qyOverdueListObj">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overdueDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overdueStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odOverhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odCharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odForfeit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odLatefee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odDamages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofOverhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofCharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofForfeit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofLatefee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofDamages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfOverhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfCharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfForfeit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfLatefee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="odfDamages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "qyOverdueListObj", propOrder = {
    "overdueDays",
    "overdueStart",
    "odCorpus",
    "odInterest",
    "odOverhead",
    "odCharges",
    "odForfeit",
    "odLatefee",
    "odDamages",
    "ofCorpus",
    "ofInterest",
    "ofOverhead",
    "ofCharges",
    "ofForfeit",
    "ofLatefee",
    "ofDamages",
    "odfCorpus",
    "odfInterest",
    "odfOverhead",
    "odfCharges",
    "odfForfeit",
    "odfLatefee",
    "odfDamages"
})
public class QyOverdueListObj {

    protected String overdueDays;
    protected String overdueStart;
    protected String odCorpus;
    protected String odInterest;
    protected String odOverhead;
    protected String odCharges;
    protected String odForfeit;
    protected String odLatefee;
    protected String odDamages;
    protected String ofCorpus;
    protected String ofInterest;
    protected String ofOverhead;
    protected String ofCharges;
    protected String ofForfeit;
    protected String ofLatefee;
    protected String ofDamages;
    protected String odfCorpus;
    protected String odfInterest;
    protected String odfOverhead;
    protected String odfCharges;
    protected String odfForfeit;
    protected String odfLatefee;
    protected String odfDamages;

    /**
     * Gets the value of the overdueDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverdueDays() {
        return overdueDays;
    }

    /**
     * Sets the value of the overdueDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverdueDays(String value) {
        this.overdueDays = value;
    }

    /**
     * Gets the value of the overdueStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverdueStart() {
        return overdueStart;
    }

    /**
     * Sets the value of the overdueStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverdueStart(String value) {
        this.overdueStart = value;
    }

    /**
     * Gets the value of the odCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdCorpus() {
        return odCorpus;
    }

    /**
     * Sets the value of the odCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdCorpus(String value) {
        this.odCorpus = value;
    }

    /**
     * Gets the value of the odInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdInterest() {
        return odInterest;
    }

    /**
     * Sets the value of the odInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdInterest(String value) {
        this.odInterest = value;
    }

    /**
     * Gets the value of the odOverhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdOverhead() {
        return odOverhead;
    }

    /**
     * Sets the value of the odOverhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdOverhead(String value) {
        this.odOverhead = value;
    }

    /**
     * Gets the value of the odCharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdCharges() {
        return odCharges;
    }

    /**
     * Sets the value of the odCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdCharges(String value) {
        this.odCharges = value;
    }

    /**
     * Gets the value of the odForfeit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdForfeit() {
        return odForfeit;
    }

    /**
     * Sets the value of the odForfeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdForfeit(String value) {
        this.odForfeit = value;
    }

    /**
     * Gets the value of the odLatefee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdLatefee() {
        return odLatefee;
    }

    /**
     * Sets the value of the odLatefee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdLatefee(String value) {
        this.odLatefee = value;
    }

    /**
     * Gets the value of the odDamages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdDamages() {
        return odDamages;
    }

    /**
     * Sets the value of the odDamages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdDamages(String value) {
        this.odDamages = value;
    }

    /**
     * Gets the value of the ofCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfCorpus() {
        return ofCorpus;
    }

    /**
     * Sets the value of the ofCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfCorpus(String value) {
        this.ofCorpus = value;
    }

    /**
     * Gets the value of the ofInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfInterest() {
        return ofInterest;
    }

    /**
     * Sets the value of the ofInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfInterest(String value) {
        this.ofInterest = value;
    }

    /**
     * Gets the value of the ofOverhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfOverhead() {
        return ofOverhead;
    }

    /**
     * Sets the value of the ofOverhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfOverhead(String value) {
        this.ofOverhead = value;
    }

    /**
     * Gets the value of the ofCharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfCharges() {
        return ofCharges;
    }

    /**
     * Sets the value of the ofCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfCharges(String value) {
        this.ofCharges = value;
    }

    /**
     * Gets the value of the ofForfeit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfForfeit() {
        return ofForfeit;
    }

    /**
     * Sets the value of the ofForfeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfForfeit(String value) {
        this.ofForfeit = value;
    }

    /**
     * Gets the value of the ofLatefee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfLatefee() {
        return ofLatefee;
    }

    /**
     * Sets the value of the ofLatefee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfLatefee(String value) {
        this.ofLatefee = value;
    }

    /**
     * Gets the value of the ofDamages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfDamages() {
        return ofDamages;
    }

    /**
     * Sets the value of the ofDamages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfDamages(String value) {
        this.ofDamages = value;
    }

    /**
     * Gets the value of the odfCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfCorpus() {
        return odfCorpus;
    }

    /**
     * Sets the value of the odfCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfCorpus(String value) {
        this.odfCorpus = value;
    }

    /**
     * Gets the value of the odfInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfInterest() {
        return odfInterest;
    }

    /**
     * Sets the value of the odfInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfInterest(String value) {
        this.odfInterest = value;
    }

    /**
     * Gets the value of the odfOverhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfOverhead() {
        return odfOverhead;
    }

    /**
     * Sets the value of the odfOverhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfOverhead(String value) {
        this.odfOverhead = value;
    }

    /**
     * Gets the value of the odfCharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfCharges() {
        return odfCharges;
    }

    /**
     * Sets the value of the odfCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfCharges(String value) {
        this.odfCharges = value;
    }

    /**
     * Gets the value of the odfForfeit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfForfeit() {
        return odfForfeit;
    }

    /**
     * Sets the value of the odfForfeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfForfeit(String value) {
        this.odfForfeit = value;
    }

    /**
     * Gets the value of the odfLatefee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfLatefee() {
        return odfLatefee;
    }

    /**
     * Sets the value of the odfLatefee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfLatefee(String value) {
        this.odfLatefee = value;
    }

    /**
     * Gets the value of the odfDamages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdfDamages() {
        return odfDamages;
    }

    /**
     * Sets the value of the odfDamages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdfDamages(String value) {
        this.odfDamages = value;
    }

}

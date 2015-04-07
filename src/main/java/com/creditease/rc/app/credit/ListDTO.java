
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contract_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="client_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sort_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amortisation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="month_return" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start_time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="end_time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ahead_amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ahead_time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payoff_amortisation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="residual_corpus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="p_breach_num" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="p_breach_days" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="most_breach_days" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listDTO", propOrder = {
    "contractId",
    "idNumber",
    "clientName",
    "sortName",
    "amount",
    "amortisation",
    "monthReturn",
    "status",
    "startTime",
    "endTime",
    "aheadAmount",
    "aheadTime",
    "payoffAmortisation",
    "residualCorpus",
    "pBreachNum",
    "pBreachDays",
    "mostBreachDays"
})
public class ListDTO {

    @XmlElement(name = "contract_id", required = true)
    protected String contractId;
    @XmlElement(name = "id_number", required = true)
    protected String idNumber;
    @XmlElement(name = "client_name", required = true)
    protected String clientName;
    @XmlElement(name = "sort_name", required = true)
    protected String sortName;
    @XmlElement(required = true)
    protected String amount;
    @XmlElement(required = true)
    protected String amortisation;
    @XmlElement(name = "month_return", required = true)
    protected String monthReturn;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(name = "start_time", required = true)
    protected String startTime;
    @XmlElement(name = "end_time", required = true)
    protected String endTime;
    @XmlElement(name = "ahead_amount", required = true)
    protected String aheadAmount;
    @XmlElement(name = "ahead_time", required = true)
    protected String aheadTime;
    @XmlElement(name = "payoff_amortisation", required = true)
    protected String payoffAmortisation;
    @XmlElement(name = "residual_corpus", required = true)
    protected String residualCorpus;
    @XmlElement(name = "p_breach_num", required = true)
    protected String pBreachNum;
    @XmlElement(name = "p_breach_days", required = true)
    protected String pBreachDays;
    @XmlElement(name = "most_breach_days", required = true)
    protected String mostBreachDays;

    /**
     * Gets the value of the contractId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractId(String value) {
        this.contractId = value;
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
     * Gets the value of the sortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * Sets the value of the sortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortName(String value) {
        this.sortName = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
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
     * Gets the value of the monthReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthReturn() {
        return monthReturn;
    }

    /**
     * Sets the value of the monthReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthReturn(String value) {
        this.monthReturn = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the aheadAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAheadAmount() {
        return aheadAmount;
    }

    /**
     * Sets the value of the aheadAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAheadAmount(String value) {
        this.aheadAmount = value;
    }

    /**
     * Gets the value of the aheadTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAheadTime() {
        return aheadTime;
    }

    /**
     * Sets the value of the aheadTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAheadTime(String value) {
        this.aheadTime = value;
    }

    /**
     * Gets the value of the payoffAmortisation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayoffAmortisation() {
        return payoffAmortisation;
    }

    /**
     * Sets the value of the payoffAmortisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayoffAmortisation(String value) {
        this.payoffAmortisation = value;
    }

    /**
     * Gets the value of the residualCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidualCorpus() {
        return residualCorpus;
    }

    /**
     * Sets the value of the residualCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidualCorpus(String value) {
        this.residualCorpus = value;
    }

    /**
     * Gets the value of the pBreachNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPBreachNum() {
        return pBreachNum;
    }

    /**
     * Sets the value of the pBreachNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPBreachNum(String value) {
        this.pBreachNum = value;
    }

    /**
     * Gets the value of the pBreachDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPBreachDays() {
        return pBreachDays;
    }

    /**
     * Sets the value of the pBreachDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPBreachDays(String value) {
        this.pBreachDays = value;
    }

    /**
     * Gets the value of the mostBreachDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostBreachDays() {
        return mostBreachDays;
    }

    /**
     * Sets the value of the mostBreachDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostBreachDays(String value) {
        this.mostBreachDays = value;
    }

}

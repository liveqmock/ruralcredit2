
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mortgageRepayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgageRepayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="borrowSort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bsTransportId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="decesionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isPrepayment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="prepaymentAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="prepaymentCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="repaymentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repaymentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="restPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgageRepayment", propOrder = {
    "borrowSort",
    "bsTransportId",
    "contractId",
    "createDate",
    "creator",
    "decesionId",
    "isPrepayment",
    "memo",
    "operDate",
    "operator",
    "prepaymentAmount",
    "prepaymentCost",
    "repaymentDate",
    "repaymentId",
    "restPrincipal"
})
public class MortgageRepayment {

    protected String borrowSort;
    protected Long bsTransportId;
    protected Long contractId;
    protected String createDate;
    protected Long creator;
    protected Long decesionId;
    protected String isPrepayment;
    protected String memo;
    protected String operDate;
    protected Long operator;
    protected BigDecimal prepaymentAmount;
    protected BigDecimal prepaymentCost;
    protected String repaymentDate;
    protected Long repaymentId;
    protected BigDecimal restPrincipal;

    /**
     * Gets the value of the borrowSort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowSort() {
        return borrowSort;
    }

    /**
     * Sets the value of the borrowSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowSort(String value) {
        this.borrowSort = value;
    }

    /**
     * Gets the value of the bsTransportId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBsTransportId() {
        return bsTransportId;
    }

    /**
     * Sets the value of the bsTransportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBsTransportId(Long value) {
        this.bsTransportId = value;
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
     * Gets the value of the decesionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDecesionId() {
        return decesionId;
    }

    /**
     * Sets the value of the decesionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDecesionId(Long value) {
        this.decesionId = value;
    }

    /**
     * Gets the value of the isPrepayment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPrepayment() {
        return isPrepayment;
    }

    /**
     * Sets the value of the isPrepayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPrepayment(String value) {
        this.isPrepayment = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
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

    /**
     * Gets the value of the prepaymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrepaymentAmount() {
        return prepaymentAmount;
    }

    /**
     * Sets the value of the prepaymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrepaymentAmount(BigDecimal value) {
        this.prepaymentAmount = value;
    }

    /**
     * Gets the value of the prepaymentCost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrepaymentCost() {
        return prepaymentCost;
    }

    /**
     * Sets the value of the prepaymentCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrepaymentCost(BigDecimal value) {
        this.prepaymentCost = value;
    }

    /**
     * Gets the value of the repaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
     * Sets the value of the repaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaymentDate(String value) {
        this.repaymentDate = value;
    }

    /**
     * Gets the value of the repaymentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRepaymentId() {
        return repaymentId;
    }

    /**
     * Sets the value of the repaymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRepaymentId(Long value) {
        this.repaymentId = value;
    }

    /**
     * Gets the value of the restPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRestPrincipal() {
        return restPrincipal;
    }

    /**
     * Sets the value of the restPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRestPrincipal(BigDecimal value) {
        this.restPrincipal = value;
    }

}

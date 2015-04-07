
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contractStateReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractStateReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractStateReq", propOrder = {
    "applyId",
    "borrowState",
    "matchId",
    "operationId"
})
public class ContractStateReq {

    protected String applyId;
    protected String borrowState;
    protected String matchId;
    protected Long operationId;

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
     * Gets the value of the borrowState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowState() {
        return borrowState;
    }

    /**
     * Sets the value of the borrowState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowState(String value) {
        this.borrowState = value;
    }

    /**
     * Gets the value of the matchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * Sets the value of the matchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchId(String value) {
        this.matchId = value;
    }

    /**
     * Gets the value of the operationId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperationId() {
        return operationId;
    }

    /**
     * Sets the value of the operationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperationId(Long value) {
        this.operationId = value;
    }

}

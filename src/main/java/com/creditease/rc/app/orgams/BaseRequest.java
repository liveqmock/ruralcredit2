
package com.creditease.rc.app.orgams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="financeFormId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="systemSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseRequest", propOrder = {
    "financeFormId",
    "matchId",
    "operationId",
    "systemSign"
})
public class BaseRequest {

    protected String financeFormId;
    protected String matchId;
    protected Long operationId;
    protected String systemSign;

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

    /**
     * Gets the value of the systemSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemSign() {
        return systemSign;
    }

    /**
     * Sets the value of the systemSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemSign(String value) {
        this.systemSign = value;
    }

}

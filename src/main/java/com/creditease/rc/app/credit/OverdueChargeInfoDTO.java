
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OverdueChargeInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverdueChargeInfoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="o_chargeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="o_receivableCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="o_receivedCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverdueChargeInfoDTO", propOrder = {
    "oChargeTypeCode",
    "oReceivableCharge",
    "oReceivedCharge"
})
public class OverdueChargeInfoDTO {

    @XmlElement(name = "o_chargeTypeCode")
    protected String oChargeTypeCode;
    @XmlElement(name = "o_receivableCharge")
    protected String oReceivableCharge;
    @XmlElement(name = "o_receivedCharge")
    protected String oReceivedCharge;

    /**
     * Gets the value of the oChargeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOChargeTypeCode() {
        return oChargeTypeCode;
    }

    /**
     * Sets the value of the oChargeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOChargeTypeCode(String value) {
        this.oChargeTypeCode = value;
    }

    /**
     * Gets the value of the oReceivableCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOReceivableCharge() {
        return oReceivableCharge;
    }

    /**
     * Sets the value of the oReceivableCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOReceivableCharge(String value) {
        this.oReceivableCharge = value;
    }

    /**
     * Gets the value of the oReceivedCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOReceivedCharge() {
        return oReceivedCharge;
    }

    /**
     * Sets the value of the oReceivedCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOReceivedCharge(String value) {
        this.oReceivedCharge = value;
    }

}

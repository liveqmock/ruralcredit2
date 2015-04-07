
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for increasedChargeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="increasedChargeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivableCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receiveType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "increasedChargeInfo", propOrder = {
    "chargeTypeCode",
    "cycle",
    "receivableCharge",
    "receiveType"
})
public class IncreasedChargeInfo {

    @XmlElement(required = true)
    protected String chargeTypeCode;
    protected String cycle;
    @XmlElement(required = true)
    protected BigDecimal receivableCharge;
    protected String receiveType;

    /**
     * Gets the value of the chargeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTypeCode() {
        return chargeTypeCode;
    }

    /**
     * Sets the value of the chargeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTypeCode(String value) {
        this.chargeTypeCode = value;
    }

    /**
     * Gets the value of the cycle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * Sets the value of the cycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCycle(String value) {
        this.cycle = value;
    }

    /**
     * Gets the value of the receivableCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivableCharge() {
        return receivableCharge;
    }

    /**
     * Sets the value of the receivableCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivableCharge(BigDecimal value) {
        this.receivableCharge = value;
    }

    /**
     * Gets the value of the receiveType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveType() {
        return receiveType;
    }

    /**
     * Sets the value of the receiveType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveType(String value) {
        this.receiveType = value;
    }

}

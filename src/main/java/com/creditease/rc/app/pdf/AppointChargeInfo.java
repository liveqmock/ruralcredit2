
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appointChargeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appointChargeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivableCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appointChargeInfo", propOrder = {
    "chargeTypeCode",
    "receivableCharge"
})
public class AppointChargeInfo {

    @XmlElement(required = true)
    protected String chargeTypeCode;
    @XmlElement(required = true)
    protected BigDecimal receivableCharge;

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

}

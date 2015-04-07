
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppointChargeInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppointChargeInfoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivableCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointChargeInfoDTO", propOrder = {
    "chargeTypeCode",
    "receivableCharge"
})
public class AppointChargeInfoDTO {

    protected String chargeTypeCode;
    protected String receivableCharge;

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
     *     {@link String }
     *     
     */
    public String getReceivableCharge() {
        return receivableCharge;
    }

    /**
     * Sets the value of the receivableCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivableCharge(String value) {
        this.receivableCharge = value;
    }

}

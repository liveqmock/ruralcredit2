
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chargeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="chargeFormula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chargeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chargeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargeInfo", propOrder = {
    "charge",
    "chargeFormula",
    "chargeName",
    "chargeType"
})
public class ChargeInfo {

    @XmlElement(required = true)
    protected BigDecimal charge;
    @XmlElement(required = true)
    protected String chargeFormula;
    @XmlElement(required = true)
    protected String chargeName;
    @XmlElement(required = true)
    protected String chargeType;

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
     * Gets the value of the chargeFormula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeFormula() {
        return chargeFormula;
    }

    /**
     * Sets the value of the chargeFormula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeFormula(String value) {
        this.chargeFormula = value;
    }

    /**
     * Gets the value of the chargeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeName() {
        return chargeName;
    }

    /**
     * Sets the value of the chargeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeName(String value) {
        this.chargeName = value;
    }

    /**
     * Gets the value of the chargeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * Sets the value of the chargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeType(String value) {
        this.chargeType = value;
    }

}

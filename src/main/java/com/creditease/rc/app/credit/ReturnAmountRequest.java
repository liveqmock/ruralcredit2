
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnAmountRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnAmountRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ifPayAhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registReturnTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnAmountRequest", propOrder = {
    "applyId",
    "ifPayAhead",
    "registReturnTime"
})
public class ReturnAmountRequest {

    protected String applyId;
    protected String ifPayAhead;
    protected String registReturnTime;

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
     * Gets the value of the ifPayAhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfPayAhead() {
        return ifPayAhead;
    }

    /**
     * Sets the value of the ifPayAhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfPayAhead(String value) {
        this.ifPayAhead = value;
    }

    /**
     * Gets the value of the registReturnTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistReturnTime() {
        return registReturnTime;
    }

    /**
     * Sets the value of the registReturnTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistReturnTime(String value) {
        this.registReturnTime = value;
    }

}

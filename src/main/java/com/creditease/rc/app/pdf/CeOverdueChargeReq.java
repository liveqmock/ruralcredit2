
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ceOverdueChargeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ceOverdueChargeReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overdueChargeReq" type="{http://www.creditease.cn}overdueChargeReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceOverdueChargeReq", propOrder = {
    "overdueChargeReq"
})
public class CeOverdueChargeReq {

    protected OverdueChargeReq overdueChargeReq;

    /**
     * Gets the value of the overdueChargeReq property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueChargeReq }
     *     
     */
    public OverdueChargeReq getOverdueChargeReq() {
        return overdueChargeReq;
    }

    /**
     * Sets the value of the overdueChargeReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueChargeReq }
     *     
     */
    public void setOverdueChargeReq(OverdueChargeReq value) {
        this.overdueChargeReq = value;
    }

}

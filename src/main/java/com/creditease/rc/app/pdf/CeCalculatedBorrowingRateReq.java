
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ceCalculatedBorrowingRateReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ceCalculatedBorrowingRateReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rateReqParam" type="{http://www.creditease.cn}rateReqParam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceCalculatedBorrowingRateReq", propOrder = {
    "rateReqParam"
})
public class CeCalculatedBorrowingRateReq {

    protected RateReqParam rateReqParam;

    /**
     * Gets the value of the rateReqParam property.
     * 
     * @return
     *     possible object is
     *     {@link RateReqParam }
     *     
     */
    public RateReqParam getRateReqParam() {
        return rateReqParam;
    }

    /**
     * Sets the value of the rateReqParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateReqParam }
     *     
     */
    public void setRateReqParam(RateReqParam value) {
        this.rateReqParam = value;
    }

}

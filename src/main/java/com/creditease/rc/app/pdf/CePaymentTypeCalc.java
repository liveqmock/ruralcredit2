
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cePaymentTypeCalc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cePaymentTypeCalc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentTypeCalcReq" type="{http://www.creditease.cn}paymentTypeCalcReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cePaymentTypeCalc", propOrder = {
    "paymentTypeCalcReq"
})
public class CePaymentTypeCalc {

    protected PaymentTypeCalcReq paymentTypeCalcReq;

    /**
     * Gets the value of the paymentTypeCalcReq property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTypeCalcReq }
     *     
     */
    public PaymentTypeCalcReq getPaymentTypeCalcReq() {
        return paymentTypeCalcReq;
    }

    /**
     * Sets the value of the paymentTypeCalcReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTypeCalcReq }
     *     
     */
    public void setPaymentTypeCalcReq(PaymentTypeCalcReq value) {
        this.paymentTypeCalcReq = value;
    }

}

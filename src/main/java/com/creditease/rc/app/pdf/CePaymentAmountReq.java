
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cePaymentAmountReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cePaymentAmountReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentAmountReq" type="{http://www.creditease.cn}paymentAmountReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cePaymentAmountReq", propOrder = {
    "paymentAmountReq"
})
public class CePaymentAmountReq {

    protected PaymentAmountReq paymentAmountReq;

    /**
     * Gets the value of the paymentAmountReq property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentAmountReq }
     *     
     */
    public PaymentAmountReq getPaymentAmountReq() {
        return paymentAmountReq;
    }

    /**
     * Sets the value of the paymentAmountReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentAmountReq }
     *     
     */
    public void setPaymentAmountReq(PaymentAmountReq value) {
        this.paymentAmountReq = value;
    }

}

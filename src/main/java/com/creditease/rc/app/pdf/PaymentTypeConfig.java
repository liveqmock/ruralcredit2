
package com.creditease.rc.app.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentTypeConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentTypeConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentTypeParam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="paymentTypeParams" type="{http://www.creditease.cn}paymentTypeParam" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentTypeConfig", propOrder = {
    "paymentTypeCode",
    "paymentTypeParam"
})
public class PaymentTypeConfig {

    @XmlElement(required = true)
    protected String paymentTypeCode;
    protected PaymentTypeConfig.PaymentTypeParam paymentTypeParam;

    /**
     * Gets the value of the paymentTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    /**
     * Sets the value of the paymentTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTypeCode(String value) {
        this.paymentTypeCode = value;
    }

    /**
     * Gets the value of the paymentTypeParam property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTypeConfig.PaymentTypeParam }
     *     
     */
    public PaymentTypeConfig.PaymentTypeParam getPaymentTypeParam() {
        return paymentTypeParam;
    }

    /**
     * Sets the value of the paymentTypeParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTypeConfig.PaymentTypeParam }
     *     
     */
    public void setPaymentTypeParam(PaymentTypeConfig.PaymentTypeParam value) {
        this.paymentTypeParam = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="paymentTypeParams" type="{http://www.creditease.cn}paymentTypeParam" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "paymentTypeParams"
    })
    public static class PaymentTypeParam {

        @XmlElement(required = true)
        protected List<com.creditease.rc.app.pdf.PaymentTypeParam> paymentTypeParams;

        /**
         * Gets the value of the paymentTypeParams property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paymentTypeParams property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPaymentTypeParams().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.creditease.rc.app.pdf.PaymentTypeParam }
         * 
         * 
         */
        public List<com.creditease.rc.app.pdf.PaymentTypeParam> getPaymentTypeParams() {
            if (paymentTypeParams == null) {
                paymentTypeParams = new ArrayList<com.creditease.rc.app.pdf.PaymentTypeParam>();
            }
            return this.paymentTypeParams;
        }

    }

}

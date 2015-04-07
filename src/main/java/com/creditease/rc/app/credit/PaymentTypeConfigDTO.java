
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentTypeConfigDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentTypeConfigDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentTypeParams" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PaymentTypeParamDTO" type="{http://www.creditease.cn/RuralBusyService/}PaymentTypeParamDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "PaymentTypeConfigDTO", propOrder = {
    "paymentTypeCode",
    "paymentTypeParams"
})
public class PaymentTypeConfigDTO {

    protected String paymentTypeCode;
    protected PaymentTypeConfigDTO.PaymentTypeParams paymentTypeParams;

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
     * Gets the value of the paymentTypeParams property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTypeConfigDTO.PaymentTypeParams }
     *     
     */
    public PaymentTypeConfigDTO.PaymentTypeParams getPaymentTypeParams() {
        return paymentTypeParams;
    }

    /**
     * Sets the value of the paymentTypeParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTypeConfigDTO.PaymentTypeParams }
     *     
     */
    public void setPaymentTypeParams(PaymentTypeConfigDTO.PaymentTypeParams value) {
        this.paymentTypeParams = value;
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
     *         &lt;element name="PaymentTypeParamDTO" type="{http://www.creditease.cn/RuralBusyService/}PaymentTypeParamDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "paymentTypeParamDTO"
    })
    public static class PaymentTypeParams {

        @XmlElement(name = "PaymentTypeParamDTO")
        protected List<PaymentTypeParamDTO> paymentTypeParamDTO;

        /**
         * Gets the value of the paymentTypeParamDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paymentTypeParamDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPaymentTypeParamDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PaymentTypeParamDTO }
         * 
         * 
         */
        public List<PaymentTypeParamDTO> getPaymentTypeParamDTO() {
            if (paymentTypeParamDTO == null) {
                paymentTypeParamDTO = new ArrayList<PaymentTypeParamDTO>();
            }
            return this.paymentTypeParamDTO;
        }

    }

}

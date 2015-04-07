
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proPayTypeParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proPayTypeParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paramCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="paymentTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productInstalmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proPayTypeParam", propOrder = {
    "paramCode",
    "paramName",
    "paramValue",
    "paymentTypeCode",
    "paymentTypeName",
    "productInstalmentId"
})
public class ProPayTypeParam {

    protected String paramCode;
    protected String paramName;
    protected BigDecimal paramValue;
    protected String paymentTypeCode;
    protected String paymentTypeName;
    protected long productInstalmentId;

    /**
     * Gets the value of the paramCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * Sets the value of the paramCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamCode(String value) {
        this.paramCode = value;
    }

    /**
     * Gets the value of the paramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets the value of the paramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamName(String value) {
        this.paramName = value;
    }

    /**
     * Gets the value of the paramValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getParamValue() {
        return paramValue;
    }

    /**
     * Sets the value of the paramValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setParamValue(BigDecimal value) {
        this.paramValue = value;
    }

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
     * Gets the value of the paymentTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    /**
     * Sets the value of the paymentTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTypeName(String value) {
        this.paymentTypeName = value;
    }

    /**
     * Gets the value of the productInstalmentId property.
     * 
     */
    public long getProductInstalmentId() {
        return productInstalmentId;
    }

    /**
     * Sets the value of the productInstalmentId property.
     * 
     */
    public void setProductInstalmentId(long value) {
        this.productInstalmentId = value;
    }

}

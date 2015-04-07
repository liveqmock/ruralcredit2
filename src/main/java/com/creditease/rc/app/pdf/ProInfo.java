
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capitalLowerLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="capitalUpperLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="instChargeIRR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="instalments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preChargeIRR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="proVersionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategoryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yearChargeIRR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proInfo", propOrder = {
    "capitalLowerLimit",
    "capitalUpperLimit",
    "instChargeIRR",
    "instalments",
    "preChargeIRR",
    "proVersionName",
    "productCategoryId",
    "productInfoId",
    "productName",
    "version",
    "yearChargeIRR"
})
public class ProInfo {

    protected BigDecimal capitalLowerLimit;
    protected BigDecimal capitalUpperLimit;
    protected BigDecimal instChargeIRR;
    protected String instalments;
    protected BigDecimal preChargeIRR;
    protected String proVersionName;
    protected String productCategoryId;
    protected Long productInfoId;
    protected String productName;
    protected String version;
    protected BigDecimal yearChargeIRR;

    /**
     * Gets the value of the capitalLowerLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCapitalLowerLimit() {
        return capitalLowerLimit;
    }

    /**
     * Sets the value of the capitalLowerLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCapitalLowerLimit(BigDecimal value) {
        this.capitalLowerLimit = value;
    }

    /**
     * Gets the value of the capitalUpperLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCapitalUpperLimit() {
        return capitalUpperLimit;
    }

    /**
     * Sets the value of the capitalUpperLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCapitalUpperLimit(BigDecimal value) {
        this.capitalUpperLimit = value;
    }

    /**
     * Gets the value of the instChargeIRR property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInstChargeIRR() {
        return instChargeIRR;
    }

    /**
     * Sets the value of the instChargeIRR property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInstChargeIRR(BigDecimal value) {
        this.instChargeIRR = value;
    }

    /**
     * Gets the value of the instalments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstalments() {
        return instalments;
    }

    /**
     * Sets the value of the instalments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstalments(String value) {
        this.instalments = value;
    }

    /**
     * Gets the value of the preChargeIRR property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPreChargeIRR() {
        return preChargeIRR;
    }

    /**
     * Sets the value of the preChargeIRR property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPreChargeIRR(BigDecimal value) {
        this.preChargeIRR = value;
    }

    /**
     * Gets the value of the proVersionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProVersionName() {
        return proVersionName;
    }

    /**
     * Sets the value of the proVersionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProVersionName(String value) {
        this.proVersionName = value;
    }

    /**
     * Gets the value of the productCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Sets the value of the productCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategoryId(String value) {
        this.productCategoryId = value;
    }

    /**
     * Gets the value of the productInfoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductInfoId() {
        return productInfoId;
    }

    /**
     * Sets the value of the productInfoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductInfoId(Long value) {
        this.productInfoId = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the yearChargeIRR property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getYearChargeIRR() {
        return yearChargeIRR;
    }

    /**
     * Sets the value of the yearChargeIRR property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setYearChargeIRR(BigDecimal value) {
        this.yearChargeIRR = value;
    }

}

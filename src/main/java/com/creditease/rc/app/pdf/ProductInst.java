
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productInst complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productInst">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defaultRepaymentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instalments" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="irr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="irrUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentRatio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="phase" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="phaseInstalments" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="phaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productInstalmentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="repaymentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productInst", propOrder = {
    "defaultRepaymentType",
    "instalments",
    "irr",
    "irrUnit",
    "paymentRatio",
    "phase",
    "phaseInstalments",
    "phaseName",
    "productInfoId",
    "productInstalmentId",
    "repaymentType"
})
public class ProductInst {

    protected String defaultRepaymentType;
    protected Integer instalments;
    protected String irr;
    protected String irrUnit;
    protected BigDecimal paymentRatio;
    protected Short phase;
    protected Short phaseInstalments;
    protected String phaseName;
    protected Long productInfoId;
    protected Long productInstalmentId;
    protected String repaymentType;

    /**
     * Gets the value of the defaultRepaymentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultRepaymentType() {
        return defaultRepaymentType;
    }

    /**
     * Sets the value of the defaultRepaymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultRepaymentType(String value) {
        this.defaultRepaymentType = value;
    }

    /**
     * Gets the value of the instalments property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInstalments() {
        return instalments;
    }

    /**
     * Sets the value of the instalments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInstalments(Integer value) {
        this.instalments = value;
    }

    /**
     * Gets the value of the irr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIrr() {
        return irr;
    }

    /**
     * Sets the value of the irr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIrr(String value) {
        this.irr = value;
    }

    /**
     * Gets the value of the irrUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIrrUnit() {
        return irrUnit;
    }

    /**
     * Sets the value of the irrUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIrrUnit(String value) {
        this.irrUnit = value;
    }

    /**
     * Gets the value of the paymentRatio property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentRatio() {
        return paymentRatio;
    }

    /**
     * Sets the value of the paymentRatio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentRatio(BigDecimal value) {
        this.paymentRatio = value;
    }

    /**
     * Gets the value of the phase property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setPhase(Short value) {
        this.phase = value;
    }

    /**
     * Gets the value of the phaseInstalments property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getPhaseInstalments() {
        return phaseInstalments;
    }

    /**
     * Sets the value of the phaseInstalments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setPhaseInstalments(Short value) {
        this.phaseInstalments = value;
    }

    /**
     * Gets the value of the phaseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhaseName() {
        return phaseName;
    }

    /**
     * Sets the value of the phaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhaseName(String value) {
        this.phaseName = value;
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
     * Gets the value of the productInstalmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductInstalmentId() {
        return productInstalmentId;
    }

    /**
     * Sets the value of the productInstalmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductInstalmentId(Long value) {
        this.productInstalmentId = value;
    }

    /**
     * Gets the value of the repaymentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaymentType() {
        return repaymentType;
    }

    /**
     * Sets the value of the repaymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaymentType(String value) {
        this.repaymentType = value;
    }

}

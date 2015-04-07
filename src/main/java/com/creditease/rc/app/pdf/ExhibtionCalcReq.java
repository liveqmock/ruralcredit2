
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for exhibtionCalcReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exhibtionCalcReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="exPeriods" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="exhibitionPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="lastPayDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exhibtionCalcReq", propOrder = {
    "contractMoney",
    "exPeriods",
    "exhibitionPrincipal",
    "lastPayDate",
    "period",
    "productInfoId"
})
public class ExhibtionCalcReq {

    @XmlElement(required = true)
    protected BigDecimal contractMoney;
    protected long exPeriods;
    @XmlElement(required = true)
    protected BigDecimal exhibitionPrincipal;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastPayDate;
    protected long period;
    protected long productInfoId;

    /**
     * Gets the value of the contractMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    /**
     * Sets the value of the contractMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractMoney(BigDecimal value) {
        this.contractMoney = value;
    }

    /**
     * Gets the value of the exPeriods property.
     * 
     */
    public long getExPeriods() {
        return exPeriods;
    }

    /**
     * Sets the value of the exPeriods property.
     * 
     */
    public void setExPeriods(long value) {
        this.exPeriods = value;
    }

    /**
     * Gets the value of the exhibitionPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExhibitionPrincipal() {
        return exhibitionPrincipal;
    }

    /**
     * Sets the value of the exhibitionPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExhibitionPrincipal(BigDecimal value) {
        this.exhibitionPrincipal = value;
    }

    /**
     * Gets the value of the lastPayDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastPayDate() {
        return lastPayDate;
    }

    /**
     * Sets the value of the lastPayDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPayDate(XMLGregorianCalendar value) {
        this.lastPayDate = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public long getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(long value) {
        this.period = value;
    }

    /**
     * Gets the value of the productInfoId property.
     * 
     */
    public long getProductInfoId() {
        return productInfoId;
    }

    /**
     * Sets the value of the productInfoId property.
     * 
     */
    public void setProductInfoId(long value) {
        this.productInfoId = value;
    }

}

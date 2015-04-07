
package com.creditease.rc.app.orgams;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for cashFlowVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cashFlowVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interestAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="manageFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="principalAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="returnDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cashFlowVo", propOrder = {
    "interestAmt",
    "manageFee",
    "principalAmt",
    "returnDate"
})
public class CashFlowVo {

    protected BigDecimal interestAmt;
    protected BigDecimal manageFee;
    protected BigDecimal principalAmt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar returnDate;

    /**
     * Gets the value of the interestAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInterestAmt() {
        return interestAmt;
    }

    /**
     * Sets the value of the interestAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInterestAmt(BigDecimal value) {
        this.interestAmt = value;
    }

    /**
     * Gets the value of the manageFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getManageFee() {
        return manageFee;
    }

    /**
     * Sets the value of the manageFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setManageFee(BigDecimal value) {
        this.manageFee = value;
    }

    /**
     * Gets the value of the principalAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrincipalAmt() {
        return principalAmt;
    }

    /**
     * Sets the value of the principalAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrincipalAmt(BigDecimal value) {
        this.principalAmt = value;
    }

    /**
     * Gets the value of the returnDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the value of the returnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReturnDate(XMLGregorianCalendar value) {
        this.returnDate = value;
    }

}

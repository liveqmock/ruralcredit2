
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for settleBeforeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="settleBeforeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beforeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="beforeMoney" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="entrustDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="entrustMoney" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "settleBeforeInfo", propOrder = {
    "beforeDate",
    "beforeMoney",
    "entrustDate",
    "entrustMoney"
})
public class SettleBeforeInfo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beforeDate;
    protected BigDecimal beforeMoney;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entrustDate;
    protected BigDecimal entrustMoney;

    /**
     * Gets the value of the beforeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeforeDate() {
        return beforeDate;
    }

    /**
     * Sets the value of the beforeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeforeDate(XMLGregorianCalendar value) {
        this.beforeDate = value;
    }

    /**
     * Gets the value of the beforeMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBeforeMoney() {
        return beforeMoney;
    }

    /**
     * Sets the value of the beforeMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBeforeMoney(BigDecimal value) {
        this.beforeMoney = value;
    }

    /**
     * Gets the value of the entrustDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntrustDate() {
        return entrustDate;
    }

    /**
     * Sets the value of the entrustDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntrustDate(XMLGregorianCalendar value) {
        this.entrustDate = value;
    }

    /**
     * Gets the value of the entrustMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEntrustMoney() {
        return entrustMoney;
    }

    /**
     * Sets the value of the entrustMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEntrustMoney(BigDecimal value) {
        this.entrustMoney = value;
    }

}

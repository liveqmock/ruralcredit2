
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for creditClosingNoticeDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditClosingNoticeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="closingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="isPrepayment" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditClosingNoticeDTO", propOrder = {
    "closingDate",
    "isPrepayment",
    "transportId"
})
public class CreditClosingNoticeDTO {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar closingDate;
    protected boolean isPrepayment;
    @XmlElement(required = true)
    protected String transportId;

    /**
     * Gets the value of the closingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClosingDate() {
        return closingDate;
    }

    /**
     * Sets the value of the closingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClosingDate(XMLGregorianCalendar value) {
        this.closingDate = value;
    }

    /**
     * Gets the value of the isPrepayment property.
     * 
     */
    public boolean isIsPrepayment() {
        return isPrepayment;
    }

    /**
     * Sets the value of the isPrepayment property.
     * 
     */
    public void setIsPrepayment(boolean value) {
        this.isPrepayment = value;
    }

    /**
     * Gets the value of the transportId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportId() {
        return transportId;
    }

    /**
     * Sets the value of the transportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportId(String value) {
        this.transportId = value;
    }

}

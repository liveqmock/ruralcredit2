
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for paymentResultNoticeDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentResultNoticeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bizId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="completeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="receiveTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="retInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tradeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
@XmlType(name = "paymentResultNoticeDTO", propOrder = {
    "bizId",
    "completeTime",
    "receiveTime",
    "retInfo",
    "state",
    "tradeTime",
    "transportId"
})
public class PaymentResultNoticeDTO {

    @XmlElement(required = true)
    protected String bizId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar completeTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar receiveTime;
    @XmlElement(required = true)
    protected String retInfo;
    @XmlElement(required = true)
    protected String state;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tradeTime;
    @XmlElement(required = true)
    protected String transportId;

    /**
     * Gets the value of the bizId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * Sets the value of the bizId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizId(String value) {
        this.bizId = value;
    }

    /**
     * Gets the value of the completeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompleteTime() {
        return completeTime;
    }

    /**
     * Sets the value of the completeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompleteTime(XMLGregorianCalendar value) {
        this.completeTime = value;
    }

    /**
     * Gets the value of the receiveTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReceiveTime() {
        return receiveTime;
    }

    /**
     * Sets the value of the receiveTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReceiveTime(XMLGregorianCalendar value) {
        this.receiveTime = value;
    }

    /**
     * Gets the value of the retInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetInfo() {
        return retInfo;
    }

    /**
     * Sets the value of the retInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetInfo(String value) {
        this.retInfo = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the tradeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTradeTime() {
        return tradeTime;
    }

    /**
     * Sets the value of the tradeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTradeTime(XMLGregorianCalendar value) {
        this.tradeTime = value;
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

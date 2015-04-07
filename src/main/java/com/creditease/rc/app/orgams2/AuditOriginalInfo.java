
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for auditOriginalInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auditOriginalInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="retInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auditOriginalInfo", propOrder = {
    "confirmDate",
    "pass",
    "retInfo",
    "transportId"
})
public class AuditOriginalInfo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar confirmDate;
    protected boolean pass;
    protected String retInfo;
    protected String transportId;

    /**
     * Gets the value of the confirmDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConfirmDate() {
        return confirmDate;
    }

    /**
     * Sets the value of the confirmDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConfirmDate(XMLGregorianCalendar value) {
        this.confirmDate = value;
    }

    /**
     * Gets the value of the pass property.
     * 
     */
    public boolean isPass() {
        return pass;
    }

    /**
     * Sets the value of the pass property.
     * 
     */
    public void setPass(boolean value) {
        this.pass = value;
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

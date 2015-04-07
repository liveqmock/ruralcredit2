
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contactInfoListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contactInfoListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="r_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationShip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactFixPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contactInfoListDTO", propOrder = {
    "rName",
    "contactType",
    "relationShip",
    "contactCompany",
    "contactAddress",
    "contactPosition",
    "contactFixPhone",
    "contactMobile",
    "contactRemark"
})
public class ContactInfoListDTO {

    @XmlElement(name = "r_name")
    protected String rName;
    protected String contactType;
    protected String relationShip;
    protected String contactCompany;
    protected String contactAddress;
    protected String contactPosition;
    protected String contactFixPhone;
    protected String contactMobile;
    protected String contactRemark;

    /**
     * Gets the value of the rName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRName() {
        return rName;
    }

    /**
     * Sets the value of the rName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRName(String value) {
        this.rName = value;
    }

    /**
     * Gets the value of the contactType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * Sets the value of the contactType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactType(String value) {
        this.contactType = value;
    }

    /**
     * Gets the value of the relationShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationShip() {
        return relationShip;
    }

    /**
     * Sets the value of the relationShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationShip(String value) {
        this.relationShip = value;
    }

    /**
     * Gets the value of the contactCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactCompany() {
        return contactCompany;
    }

    /**
     * Sets the value of the contactCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactCompany(String value) {
        this.contactCompany = value;
    }

    /**
     * Gets the value of the contactAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * Sets the value of the contactAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactAddress(String value) {
        this.contactAddress = value;
    }

    /**
     * Gets the value of the contactPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPosition() {
        return contactPosition;
    }

    /**
     * Sets the value of the contactPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPosition(String value) {
        this.contactPosition = value;
    }

    /**
     * Gets the value of the contactFixPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactFixPhone() {
        return contactFixPhone;
    }

    /**
     * Sets the value of the contactFixPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactFixPhone(String value) {
        this.contactFixPhone = value;
    }

    /**
     * Gets the value of the contactMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * Sets the value of the contactMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactMobile(String value) {
        this.contactMobile = value;
    }

    /**
     * Gets the value of the contactRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactRemark() {
        return contactRemark;
    }

    /**
     * Sets the value of the contactRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactRemark(String value) {
        this.contactRemark = value;
    }

}

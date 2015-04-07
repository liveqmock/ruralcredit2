
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for returnIdListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnIdListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnIdListDTO", propOrder = {
    "returnId",
    "reserveId"
})
public class ReturnIdListDTO {

    protected String returnId;
    protected String reserveId;

    /**
     * Gets the value of the returnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnId() {
        return returnId;
    }

    /**
     * Sets the value of the returnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnId(String value) {
        this.returnId = value;
    }

    /**
     * Gets the value of the reserveId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveId() {
        return reserveId;
    }

    /**
     * Sets the value of the reserveId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveId(String value) {
        this.reserveId = value;
    }

}

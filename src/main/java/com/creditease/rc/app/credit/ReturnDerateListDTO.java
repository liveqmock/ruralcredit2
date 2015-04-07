
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnDerateListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnDerateListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dForfeit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dLatefee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnDerateListDTO", propOrder = {
    "returnDate",
    "dForfeit",
    "dLatefee",
    "fDate",
    "lDate",
    "dReason",
    "nDate"
})
public class ReturnDerateListDTO {

    protected String returnDate;
    protected String dForfeit;
    protected String dLatefee;
    protected String fDate;
    protected String lDate;
    protected String dReason;
    protected String nDate;

    /**
     * Gets the value of the returnDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the value of the returnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnDate(String value) {
        this.returnDate = value;
    }

    /**
     * Gets the value of the dForfeit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDForfeit() {
        return dForfeit;
    }

    /**
     * Sets the value of the dForfeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDForfeit(String value) {
        this.dForfeit = value;
    }

    /**
     * Gets the value of the dLatefee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDLatefee() {
        return dLatefee;
    }

    /**
     * Sets the value of the dLatefee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDLatefee(String value) {
        this.dLatefee = value;
    }

    /**
     * Gets the value of the fDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFDate() {
        return fDate;
    }

    /**
     * Sets the value of the fDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFDate(String value) {
        this.fDate = value;
    }

    /**
     * Gets the value of the lDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLDate() {
        return lDate;
    }

    /**
     * Sets the value of the lDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLDate(String value) {
        this.lDate = value;
    }

    /**
     * Gets the value of the dReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDReason() {
        return dReason;
    }

    /**
     * Sets the value of the dReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDReason(String value) {
        this.dReason = value;
    }

    /**
     * Gets the value of the nDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNDate() {
        return nDate;
    }

    /**
     * Sets the value of the nDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNDate(String value) {
        this.nDate = value;
    }

}


package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applyListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applyListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applyListDTO", propOrder = {
    "applyId",
    "sellId",
    "sellName",
    "officeId",
    "officeName"
})
public class ApplyListDTO {

    protected String applyId;
    protected String sellId;
    protected String sellName;
    protected String officeId;
    protected String officeName;

    /**
     * Gets the value of the applyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * Sets the value of the applyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyId(String value) {
        this.applyId = value;
    }

    /**
     * Gets the value of the sellId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellId() {
        return sellId;
    }

    /**
     * Sets the value of the sellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellId(String value) {
        this.sellId = value;
    }

    /**
     * Gets the value of the sellName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellName() {
        return sellName;
    }

    /**
     * Sets the value of the sellName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellName(String value) {
        this.sellName = value;
    }

    /**
     * Gets the value of the officeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * Sets the value of the officeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeId(String value) {
        this.officeId = value;
    }

    /**
     * Gets the value of the officeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * Sets the value of the officeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeName(String value) {
        this.officeName = value;
    }

}

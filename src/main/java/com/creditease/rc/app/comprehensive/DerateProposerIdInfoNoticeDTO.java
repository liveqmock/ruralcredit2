
package com.creditease.rc.app.comprehensive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for derateProposerIdInfoNoticeDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="derateProposerIdInfoNoticeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="afterPropId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beforePropId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "derateProposerIdInfoNoticeDTO", propOrder = {
    "afterPropId",
    "beforePropId"
})
public class DerateProposerIdInfoNoticeDTO {

    protected String afterPropId;
    protected String beforePropId;

    /**
     * Gets the value of the afterPropId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAfterPropId() {
        return afterPropId;
    }

    /**
     * Sets the value of the afterPropId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAfterPropId(String value) {
        this.afterPropId = value;
    }

    /**
     * Gets the value of the beforePropId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeforePropId() {
        return beforePropId;
    }

    /**
     * Sets the value of the beforePropId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeforePropId(String value) {
        this.beforePropId = value;
    }

}

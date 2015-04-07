
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnSchemeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnSchemeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnSchemeResult" type="{http://www.creditease.cn/RuralBusyService/}ReturnSchemeResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSchemeResponse", propOrder = {
    "returnSchemeResult"
})
public class ReturnSchemeResponse {

    @XmlElement(required = true)
    protected ReturnSchemeResult returnSchemeResult;

    /**
     * Gets the value of the returnSchemeResult property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSchemeResult }
     *     
     */
    public ReturnSchemeResult getReturnSchemeResult() {
        return returnSchemeResult;
    }

    /**
     * Sets the value of the returnSchemeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSchemeResult }
     *     
     */
    public void setReturnSchemeResult(ReturnSchemeResult value) {
        this.returnSchemeResult = value;
    }

}

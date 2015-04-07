
package com.creditease.rc.app.orgams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unMatching complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unMatching">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://service.tradeplatform.ws.component.creditease.com/}unMatchingReqVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unMatching", propOrder = {
    "request"
})
public class UnMatching {

    protected UnMatchingReqVo request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link UnMatchingReqVo }
     *     
     */
    public UnMatchingReqVo getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnMatchingReqVo }
     *     
     */
    public void setRequest(UnMatchingReqVo value) {
        this.request = value;
    }

}

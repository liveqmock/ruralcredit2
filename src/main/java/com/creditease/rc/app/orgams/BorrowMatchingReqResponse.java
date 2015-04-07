
package com.creditease.rc.app.orgams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for borrowMatchingReqResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="borrowMatchingReqResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.tradeplatform.ws.component.creditease.com/}matchBorrowResVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "borrowMatchingReqResponse", propOrder = {
    "_return"
})
public class BorrowMatchingReqResponse {

    @XmlElement(name = "return")
    protected MatchBorrowResVo _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link MatchBorrowResVo }
     *     
     */
    public MatchBorrowResVo getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchBorrowResVo }
     *     
     */
    public void setReturn(MatchBorrowResVo value) {
        this._return = value;
    }

}

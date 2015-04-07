
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QyReserveResultResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QyReserveResultResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qyReserveResult" type="{http://www.creditease.cn/RuralBusyService/}QyReserveResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QyReserveResultResponse", propOrder = {
    "qyReserveResult"
})
public class QyReserveResultResponse {

    @XmlElement(required = true)
    protected QyReserveResult qyReserveResult;

    /**
     * Gets the value of the qyReserveResult property.
     * 
     * @return
     *     possible object is
     *     {@link QyReserveResult }
     *     
     */
    public QyReserveResult getQyReserveResult() {
        return qyReserveResult;
    }

    /**
     * Sets the value of the qyReserveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link QyReserveResult }
     *     
     */
    public void setQyReserveResult(QyReserveResult value) {
        this.qyReserveResult = value;
    }

}

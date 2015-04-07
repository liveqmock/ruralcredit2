
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OverdueInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverdueInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overdueInfoResult" type="{http://www.creditease.cn/RuralBusyService/}OverdueInfoResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverdueInfoResponse", propOrder = {
    "overdueInfoResult"
})
public class OverdueInfoResponse {

    @XmlElement(required = true)
    protected OverdueInfoResult overdueInfoResult;

    /**
     * Gets the value of the overdueInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueInfoResult }
     *     
     */
    public OverdueInfoResult getOverdueInfoResult() {
        return overdueInfoResult;
    }

    /**
     * Sets the value of the overdueInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueInfoResult }
     *     
     */
    public void setOverdueInfoResult(OverdueInfoResult value) {
        this.overdueInfoResult = value;
    }

}

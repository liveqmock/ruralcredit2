
package com.creditease.rc.app.icp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for overdueMicroInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overdueMicroInfoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="historyMaxOverDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="overDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overdueMicroInfoDTO", propOrder = {
    "applyId",
    "historyMaxOverDays",
    "overDays"
})
public class OverdueMicroInfoDTO {

    protected String applyId;
    protected int historyMaxOverDays;
    protected int overDays;

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
     * Gets the value of the historyMaxOverDays property.
     * 
     */
    public int getHistoryMaxOverDays() {
        return historyMaxOverDays;
    }

    /**
     * Sets the value of the historyMaxOverDays property.
     * 
     */
    public void setHistoryMaxOverDays(int value) {
        this.historyMaxOverDays = value;
    }

    /**
     * Gets the value of the overDays property.
     * 
     */
    public int getOverDays() {
        return overDays;
    }

    /**
     * Sets the value of the overDays property.
     * 
     */
    public void setOverDays(int value) {
        this.overDays = value;
    }

}

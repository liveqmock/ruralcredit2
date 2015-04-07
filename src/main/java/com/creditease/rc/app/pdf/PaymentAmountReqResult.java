
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentAmountReqResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentAmountReqResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allAheadSchedule" type="{http://www.creditease.cn}allAheadSchedule"/>
 *         &lt;element name="appointSchedule" type="{http://www.creditease.cn}appointSchedule"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentAmountReqResult", propOrder = {
    "allAheadSchedule",
    "appointSchedule",
    "resultCode",
    "resultMessage"
})
public class PaymentAmountReqResult {

    @XmlElement(required = true)
    protected AllAheadSchedule allAheadSchedule;
    @XmlElement(required = true)
    protected AppointSchedule appointSchedule;
    @XmlElement(required = true)
    protected String resultCode;
    @XmlElement(required = true)
    protected String resultMessage;

    /**
     * Gets the value of the allAheadSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link AllAheadSchedule }
     *     
     */
    public AllAheadSchedule getAllAheadSchedule() {
        return allAheadSchedule;
    }

    /**
     * Sets the value of the allAheadSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllAheadSchedule }
     *     
     */
    public void setAllAheadSchedule(AllAheadSchedule value) {
        this.allAheadSchedule = value;
    }

    /**
     * Gets the value of the appointSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link AppointSchedule }
     *     
     */
    public AppointSchedule getAppointSchedule() {
        return appointSchedule;
    }

    /**
     * Sets the value of the appointSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointSchedule }
     *     
     */
    public void setAppointSchedule(AppointSchedule value) {
        this.appointSchedule = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the value of the resultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMessage(String value) {
        this.resultMessage = value;
    }

}

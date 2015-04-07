
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankListResult" type="{http://www.creditease.cn/RuralBusyService/}BankListResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankListResponse", propOrder = {
    "bankListResult"
})
public class BankListResponse {

    @XmlElement(required = true)
    protected BankListResult bankListResult;

    /**
     * Gets the value of the bankListResult property.
     * 
     * @return
     *     possible object is
     *     {@link BankListResult }
     *     
     */
    public BankListResult getBankListResult() {
        return bankListResult;
    }

    /**
     * Sets the value of the bankListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankListResult }
     *     
     */
    public void setBankListResult(BankListResult value) {
        this.bankListResult = value;
    }

}


package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoanBalanceDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoanBalanceDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loanBalanceDataResult" type="{http://www.creditease.cn/RuralBusyService/}LoanBalanceDataResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoanBalanceDataResponse", propOrder = {
    "loanBalanceDataResult"
})
public class LoanBalanceDataResponse {

    @XmlElement(required = true)
    protected LoanBalanceDataResult loanBalanceDataResult;

    /**
     * Gets the value of the loanBalanceDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link LoanBalanceDataResult }
     *     
     */
    public LoanBalanceDataResult getLoanBalanceDataResult() {
        return loanBalanceDataResult;
    }

    /**
     * Sets the value of the loanBalanceDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoanBalanceDataResult }
     *     
     */
    public void setLoanBalanceDataResult(LoanBalanceDataResult value) {
        this.loanBalanceDataResult = value;
    }

}

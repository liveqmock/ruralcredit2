
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for p2PLender complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="p2PLender">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="contractMoney" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="identNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanContractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monInterest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payMonthMoeny" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="serviceCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "p2PLender", propOrder = {
    "consCharge",
    "contractMoney",
    "identNo",
    "identType",
    "loanContractNo",
    "monInterest",
    "name",
    "payMonthMoeny",
    "serviceCharge"
})
public class P2PLender {

    protected BigDecimal consCharge;
    protected BigDecimal contractMoney;
    @XmlElement(required = true)
    protected String identNo;
    protected String identType;
    protected String loanContractNo;
    protected BigDecimal monInterest;
    @XmlElement(required = true)
    protected String name;
    protected BigDecimal payMonthMoeny;
    protected BigDecimal serviceCharge;

    /**
     * Gets the value of the consCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getConsCharge() {
        return consCharge;
    }

    /**
     * Sets the value of the consCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setConsCharge(BigDecimal value) {
        this.consCharge = value;
    }

    /**
     * Gets the value of the contractMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    /**
     * Sets the value of the contractMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractMoney(BigDecimal value) {
        this.contractMoney = value;
    }

    /**
     * Gets the value of the identNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentNo() {
        return identNo;
    }

    /**
     * Sets the value of the identNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentNo(String value) {
        this.identNo = value;
    }

    /**
     * Gets the value of the identType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentType() {
        return identType;
    }

    /**
     * Sets the value of the identType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentType(String value) {
        this.identType = value;
    }

    /**
     * Gets the value of the loanContractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanContractNo() {
        return loanContractNo;
    }

    /**
     * Sets the value of the loanContractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanContractNo(String value) {
        this.loanContractNo = value;
    }

    /**
     * Gets the value of the monInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonInterest() {
        return monInterest;
    }

    /**
     * Sets the value of the monInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonInterest(BigDecimal value) {
        this.monInterest = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the payMonthMoeny property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPayMonthMoeny() {
        return payMonthMoeny;
    }

    /**
     * Sets the value of the payMonthMoeny property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPayMonthMoeny(BigDecimal value) {
        this.payMonthMoeny = value;
    }

    /**
     * Gets the value of the serviceCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    /**
     * Sets the value of the serviceCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setServiceCharge(BigDecimal value) {
        this.serviceCharge = value;
    }

}

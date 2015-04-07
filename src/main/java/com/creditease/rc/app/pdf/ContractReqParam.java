
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contractReqParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractReqParam">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn}baseContractReqParam">
 *       &lt;sequence>
 *         &lt;element name="contractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lender" type="{http://www.creditease.cn}contractPart" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractReqParam", propOrder = {
    "contractNo",
    "customerManager",
    "lender"
})
public class ContractReqParam
    extends BaseContractReqParam
{

    protected String contractNo;
    protected String customerManager;
    protected ContractPart lender;

    /**
     * Gets the value of the contractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * Sets the value of the contractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNo(String value) {
        this.contractNo = value;
    }

    /**
     * Gets the value of the customerManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerManager() {
        return customerManager;
    }

    /**
     * Sets the value of the customerManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerManager(String value) {
        this.customerManager = value;
    }

    /**
     * Gets the value of the lender property.
     * 
     * @return
     *     possible object is
     *     {@link ContractPart }
     *     
     */
    public ContractPart getLender() {
        return lender;
    }

    /**
     * Sets the value of the lender property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractPart }
     *     
     */
    public void setLender(ContractPart value) {
        this.lender = value;
    }

}

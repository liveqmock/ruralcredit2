
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoanBalanceDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoanBalanceDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sysId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="signInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoanBalanceDataRequest", propOrder = {
    "applyId",
    "officeId",
    "sellerId",
    "sysId",
    "signInfo"
})
public class LoanBalanceDataRequest {

    @XmlElement(required = true)
    protected String applyId;
    @XmlElement(required = true)
    protected String officeId;
    @XmlElement(required = true)
    protected String sellerId;
    @XmlElement(required = true)
    protected String sysId;
    @XmlElement(required = true)
    protected String signInfo;

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
     * Gets the value of the officeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * Sets the value of the officeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeId(String value) {
        this.officeId = value;
    }

    /**
     * Gets the value of the sellerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * Sets the value of the sellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellerId(String value) {
        this.sellerId = value;
    }

    /**
     * Gets the value of the sysId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * Sets the value of the sysId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysId(String value) {
        this.sysId = value;
    }

    /**
     * Gets the value of the signInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignInfo() {
        return signInfo;
    }

    /**
     * Sets the value of the signInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignInfo(String value) {
        this.signInfo = value;
    }

}

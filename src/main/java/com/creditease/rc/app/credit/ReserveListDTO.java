
package com.creditease.rc.app.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reserveListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reserveListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bizid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applyAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amortisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keapAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keapDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reserveListDTO", propOrder = {
    "applyId",
    "reserveId",
    "bizid",
    "clientName",
    "returnName",
    "idNumber",
    "productId",
    "officeId",
    "applyAmount",
    "amortisation",
    "loanAmount",
    "startTime",
    "returnDate",
    "dAmount",
    "keapAmount",
    "keapDate",
    "returnType",
    "reserveResult",
    "reserveInfo"
})
public class ReserveListDTO {

    protected String applyId;
    protected String reserveId;
    protected String bizid;
    protected String clientName;
    protected String returnName;
    protected String idNumber;
    protected String productId;
    protected String officeId;
    protected String applyAmount;
    protected String amortisation;
    protected String loanAmount;
    protected String startTime;
    protected String returnDate;
    protected String dAmount;
    protected String keapAmount;
    protected String keapDate;
    protected String returnType;
    protected String reserveResult;
    protected String reserveInfo;

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
     * Gets the value of the reserveId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveId() {
        return reserveId;
    }

    /**
     * Sets the value of the reserveId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveId(String value) {
        this.reserveId = value;
    }

    /**
     * Gets the value of the bizid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizid() {
        return bizid;
    }

    /**
     * Sets the value of the bizid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizid(String value) {
        this.bizid = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the returnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnName() {
        return returnName;
    }

    /**
     * Sets the value of the returnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnName(String value) {
        this.returnName = value;
    }

    /**
     * Gets the value of the idNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the value of the idNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumber(String value) {
        this.idNumber = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
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
     * Gets the value of the applyAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyAmount() {
        return applyAmount;
    }

    /**
     * Sets the value of the applyAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyAmount(String value) {
        this.applyAmount = value;
    }

    /**
     * Gets the value of the amortisation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmortisation() {
        return amortisation;
    }

    /**
     * Sets the value of the amortisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmortisation(String value) {
        this.amortisation = value;
    }

    /**
     * Gets the value of the loanAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the value of the loanAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanAmount(String value) {
        this.loanAmount = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the returnDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the value of the returnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnDate(String value) {
        this.returnDate = value;
    }

    /**
     * Gets the value of the dAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDAmount() {
        return dAmount;
    }

    /**
     * Sets the value of the dAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDAmount(String value) {
        this.dAmount = value;
    }

    /**
     * Gets the value of the keapAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeapAmount() {
        return keapAmount;
    }

    /**
     * Sets the value of the keapAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeapAmount(String value) {
        this.keapAmount = value;
    }

    /**
     * Gets the value of the keapDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeapDate() {
        return keapDate;
    }

    /**
     * Sets the value of the keapDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeapDate(String value) {
        this.keapDate = value;
    }

    /**
     * Gets the value of the returnType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * Sets the value of the returnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnType(String value) {
        this.returnType = value;
    }

    /**
     * Gets the value of the reserveResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveResult() {
        return reserveResult;
    }

    /**
     * Sets the value of the reserveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveResult(String value) {
        this.reserveResult = value;
    }

    /**
     * Gets the value of the reserveInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveInfo() {
        return reserveInfo;
    }

    /**
     * Sets the value of the reserveInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveInfo(String value) {
        this.reserveInfo = value;
    }

}

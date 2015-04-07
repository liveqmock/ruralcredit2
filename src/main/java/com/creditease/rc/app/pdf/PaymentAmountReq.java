
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for paymentAmountReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentAmountReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appointmentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="apsList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actualPeriodSchedule" type="{http://www.creditease.cn}actualPeriodSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="auditDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="catagoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="frontChargeDisount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isALLAhead" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lenderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="periodCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prevCalcDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recvFrontCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="reqDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reqSysDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentAmountReq", propOrder = {
    "appointmentDate",
    "apsList",
    "auditDate",
    "catagoryId",
    "contractMoney",
    "departmentId",
    "frontChargeDisount",
    "isALLAhead",
    "lenderDate",
    "periodCount",
    "prevCalcDate",
    "productInfoId",
    "recvFrontCharge",
    "reqDate",
    "reqSysDate"
})
public class PaymentAmountReq {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar appointmentDate;
    protected PaymentAmountReq.ApsList apsList;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar auditDate;
    protected Long catagoryId;
    @XmlElement(required = true)
    protected BigDecimal contractMoney;
    protected Long departmentId;
    protected BigDecimal frontChargeDisount;
    protected boolean isALLAhead;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lenderDate;
    protected int periodCount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prevCalcDate;
    protected Long productInfoId;
    protected BigDecimal recvFrontCharge;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reqDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reqSysDate;

    /**
     * Gets the value of the appointmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Sets the value of the appointmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAppointmentDate(XMLGregorianCalendar value) {
        this.appointmentDate = value;
    }

    /**
     * Gets the value of the apsList property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentAmountReq.ApsList }
     *     
     */
    public PaymentAmountReq.ApsList getApsList() {
        return apsList;
    }

    /**
     * Sets the value of the apsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentAmountReq.ApsList }
     *     
     */
    public void setApsList(PaymentAmountReq.ApsList value) {
        this.apsList = value;
    }

    /**
     * Gets the value of the auditDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAuditDate() {
        return auditDate;
    }

    /**
     * Sets the value of the auditDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAuditDate(XMLGregorianCalendar value) {
        this.auditDate = value;
    }

    /**
     * Gets the value of the catagoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCatagoryId() {
        return catagoryId;
    }

    /**
     * Sets the value of the catagoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCatagoryId(Long value) {
        this.catagoryId = value;
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
     * Gets the value of the departmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDepartmentId(Long value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the frontChargeDisount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFrontChargeDisount() {
        return frontChargeDisount;
    }

    /**
     * Sets the value of the frontChargeDisount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFrontChargeDisount(BigDecimal value) {
        this.frontChargeDisount = value;
    }

    /**
     * Gets the value of the isALLAhead property.
     * 
     */
    public boolean isIsALLAhead() {
        return isALLAhead;
    }

    /**
     * Sets the value of the isALLAhead property.
     * 
     */
    public void setIsALLAhead(boolean value) {
        this.isALLAhead = value;
    }

    /**
     * Gets the value of the lenderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLenderDate() {
        return lenderDate;
    }

    /**
     * Sets the value of the lenderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLenderDate(XMLGregorianCalendar value) {
        this.lenderDate = value;
    }

    /**
     * Gets the value of the periodCount property.
     * 
     */
    public int getPeriodCount() {
        return periodCount;
    }

    /**
     * Sets the value of the periodCount property.
     * 
     */
    public void setPeriodCount(int value) {
        this.periodCount = value;
    }

    /**
     * Gets the value of the prevCalcDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrevCalcDate() {
        return prevCalcDate;
    }

    /**
     * Sets the value of the prevCalcDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrevCalcDate(XMLGregorianCalendar value) {
        this.prevCalcDate = value;
    }

    /**
     * Gets the value of the productInfoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductInfoId() {
        return productInfoId;
    }

    /**
     * Sets the value of the productInfoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductInfoId(Long value) {
        this.productInfoId = value;
    }

    /**
     * Gets the value of the recvFrontCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecvFrontCharge() {
        return recvFrontCharge;
    }

    /**
     * Sets the value of the recvFrontCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecvFrontCharge(BigDecimal value) {
        this.recvFrontCharge = value;
    }

    /**
     * Gets the value of the reqDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReqDate() {
        return reqDate;
    }

    /**
     * Sets the value of the reqDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReqDate(XMLGregorianCalendar value) {
        this.reqDate = value;
    }

    /**
     * Gets the value of the reqSysDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReqSysDate() {
        return reqSysDate;
    }

    /**
     * Sets the value of the reqSysDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReqSysDate(XMLGregorianCalendar value) {
        this.reqSysDate = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="actualPeriodSchedule" type="{http://www.creditease.cn}actualPeriodSchedule" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "actualPeriodSchedule"
    })
    public static class ApsList {

        protected List<ActualPeriodSchedule> actualPeriodSchedule;

        /**
         * Gets the value of the actualPeriodSchedule property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actualPeriodSchedule property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActualPeriodSchedule().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActualPeriodSchedule }
         * 
         * 
         */
        public List<ActualPeriodSchedule> getActualPeriodSchedule() {
            if (actualPeriodSchedule == null) {
                actualPeriodSchedule = new ArrayList<ActualPeriodSchedule>();
            }
            return this.actualPeriodSchedule;
        }

    }

}

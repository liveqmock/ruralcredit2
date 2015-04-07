
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
 * <p>Java class for repaymentPlanReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="repaymentPlanReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auditDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="catagoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="firstInterestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstPayDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frontChargeDiscountList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="isPhoenix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="judgeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lenderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="paymentTypeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="paymentTypeConfig" type="{http://www.creditease.cn}paymentTypeConfig" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodChargeDiscountList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="reqDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "repaymentPlanReq", propOrder = {
    "auditDate",
    "catagoryId",
    "contractMoney",
    "departmentId",
    "firstInterestType",
    "firstPayDateType",
    "frontChargeDiscountList",
    "isPhoenix",
    "judgeDate",
    "lenderDate",
    "paymentTypeList",
    "periodChargeDiscountList",
    "periodCount",
    "productInfoId",
    "reqDate"
})
public class RepaymentPlanReq {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar auditDate;
    protected Long catagoryId;
    @XmlElement(required = true)
    protected BigDecimal contractMoney;
    protected long departmentId;
    protected String firstInterestType;
    protected String firstPayDateType;
    protected RepaymentPlanReq.FrontChargeDiscountList frontChargeDiscountList;
    protected String isPhoenix;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar judgeDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lenderDate;
    protected RepaymentPlanReq.PaymentTypeList paymentTypeList;
    protected RepaymentPlanReq.PeriodChargeDiscountList periodChargeDiscountList;
    protected int periodCount;
    protected Long productInfoId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reqDate;

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
     */
    public long getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     */
    public void setDepartmentId(long value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the firstInterestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstInterestType() {
        return firstInterestType;
    }

    /**
     * Sets the value of the firstInterestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstInterestType(String value) {
        this.firstInterestType = value;
    }

    /**
     * Gets the value of the firstPayDateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPayDateType() {
        return firstPayDateType;
    }

    /**
     * Sets the value of the firstPayDateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPayDateType(String value) {
        this.firstPayDateType = value;
    }

    /**
     * Gets the value of the frontChargeDiscountList property.
     * 
     * @return
     *     possible object is
     *     {@link RepaymentPlanReq.FrontChargeDiscountList }
     *     
     */
    public RepaymentPlanReq.FrontChargeDiscountList getFrontChargeDiscountList() {
        return frontChargeDiscountList;
    }

    /**
     * Sets the value of the frontChargeDiscountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepaymentPlanReq.FrontChargeDiscountList }
     *     
     */
    public void setFrontChargeDiscountList(RepaymentPlanReq.FrontChargeDiscountList value) {
        this.frontChargeDiscountList = value;
    }

    /**
     * Gets the value of the isPhoenix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPhoenix() {
        return isPhoenix;
    }

    /**
     * Sets the value of the isPhoenix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPhoenix(String value) {
        this.isPhoenix = value;
    }

    /**
     * Gets the value of the judgeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getJudgeDate() {
        return judgeDate;
    }

    /**
     * Sets the value of the judgeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setJudgeDate(XMLGregorianCalendar value) {
        this.judgeDate = value;
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
     * Gets the value of the paymentTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link RepaymentPlanReq.PaymentTypeList }
     *     
     */
    public RepaymentPlanReq.PaymentTypeList getPaymentTypeList() {
        return paymentTypeList;
    }

    /**
     * Sets the value of the paymentTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepaymentPlanReq.PaymentTypeList }
     *     
     */
    public void setPaymentTypeList(RepaymentPlanReq.PaymentTypeList value) {
        this.paymentTypeList = value;
    }

    /**
     * Gets the value of the periodChargeDiscountList property.
     * 
     * @return
     *     possible object is
     *     {@link RepaymentPlanReq.PeriodChargeDiscountList }
     *     
     */
    public RepaymentPlanReq.PeriodChargeDiscountList getPeriodChargeDiscountList() {
        return periodChargeDiscountList;
    }

    /**
     * Sets the value of the periodChargeDiscountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepaymentPlanReq.PeriodChargeDiscountList }
     *     
     */
    public void setPeriodChargeDiscountList(RepaymentPlanReq.PeriodChargeDiscountList value) {
        this.periodChargeDiscountList = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "chargeDiscountInfo"
    })
    public static class FrontChargeDiscountList {

        protected List<ChargeDiscountInfo> chargeDiscountInfo;

        /**
         * Gets the value of the chargeDiscountInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeDiscountInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeDiscountInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeDiscountInfo }
         * 
         * 
         */
        public List<ChargeDiscountInfo> getChargeDiscountInfo() {
            if (chargeDiscountInfo == null) {
                chargeDiscountInfo = new ArrayList<ChargeDiscountInfo>();
            }
            return this.chargeDiscountInfo;
        }

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
     *         &lt;element name="paymentTypeConfig" type="{http://www.creditease.cn}paymentTypeConfig" maxOccurs="unbounded" minOccurs="0"/>
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
        "paymentTypeConfig"
    })
    public static class PaymentTypeList {

        protected List<PaymentTypeConfig> paymentTypeConfig;

        /**
         * Gets the value of the paymentTypeConfig property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paymentTypeConfig property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPaymentTypeConfig().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PaymentTypeConfig }
         * 
         * 
         */
        public List<PaymentTypeConfig> getPaymentTypeConfig() {
            if (paymentTypeConfig == null) {
                paymentTypeConfig = new ArrayList<PaymentTypeConfig>();
            }
            return this.paymentTypeConfig;
        }

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
     *         &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "chargeDiscountInfo"
    })
    public static class PeriodChargeDiscountList {

        protected List<ChargeDiscountInfo> chargeDiscountInfo;

        /**
         * Gets the value of the chargeDiscountInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeDiscountInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeDiscountInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeDiscountInfo }
         * 
         * 
         */
        public List<ChargeDiscountInfo> getChargeDiscountInfo() {
            if (chargeDiscountInfo == null) {
                chargeDiscountInfo = new ArrayList<ChargeDiscountInfo>();
            }
            return this.chargeDiscountInfo;
        }

    }

}


package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for productInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capitalFormula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="capitalLowerLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="capitalUpperLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="contractVersionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creater" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extendedFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstPaydayInterestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstRepaymentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instalmentCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instalmentUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instalmentUnitDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interestScale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isChangeRepDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isGroupMakeLoan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentDateFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proVersionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productOutline" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="productOutlineStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repayProType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repaymentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repaymentDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serChargeScale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="standardTimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productInfo", propOrder = {
    "capitalFormula",
    "capitalLowerLimit",
    "capitalUpperLimit",
    "contractVersionId",
    "createTime",
    "creater",
    "currency",
    "extendedFlag",
    "firstPaydayInterestType",
    "firstRepaymentDate",
    "instalmentCount",
    "instalmentUnit",
    "instalmentUnitDays",
    "interestScale",
    "isChangeRepDate",
    "isGroupMakeLoan",
    "paymentDateFlag",
    "proVersionName",
    "productCategoryId",
    "productInfoId",
    "productName",
    "productOutline",
    "productOutlineStatus",
    "publishTime",
    "remark",
    "repayProType",
    "repaymentDate",
    "repaymentDateType",
    "serChargeScale",
    "standardTimeType",
    "startTime",
    "status",
    "terminateTime",
    "version"
})
public class ProductInfo {

    protected String capitalFormula;
    protected BigDecimal capitalLowerLimit;
    protected BigDecimal capitalUpperLimit;
    protected Long contractVersionId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String creater;
    protected String currency;
    protected String extendedFlag;
    protected String firstPaydayInterestType;
    protected String firstRepaymentDate;
    protected String instalmentCount;
    protected String instalmentUnit;
    protected String instalmentUnitDays;
    protected String interestScale;
    protected String isChangeRepDate;
    protected String isGroupMakeLoan;
    protected String paymentDateFlag;
    protected String proVersionName;
    protected Long productCategoryId;
    protected Long productInfoId;
    protected String productName;
    protected byte[] productOutline;
    protected String productOutlineStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishTime;
    protected String remark;
    protected String repayProType;
    protected String repaymentDate;
    protected String repaymentDateType;
    protected String serChargeScale;
    protected String standardTimeType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar terminateTime;
    protected String version;

    /**
     * Gets the value of the capitalFormula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapitalFormula() {
        return capitalFormula;
    }

    /**
     * Sets the value of the capitalFormula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapitalFormula(String value) {
        this.capitalFormula = value;
    }

    /**
     * Gets the value of the capitalLowerLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCapitalLowerLimit() {
        return capitalLowerLimit;
    }

    /**
     * Sets the value of the capitalLowerLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCapitalLowerLimit(BigDecimal value) {
        this.capitalLowerLimit = value;
    }

    /**
     * Gets the value of the capitalUpperLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCapitalUpperLimit() {
        return capitalUpperLimit;
    }

    /**
     * Sets the value of the capitalUpperLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCapitalUpperLimit(BigDecimal value) {
        this.capitalUpperLimit = value;
    }

    /**
     * Gets the value of the contractVersionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContractVersionId() {
        return contractVersionId;
    }

    /**
     * Sets the value of the contractVersionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContractVersionId(Long value) {
        this.contractVersionId = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the creater property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreater() {
        return creater;
    }

    /**
     * Sets the value of the creater property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreater(String value) {
        this.creater = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the extendedFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendedFlag() {
        return extendedFlag;
    }

    /**
     * Sets the value of the extendedFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendedFlag(String value) {
        this.extendedFlag = value;
    }

    /**
     * Gets the value of the firstPaydayInterestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPaydayInterestType() {
        return firstPaydayInterestType;
    }

    /**
     * Sets the value of the firstPaydayInterestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPaydayInterestType(String value) {
        this.firstPaydayInterestType = value;
    }

    /**
     * Gets the value of the firstRepaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstRepaymentDate() {
        return firstRepaymentDate;
    }

    /**
     * Sets the value of the firstRepaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstRepaymentDate(String value) {
        this.firstRepaymentDate = value;
    }

    /**
     * Gets the value of the instalmentCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstalmentCount() {
        return instalmentCount;
    }

    /**
     * Sets the value of the instalmentCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstalmentCount(String value) {
        this.instalmentCount = value;
    }

    /**
     * Gets the value of the instalmentUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstalmentUnit() {
        return instalmentUnit;
    }

    /**
     * Sets the value of the instalmentUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstalmentUnit(String value) {
        this.instalmentUnit = value;
    }

    /**
     * Gets the value of the instalmentUnitDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstalmentUnitDays() {
        return instalmentUnitDays;
    }

    /**
     * Sets the value of the instalmentUnitDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstalmentUnitDays(String value) {
        this.instalmentUnitDays = value;
    }

    /**
     * Gets the value of the interestScale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterestScale() {
        return interestScale;
    }

    /**
     * Sets the value of the interestScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterestScale(String value) {
        this.interestScale = value;
    }

    /**
     * Gets the value of the isChangeRepDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsChangeRepDate() {
        return isChangeRepDate;
    }

    /**
     * Sets the value of the isChangeRepDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsChangeRepDate(String value) {
        this.isChangeRepDate = value;
    }

    /**
     * Gets the value of the isGroupMakeLoan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsGroupMakeLoan() {
        return isGroupMakeLoan;
    }

    /**
     * Sets the value of the isGroupMakeLoan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsGroupMakeLoan(String value) {
        this.isGroupMakeLoan = value;
    }

    /**
     * Gets the value of the paymentDateFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentDateFlag() {
        return paymentDateFlag;
    }

    /**
     * Sets the value of the paymentDateFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentDateFlag(String value) {
        this.paymentDateFlag = value;
    }

    /**
     * Gets the value of the proVersionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProVersionName() {
        return proVersionName;
    }

    /**
     * Sets the value of the proVersionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProVersionName(String value) {
        this.proVersionName = value;
    }

    /**
     * Gets the value of the productCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Sets the value of the productCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductCategoryId(Long value) {
        this.productCategoryId = value;
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
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the productOutline property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getProductOutline() {
        return productOutline;
    }

    /**
     * Sets the value of the productOutline property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setProductOutline(byte[] value) {
        this.productOutline = ((byte[]) value);
    }

    /**
     * Gets the value of the productOutlineStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOutlineStatus() {
        return productOutlineStatus;
    }

    /**
     * Sets the value of the productOutlineStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOutlineStatus(String value) {
        this.productOutlineStatus = value;
    }

    /**
     * Gets the value of the publishTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishTime() {
        return publishTime;
    }

    /**
     * Sets the value of the publishTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishTime(XMLGregorianCalendar value) {
        this.publishTime = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the repayProType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepayProType() {
        return repayProType;
    }

    /**
     * Sets the value of the repayProType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepayProType(String value) {
        this.repayProType = value;
    }

    /**
     * Gets the value of the repaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
     * Sets the value of the repaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaymentDate(String value) {
        this.repaymentDate = value;
    }

    /**
     * Gets the value of the repaymentDateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaymentDateType() {
        return repaymentDateType;
    }

    /**
     * Sets the value of the repaymentDateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaymentDateType(String value) {
        this.repaymentDateType = value;
    }

    /**
     * Gets the value of the serChargeScale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerChargeScale() {
        return serChargeScale;
    }

    /**
     * Sets the value of the serChargeScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerChargeScale(String value) {
        this.serChargeScale = value;
    }

    /**
     * Gets the value of the standardTimeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardTimeType() {
        return standardTimeType;
    }

    /**
     * Sets the value of the standardTimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardTimeType(String value) {
        this.standardTimeType = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the terminateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTerminateTime() {
        return terminateTime;
    }

    /**
     * Sets the value of the terminateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTerminateTime(XMLGregorianCalendar value) {
        this.terminateTime = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}

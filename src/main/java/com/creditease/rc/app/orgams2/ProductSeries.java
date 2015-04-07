
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for productSeries complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productSeries">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exchangeCycle" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="exchangeTerm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="iconClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCircle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="organId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="organName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTerm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verifyOper" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="verifyRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productSeries", propOrder = {
    "createTime",
    "creator",
    "displayName",
    "exchangeCycle",
    "exchangeTerm",
    "iconClass",
    "isCircle",
    "isReturn",
    "modifyTime",
    "operator",
    "organId",
    "organName",
    "payRule",
    "productId",
    "productName",
    "productTerm",
    "remark",
    "returnRule",
    "sort",
    "state",
    "sysStatus",
    "verifyOper",
    "verifyRemark",
    "verifyTime",
    "version"
})
public class ProductSeries {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected BigDecimal creator;
    protected String displayName;
    protected Long exchangeCycle;
    protected Long exchangeTerm;
    protected String iconClass;
    protected String isCircle;
    protected String isReturn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyTime;
    protected BigDecimal operator;
    protected BigDecimal organId;
    protected String organName;
    protected String payRule;
    protected BigDecimal productId;
    protected String productName;
    protected Long productTerm;
    protected String remark;
    protected String returnRule;
    protected Long sort;
    protected String state;
    protected String sysStatus;
    protected Long verifyOper;
    protected String verifyRemark;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verifyTime;
    protected Long version;

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
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreator(BigDecimal value) {
        this.creator = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the exchangeCycle property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExchangeCycle() {
        return exchangeCycle;
    }

    /**
     * Sets the value of the exchangeCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExchangeCycle(Long value) {
        this.exchangeCycle = value;
    }

    /**
     * Gets the value of the exchangeTerm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExchangeTerm() {
        return exchangeTerm;
    }

    /**
     * Sets the value of the exchangeTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExchangeTerm(Long value) {
        this.exchangeTerm = value;
    }

    /**
     * Gets the value of the iconClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconClass() {
        return iconClass;
    }

    /**
     * Sets the value of the iconClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconClass(String value) {
        this.iconClass = value;
    }

    /**
     * Gets the value of the isCircle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCircle() {
        return isCircle;
    }

    /**
     * Sets the value of the isCircle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCircle(String value) {
        this.isCircle = value;
    }

    /**
     * Gets the value of the isReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReturn() {
        return isReturn;
    }

    /**
     * Sets the value of the isReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReturn(String value) {
        this.isReturn = value;
    }

    /**
     * Gets the value of the modifyTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifyTime() {
        return modifyTime;
    }

    /**
     * Sets the value of the modifyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifyTime(XMLGregorianCalendar value) {
        this.modifyTime = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOperator(BigDecimal value) {
        this.operator = value;
    }

    /**
     * Gets the value of the organId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrganId() {
        return organId;
    }

    /**
     * Sets the value of the organId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrganId(BigDecimal value) {
        this.organId = value;
    }

    /**
     * Gets the value of the organName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganName() {
        return organName;
    }

    /**
     * Sets the value of the organName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganName(String value) {
        this.organName = value;
    }

    /**
     * Gets the value of the payRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayRule() {
        return payRule;
    }

    /**
     * Sets the value of the payRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayRule(String value) {
        this.payRule = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProductId(BigDecimal value) {
        this.productId = value;
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
     * Gets the value of the productTerm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductTerm() {
        return productTerm;
    }

    /**
     * Sets the value of the productTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductTerm(Long value) {
        this.productTerm = value;
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
     * Gets the value of the returnRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnRule() {
        return returnRule;
    }

    /**
     * Sets the value of the returnRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnRule(String value) {
        this.returnRule = value;
    }

    /**
     * Gets the value of the sort property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSort() {
        return sort;
    }

    /**
     * Sets the value of the sort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSort(Long value) {
        this.sort = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the sysStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysStatus() {
        return sysStatus;
    }

    /**
     * Sets the value of the sysStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysStatus(String value) {
        this.sysStatus = value;
    }

    /**
     * Gets the value of the verifyOper property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVerifyOper() {
        return verifyOper;
    }

    /**
     * Sets the value of the verifyOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVerifyOper(Long value) {
        this.verifyOper = value;
    }

    /**
     * Gets the value of the verifyRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerifyRemark() {
        return verifyRemark;
    }

    /**
     * Sets the value of the verifyRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerifyRemark(String value) {
        this.verifyRemark = value;
    }

    /**
     * Gets the value of the verifyTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerifyTime() {
        return verifyTime;
    }

    /**
     * Sets the value of the verifyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerifyTime(XMLGregorianCalendar value) {
        this.verifyTime = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVersion(Long value) {
        this.version = value;
    }

}

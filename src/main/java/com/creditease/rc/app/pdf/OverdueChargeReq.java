
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
 * <p>Java class for overdueChargeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overdueChargeReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
 *         &lt;element name="calcDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="catagoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="periodCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prevCalcDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "overdueChargeReq", propOrder = {
    "apsList",
    "auditDate",
    "calcDate",
    "catagoryId",
    "contractMoney",
    "departmentId",
    "periodCount",
    "prevCalcDate",
    "productInfoId",
    "reqDate"
})
public class OverdueChargeReq {

    protected OverdueChargeReq.ApsList apsList;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar auditDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcDate;
    protected Long catagoryId;
    @XmlElement(required = true)
    protected BigDecimal contractMoney;
    protected long departmentId;
    protected int periodCount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prevCalcDate;
    protected long productInfoId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reqDate;

    /**
     * Gets the value of the apsList property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueChargeReq.ApsList }
     *     
     */
    public OverdueChargeReq.ApsList getApsList() {
        return apsList;
    }

    /**
     * Sets the value of the apsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueChargeReq.ApsList }
     *     
     */
    public void setApsList(OverdueChargeReq.ApsList value) {
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
     * Gets the value of the calcDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcDate() {
        return calcDate;
    }

    /**
     * Sets the value of the calcDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcDate(XMLGregorianCalendar value) {
        this.calcDate = value;
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
     */
    public long getProductInfoId() {
        return productInfoId;
    }

    /**
     * Sets the value of the productInfoId property.
     * 
     */
    public void setProductInfoId(long value) {
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

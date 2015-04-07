
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
 * <p>Java class for actualPeriodSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actualPeriodSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isOverdue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="overdueChargeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actualChargeInfo" type="{http://www.creditease.cn}actualChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="periodChargeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actualChargeInfo" type="{http://www.creditease.cn}actualChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="receivableInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivableMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivablePrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivedInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivedMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivedPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="repayDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualPeriodSchedule", propOrder = {
    "isOverdue",
    "overdueChargeList",
    "period",
    "periodChargeList",
    "receivableInterest",
    "receivableMoney",
    "receivablePrincipal",
    "receivedInterest",
    "receivedMoney",
    "receivedPrincipal",
    "repayDate"
})
public class ActualPeriodSchedule {

    protected boolean isOverdue;
    protected ActualPeriodSchedule.OverdueChargeList overdueChargeList;
    protected int period;
    protected ActualPeriodSchedule.PeriodChargeList periodChargeList;
    @XmlElement(required = true)
    protected BigDecimal receivableInterest;
    @XmlElement(required = true)
    protected BigDecimal receivableMoney;
    @XmlElement(required = true)
    protected BigDecimal receivablePrincipal;
    @XmlElement(required = true)
    protected BigDecimal receivedInterest;
    @XmlElement(required = true)
    protected BigDecimal receivedMoney;
    @XmlElement(required = true)
    protected BigDecimal receivedPrincipal;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar repayDate;

    /**
     * Gets the value of the isOverdue property.
     * 
     */
    public boolean isIsOverdue() {
        return isOverdue;
    }

    /**
     * Sets the value of the isOverdue property.
     * 
     */
    public void setIsOverdue(boolean value) {
        this.isOverdue = value;
    }

    /**
     * Gets the value of the overdueChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link ActualPeriodSchedule.OverdueChargeList }
     *     
     */
    public ActualPeriodSchedule.OverdueChargeList getOverdueChargeList() {
        return overdueChargeList;
    }

    /**
     * Sets the value of the overdueChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualPeriodSchedule.OverdueChargeList }
     *     
     */
    public void setOverdueChargeList(ActualPeriodSchedule.OverdueChargeList value) {
        this.overdueChargeList = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(int value) {
        this.period = value;
    }

    /**
     * Gets the value of the periodChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link ActualPeriodSchedule.PeriodChargeList }
     *     
     */
    public ActualPeriodSchedule.PeriodChargeList getPeriodChargeList() {
        return periodChargeList;
    }

    /**
     * Sets the value of the periodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualPeriodSchedule.PeriodChargeList }
     *     
     */
    public void setPeriodChargeList(ActualPeriodSchedule.PeriodChargeList value) {
        this.periodChargeList = value;
    }

    /**
     * Gets the value of the receivableInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivableInterest() {
        return receivableInterest;
    }

    /**
     * Sets the value of the receivableInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivableInterest(BigDecimal value) {
        this.receivableInterest = value;
    }

    /**
     * Gets the value of the receivableMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivableMoney() {
        return receivableMoney;
    }

    /**
     * Sets the value of the receivableMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivableMoney(BigDecimal value) {
        this.receivableMoney = value;
    }

    /**
     * Gets the value of the receivablePrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivablePrincipal() {
        return receivablePrincipal;
    }

    /**
     * Sets the value of the receivablePrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivablePrincipal(BigDecimal value) {
        this.receivablePrincipal = value;
    }

    /**
     * Gets the value of the receivedInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivedInterest() {
        return receivedInterest;
    }

    /**
     * Sets the value of the receivedInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivedInterest(BigDecimal value) {
        this.receivedInterest = value;
    }

    /**
     * Gets the value of the receivedMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivedMoney() {
        return receivedMoney;
    }

    /**
     * Sets the value of the receivedMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivedMoney(BigDecimal value) {
        this.receivedMoney = value;
    }

    /**
     * Gets the value of the receivedPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivedPrincipal() {
        return receivedPrincipal;
    }

    /**
     * Sets the value of the receivedPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivedPrincipal(BigDecimal value) {
        this.receivedPrincipal = value;
    }

    /**
     * Gets the value of the repayDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRepayDate() {
        return repayDate;
    }

    /**
     * Sets the value of the repayDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRepayDate(XMLGregorianCalendar value) {
        this.repayDate = value;
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
     *         &lt;element name="actualChargeInfo" type="{http://www.creditease.cn}actualChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "actualChargeInfo"
    })
    public static class OverdueChargeList {

        protected List<ActualChargeInfo> actualChargeInfo;

        /**
         * Gets the value of the actualChargeInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actualChargeInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActualChargeInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActualChargeInfo }
         * 
         * 
         */
        public List<ActualChargeInfo> getActualChargeInfo() {
            if (actualChargeInfo == null) {
                actualChargeInfo = new ArrayList<ActualChargeInfo>();
            }
            return this.actualChargeInfo;
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
     *         &lt;element name="actualChargeInfo" type="{http://www.creditease.cn}actualChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "actualChargeInfo"
    })
    public static class PeriodChargeList {

        protected List<ActualChargeInfo> actualChargeInfo;

        /**
         * Gets the value of the actualChargeInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actualChargeInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActualChargeInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActualChargeInfo }
         * 
         * 
         */
        public List<ActualChargeInfo> getActualChargeInfo() {
            if (actualChargeInfo == null) {
                actualChargeInfo = new ArrayList<ActualChargeInfo>();
            }
            return this.actualChargeInfo;
        }

    }

}

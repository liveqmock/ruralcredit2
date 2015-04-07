
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appointPeriodSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appointPeriodSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overdueChargeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appointChargeInfo" type="{http://www.creditease.cn}appointChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="appointChargeInfo" type="{http://www.creditease.cn}appointChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="receivableInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivableMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="receivablePrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appointPeriodSchedule", propOrder = {
    "overdueChargeList",
    "period",
    "periodChargeList",
    "receivableInterest",
    "receivableMoney",
    "receivablePrincipal"
})
public class AppointPeriodSchedule {

    protected AppointPeriodSchedule.OverdueChargeList overdueChargeList;
    protected int period;
    protected AppointPeriodSchedule.PeriodChargeList periodChargeList;
    @XmlElement(required = true)
    protected BigDecimal receivableInterest;
    @XmlElement(required = true)
    protected BigDecimal receivableMoney;
    @XmlElement(required = true)
    protected BigDecimal receivablePrincipal;

    /**
     * Gets the value of the overdueChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointPeriodSchedule.OverdueChargeList }
     *     
     */
    public AppointPeriodSchedule.OverdueChargeList getOverdueChargeList() {
        return overdueChargeList;
    }

    /**
     * Sets the value of the overdueChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointPeriodSchedule.OverdueChargeList }
     *     
     */
    public void setOverdueChargeList(AppointPeriodSchedule.OverdueChargeList value) {
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
     *     {@link AppointPeriodSchedule.PeriodChargeList }
     *     
     */
    public AppointPeriodSchedule.PeriodChargeList getPeriodChargeList() {
        return periodChargeList;
    }

    /**
     * Sets the value of the periodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointPeriodSchedule.PeriodChargeList }
     *     
     */
    public void setPeriodChargeList(AppointPeriodSchedule.PeriodChargeList value) {
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="appointChargeInfo" type="{http://www.creditease.cn}appointChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointChargeInfo"
    })
    public static class OverdueChargeList {

        protected List<AppointChargeInfo> appointChargeInfo;

        /**
         * Gets the value of the appointChargeInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointChargeInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointChargeInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointChargeInfo }
         * 
         * 
         */
        public List<AppointChargeInfo> getAppointChargeInfo() {
            if (appointChargeInfo == null) {
                appointChargeInfo = new ArrayList<AppointChargeInfo>();
            }
            return this.appointChargeInfo;
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
     *         &lt;element name="appointChargeInfo" type="{http://www.creditease.cn}appointChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointChargeInfo"
    })
    public static class PeriodChargeList {

        protected List<AppointChargeInfo> appointChargeInfo;

        /**
         * Gets the value of the appointChargeInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointChargeInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointChargeInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointChargeInfo }
         * 
         * 
         */
        public List<AppointChargeInfo> getAppointChargeInfo() {
            if (appointChargeInfo == null) {
                appointChargeInfo = new ArrayList<AppointChargeInfo>();
            }
            return this.appointChargeInfo;
        }

    }

}

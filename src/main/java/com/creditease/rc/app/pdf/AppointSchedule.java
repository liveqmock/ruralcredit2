
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appointSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appointSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apsList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appointPeriodSchedule" type="{http://www.creditease.cn}appointPeriodSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="totalOverdueChargeList" minOccurs="0">
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
 *         &lt;element name="totalPeriodChargeList" minOccurs="0">
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
 *         &lt;element name="totalReceivableInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalReceivableMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalReceivablePrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appointSchedule", propOrder = {
    "apsList",
    "totalOverdueChargeList",
    "totalPeriodChargeList",
    "totalReceivableInterest",
    "totalReceivableMoney",
    "totalReceivablePrincipal"
})
public class AppointSchedule {

    protected AppointSchedule.ApsList apsList;
    protected AppointSchedule.TotalOverdueChargeList totalOverdueChargeList;
    protected AppointSchedule.TotalPeriodChargeList totalPeriodChargeList;
    @XmlElement(required = true)
    protected BigDecimal totalReceivableInterest;
    @XmlElement(required = true)
    protected BigDecimal totalReceivableMoney;
    @XmlElement(required = true)
    protected BigDecimal totalReceivablePrincipal;

    /**
     * Gets the value of the apsList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointSchedule.ApsList }
     *     
     */
    public AppointSchedule.ApsList getApsList() {
        return apsList;
    }

    /**
     * Sets the value of the apsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointSchedule.ApsList }
     *     
     */
    public void setApsList(AppointSchedule.ApsList value) {
        this.apsList = value;
    }

    /**
     * Gets the value of the totalOverdueChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointSchedule.TotalOverdueChargeList }
     *     
     */
    public AppointSchedule.TotalOverdueChargeList getTotalOverdueChargeList() {
        return totalOverdueChargeList;
    }

    /**
     * Sets the value of the totalOverdueChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointSchedule.TotalOverdueChargeList }
     *     
     */
    public void setTotalOverdueChargeList(AppointSchedule.TotalOverdueChargeList value) {
        this.totalOverdueChargeList = value;
    }

    /**
     * Gets the value of the totalPeriodChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointSchedule.TotalPeriodChargeList }
     *     
     */
    public AppointSchedule.TotalPeriodChargeList getTotalPeriodChargeList() {
        return totalPeriodChargeList;
    }

    /**
     * Sets the value of the totalPeriodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointSchedule.TotalPeriodChargeList }
     *     
     */
    public void setTotalPeriodChargeList(AppointSchedule.TotalPeriodChargeList value) {
        this.totalPeriodChargeList = value;
    }

    /**
     * Gets the value of the totalReceivableInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalReceivableInterest() {
        return totalReceivableInterest;
    }

    /**
     * Sets the value of the totalReceivableInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalReceivableInterest(BigDecimal value) {
        this.totalReceivableInterest = value;
    }

    /**
     * Gets the value of the totalReceivableMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalReceivableMoney() {
        return totalReceivableMoney;
    }

    /**
     * Sets the value of the totalReceivableMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalReceivableMoney(BigDecimal value) {
        this.totalReceivableMoney = value;
    }

    /**
     * Gets the value of the totalReceivablePrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalReceivablePrincipal() {
        return totalReceivablePrincipal;
    }

    /**
     * Sets the value of the totalReceivablePrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalReceivablePrincipal(BigDecimal value) {
        this.totalReceivablePrincipal = value;
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
     *         &lt;element name="appointPeriodSchedule" type="{http://www.creditease.cn}appointPeriodSchedule" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointPeriodSchedule"
    })
    public static class ApsList {

        protected List<AppointPeriodSchedule> appointPeriodSchedule;

        /**
         * Gets the value of the appointPeriodSchedule property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointPeriodSchedule property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointPeriodSchedule().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointPeriodSchedule }
         * 
         * 
         */
        public List<AppointPeriodSchedule> getAppointPeriodSchedule() {
            if (appointPeriodSchedule == null) {
                appointPeriodSchedule = new ArrayList<AppointPeriodSchedule>();
            }
            return this.appointPeriodSchedule;
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
    public static class TotalOverdueChargeList {

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
    public static class TotalPeriodChargeList {

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

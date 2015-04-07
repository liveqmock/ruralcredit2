
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppointPeriodScheduleDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppointPeriodScheduleDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivablePrincipal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivableInterest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivableMoney" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="periodChargeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appointChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="overdueChargeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appointChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointPeriodScheduleDTO", propOrder = {
    "period",
    "receivablePrincipal",
    "receivableInterest",
    "receivableMoney",
    "periodChargeList",
    "overdueChargeList"
})
public class AppointPeriodScheduleDTO {

    @XmlElement(required = true)
    protected String period;
    @XmlElement(required = true)
    protected String receivablePrincipal;
    @XmlElement(required = true)
    protected String receivableInterest;
    @XmlElement(required = true)
    protected String receivableMoney;
    protected AppointPeriodScheduleDTO.PeriodChargeList periodChargeList;
    protected AppointPeriodScheduleDTO.OverdueChargeList overdueChargeList;

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod(String value) {
        this.period = value;
    }

    /**
     * Gets the value of the receivablePrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivablePrincipal() {
        return receivablePrincipal;
    }

    /**
     * Sets the value of the receivablePrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivablePrincipal(String value) {
        this.receivablePrincipal = value;
    }

    /**
     * Gets the value of the receivableInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivableInterest() {
        return receivableInterest;
    }

    /**
     * Sets the value of the receivableInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivableInterest(String value) {
        this.receivableInterest = value;
    }

    /**
     * Gets the value of the receivableMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivableMoney() {
        return receivableMoney;
    }

    /**
     * Sets the value of the receivableMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivableMoney(String value) {
        this.receivableMoney = value;
    }

    /**
     * Gets the value of the periodChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointPeriodScheduleDTO.PeriodChargeList }
     *     
     */
    public AppointPeriodScheduleDTO.PeriodChargeList getPeriodChargeList() {
        return periodChargeList;
    }

    /**
     * Sets the value of the periodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointPeriodScheduleDTO.PeriodChargeList }
     *     
     */
    public void setPeriodChargeList(AppointPeriodScheduleDTO.PeriodChargeList value) {
        this.periodChargeList = value;
    }

    /**
     * Gets the value of the overdueChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointPeriodScheduleDTO.OverdueChargeList }
     *     
     */
    public AppointPeriodScheduleDTO.OverdueChargeList getOverdueChargeList() {
        return overdueChargeList;
    }

    /**
     * Sets the value of the overdueChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointPeriodScheduleDTO.OverdueChargeList }
     *     
     */
    public void setOverdueChargeList(AppointPeriodScheduleDTO.OverdueChargeList value) {
        this.overdueChargeList = value;
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
     *         &lt;element name="appointChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointChargeInfoDTO"
    })
    public static class OverdueChargeList {

        protected List<AppointChargeInfoDTO> appointChargeInfoDTO;

        /**
         * Gets the value of the appointChargeInfoDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointChargeInfoDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointChargeInfoDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointChargeInfoDTO }
         * 
         * 
         */
        public List<AppointChargeInfoDTO> getAppointChargeInfoDTO() {
            if (appointChargeInfoDTO == null) {
                appointChargeInfoDTO = new ArrayList<AppointChargeInfoDTO>();
            }
            return this.appointChargeInfoDTO;
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
     *         &lt;element name="appointChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointChargeInfoDTO"
    })
    public static class PeriodChargeList {

        protected List<AppointChargeInfoDTO> appointChargeInfoDTO;

        /**
         * Gets the value of the appointChargeInfoDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointChargeInfoDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointChargeInfoDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointChargeInfoDTO }
         * 
         * 
         */
        public List<AppointChargeInfoDTO> getAppointChargeInfoDTO() {
            if (appointChargeInfoDTO == null) {
                appointChargeInfoDTO = new ArrayList<AppointChargeInfoDTO>();
            }
            return this.appointChargeInfoDTO;
        }

    }

}

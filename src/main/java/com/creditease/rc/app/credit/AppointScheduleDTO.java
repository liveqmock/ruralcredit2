
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppointScheduleDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppointScheduleDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apsList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appointPeriodScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointPeriodScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="totalReceivablePrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalReceivableInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalReceivableMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalPeriodChargeList" minOccurs="0">
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
 *         &lt;element name="totalOverdueChargeList" minOccurs="0">
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
@XmlType(name = "AppointScheduleDTO", propOrder = {
    "apsList",
    "totalReceivablePrincipal",
    "totalReceivableInterest",
    "totalReceivableMoney",
    "totalPeriodChargeList",
    "totalOverdueChargeList"
})
public class AppointScheduleDTO {

    protected AppointScheduleDTO.ApsList apsList;
    protected String totalReceivablePrincipal;
    protected String totalReceivableInterest;
    protected String totalReceivableMoney;
    protected AppointScheduleDTO.TotalPeriodChargeList totalPeriodChargeList;
    protected AppointScheduleDTO.TotalOverdueChargeList totalOverdueChargeList;

    /**
     * Gets the value of the apsList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointScheduleDTO.ApsList }
     *     
     */
    public AppointScheduleDTO.ApsList getApsList() {
        return apsList;
    }

    /**
     * Sets the value of the apsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointScheduleDTO.ApsList }
     *     
     */
    public void setApsList(AppointScheduleDTO.ApsList value) {
        this.apsList = value;
    }

    /**
     * Gets the value of the totalReceivablePrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalReceivablePrincipal() {
        return totalReceivablePrincipal;
    }

    /**
     * Sets the value of the totalReceivablePrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalReceivablePrincipal(String value) {
        this.totalReceivablePrincipal = value;
    }

    /**
     * Gets the value of the totalReceivableInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalReceivableInterest() {
        return totalReceivableInterest;
    }

    /**
     * Sets the value of the totalReceivableInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalReceivableInterest(String value) {
        this.totalReceivableInterest = value;
    }

    /**
     * Gets the value of the totalReceivableMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalReceivableMoney() {
        return totalReceivableMoney;
    }

    /**
     * Sets the value of the totalReceivableMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalReceivableMoney(String value) {
        this.totalReceivableMoney = value;
    }

    /**
     * Gets the value of the totalPeriodChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointScheduleDTO.TotalPeriodChargeList }
     *     
     */
    public AppointScheduleDTO.TotalPeriodChargeList getTotalPeriodChargeList() {
        return totalPeriodChargeList;
    }

    /**
     * Sets the value of the totalPeriodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointScheduleDTO.TotalPeriodChargeList }
     *     
     */
    public void setTotalPeriodChargeList(AppointScheduleDTO.TotalPeriodChargeList value) {
        this.totalPeriodChargeList = value;
    }

    /**
     * Gets the value of the totalOverdueChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link AppointScheduleDTO.TotalOverdueChargeList }
     *     
     */
    public AppointScheduleDTO.TotalOverdueChargeList getTotalOverdueChargeList() {
        return totalOverdueChargeList;
    }

    /**
     * Sets the value of the totalOverdueChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointScheduleDTO.TotalOverdueChargeList }
     *     
     */
    public void setTotalOverdueChargeList(AppointScheduleDTO.TotalOverdueChargeList value) {
        this.totalOverdueChargeList = value;
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
     *         &lt;element name="appointPeriodScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointPeriodScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointPeriodScheduleDTO"
    })
    public static class ApsList {

        protected List<AppointPeriodScheduleDTO> appointPeriodScheduleDTO;

        /**
         * Gets the value of the appointPeriodScheduleDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointPeriodScheduleDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointPeriodScheduleDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointPeriodScheduleDTO }
         * 
         * 
         */
        public List<AppointPeriodScheduleDTO> getAppointPeriodScheduleDTO() {
            if (appointPeriodScheduleDTO == null) {
                appointPeriodScheduleDTO = new ArrayList<AppointPeriodScheduleDTO>();
            }
            return this.appointPeriodScheduleDTO;
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
    public static class TotalOverdueChargeList {

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
    public static class TotalPeriodChargeList {

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

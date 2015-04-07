
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActualPeriodScheduleDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActualPeriodScheduleDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivablePrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivableInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedlft" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivablelft" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivableMarrearage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivableArrearage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivableMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isOverdue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReturned" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repayDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodChargeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actualChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}ActualChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="overdueChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}OverdueChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ActualPeriodScheduleDTO", propOrder = {
    "period",
    "receivablePrincipal",
    "receivedPrincipal",
    "receivableInterest",
    "receivedInterest",
    "receivedlft",
    "receivablelft",
    "receivableMarrearage",
    "receivableArrearage",
    "receivableMoney",
    "receivedMoney",
    "isOverdue",
    "isReturned",
    "repayDate",
    "overDays",
    "returnTime",
    "periodChargeList",
    "overdueChargeList"
})
public class ActualPeriodScheduleDTO {

    protected String period;
    protected String receivablePrincipal;
    protected String receivedPrincipal;
    protected String receivableInterest;
    protected String receivedInterest;
    protected String receivedlft;
    protected String receivablelft;
    protected String receivableMarrearage;
    protected String receivableArrearage;
    protected String receivableMoney;
    protected String receivedMoney;
    protected String isOverdue;
    protected String isReturned;
    protected String repayDate;
    protected String overDays;
    protected String returnTime;
    protected ActualPeriodScheduleDTO.PeriodChargeList periodChargeList;
    protected ActualPeriodScheduleDTO.OverdueChargeList overdueChargeList;

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
     * Gets the value of the receivedPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedPrincipal() {
        return receivedPrincipal;
    }

    /**
     * Sets the value of the receivedPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedPrincipal(String value) {
        this.receivedPrincipal = value;
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
     * Gets the value of the receivedInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedInterest() {
        return receivedInterest;
    }

    /**
     * Sets the value of the receivedInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedInterest(String value) {
        this.receivedInterest = value;
    }

    /**
     * Gets the value of the receivedlft property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedlft() {
        return receivedlft;
    }

    /**
     * Sets the value of the receivedlft property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedlft(String value) {
        this.receivedlft = value;
    }

    /**
     * Gets the value of the receivablelft property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivablelft() {
        return receivablelft;
    }

    /**
     * Sets the value of the receivablelft property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivablelft(String value) {
        this.receivablelft = value;
    }

    /**
     * Gets the value of the receivableMarrearage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivableMarrearage() {
        return receivableMarrearage;
    }

    /**
     * Sets the value of the receivableMarrearage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivableMarrearage(String value) {
        this.receivableMarrearage = value;
    }

    /**
     * Gets the value of the receivableArrearage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivableArrearage() {
        return receivableArrearage;
    }

    /**
     * Sets the value of the receivableArrearage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivableArrearage(String value) {
        this.receivableArrearage = value;
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
     * Gets the value of the receivedMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedMoney() {
        return receivedMoney;
    }

    /**
     * Sets the value of the receivedMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedMoney(String value) {
        this.receivedMoney = value;
    }

    /**
     * Gets the value of the isOverdue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsOverdue() {
        return isOverdue;
    }

    /**
     * Sets the value of the isOverdue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsOverdue(String value) {
        this.isOverdue = value;
    }

    /**
     * Gets the value of the isReturned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReturned() {
        return isReturned;
    }

    /**
     * Sets the value of the isReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReturned(String value) {
        this.isReturned = value;
    }

    /**
     * Gets the value of the repayDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepayDate() {
        return repayDate;
    }

    /**
     * Sets the value of the repayDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepayDate(String value) {
        this.repayDate = value;
    }

    /**
     * Gets the value of the overDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverDays() {
        return overDays;
    }

    /**
     * Sets the value of the overDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverDays(String value) {
        this.overDays = value;
    }

    /**
     * Gets the value of the returnTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnTime() {
        return returnTime;
    }

    /**
     * Sets the value of the returnTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnTime(String value) {
        this.returnTime = value;
    }

    /**
     * Gets the value of the periodChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link ActualPeriodScheduleDTO.PeriodChargeList }
     *     
     */
    public ActualPeriodScheduleDTO.PeriodChargeList getPeriodChargeList() {
        return periodChargeList;
    }

    /**
     * Sets the value of the periodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualPeriodScheduleDTO.PeriodChargeList }
     *     
     */
    public void setPeriodChargeList(ActualPeriodScheduleDTO.PeriodChargeList value) {
        this.periodChargeList = value;
    }

    /**
     * Gets the value of the overdueChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link ActualPeriodScheduleDTO.OverdueChargeList }
     *     
     */
    public ActualPeriodScheduleDTO.OverdueChargeList getOverdueChargeList() {
        return overdueChargeList;
    }

    /**
     * Sets the value of the overdueChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualPeriodScheduleDTO.OverdueChargeList }
     *     
     */
    public void setOverdueChargeList(ActualPeriodScheduleDTO.OverdueChargeList value) {
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
     *         &lt;element name="overdueChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}OverdueChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "overdueChargeInfoDTO"
    })
    public static class OverdueChargeList {

        protected List<OverdueChargeInfoDTO> overdueChargeInfoDTO;

        /**
         * Gets the value of the overdueChargeInfoDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the overdueChargeInfoDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOverdueChargeInfoDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OverdueChargeInfoDTO }
         * 
         * 
         */
        public List<OverdueChargeInfoDTO> getOverdueChargeInfoDTO() {
            if (overdueChargeInfoDTO == null) {
                overdueChargeInfoDTO = new ArrayList<OverdueChargeInfoDTO>();
            }
            return this.overdueChargeInfoDTO;
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
     *         &lt;element name="actualChargeInfoDTO" type="{http://www.creditease.cn/RuralBusyService/}ActualChargeInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "actualChargeInfoDTO"
    })
    public static class PeriodChargeList {

        protected List<ActualChargeInfoDTO> actualChargeInfoDTO;

        /**
         * Gets the value of the actualChargeInfoDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actualChargeInfoDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActualChargeInfoDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActualChargeInfoDTO }
         * 
         * 
         */
        public List<ActualChargeInfoDTO> getActualChargeInfoDTO() {
            if (actualChargeInfoDTO == null) {
                actualChargeInfoDTO = new ArrayList<ActualChargeInfoDTO>();
            }
            return this.actualChargeInfoDTO;
        }

    }

}

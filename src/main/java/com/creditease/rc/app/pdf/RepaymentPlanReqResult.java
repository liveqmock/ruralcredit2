
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for repaymentPlanReqResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="repaymentPlanReqResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="frontChangeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chargeInfo" type="{http://www.creditease.cn}chargeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodScheduleList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="periodSchedule" type="{http://www.creditease.cn}periodSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="recvMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "repaymentPlanReqResult", propOrder = {
    "frontChangeList",
    "periodScheduleList",
    "recvMoney",
    "resultCode",
    "resultMessage"
})
public class RepaymentPlanReqResult {

    protected RepaymentPlanReqResult.FrontChangeList frontChangeList;
    protected RepaymentPlanReqResult.PeriodScheduleList periodScheduleList;
    @XmlElement(required = true)
    protected BigDecimal recvMoney;
    @XmlElement(required = true)
    protected String resultCode;
    @XmlElement(required = true)
    protected String resultMessage;

    /**
     * Gets the value of the frontChangeList property.
     * 
     * @return
     *     possible object is
     *     {@link RepaymentPlanReqResult.FrontChangeList }
     *     
     */
    public RepaymentPlanReqResult.FrontChangeList getFrontChangeList() {
        return frontChangeList;
    }

    /**
     * Sets the value of the frontChangeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepaymentPlanReqResult.FrontChangeList }
     *     
     */
    public void setFrontChangeList(RepaymentPlanReqResult.FrontChangeList value) {
        this.frontChangeList = value;
    }

    /**
     * Gets the value of the periodScheduleList property.
     * 
     * @return
     *     possible object is
     *     {@link RepaymentPlanReqResult.PeriodScheduleList }
     *     
     */
    public RepaymentPlanReqResult.PeriodScheduleList getPeriodScheduleList() {
        return periodScheduleList;
    }

    /**
     * Sets the value of the periodScheduleList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepaymentPlanReqResult.PeriodScheduleList }
     *     
     */
    public void setPeriodScheduleList(RepaymentPlanReqResult.PeriodScheduleList value) {
        this.periodScheduleList = value;
    }

    /**
     * Gets the value of the recvMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecvMoney() {
        return recvMoney;
    }

    /**
     * Sets the value of the recvMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecvMoney(BigDecimal value) {
        this.recvMoney = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the value of the resultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMessage(String value) {
        this.resultMessage = value;
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
     *         &lt;element name="chargeInfo" type="{http://www.creditease.cn}chargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "chargeInfo"
    })
    public static class FrontChangeList {

        protected List<ChargeInfo> chargeInfo;

        /**
         * Gets the value of the chargeInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeInfo }
         * 
         * 
         */
        public List<ChargeInfo> getChargeInfo() {
            if (chargeInfo == null) {
                chargeInfo = new ArrayList<ChargeInfo>();
            }
            return this.chargeInfo;
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
     *         &lt;element name="periodSchedule" type="{http://www.creditease.cn}periodSchedule" maxOccurs="unbounded" minOccurs="0"/>
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
        "periodSchedule"
    })
    public static class PeriodScheduleList {

        protected List<PeriodSchedule> periodSchedule;

        /**
         * Gets the value of the periodSchedule property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the periodSchedule property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPeriodSchedule().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PeriodSchedule }
         * 
         * 
         */
        public List<PeriodSchedule> getPeriodSchedule() {
            if (periodSchedule == null) {
                periodSchedule = new ArrayList<PeriodSchedule>();
            }
            return this.periodSchedule;
        }

    }

}

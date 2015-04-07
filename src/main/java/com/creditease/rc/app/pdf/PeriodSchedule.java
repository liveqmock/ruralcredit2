
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
 * <p>Java class for periodSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="periodSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accumulativeInterest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="accumulativeMoney" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="accumulativePrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="allheadMoney" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="amortizedInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="amortizedPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="irr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="periodCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="periodChargeList" minOccurs="0">
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
 *         &lt;element name="periodMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="phase" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="principalInterest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="repayDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="surplusPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "periodSchedule", propOrder = {
    "accumulativeInterest",
    "accumulativeMoney",
    "accumulativePrincipal",
    "allheadMoney",
    "amortizedInterest",
    "amortizedPrincipal",
    "irr",
    "period",
    "periodCharge",
    "periodChargeList",
    "periodMoney",
    "phase",
    "principalInterest",
    "repayDate",
    "surplusPrincipal"
})
public class PeriodSchedule {

    protected BigDecimal accumulativeInterest;
    protected BigDecimal accumulativeMoney;
    protected BigDecimal accumulativePrincipal;
    protected BigDecimal allheadMoney;
    @XmlElement(required = true)
    protected BigDecimal amortizedInterest;
    @XmlElement(required = true)
    protected BigDecimal amortizedPrincipal;
    @XmlElement(required = true)
    protected BigDecimal irr;
    protected long period;
    @XmlElement(required = true)
    protected BigDecimal periodCharge;
    protected PeriodSchedule.PeriodChargeList periodChargeList;
    @XmlElement(required = true)
    protected BigDecimal periodMoney;
    protected long phase;
    protected BigDecimal principalInterest;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar repayDate;
    @XmlElement(required = true)
    protected BigDecimal surplusPrincipal;

    /**
     * Gets the value of the accumulativeInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccumulativeInterest() {
        return accumulativeInterest;
    }

    /**
     * Sets the value of the accumulativeInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccumulativeInterest(BigDecimal value) {
        this.accumulativeInterest = value;
    }

    /**
     * Gets the value of the accumulativeMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccumulativeMoney() {
        return accumulativeMoney;
    }

    /**
     * Sets the value of the accumulativeMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccumulativeMoney(BigDecimal value) {
        this.accumulativeMoney = value;
    }

    /**
     * Gets the value of the accumulativePrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccumulativePrincipal() {
        return accumulativePrincipal;
    }

    /**
     * Sets the value of the accumulativePrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccumulativePrincipal(BigDecimal value) {
        this.accumulativePrincipal = value;
    }

    /**
     * Gets the value of the allheadMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAllheadMoney() {
        return allheadMoney;
    }

    /**
     * Sets the value of the allheadMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAllheadMoney(BigDecimal value) {
        this.allheadMoney = value;
    }

    /**
     * Gets the value of the amortizedInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmortizedInterest() {
        return amortizedInterest;
    }

    /**
     * Sets the value of the amortizedInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmortizedInterest(BigDecimal value) {
        this.amortizedInterest = value;
    }

    /**
     * Gets the value of the amortizedPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmortizedPrincipal() {
        return amortizedPrincipal;
    }

    /**
     * Sets the value of the amortizedPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmortizedPrincipal(BigDecimal value) {
        this.amortizedPrincipal = value;
    }

    /**
     * Gets the value of the irr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrr() {
        return irr;
    }

    /**
     * Sets the value of the irr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrr(BigDecimal value) {
        this.irr = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public long getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(long value) {
        this.period = value;
    }

    /**
     * Gets the value of the periodCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriodCharge() {
        return periodCharge;
    }

    /**
     * Sets the value of the periodCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriodCharge(BigDecimal value) {
        this.periodCharge = value;
    }

    /**
     * Gets the value of the periodChargeList property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodSchedule.PeriodChargeList }
     *     
     */
    public PeriodSchedule.PeriodChargeList getPeriodChargeList() {
        return periodChargeList;
    }

    /**
     * Sets the value of the periodChargeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodSchedule.PeriodChargeList }
     *     
     */
    public void setPeriodChargeList(PeriodSchedule.PeriodChargeList value) {
        this.periodChargeList = value;
    }

    /**
     * Gets the value of the periodMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriodMoney() {
        return periodMoney;
    }

    /**
     * Sets the value of the periodMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriodMoney(BigDecimal value) {
        this.periodMoney = value;
    }

    /**
     * Gets the value of the phase property.
     * 
     */
    public long getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     */
    public void setPhase(long value) {
        this.phase = value;
    }

    /**
     * Gets the value of the principalInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrincipalInterest() {
        return principalInterest;
    }

    /**
     * Sets the value of the principalInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrincipalInterest(BigDecimal value) {
        this.principalInterest = value;
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
     * Gets the value of the surplusPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSurplusPrincipal() {
        return surplusPrincipal;
    }

    /**
     * Sets the value of the surplusPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSurplusPrincipal(BigDecimal value) {
        this.surplusPrincipal = value;
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
    public static class PeriodChargeList {

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

}

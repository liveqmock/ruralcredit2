
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for exPaymentSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exPaymentSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amortizedInterest" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="amortizedPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="curPeriod" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="periodCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="periodMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
@XmlType(name = "exPaymentSchedule", propOrder = {
    "amortizedInterest",
    "amortizedPrincipal",
    "curPeriod",
    "period",
    "periodCharge",
    "periodMoney",
    "repayDate",
    "surplusPrincipal"
})
public class ExPaymentSchedule {

    @XmlElement(required = true)
    protected BigDecimal amortizedInterest;
    @XmlElement(required = true)
    protected BigDecimal amortizedPrincipal;
    protected long curPeriod;
    protected long period;
    @XmlElement(required = true)
    protected BigDecimal periodCharge;
    @XmlElement(required = true)
    protected BigDecimal periodMoney;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar repayDate;
    @XmlElement(required = true)
    protected BigDecimal surplusPrincipal;

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
     * Gets the value of the curPeriod property.
     * 
     */
    public long getCurPeriod() {
        return curPeriod;
    }

    /**
     * Sets the value of the curPeriod property.
     * 
     */
    public void setCurPeriod(long value) {
        this.curPeriod = value;
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

}

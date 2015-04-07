
package com.creditease.rc.app.pdf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for exhibtionCalcResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exhibtionCalcResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="epsList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="exPaymentSchedule" type="{http://www.creditease.cn}exPaymentSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="exPeriods" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="exhibitionCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "exhibtionCalcResult", propOrder = {
    "epsList",
    "exPeriods",
    "exhibitionCharge",
    "period",
    "productInfoId",
    "resultCode",
    "resultMessage"
})
public class ExhibtionCalcResult {

    protected ExhibtionCalcResult.EpsList epsList;
    protected long exPeriods;
    @XmlElement(required = true)
    protected BigDecimal exhibitionCharge;
    protected long period;
    protected long productInfoId;
    @XmlElement(required = true)
    protected String resultCode;
    @XmlElement(required = true)
    protected String resultMessage;

    /**
     * Gets the value of the epsList property.
     * 
     * @return
     *     possible object is
     *     {@link ExhibtionCalcResult.EpsList }
     *     
     */
    public ExhibtionCalcResult.EpsList getEpsList() {
        return epsList;
    }

    /**
     * Sets the value of the epsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExhibtionCalcResult.EpsList }
     *     
     */
    public void setEpsList(ExhibtionCalcResult.EpsList value) {
        this.epsList = value;
    }

    /**
     * Gets the value of the exPeriods property.
     * 
     */
    public long getExPeriods() {
        return exPeriods;
    }

    /**
     * Sets the value of the exPeriods property.
     * 
     */
    public void setExPeriods(long value) {
        this.exPeriods = value;
    }

    /**
     * Gets the value of the exhibitionCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExhibitionCharge() {
        return exhibitionCharge;
    }

    /**
     * Sets the value of the exhibitionCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExhibitionCharge(BigDecimal value) {
        this.exhibitionCharge = value;
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
     *         &lt;element name="exPaymentSchedule" type="{http://www.creditease.cn}exPaymentSchedule" maxOccurs="unbounded" minOccurs="0"/>
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
        "exPaymentSchedule"
    })
    public static class EpsList {

        protected List<ExPaymentSchedule> exPaymentSchedule;

        /**
         * Gets the value of the exPaymentSchedule property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exPaymentSchedule property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExPaymentSchedule().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ExPaymentSchedule }
         * 
         * 
         */
        public List<ExPaymentSchedule> getExPaymentSchedule() {
            if (exPaymentSchedule == null) {
                exPaymentSchedule = new ArrayList<ExPaymentSchedule>();
            }
            return this.exPaymentSchedule;
        }

    }

}


package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnSchemeResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnSchemeResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn/RuralBusyService/}WSResult">
 *       &lt;sequence>
 *         &lt;element name="apsList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actualPeriodScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}ActualPeriodScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="returnDerateList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ReturnDerateListDTO" type="{http://www.creditease.cn/RuralBusyService/}ReturnDerateListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="client_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="return_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bank_account" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amortisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="month_return" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="month_corpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="month_accrual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="month_charge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="end_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrearage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payoff_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="breach_day" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="b_days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="p_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSchemeResult", propOrder = {
    "apsList",
    "returnDerateList",
    "clientName",
    "idNumber",
    "version",
    "returnType",
    "bankAccount",
    "amount",
    "amortisation",
    "monthReturn",
    "monthCorpus",
    "monthAccrual",
    "monthCharge",
    "startTime",
    "endTime",
    "status",
    "total",
    "arrearage",
    "payoffNum",
    "breachDay",
    "bDays",
    "pNum"
})
public class ReturnSchemeResult
    extends WSResult
{

    @XmlElement(required = true)
    protected ReturnSchemeResult.ApsList apsList;
    @XmlElement(required = true)
    protected ReturnSchemeResult.ReturnDerateList returnDerateList;
    @XmlElement(name = "client_name")
    protected String clientName;
    @XmlElement(name = "id_number")
    protected String idNumber;
    protected String version;
    @XmlElement(name = "return_type")
    protected String returnType;
    @XmlElement(name = "bank_account")
    protected String bankAccount;
    protected String amount;
    protected String amortisation;
    @XmlElement(name = "month_return")
    protected String monthReturn;
    @XmlElement(name = "month_corpus")
    protected String monthCorpus;
    @XmlElement(name = "month_accrual")
    protected String monthAccrual;
    @XmlElement(name = "month_charge")
    protected String monthCharge;
    @XmlElement(name = "start_time")
    protected String startTime;
    @XmlElement(name = "end_time")
    protected String endTime;
    protected String status;
    protected String total;
    protected String arrearage;
    @XmlElement(name = "payoff_num")
    protected String payoffNum;
    @XmlElement(name = "breach_day")
    protected String breachDay;
    @XmlElement(name = "b_days")
    protected String bDays;
    @XmlElement(name = "p_num")
    protected String pNum;

    /**
     * Gets the value of the apsList property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSchemeResult.ApsList }
     *     
     */
    public ReturnSchemeResult.ApsList getApsList() {
        return apsList;
    }

    /**
     * Sets the value of the apsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSchemeResult.ApsList }
     *     
     */
    public void setApsList(ReturnSchemeResult.ApsList value) {
        this.apsList = value;
    }

    /**
     * Gets the value of the returnDerateList property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSchemeResult.ReturnDerateList }
     *     
     */
    public ReturnSchemeResult.ReturnDerateList getReturnDerateList() {
        return returnDerateList;
    }

    /**
     * Sets the value of the returnDerateList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSchemeResult.ReturnDerateList }
     *     
     */
    public void setReturnDerateList(ReturnSchemeResult.ReturnDerateList value) {
        this.returnDerateList = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the idNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the value of the idNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumber(String value) {
        this.idNumber = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the returnType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * Sets the value of the returnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnType(String value) {
        this.returnType = value;
    }

    /**
     * Gets the value of the bankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the value of the bankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccount(String value) {
        this.bankAccount = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the amortisation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmortisation() {
        return amortisation;
    }

    /**
     * Sets the value of the amortisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmortisation(String value) {
        this.amortisation = value;
    }

    /**
     * Gets the value of the monthReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthReturn() {
        return monthReturn;
    }

    /**
     * Sets the value of the monthReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthReturn(String value) {
        this.monthReturn = value;
    }

    /**
     * Gets the value of the monthCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthCorpus() {
        return monthCorpus;
    }

    /**
     * Sets the value of the monthCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthCorpus(String value) {
        this.monthCorpus = value;
    }

    /**
     * Gets the value of the monthAccrual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthAccrual() {
        return monthAccrual;
    }

    /**
     * Sets the value of the monthAccrual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthAccrual(String value) {
        this.monthAccrual = value;
    }

    /**
     * Gets the value of the monthCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthCharge() {
        return monthCharge;
    }

    /**
     * Sets the value of the monthCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthCharge(String value) {
        this.monthCharge = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

    /**
     * Gets the value of the arrearage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrearage() {
        return arrearage;
    }

    /**
     * Sets the value of the arrearage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrearage(String value) {
        this.arrearage = value;
    }

    /**
     * Gets the value of the payoffNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayoffNum() {
        return payoffNum;
    }

    /**
     * Sets the value of the payoffNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayoffNum(String value) {
        this.payoffNum = value;
    }

    /**
     * Gets the value of the breachDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBreachDay() {
        return breachDay;
    }

    /**
     * Sets the value of the breachDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBreachDay(String value) {
        this.breachDay = value;
    }

    /**
     * Gets the value of the bDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBDays() {
        return bDays;
    }

    /**
     * Sets the value of the bDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBDays(String value) {
        this.bDays = value;
    }

    /**
     * Gets the value of the pNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPNum() {
        return pNum;
    }

    /**
     * Sets the value of the pNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPNum(String value) {
        this.pNum = value;
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
     *         &lt;element name="actualPeriodScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}ActualPeriodScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "actualPeriodScheduleDTO"
    })
    public static class ApsList {

        protected List<ActualPeriodScheduleDTO> actualPeriodScheduleDTO;

        /**
         * Gets the value of the actualPeriodScheduleDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actualPeriodScheduleDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActualPeriodScheduleDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActualPeriodScheduleDTO }
         * 
         * 
         */
        public List<ActualPeriodScheduleDTO> getActualPeriodScheduleDTO() {
            if (actualPeriodScheduleDTO == null) {
                actualPeriodScheduleDTO = new ArrayList<ActualPeriodScheduleDTO>();
            }
            return this.actualPeriodScheduleDTO;
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
     *         &lt;element name="ReturnDerateListDTO" type="{http://www.creditease.cn/RuralBusyService/}ReturnDerateListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "returnDerateListDTO"
    })
    public static class ReturnDerateList {

        @XmlElement(name = "ReturnDerateListDTO")
        protected List<ReturnDerateListDTO> returnDerateListDTO;

        /**
         * Gets the value of the returnDerateListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the returnDerateListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReturnDerateListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReturnDerateListDTO }
         * 
         * 
         */
        public List<ReturnDerateListDTO> getReturnDerateListDTO() {
            if (returnDerateListDTO == null) {
                returnDerateListDTO = new ArrayList<ReturnDerateListDTO>();
            }
            return this.returnDerateListDTO;
        }

    }

}

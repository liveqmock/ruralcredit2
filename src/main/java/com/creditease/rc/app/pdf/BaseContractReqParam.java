
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
 * <p>Java class for baseContractReqParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseContractReqParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barcodeImg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrower" type="{http://www.creditease.cn}contractPart" minOccurs="0"/>
 *         &lt;element name="commBorrowerList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contractPart" type="{http://www.creditease.cn}contractPart" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="commGuarantorList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contractPart" type="{http://www.creditease.cn}contractPart" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="contractMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entrustor" type="{http://www.creditease.cn}contractPart" minOccurs="0"/>
 *         &lt;element name="firstInterestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstPayDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frontChargeDiscountList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="isMultiGuartLetter" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isSettleBefore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="judgeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lenderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="lenderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loanPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="param" type="{http://www.creditease.cn}param" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="paymentTypeList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="paymentTypeConfig" type="{http://www.creditease.cn}paymentTypeConfig" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodChargeDiscountList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productInfoId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="receBankAccount" type="{http://www.creditease.cn}bankAccount" minOccurs="0"/>
 *         &lt;element name="repayBankAccount" type="{http://www.creditease.cn}bankAccount" minOccurs="0"/>
 *         &lt;element name="settleBeforeInfo" type="{http://www.creditease.cn}settleBeforeInfo" minOccurs="0"/>
 *         &lt;element name="signAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseContractReqParam", propOrder = {
    "barcodeImg",
    "borrower",
    "commBorrowerList",
    "commGuarantorList",
    "contractMoney",
    "departmentId",
    "email",
    "entrustor",
    "firstInterestType",
    "firstPayDateType",
    "frontChargeDiscountList",
    "isMultiGuartLetter",
    "isSettleBefore",
    "judgeDate",
    "lenderDate",
    "lenderType",
    "loanPurpose",
    "paramList",
    "paymentTypeList",
    "periodChargeDiscountList",
    "periodCount",
    "productInfoId",
    "receBankAccount",
    "repayBankAccount",
    "settleBeforeInfo",
    "signAddress",
    "signDate"
})
public class BaseContractReqParam {

    protected String barcodeImg;
    protected ContractPart borrower;
    protected BaseContractReqParam.CommBorrowerList commBorrowerList;
    protected BaseContractReqParam.CommGuarantorList commGuarantorList;
    @XmlElement(required = true)
    protected BigDecimal contractMoney;
    protected long departmentId;
    protected String email;
    protected ContractPart entrustor;
    protected String firstInterestType;
    protected String firstPayDateType;
    protected BaseContractReqParam.FrontChargeDiscountList frontChargeDiscountList;
    protected Boolean isMultiGuartLetter;
    protected Boolean isSettleBefore;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar judgeDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lenderDate;
    @XmlElement(required = true)
    protected String lenderType;
    protected String loanPurpose;
    protected BaseContractReqParam.ParamList paramList;
    protected BaseContractReqParam.PaymentTypeList paymentTypeList;
    protected BaseContractReqParam.PeriodChargeDiscountList periodChargeDiscountList;
    protected int periodCount;
    protected long productInfoId;
    protected BankAccount receBankAccount;
    protected BankAccount repayBankAccount;
    protected SettleBeforeInfo settleBeforeInfo;
    protected String signAddress;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signDate;

    /**
     * Gets the value of the barcodeImg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarcodeImg() {
        return barcodeImg;
    }

    /**
     * Sets the value of the barcodeImg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarcodeImg(String value) {
        this.barcodeImg = value;
    }

    /**
     * Gets the value of the borrower property.
     * 
     * @return
     *     possible object is
     *     {@link ContractPart }
     *     
     */
    public ContractPart getBorrower() {
        return borrower;
    }

    /**
     * Sets the value of the borrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractPart }
     *     
     */
    public void setBorrower(ContractPart value) {
        this.borrower = value;
    }

    /**
     * Gets the value of the commBorrowerList property.
     * 
     * @return
     *     possible object is
     *     {@link BaseContractReqParam.CommBorrowerList }
     *     
     */
    public BaseContractReqParam.CommBorrowerList getCommBorrowerList() {
        return commBorrowerList;
    }

    /**
     * Sets the value of the commBorrowerList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseContractReqParam.CommBorrowerList }
     *     
     */
    public void setCommBorrowerList(BaseContractReqParam.CommBorrowerList value) {
        this.commBorrowerList = value;
    }

    /**
     * Gets the value of the commGuarantorList property.
     * 
     * @return
     *     possible object is
     *     {@link BaseContractReqParam.CommGuarantorList }
     *     
     */
    public BaseContractReqParam.CommGuarantorList getCommGuarantorList() {
        return commGuarantorList;
    }

    /**
     * Sets the value of the commGuarantorList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseContractReqParam.CommGuarantorList }
     *     
     */
    public void setCommGuarantorList(BaseContractReqParam.CommGuarantorList value) {
        this.commGuarantorList = value;
    }

    /**
     * Gets the value of the contractMoney property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    /**
     * Sets the value of the contractMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractMoney(BigDecimal value) {
        this.contractMoney = value;
    }

    /**
     * Gets the value of the departmentId property.
     * 
     */
    public long getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     */
    public void setDepartmentId(long value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the entrustor property.
     * 
     * @return
     *     possible object is
     *     {@link ContractPart }
     *     
     */
    public ContractPart getEntrustor() {
        return entrustor;
    }

    /**
     * Sets the value of the entrustor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractPart }
     *     
     */
    public void setEntrustor(ContractPart value) {
        this.entrustor = value;
    }

    /**
     * Gets the value of the firstInterestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstInterestType() {
        return firstInterestType;
    }

    /**
     * Sets the value of the firstInterestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstInterestType(String value) {
        this.firstInterestType = value;
    }

    /**
     * Gets the value of the firstPayDateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPayDateType() {
        return firstPayDateType;
    }

    /**
     * Sets the value of the firstPayDateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPayDateType(String value) {
        this.firstPayDateType = value;
    }

    /**
     * Gets the value of the frontChargeDiscountList property.
     * 
     * @return
     *     possible object is
     *     {@link BaseContractReqParam.FrontChargeDiscountList }
     *     
     */
    public BaseContractReqParam.FrontChargeDiscountList getFrontChargeDiscountList() {
        return frontChargeDiscountList;
    }

    /**
     * Sets the value of the frontChargeDiscountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseContractReqParam.FrontChargeDiscountList }
     *     
     */
    public void setFrontChargeDiscountList(BaseContractReqParam.FrontChargeDiscountList value) {
        this.frontChargeDiscountList = value;
    }

    /**
     * Gets the value of the isMultiGuartLetter property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsMultiGuartLetter() {
        return isMultiGuartLetter;
    }

    /**
     * Sets the value of the isMultiGuartLetter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsMultiGuartLetter(Boolean value) {
        this.isMultiGuartLetter = value;
    }

    /**
     * Gets the value of the isSettleBefore property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSettleBefore() {
        return isSettleBefore;
    }

    /**
     * Sets the value of the isSettleBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSettleBefore(Boolean value) {
        this.isSettleBefore = value;
    }

    /**
     * Gets the value of the judgeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getJudgeDate() {
        return judgeDate;
    }

    /**
     * Sets the value of the judgeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setJudgeDate(XMLGregorianCalendar value) {
        this.judgeDate = value;
    }

    /**
     * Gets the value of the lenderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLenderDate() {
        return lenderDate;
    }

    /**
     * Sets the value of the lenderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLenderDate(XMLGregorianCalendar value) {
        this.lenderDate = value;
    }

    /**
     * Gets the value of the lenderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLenderType() {
        return lenderType;
    }

    /**
     * Sets the value of the lenderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLenderType(String value) {
        this.lenderType = value;
    }

    /**
     * Gets the value of the loanPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanPurpose() {
        return loanPurpose;
    }

    /**
     * Sets the value of the loanPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanPurpose(String value) {
        this.loanPurpose = value;
    }

    /**
     * Gets the value of the paramList property.
     * 
     * @return
     *     possible object is
     *     {@link BaseContractReqParam.ParamList }
     *     
     */
    public BaseContractReqParam.ParamList getParamList() {
        return paramList;
    }

    /**
     * Sets the value of the paramList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseContractReqParam.ParamList }
     *     
     */
    public void setParamList(BaseContractReqParam.ParamList value) {
        this.paramList = value;
    }

    /**
     * Gets the value of the paymentTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link BaseContractReqParam.PaymentTypeList }
     *     
     */
    public BaseContractReqParam.PaymentTypeList getPaymentTypeList() {
        return paymentTypeList;
    }

    /**
     * Sets the value of the paymentTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseContractReqParam.PaymentTypeList }
     *     
     */
    public void setPaymentTypeList(BaseContractReqParam.PaymentTypeList value) {
        this.paymentTypeList = value;
    }

    /**
     * Gets the value of the periodChargeDiscountList property.
     * 
     * @return
     *     possible object is
     *     {@link BaseContractReqParam.PeriodChargeDiscountList }
     *     
     */
    public BaseContractReqParam.PeriodChargeDiscountList getPeriodChargeDiscountList() {
        return periodChargeDiscountList;
    }

    /**
     * Sets the value of the periodChargeDiscountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseContractReqParam.PeriodChargeDiscountList }
     *     
     */
    public void setPeriodChargeDiscountList(BaseContractReqParam.PeriodChargeDiscountList value) {
        this.periodChargeDiscountList = value;
    }

    /**
     * Gets the value of the periodCount property.
     * 
     */
    public int getPeriodCount() {
        return periodCount;
    }

    /**
     * Sets the value of the periodCount property.
     * 
     */
    public void setPeriodCount(int value) {
        this.periodCount = value;
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
     * Gets the value of the receBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link BankAccount }
     *     
     */
    public BankAccount getReceBankAccount() {
        return receBankAccount;
    }

    /**
     * Sets the value of the receBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAccount }
     *     
     */
    public void setReceBankAccount(BankAccount value) {
        this.receBankAccount = value;
    }

    /**
     * Gets the value of the repayBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link BankAccount }
     *     
     */
    public BankAccount getRepayBankAccount() {
        return repayBankAccount;
    }

    /**
     * Sets the value of the repayBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAccount }
     *     
     */
    public void setRepayBankAccount(BankAccount value) {
        this.repayBankAccount = value;
    }

    /**
     * Gets the value of the settleBeforeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SettleBeforeInfo }
     *     
     */
    public SettleBeforeInfo getSettleBeforeInfo() {
        return settleBeforeInfo;
    }

    /**
     * Sets the value of the settleBeforeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SettleBeforeInfo }
     *     
     */
    public void setSettleBeforeInfo(SettleBeforeInfo value) {
        this.settleBeforeInfo = value;
    }

    /**
     * Gets the value of the signAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignAddress() {
        return signAddress;
    }

    /**
     * Sets the value of the signAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignAddress(String value) {
        this.signAddress = value;
    }

    /**
     * Gets the value of the signDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSignDate() {
        return signDate;
    }

    /**
     * Sets the value of the signDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSignDate(XMLGregorianCalendar value) {
        this.signDate = value;
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
     *         &lt;element name="contractPart" type="{http://www.creditease.cn}contractPart" maxOccurs="unbounded" minOccurs="0"/>
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
        "contractPart"
    })
    public static class CommBorrowerList {

        protected List<ContractPart> contractPart;

        /**
         * Gets the value of the contractPart property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contractPart property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContractPart().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContractPart }
         * 
         * 
         */
        public List<ContractPart> getContractPart() {
            if (contractPart == null) {
                contractPart = new ArrayList<ContractPart>();
            }
            return this.contractPart;
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
     *         &lt;element name="contractPart" type="{http://www.creditease.cn}contractPart" maxOccurs="unbounded" minOccurs="0"/>
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
        "contractPart"
    })
    public static class CommGuarantorList {

        protected List<ContractPart> contractPart;

        /**
         * Gets the value of the contractPart property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contractPart property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContractPart().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContractPart }
         * 
         * 
         */
        public List<ContractPart> getContractPart() {
            if (contractPart == null) {
                contractPart = new ArrayList<ContractPart>();
            }
            return this.contractPart;
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
     *         &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "chargeDiscountInfo"
    })
    public static class FrontChargeDiscountList {

        protected List<ChargeDiscountInfo> chargeDiscountInfo;

        /**
         * Gets the value of the chargeDiscountInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeDiscountInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeDiscountInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeDiscountInfo }
         * 
         * 
         */
        public List<ChargeDiscountInfo> getChargeDiscountInfo() {
            if (chargeDiscountInfo == null) {
                chargeDiscountInfo = new ArrayList<ChargeDiscountInfo>();
            }
            return this.chargeDiscountInfo;
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
     *         &lt;element name="param" type="{http://www.creditease.cn}param" maxOccurs="unbounded" minOccurs="0"/>
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
        "param"
    })
    public static class ParamList {

        protected List<Param> param;

        /**
         * Gets the value of the param property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the param property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParam().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Param }
         * 
         * 
         */
        public List<Param> getParam() {
            if (param == null) {
                param = new ArrayList<Param>();
            }
            return this.param;
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
     *         &lt;element name="paymentTypeConfig" type="{http://www.creditease.cn}paymentTypeConfig" maxOccurs="unbounded" minOccurs="0"/>
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
        "paymentTypeConfig"
    })
    public static class PaymentTypeList {

        protected List<PaymentTypeConfig> paymentTypeConfig;

        /**
         * Gets the value of the paymentTypeConfig property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paymentTypeConfig property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPaymentTypeConfig().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PaymentTypeConfig }
         * 
         * 
         */
        public List<PaymentTypeConfig> getPaymentTypeConfig() {
            if (paymentTypeConfig == null) {
                paymentTypeConfig = new ArrayList<PaymentTypeConfig>();
            }
            return this.paymentTypeConfig;
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
     *         &lt;element name="chargeDiscountInfo" type="{http://www.creditease.cn}chargeDiscountInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "chargeDiscountInfo"
    })
    public static class PeriodChargeDiscountList {

        protected List<ChargeDiscountInfo> chargeDiscountInfo;

        /**
         * Gets the value of the chargeDiscountInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chargeDiscountInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChargeDiscountInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargeDiscountInfo }
         * 
         * 
         */
        public List<ChargeDiscountInfo> getChargeDiscountInfo() {
            if (chargeDiscountInfo == null) {
                chargeDiscountInfo = new ArrayList<ChargeDiscountInfo>();
            }
            return this.chargeDiscountInfo;
        }

    }

}

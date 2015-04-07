
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mortgagor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annualIncome" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="borrowerInfoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="borrowerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessRegNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyAnnualProfit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="companyHouseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creditCardLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="creditCardSum" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicileZipcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="graduateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hasCar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasChildren" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasSocialInsurance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="house" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseCondition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseConditionDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inCityYears" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="incomeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReplaceReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReported" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jobPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="loanTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="major" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marriageDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthIncome" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="monthOtherIncome" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="monthOtherIncomeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthPay" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="monthlyOutlay" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orgAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgProvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgTelArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherIncome" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="provideForCount" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="querySeqId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recruitmentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentalFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="roomCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salary" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="studentCardid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userNickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagor", propOrder = {
    "annualIncome",
    "borrowerInfoId",
    "borrowerType",
    "businessRegNo",
    "businessScope",
    "companyAnnualProfit",
    "companyHouseType",
    "companyStartDate",
    "creditCardLimit",
    "creditCardSum",
    "department",
    "departmentTel",
    "description",
    "domicileZipcode",
    "grade",
    "graduateDate",
    "hasCar",
    "hasChildren",
    "hasSocialInsurance",
    "house",
    "houseCondition",
    "houseConditionDesc",
    "inCityYears",
    "incomeType",
    "isReplaceReport",
    "isReported",
    "jobPosition",
    "loanRate",
    "loanTitle",
    "major",
    "marriageDesc",
    "mobile2",
    "monthIncome",
    "monthOtherIncome",
    "monthOtherIncomeDesc",
    "monthPay",
    "monthlyOutlay",
    "mortgagorId",
    "orgAddress",
    "orgCity",
    "orgDistrict",
    "orgName",
    "orgProvice",
    "orgTelArea",
    "orgType",
    "otherIncome",
    "provideForCount",
    "querySeqId",
    "recruitmentDate",
    "rentalFee",
    "roomCharge",
    "salary",
    "studentCardid",
    "taxNo",
    "title",
    "userNickname"
})
public class Mortgagor {

    protected BigDecimal annualIncome;
    protected Long borrowerInfoId;
    protected String borrowerType;
    protected String businessRegNo;
    protected String businessScope;
    protected BigDecimal companyAnnualProfit;
    protected String companyHouseType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar companyStartDate;
    protected BigDecimal creditCardLimit;
    protected BigDecimal creditCardSum;
    protected String department;
    protected String departmentTel;
    protected String description;
    protected String domicileZipcode;
    protected String grade;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar graduateDate;
    protected String hasCar;
    protected String hasChildren;
    protected String hasSocialInsurance;
    protected String house;
    protected String houseCondition;
    protected String houseConditionDesc;
    protected Short inCityYears;
    protected String incomeType;
    protected String isReplaceReport;
    protected String isReported;
    protected String jobPosition;
    protected BigDecimal loanRate;
    protected String loanTitle;
    protected String major;
    protected String marriageDesc;
    protected String mobile2;
    protected BigDecimal monthIncome;
    protected BigDecimal monthOtherIncome;
    protected String monthOtherIncomeDesc;
    protected BigDecimal monthPay;
    protected BigDecimal monthlyOutlay;
    protected Long mortgagorId;
    protected String orgAddress;
    protected String orgCity;
    protected String orgDistrict;
    protected String orgName;
    protected String orgProvice;
    protected String orgTelArea;
    protected String orgType;
    protected BigDecimal otherIncome;
    protected Short provideForCount;
    protected Long querySeqId;
    protected String recruitmentDate;
    protected BigDecimal rentalFee;
    protected String roomCharge;
    protected BigDecimal salary;
    protected String studentCardid;
    protected String taxNo;
    protected String title;
    protected String userNickname;

    /**
     * Gets the value of the annualIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the value of the annualIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAnnualIncome(BigDecimal value) {
        this.annualIncome = value;
    }

    /**
     * Gets the value of the borrowerInfoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBorrowerInfoId() {
        return borrowerInfoId;
    }

    /**
     * Sets the value of the borrowerInfoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBorrowerInfoId(Long value) {
        this.borrowerInfoId = value;
    }

    /**
     * Gets the value of the borrowerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerType() {
        return borrowerType;
    }

    /**
     * Sets the value of the borrowerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerType(String value) {
        this.borrowerType = value;
    }

    /**
     * Gets the value of the businessRegNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessRegNo() {
        return businessRegNo;
    }

    /**
     * Sets the value of the businessRegNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessRegNo(String value) {
        this.businessRegNo = value;
    }

    /**
     * Gets the value of the businessScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * Sets the value of the businessScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessScope(String value) {
        this.businessScope = value;
    }

    /**
     * Gets the value of the companyAnnualProfit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCompanyAnnualProfit() {
        return companyAnnualProfit;
    }

    /**
     * Sets the value of the companyAnnualProfit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCompanyAnnualProfit(BigDecimal value) {
        this.companyAnnualProfit = value;
    }

    /**
     * Gets the value of the companyHouseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyHouseType() {
        return companyHouseType;
    }

    /**
     * Sets the value of the companyHouseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyHouseType(String value) {
        this.companyHouseType = value;
    }

    /**
     * Gets the value of the companyStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompanyStartDate() {
        return companyStartDate;
    }

    /**
     * Sets the value of the companyStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompanyStartDate(XMLGregorianCalendar value) {
        this.companyStartDate = value;
    }

    /**
     * Gets the value of the creditCardLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditCardLimit() {
        return creditCardLimit;
    }

    /**
     * Sets the value of the creditCardLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditCardLimit(BigDecimal value) {
        this.creditCardLimit = value;
    }

    /**
     * Gets the value of the creditCardSum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditCardSum() {
        return creditCardSum;
    }

    /**
     * Sets the value of the creditCardSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditCardSum(BigDecimal value) {
        this.creditCardSum = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartment(String value) {
        this.department = value;
    }

    /**
     * Gets the value of the departmentTel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentTel() {
        return departmentTel;
    }

    /**
     * Sets the value of the departmentTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentTel(String value) {
        this.departmentTel = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the domicileZipcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomicileZipcode() {
        return domicileZipcode;
    }

    /**
     * Sets the value of the domicileZipcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomicileZipcode(String value) {
        this.domicileZipcode = value;
    }

    /**
     * Gets the value of the grade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the value of the grade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrade(String value) {
        this.grade = value;
    }

    /**
     * Gets the value of the graduateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGraduateDate() {
        return graduateDate;
    }

    /**
     * Sets the value of the graduateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGraduateDate(XMLGregorianCalendar value) {
        this.graduateDate = value;
    }

    /**
     * Gets the value of the hasCar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasCar() {
        return hasCar;
    }

    /**
     * Sets the value of the hasCar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasCar(String value) {
        this.hasCar = value;
    }

    /**
     * Gets the value of the hasChildren property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasChildren() {
        return hasChildren;
    }

    /**
     * Sets the value of the hasChildren property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasChildren(String value) {
        this.hasChildren = value;
    }

    /**
     * Gets the value of the hasSocialInsurance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasSocialInsurance() {
        return hasSocialInsurance;
    }

    /**
     * Sets the value of the hasSocialInsurance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasSocialInsurance(String value) {
        this.hasSocialInsurance = value;
    }

    /**
     * Gets the value of the house property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets the value of the house property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouse(String value) {
        this.house = value;
    }

    /**
     * Gets the value of the houseCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseCondition() {
        return houseCondition;
    }

    /**
     * Sets the value of the houseCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseCondition(String value) {
        this.houseCondition = value;
    }

    /**
     * Gets the value of the houseConditionDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseConditionDesc() {
        return houseConditionDesc;
    }

    /**
     * Sets the value of the houseConditionDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseConditionDesc(String value) {
        this.houseConditionDesc = value;
    }

    /**
     * Gets the value of the inCityYears property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getInCityYears() {
        return inCityYears;
    }

    /**
     * Sets the value of the inCityYears property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setInCityYears(Short value) {
        this.inCityYears = value;
    }

    /**
     * Gets the value of the incomeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * Sets the value of the incomeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomeType(String value) {
        this.incomeType = value;
    }

    /**
     * Gets the value of the isReplaceReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReplaceReport() {
        return isReplaceReport;
    }

    /**
     * Sets the value of the isReplaceReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReplaceReport(String value) {
        this.isReplaceReport = value;
    }

    /**
     * Gets the value of the isReported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReported() {
        return isReported;
    }

    /**
     * Sets the value of the isReported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReported(String value) {
        this.isReported = value;
    }

    /**
     * Gets the value of the jobPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobPosition() {
        return jobPosition;
    }

    /**
     * Sets the value of the jobPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobPosition(String value) {
        this.jobPosition = value;
    }

    /**
     * Gets the value of the loanRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLoanRate() {
        return loanRate;
    }

    /**
     * Sets the value of the loanRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLoanRate(BigDecimal value) {
        this.loanRate = value;
    }

    /**
     * Gets the value of the loanTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanTitle() {
        return loanTitle;
    }

    /**
     * Sets the value of the loanTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanTitle(String value) {
        this.loanTitle = value;
    }

    /**
     * Gets the value of the major property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the value of the major property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajor(String value) {
        this.major = value;
    }

    /**
     * Gets the value of the marriageDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarriageDesc() {
        return marriageDesc;
    }

    /**
     * Sets the value of the marriageDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarriageDesc(String value) {
        this.marriageDesc = value;
    }

    /**
     * Gets the value of the mobile2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile2() {
        return mobile2;
    }

    /**
     * Sets the value of the mobile2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile2(String value) {
        this.mobile2 = value;
    }

    /**
     * Gets the value of the monthIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthIncome() {
        return monthIncome;
    }

    /**
     * Sets the value of the monthIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthIncome(BigDecimal value) {
        this.monthIncome = value;
    }

    /**
     * Gets the value of the monthOtherIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthOtherIncome() {
        return monthOtherIncome;
    }

    /**
     * Sets the value of the monthOtherIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthOtherIncome(BigDecimal value) {
        this.monthOtherIncome = value;
    }

    /**
     * Gets the value of the monthOtherIncomeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthOtherIncomeDesc() {
        return monthOtherIncomeDesc;
    }

    /**
     * Sets the value of the monthOtherIncomeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthOtherIncomeDesc(String value) {
        this.monthOtherIncomeDesc = value;
    }

    /**
     * Gets the value of the monthPay property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthPay() {
        return monthPay;
    }

    /**
     * Sets the value of the monthPay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthPay(BigDecimal value) {
        this.monthPay = value;
    }

    /**
     * Gets the value of the monthlyOutlay property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthlyOutlay() {
        return monthlyOutlay;
    }

    /**
     * Sets the value of the monthlyOutlay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthlyOutlay(BigDecimal value) {
        this.monthlyOutlay = value;
    }

    /**
     * Gets the value of the mortgagorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMortgagorId() {
        return mortgagorId;
    }

    /**
     * Sets the value of the mortgagorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMortgagorId(Long value) {
        this.mortgagorId = value;
    }

    /**
     * Gets the value of the orgAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgAddress() {
        return orgAddress;
    }

    /**
     * Sets the value of the orgAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgAddress(String value) {
        this.orgAddress = value;
    }

    /**
     * Gets the value of the orgCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCity() {
        return orgCity;
    }

    /**
     * Sets the value of the orgCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCity(String value) {
        this.orgCity = value;
    }

    /**
     * Gets the value of the orgDistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgDistrict() {
        return orgDistrict;
    }

    /**
     * Sets the value of the orgDistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgDistrict(String value) {
        this.orgDistrict = value;
    }

    /**
     * Gets the value of the orgName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * Sets the value of the orgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgName(String value) {
        this.orgName = value;
    }

    /**
     * Gets the value of the orgProvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgProvice() {
        return orgProvice;
    }

    /**
     * Sets the value of the orgProvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgProvice(String value) {
        this.orgProvice = value;
    }

    /**
     * Gets the value of the orgTelArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgTelArea() {
        return orgTelArea;
    }

    /**
     * Sets the value of the orgTelArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgTelArea(String value) {
        this.orgTelArea = value;
    }

    /**
     * Gets the value of the orgType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * Sets the value of the orgType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgType(String value) {
        this.orgType = value;
    }

    /**
     * Gets the value of the otherIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOtherIncome() {
        return otherIncome;
    }

    /**
     * Sets the value of the otherIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOtherIncome(BigDecimal value) {
        this.otherIncome = value;
    }

    /**
     * Gets the value of the provideForCount property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getProvideForCount() {
        return provideForCount;
    }

    /**
     * Sets the value of the provideForCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setProvideForCount(Short value) {
        this.provideForCount = value;
    }

    /**
     * Gets the value of the querySeqId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQuerySeqId() {
        return querySeqId;
    }

    /**
     * Sets the value of the querySeqId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQuerySeqId(Long value) {
        this.querySeqId = value;
    }

    /**
     * Gets the value of the recruitmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecruitmentDate() {
        return recruitmentDate;
    }

    /**
     * Sets the value of the recruitmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecruitmentDate(String value) {
        this.recruitmentDate = value;
    }

    /**
     * Gets the value of the rentalFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRentalFee() {
        return rentalFee;
    }

    /**
     * Sets the value of the rentalFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRentalFee(BigDecimal value) {
        this.rentalFee = value;
    }

    /**
     * Gets the value of the roomCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomCharge() {
        return roomCharge;
    }

    /**
     * Sets the value of the roomCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomCharge(String value) {
        this.roomCharge = value;
    }

    /**
     * Gets the value of the salary property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets the value of the salary property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSalary(BigDecimal value) {
        this.salary = value;
    }

    /**
     * Gets the value of the studentCardid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentCardid() {
        return studentCardid;
    }

    /**
     * Sets the value of the studentCardid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentCardid(String value) {
        this.studentCardid = value;
    }

    /**
     * Gets the value of the taxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * Sets the value of the taxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxNo(String value) {
        this.taxNo = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the userNickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * Sets the value of the userNickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNickname(String value) {
        this.userNickname = value;
    }

}

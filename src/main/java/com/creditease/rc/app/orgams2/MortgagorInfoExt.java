
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mortgagorInfoExt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorInfoExt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="annualIncome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applyAddressIsIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="borrowSort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="career" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="childrenStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cohabitRela" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="creditCardLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc16a1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description1016" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasHousingLoan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homePhoneConnected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="industryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="industryCode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="industryCode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="industryCode3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="infoExtId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isCeMortgagor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCompliance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isMortAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isNative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="livingStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="loanAmountMax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="mainBusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marriageStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mateHasHouse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxDiploma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthExpend" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="monthRent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="monthRepayment" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="mortDept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nowAddressIsIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgTelArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerRelationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privSalary" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="privWorkYears" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="professionalTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordFiles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentBeginDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telDistNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalHouse" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vocationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whenAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yrdApplyResource1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yrdApplyResource2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdAcceptCheck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdAcceptCheckDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdFamilySupport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdGraduationData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdPeopleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdPlanCheck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdPlanCheckDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdSchoolName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdWorkingExperience" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yxdWorkingExperienceDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorInfoExt", propOrder = {
    "address",
    "addressDesc",
    "annualIncome",
    "applyAddressIsIdentity",
    "birthDate",
    "borrowSort",
    "career",
    "childrenStatus",
    "city",
    "cohabitRela",
    "createDate",
    "creator",
    "creditCardLimit",
    "desc16A1",
    "descRemark",
    "description",
    "description1016",
    "distNum",
    "district",
    "gender",
    "hasHousingLoan",
    "homeAddress",
    "homeCity",
    "homeDistrict",
    "homePhoneConnected",
    "homeProvince",
    "houseStatus",
    "industryCode",
    "industryCode1",
    "industryCode2",
    "industryCode3",
    "infoExtId",
    "isCeMortgagor",
    "isCompliance",
    "isMortAnswer",
    "isNative",
    "job",
    "livingStatus",
    "loanAmount",
    "loanAmountMax",
    "mainBusiness",
    "marriageStatus",
    "mateHasHouse",
    "maxDiploma",
    "memo",
    "mobile",
    "monthExpend",
    "monthRent",
    "monthRepayment",
    "mortDept",
    "mortgagorId",
    "nowAddressIsIdentity",
    "operDate",
    "operator",
    "orgName",
    "orgTel",
    "orgTelArea",
    "ownerName",
    "ownerRelationship",
    "periodization",
    "phone",
    "privAddress",
    "privCity",
    "privDistrict",
    "privOrg",
    "privProvince",
    "privSalary",
    "privWorkYears",
    "professionalTitle",
    "province",
    "recordFiles",
    "relationship",
    "rentBeginDate",
    "rentEndDate",
    "rentMethod",
    "rootType",
    "scale",
    "telDistNum",
    "telNum",
    "totalHouse",
    "vocationCode",
    "whenAnswer",
    "workYear",
    "yrdApplyResource1",
    "yrdApplyResource2",
    "yxdAcceptCheck",
    "yxdAcceptCheckDescription",
    "yxdFamilySupport",
    "yxdGraduationData",
    "yxdPeopleType",
    "yxdPlanCheck",
    "yxdPlanCheckDescription",
    "yxdSchoolName",
    "yxdWorkingExperience",
    "yxdWorkingExperienceDesc"
})
public class MortgagorInfoExt {

    protected String address;
    protected String addressDesc;
    protected String annualIncome;
    protected String applyAddressIsIdentity;
    protected String birthDate;
    protected String borrowSort;
    protected String career;
    protected String childrenStatus;
    protected String city;
    protected String cohabitRela;
    protected String createDate;
    protected Long creator;
    protected String creditCardLimit;
    @XmlElement(name = "desc16a1")
    protected String desc16A1;
    protected String descRemark;
    protected String description;
    protected String description1016;
    protected String distNum;
    protected String district;
    protected String gender;
    protected String hasHousingLoan;
    protected String homeAddress;
    protected String homeCity;
    protected String homeDistrict;
    protected String homePhoneConnected;
    protected String homeProvince;
    protected String houseStatus;
    protected String industryCode;
    protected String industryCode1;
    protected String industryCode2;
    protected String industryCode3;
    protected Long infoExtId;
    protected String isCeMortgagor;
    protected String isCompliance;
    protected String isMortAnswer;
    protected String isNative;
    protected String job;
    protected String livingStatus;
    protected BigDecimal loanAmount;
    protected BigDecimal loanAmountMax;
    protected String mainBusiness;
    protected String marriageStatus;
    protected String mateHasHouse;
    protected String maxDiploma;
    protected String memo;
    protected String mobile;
    protected BigDecimal monthExpend;
    protected BigDecimal monthRent;
    protected BigDecimal monthRepayment;
    protected String mortDept;
    protected Long mortgagorId;
    protected String nowAddressIsIdentity;
    protected String operDate;
    protected Long operator;
    protected String orgName;
    protected String orgTel;
    protected String orgTelArea;
    protected String ownerName;
    protected String ownerRelationship;
    protected String periodization;
    protected String phone;
    protected String privAddress;
    protected String privCity;
    protected String privDistrict;
    protected String privOrg;
    protected String privProvince;
    protected BigDecimal privSalary;
    protected Integer privWorkYears;
    protected String professionalTitle;
    protected String province;
    protected String recordFiles;
    protected String relationship;
    protected String rentBeginDate;
    protected String rentEndDate;
    protected String rentMethod;
    protected String rootType;
    protected String scale;
    protected String telDistNum;
    protected String telNum;
    protected Integer totalHouse;
    protected String vocationCode;
    protected String whenAnswer;
    protected String workYear;
    protected String yrdApplyResource1;
    protected String yrdApplyResource2;
    protected String yxdAcceptCheck;
    protected String yxdAcceptCheckDescription;
    protected String yxdFamilySupport;
    protected String yxdGraduationData;
    protected String yxdPeopleType;
    protected String yxdPlanCheck;
    protected String yxdPlanCheckDescription;
    protected String yxdSchoolName;
    protected String yxdWorkingExperience;
    protected String yxdWorkingExperienceDesc;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the addressDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressDesc() {
        return addressDesc;
    }

    /**
     * Sets the value of the addressDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressDesc(String value) {
        this.addressDesc = value;
    }

    /**
     * Gets the value of the annualIncome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the value of the annualIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnualIncome(String value) {
        this.annualIncome = value;
    }

    /**
     * Gets the value of the applyAddressIsIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyAddressIsIdentity() {
        return applyAddressIsIdentity;
    }

    /**
     * Sets the value of the applyAddressIsIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyAddressIsIdentity(String value) {
        this.applyAddressIsIdentity = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the borrowSort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowSort() {
        return borrowSort;
    }

    /**
     * Sets the value of the borrowSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowSort(String value) {
        this.borrowSort = value;
    }

    /**
     * Gets the value of the career property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCareer() {
        return career;
    }

    /**
     * Sets the value of the career property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCareer(String value) {
        this.career = value;
    }

    /**
     * Gets the value of the childrenStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildrenStatus() {
        return childrenStatus;
    }

    /**
     * Sets the value of the childrenStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChildrenStatus(String value) {
        this.childrenStatus = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the cohabitRela property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCohabitRela() {
        return cohabitRela;
    }

    /**
     * Sets the value of the cohabitRela property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCohabitRela(String value) {
        this.cohabitRela = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateDate(String value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreator(Long value) {
        this.creator = value;
    }

    /**
     * Gets the value of the creditCardLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardLimit() {
        return creditCardLimit;
    }

    /**
     * Sets the value of the creditCardLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardLimit(String value) {
        this.creditCardLimit = value;
    }

    /**
     * Gets the value of the desc16A1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc16A1() {
        return desc16A1;
    }

    /**
     * Sets the value of the desc16A1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc16A1(String value) {
        this.desc16A1 = value;
    }

    /**
     * Gets the value of the descRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRemark() {
        return descRemark;
    }

    /**
     * Sets the value of the descRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRemark(String value) {
        this.descRemark = value;
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
     * Gets the value of the description1016 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription1016() {
        return description1016;
    }

    /**
     * Sets the value of the description1016 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription1016(String value) {
        this.description1016 = value;
    }

    /**
     * Gets the value of the distNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistNum() {
        return distNum;
    }

    /**
     * Sets the value of the distNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistNum(String value) {
        this.distNum = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the hasHousingLoan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasHousingLoan() {
        return hasHousingLoan;
    }

    /**
     * Sets the value of the hasHousingLoan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasHousingLoan(String value) {
        this.hasHousingLoan = value;
    }

    /**
     * Gets the value of the homeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * Sets the value of the homeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeAddress(String value) {
        this.homeAddress = value;
    }

    /**
     * Gets the value of the homeCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * Sets the value of the homeCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCity(String value) {
        this.homeCity = value;
    }

    /**
     * Gets the value of the homeDistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeDistrict() {
        return homeDistrict;
    }

    /**
     * Sets the value of the homeDistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeDistrict(String value) {
        this.homeDistrict = value;
    }

    /**
     * Gets the value of the homePhoneConnected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomePhoneConnected() {
        return homePhoneConnected;
    }

    /**
     * Sets the value of the homePhoneConnected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomePhoneConnected(String value) {
        this.homePhoneConnected = value;
    }

    /**
     * Gets the value of the homeProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeProvince() {
        return homeProvince;
    }

    /**
     * Sets the value of the homeProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeProvince(String value) {
        this.homeProvince = value;
    }

    /**
     * Gets the value of the houseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseStatus() {
        return houseStatus;
    }

    /**
     * Sets the value of the houseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseStatus(String value) {
        this.houseStatus = value;
    }

    /**
     * Gets the value of the industryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryCode(String value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the industryCode1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryCode1() {
        return industryCode1;
    }

    /**
     * Sets the value of the industryCode1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryCode1(String value) {
        this.industryCode1 = value;
    }

    /**
     * Gets the value of the industryCode2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryCode2() {
        return industryCode2;
    }

    /**
     * Sets the value of the industryCode2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryCode2(String value) {
        this.industryCode2 = value;
    }

    /**
     * Gets the value of the industryCode3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryCode3() {
        return industryCode3;
    }

    /**
     * Sets the value of the industryCode3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryCode3(String value) {
        this.industryCode3 = value;
    }

    /**
     * Gets the value of the infoExtId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInfoExtId() {
        return infoExtId;
    }

    /**
     * Sets the value of the infoExtId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInfoExtId(Long value) {
        this.infoExtId = value;
    }

    /**
     * Gets the value of the isCeMortgagor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCeMortgagor() {
        return isCeMortgagor;
    }

    /**
     * Sets the value of the isCeMortgagor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCeMortgagor(String value) {
        this.isCeMortgagor = value;
    }

    /**
     * Gets the value of the isCompliance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCompliance() {
        return isCompliance;
    }

    /**
     * Sets the value of the isCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCompliance(String value) {
        this.isCompliance = value;
    }

    /**
     * Gets the value of the isMortAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMortAnswer() {
        return isMortAnswer;
    }

    /**
     * Sets the value of the isMortAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMortAnswer(String value) {
        this.isMortAnswer = value;
    }

    /**
     * Gets the value of the isNative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsNative() {
        return isNative;
    }

    /**
     * Sets the value of the isNative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsNative(String value) {
        this.isNative = value;
    }

    /**
     * Gets the value of the job property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets the value of the job property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJob(String value) {
        this.job = value;
    }

    /**
     * Gets the value of the livingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivingStatus() {
        return livingStatus;
    }

    /**
     * Sets the value of the livingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivingStatus(String value) {
        this.livingStatus = value;
    }

    /**
     * Gets the value of the loanAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the value of the loanAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLoanAmount(BigDecimal value) {
        this.loanAmount = value;
    }

    /**
     * Gets the value of the loanAmountMax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLoanAmountMax() {
        return loanAmountMax;
    }

    /**
     * Sets the value of the loanAmountMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLoanAmountMax(BigDecimal value) {
        this.loanAmountMax = value;
    }

    /**
     * Gets the value of the mainBusiness property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainBusiness() {
        return mainBusiness;
    }

    /**
     * Sets the value of the mainBusiness property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainBusiness(String value) {
        this.mainBusiness = value;
    }

    /**
     * Gets the value of the marriageStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarriageStatus() {
        return marriageStatus;
    }

    /**
     * Sets the value of the marriageStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarriageStatus(String value) {
        this.marriageStatus = value;
    }

    /**
     * Gets the value of the mateHasHouse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMateHasHouse() {
        return mateHasHouse;
    }

    /**
     * Sets the value of the mateHasHouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMateHasHouse(String value) {
        this.mateHasHouse = value;
    }

    /**
     * Gets the value of the maxDiploma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxDiploma() {
        return maxDiploma;
    }

    /**
     * Sets the value of the maxDiploma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxDiploma(String value) {
        this.maxDiploma = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the monthExpend property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthExpend() {
        return monthExpend;
    }

    /**
     * Sets the value of the monthExpend property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthExpend(BigDecimal value) {
        this.monthExpend = value;
    }

    /**
     * Gets the value of the monthRent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthRent() {
        return monthRent;
    }

    /**
     * Sets the value of the monthRent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthRent(BigDecimal value) {
        this.monthRent = value;
    }

    /**
     * Gets the value of the monthRepayment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthRepayment() {
        return monthRepayment;
    }

    /**
     * Sets the value of the monthRepayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthRepayment(BigDecimal value) {
        this.monthRepayment = value;
    }

    /**
     * Gets the value of the mortDept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMortDept() {
        return mortDept;
    }

    /**
     * Sets the value of the mortDept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMortDept(String value) {
        this.mortDept = value;
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
     * Gets the value of the nowAddressIsIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNowAddressIsIdentity() {
        return nowAddressIsIdentity;
    }

    /**
     * Sets the value of the nowAddressIsIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNowAddressIsIdentity(String value) {
        this.nowAddressIsIdentity = value;
    }

    /**
     * Gets the value of the operDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperDate() {
        return operDate;
    }

    /**
     * Sets the value of the operDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperDate(String value) {
        this.operDate = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperator(Long value) {
        this.operator = value;
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
     * Gets the value of the orgTel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgTel() {
        return orgTel;
    }

    /**
     * Sets the value of the orgTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgTel(String value) {
        this.orgTel = value;
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
     * Gets the value of the ownerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the value of the ownerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerName(String value) {
        this.ownerName = value;
    }

    /**
     * Gets the value of the ownerRelationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerRelationship() {
        return ownerRelationship;
    }

    /**
     * Sets the value of the ownerRelationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerRelationship(String value) {
        this.ownerRelationship = value;
    }

    /**
     * Gets the value of the periodization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodization() {
        return periodization;
    }

    /**
     * Sets the value of the periodization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodization(String value) {
        this.periodization = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the privAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivAddress() {
        return privAddress;
    }

    /**
     * Sets the value of the privAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivAddress(String value) {
        this.privAddress = value;
    }

    /**
     * Gets the value of the privCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivCity() {
        return privCity;
    }

    /**
     * Sets the value of the privCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivCity(String value) {
        this.privCity = value;
    }

    /**
     * Gets the value of the privDistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivDistrict() {
        return privDistrict;
    }

    /**
     * Sets the value of the privDistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivDistrict(String value) {
        this.privDistrict = value;
    }

    /**
     * Gets the value of the privOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivOrg() {
        return privOrg;
    }

    /**
     * Sets the value of the privOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivOrg(String value) {
        this.privOrg = value;
    }

    /**
     * Gets the value of the privProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivProvince() {
        return privProvince;
    }

    /**
     * Sets the value of the privProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivProvince(String value) {
        this.privProvince = value;
    }

    /**
     * Gets the value of the privSalary property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrivSalary() {
        return privSalary;
    }

    /**
     * Sets the value of the privSalary property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrivSalary(BigDecimal value) {
        this.privSalary = value;
    }

    /**
     * Gets the value of the privWorkYears property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrivWorkYears() {
        return privWorkYears;
    }

    /**
     * Sets the value of the privWorkYears property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrivWorkYears(Integer value) {
        this.privWorkYears = value;
    }

    /**
     * Gets the value of the professionalTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * Sets the value of the professionalTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessionalTitle(String value) {
        this.professionalTitle = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvince(String value) {
        this.province = value;
    }

    /**
     * Gets the value of the recordFiles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordFiles() {
        return recordFiles;
    }

    /**
     * Sets the value of the recordFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordFiles(String value) {
        this.recordFiles = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationship(String value) {
        this.relationship = value;
    }

    /**
     * Gets the value of the rentBeginDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentBeginDate() {
        return rentBeginDate;
    }

    /**
     * Sets the value of the rentBeginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentBeginDate(String value) {
        this.rentBeginDate = value;
    }

    /**
     * Gets the value of the rentEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentEndDate() {
        return rentEndDate;
    }

    /**
     * Sets the value of the rentEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentEndDate(String value) {
        this.rentEndDate = value;
    }

    /**
     * Gets the value of the rentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentMethod() {
        return rentMethod;
    }

    /**
     * Sets the value of the rentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentMethod(String value) {
        this.rentMethod = value;
    }

    /**
     * Gets the value of the rootType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootType() {
        return rootType;
    }

    /**
     * Sets the value of the rootType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootType(String value) {
        this.rootType = value;
    }

    /**
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScale(String value) {
        this.scale = value;
    }

    /**
     * Gets the value of the telDistNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelDistNum() {
        return telDistNum;
    }

    /**
     * Sets the value of the telDistNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelDistNum(String value) {
        this.telDistNum = value;
    }

    /**
     * Gets the value of the telNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * Sets the value of the telNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelNum(String value) {
        this.telNum = value;
    }

    /**
     * Gets the value of the totalHouse property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalHouse() {
        return totalHouse;
    }

    /**
     * Sets the value of the totalHouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalHouse(Integer value) {
        this.totalHouse = value;
    }

    /**
     * Gets the value of the vocationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocationCode() {
        return vocationCode;
    }

    /**
     * Sets the value of the vocationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocationCode(String value) {
        this.vocationCode = value;
    }

    /**
     * Gets the value of the whenAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhenAnswer() {
        return whenAnswer;
    }

    /**
     * Sets the value of the whenAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhenAnswer(String value) {
        this.whenAnswer = value;
    }

    /**
     * Gets the value of the workYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkYear() {
        return workYear;
    }

    /**
     * Sets the value of the workYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkYear(String value) {
        this.workYear = value;
    }

    /**
     * Gets the value of the yrdApplyResource1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYrdApplyResource1() {
        return yrdApplyResource1;
    }

    /**
     * Sets the value of the yrdApplyResource1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYrdApplyResource1(String value) {
        this.yrdApplyResource1 = value;
    }

    /**
     * Gets the value of the yrdApplyResource2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYrdApplyResource2() {
        return yrdApplyResource2;
    }

    /**
     * Sets the value of the yrdApplyResource2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYrdApplyResource2(String value) {
        this.yrdApplyResource2 = value;
    }

    /**
     * Gets the value of the yxdAcceptCheck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdAcceptCheck() {
        return yxdAcceptCheck;
    }

    /**
     * Sets the value of the yxdAcceptCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdAcceptCheck(String value) {
        this.yxdAcceptCheck = value;
    }

    /**
     * Gets the value of the yxdAcceptCheckDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdAcceptCheckDescription() {
        return yxdAcceptCheckDescription;
    }

    /**
     * Sets the value of the yxdAcceptCheckDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdAcceptCheckDescription(String value) {
        this.yxdAcceptCheckDescription = value;
    }

    /**
     * Gets the value of the yxdFamilySupport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdFamilySupport() {
        return yxdFamilySupport;
    }

    /**
     * Sets the value of the yxdFamilySupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdFamilySupport(String value) {
        this.yxdFamilySupport = value;
    }

    /**
     * Gets the value of the yxdGraduationData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdGraduationData() {
        return yxdGraduationData;
    }

    /**
     * Sets the value of the yxdGraduationData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdGraduationData(String value) {
        this.yxdGraduationData = value;
    }

    /**
     * Gets the value of the yxdPeopleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdPeopleType() {
        return yxdPeopleType;
    }

    /**
     * Sets the value of the yxdPeopleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdPeopleType(String value) {
        this.yxdPeopleType = value;
    }

    /**
     * Gets the value of the yxdPlanCheck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdPlanCheck() {
        return yxdPlanCheck;
    }

    /**
     * Sets the value of the yxdPlanCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdPlanCheck(String value) {
        this.yxdPlanCheck = value;
    }

    /**
     * Gets the value of the yxdPlanCheckDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdPlanCheckDescription() {
        return yxdPlanCheckDescription;
    }

    /**
     * Sets the value of the yxdPlanCheckDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdPlanCheckDescription(String value) {
        this.yxdPlanCheckDescription = value;
    }

    /**
     * Gets the value of the yxdSchoolName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdSchoolName() {
        return yxdSchoolName;
    }

    /**
     * Sets the value of the yxdSchoolName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdSchoolName(String value) {
        this.yxdSchoolName = value;
    }

    /**
     * Gets the value of the yxdWorkingExperience property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdWorkingExperience() {
        return yxdWorkingExperience;
    }

    /**
     * Sets the value of the yxdWorkingExperience property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdWorkingExperience(String value) {
        this.yxdWorkingExperience = value;
    }

    /**
     * Gets the value of the yxdWorkingExperienceDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYxdWorkingExperienceDesc() {
        return yxdWorkingExperienceDesc;
    }

    /**
     * Sets the value of the yxdWorkingExperienceDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYxdWorkingExperienceDesc(String value) {
        this.yxdWorkingExperienceDesc = value;
    }

}

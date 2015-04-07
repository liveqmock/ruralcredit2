
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mortgagorCorporation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorCorporation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="annualSurveied" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corporationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="customerIdentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="establishDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="landlord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalRepresentative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licenceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="obligee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orgNature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgNatureDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizationNm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paidDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="registeredCapital" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentTermEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rental" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="residentAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondDocType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tenantry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorCorporation", propOrder = {
    "address",
    "annualSurveied",
    "billType",
    "contentType",
    "corporationId",
    "createDate",
    "creator",
    "customerIdentType",
    "description",
    "establishDate",
    "issueDate",
    "landlord",
    "legalRepresentative",
    "licenceType",
    "memo",
    "mortgagorId",
    "obligee",
    "operDate",
    "operator",
    "orgNature",
    "orgNatureDesc",
    "orgType",
    "organizationNm",
    "paidDate",
    "portion",
    "registeredCapital",
    "relationship",
    "rentTerm",
    "rentTermEnd",
    "rental",
    "residentAddr",
    "secondDocType",
    "tenantry"
})
public class MortgagorCorporation {

    protected String address;
    protected String annualSurveied;
    protected String billType;
    protected String contentType;
    protected Long corporationId;
    protected String createDate;
    protected Long creator;
    protected String customerIdentType;
    protected String description;
    protected String establishDate;
    protected String issueDate;
    protected String landlord;
    protected String legalRepresentative;
    protected String licenceType;
    protected String memo;
    protected Long mortgagorId;
    protected String obligee;
    protected String operDate;
    protected Long operator;
    protected String orgNature;
    protected String orgNatureDesc;
    protected String orgType;
    protected String organizationNm;
    protected String paidDate;
    protected BigDecimal portion;
    protected BigDecimal registeredCapital;
    protected String relationship;
    protected String rentTerm;
    protected String rentTermEnd;
    protected BigDecimal rental;
    protected String residentAddr;
    protected String secondDocType;
    protected String tenantry;

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
     * Gets the value of the annualSurveied property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnualSurveied() {
        return annualSurveied;
    }

    /**
     * Sets the value of the annualSurveied property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnualSurveied(String value) {
        this.annualSurveied = value;
    }

    /**
     * Gets the value of the billType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillType() {
        return billType;
    }

    /**
     * Sets the value of the billType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillType(String value) {
        this.billType = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the corporationId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCorporationId() {
        return corporationId;
    }

    /**
     * Sets the value of the corporationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCorporationId(Long value) {
        this.corporationId = value;
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
     * Gets the value of the customerIdentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerIdentType() {
        return customerIdentType;
    }

    /**
     * Sets the value of the customerIdentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerIdentType(String value) {
        this.customerIdentType = value;
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
     * Gets the value of the establishDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstablishDate() {
        return establishDate;
    }

    /**
     * Sets the value of the establishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstablishDate(String value) {
        this.establishDate = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the landlord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLandlord() {
        return landlord;
    }

    /**
     * Sets the value of the landlord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLandlord(String value) {
        this.landlord = value;
    }

    /**
     * Gets the value of the legalRepresentative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     * Sets the value of the legalRepresentative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalRepresentative(String value) {
        this.legalRepresentative = value;
    }

    /**
     * Gets the value of the licenceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenceType() {
        return licenceType;
    }

    /**
     * Sets the value of the licenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenceType(String value) {
        this.licenceType = value;
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
     * Gets the value of the obligee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligee() {
        return obligee;
    }

    /**
     * Sets the value of the obligee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligee(String value) {
        this.obligee = value;
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
     * Gets the value of the orgNature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgNature() {
        return orgNature;
    }

    /**
     * Sets the value of the orgNature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgNature(String value) {
        this.orgNature = value;
    }

    /**
     * Gets the value of the orgNatureDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgNatureDesc() {
        return orgNatureDesc;
    }

    /**
     * Sets the value of the orgNatureDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgNatureDesc(String value) {
        this.orgNatureDesc = value;
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
     * Gets the value of the organizationNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationNm() {
        return organizationNm;
    }

    /**
     * Sets the value of the organizationNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationNm(String value) {
        this.organizationNm = value;
    }

    /**
     * Gets the value of the paidDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaidDate() {
        return paidDate;
    }

    /**
     * Sets the value of the paidDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaidDate(String value) {
        this.paidDate = value;
    }

    /**
     * Gets the value of the portion property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPortion() {
        return portion;
    }

    /**
     * Sets the value of the portion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPortion(BigDecimal value) {
        this.portion = value;
    }

    /**
     * Gets the value of the registeredCapital property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    /**
     * Sets the value of the registeredCapital property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRegisteredCapital(BigDecimal value) {
        this.registeredCapital = value;
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
     * Gets the value of the rentTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentTerm() {
        return rentTerm;
    }

    /**
     * Sets the value of the rentTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentTerm(String value) {
        this.rentTerm = value;
    }

    /**
     * Gets the value of the rentTermEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentTermEnd() {
        return rentTermEnd;
    }

    /**
     * Sets the value of the rentTermEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentTermEnd(String value) {
        this.rentTermEnd = value;
    }

    /**
     * Gets the value of the rental property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRental() {
        return rental;
    }

    /**
     * Sets the value of the rental property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRental(BigDecimal value) {
        this.rental = value;
    }

    /**
     * Gets the value of the residentAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentAddr() {
        return residentAddr;
    }

    /**
     * Sets the value of the residentAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentAddr(String value) {
        this.residentAddr = value;
    }

    /**
     * Gets the value of the secondDocType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondDocType() {
        return secondDocType;
    }

    /**
     * Sets the value of the secondDocType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondDocType(String value) {
        this.secondDocType = value;
    }

    /**
     * Gets the value of the tenantry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantry() {
        return tenantry;
    }

    /**
     * Sets the value of the tenantry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantry(String value) {
        this.tenantry = value;
    }

}

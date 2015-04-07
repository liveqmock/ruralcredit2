
package com.creditease.rc.app.orgams2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mortgagorFixedAssets complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorFixedAssets">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressZipcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeAgainstAssets" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="chargeAgainstLiabilities" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fixedAssetsId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="house" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseOwnerTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPledge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jointOwners" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="propertyDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proportion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="relatedLoan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationshipTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootCharacter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorFixedAssets", propOrder = {
    "address",
    "addressCity",
    "addressDistrict",
    "addressProvince",
    "addressZipcode",
    "area",
    "assestType",
    "brand",
    "chargeAgainstAssets",
    "chargeAgainstLiabilities",
    "createDate",
    "creator",
    "description",
    "fixedAssetsId",
    "house",
    "houseOwnerTypeDesc",
    "houseTypeDesc",
    "isPledge",
    "jointOwners",
    "memo",
    "mortgagorId",
    "operDate",
    "operator",
    "propertyDate",
    "proportion",
    "relatedLoan",
    "relationship",
    "relationshipTwo",
    "rootCharacter",
    "rootType",
    "secondPrice",
    "seqNo"
})
public class MortgagorFixedAssets {

    protected String address;
    protected String addressCity;
    protected String addressDistrict;
    protected String addressProvince;
    protected String addressZipcode;
    protected String area;
    protected String assestType;
    protected String brand;
    protected BigDecimal chargeAgainstAssets;
    protected BigDecimal chargeAgainstLiabilities;
    protected String createDate;
    protected Long creator;
    protected String description;
    protected Long fixedAssetsId;
    protected String house;
    protected String houseOwnerTypeDesc;
    protected String houseTypeDesc;
    protected String isPledge;
    protected Integer jointOwners;
    protected String memo;
    protected Long mortgagorId;
    protected String operDate;
    protected Long operator;
    protected String propertyDate;
    protected BigDecimal proportion;
    protected String relatedLoan;
    protected String relationship;
    protected String relationshipTwo;
    protected String rootCharacter;
    protected String rootType;
    protected BigDecimal secondPrice;
    protected Integer seqNo;

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
     * Gets the value of the addressCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets the value of the addressCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCity(String value) {
        this.addressCity = value;
    }

    /**
     * Gets the value of the addressDistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressDistrict() {
        return addressDistrict;
    }

    /**
     * Sets the value of the addressDistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressDistrict(String value) {
        this.addressDistrict = value;
    }

    /**
     * Gets the value of the addressProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressProvince() {
        return addressProvince;
    }

    /**
     * Sets the value of the addressProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressProvince(String value) {
        this.addressProvince = value;
    }

    /**
     * Gets the value of the addressZipcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressZipcode() {
        return addressZipcode;
    }

    /**
     * Sets the value of the addressZipcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressZipcode(String value) {
        this.addressZipcode = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the assestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssestType() {
        return assestType;
    }

    /**
     * Sets the value of the assestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssestType(String value) {
        this.assestType = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * Gets the value of the chargeAgainstAssets property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChargeAgainstAssets() {
        return chargeAgainstAssets;
    }

    /**
     * Sets the value of the chargeAgainstAssets property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChargeAgainstAssets(BigDecimal value) {
        this.chargeAgainstAssets = value;
    }

    /**
     * Gets the value of the chargeAgainstLiabilities property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChargeAgainstLiabilities() {
        return chargeAgainstLiabilities;
    }

    /**
     * Sets the value of the chargeAgainstLiabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChargeAgainstLiabilities(BigDecimal value) {
        this.chargeAgainstLiabilities = value;
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
     * Gets the value of the fixedAssetsId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFixedAssetsId() {
        return fixedAssetsId;
    }

    /**
     * Sets the value of the fixedAssetsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFixedAssetsId(Long value) {
        this.fixedAssetsId = value;
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
     * Gets the value of the houseOwnerTypeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseOwnerTypeDesc() {
        return houseOwnerTypeDesc;
    }

    /**
     * Sets the value of the houseOwnerTypeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseOwnerTypeDesc(String value) {
        this.houseOwnerTypeDesc = value;
    }

    /**
     * Gets the value of the houseTypeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseTypeDesc() {
        return houseTypeDesc;
    }

    /**
     * Sets the value of the houseTypeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseTypeDesc(String value) {
        this.houseTypeDesc = value;
    }

    /**
     * Gets the value of the isPledge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPledge() {
        return isPledge;
    }

    /**
     * Sets the value of the isPledge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPledge(String value) {
        this.isPledge = value;
    }

    /**
     * Gets the value of the jointOwners property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJointOwners() {
        return jointOwners;
    }

    /**
     * Sets the value of the jointOwners property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJointOwners(Integer value) {
        this.jointOwners = value;
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
     * Gets the value of the propertyDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyDate() {
        return propertyDate;
    }

    /**
     * Sets the value of the propertyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyDate(String value) {
        this.propertyDate = value;
    }

    /**
     * Gets the value of the proportion property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProportion() {
        return proportion;
    }

    /**
     * Sets the value of the proportion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProportion(BigDecimal value) {
        this.proportion = value;
    }

    /**
     * Gets the value of the relatedLoan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedLoan() {
        return relatedLoan;
    }

    /**
     * Sets the value of the relatedLoan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedLoan(String value) {
        this.relatedLoan = value;
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
     * Gets the value of the relationshipTwo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationshipTwo() {
        return relationshipTwo;
    }

    /**
     * Sets the value of the relationshipTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationshipTwo(String value) {
        this.relationshipTwo = value;
    }

    /**
     * Gets the value of the rootCharacter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootCharacter() {
        return rootCharacter;
    }

    /**
     * Sets the value of the rootCharacter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootCharacter(String value) {
        this.rootCharacter = value;
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
     * Gets the value of the secondPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSecondPrice() {
        return secondPrice;
    }

    /**
     * Sets the value of the secondPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSecondPrice(BigDecimal value) {
        this.secondPrice = value;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeqNo(Integer value) {
        this.seqNo = value;
    }

}

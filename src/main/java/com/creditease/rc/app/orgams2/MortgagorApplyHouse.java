
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mortgagorApplyHouse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorApplyHouse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyHouseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="buyHouseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="houseAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseOwnerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseOwnerTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseZipcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSameWithApplay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorApplyHouse", propOrder = {
    "applyHouseId",
    "buyHouseDate",
    "createDate",
    "houseAddr",
    "houseCity",
    "houseDistrict",
    "houseOwnerType",
    "houseOwnerTypeDesc",
    "houseProvince",
    "houseType",
    "houseTypeDesc",
    "houseZipcode",
    "isSameWithApplay",
    "mortgagorId",
    "transportId"
})
public class MortgagorApplyHouse {

    protected Long applyHouseId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar buyHouseDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String houseAddr;
    protected String houseCity;
    protected String houseDistrict;
    protected String houseOwnerType;
    protected String houseOwnerTypeDesc;
    protected String houseProvince;
    protected String houseType;
    protected String houseTypeDesc;
    protected String houseZipcode;
    protected String isSameWithApplay;
    protected Long mortgagorId;
    protected Long transportId;

    /**
     * Gets the value of the applyHouseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getApplyHouseId() {
        return applyHouseId;
    }

    /**
     * Sets the value of the applyHouseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setApplyHouseId(Long value) {
        this.applyHouseId = value;
    }

    /**
     * Gets the value of the buyHouseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBuyHouseDate() {
        return buyHouseDate;
    }

    /**
     * Sets the value of the buyHouseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBuyHouseDate(XMLGregorianCalendar value) {
        this.buyHouseDate = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the houseAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseAddr() {
        return houseAddr;
    }

    /**
     * Sets the value of the houseAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseAddr(String value) {
        this.houseAddr = value;
    }

    /**
     * Gets the value of the houseCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseCity() {
        return houseCity;
    }

    /**
     * Sets the value of the houseCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseCity(String value) {
        this.houseCity = value;
    }

    /**
     * Gets the value of the houseDistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseDistrict() {
        return houseDistrict;
    }

    /**
     * Sets the value of the houseDistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseDistrict(String value) {
        this.houseDistrict = value;
    }

    /**
     * Gets the value of the houseOwnerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseOwnerType() {
        return houseOwnerType;
    }

    /**
     * Sets the value of the houseOwnerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseOwnerType(String value) {
        this.houseOwnerType = value;
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
     * Gets the value of the houseProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseProvince() {
        return houseProvince;
    }

    /**
     * Sets the value of the houseProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseProvince(String value) {
        this.houseProvince = value;
    }

    /**
     * Gets the value of the houseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * Sets the value of the houseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseType(String value) {
        this.houseType = value;
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
     * Gets the value of the houseZipcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseZipcode() {
        return houseZipcode;
    }

    /**
     * Sets the value of the houseZipcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseZipcode(String value) {
        this.houseZipcode = value;
    }

    /**
     * Gets the value of the isSameWithApplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSameWithApplay() {
        return isSameWithApplay;
    }

    /**
     * Sets the value of the isSameWithApplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSameWithApplay(String value) {
        this.isSameWithApplay = value;
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
     * Gets the value of the transportId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransportId() {
        return transportId;
    }

    /**
     * Sets the value of the transportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransportId(Long value) {
        this.transportId = value;
    }

}

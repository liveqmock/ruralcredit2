package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for trustOrgan complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="trustOrgan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attachmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPerson1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPerson2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPerson3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPerson4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPerson5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactTel1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactTel2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactTel3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactTel4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactTel5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iconClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="organCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="organName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trustOrgan", propOrder = { "attachmentId", "city",
		"contactPerson1", "contactPerson2", "contactPerson3", "contactPerson4",
		"contactPerson5", "contactTel1", "contactTel2", "contactTel3",
		"contactTel4", "contactTel5", "createTime", "creator", "displayName",
		"iconClass", "modifyTime", "operator", "organCode", "organId",
		"organName", "organType", "postCode", "province", "remark", "state",
		"street", "sysStatus", "version" })
public class TrustOrgan {

	protected String attachmentId;
	protected String city;
	protected String contactPerson1;
	protected String contactPerson2;
	protected String contactPerson3;
	protected String contactPerson4;
	protected String contactPerson5;
	protected String contactTel1;
	protected String contactTel2;
	protected String contactTel3;
	protected String contactTel4;
	protected String contactTel5;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	protected BigDecimal creator;
	protected String displayName;
	protected String iconClass;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modifyTime;
	protected BigDecimal operator;
	protected String organCode;
	protected BigDecimal organId;
	protected String organName;
	protected String organType;
	protected Integer postCode;
	protected String province;
	protected String remark;
	protected String state;
	protected String street;
	protected String sysStatus;
	protected Long version;

	/**
	 * Gets the value of the attachmentId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentId() {
		return attachmentId;
	}

	/**
	 * Sets the value of the attachmentId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentId(String value) {
		this.attachmentId = value;
	}

	/**
	 * Gets the value of the city property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the value of the city property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCity(String value) {
		this.city = value;
	}

	/**
	 * Gets the value of the contactPerson1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPerson1() {
		return contactPerson1;
	}

	/**
	 * Sets the value of the contactPerson1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPerson1(String value) {
		this.contactPerson1 = value;
	}

	/**
	 * Gets the value of the contactPerson2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPerson2() {
		return contactPerson2;
	}

	/**
	 * Sets the value of the contactPerson2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPerson2(String value) {
		this.contactPerson2 = value;
	}

	/**
	 * Gets the value of the contactPerson3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPerson3() {
		return contactPerson3;
	}

	/**
	 * Sets the value of the contactPerson3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPerson3(String value) {
		this.contactPerson3 = value;
	}

	/**
	 * Gets the value of the contactPerson4 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPerson4() {
		return contactPerson4;
	}

	/**
	 * Sets the value of the contactPerson4 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPerson4(String value) {
		this.contactPerson4 = value;
	}

	/**
	 * Gets the value of the contactPerson5 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPerson5() {
		return contactPerson5;
	}

	/**
	 * Sets the value of the contactPerson5 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPerson5(String value) {
		this.contactPerson5 = value;
	}

	/**
	 * Gets the value of the contactTel1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactTel1() {
		return contactTel1;
	}

	/**
	 * Sets the value of the contactTel1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactTel1(String value) {
		this.contactTel1 = value;
	}

	/**
	 * Gets the value of the contactTel2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactTel2() {
		return contactTel2;
	}

	/**
	 * Sets the value of the contactTel2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactTel2(String value) {
		this.contactTel2 = value;
	}

	/**
	 * Gets the value of the contactTel3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactTel3() {
		return contactTel3;
	}

	/**
	 * Sets the value of the contactTel3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactTel3(String value) {
		this.contactTel3 = value;
	}

	/**
	 * Gets the value of the contactTel4 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactTel4() {
		return contactTel4;
	}

	/**
	 * Sets the value of the contactTel4 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactTel4(String value) {
		this.contactTel4 = value;
	}

	/**
	 * Gets the value of the contactTel5 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactTel5() {
		return contactTel5;
	}

	/**
	 * Sets the value of the contactTel5 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactTel5(String value) {
		this.contactTel5 = value;
	}

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateTime(XMLGregorianCalendar value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the creator property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getCreator() {
		return creator;
	}

	/**
	 * Sets the value of the creator property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setCreator(BigDecimal value) {
		this.creator = value;
	}

	/**
	 * Gets the value of the displayName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the value of the displayName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDisplayName(String value) {
		this.displayName = value;
	}

	/**
	 * Gets the value of the iconClass property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIconClass() {
		return iconClass;
	}

	/**
	 * Sets the value of the iconClass property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIconClass(String value) {
		this.iconClass = value;
	}

	/**
	 * Gets the value of the modifyTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getModifyTime() {
		return modifyTime;
	}

	/**
	 * Sets the value of the modifyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setModifyTime(XMLGregorianCalendar value) {
		this.modifyTime = value;
	}

	/**
	 * Gets the value of the operator property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getOperator() {
		return operator;
	}

	/**
	 * Sets the value of the operator property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setOperator(BigDecimal value) {
		this.operator = value;
	}

	/**
	 * Gets the value of the organCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganCode() {
		return organCode;
	}

	/**
	 * Sets the value of the organCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganCode(String value) {
		this.organCode = value;
	}

	/**
	 * Gets the value of the organId property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getOrganId() {
		return organId;
	}

	/**
	 * Sets the value of the organId property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setOrganId(BigDecimal value) {
		this.organId = value;
	}

	/**
	 * Gets the value of the organName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * Sets the value of the organName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganName(String value) {
		this.organName = value;
	}

	/**
	 * Gets the value of the organType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganType() {
		return organType;
	}

	/**
	 * Sets the value of the organType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganType(String value) {
		this.organType = value;
	}

	/**
	 * Gets the value of the postCode property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPostCode() {
		return postCode;
	}

	/**
	 * Sets the value of the postCode property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPostCode(Integer value) {
		this.postCode = value;
	}

	/**
	 * Gets the value of the province property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the value of the province property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProvince(String value) {
		this.province = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemark(String value) {
		this.remark = value;
	}

	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setState(String value) {
		this.state = value;
	}

	/**
	 * Gets the value of the street property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the value of the street property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStreet(String value) {
		this.street = value;
	}

	/**
	 * Gets the value of the sysStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSysStatus() {
		return sysStatus;
	}

	/**
	 * Sets the value of the sysStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSysStatus(String value) {
		this.sysStatus = value;
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setVersion(Long value) {
		this.version = value;
	}

}

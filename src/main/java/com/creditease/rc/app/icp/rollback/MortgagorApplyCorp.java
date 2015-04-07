package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for mortgagorApplyCorp complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorApplyCorp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyCorpId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="corpNature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corpNatureDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corpRegistCapital" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="corpRegistDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerIdentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="shareProportion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
@XmlType(name = "mortgagorApplyCorp", propOrder = { "applyCorpId",
		"corpNature", "corpNatureDesc", "corpRegistCapital", "corpRegistDate",
		"createDate", "customerIdentType", "mortgagorId", "shareProportion",
		"transportId" })
public class MortgagorApplyCorp {

	protected Long applyCorpId;
	protected String corpNature;
	protected String corpNatureDesc;
	protected BigDecimal corpRegistCapital;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar corpRegistDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createDate;
	protected String customerIdentType;
	protected Long mortgagorId;
	protected BigDecimal shareProportion;
	protected Long transportId;

	/**
	 * Gets the value of the applyCorpId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getApplyCorpId() {
		return applyCorpId;
	}

	/**
	 * Sets the value of the applyCorpId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setApplyCorpId(Long value) {
		this.applyCorpId = value;
	}

	/**
	 * Gets the value of the corpNature property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCorpNature() {
		return corpNature;
	}

	/**
	 * Sets the value of the corpNature property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCorpNature(String value) {
		this.corpNature = value;
	}

	/**
	 * Gets the value of the corpNatureDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCorpNatureDesc() {
		return corpNatureDesc;
	}

	/**
	 * Sets the value of the corpNatureDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCorpNatureDesc(String value) {
		this.corpNatureDesc = value;
	}

	/**
	 * Gets the value of the corpRegistCapital property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getCorpRegistCapital() {
		return corpRegistCapital;
	}

	/**
	 * Sets the value of the corpRegistCapital property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setCorpRegistCapital(BigDecimal value) {
		this.corpRegistCapital = value;
	}

	/**
	 * Gets the value of the corpRegistDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCorpRegistDate() {
		return corpRegistDate;
	}

	/**
	 * Sets the value of the corpRegistDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCorpRegistDate(XMLGregorianCalendar value) {
		this.corpRegistDate = value;
	}

	/**
	 * Gets the value of the createDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateDate(XMLGregorianCalendar value) {
		this.createDate = value;
	}

	/**
	 * Gets the value of the customerIdentType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustomerIdentType() {
		return customerIdentType;
	}

	/**
	 * Sets the value of the customerIdentType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustomerIdentType(String value) {
		this.customerIdentType = value;
	}

	/**
	 * Gets the value of the mortgagorId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getMortgagorId() {
		return mortgagorId;
	}

	/**
	 * Sets the value of the mortgagorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setMortgagorId(Long value) {
		this.mortgagorId = value;
	}

	/**
	 * Gets the value of the shareProportion property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getShareProportion() {
		return shareProportion;
	}

	/**
	 * Sets the value of the shareProportion property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setShareProportion(BigDecimal value) {
		this.shareProportion = value;
	}

	/**
	 * Gets the value of the transportId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getTransportId() {
		return transportId;
	}

	/**
	 * Sets the value of the transportId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setTransportId(Long value) {
		this.transportId = value;
	}

}

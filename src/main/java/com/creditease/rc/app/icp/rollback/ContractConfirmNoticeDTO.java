package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for contractConfirmNoticeDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="contractConfirmNoticeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="isPass" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="retInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractConfirmNoticeDTO", propOrder = { "confirmDate",
		"isPass", "retInfo", "transportId" })
public class ContractConfirmNoticeDTO {

	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar confirmDate;
	protected boolean isPass;
	@XmlElement(required = true)
	protected String retInfo;
	@XmlElement(required = true)
	protected String transportId;

	/**
	 * Gets the value of the confirmDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getConfirmDate() {
		return confirmDate;
	}

	/**
	 * Sets the value of the confirmDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setConfirmDate(XMLGregorianCalendar value) {
		this.confirmDate = value;
	}

	/**
	 * Gets the value of the isPass property.
	 * 
	 */
	public boolean isIsPass() {
		return isPass;
	}

	/**
	 * Sets the value of the isPass property.
	 * 
	 */
	public void setIsPass(boolean value) {
		this.isPass = value;
	}

	/**
	 * Gets the value of the retInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRetInfo() {
		return retInfo;
	}

	/**
	 * Sets the value of the retInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRetInfo(String value) {
		this.retInfo = value;
	}

	/**
	 * Gets the value of the transportId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransportId() {
		return transportId;
	}

	/**
	 * Sets the value of the transportId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransportId(String value) {
		this.transportId = value;
	}

}

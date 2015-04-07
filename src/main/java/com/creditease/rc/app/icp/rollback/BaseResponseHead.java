package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for baseResponseHead complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="baseResponseHead">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionCompleteTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="transactionMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionReceiveTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseResponseHead", propOrder = { "transactionCode",
		"transactionCompleteTime", "transactionMsg", "transactionReceiveTime" })
public class BaseResponseHead {

	@XmlElement(required = true)
	protected String transactionCode;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar transactionCompleteTime;
	@XmlElement(required = true)
	protected String transactionMsg;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar transactionReceiveTime;

	/**
	 * Gets the value of the transactionCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransactionCode() {
		return transactionCode;
	}

	/**
	 * Sets the value of the transactionCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionCode(String value) {
		this.transactionCode = value;
	}

	/**
	 * Gets the value of the transactionCompleteTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTransactionCompleteTime() {
		return transactionCompleteTime;
	}

	/**
	 * Sets the value of the transactionCompleteTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTransactionCompleteTime(XMLGregorianCalendar value) {
		this.transactionCompleteTime = value;
	}

	/**
	 * Gets the value of the transactionMsg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransactionMsg() {
		return transactionMsg;
	}

	/**
	 * Sets the value of the transactionMsg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionMsg(String value) {
		this.transactionMsg = value;
	}

	/**
	 * Gets the value of the transactionReceiveTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTransactionReceiveTime() {
		return transactionReceiveTime;
	}

	/**
	 * Sets the value of the transactionReceiveTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTransactionReceiveTime(XMLGregorianCalendar value) {
		this.transactionReceiveTime = value;
	}

}

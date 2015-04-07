package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ceSingleRtReceiptRes complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ceSingleRtReceiptRes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}baseResBillData">
 *       &lt;sequence>
 *         &lt;element name="completeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="handleTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceSingleRtReceiptRes", propOrder = { "completeTime",
		"handleTime", "tradeTime" })
public class CeSingleRtReceiptRes extends BaseResBillData {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar completeTime;
	protected String handleTime;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar tradeTime;

	/**
	 * Gets the value of the completeTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCompleteTime() {
		return completeTime;
	}

	/**
	 * Sets the value of the completeTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCompleteTime(XMLGregorianCalendar value) {
		this.completeTime = value;
	}

	/**
	 * Gets the value of the handleTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHandleTime() {
		return handleTime;
	}

	/**
	 * Sets the value of the handleTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHandleTime(String value) {
		this.handleTime = value;
	}

	/**
	 * Gets the value of the tradeTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTradeTime() {
		return tradeTime;
	}

	/**
	 * Sets the value of the tradeTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTradeTime(XMLGregorianCalendar value) {
		this.tradeTime = value;
	}

}

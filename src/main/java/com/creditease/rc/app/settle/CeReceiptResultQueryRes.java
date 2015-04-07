package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ceReceiptResultQueryRes complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ceReceiptResultQueryRes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}baseCeResQueryData">
 *       &lt;sequence>
 *         &lt;element name="accountSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="completeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="handleTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ceReceiptResultQueryRes", propOrder = { "accountSourceId",
		"completeTime", "handleTime", "productId", "tradeTime" })
public class CeReceiptResultQueryRes extends BaseCeResQueryData {

	protected String accountSourceId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar completeTime;
	protected String handleTime;
	protected String productId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar tradeTime;

	/**
	 * Gets the value of the accountSourceId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountSourceId() {
		return accountSourceId;
	}

	/**
	 * Sets the value of the accountSourceId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountSourceId(String value) {
		this.accountSourceId = value;
	}

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
	 * Gets the value of the productId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the value of the productId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductId(String value) {
		this.productId = value;
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

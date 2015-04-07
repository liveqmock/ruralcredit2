package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for baseResBillData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="baseResBillData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}baseBillData">
 *       &lt;sequence>
 *         &lt;element name="receiveTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="retCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseResBillData", propOrder = { "receiveTime", "retCode",
		"retInfo", "state" })
public abstract class BaseResBillData extends BaseBillData {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar receiveTime;
	protected String retCode;
	protected String retInfo;
	protected String state;

	/**
	 * Gets the value of the receiveTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getReceiveTime() {
		return receiveTime;
	}

	/**
	 * Sets the value of the receiveTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setReceiveTime(XMLGregorianCalendar value) {
		this.receiveTime = value;
	}

	/**
	 * Gets the value of the retCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRetCode() {
		return retCode;
	}

	/**
	 * Sets the value of the retCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRetCode(String value) {
		this.retCode = value;
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

}

package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for baseRequestHeadForMatch complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="baseRequestHeadForMatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseRequestHeadForMatch", propOrder = { "operateTime",
		"operator", "signInfo" })
public class BaseRequestHeadForMatch {

	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar operateTime;
	protected String operator;
	@XmlElement(required = true)
	protected String signInfo;

	/**
	 * Gets the value of the operateTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getOperateTime() {
		return operateTime;
	}

	/**
	 * Sets the value of the operateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setOperateTime(XMLGregorianCalendar value) {
		this.operateTime = value;
	}

	/**
	 * Gets the value of the operator property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Sets the value of the operator property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOperator(String value) {
		this.operator = value;
	}

	/**
	 * Gets the value of the signInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignInfo() {
		return signInfo;
	}

	/**
	 * Sets the value of the signInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSignInfo(String value) {
		this.signInfo = value;
	}

}

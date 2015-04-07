package com.creditease.rc.app.cm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getImgLenderAmount complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="getImgLenderAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bussTableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getImgLenderAmount", propOrder = { "clientId", "bussTableName" })
public class GetImgLenderAmount {

	protected String clientId;
	protected String bussTableName;

	/**
	 * Gets the value of the clientId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Sets the value of the clientId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClientId(String value) {
		this.clientId = value;
	}

	/**
	 * Gets the value of the bussTableName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBussTableName() {
		return bussTableName;
	}

	/**
	 * Sets the value of the bussTableName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBussTableName(String value) {
		this.bussTableName = value;
	}

}

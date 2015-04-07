package com.creditease.rc.app.cm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for achieveIntegrityTypeFilesPath complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="achieveIntegrityTypeFilesPath">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bussTableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filetypes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "achieveIntegrityTypeFilesPath", propOrder = { "bussTableName",
		"clientId", "batchId", "filetypes" })
public class AchieveIntegrityTypeFilesPath {

	protected String bussTableName;
	protected String clientId;
	protected String batchId;
	protected String filetypes;

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
	 * Gets the value of the batchId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * Sets the value of the batchId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBatchId(String value) {
		this.batchId = value;
	}

	/**
	 * Gets the value of the filetypes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFiletypes() {
		return filetypes;
	}

	/**
	 * Sets the value of the filetypes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFiletypes(String value) {
		this.filetypes = value;
	}

}

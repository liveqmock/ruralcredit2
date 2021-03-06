package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ceRespEnv complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ceRespEnv">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceRespEnv", propOrder = { "respCode", "respMsg" })
public class CeRespEnv {

	protected String respCode;
	protected String respMsg;

	/**
	 * Gets the value of the respCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * Sets the value of the respCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRespCode(String value) {
		this.respCode = value;
	}

	/**
	 * Gets the value of the respMsg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * Sets the value of the respMsg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRespMsg(String value) {
		this.respMsg = value;
	}

}

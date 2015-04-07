package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for reconsiderationInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="reconsiderationInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reconsiderationInfo", propOrder = { "applyReason", "memo",
		"transportId" })
public class ReconsiderationInfo {

	protected String applyReason;
	protected String memo;
	protected String transportId;

	/**
	 * Gets the value of the applyReason property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApplyReason() {
		return applyReason;
	}

	/**
	 * Sets the value of the applyReason property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApplyReason(String value) {
		this.applyReason = value;
	}

	/**
	 * Gets the value of the memo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the value of the memo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMemo(String value) {
		this.memo = value;
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

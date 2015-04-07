package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for matchRevokeNoticeDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="matchRevokeNoticeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchRevokeNoticeDTO", propOrder = { "applyID", "contractID",
		"status", "uuid" })
public class MatchRevokeNoticeDTO {

	protected String applyID;
	protected String contractID;
	protected String status;
	protected String uuid;

	/**
	 * Gets the value of the applyID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApplyID() {
		return applyID;
	}

	/**
	 * Sets the value of the applyID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApplyID(String value) {
		this.applyID = value;
	}

	/**
	 * Gets the value of the contractID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContractID() {
		return contractID;
	}

	/**
	 * Sets the value of the contractID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContractID(String value) {
		this.contractID = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

	/**
	 * Gets the value of the uuid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the value of the uuid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUuid(String value) {
		this.uuid = value;
	}

}

package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for reconsiderationFailureInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="reconsiderationFailureInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reconsiderResult" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "reconsiderationFailureInfo", propOrder = { "reconsiderResult",
		"transportId" })
public class ReconsiderationFailureInfo {

	protected Boolean reconsiderResult;
	protected String transportId;

	/**
	 * Gets the value of the reconsiderResult property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isReconsiderResult() {
		return reconsiderResult;
	}

	/**
	 * Sets the value of the reconsiderResult property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setReconsiderResult(Boolean value) {
		this.reconsiderResult = value;
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

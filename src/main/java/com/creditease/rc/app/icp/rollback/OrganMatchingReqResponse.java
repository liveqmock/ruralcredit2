package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for organMatchingReqResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="organMatchingReqResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.server.ws.icp.creditease.com/}trustOrganRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organMatchingReqResponse", propOrder = { "_return" })
public class OrganMatchingReqResponse {

	@XmlElement(name = "return")
	protected TrustOrganRes _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link TrustOrganRes }
	 * 
	 */
	public TrustOrganRes getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link TrustOrganRes }
	 * 
	 */
	public void setReturn(TrustOrganRes value) {
		this._return = value;
	}

}

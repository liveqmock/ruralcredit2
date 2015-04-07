package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for clicReturnFailureReqResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="clicReturnFailureReqResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.server.ws.icp.creditease.com/}returnFailureRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clicReturnFailureReqResponse", propOrder = { "_return" })
public class ClicReturnFailureReqResponse {

	@XmlElement(name = "return")
	protected ReturnFailureRes _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link ReturnFailureRes }
	 * 
	 */
	public ReturnFailureRes getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link ReturnFailureRes }
	 * 
	 */
	public void setReturn(ReturnFailureRes value) {
		this._return = value;
	}

}

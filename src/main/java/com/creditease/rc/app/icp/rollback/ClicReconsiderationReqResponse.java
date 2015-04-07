package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for clicReconsiderationReqResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="clicReconsiderationReqResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.server.ws.icp.creditease.com/}reconsiderationInfoRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clicReconsiderationReqResponse", propOrder = { "_return" })
public class ClicReconsiderationReqResponse {

	@XmlElement(name = "return")
	protected ReconsiderationInfoRes _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link ReconsiderationInfoRes }
	 * 
	 */
	public ReconsiderationInfoRes getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link ReconsiderationInfoRes }
	 * 
	 */
	public void setReturn(ReconsiderationInfoRes value) {
		this._return = value;
	}

}

package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for clicReconsiderationReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="clicReconsiderationReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.server.ws.icp.creditease.com/}reconsiderationInfoReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clicReconsiderationReq", propOrder = { "arg0" })
public class ClicReconsiderationReq {

	protected ReconsiderationInfoReq arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link ReconsiderationInfoReq }
	 * 
	 */
	public ReconsiderationInfoReq getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link ReconsiderationInfoReq }
	 * 
	 */
	public void setArg0(ReconsiderationInfoReq value) {
		this.arg0 = value;
	}

}

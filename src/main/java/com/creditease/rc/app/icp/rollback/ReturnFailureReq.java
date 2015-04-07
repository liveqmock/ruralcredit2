package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for returnFailureReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="returnFailureReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="returnFailureInfo" type="{http://service.server.ws.icp.creditease.com/}returnFailureInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnFailureReq", propOrder = { "returnFailureInfo" })
public class ReturnFailureReq extends BaseRequestHead {

	protected ReturnFailureInfo returnFailureInfo;

	/**
	 * Gets the value of the returnFailureInfo property.
	 * 
	 * @return possible object is {@link ReturnFailureInfo }
	 * 
	 */
	public ReturnFailureInfo getReturnFailureInfo() {
		return returnFailureInfo;
	}

	/**
	 * Sets the value of the returnFailureInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link ReturnFailureInfo }
	 * 
	 */
	public void setReturnFailureInfo(ReturnFailureInfo value) {
		this.returnFailureInfo = value;
	}

}

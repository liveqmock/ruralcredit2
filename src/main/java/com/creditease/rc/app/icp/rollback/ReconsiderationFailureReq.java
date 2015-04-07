package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for reconsiderationFailureReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="reconsiderationFailureReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="reconsiderFailureInfo" type="{http://service.server.ws.icp.creditease.com/}reconsiderationFailureInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reconsiderationFailureReq", propOrder = { "reconsiderFailureInfo" })
public class ReconsiderationFailureReq extends BaseRequestHead {

	protected ReconsiderationFailureInfo reconsiderFailureInfo;

	/**
	 * Gets the value of the reconsiderFailureInfo property.
	 * 
	 * @return possible object is {@link ReconsiderationFailureInfo }
	 * 
	 */
	public ReconsiderationFailureInfo getReconsiderFailureInfo() {
		return reconsiderFailureInfo;
	}

	/**
	 * Sets the value of the reconsiderFailureInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link ReconsiderationFailureInfo }
	 * 
	 */
	public void setReconsiderFailureInfo(ReconsiderationFailureInfo value) {
		this.reconsiderFailureInfo = value;
	}

}

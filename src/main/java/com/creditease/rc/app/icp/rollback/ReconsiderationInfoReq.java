package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for reconsiderationInfoReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="reconsiderationInfoReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="reconsiderationInfo" type="{http://service.server.ws.icp.creditease.com/}reconsiderationInfo" minOccurs="0"/>
 *         &lt;element name="submitType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reconsiderationInfoReq", propOrder = { "reconsiderationInfo",
		"submitType" })
public class ReconsiderationInfoReq extends BaseRequestHead {

	protected ReconsiderationInfo reconsiderationInfo;
	protected Long submitType;

	/**
	 * Gets the value of the reconsiderationInfo property.
	 * 
	 * @return possible object is {@link ReconsiderationInfo }
	 * 
	 */
	public ReconsiderationInfo getReconsiderationInfo() {
		return reconsiderationInfo;
	}

	/**
	 * Sets the value of the reconsiderationInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link ReconsiderationInfo }
	 * 
	 */
	public void setReconsiderationInfo(ReconsiderationInfo value) {
		this.reconsiderationInfo = value;
	}

	/**
	 * Gets the value of the submitType property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSubmitType() {
		return submitType;
	}

	/**
	 * Sets the value of the submitType property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSubmitType(Long value) {
		this.submitType = value;
	}

}

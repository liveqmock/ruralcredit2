package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for clicUploadRiskResultReqResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="clicUploadRiskResultReqResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.server.ws.icp.creditease.com/}uploadRiskResultRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clicUploadRiskResultReqResponse", propOrder = { "_return" })
public class ClicUploadRiskResultReqResponse {

	@XmlElement(name = "return")
	protected UploadRiskResultRes _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link UploadRiskResultRes }
	 * 
	 */
	public UploadRiskResultRes getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link UploadRiskResultRes }
	 * 
	 */
	public void setReturn(UploadRiskResultRes value) {
		this._return = value;
	}

}

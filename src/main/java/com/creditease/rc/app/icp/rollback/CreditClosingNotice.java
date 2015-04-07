package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for creditClosingNotice complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="creditClosingNotice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.server.ws.icp.creditease.com/}creditClosingNoticeReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditClosingNotice", propOrder = { "arg0" })
public class CreditClosingNotice {

	protected CreditClosingNoticeReq arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link CreditClosingNoticeReq }
	 * 
	 */
	public CreditClosingNoticeReq getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link CreditClosingNoticeReq }
	 * 
	 */
	public void setArg0(CreditClosingNoticeReq value) {
		this.arg0 = value;
	}

}

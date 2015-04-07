package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for creditMatchRevokeNotice complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="creditMatchRevokeNotice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.server.ws.icp.creditease.com/}matchRevokeNoticeReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditMatchRevokeNotice", propOrder = { "arg0" })
public class CreditMatchRevokeNotice {

	protected MatchRevokeNoticeReq arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link MatchRevokeNoticeReq }
	 * 
	 */
	public MatchRevokeNoticeReq getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link MatchRevokeNoticeReq }
	 * 
	 */
	public void setArg0(MatchRevokeNoticeReq value) {
		this.arg0 = value;
	}

}

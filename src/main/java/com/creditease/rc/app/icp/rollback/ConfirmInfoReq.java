package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for confirmInfoReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="confirmInfoReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="creditConfirmInfo" type="{http://service.server.ws.icp.creditease.com/}creditConfirmInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmInfoReq", propOrder = { "creditConfirmInfo" })
public class ConfirmInfoReq extends BaseRequestHead {

	protected CreditConfirmInfo creditConfirmInfo;

	/**
	 * Gets the value of the creditConfirmInfo property.
	 * 
	 * @return possible object is {@link CreditConfirmInfo }
	 * 
	 */
	public CreditConfirmInfo getCreditConfirmInfo() {
		return creditConfirmInfo;
	}

	/**
	 * Sets the value of the creditConfirmInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link CreditConfirmInfo }
	 * 
	 */
	public void setCreditConfirmInfo(CreditConfirmInfo value) {
		this.creditConfirmInfo = value;
	}

}

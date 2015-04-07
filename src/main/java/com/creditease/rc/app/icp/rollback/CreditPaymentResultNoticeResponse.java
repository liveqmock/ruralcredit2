package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for creditPaymentResultNoticeResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="creditPaymentResultNoticeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.server.ws.icp.creditease.com/}paymentResultNoticeRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditPaymentResultNoticeResponse", propOrder = { "_return" })
public class CreditPaymentResultNoticeResponse {

	@XmlElement(name = "return")
	protected PaymentResultNoticeRes _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link PaymentResultNoticeRes }
	 * 
	 */
	public PaymentResultNoticeRes getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link PaymentResultNoticeRes }
	 * 
	 */
	public void setReturn(PaymentResultNoticeRes value) {
		this._return = value;
	}

}
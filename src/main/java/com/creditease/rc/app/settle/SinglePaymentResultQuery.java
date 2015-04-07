package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for singlePaymentResultQuery complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="singlePaymentResultQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}cePaymentResultQueryReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "singlePaymentResultQuery", propOrder = { "request" })
public class SinglePaymentResultQuery {

	protected CePaymentResultQueryReq request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link CePaymentResultQueryReq }
	 * 
	 */
	public CePaymentResultQueryReq getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link CePaymentResultQueryReq }
	 * 
	 */
	public void setRequest(CePaymentResultQueryReq value) {
		this.request = value;
	}

}

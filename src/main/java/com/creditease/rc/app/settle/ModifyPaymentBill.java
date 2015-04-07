package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyPaymentBill complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="modifyPaymentBill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}ceModifyPaymentReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyPaymentBill", propOrder = { "request" })
public class ModifyPaymentBill {

	protected CeModifyPaymentReq request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link CeModifyPaymentReq }
	 * 
	 */
	public CeModifyPaymentReq getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link CeModifyPaymentReq }
	 * 
	 */
	public void setRequest(CeModifyPaymentReq value) {
		this.request = value;
	}

}

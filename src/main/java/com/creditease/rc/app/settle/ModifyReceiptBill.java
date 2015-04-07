package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyReceiptBill complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="modifyReceiptBill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}ceModifyReceiptReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyReceiptBill", propOrder = { "request" })
public class ModifyReceiptBill {

	protected CeModifyReceiptReq request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link CeModifyReceiptReq }
	 * 
	 */
	public CeModifyReceiptReq getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link CeModifyReceiptReq }
	 * 
	 */
	public void setRequest(CeModifyReceiptReq value) {
		this.request = value;
	}

}

package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for singleRtReceiptResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="singleRtReceiptResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}ceSingleRtReceiptRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "singleRtReceiptResponse", propOrder = { "_return" })
public class SingleRtReceiptResponse {

	@XmlElement(name = "return")
	protected CeSingleRtReceiptRes _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link CeSingleRtReceiptRes }
	 * 
	 */
	public CeSingleRtReceiptRes getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link CeSingleRtReceiptRes }
	 * 
	 */
	public void setReturn(CeSingleRtReceiptRes value) {
		this._return = value;
	}

}

package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for singleUndo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="singleUndo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}ceUndoReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "singleUndo", propOrder = { "request" })
public class SingleUndo {

	protected CeUndoReq request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link CeUndoReq }
	 * 
	 */
	public CeUndoReq getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link CeUndoReq }
	 * 
	 */
	public void setRequest(CeUndoReq value) {
		this.request = value;
	}

}

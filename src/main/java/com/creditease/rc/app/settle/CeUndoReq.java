package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ceUndoReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ceUndoReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}baseReqBillData">
 *       &lt;sequence>
 *         &lt;element name="undoTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceUndoReq", propOrder = { "undoTypeId" })
public class CeUndoReq extends BaseReqBillData {

	protected String undoTypeId;

	/**
	 * Gets the value of the undoTypeId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUndoTypeId() {
		return undoTypeId;
	}

	/**
	 * Sets the value of the undoTypeId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUndoTypeId(String value) {
		this.undoTypeId = value;
	}

}

package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for electronicContractResultReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="electronicContractResultReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.server.ws.icp.creditease.com/}electronicContractReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "electronicContractResultReq", propOrder = { "arg0" })
public class ElectronicContractResultReq {

	protected ElectronicContractReq arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link ElectronicContractReq }
	 * 
	 */
	public ElectronicContractReq getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link ElectronicContractReq }
	 * 
	 */
	public void setArg0(ElectronicContractReq value) {
		this.arg0 = value;
	}

}
package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for contractReturnReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="contractReturnReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="contractReturnInfo" type="{http://service.server.ws.icp.creditease.com/}contractReturnInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractReturnReq", propOrder = { "contractReturnInfo" })
public class ContractReturnReq extends BaseRequestHead {

	protected ContractReturnInfo contractReturnInfo;

	/**
	 * Gets the value of the contractReturnInfo property.
	 * 
	 * @return possible object is {@link ContractReturnInfo }
	 * 
	 */
	public ContractReturnInfo getContractReturnInfo() {
		return contractReturnInfo;
	}

	/**
	 * Sets the value of the contractReturnInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link ContractReturnInfo }
	 * 
	 */
	public void setContractReturnInfo(ContractReturnInfo value) {
		this.contractReturnInfo = value;
	}

}

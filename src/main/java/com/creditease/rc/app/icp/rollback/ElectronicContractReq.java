package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for electronicContractReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="electronicContractReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="electronicResultInfo" type="{http://service.server.ws.icp.creditease.com/}electronicResultInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "electronicContractReq", propOrder = { "electronicResultInfo" })
public class ElectronicContractReq extends BaseRequestHead {

	protected ElectronicResultInfo electronicResultInfo;

	/**
	 * Gets the value of the electronicResultInfo property.
	 * 
	 * @return possible object is {@link ElectronicResultInfo }
	 * 
	 */
	public ElectronicResultInfo getElectronicResultInfo() {
		return electronicResultInfo;
	}

	/**
	 * Sets the value of the electronicResultInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link ElectronicResultInfo }
	 * 
	 */
	public void setElectronicResultInfo(ElectronicResultInfo value) {
		this.electronicResultInfo = value;
	}

}

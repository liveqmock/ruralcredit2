package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for uploadRiskInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="uploadRiskInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultFlg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="transportId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadRiskInfo", propOrder = { "resultFlg", "transportId" })
public class UploadRiskInfo {

	protected Boolean resultFlg;
	protected String transportId;

	/**
	 * Gets the value of the resultFlg property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isResultFlg() {
		return resultFlg;
	}

	/**
	 * Sets the value of the resultFlg property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setResultFlg(Boolean value) {
		this.resultFlg = value;
	}

	/**
	 * Gets the value of the transportId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransportId() {
		return transportId;
	}

	/**
	 * Sets the value of the transportId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransportId(String value) {
		this.transportId = value;
	}

}

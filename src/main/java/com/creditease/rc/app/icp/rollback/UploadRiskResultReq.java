package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for uploadRiskResultReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="uploadRiskResultReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="uploadRiskInfo" type="{http://service.server.ws.icp.creditease.com/}uploadRiskInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadRiskResultReq", propOrder = { "uploadRiskInfo" })
public class UploadRiskResultReq extends BaseRequestHead {

	protected UploadRiskInfo uploadRiskInfo;

	/**
	 * Gets the value of the uploadRiskInfo property.
	 * 
	 * @return possible object is {@link UploadRiskInfo }
	 * 
	 */
	public UploadRiskInfo getUploadRiskInfo() {
		return uploadRiskInfo;
	}

	/**
	 * Sets the value of the uploadRiskInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link UploadRiskInfo }
	 * 
	 */
	public void setUploadRiskInfo(UploadRiskInfo value) {
		this.uploadRiskInfo = value;
	}

}

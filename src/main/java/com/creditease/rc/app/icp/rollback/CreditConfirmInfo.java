package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for creditConfirmInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="creditConfirmInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditConfirmInfo", propOrder = { "applyId", "resultFlg" })
public class CreditConfirmInfo {

	protected String applyId;
	protected String resultFlg;

	/**
	 * Gets the value of the applyId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApplyId() {
		return applyId;
	}

	/**
	 * Sets the value of the applyId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApplyId(String value) {
		this.applyId = value;
	}

	/**
	 * Gets the value of the resultFlg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResultFlg() {
		return resultFlg;
	}

	/**
	 * Sets the value of the resultFlg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResultFlg(String value) {
		this.resultFlg = value;
	}

}

package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for baseReqBillData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="baseReqBillData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}baseBillData">
 *       &lt;sequence>
 *         &lt;element name="accountSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specialId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseReqBillData", propOrder = { "accountSourceId",
		"accountType", "mobile", "productId", "reserveId", "signInfo",
		"specialId" })
public abstract class BaseReqBillData extends BaseBillData {

	protected String accountSourceId;
	protected String accountType;
	protected String mobile;
	protected String productId;
	protected String reserveId;
	protected String signInfo;
	protected String specialId;

	/**
	 * Gets the value of the accountSourceId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountSourceId() {
		return accountSourceId;
	}

	/**
	 * Sets the value of the accountSourceId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountSourceId(String value) {
		this.accountSourceId = value;
	}

	/**
	 * Gets the value of the accountType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the value of the accountType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountType(String value) {
		this.accountType = value;
	}

	/**
	 * Gets the value of the mobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobile(String value) {
		this.mobile = value;
	}

	/**
	 * Gets the value of the productId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the value of the productId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductId(String value) {
		this.productId = value;
	}

	/**
	 * Gets the value of the reserveId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReserveId() {
		return reserveId;
	}

	/**
	 * Sets the value of the reserveId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReserveId(String value) {
		this.reserveId = value;
	}

	/**
	 * Gets the value of the signInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignInfo() {
		return signInfo;
	}

	/**
	 * Sets the value of the signInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSignInfo(String value) {
		this.signInfo = value;
	}

	/**
	 * Gets the value of the specialId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSpecialId() {
		return specialId;
	}

	/**
	 * Sets the value of the specialId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSpecialId(String value) {
		this.specialId = value;
	}

}

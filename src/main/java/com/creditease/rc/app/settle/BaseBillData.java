package com.creditease.rc.app.settle;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for baseBillData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="baseBillData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="bizId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chnId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseBillData", propOrder = { "amount", "bizId", "chnId",
		"remark", "systemSourceId" })
public abstract class BaseBillData {

	protected BigDecimal amount;
	protected String bizId;
	protected String chnId;
	protected String remark;
	protected String systemSourceId;

	/**
	 * Gets the value of the amount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the value of the amount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setAmount(BigDecimal value) {
		this.amount = value;
	}

	/**
	 * Gets the value of the bizId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBizId() {
		return bizId;
	}

	/**
	 * Sets the value of the bizId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBizId(String value) {
		this.bizId = value;
	}

	/**
	 * Gets the value of the chnId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChnId() {
		return chnId;
	}

	/**
	 * Sets the value of the chnId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChnId(String value) {
		this.chnId = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemark(String value) {
		this.remark = value;
	}

	/**
	 * Gets the value of the systemSourceId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSystemSourceId() {
		return systemSourceId;
	}

	/**
	 * Sets the value of the systemSourceId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSystemSourceId(String value) {
		this.systemSourceId = value;
	}

}

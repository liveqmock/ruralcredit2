package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ceModifyReceiptReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ceModifyReceiptReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}baseReqBillData">
 *       &lt;sequence>
 *         &lt;element name="accountFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="openBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payBranchNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reserveTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceModifyReceiptReq", propOrder = { "accountFlag",
		"accountName", "accountNo", "bankId", "cardKind", "idCard",
		"openBankName", "payBranchNo", "reserveFlag", "reserveTime" })
public class CeModifyReceiptReq extends BaseReqBillData {

	protected String accountFlag;
	protected String accountName;
	protected String accountNo;
	protected String bankId;
	protected String cardKind;
	protected String idCard;
	protected String openBankName;
	protected String payBranchNo;
	protected boolean reserveFlag;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar reserveTime;

	/**
	 * Gets the value of the accountFlag property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountFlag() {
		return accountFlag;
	}

	/**
	 * Sets the value of the accountFlag property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountFlag(String value) {
		this.accountFlag = value;
	}

	/**
	 * Gets the value of the accountName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Sets the value of the accountName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountName(String value) {
		this.accountName = value;
	}

	/**
	 * Gets the value of the accountNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * Sets the value of the accountNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountNo(String value) {
		this.accountNo = value;
	}

	/**
	 * Gets the value of the bankId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * Sets the value of the bankId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBankId(String value) {
		this.bankId = value;
	}

	/**
	 * Gets the value of the cardKind property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCardKind() {
		return cardKind;
	}

	/**
	 * Sets the value of the cardKind property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCardKind(String value) {
		this.cardKind = value;
	}

	/**
	 * Gets the value of the idCard property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * Sets the value of the idCard property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdCard(String value) {
		this.idCard = value;
	}

	/**
	 * Gets the value of the openBankName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOpenBankName() {
		return openBankName;
	}

	/**
	 * Sets the value of the openBankName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOpenBankName(String value) {
		this.openBankName = value;
	}

	/**
	 * Gets the value of the payBranchNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayBranchNo() {
		return payBranchNo;
	}

	/**
	 * Sets the value of the payBranchNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayBranchNo(String value) {
		this.payBranchNo = value;
	}

	/**
	 * Gets the value of the reserveFlag property.
	 * 
	 */
	public boolean isReserveFlag() {
		return reserveFlag;
	}

	/**
	 * Sets the value of the reserveFlag property.
	 * 
	 */
	public void setReserveFlag(boolean value) {
		this.reserveFlag = value;
	}

	/**
	 * Gets the value of the reserveTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getReserveTime() {
		return reserveTime;
	}

	/**
	 * Sets the value of the reserveTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setReserveTime(XMLGregorianCalendar value) {
		this.reserveTime = value;
	}

}

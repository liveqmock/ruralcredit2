package com.creditease.rc.app.icp.rollback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for loanReceipt complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="loanReceipt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="borrowContractId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="historyFlag" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="historyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lenderAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lenderIdNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lenderIdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lenderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lenderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanReceiptId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="makeMatchNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthAccrual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loanReceipt", propOrder = { "borrowContractId", "createDate",
		"historyFlag", "historyId", "lenderAmount", "lenderIdNum",
		"lenderIdType", "lenderName", "lenderNo", "loanReceiptId",
		"makeMatchNo", "monthAccrual", "monthCorpus" })
public class LoanReceipt {

	protected Long borrowContractId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createDate;
	protected Short historyFlag;
	protected Long historyId;
	protected String lenderAmount;
	protected String lenderIdNum;
	protected String lenderIdType;
	protected String lenderName;
	protected String lenderNo;
	protected Long loanReceiptId;
	protected String makeMatchNo;
	protected String monthAccrual;
	protected String monthCorpus;

	/**
	 * Gets the value of the borrowContractId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getBorrowContractId() {
		return borrowContractId;
	}

	/**
	 * Sets the value of the borrowContractId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setBorrowContractId(Long value) {
		this.borrowContractId = value;
	}

	/**
	 * Gets the value of the createDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateDate(XMLGregorianCalendar value) {
		this.createDate = value;
	}

	/**
	 * Gets the value of the historyFlag property.
	 * 
	 * @return possible object is {@link Short }
	 * 
	 */
	public Short getHistoryFlag() {
		return historyFlag;
	}

	/**
	 * Sets the value of the historyFlag property.
	 * 
	 * @param value
	 *            allowed object is {@link Short }
	 * 
	 */
	public void setHistoryFlag(Short value) {
		this.historyFlag = value;
	}

	/**
	 * Gets the value of the historyId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getHistoryId() {
		return historyId;
	}

	/**
	 * Sets the value of the historyId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setHistoryId(Long value) {
		this.historyId = value;
	}

	/**
	 * Gets the value of the lenderAmount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLenderAmount() {
		return lenderAmount;
	}

	/**
	 * Sets the value of the lenderAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLenderAmount(String value) {
		this.lenderAmount = value;
	}

	/**
	 * Gets the value of the lenderIdNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLenderIdNum() {
		return lenderIdNum;
	}

	/**
	 * Sets the value of the lenderIdNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLenderIdNum(String value) {
		this.lenderIdNum = value;
	}

	/**
	 * Gets the value of the lenderIdType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLenderIdType() {
		return lenderIdType;
	}

	/**
	 * Sets the value of the lenderIdType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLenderIdType(String value) {
		this.lenderIdType = value;
	}

	/**
	 * Gets the value of the lenderName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLenderName() {
		return lenderName;
	}

	/**
	 * Sets the value of the lenderName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLenderName(String value) {
		this.lenderName = value;
	}

	/**
	 * Gets the value of the lenderNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLenderNo() {
		return lenderNo;
	}

	/**
	 * Sets the value of the lenderNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLenderNo(String value) {
		this.lenderNo = value;
	}

	/**
	 * Gets the value of the loanReceiptId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getLoanReceiptId() {
		return loanReceiptId;
	}

	/**
	 * Sets the value of the loanReceiptId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setLoanReceiptId(Long value) {
		this.loanReceiptId = value;
	}

	/**
	 * Gets the value of the makeMatchNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMakeMatchNo() {
		return makeMatchNo;
	}

	/**
	 * Sets the value of the makeMatchNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMakeMatchNo(String value) {
		this.makeMatchNo = value;
	}

	/**
	 * Gets the value of the monthAccrual property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMonthAccrual() {
		return monthAccrual;
	}

	/**
	 * Sets the value of the monthAccrual property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMonthAccrual(String value) {
		this.monthAccrual = value;
	}

	/**
	 * Gets the value of the monthCorpus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMonthCorpus() {
		return monthCorpus;
	}

	/**
	 * Sets the value of the monthCorpus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMonthCorpus(String value) {
		this.monthCorpus = value;
	}

}

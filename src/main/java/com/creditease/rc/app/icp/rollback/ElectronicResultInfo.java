package com.creditease.rc.app.icp.rollback;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for electronicResultInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="electronicResultInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanReceiptList" type="{http://service.server.ws.icp.creditease.com/}loanReceipt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="matchResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "electronicResultInfo", propOrder = { "applyId",
		"loanReceiptList", "matchResult", "url" })
public class ElectronicResultInfo {

	protected String applyId;
	@XmlElement(nillable = true)
	protected List<LoanReceipt> loanReceiptList;
	protected String matchResult;
	protected String url;

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
	 * Gets the value of the loanReceiptList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the loanReceiptList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getLoanReceiptList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link LoanReceipt }
	 * 
	 * 
	 */
	public List<LoanReceipt> getLoanReceiptList() {
		if (loanReceiptList == null) {
			loanReceiptList = new ArrayList<LoanReceipt>();
		}
		return this.loanReceiptList;
	}

	/**
	 * Gets the value of the matchResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMatchResult() {
		return matchResult;
	}

	/**
	 * Sets the value of the matchResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMatchResult(String value) {
		this.matchResult = value;
	}

	/**
	 * Gets the value of the url property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the value of the url property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUrl(String value) {
		this.url = value;
	}

}

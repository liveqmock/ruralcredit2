package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerInfoReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="customerInfoReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="creditCustomerInfoVO" type="{http://service.server.ws.icp.creditease.com/}creditCustomerInfoVO" minOccurs="0"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="revolvingCreditList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="submitType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerInfoReq", propOrder = { "amount",
		"creditCustomerInfoVO", "loanAmount", "productName",
		"revolvingCreditList", "submitType" })
public class CustomerInfoReq extends BaseRequestHead {

	protected BigDecimal amount;
	protected CreditCustomerInfoVO creditCustomerInfoVO;
	protected BigDecimal loanAmount;
	protected String productName;
	@XmlElement(nillable = true)
	protected List<String> revolvingCreditList;
	protected Long submitType;

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
	 * Gets the value of the creditCustomerInfoVO property.
	 * 
	 * @return possible object is {@link CreditCustomerInfoVO }
	 * 
	 */
	public CreditCustomerInfoVO getCreditCustomerInfoVO() {
		return creditCustomerInfoVO;
	}

	/**
	 * Sets the value of the creditCustomerInfoVO property.
	 * 
	 * @param value
	 *            allowed object is {@link CreditCustomerInfoVO }
	 * 
	 */
	public void setCreditCustomerInfoVO(CreditCustomerInfoVO value) {
		this.creditCustomerInfoVO = value;
	}

	/**
	 * Gets the value of the loanAmount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	/**
	 * Sets the value of the loanAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setLoanAmount(BigDecimal value) {
		this.loanAmount = value;
	}

	/**
	 * Gets the value of the productName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the value of the productName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductName(String value) {
		this.productName = value;
	}

	/**
	 * Gets the value of the revolvingCreditList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the revolvingCreditList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRevolvingCreditList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getRevolvingCreditList() {
		if (revolvingCreditList == null) {
			revolvingCreditList = new ArrayList<String>();
		}
		return this.revolvingCreditList;
	}

	/**
	 * Gets the value of the submitType property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSubmitType() {
		return submitType;
	}

	/**
	 * Sets the value of the submitType property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSubmitType(Long value) {
		this.submitType = value;
	}

}

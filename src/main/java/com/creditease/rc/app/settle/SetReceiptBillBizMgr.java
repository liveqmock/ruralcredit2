package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for setReceiptBillBizMgr complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="setReceiptBillBizMgr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="receiptBillBizMgr" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}receiptBillBizMgr" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setReceiptBillBizMgr", propOrder = { "receiptBillBizMgr" })
public class SetReceiptBillBizMgr {

	protected ReceiptBillBizMgr receiptBillBizMgr;

	/**
	 * Gets the value of the receiptBillBizMgr property.
	 * 
	 * @return possible object is {@link ReceiptBillBizMgr }
	 * 
	 */
	public ReceiptBillBizMgr getReceiptBillBizMgr() {
		return receiptBillBizMgr;
	}

	/**
	 * Sets the value of the receiptBillBizMgr property.
	 * 
	 * @param value
	 *            allowed object is {@link ReceiptBillBizMgr }
	 * 
	 */
	public void setReceiptBillBizMgr(ReceiptBillBizMgr value) {
		this.receiptBillBizMgr = value;
	}

}

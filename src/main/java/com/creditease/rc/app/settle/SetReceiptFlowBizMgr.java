package com.creditease.rc.app.settle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for setReceiptFlowBizMgr complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="setReceiptFlowBizMgr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="receiptFlowBizMgr" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}receiptFlowBizMgr" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setReceiptFlowBizMgr", propOrder = { "receiptFlowBizMgr" })
public class SetReceiptFlowBizMgr {

	protected ReceiptFlowBizMgr receiptFlowBizMgr;

	/**
	 * Gets the value of the receiptFlowBizMgr property.
	 * 
	 * @return possible object is {@link ReceiptFlowBizMgr }
	 * 
	 */
	public ReceiptFlowBizMgr getReceiptFlowBizMgr() {
		return receiptFlowBizMgr;
	}

	/**
	 * Sets the value of the receiptFlowBizMgr property.
	 * 
	 * @param value
	 *            allowed object is {@link ReceiptFlowBizMgr }
	 * 
	 */
	public void setReceiptFlowBizMgr(ReceiptFlowBizMgr value) {
		this.receiptFlowBizMgr = value;
	}

}

package com.creditease.rc.app.settle;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for batchPaymentResultQuery complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="batchPaymentResultQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestList" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}cePaymentResultQueryReq" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchPaymentResultQuery", propOrder = { "requestList" })
public class BatchPaymentResultQuery {

	protected List<CePaymentResultQueryReq> requestList;

	/**
	 * Gets the value of the requestList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the requestList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRequestList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CePaymentResultQueryReq }
	 * 
	 * 
	 */
	public List<CePaymentResultQueryReq> getRequestList() {
		if (requestList == null) {
			requestList = new ArrayList<CePaymentResultQueryReq>();
		}
		return this.requestList;
	}

}

package com.creditease.rc.app.settle;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for batchReceiptResultQuery complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="batchReceiptResultQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestList" type="{http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/}ceReceiptResultQueryReq" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchReceiptResultQuery", propOrder = { "requestList" })
public class BatchReceiptResultQuery {

	protected List<CeReceiptResultQueryReq> requestList;

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
	 * {@link CeReceiptResultQueryReq }
	 * 
	 * 
	 */
	public List<CeReceiptResultQueryReq> getRequestList() {
		if (requestList == null) {
			requestList = new ArrayList<CeReceiptResultQueryReq>();
		}
		return this.requestList;
	}

}

package com.creditease.rc.app.icp.rollback;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for paymentResultNoticeReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="paymentResultNoticeReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="paymentResultNoticeDTOList" type="{http://service.server.ws.icp.creditease.com/}paymentResultNoticeDTO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentResultNoticeReq", propOrder = { "paymentResultNoticeDTOList" })
public class PaymentResultNoticeReq extends BaseRequestHead {

	@XmlElement(required = true)
	protected List<PaymentResultNoticeDTO> paymentResultNoticeDTOList;

	/**
	 * Gets the value of the paymentResultNoticeDTOList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the paymentResultNoticeDTOList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPaymentResultNoticeDTOList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PaymentResultNoticeDTO }
	 * 
	 * 
	 */
	public List<PaymentResultNoticeDTO> getPaymentResultNoticeDTOList() {
		if (paymentResultNoticeDTOList == null) {
			paymentResultNoticeDTOList = new ArrayList<PaymentResultNoticeDTO>();
		}
		return this.paymentResultNoticeDTOList;
	}

}

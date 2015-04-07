package com.creditease.rc.app.icp.rollback;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for creditClosingNoticeReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="creditClosingNoticeReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="creditClosingNoticeDTOList" type="{http://service.server.ws.icp.creditease.com/}creditClosingNoticeDTO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditClosingNoticeReq", propOrder = { "creditClosingNoticeDTOList" })
public class CreditClosingNoticeReq extends BaseRequestHead {

	@XmlElement(required = true)
	protected List<CreditClosingNoticeDTO> creditClosingNoticeDTOList;

	/**
	 * Gets the value of the creditClosingNoticeDTOList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the creditClosingNoticeDTOList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCreditClosingNoticeDTOList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CreditClosingNoticeDTO }
	 * 
	 * 
	 */
	public List<CreditClosingNoticeDTO> getCreditClosingNoticeDTOList() {
		if (creditClosingNoticeDTOList == null) {
			creditClosingNoticeDTOList = new ArrayList<CreditClosingNoticeDTO>();
		}
		return this.creditClosingNoticeDTOList;
	}

}

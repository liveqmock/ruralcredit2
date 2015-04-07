package com.creditease.rc.app.icp.rollback;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for matchRevokeNoticeReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="matchRevokeNoticeReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHeadForMatch">
 *       &lt;sequence>
 *         &lt;element name="matchRevokeNoticeDTOList" type="{http://service.server.ws.icp.creditease.com/}matchRevokeNoticeDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchRevokeNoticeReq", propOrder = { "matchRevokeNoticeDTOList" })
public class MatchRevokeNoticeReq extends BaseRequestHeadForMatch {

	@XmlElement(nillable = true)
	protected List<MatchRevokeNoticeDTO> matchRevokeNoticeDTOList;

	/**
	 * Gets the value of the matchRevokeNoticeDTOList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the matchRevokeNoticeDTOList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMatchRevokeNoticeDTOList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MatchRevokeNoticeDTO }
	 * 
	 * 
	 */
	public List<MatchRevokeNoticeDTO> getMatchRevokeNoticeDTOList() {
		if (matchRevokeNoticeDTOList == null) {
			matchRevokeNoticeDTOList = new ArrayList<MatchRevokeNoticeDTO>();
		}
		return this.matchRevokeNoticeDTOList;
	}

}

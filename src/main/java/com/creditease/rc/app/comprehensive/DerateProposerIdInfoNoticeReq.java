
package com.creditease.rc.app.comprehensive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for derateProposerIdInfoNoticeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="derateProposerIdInfoNoticeReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="derateProposerIdInfoNoticeDTOList" type="{http://service.server.ws.icp.creditease.com/}derateProposerIdInfoNoticeDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "derateProposerIdInfoNoticeReq", propOrder = {
    "derateProposerIdInfoNoticeDTOList"
})
public class DerateProposerIdInfoNoticeReq {

    @XmlElement(nillable = true)
    protected List<DerateProposerIdInfoNoticeDTO> derateProposerIdInfoNoticeDTOList;

    /**
     * Gets the value of the derateProposerIdInfoNoticeDTOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the derateProposerIdInfoNoticeDTOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDerateProposerIdInfoNoticeDTOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DerateProposerIdInfoNoticeDTO }
     * 
     * 
     */
    public List<DerateProposerIdInfoNoticeDTO> getDerateProposerIdInfoNoticeDTOList() {
        if (derateProposerIdInfoNoticeDTOList == null) {
            derateProposerIdInfoNoticeDTOList = new ArrayList<DerateProposerIdInfoNoticeDTO>();
        }
        return this.derateProposerIdInfoNoticeDTOList;
    }

}

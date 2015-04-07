
package com.creditease.rc.app.orgams2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contractConfirmNoticeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractConfirmNoticeReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.icp.ws.component.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="contractConfirmNoticeDTOList" type="{http://service.icp.ws.component.creditease.com/}contractConfirmNoticeDTO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractConfirmNoticeReq", propOrder = {
    "contractConfirmNoticeDTOList"
})
public class ContractConfirmNoticeReq
    extends BaseRequestHead
{

    @XmlElement(required = true)
    protected List<ContractConfirmNoticeDTO> contractConfirmNoticeDTOList;

    /**
     * Gets the value of the contractConfirmNoticeDTOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractConfirmNoticeDTOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractConfirmNoticeDTOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractConfirmNoticeDTO }
     * 
     * 
     */
    public List<ContractConfirmNoticeDTO> getContractConfirmNoticeDTOList() {
        if (contractConfirmNoticeDTOList == null) {
            contractConfirmNoticeDTOList = new ArrayList<ContractConfirmNoticeDTO>();
        }
        return this.contractConfirmNoticeDTOList;
    }

}

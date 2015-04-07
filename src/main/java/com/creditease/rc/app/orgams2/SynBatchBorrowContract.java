
package com.creditease.rc.app.orgams2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for synBatchBorrowContract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="synBatchBorrowContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestList" type="{http://service.icp.ws.component.creditease.com/}borrowContractReq" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "synBatchBorrowContract", propOrder = {
    "requestList"
})
public class SynBatchBorrowContract {

    protected List<BorrowContractReq> requestList;

    /**
     * Gets the value of the requestList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowContractReq }
     * 
     * 
     */
    public List<BorrowContractReq> getRequestList() {
        if (requestList == null) {
            requestList = new ArrayList<BorrowContractReq>();
        }
        return this.requestList;
    }

}

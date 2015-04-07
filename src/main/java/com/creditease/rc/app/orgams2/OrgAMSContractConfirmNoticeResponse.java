
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrgAMSContractConfirmNoticeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrgAMSContractConfirmNoticeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.icp.ws.component.creditease.com/}contractConfirmNoticeRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgAMSContractConfirmNoticeResponse", propOrder = {
    "_return"
})
public class OrgAMSContractConfirmNoticeResponse {

    @XmlElement(name = "return")
    protected ContractConfirmNoticeRes _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ContractConfirmNoticeRes }
     *     
     */
    public ContractConfirmNoticeRes getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractConfirmNoticeRes }
     *     
     */
    public void setReturn(ContractConfirmNoticeRes value) {
        this._return = value;
    }

}

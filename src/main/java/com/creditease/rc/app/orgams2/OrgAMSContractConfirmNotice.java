
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrgAMSContractConfirmNotice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrgAMSContractConfirmNotice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.icp.ws.component.creditease.com/}contractConfirmNoticeReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgAMSContractConfirmNotice", propOrder = {
    "arg0"
})
public class OrgAMSContractConfirmNotice {

    protected ContractConfirmNoticeReq arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link ContractConfirmNoticeReq }
     *     
     */
    public ContractConfirmNoticeReq getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractConfirmNoticeReq }
     *     
     */
    public void setArg0(ContractConfirmNoticeReq value) {
        this.arg0 = value;
    }

}

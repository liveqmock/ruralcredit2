
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ceAgreementDownloadReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ceAgreementDownloadReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractReqParam" type="{http://www.creditease.cn}contractReqParam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceAgreementDownloadReq", propOrder = {
    "contractReqParam"
})
public class CeAgreementDownloadReq {

    protected ContractReqParam contractReqParam;

    /**
     * Gets the value of the contractReqParam property.
     * 
     * @return
     *     possible object is
     *     {@link ContractReqParam }
     *     
     */
    public ContractReqParam getContractReqParam() {
        return contractReqParam;
    }

    /**
     * Sets the value of the contractReqParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractReqParam }
     *     
     */
    public void setContractReqParam(ContractReqParam value) {
        this.contractReqParam = value;
    }

}

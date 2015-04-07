
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ceP2PAgreementDownloadReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ceP2PAgreementDownloadReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="p2PContractReqParam" type="{http://www.creditease.cn}p2PContractReqParam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceP2PAgreementDownloadReq", propOrder = {
    "p2PContractReqParam"
})
public class CeP2PAgreementDownloadReq {

    protected P2PContractReqParam p2PContractReqParam;

    /**
     * Gets the value of the p2PContractReqParam property.
     * 
     * @return
     *     possible object is
     *     {@link P2PContractReqParam }
     *     
     */
    public P2PContractReqParam getP2PContractReqParam() {
        return p2PContractReqParam;
    }

    /**
     * Sets the value of the p2PContractReqParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link P2PContractReqParam }
     *     
     */
    public void setP2PContractReqParam(P2PContractReqParam value) {
        this.p2PContractReqParam = value;
    }

}


package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchEncodeQRCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchEncodeQRCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bc" type="{http://www.creditease.cn}batchContentReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchEncodeQRCode", propOrder = {
    "bc"
})
public class BatchEncodeQRCode {

    protected BatchContentReq bc;

    /**
     * Gets the value of the bc property.
     * 
     * @return
     *     possible object is
     *     {@link BatchContentReq }
     *     
     */
    public BatchContentReq getBc() {
        return bc;
    }

    /**
     * Sets the value of the bc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchContentReq }
     *     
     */
    public void setBc(BatchContentReq value) {
        this.bc = value;
    }

}

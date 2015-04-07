
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchDecodeQRCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchDecodeQRCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bp" type="{http://www.creditease.cn}batchPictureReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchDecodeQRCode", propOrder = {
    "bp"
})
public class BatchDecodeQRCode {

    protected BatchPictureReq bp;

    /**
     * Gets the value of the bp property.
     * 
     * @return
     *     possible object is
     *     {@link BatchPictureReq }
     *     
     */
    public BatchPictureReq getBp() {
        return bp;
    }

    /**
     * Sets the value of the bp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchPictureReq }
     *     
     */
    public void setBp(BatchPictureReq value) {
        this.bp = value;
    }

}

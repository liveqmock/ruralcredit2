
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for originalStatusNoticeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="originalStatusNoticeReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.icp.ws.component.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="auditOriginalInfo" type="{http://service.icp.ws.component.creditease.com/}auditOriginalInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "originalStatusNoticeReq", propOrder = {
    "auditOriginalInfo"
})
public class OriginalStatusNoticeReq
    extends BaseRequestHead
{

    protected AuditOriginalInfo auditOriginalInfo;

    /**
     * Gets the value of the auditOriginalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AuditOriginalInfo }
     *     
     */
    public AuditOriginalInfo getAuditOriginalInfo() {
        return auditOriginalInfo;
    }

    /**
     * Sets the value of the auditOriginalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuditOriginalInfo }
     *     
     */
    public void setAuditOriginalInfo(AuditOriginalInfo value) {
        this.auditOriginalInfo = value;
    }

}

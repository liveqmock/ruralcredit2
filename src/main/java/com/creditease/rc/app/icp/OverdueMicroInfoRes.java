
package com.creditease.rc.app.icp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for overdueMicroInfoRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overdueMicroInfoRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overduelist" type="{http://service.server.ws.icp.creditease.com/}overdueMicroInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overdueMicroInfoRes", propOrder = {
    "codeInfo",
    "message",
    "overduelist"
})
public class OverdueMicroInfoRes {

    protected String codeInfo;
    protected String message;
    @XmlElement(nillable = true)
    protected List<OverdueMicroInfoDTO> overduelist;

    /**
     * Gets the value of the codeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeInfo() {
        return codeInfo;
    }

    /**
     * Sets the value of the codeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeInfo(String value) {
        this.codeInfo = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the overduelist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the overduelist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOverduelist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OverdueMicroInfoDTO }
     * 
     * 
     */
    public List<OverdueMicroInfoDTO> getOverduelist() {
        if (overduelist == null) {
            overduelist = new ArrayList<OverdueMicroInfoDTO>();
        }
        return this.overduelist;
    }

}


package com.creditease.rc.app.orgams2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trustOrganReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trustOrganReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.icp.ws.component.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="trustOrganList" type="{http://service.icp.ws.component.creditease.com/}trustOrgan" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trustOrganReq", propOrder = {
    "trustOrganList"
})
public class TrustOrganReq
    extends BaseRequestHead
{

    @XmlElement(nillable = true)
    protected List<TrustOrgan> trustOrganList;

    /**
     * Gets the value of the trustOrganList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trustOrganList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrustOrganList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrustOrgan }
     * 
     * 
     */
    public List<TrustOrgan> getTrustOrganList() {
        if (trustOrganList == null) {
            trustOrganList = new ArrayList<TrustOrgan>();
        }
        return this.trustOrganList;
    }

}


package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OverdueInfoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverdueInfoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeIdList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="officeIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}officeIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverdueInfoRequest", propOrder = {
    "signID",
    "officeIdList"
})
public class OverdueInfoRequest {

    protected String signID;
    @XmlElement(required = true)
    protected OverdueInfoRequest.OfficeIdList officeIdList;

    /**
     * Gets the value of the signID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignID() {
        return signID;
    }

    /**
     * Sets the value of the signID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignID(String value) {
        this.signID = value;
    }

    /**
     * Gets the value of the officeIdList property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueInfoRequest.OfficeIdList }
     *     
     */
    public OverdueInfoRequest.OfficeIdList getOfficeIdList() {
        return officeIdList;
    }

    /**
     * Sets the value of the officeIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueInfoRequest.OfficeIdList }
     *     
     */
    public void setOfficeIdList(OverdueInfoRequest.OfficeIdList value) {
        this.officeIdList = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="officeIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}officeIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "officeIdListDTO"
    })
    public static class OfficeIdList {

        protected List<OfficeIdListDTO> officeIdListDTO;

        /**
         * Gets the value of the officeIdListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the officeIdListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOfficeIdListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OfficeIdListDTO }
         * 
         * 
         */
        public List<OfficeIdListDTO> getOfficeIdListDTO() {
            if (officeIdListDTO == null) {
                officeIdListDTO = new ArrayList<OfficeIdListDTO>();
            }
            return this.officeIdListDTO;
        }

    }

}

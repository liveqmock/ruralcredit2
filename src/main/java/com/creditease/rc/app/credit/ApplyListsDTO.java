
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applyListsDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applyListsDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="r_sellIdList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="r_sellIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}r_sellIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="overdueCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applyListsDTO", propOrder = {
    "officeId",
    "rSellIdList",
    "overdueCount"
})
public class ApplyListsDTO {

    protected String officeId;
    @XmlElement(name = "r_sellIdList")
    protected ApplyListsDTO.RSellIdList rSellIdList;
    protected String overdueCount;

    /**
     * Gets the value of the officeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * Sets the value of the officeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeId(String value) {
        this.officeId = value;
    }

    /**
     * Gets the value of the rSellIdList property.
     * 
     * @return
     *     possible object is
     *     {@link ApplyListsDTO.RSellIdList }
     *     
     */
    public ApplyListsDTO.RSellIdList getRSellIdList() {
        return rSellIdList;
    }

    /**
     * Sets the value of the rSellIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplyListsDTO.RSellIdList }
     *     
     */
    public void setRSellIdList(ApplyListsDTO.RSellIdList value) {
        this.rSellIdList = value;
    }

    /**
     * Gets the value of the overdueCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverdueCount() {
        return overdueCount;
    }

    /**
     * Sets the value of the overdueCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverdueCount(String value) {
        this.overdueCount = value;
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
     *         &lt;element name="r_sellIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}r_sellIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "rSellIdListDTO"
    })
    public static class RSellIdList {

        @XmlElement(name = "r_sellIdListDTO")
        protected List<RSellIdListDTO> rSellIdListDTO;

        /**
         * Gets the value of the rSellIdListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rSellIdListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRSellIdListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RSellIdListDTO }
         * 
         * 
         */
        public List<RSellIdListDTO> getRSellIdListDTO() {
            if (rSellIdListDTO == null) {
                rSellIdListDTO = new ArrayList<RSellIdListDTO>();
            }
            return this.rSellIdListDTO;
        }

    }

}

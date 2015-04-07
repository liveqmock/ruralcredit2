
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for r_sellIdListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="r_sellIdListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="r_sellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applyIdList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="applyIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}applyIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="s_overdueCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "r_sellIdListDTO", propOrder = {
    "rSellId",
    "applyIdList",
    "sOverdueCount"
})
public class RSellIdListDTO {

    @XmlElement(name = "r_sellId")
    protected String rSellId;
    protected RSellIdListDTO.ApplyIdList applyIdList;
    @XmlElement(name = "s_overdueCount")
    protected String sOverdueCount;

    /**
     * Gets the value of the rSellId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRSellId() {
        return rSellId;
    }

    /**
     * Sets the value of the rSellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRSellId(String value) {
        this.rSellId = value;
    }

    /**
     * Gets the value of the applyIdList property.
     * 
     * @return
     *     possible object is
     *     {@link RSellIdListDTO.ApplyIdList }
     *     
     */
    public RSellIdListDTO.ApplyIdList getApplyIdList() {
        return applyIdList;
    }

    /**
     * Sets the value of the applyIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RSellIdListDTO.ApplyIdList }
     *     
     */
    public void setApplyIdList(RSellIdListDTO.ApplyIdList value) {
        this.applyIdList = value;
    }

    /**
     * Gets the value of the sOverdueCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOverdueCount() {
        return sOverdueCount;
    }

    /**
     * Sets the value of the sOverdueCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOverdueCount(String value) {
        this.sOverdueCount = value;
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
     *         &lt;element name="applyIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}applyIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "applyIdListDTO"
    })
    public static class ApplyIdList {

        protected List<ApplyIdListDTO> applyIdListDTO;

        /**
         * Gets the value of the applyIdListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the applyIdListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApplyIdListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ApplyIdListDTO }
         * 
         * 
         */
        public List<ApplyIdListDTO> getApplyIdListDTO() {
            if (applyIdListDTO == null) {
                applyIdListDTO = new ArrayList<ApplyIdListDTO>();
            }
            return this.applyIdListDTO;
        }

    }

}

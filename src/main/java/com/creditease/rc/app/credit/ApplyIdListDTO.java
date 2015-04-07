
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applyIdListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applyIdListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="a_overdueStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="a_overdueMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="a_overdueCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overdueDayCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applyDateList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="applyDateListDTO" type="{http://www.creditease.cn/RuralBusyService/}applyDateListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "applyIdListDTO", propOrder = {
    "applyId",
    "aOverdueStart",
    "aOverdueMoney",
    "aOverdueCount",
    "overdueDayCount",
    "applyDateList"
})
public class ApplyIdListDTO {

    protected String applyId;
    @XmlElement(name = "a_overdueStart")
    protected String aOverdueStart;
    @XmlElement(name = "a_overdueMoney")
    protected String aOverdueMoney;
    @XmlElement(name = "a_overdueCount")
    protected String aOverdueCount;
    protected String overdueDayCount;
    protected ApplyIdListDTO.ApplyDateList applyDateList;

    /**
     * Gets the value of the applyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * Sets the value of the applyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyId(String value) {
        this.applyId = value;
    }

    /**
     * Gets the value of the aOverdueStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAOverdueStart() {
        return aOverdueStart;
    }

    /**
     * Sets the value of the aOverdueStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAOverdueStart(String value) {
        this.aOverdueStart = value;
    }

    /**
     * Gets the value of the aOverdueMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAOverdueMoney() {
        return aOverdueMoney;
    }

    /**
     * Sets the value of the aOverdueMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAOverdueMoney(String value) {
        this.aOverdueMoney = value;
    }

    /**
     * Gets the value of the aOverdueCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAOverdueCount() {
        return aOverdueCount;
    }

    /**
     * Sets the value of the aOverdueCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAOverdueCount(String value) {
        this.aOverdueCount = value;
    }

    /**
     * Gets the value of the overdueDayCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverdueDayCount() {
        return overdueDayCount;
    }

    /**
     * Sets the value of the overdueDayCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverdueDayCount(String value) {
        this.overdueDayCount = value;
    }

    /**
     * Gets the value of the applyDateList property.
     * 
     * @return
     *     possible object is
     *     {@link ApplyIdListDTO.ApplyDateList }
     *     
     */
    public ApplyIdListDTO.ApplyDateList getApplyDateList() {
        return applyDateList;
    }

    /**
     * Sets the value of the applyDateList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplyIdListDTO.ApplyDateList }
     *     
     */
    public void setApplyDateList(ApplyIdListDTO.ApplyDateList value) {
        this.applyDateList = value;
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
     *         &lt;element name="applyDateListDTO" type="{http://www.creditease.cn/RuralBusyService/}applyDateListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "applyDateListDTO"
    })
    public static class ApplyDateList {

        protected List<ApplyDateListDTO> applyDateListDTO;

        /**
         * Gets the value of the applyDateListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the applyDateListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApplyDateListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ApplyDateListDTO }
         * 
         * 
         */
        public List<ApplyDateListDTO> getApplyDateListDTO() {
            if (applyDateListDTO == null) {
                applyDateListDTO = new ArrayList<ApplyDateListDTO>();
            }
            return this.applyDateListDTO;
        }

    }

}

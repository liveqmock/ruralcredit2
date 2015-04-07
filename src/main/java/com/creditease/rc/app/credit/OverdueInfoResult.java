
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OverdueInfoResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverdueInfoResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn/RuralBusyService/}WSResult">
 *       &lt;sequence>
 *         &lt;element name="applyList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="applyListsDTO" type="{http://www.creditease.cn/RuralBusyService/}applyListsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverdueInfoResult", propOrder = {
    "applyList"
})
public class OverdueInfoResult
    extends WSResult
{

    @XmlElement(required = true)
    protected OverdueInfoResult.ApplyList applyList;

    /**
     * Gets the value of the applyList property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueInfoResult.ApplyList }
     *     
     */
    public OverdueInfoResult.ApplyList getApplyList() {
        return applyList;
    }

    /**
     * Sets the value of the applyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueInfoResult.ApplyList }
     *     
     */
    public void setApplyList(OverdueInfoResult.ApplyList value) {
        this.applyList = value;
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
     *         &lt;element name="applyListsDTO" type="{http://www.creditease.cn/RuralBusyService/}applyListsDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "applyListsDTO"
    })
    public static class ApplyList {

        protected List<ApplyListsDTO> applyListsDTO;

        /**
         * Gets the value of the applyListsDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the applyListsDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApplyListsDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ApplyListsDTO }
         * 
         * 
         */
        public List<ApplyListsDTO> getApplyListsDTO() {
            if (applyListsDTO == null) {
                applyListsDTO = new ArrayList<ApplyListsDTO>();
            }
            return this.applyListsDTO;
        }

    }

}

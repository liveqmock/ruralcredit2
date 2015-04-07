
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModifySellerRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModifySellerRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="applyListDTO" type="{http://www.creditease.cn/RuralBusyService/}applyListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ModifySellerRequest", propOrder = {
    "applyList"
})
public class ModifySellerRequest {

    protected ModifySellerRequest.ApplyList applyList;

    /**
     * Gets the value of the applyList property.
     * 
     * @return
     *     possible object is
     *     {@link ModifySellerRequest.ApplyList }
     *     
     */
    public ModifySellerRequest.ApplyList getApplyList() {
        return applyList;
    }

    /**
     * Sets the value of the applyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifySellerRequest.ApplyList }
     *     
     */
    public void setApplyList(ModifySellerRequest.ApplyList value) {
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
     *         &lt;element name="applyListDTO" type="{http://www.creditease.cn/RuralBusyService/}applyListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "applyListDTO"
    })
    public static class ApplyList {

        protected List<ApplyListDTO> applyListDTO;

        /**
         * Gets the value of the applyListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the applyListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApplyListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ApplyListDTO }
         * 
         * 
         */
        public List<ApplyListDTO> getApplyListDTO() {
            if (applyListDTO == null) {
                applyListDTO = new ArrayList<ApplyListDTO>();
            }
            return this.applyListDTO;
        }

    }

}

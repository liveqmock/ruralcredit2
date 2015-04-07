
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReserveReturnRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReserveReturnRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="returnListDTO" type="{http://www.creditease.cn/RuralBusyService/}returnListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ReserveReturnRequest", propOrder = {
    "returnList"
})
public class ReserveReturnRequest {

    protected ReserveReturnRequest.ReturnList returnList;

    /**
     * Gets the value of the returnList property.
     * 
     * @return
     *     possible object is
     *     {@link ReserveReturnRequest.ReturnList }
     *     
     */
    public ReserveReturnRequest.ReturnList getReturnList() {
        return returnList;
    }

    /**
     * Sets the value of the returnList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReserveReturnRequest.ReturnList }
     *     
     */
    public void setReturnList(ReserveReturnRequest.ReturnList value) {
        this.returnList = value;
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
     *         &lt;element name="returnListDTO" type="{http://www.creditease.cn/RuralBusyService/}returnListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "returnListDTO"
    })
    public static class ReturnList {

        protected List<ReturnListDTO> returnListDTO;

        /**
         * Gets the value of the returnListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the returnListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReturnListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReturnListDTO }
         * 
         * 
         */
        public List<ReturnListDTO> getReturnListDTO() {
            if (returnListDTO == null) {
                returnListDTO = new ArrayList<ReturnListDTO>();
            }
            return this.returnListDTO;
        }

    }

}

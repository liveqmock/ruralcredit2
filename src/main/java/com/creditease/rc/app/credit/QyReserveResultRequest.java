
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QyReserveResultRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QyReserveResultRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reserveList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reserveIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}reserveIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "QyReserveResultRequest", propOrder = {
    "reserveList"
})
public class QyReserveResultRequest {

    protected QyReserveResultRequest.ReserveList reserveList;

    /**
     * Gets the value of the reserveList property.
     * 
     * @return
     *     possible object is
     *     {@link QyReserveResultRequest.ReserveList }
     *     
     */
    public QyReserveResultRequest.ReserveList getReserveList() {
        return reserveList;
    }

    /**
     * Sets the value of the reserveList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QyReserveResultRequest.ReserveList }
     *     
     */
    public void setReserveList(QyReserveResultRequest.ReserveList value) {
        this.reserveList = value;
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
     *         &lt;element name="reserveIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}reserveIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "reserveIdListDTO"
    })
    public static class ReserveList {

        protected List<ReserveIdListDTO> reserveIdListDTO;

        /**
         * Gets the value of the reserveIdListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reserveIdListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReserveIdListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReserveIdListDTO }
         * 
         * 
         */
        public List<ReserveIdListDTO> getReserveIdListDTO() {
            if (reserveIdListDTO == null) {
                reserveIdListDTO = new ArrayList<ReserveIdListDTO>();
            }
            return this.reserveIdListDTO;
        }

    }

}

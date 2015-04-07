
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QyReserveResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QyReserveResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn/RuralBusyService/}WSResult">
 *       &lt;sequence>
 *         &lt;element name="reserveList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reserveListDTO" type="{http://www.creditease.cn/RuralBusyService/}reserveListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "QyReserveResult", propOrder = {
    "reserveList"
})
public class QyReserveResult
    extends WSResult
{

    @XmlElement(required = true)
    protected QyReserveResult.ReserveList reserveList;

    /**
     * Gets the value of the reserveList property.
     * 
     * @return
     *     possible object is
     *     {@link QyReserveResult.ReserveList }
     *     
     */
    public QyReserveResult.ReserveList getReserveList() {
        return reserveList;
    }

    /**
     * Sets the value of the reserveList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QyReserveResult.ReserveList }
     *     
     */
    public void setReserveList(QyReserveResult.ReserveList value) {
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
     *         &lt;element name="reserveListDTO" type="{http://www.creditease.cn/RuralBusyService/}reserveListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "reserveListDTO"
    })
    public static class ReserveList {

        protected List<ReserveListDTO> reserveListDTO;

        /**
         * Gets the value of the reserveListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reserveListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReserveListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReserveListDTO }
         * 
         * 
         */
        public List<ReserveListDTO> getReserveListDTO() {
            if (reserveListDTO == null) {
                reserveListDTO = new ArrayList<ReserveListDTO>();
            }
            return this.reserveListDTO;
        }

    }

}

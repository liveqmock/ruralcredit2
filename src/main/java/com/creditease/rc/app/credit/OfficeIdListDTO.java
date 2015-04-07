
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for officeIdListDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="officeIdListDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellIdList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="sellIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}sellIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "officeIdListDTO", propOrder = {
    "officeId",
    "sellIdList"
})
public class OfficeIdListDTO {

    protected String officeId;
    @XmlElement(required = true)
    protected OfficeIdListDTO.SellIdList sellIdList;

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
     * Gets the value of the sellIdList property.
     * 
     * @return
     *     possible object is
     *     {@link OfficeIdListDTO.SellIdList }
     *     
     */
    public OfficeIdListDTO.SellIdList getSellIdList() {
        return sellIdList;
    }

    /**
     * Sets the value of the sellIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfficeIdListDTO.SellIdList }
     *     
     */
    public void setSellIdList(OfficeIdListDTO.SellIdList value) {
        this.sellIdList = value;
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
     *         &lt;element name="sellIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}sellIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "sellIdListDTO"
    })
    public static class SellIdList {

        protected List<SellIdListDTO> sellIdListDTO;

        /**
         * Gets the value of the sellIdListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sellIdListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSellIdListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SellIdListDTO }
         * 
         * 
         */
        public List<SellIdListDTO> getSellIdListDTO() {
            if (sellIdListDTO == null) {
                sellIdListDTO = new ArrayList<SellIdListDTO>();
            }
            return this.sellIdListDTO;
        }

    }

}

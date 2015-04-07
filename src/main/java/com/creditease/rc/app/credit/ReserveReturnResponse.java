
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReserveReturnResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReserveReturnResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnIdList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="returnIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}returnIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ReserveReturnResponse", propOrder = {
    "retCode",
    "retInfo",
    "returnIdList"
})
public class ReserveReturnResponse {

    protected String retCode;
    protected String retInfo;
    protected ReserveReturnResponse.ReturnIdList returnIdList;

    /**
     * Gets the value of the retCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetCode() {
        return retCode;
    }

    /**
     * Sets the value of the retCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetCode(String value) {
        this.retCode = value;
    }

    /**
     * Gets the value of the retInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetInfo() {
        return retInfo;
    }

    /**
     * Sets the value of the retInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetInfo(String value) {
        this.retInfo = value;
    }

    /**
     * Gets the value of the returnIdList property.
     * 
     * @return
     *     possible object is
     *     {@link ReserveReturnResponse.ReturnIdList }
     *     
     */
    public ReserveReturnResponse.ReturnIdList getReturnIdList() {
        return returnIdList;
    }

    /**
     * Sets the value of the returnIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReserveReturnResponse.ReturnIdList }
     *     
     */
    public void setReturnIdList(ReserveReturnResponse.ReturnIdList value) {
        this.returnIdList = value;
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
     *         &lt;element name="returnIdListDTO" type="{http://www.creditease.cn/RuralBusyService/}returnIdListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "returnIdListDTO"
    })
    public static class ReturnIdList {

        protected List<ReturnIdListDTO> returnIdListDTO;

        /**
         * Gets the value of the returnIdListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the returnIdListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReturnIdListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReturnIdListDTO }
         * 
         * 
         */
        public List<ReturnIdListDTO> getReturnIdListDTO() {
            if (returnIdListDTO == null) {
                returnIdListDTO = new ArrayList<ReturnIdListDTO>();
            }
            return this.returnIdListDTO;
        }

    }

}

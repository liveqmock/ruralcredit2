
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QyClientApplyResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QyClientApplyResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qyapplyList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qyapplyListDTO" type="{http://www.creditease.cn/RuralBusyService/}qyapplyListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="retCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QyClientApplyResponse", propOrder = {
    "qyapplyList",
    "retCode",
    "retInfo"
})
public class QyClientApplyResponse {

    protected QyClientApplyResponse.QyapplyList qyapplyList;
    protected String retCode;
    protected String retInfo;

    /**
     * Gets the value of the qyapplyList property.
     * 
     * @return
     *     possible object is
     *     {@link QyClientApplyResponse.QyapplyList }
     *     
     */
    public QyClientApplyResponse.QyapplyList getQyapplyList() {
        return qyapplyList;
    }

    /**
     * Sets the value of the qyapplyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QyClientApplyResponse.QyapplyList }
     *     
     */
    public void setQyapplyList(QyClientApplyResponse.QyapplyList value) {
        this.qyapplyList = value;
    }

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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="qyapplyListDTO" type="{http://www.creditease.cn/RuralBusyService/}qyapplyListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "qyapplyListDTO"
    })
    public static class QyapplyList {

        protected List<QyapplyListDTO> qyapplyListDTO;

        /**
         * Gets the value of the qyapplyListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the qyapplyListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getQyapplyListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link QyapplyListDTO }
         * 
         * 
         */
        public List<QyapplyListDTO> getQyapplyListDTO() {
            if (qyapplyListDTO == null) {
                qyapplyListDTO = new ArrayList<QyapplyListDTO>();
            }
            return this.qyapplyListDTO;
        }

    }

}

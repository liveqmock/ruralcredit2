
package com.creditease.rc.app.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for overdueChargeReqResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overdueChargeReqResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calcDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ocdList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="overdueChargeDetail" type="{http://www.creditease.cn}overdueChargeDetail" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overdueChargeReqResult", propOrder = {
    "calcDate",
    "ocdList",
    "resultCode",
    "resultMessage"
})
public class OverdueChargeReqResult {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcDate;
    protected OverdueChargeReqResult.OcdList ocdList;
    @XmlElement(required = true)
    protected String resultCode;
    @XmlElement(required = true)
    protected String resultMessage;

    /**
     * Gets the value of the calcDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcDate() {
        return calcDate;
    }

    /**
     * Sets the value of the calcDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcDate(XMLGregorianCalendar value) {
        this.calcDate = value;
    }

    /**
     * Gets the value of the ocdList property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueChargeReqResult.OcdList }
     *     
     */
    public OverdueChargeReqResult.OcdList getOcdList() {
        return ocdList;
    }

    /**
     * Sets the value of the ocdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueChargeReqResult.OcdList }
     *     
     */
    public void setOcdList(OverdueChargeReqResult.OcdList value) {
        this.ocdList = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the value of the resultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMessage(String value) {
        this.resultMessage = value;
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
     *         &lt;element name="overdueChargeDetail" type="{http://www.creditease.cn}overdueChargeDetail" maxOccurs="unbounded" minOccurs="0"/>
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
        "overdueChargeDetail"
    })
    public static class OcdList {

        protected List<OverdueChargeDetail> overdueChargeDetail;

        /**
         * Gets the value of the overdueChargeDetail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the overdueChargeDetail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOverdueChargeDetail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OverdueChargeDetail }
         * 
         * 
         */
        public List<OverdueChargeDetail> getOverdueChargeDetail() {
            if (overdueChargeDetail == null) {
                overdueChargeDetail = new ArrayList<OverdueChargeDetail>();
            }
            return this.overdueChargeDetail;
        }

    }

}


package com.creditease.rc.app.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for overdueChargeDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overdueChargeDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iciList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="increasedChargeInfo" type="{http://www.creditease.cn}increasedChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overdueChargeDetail", propOrder = {
    "iciList",
    "period"
})
public class OverdueChargeDetail {

    protected OverdueChargeDetail.IciList iciList;
    protected int period;

    /**
     * Gets the value of the iciList property.
     * 
     * @return
     *     possible object is
     *     {@link OverdueChargeDetail.IciList }
     *     
     */
    public OverdueChargeDetail.IciList getIciList() {
        return iciList;
    }

    /**
     * Sets the value of the iciList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdueChargeDetail.IciList }
     *     
     */
    public void setIciList(OverdueChargeDetail.IciList value) {
        this.iciList = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(int value) {
        this.period = value;
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
     *         &lt;element name="increasedChargeInfo" type="{http://www.creditease.cn}increasedChargeInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "increasedChargeInfo"
    })
    public static class IciList {

        protected List<IncreasedChargeInfo> increasedChargeInfo;

        /**
         * Gets the value of the increasedChargeInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the increasedChargeInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIncreasedChargeInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IncreasedChargeInfo }
         * 
         * 
         */
        public List<IncreasedChargeInfo> getIncreasedChargeInfo() {
            if (increasedChargeInfo == null) {
                increasedChargeInfo = new ArrayList<IncreasedChargeInfo>();
            }
            return this.increasedChargeInfo;
        }

    }

}

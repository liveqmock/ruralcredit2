
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contractObj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractObj">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dOverhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dCharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fOverhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fCharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfCorpus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfInterest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfOverhead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfCharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qyOverdueList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qyOverdueListObj" type="{http://www.creditease.cn/RuralBusyService/}qyOverdueListObj" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "contractObj", propOrder = {
    "applyId",
    "dCorpus",
    "dInterest",
    "dOverhead",
    "dCharges",
    "fCorpus",
    "fInterest",
    "fOverhead",
    "fCharges",
    "dfCorpus",
    "dfInterest",
    "dfOverhead",
    "dfCharges",
    "qyOverdueList"
})
public class ContractObj {

    protected String applyId;
    protected String dCorpus;
    protected String dInterest;
    protected String dOverhead;
    protected String dCharges;
    protected String fCorpus;
    protected String fInterest;
    protected String fOverhead;
    protected String fCharges;
    protected String dfCorpus;
    protected String dfInterest;
    protected String dfOverhead;
    protected String dfCharges;
    protected ContractObj.QyOverdueList qyOverdueList;

    /**
     * Gets the value of the applyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * Sets the value of the applyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyId(String value) {
        this.applyId = value;
    }

    /**
     * Gets the value of the dCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDCorpus() {
        return dCorpus;
    }

    /**
     * Sets the value of the dCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDCorpus(String value) {
        this.dCorpus = value;
    }

    /**
     * Gets the value of the dInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDInterest() {
        return dInterest;
    }

    /**
     * Sets the value of the dInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDInterest(String value) {
        this.dInterest = value;
    }

    /**
     * Gets the value of the dOverhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOverhead() {
        return dOverhead;
    }

    /**
     * Sets the value of the dOverhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOverhead(String value) {
        this.dOverhead = value;
    }

    /**
     * Gets the value of the dCharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDCharges() {
        return dCharges;
    }

    /**
     * Sets the value of the dCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDCharges(String value) {
        this.dCharges = value;
    }

    /**
     * Gets the value of the fCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFCorpus() {
        return fCorpus;
    }

    /**
     * Sets the value of the fCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFCorpus(String value) {
        this.fCorpus = value;
    }

    /**
     * Gets the value of the fInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFInterest() {
        return fInterest;
    }

    /**
     * Sets the value of the fInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFInterest(String value) {
        this.fInterest = value;
    }

    /**
     * Gets the value of the fOverhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFOverhead() {
        return fOverhead;
    }

    /**
     * Sets the value of the fOverhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFOverhead(String value) {
        this.fOverhead = value;
    }

    /**
     * Gets the value of the fCharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFCharges() {
        return fCharges;
    }

    /**
     * Sets the value of the fCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFCharges(String value) {
        this.fCharges = value;
    }

    /**
     * Gets the value of the dfCorpus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDfCorpus() {
        return dfCorpus;
    }

    /**
     * Sets the value of the dfCorpus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDfCorpus(String value) {
        this.dfCorpus = value;
    }

    /**
     * Gets the value of the dfInterest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDfInterest() {
        return dfInterest;
    }

    /**
     * Sets the value of the dfInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDfInterest(String value) {
        this.dfInterest = value;
    }

    /**
     * Gets the value of the dfOverhead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDfOverhead() {
        return dfOverhead;
    }

    /**
     * Sets the value of the dfOverhead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDfOverhead(String value) {
        this.dfOverhead = value;
    }

    /**
     * Gets the value of the dfCharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDfCharges() {
        return dfCharges;
    }

    /**
     * Sets the value of the dfCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDfCharges(String value) {
        this.dfCharges = value;
    }

    /**
     * Gets the value of the qyOverdueList property.
     * 
     * @return
     *     possible object is
     *     {@link ContractObj.QyOverdueList }
     *     
     */
    public ContractObj.QyOverdueList getQyOverdueList() {
        return qyOverdueList;
    }

    /**
     * Sets the value of the qyOverdueList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractObj.QyOverdueList }
     *     
     */
    public void setQyOverdueList(ContractObj.QyOverdueList value) {
        this.qyOverdueList = value;
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
     *         &lt;element name="qyOverdueListObj" type="{http://www.creditease.cn/RuralBusyService/}qyOverdueListObj" maxOccurs="unbounded" minOccurs="0"/>
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
        "qyOverdueListObj"
    })
    public static class QyOverdueList {

        protected List<QyOverdueListObj> qyOverdueListObj;

        /**
         * Gets the value of the qyOverdueListObj property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the qyOverdueListObj property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getQyOverdueListObj().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link QyOverdueListObj }
         * 
         * 
         */
        public List<QyOverdueListObj> getQyOverdueListObj() {
            if (qyOverdueListObj == null) {
                qyOverdueListObj = new ArrayList<QyOverdueListObj>();
            }
            return this.qyOverdueListObj;
        }

    }

}

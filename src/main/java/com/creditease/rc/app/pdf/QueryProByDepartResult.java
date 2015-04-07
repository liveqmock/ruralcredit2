
package com.creditease.rc.app.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryProByDepartResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryProByDepartResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proInfoList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="proInfo" type="{http://www.creditease.cn}proInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="proInstList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="productInst" type="{http://www.creditease.cn}productInst" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="proParamList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="productParam" type="{http://www.creditease.cn}productParam" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="proPayTypeParamList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="proPayTypeParam" type="{http://www.creditease.cn}proPayTypeParam" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "queryProByDepartResult", propOrder = {
    "proInfoList",
    "proInstList",
    "proParamList",
    "proPayTypeParamList",
    "resultCode",
    "resultMessage"
})
public class QueryProByDepartResult {

    protected QueryProByDepartResult.ProInfoList proInfoList;
    protected QueryProByDepartResult.ProInstList proInstList;
    protected QueryProByDepartResult.ProParamList proParamList;
    protected QueryProByDepartResult.ProPayTypeParamList proPayTypeParamList;
    @XmlElement(required = true)
    protected String resultCode;
    @XmlElement(required = true)
    protected String resultMessage;

    /**
     * Gets the value of the proInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProByDepartResult.ProInfoList }
     *     
     */
    public QueryProByDepartResult.ProInfoList getProInfoList() {
        return proInfoList;
    }

    /**
     * Sets the value of the proInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProByDepartResult.ProInfoList }
     *     
     */
    public void setProInfoList(QueryProByDepartResult.ProInfoList value) {
        this.proInfoList = value;
    }

    /**
     * Gets the value of the proInstList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProByDepartResult.ProInstList }
     *     
     */
    public QueryProByDepartResult.ProInstList getProInstList() {
        return proInstList;
    }

    /**
     * Sets the value of the proInstList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProByDepartResult.ProInstList }
     *     
     */
    public void setProInstList(QueryProByDepartResult.ProInstList value) {
        this.proInstList = value;
    }

    /**
     * Gets the value of the proParamList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProByDepartResult.ProParamList }
     *     
     */
    public QueryProByDepartResult.ProParamList getProParamList() {
        return proParamList;
    }

    /**
     * Sets the value of the proParamList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProByDepartResult.ProParamList }
     *     
     */
    public void setProParamList(QueryProByDepartResult.ProParamList value) {
        this.proParamList = value;
    }

    /**
     * Gets the value of the proPayTypeParamList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProByDepartResult.ProPayTypeParamList }
     *     
     */
    public QueryProByDepartResult.ProPayTypeParamList getProPayTypeParamList() {
        return proPayTypeParamList;
    }

    /**
     * Sets the value of the proPayTypeParamList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProByDepartResult.ProPayTypeParamList }
     *     
     */
    public void setProPayTypeParamList(QueryProByDepartResult.ProPayTypeParamList value) {
        this.proPayTypeParamList = value;
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
     *         &lt;element name="proInfo" type="{http://www.creditease.cn}proInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "proInfo"
    })
    public static class ProInfoList {

        protected List<ProInfo> proInfo;

        /**
         * Gets the value of the proInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the proInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProInfo }
         * 
         * 
         */
        public List<ProInfo> getProInfo() {
            if (proInfo == null) {
                proInfo = new ArrayList<ProInfo>();
            }
            return this.proInfo;
        }

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
     *         &lt;element name="productInst" type="{http://www.creditease.cn}productInst" maxOccurs="unbounded" minOccurs="0"/>
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
        "productInst"
    })
    public static class ProInstList {

        protected List<ProductInst> productInst;

        /**
         * Gets the value of the productInst property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the productInst property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProductInst().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductInst }
         * 
         * 
         */
        public List<ProductInst> getProductInst() {
            if (productInst == null) {
                productInst = new ArrayList<ProductInst>();
            }
            return this.productInst;
        }

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
     *         &lt;element name="productParam" type="{http://www.creditease.cn}productParam" maxOccurs="unbounded" minOccurs="0"/>
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
        "productParam"
    })
    public static class ProParamList {

        protected List<ProductParam> productParam;

        /**
         * Gets the value of the productParam property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the productParam property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProductParam().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductParam }
         * 
         * 
         */
        public List<ProductParam> getProductParam() {
            if (productParam == null) {
                productParam = new ArrayList<ProductParam>();
            }
            return this.productParam;
        }

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
     *         &lt;element name="proPayTypeParam" type="{http://www.creditease.cn}proPayTypeParam" maxOccurs="unbounded" minOccurs="0"/>
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
        "proPayTypeParam"
    })
    public static class ProPayTypeParamList {

        protected List<ProPayTypeParam> proPayTypeParam;

        /**
         * Gets the value of the proPayTypeParam property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the proPayTypeParam property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProPayTypeParam().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProPayTypeParam }
         * 
         * 
         */
        public List<ProPayTypeParam> getProPayTypeParam() {
            if (proPayTypeParam == null) {
                proPayTypeParam = new ArrayList<ProPayTypeParam>();
            }
            return this.proPayTypeParam;
        }

    }

}

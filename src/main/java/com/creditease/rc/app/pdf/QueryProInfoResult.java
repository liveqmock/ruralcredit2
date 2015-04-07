
package com.creditease.rc.app.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryProInfoResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryProInfoResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proCategoryList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="proCategory" type="{http://www.creditease.cn}proCategory" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="proInfoList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="productInfo" type="{http://www.creditease.cn}productInfo" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "queryProInfoResult", propOrder = {
    "proCategoryList",
    "proInfoList",
    "proInstList",
    "proParamList",
    "proPayTypeParamList",
    "resultCode",
    "resultMessage"
})
public class QueryProInfoResult {

    protected QueryProInfoResult.ProCategoryList proCategoryList;
    protected QueryProInfoResult.ProInfoList proInfoList;
    protected QueryProInfoResult.ProInstList proInstList;
    protected QueryProInfoResult.ProParamList proParamList;
    protected QueryProInfoResult.ProPayTypeParamList proPayTypeParamList;
    @XmlElement(required = true)
    protected String resultCode;
    @XmlElement(required = true)
    protected String resultMessage;

    /**
     * Gets the value of the proCategoryList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProInfoResult.ProCategoryList }
     *     
     */
    public QueryProInfoResult.ProCategoryList getProCategoryList() {
        return proCategoryList;
    }

    /**
     * Sets the value of the proCategoryList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProInfoResult.ProCategoryList }
     *     
     */
    public void setProCategoryList(QueryProInfoResult.ProCategoryList value) {
        this.proCategoryList = value;
    }

    /**
     * Gets the value of the proInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProInfoResult.ProInfoList }
     *     
     */
    public QueryProInfoResult.ProInfoList getProInfoList() {
        return proInfoList;
    }

    /**
     * Sets the value of the proInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProInfoResult.ProInfoList }
     *     
     */
    public void setProInfoList(QueryProInfoResult.ProInfoList value) {
        this.proInfoList = value;
    }

    /**
     * Gets the value of the proInstList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProInfoResult.ProInstList }
     *     
     */
    public QueryProInfoResult.ProInstList getProInstList() {
        return proInstList;
    }

    /**
     * Sets the value of the proInstList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProInfoResult.ProInstList }
     *     
     */
    public void setProInstList(QueryProInfoResult.ProInstList value) {
        this.proInstList = value;
    }

    /**
     * Gets the value of the proParamList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProInfoResult.ProParamList }
     *     
     */
    public QueryProInfoResult.ProParamList getProParamList() {
        return proParamList;
    }

    /**
     * Sets the value of the proParamList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProInfoResult.ProParamList }
     *     
     */
    public void setProParamList(QueryProInfoResult.ProParamList value) {
        this.proParamList = value;
    }

    /**
     * Gets the value of the proPayTypeParamList property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProInfoResult.ProPayTypeParamList }
     *     
     */
    public QueryProInfoResult.ProPayTypeParamList getProPayTypeParamList() {
        return proPayTypeParamList;
    }

    /**
     * Sets the value of the proPayTypeParamList property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProInfoResult.ProPayTypeParamList }
     *     
     */
    public void setProPayTypeParamList(QueryProInfoResult.ProPayTypeParamList value) {
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
     *         &lt;element name="proCategory" type="{http://www.creditease.cn}proCategory" maxOccurs="unbounded" minOccurs="0"/>
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
        "proCategory"
    })
    public static class ProCategoryList {

        protected List<ProCategory> proCategory;

        /**
         * Gets the value of the proCategory property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the proCategory property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProCategory().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProCategory }
         * 
         * 
         */
        public List<ProCategory> getProCategory() {
            if (proCategory == null) {
                proCategory = new ArrayList<ProCategory>();
            }
            return this.proCategory;
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
     *         &lt;element name="productInfo" type="{http://www.creditease.cn}productInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "productInfo"
    })
    public static class ProInfoList {

        protected List<ProductInfo> productInfo;

        /**
         * Gets the value of the productInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the productInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProductInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductInfo }
         * 
         * 
         */
        public List<ProductInfo> getProductInfo() {
            if (productInfo == null) {
                productInfo = new ArrayList<ProductInfo>();
            }
            return this.productInfo;
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

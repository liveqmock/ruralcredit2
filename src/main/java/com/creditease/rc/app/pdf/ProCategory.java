
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryLevel" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isEndNode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proCategory", propOrder = {
    "categoryCode",
    "categoryLevel",
    "categoryName",
    "isEndNode",
    "parentCategoryCode",
    "productCategoryId"
})
public class ProCategory {

    protected String categoryCode;
    protected Short categoryLevel;
    protected String categoryName;
    protected String isEndNode;
    protected String parentCategoryCode;
    protected Long productCategoryId;

    /**
     * Gets the value of the categoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets the value of the categoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * Gets the value of the categoryLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * Sets the value of the categoryLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCategoryLevel(Short value) {
        this.categoryLevel = value;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the isEndNode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEndNode() {
        return isEndNode;
    }

    /**
     * Sets the value of the isEndNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEndNode(String value) {
        this.isEndNode = value;
    }

    /**
     * Gets the value of the parentCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    /**
     * Sets the value of the parentCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentCategoryCode(String value) {
        this.parentCategoryCode = value;
    }

    /**
     * Gets the value of the productCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Sets the value of the productCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductCategoryId(Long value) {
        this.productCategoryId = value;
    }

}

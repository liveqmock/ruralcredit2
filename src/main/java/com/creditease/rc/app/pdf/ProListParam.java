
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proListParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proListParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="queryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proListParam", propOrder = {
    "departmentId",
    "queryDate"
})
public class ProListParam {

    protected long departmentId;
    protected String queryDate;

    /**
     * Gets the value of the departmentId property.
     * 
     */
    public long getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     */
    public void setDepartmentId(long value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the queryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryDate() {
        return queryDate;
    }

    /**
     * Sets the value of the queryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryDate(String value) {
        this.queryDate = value;
    }

}

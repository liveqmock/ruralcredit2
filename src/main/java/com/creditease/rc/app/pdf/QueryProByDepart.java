
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryProByDepart complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryProByDepart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proListParam" type="{http://www.creditease.cn}proListParam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryProByDepart", propOrder = {
    "proListParam"
})
public class QueryProByDepart {

    protected ProListParam proListParam;

    /**
     * Gets the value of the proListParam property.
     * 
     * @return
     *     possible object is
     *     {@link ProListParam }
     *     
     */
    public ProListParam getProListParam() {
        return proListParam;
    }

    /**
     * Sets the value of the proListParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProListParam }
     *     
     */
    public void setProListParam(ProListParam value) {
        this.proListParam = value;
    }

}

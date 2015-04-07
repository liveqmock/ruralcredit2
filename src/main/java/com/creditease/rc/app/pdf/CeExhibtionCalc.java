
package com.creditease.rc.app.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ceExhibtionCalc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ceExhibtionCalc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exhibtionCalcReq" type="{http://www.creditease.cn}exhibtionCalcReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ceExhibtionCalc", propOrder = {
    "exhibtionCalcReq"
})
public class CeExhibtionCalc {

    protected ExhibtionCalcReq exhibtionCalcReq;

    /**
     * Gets the value of the exhibtionCalcReq property.
     * 
     * @return
     *     possible object is
     *     {@link ExhibtionCalcReq }
     *     
     */
    public ExhibtionCalcReq getExhibtionCalcReq() {
        return exhibtionCalcReq;
    }

    /**
     * Sets the value of the exhibtionCalcReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExhibtionCalcReq }
     *     
     */
    public void setExhibtionCalcReq(ExhibtionCalcReq value) {
        this.exhibtionCalcReq = value;
    }

}

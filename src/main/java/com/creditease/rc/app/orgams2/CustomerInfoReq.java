
package com.creditease.rc.app.orgams2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerInfoReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerInfoReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.icp.ws.component.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="creditCustomerInfoVO" type="{http://service.icp.ws.component.creditease.com/}creditCustomerInfoVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerInfoReq", propOrder = {
    "creditCustomerInfoVO"
})
public class CustomerInfoReq
    extends BaseRequestHead
{

    protected CreditCustomerInfoVO creditCustomerInfoVO;

    /**
     * Gets the value of the creditCustomerInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCustomerInfoVO }
     *     
     */
    public CreditCustomerInfoVO getCreditCustomerInfoVO() {
        return creditCustomerInfoVO;
    }

    /**
     * Sets the value of the creditCustomerInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCustomerInfoVO }
     *     
     */
    public void setCreditCustomerInfoVO(CreditCustomerInfoVO value) {
        this.creditCustomerInfoVO = value;
    }

}

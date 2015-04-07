
package com.creditease.rc.app.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for p2PContractReqParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="p2PContractReqParam">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn}baseContractReqParam">
 *       &lt;sequence>
 *         &lt;element name="authContractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessLogicType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consContractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entContractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lenderList" type="{http://www.creditease.cn}p2PLender" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="repayContractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "p2PContractReqParam", propOrder = {
    "authContractNo",
    "businessLogicType",
    "consContractNo",
    "entContractNo",
    "lenderList",
    "repayContractNo"
})
public class P2PContractReqParam
    extends BaseContractReqParam
{

    protected String authContractNo;
    protected String businessLogicType;
    protected String consContractNo;
    protected String entContractNo;
    protected List<P2PLender> lenderList;
    protected String repayContractNo;

    /**
     * Gets the value of the authContractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthContractNo() {
        return authContractNo;
    }

    /**
     * Sets the value of the authContractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthContractNo(String value) {
        this.authContractNo = value;
    }

    /**
     * Gets the value of the businessLogicType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessLogicType() {
        return businessLogicType;
    }

    /**
     * Sets the value of the businessLogicType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessLogicType(String value) {
        this.businessLogicType = value;
    }

    /**
     * Gets the value of the consContractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsContractNo() {
        return consContractNo;
    }

    /**
     * Sets the value of the consContractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsContractNo(String value) {
        this.consContractNo = value;
    }

    /**
     * Gets the value of the entContractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntContractNo() {
        return entContractNo;
    }

    /**
     * Sets the value of the entContractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntContractNo(String value) {
        this.entContractNo = value;
    }

    /**
     * Gets the value of the lenderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lenderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLenderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link P2PLender }
     * 
     * 
     */
    public List<P2PLender> getLenderList() {
        if (lenderList == null) {
            lenderList = new ArrayList<P2PLender>();
        }
        return this.lenderList;
    }

    /**
     * Gets the value of the repayContractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepayContractNo() {
        return repayContractNo;
    }

    /**
     * Sets the value of the repayContractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepayContractNo(String value) {
        this.repayContractNo = value;
    }

}

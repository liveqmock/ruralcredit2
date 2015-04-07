
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankListResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankListResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn/RuralBusyService/}WSResult">
 *       &lt;sequence>
 *         &lt;element name="bankList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="bankListDTO" type="{http://www.creditease.cn/RuralBusyService/}bankListDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankListResult", propOrder = {
    "bankList"
})
public class BankListResult
    extends WSResult
{

    @XmlElement(required = true)
    protected BankListResult.BankList bankList;

    /**
     * Gets the value of the bankList property.
     * 
     * @return
     *     possible object is
     *     {@link BankListResult.BankList }
     *     
     */
    public BankListResult.BankList getBankList() {
        return bankList;
    }

    /**
     * Sets the value of the bankList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankListResult.BankList }
     *     
     */
    public void setBankList(BankListResult.BankList value) {
        this.bankList = value;
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
     *         &lt;element name="bankListDTO" type="{http://www.creditease.cn/RuralBusyService/}bankListDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "bankListDTO"
    })
    public static class BankList {

        protected List<BankListDTO> bankListDTO;

        /**
         * Gets the value of the bankListDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bankListDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBankListDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BankListDTO }
         * 
         * 
         */
        public List<BankListDTO> getBankListDTO() {
            if (bankListDTO == null) {
                bankListDTO = new ArrayList<BankListDTO>();
            }
            return this.bankListDTO;
        }

    }

}


package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoanBalanceDataResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoanBalanceDataResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn/RuralBusyService/}WSResult">
 *       &lt;sequence>
 *         &lt;element name="contractList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contractObj" type="{http://www.creditease.cn/RuralBusyService/}contractObj" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "LoanBalanceDataResult", propOrder = {
    "contractList"
})
public class LoanBalanceDataResult
    extends WSResult
{

    @XmlElement(required = true)
    protected LoanBalanceDataResult.ContractList contractList;

    /**
     * Gets the value of the contractList property.
     * 
     * @return
     *     possible object is
     *     {@link LoanBalanceDataResult.ContractList }
     *     
     */
    public LoanBalanceDataResult.ContractList getContractList() {
        return contractList;
    }

    /**
     * Sets the value of the contractList property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoanBalanceDataResult.ContractList }
     *     
     */
    public void setContractList(LoanBalanceDataResult.ContractList value) {
        this.contractList = value;
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
     *         &lt;element name="contractObj" type="{http://www.creditease.cn/RuralBusyService/}contractObj" maxOccurs="unbounded" minOccurs="0"/>
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
        "contractObj"
    })
    public static class ContractList {

        protected List<ContractObj> contractObj;

        /**
         * Gets the value of the contractObj property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contractObj property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContractObj().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContractObj }
         * 
         * 
         */
        public List<ContractObj> getContractObj() {
            if (contractObj == null) {
                contractObj = new ArrayList<ContractObj>();
            }
            return this.contractObj;
        }

    }

}

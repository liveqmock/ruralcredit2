
package com.creditease.rc.app.orgams2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditCustomerInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditCustomerInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bsTransport" type="{http://service.icp.ws.component.creditease.com/}bsTransport" minOccurs="0"/>
 *         &lt;element name="intoPieces" type="{http://service.icp.ws.component.creditease.com/}intoPieces" minOccurs="0"/>
 *         &lt;element name="mortgageRepaymentList" type="{http://service.icp.ws.component.creditease.com/}mortgageRepayment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorVOList" type="{http://service.icp.ws.component.creditease.com/}mortgagorVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditCustomerInfoVO", propOrder = {
    "bsTransport",
    "intoPieces",
    "mortgageRepaymentList",
    "mortgagorVOList"
})
public class CreditCustomerInfoVO {

    protected BsTransport bsTransport;
    protected IntoPieces intoPieces;
    @XmlElement(nillable = true)
    protected List<MortgageRepayment> mortgageRepaymentList;
    @XmlElement(nillable = true)
    protected List<MortgagorVO> mortgagorVOList;

    /**
     * Gets the value of the bsTransport property.
     * 
     * @return
     *     possible object is
     *     {@link BsTransport }
     *     
     */
    public BsTransport getBsTransport() {
        return bsTransport;
    }

    /**
     * Sets the value of the bsTransport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BsTransport }
     *     
     */
    public void setBsTransport(BsTransport value) {
        this.bsTransport = value;
    }

    /**
     * Gets the value of the intoPieces property.
     * 
     * @return
     *     possible object is
     *     {@link IntoPieces }
     *     
     */
    public IntoPieces getIntoPieces() {
        return intoPieces;
    }

    /**
     * Sets the value of the intoPieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntoPieces }
     *     
     */
    public void setIntoPieces(IntoPieces value) {
        this.intoPieces = value;
    }

    /**
     * Gets the value of the mortgageRepaymentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mortgageRepaymentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMortgageRepaymentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MortgageRepayment }
     * 
     * 
     */
    public List<MortgageRepayment> getMortgageRepaymentList() {
        if (mortgageRepaymentList == null) {
            mortgageRepaymentList = new ArrayList<MortgageRepayment>();
        }
        return this.mortgageRepaymentList;
    }

    /**
     * Gets the value of the mortgagorVOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mortgagorVOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMortgagorVOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MortgagorVO }
     * 
     * 
     */
    public List<MortgagorVO> getMortgagorVOList() {
        if (mortgagorVOList == null) {
            mortgagorVOList = new ArrayList<MortgagorVO>();
        }
        return this.mortgagorVOList;
    }

}

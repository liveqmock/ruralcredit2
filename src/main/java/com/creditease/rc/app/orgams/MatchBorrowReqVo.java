
package com.creditease.rc.app.orgams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for matchBorrowReqVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchBorrowReqVo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tradeplatform.ws.component.creditease.com/}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="extParam" type="{http://service.tradeplatform.ws.component.creditease.com/}MapConvertor" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="applyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cashflowList" type="{http://service.tradeplatform.ws.component.creditease.com/}cashFlowVo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="expireTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="financerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="irr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="signDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchBorrowReqVo", propOrder = {
    "extParam",
    "amount",
    "applyId",
    "cashflowList",
    "expireTime",
    "financerId",
    "irr",
    "period",
    "priority",
    "productType",
    "signDate"
})
public class MatchBorrowReqVo
    extends BaseRequest
{

    protected MapConvertor extParam;
    protected BigDecimal amount;
    protected String applyId;
    @XmlElement(nillable = true)
    protected List<CashFlowVo> cashflowList;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expireTime;
    protected String financerId;
    protected BigDecimal irr;
    protected Long period;
    protected Long priority;
    protected Long productType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signDate;

    /**
     * Gets the value of the extParam property.
     * 
     * @return
     *     possible object is
     *     {@link MapConvertor }
     *     
     */
    public MapConvertor getExtParam() {
        return extParam;
    }

    /**
     * Sets the value of the extParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapConvertor }
     *     
     */
    public void setExtParam(MapConvertor value) {
        this.extParam = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the applyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * Sets the value of the applyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyId(String value) {
        this.applyId = value;
    }

    /**
     * Gets the value of the cashflowList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cashflowList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCashflowList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CashFlowVo }
     * 
     * 
     */
    public List<CashFlowVo> getCashflowList() {
        if (cashflowList == null) {
            cashflowList = new ArrayList<CashFlowVo>();
        }
        return this.cashflowList;
    }

    /**
     * Gets the value of the expireTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpireTime() {
        return expireTime;
    }

    /**
     * Sets the value of the expireTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpireTime(XMLGregorianCalendar value) {
        this.expireTime = value;
    }

    /**
     * Gets the value of the financerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinancerId() {
        return financerId;
    }

    /**
     * Sets the value of the financerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinancerId(String value) {
        this.financerId = value;
    }

    /**
     * Gets the value of the irr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrr() {
        return irr;
    }

    /**
     * Sets the value of the irr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrr(BigDecimal value) {
        this.irr = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPeriod(Long value) {
        this.period = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPriority(Long value) {
        this.priority = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductType(Long value) {
        this.productType = value;
    }

    /**
     * Gets the value of the signDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSignDate() {
        return signDate;
    }

    /**
     * Sets the value of the signDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSignDate(XMLGregorianCalendar value) {
        this.signDate = value;
    }

}

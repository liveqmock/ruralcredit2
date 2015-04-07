
package com.creditease.rc.app.orgams;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for matchBorrowResVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchBorrowResVo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tradeplatform.ws.component.creditease.com/}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="submitTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tradeDealList" type="{http://service.tradeplatform.ws.component.creditease.com/}tradeDealVo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchBorrowResVo", propOrder = {
    "submitTime",
    "tradeDealList"
})
public class MatchBorrowResVo
    extends BaseResponse
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submitTime;
    @XmlElement(nillable = true)
    protected List<TradeDealVo> tradeDealList;

    /**
     * Gets the value of the submitTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmitTime() {
        return submitTime;
    }

    /**
     * Sets the value of the submitTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmitTime(XMLGregorianCalendar value) {
        this.submitTime = value;
    }

    /**
     * Gets the value of the tradeDealList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tradeDealList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTradeDealList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeDealVo }
     * 
     * 
     */
    public List<TradeDealVo> getTradeDealList() {
        if (tradeDealList == null) {
            tradeDealList = new ArrayList<TradeDealVo>();
        }
        return this.tradeDealList;
    }

}

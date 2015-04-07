
package com.creditease.rc.app.orgams2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productSeriesReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productSeriesReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.icp.ws.component.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="productSeriesList" type="{http://service.icp.ws.component.creditease.com/}productSeries" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productSeriesReq", propOrder = {
    "productSeriesList"
})
public class ProductSeriesReq
    extends BaseRequestHead
{

    @XmlElement(nillable = true)
    protected List<ProductSeries> productSeriesList;

    /**
     * Gets the value of the productSeriesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productSeriesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductSeriesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductSeries }
     * 
     * 
     */
    public List<ProductSeries> getProductSeriesList() {
        if (productSeriesList == null) {
            productSeriesList = new ArrayList<ProductSeries>();
        }
        return this.productSeriesList;
    }

}

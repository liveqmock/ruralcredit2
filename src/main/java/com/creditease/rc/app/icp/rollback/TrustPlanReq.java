package com.creditease.rc.app.icp.rollback;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for trustPlanReq complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="trustPlanReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}baseRequestHead">
 *       &lt;sequence>
 *         &lt;element name="trustPlanList" type="{http://service.server.ws.icp.creditease.com/}trustPlan" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trustPlanReq", propOrder = { "trustPlanList" })
public class TrustPlanReq extends BaseRequestHead {

	@XmlElement(nillable = true)
	protected List<TrustPlan> trustPlanList;

	/**
	 * Gets the value of the trustPlanList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the trustPlanList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTrustPlanList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TrustPlan }
	 * 
	 * 
	 */
	public List<TrustPlan> getTrustPlanList() {
		if (trustPlanList == null) {
			trustPlanList = new ArrayList<TrustPlan>();
		}
		return this.trustPlanList;
	}

}

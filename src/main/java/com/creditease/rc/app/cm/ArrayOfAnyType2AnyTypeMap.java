package com.creditease.rc.app.cm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfAnyType2anyTypeMap complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAnyType2anyTypeMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anyType2anyTypeMap" type="{http://service.creditease}anyType2anyTypeMap" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAnyType2anyTypeMap", propOrder = { "anyType2AnyTypeMap" })
public class ArrayOfAnyType2AnyTypeMap {

	@XmlElement(name = "anyType2anyTypeMap", nillable = true)
	protected List<AnyType2AnyTypeMap> anyType2AnyTypeMap;

	/**
	 * Gets the value of the anyType2AnyTypeMap property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the anyType2AnyTypeMap property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAnyType2AnyTypeMap().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AnyType2AnyTypeMap }
	 * 
	 * 
	 */
	public List<AnyType2AnyTypeMap> getAnyType2AnyTypeMap() {
		if (anyType2AnyTypeMap == null) {
			anyType2AnyTypeMap = new ArrayList<AnyType2AnyTypeMap>();
		}
		return this.anyType2AnyTypeMap;
	}

}

package com.creditease.rc.app.cm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="in0" type="{http://service.creditease}ArrayOfAnyType2anyTypeMap"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "in0" })
@XmlRootElement(name = "achieveSellManagerIntegrityPath")
public class AchieveSellManagerIntegrityPath {

	@XmlElement(required = true, nillable = true)
	protected ArrayOfAnyType2AnyTypeMap in0;

	/**
	 * Gets the value of the in0 property.
	 * 
	 * @return possible object is {@link ArrayOfAnyType2AnyTypeMap }
	 * 
	 */
	public ArrayOfAnyType2AnyTypeMap getIn0() {
		return in0;
	}

	/**
	 * Sets the value of the in0 property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfAnyType2AnyTypeMap }
	 * 
	 */
	public void setIn0(ArrayOfAnyType2AnyTypeMap value) {
		this.in0 = value;
	}

}

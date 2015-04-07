
package com.creditease.rc.app.credit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnAmountResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnAmountResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.creditease.cn/RuralBusyService/}WSResult">
 *       &lt;sequence>
 *         &lt;element name="appointSchedule" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appointScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="allAheadSchedule" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="allAheadScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}AllAheadScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ReturnAmountResult", propOrder = {
    "appointSchedule",
    "allAheadSchedule"
})
public class ReturnAmountResult
    extends WSResult
{

    protected ReturnAmountResult.AppointSchedule appointSchedule;
    protected ReturnAmountResult.AllAheadSchedule allAheadSchedule;

    /**
     * Gets the value of the appointSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnAmountResult.AppointSchedule }
     *     
     */
    public ReturnAmountResult.AppointSchedule getAppointSchedule() {
        return appointSchedule;
    }

    /**
     * Sets the value of the appointSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnAmountResult.AppointSchedule }
     *     
     */
    public void setAppointSchedule(ReturnAmountResult.AppointSchedule value) {
        this.appointSchedule = value;
    }

    /**
     * Gets the value of the allAheadSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnAmountResult.AllAheadSchedule }
     *     
     */
    public ReturnAmountResult.AllAheadSchedule getAllAheadSchedule() {
        return allAheadSchedule;
    }

    /**
     * Sets the value of the allAheadSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnAmountResult.AllAheadSchedule }
     *     
     */
    public void setAllAheadSchedule(ReturnAmountResult.AllAheadSchedule value) {
        this.allAheadSchedule = value;
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
     *         &lt;element name="allAheadScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}AllAheadScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "allAheadScheduleDTO"
    })
    public static class AllAheadSchedule {

        protected List<AllAheadScheduleDTO> allAheadScheduleDTO;

        /**
         * Gets the value of the allAheadScheduleDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the allAheadScheduleDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAllAheadScheduleDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AllAheadScheduleDTO }
         * 
         * 
         */
        public List<AllAheadScheduleDTO> getAllAheadScheduleDTO() {
            if (allAheadScheduleDTO == null) {
                allAheadScheduleDTO = new ArrayList<AllAheadScheduleDTO>();
            }
            return this.allAheadScheduleDTO;
        }

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
     *         &lt;element name="appointScheduleDTO" type="{http://www.creditease.cn/RuralBusyService/}AppointScheduleDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "appointScheduleDTO"
    })
    public static class AppointSchedule {

        protected List<AppointScheduleDTO> appointScheduleDTO;

        /**
         * Gets the value of the appointScheduleDTO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the appointScheduleDTO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAppointScheduleDTO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AppointScheduleDTO }
         * 
         * 
         */
        public List<AppointScheduleDTO> getAppointScheduleDTO() {
            if (appointScheduleDTO == null) {
                appointScheduleDTO = new ArrayList<AppointScheduleDTO>();
            }
            return this.appointScheduleDTO;
        }

    }

}

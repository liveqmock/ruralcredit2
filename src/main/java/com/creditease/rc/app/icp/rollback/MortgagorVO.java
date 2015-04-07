package com.creditease.rc.app.icp.rollback;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for mortgagorVO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorVO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.server.ws.icp.creditease.com/}mortgagor">
 *       &lt;sequence>
 *         &lt;element name="borrowerInfo" type="{http://service.server.ws.icp.creditease.com/}borrowerInfo" minOccurs="0"/>
 *         &lt;element name="mortgagor" type="{http://service.server.ws.icp.creditease.com/}mortgagor" minOccurs="0"/>
 *         &lt;element name="mortgagorApplyCorpList" type="{http://service.server.ws.icp.creditease.com/}mortgagorApplyCorp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorApplyHouseList" type="{http://service.server.ws.icp.creditease.com/}mortgagorApplyHouse" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorBankList" type="{http://service.server.ws.icp.creditease.com/}mortgagorBank" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorContactList" type="{http://service.server.ws.icp.creditease.com/}mortgagorContact" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorCorporationList" type="{http://service.server.ws.icp.creditease.com/}mortgagorCorporation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorFixedAssetList" type="{http://service.server.ws.icp.creditease.com/}mortgagorFixedAssets" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorInfoExtList" type="{http://service.server.ws.icp.creditease.com/}mortgagorInfoExt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorPersonaltyList" type="{http://service.server.ws.icp.creditease.com/}mortgagorPersonalty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorRelativesList" type="{http://service.server.ws.icp.creditease.com/}mortgagorRelatives" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mortgagorSalaryList" type="{http://service.server.ws.icp.creditease.com/}mortgagorSalary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orgCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rows" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorVO", propOrder = { "borrowerInfo", "mortgagor",
		"mortgagorApplyCorpList", "mortgagorApplyHouseList",
		"mortgagorBankList", "mortgagorContactList",
		"mortgagorCorporationList", "mortgagorFixedAssetList",
		"mortgagorInfoExtList", "mortgagorPersonaltyList",
		"mortgagorRelativesList", "mortgagorSalaryList", "orgCount", "page",
		"rows", "sortName", "sortOrder", "status" })
public class MortgagorVO extends Mortgagor {

	protected BorrowerInfo borrowerInfo;
	protected Mortgagor mortgagor;
	@XmlElement(nillable = true)
	protected List<MortgagorApplyCorp> mortgagorApplyCorpList;
	@XmlElement(nillable = true)
	protected List<MortgagorApplyHouse> mortgagorApplyHouseList;
	@XmlElement(nillable = true)
	protected List<MortgagorBank> mortgagorBankList;
	@XmlElement(nillable = true)
	protected List<MortgagorContact> mortgagorContactList;
	@XmlElement(nillable = true)
	protected List<MortgagorCorporation> mortgagorCorporationList;
	@XmlElement(nillable = true)
	protected List<MortgagorFixedAssets> mortgagorFixedAssetList;
	@XmlElement(nillable = true)
	protected List<MortgagorInfoExt> mortgagorInfoExtList;
	@XmlElement(nillable = true)
	protected List<MortgagorPersonalty> mortgagorPersonaltyList;
	@XmlElement(nillable = true)
	protected List<MortgagorRelatives> mortgagorRelativesList;
	@XmlElement(nillable = true)
	protected List<MortgagorSalary> mortgagorSalaryList;
	protected int orgCount;
	protected String page;
	protected String rows;
	protected String sortName;
	protected String sortOrder;
	protected String status;

	/**
	 * Gets the value of the borrowerInfo property.
	 * 
	 * @return possible object is {@link BorrowerInfo }
	 * 
	 */
	public BorrowerInfo getBorrowerInfo() {
		return borrowerInfo;
	}

	/**
	 * Sets the value of the borrowerInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link BorrowerInfo }
	 * 
	 */
	public void setBorrowerInfo(BorrowerInfo value) {
		this.borrowerInfo = value;
	}

	/**
	 * Gets the value of the mortgagor property.
	 * 
	 * @return possible object is {@link Mortgagor }
	 * 
	 */
	public Mortgagor getMortgagor() {
		return mortgagor;
	}

	/**
	 * Sets the value of the mortgagor property.
	 * 
	 * @param value
	 *            allowed object is {@link Mortgagor }
	 * 
	 */
	public void setMortgagor(Mortgagor value) {
		this.mortgagor = value;
	}

	/**
	 * Gets the value of the mortgagorApplyCorpList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorApplyCorpList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorApplyCorpList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorApplyCorp }
	 * 
	 * 
	 */
	public List<MortgagorApplyCorp> getMortgagorApplyCorpList() {
		if (mortgagorApplyCorpList == null) {
			mortgagorApplyCorpList = new ArrayList<MortgagorApplyCorp>();
		}
		return this.mortgagorApplyCorpList;
	}

	/**
	 * Gets the value of the mortgagorApplyHouseList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorApplyHouseList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorApplyHouseList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorApplyHouse }
	 * 
	 * 
	 */
	public List<MortgagorApplyHouse> getMortgagorApplyHouseList() {
		if (mortgagorApplyHouseList == null) {
			mortgagorApplyHouseList = new ArrayList<MortgagorApplyHouse>();
		}
		return this.mortgagorApplyHouseList;
	}

	/**
	 * Gets the value of the mortgagorBankList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorBankList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorBankList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorBank }
	 * 
	 * 
	 */
	public List<MortgagorBank> getMortgagorBankList() {
		if (mortgagorBankList == null) {
			mortgagorBankList = new ArrayList<MortgagorBank>();
		}
		return this.mortgagorBankList;
	}

	/**
	 * Gets the value of the mortgagorContactList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorContactList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorContactList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorContact }
	 * 
	 * 
	 */
	public List<MortgagorContact> getMortgagorContactList() {
		if (mortgagorContactList == null) {
			mortgagorContactList = new ArrayList<MortgagorContact>();
		}
		return this.mortgagorContactList;
	}

	/**
	 * Gets the value of the mortgagorCorporationList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorCorporationList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorCorporationList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorCorporation }
	 * 
	 * 
	 */
	public List<MortgagorCorporation> getMortgagorCorporationList() {
		if (mortgagorCorporationList == null) {
			mortgagorCorporationList = new ArrayList<MortgagorCorporation>();
		}
		return this.mortgagorCorporationList;
	}

	/**
	 * Gets the value of the mortgagorFixedAssetList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorFixedAssetList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorFixedAssetList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorFixedAssets }
	 * 
	 * 
	 */
	public List<MortgagorFixedAssets> getMortgagorFixedAssetList() {
		if (mortgagorFixedAssetList == null) {
			mortgagorFixedAssetList = new ArrayList<MortgagorFixedAssets>();
		}
		return this.mortgagorFixedAssetList;
	}

	/**
	 * Gets the value of the mortgagorInfoExtList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorInfoExtList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorInfoExtList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorInfoExt }
	 * 
	 * 
	 */
	public List<MortgagorInfoExt> getMortgagorInfoExtList() {
		if (mortgagorInfoExtList == null) {
			mortgagorInfoExtList = new ArrayList<MortgagorInfoExt>();
		}
		return this.mortgagorInfoExtList;
	}

	/**
	 * Gets the value of the mortgagorPersonaltyList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorPersonaltyList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorPersonaltyList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorPersonalty }
	 * 
	 * 
	 */
	public List<MortgagorPersonalty> getMortgagorPersonaltyList() {
		if (mortgagorPersonaltyList == null) {
			mortgagorPersonaltyList = new ArrayList<MortgagorPersonalty>();
		}
		return this.mortgagorPersonaltyList;
	}

	/**
	 * Gets the value of the mortgagorRelativesList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorRelativesList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorRelativesList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorRelatives }
	 * 
	 * 
	 */
	public List<MortgagorRelatives> getMortgagorRelativesList() {
		if (mortgagorRelativesList == null) {
			mortgagorRelativesList = new ArrayList<MortgagorRelatives>();
		}
		return this.mortgagorRelativesList;
	}

	/**
	 * Gets the value of the mortgagorSalaryList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the mortgagorSalaryList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMortgagorSalaryList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MortgagorSalary }
	 * 
	 * 
	 */
	public List<MortgagorSalary> getMortgagorSalaryList() {
		if (mortgagorSalaryList == null) {
			mortgagorSalaryList = new ArrayList<MortgagorSalary>();
		}
		return this.mortgagorSalaryList;
	}

	/**
	 * Gets the value of the orgCount property.
	 * 
	 */
	public int getOrgCount() {
		return orgCount;
	}

	/**
	 * Sets the value of the orgCount property.
	 * 
	 */
	public void setOrgCount(int value) {
		this.orgCount = value;
	}

	/**
	 * Gets the value of the page property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Sets the value of the page property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPage(String value) {
		this.page = value;
	}

	/**
	 * Gets the value of the rows property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRows() {
		return rows;
	}

	/**
	 * Sets the value of the rows property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRows(String value) {
		this.rows = value;
	}

	/**
	 * Gets the value of the sortName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * Sets the value of the sortName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSortName(String value) {
		this.sortName = value;
	}

	/**
	 * Gets the value of the sortOrder property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * Sets the value of the sortOrder property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSortOrder(String value) {
		this.sortOrder = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

}

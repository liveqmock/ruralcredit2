package com.creditease.rc.app.icp.rollback;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for mortgagorSalary complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mortgagorSalary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bigGapReasn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cashFlowTerm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cashFlowType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumptionHabits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conversionRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isavglastestmonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isavgn1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isavgn2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isavgn3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isavgn4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isavgn5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuedBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latestMonthIncome" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthIncome" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="mortgagorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mortgagorSalaryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="n1" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="n2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="n3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="n4" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="n5" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="operDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payrollForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payrollFormDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mortgagorSalary", propOrder = { "bigGapReasn", "cashFlowTerm",
		"cashFlowType", "consumptionHabits", "conversionRate", "createDate",
		"creator", "description", "endBalance", "isavglastestmonth", "isavgn1",
		"isavgn2", "isavgn3", "isavgn4", "isavgn5", "issuedBranch",
		"issuedDate", "latestMonthIncome", "memo", "monthIncome",
		"mortgagorId", "mortgagorSalaryId", "n1", "n2", "n3", "n4", "n5",
		"operDate", "payrollForm", "payrollFormDesc", "seqNo" })
public class MortgagorSalary {

	protected String bigGapReasn;
	protected Integer cashFlowTerm;
	protected String cashFlowType;
	protected String consumptionHabits;
	protected BigDecimal conversionRate;
	protected String createDate;
	protected Long creator;
	protected String description;
	protected BigDecimal endBalance;
	protected String isavglastestmonth;
	protected String isavgn1;
	protected String isavgn2;
	protected String isavgn3;
	protected String isavgn4;
	protected String isavgn5;
	protected String issuedBranch;
	protected String issuedDate;
	protected BigDecimal latestMonthIncome;
	protected String memo;
	protected BigDecimal monthIncome;
	protected Long mortgagorId;
	protected Long mortgagorSalaryId;
	protected BigDecimal n1;
	protected BigDecimal n2;
	protected BigDecimal n3;
	protected BigDecimal n4;
	protected BigDecimal n5;
	protected String operDate;
	protected String payrollForm;
	protected String payrollFormDesc;
	protected Integer seqNo;

	/**
	 * Gets the value of the bigGapReasn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBigGapReasn() {
		return bigGapReasn;
	}

	/**
	 * Sets the value of the bigGapReasn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBigGapReasn(String value) {
		this.bigGapReasn = value;
	}

	/**
	 * Gets the value of the cashFlowTerm property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCashFlowTerm() {
		return cashFlowTerm;
	}

	/**
	 * Sets the value of the cashFlowTerm property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCashFlowTerm(Integer value) {
		this.cashFlowTerm = value;
	}

	/**
	 * Gets the value of the cashFlowType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCashFlowType() {
		return cashFlowType;
	}

	/**
	 * Sets the value of the cashFlowType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCashFlowType(String value) {
		this.cashFlowType = value;
	}

	/**
	 * Gets the value of the consumptionHabits property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsumptionHabits() {
		return consumptionHabits;
	}

	/**
	 * Sets the value of the consumptionHabits property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsumptionHabits(String value) {
		this.consumptionHabits = value;
	}

	/**
	 * Gets the value of the conversionRate property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	/**
	 * Sets the value of the conversionRate property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setConversionRate(BigDecimal value) {
		this.conversionRate = value;
	}

	/**
	 * Gets the value of the createDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateDate(String value) {
		this.createDate = value;
	}

	/**
	 * Gets the value of the creator property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getCreator() {
		return creator;
	}

	/**
	 * Sets the value of the creator property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setCreator(Long value) {
		this.creator = value;
	}

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the endBalance property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getEndBalance() {
		return endBalance;
	}

	/**
	 * Sets the value of the endBalance property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setEndBalance(BigDecimal value) {
		this.endBalance = value;
	}

	/**
	 * Gets the value of the isavglastestmonth property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsavglastestmonth() {
		return isavglastestmonth;
	}

	/**
	 * Sets the value of the isavglastestmonth property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsavglastestmonth(String value) {
		this.isavglastestmonth = value;
	}

	/**
	 * Gets the value of the isavgn1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsavgn1() {
		return isavgn1;
	}

	/**
	 * Sets the value of the isavgn1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsavgn1(String value) {
		this.isavgn1 = value;
	}

	/**
	 * Gets the value of the isavgn2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsavgn2() {
		return isavgn2;
	}

	/**
	 * Sets the value of the isavgn2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsavgn2(String value) {
		this.isavgn2 = value;
	}

	/**
	 * Gets the value of the isavgn3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsavgn3() {
		return isavgn3;
	}

	/**
	 * Sets the value of the isavgn3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsavgn3(String value) {
		this.isavgn3 = value;
	}

	/**
	 * Gets the value of the isavgn4 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsavgn4() {
		return isavgn4;
	}

	/**
	 * Sets the value of the isavgn4 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsavgn4(String value) {
		this.isavgn4 = value;
	}

	/**
	 * Gets the value of the isavgn5 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsavgn5() {
		return isavgn5;
	}

	/**
	 * Sets the value of the isavgn5 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsavgn5(String value) {
		this.isavgn5 = value;
	}

	/**
	 * Gets the value of the issuedBranch property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIssuedBranch() {
		return issuedBranch;
	}

	/**
	 * Sets the value of the issuedBranch property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIssuedBranch(String value) {
		this.issuedBranch = value;
	}

	/**
	 * Gets the value of the issuedDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIssuedDate() {
		return issuedDate;
	}

	/**
	 * Sets the value of the issuedDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIssuedDate(String value) {
		this.issuedDate = value;
	}

	/**
	 * Gets the value of the latestMonthIncome property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getLatestMonthIncome() {
		return latestMonthIncome;
	}

	/**
	 * Sets the value of the latestMonthIncome property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setLatestMonthIncome(BigDecimal value) {
		this.latestMonthIncome = value;
	}

	/**
	 * Gets the value of the memo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the value of the memo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMemo(String value) {
		this.memo = value;
	}

	/**
	 * Gets the value of the monthIncome property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getMonthIncome() {
		return monthIncome;
	}

	/**
	 * Sets the value of the monthIncome property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setMonthIncome(BigDecimal value) {
		this.monthIncome = value;
	}

	/**
	 * Gets the value of the mortgagorId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getMortgagorId() {
		return mortgagorId;
	}

	/**
	 * Sets the value of the mortgagorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setMortgagorId(Long value) {
		this.mortgagorId = value;
	}

	/**
	 * Gets the value of the mortgagorSalaryId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getMortgagorSalaryId() {
		return mortgagorSalaryId;
	}

	/**
	 * Sets the value of the mortgagorSalaryId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setMortgagorSalaryId(Long value) {
		this.mortgagorSalaryId = value;
	}

	/**
	 * Gets the value of the n1 property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getN1() {
		return n1;
	}

	/**
	 * Sets the value of the n1 property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setN1(BigDecimal value) {
		this.n1 = value;
	}

	/**
	 * Gets the value of the n2 property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getN2() {
		return n2;
	}

	/**
	 * Sets the value of the n2 property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setN2(BigDecimal value) {
		this.n2 = value;
	}

	/**
	 * Gets the value of the n3 property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getN3() {
		return n3;
	}

	/**
	 * Sets the value of the n3 property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setN3(BigDecimal value) {
		this.n3 = value;
	}

	/**
	 * Gets the value of the n4 property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getN4() {
		return n4;
	}

	/**
	 * Sets the value of the n4 property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setN4(BigDecimal value) {
		this.n4 = value;
	}

	/**
	 * Gets the value of the n5 property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getN5() {
		return n5;
	}

	/**
	 * Sets the value of the n5 property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setN5(BigDecimal value) {
		this.n5 = value;
	}

	/**
	 * Gets the value of the operDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOperDate() {
		return operDate;
	}

	/**
	 * Sets the value of the operDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOperDate(String value) {
		this.operDate = value;
	}

	/**
	 * Gets the value of the payrollForm property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayrollForm() {
		return payrollForm;
	}

	/**
	 * Sets the value of the payrollForm property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayrollForm(String value) {
		this.payrollForm = value;
	}

	/**
	 * Gets the value of the payrollFormDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayrollFormDesc() {
		return payrollFormDesc;
	}

	/**
	 * Sets the value of the payrollFormDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayrollFormDesc(String value) {
		this.payrollFormDesc = value;
	}

	/**
	 * Gets the value of the seqNo property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSeqNo() {
		return seqNo;
	}

	/**
	 * Sets the value of the seqNo property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSeqNo(Integer value) {
		this.seqNo = value;
	}

}

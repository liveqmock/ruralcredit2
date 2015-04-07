package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author haoqiang
 * 
 */
public class Surveybusinessinfo implements Serializable {
	private Integer surveyBusinessInfoId;
	private Long borrowerServiceAppId;
	private String organizationName;
	private Date startBusinessTime;
	private String businessScope;
	private String siteArea;
	private String businessAddress;
	private String businessPlaceType;
	private String businessPermitNo;
	private Double totalIncome;
	private String taxRegisterNo;
	private Date businessPlaceDate;
	private String businessPlaceOther;
	private Integer employeeNumber;
	private Double pay;
	private String operatingItems; // 经营项目（选择）
	private String operatingItemsView; // 经营项目（选择）
	private String operatingItemsDetail;// 经营项目 （详细）
	private String startingDate; // 起始年份
	private String state; // 状态（营业 ，停业）
	private String businessLicense; // 营业执照（有，无）
	private String taxRegistrationCertificate; // 税务登记证（有，无）
	private Double monthGrossIncome;

	private String seq;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Double getMonthGrossIncome() {
		return monthGrossIncome;
	}

	public void setMonthGrossIncome(Double monthGrossIncome) {
		this.monthGrossIncome = monthGrossIncome;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Integer getSurveyBusinessInfoId() {
		return surveyBusinessInfoId;
	}

	public void setSurveyBusinessInfoId(Integer surveyBusinessInfoId) {
		this.surveyBusinessInfoId = surveyBusinessInfoId;
	}


	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getStartBusinessTime() {
		return startBusinessTime;
	}

	public void setStartBusinessTime(Date startBusinessTime) {
		this.startBusinessTime = startBusinessTime;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getSiteArea() {
		return siteArea;
	}

	public void setSiteArea(String siteArea) {
		this.siteArea = siteArea;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getBusinessPlaceType() {
		return businessPlaceType;
	}

	public void setBusinessPlaceType(String businessPlaceType) {
		this.businessPlaceType = businessPlaceType;
	}

	public String getBusinessPermitNo() {
		return businessPermitNo;
	}

	public void setBusinessPermitNo(String businessPermitNo) {
		this.businessPermitNo = businessPermitNo;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getTaxRegisterNo() {
		return taxRegisterNo;
	}

	public void setTaxRegisterNo(String taxRegisterNo) {
		this.taxRegisterNo = taxRegisterNo;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getBusinessPlaceDate() {
		return businessPlaceDate;
	}

	public void setBusinessPlaceDate(Date businessPlaceDate) {
		this.businessPlaceDate = businessPlaceDate;
	}

	public String getBusinessPlaceOther() {
		return businessPlaceOther;
	}

	public void setBusinessPlaceOther(String businessPlaceOther) {
		this.businessPlaceOther = businessPlaceOther;
	}

	public String getOperatingItems() {
		return operatingItems;
	}

	public void setOperatingItems(String operatingItems) {
		this.operatingItems = operatingItems;
	}

	public String getOperatingItemsDetail() {
		return operatingItemsDetail;
	}

	public void setOperatingItemsDetail(String operatingItemsDetail) {
		this.operatingItemsDetail = operatingItemsDetail;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxRegistrationCertificate() {
		return taxRegistrationCertificate;
	}

	public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
		this.taxRegistrationCertificate = taxRegistrationCertificate;
	}

	public Long getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Long borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getOperatingItemsView() {
		return operatingItemsView;
	}

	public void setOperatingItemsView(String operatingItemsView) {
		this.operatingItemsView = operatingItemsView;
	}

}

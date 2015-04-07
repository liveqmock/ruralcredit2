package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
/**
 * 
 * 经营现金流入项目
 *
 */
public class CashStream implements Serializable {
	
	private Integer cashStreamId;
	private String projectName;
	private String projectType;	//项目类型，用于区别项目类别，比如经营现金流入项目
	private Double everyDay;
	private Double everyWeek;
	private Double everyMonth;
	private Double everyQuarter;
	private Double everyHalfYear;
	private Double nineMonth;
	private Double everyYear;
	private Double calculateValue;// 计算值，计算等效月效，每日等效净现金流等
	private Integer inOrOut;	//流入还是流出 0-流入，1-流出
	private Date createDate;
	private Integer creditapplicationId;	//信贷申请ID
	private String borrowerName;			//借款人姓名
	private String projectCodeKey; //项目的 数据字典codekey
	
	public Integer getCashStreamId() {
		return cashStreamId;
	}
	public void setCashStreamId(Integer cashStreamId) {
		this.cashStreamId = cashStreamId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public Double getEveryDay() {
		return everyDay;
	}
	public void setEveryDay(Double everyDay) {
		this.everyDay = everyDay;
	}
	public Double getEveryWeek() {
		return everyWeek;
	}
	public void setEveryWeek(Double everyWeek) {
		this.everyWeek = everyWeek;
	}
	public Double getEveryMonth() {
		return everyMonth;
	}
	public void setEveryMonth(Double everyMonth) {
		this.everyMonth = everyMonth;
	}
	public Double getEveryQuarter() {
		return everyQuarter;
	}
	public void setEveryQuarter(Double everyQuarter) {
		this.everyQuarter = everyQuarter;
	}
	public Double getEveryHalfYear() {
		return everyHalfYear;
	}
	public void setEveryHalfYear(Double everyHalfYear) {
		this.everyHalfYear = everyHalfYear;
	}
	public Double getNineMonth() {
		return nineMonth;
	}
	public void setNineMonth(Double nineMonth) {
		this.nineMonth = nineMonth;
	}
	public Double getEveryYear() {
		return everyYear;
	}
	public void setEveryYear(Double everyYear) {
		this.everyYear = everyYear;
	}
	public Double getCalculateValue() {
		return calculateValue;
	}
	public void setCalculateValue(Double calculateValue) {
		this.calculateValue = calculateValue;
	}
	public Integer getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(Integer inOrOut) {
		this.inOrOut = inOrOut;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getProjectCodeKey() {
		return projectCodeKey;
	}
	public void setProjectCodeKey(String projectCodeKey) {
		this.projectCodeKey = projectCodeKey;
	}
	
}

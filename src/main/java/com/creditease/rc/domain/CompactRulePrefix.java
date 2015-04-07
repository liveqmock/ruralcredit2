package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHmsDateSerializer;

public class CompactRulePrefix {
	private Long compactRulePrefixId;

	private String prefixMark;

	private String teamDepartmentId;

	private String teamDepartmentName;

	private String operatorName;

	private String operatorId;

	private Date operateDate;

	private String onOff;

	private String province;
	private String city;
	private String area;

	public Long getCompactRulePrefixId() {
		return compactRulePrefixId;
	}

	public void setCompactRulePrefixId(Long compactRulePrefixId) {
		this.compactRulePrefixId = compactRulePrefixId;
	}

	public String getPrefixMark() {
		return prefixMark;
	}

	public void setPrefixMark(String prefixMark) {
		this.prefixMark = prefixMark;
	}

	public String getTeamDepartmentId() {
		return teamDepartmentId;
	}

	public void setTeamDepartmentId(String teamDepartmentId) {
		this.teamDepartmentId = teamDepartmentId;
	}

	public String getTeamDepartmentName() {
		return teamDepartmentName;
	}

	public void setTeamDepartmentName(String teamDepartmentName) {
		this.teamDepartmentName = teamDepartmentName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@JsonSerialize(using = JsonYMDHmsDateSerializer.class)
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOnOff() {
		return onOff;
	}

	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
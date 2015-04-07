package com.creditease.rc.domain;

import java.math.BigDecimal;

public class SalesPlanning {

    public SalesPlanning() {
    }

    public SalesPlanning(Integer year, String type) {
        this.year = year;
        this.type = type;
    }

    public SalesPlanning(Integer year, String type, String areaDepartmentIds) {
        this.year = year;
        this.type = type;
        this.areaDepartmentIds = areaDepartmentIds;
    }

    private Long salesPlanningId; // 销售计划主键
	private Integer year; // 年份
	private Integer month; // 月份
	private String type; // 数据类型：0、放款量，1、合同金额
	private Long areaDepartmentId; // 营业部ID （2015-02-03 需求变更：也值大区ID、分中心ID）
	private String areaDepartmentIds; // 营业部IDs ..s
	private String areaDepartmentName; // 营业部名称 （2015-02-03 需求变更：也值大区名称、分中心名称）
	private BigDecimal value; // 数值
	private String planType; // 大区销售计划、分中心..、营业部..

	public Long getSalesPlanningId() {
		return salesPlanningId;
	}

	public void setSalesPlanningId(Long salesPlanningId) {
		this.salesPlanningId = salesPlanningId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAreaDepartmentId() {
		return areaDepartmentId;
	}

	public void setAreaDepartmentId(Long areaDepartmentId) {
		this.areaDepartmentId = areaDepartmentId;
	}

	public String getAreaDepartmentName() {
		return areaDepartmentName;
	}

	public void setAreaDepartmentName(String areaDepartmentName) {
		this.areaDepartmentName = areaDepartmentName;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

    public String getAreaDepartmentIds() {
        return areaDepartmentIds;
    }

    public void setAreaDepartmentIds(String areaDepartmentIds) {
        this.areaDepartmentIds = areaDepartmentIds;
    }
}
package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHmsDateSerializer;

public class RollingForecast {
	private Long rollingForecastId;// 滚动预测主键
	private Integer year;// 年份
	private Integer month;// 月份
	private String type;// 数据类型：0、放款量，1、合同金额
	private Long areaDepartmentId;// 营业部ID
	private String areaDepartmentName;// 营业部名称
	private BigDecimal firstMonth;// 下一个月
	private BigDecimal secondMonth;// 下二个月
	private BigDecimal thirdMonth;// 下三个月
	private Date operateTime;// 操作时间
	private String operatorId;// 操作人ID
	private String operatorName;// 操作人姓名
	private String historyFlag;// 历史标识

	public Long getRollingForecastId() {
		return rollingForecastId;
	}

	public void setRollingForecastId(Long rollingForecastId) {
		this.rollingForecastId = rollingForecastId;
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

	public BigDecimal getFirstMonth() {
		return firstMonth;
	}

	public void setFirstMonth(BigDecimal firstMonth) {
		this.firstMonth = firstMonth;
	}

	public BigDecimal getSecondMonth() {
		return secondMonth;
	}

	public void setSecondMonth(BigDecimal secondMonth) {
		this.secondMonth = secondMonth;
	}

	public BigDecimal getThirdMonth() {
		return thirdMonth;
	}

	public void setThirdMonth(BigDecimal thirdMonth) {
		this.thirdMonth = thirdMonth;
	}

	@JsonSerialize(using = JsonYMDHmsDateSerializer.class)
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}

}

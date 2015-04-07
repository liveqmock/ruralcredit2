package com.creditease.rc.vo;

import java.math.BigDecimal;

public class RollingForecastHistory {

	private Integer year;
	private Integer month;
	private String thisYearAndMonth;
	private String type;
	private BigDecimal firstMonthTolal;
	private BigDecimal secondMonthTolal;
	private BigDecimal thirdMonthTolal;
	private String firstMonths;
	private String secondMonths;
	private String thirdMonths;

	public String getThisYearAndMonth() {
		return thisYearAndMonth;
	}

	public void setThisYearAndMonth(String thisYearAndMonth) {
		this.thisYearAndMonth = thisYearAndMonth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getFirstMonthTolal() {
		return firstMonthTolal;
	}

	public void setFirstMonthTolal(BigDecimal firstMonthTolal) {
		this.firstMonthTolal = firstMonthTolal;
	}

	public BigDecimal getSecondMonthTolal() {
		return secondMonthTolal;
	}

	public void setSecondMonthTolal(BigDecimal secondMonthTolal) {
		this.secondMonthTolal = secondMonthTolal;
	}

	public BigDecimal getThirdMonthTolal() {
		return thirdMonthTolal;
	}

	public void setThirdMonthTolal(BigDecimal thirdMonthTolal) {
		this.thirdMonthTolal = thirdMonthTolal;
	}

	public String getFirstMonths() {
		return firstMonths;
	}

	public void setFirstMonths(String firstMonths) {
		this.firstMonths = firstMonths;
	}

	public String getSecondMonths() {
		return secondMonths;
	}

	public void setSecondMonths(String secondMonths) {
		this.secondMonths = secondMonths;
	}

	public String getThirdMonths() {
		return thirdMonths;
	}

	public void setThirdMonths(String thirdMonths) {
		this.thirdMonths = thirdMonths;
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

}

package com.creditease.rc.vo;

import java.math.BigDecimal;

public class RollingForecastExcel {
	private Integer year;
	private Integer month;
	private String thisYearAndMonth;
	private BigDecimal firstMonthTolal0;
	private BigDecimal secondMonthTolal0;
	private BigDecimal thirdMonthTolal0;
	private BigDecimal firstMonthTolal1;
	private BigDecimal secondMonthTolal1;
	private BigDecimal thirdMonthTolal1;

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

	public String getThisYearAndMonth() {
		return thisYearAndMonth;
	}

	public void setThisYearAndMonth(String thisYearAndMonth) {
		this.thisYearAndMonth = thisYearAndMonth;
	}

	public BigDecimal getFirstMonthTolal0() {
		return firstMonthTolal0;
	}

	public void setFirstMonthTolal0(BigDecimal firstMonthTolal0) {
		this.firstMonthTolal0 = firstMonthTolal0;
	}

	public BigDecimal getSecondMonthTolal0() {
		return secondMonthTolal0;
	}

	public void setSecondMonthTolal0(BigDecimal secondMonthTolal0) {
		this.secondMonthTolal0 = secondMonthTolal0;
	}

	public BigDecimal getThirdMonthTolal0() {
		return thirdMonthTolal0;
	}

	public void setThirdMonthTolal0(BigDecimal thirdMonthTolal0) {
		this.thirdMonthTolal0 = thirdMonthTolal0;
	}

	public BigDecimal getFirstMonthTolal1() {
		return firstMonthTolal1;
	}

	public void setFirstMonthTolal1(BigDecimal firstMonthTolal1) {
		this.firstMonthTolal1 = firstMonthTolal1;
	}

	public BigDecimal getSecondMonthTolal1() {
		return secondMonthTolal1;
	}

	public void setSecondMonthTolal1(BigDecimal secondMonthTolal1) {
		this.secondMonthTolal1 = secondMonthTolal1;
	}

	public BigDecimal getThirdMonthTolal1() {
		return thirdMonthTolal1;
	}

	public void setThirdMonthTolal1(BigDecimal thirdMonthTolal1) {
		this.thirdMonthTolal1 = thirdMonthTolal1;
	}

}

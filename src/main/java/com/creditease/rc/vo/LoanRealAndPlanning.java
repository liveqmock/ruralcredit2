package com.creditease.rc.vo;

import java.math.BigDecimal;
import java.util.Date;

public class LoanRealAndPlanning {

	private String yearAndMonth;
	private Date dateTime;
	private BigDecimal rAmount;
	private Integer rCount;
	private BigDecimal pAmount;
	private Integer pCount;
	private BigDecimal amountRatio;
	private BigDecimal countRatio;
	private BigDecimal amountRatioTotal;
	private BigDecimal countRatioTotal;

	public String getYearAndMonth() {
		return yearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public BigDecimal getrAmount() {
		return rAmount;
	}

	public void setrAmount(BigDecimal rAmount) {
		this.rAmount = rAmount;
	}

	public Integer getrCount() {
		return rCount;
	}

	public void setrCount(Integer rCount) {
		this.rCount = rCount;
	}

	public BigDecimal getpAmount() {
		return pAmount;
	}

	public void setpAmount(BigDecimal pAmount) {
		this.pAmount = pAmount;
	}

	public Integer getpCount() {
		return pCount;
	}

	public void setpCount(Integer pCount) {
		this.pCount = pCount;
	}

	public BigDecimal getAmountRatio() {
		return amountRatio;
	}

	public void setAmountRatio(BigDecimal amountRatio) {
		this.amountRatio = amountRatio;
	}

	public BigDecimal getCountRatio() {
		return countRatio;
	}

	public void setCountRatio(BigDecimal countRatio) {
		this.countRatio = countRatio;
	}

	public BigDecimal getAmountRatioTotal() {
		return amountRatioTotal;
	}

	public void setAmountRatioTotal(BigDecimal amountRatioTotal) {
		this.amountRatioTotal = amountRatioTotal;
	}

	public BigDecimal getCountRatioTotal() {
		return countRatioTotal;
	}

	public void setCountRatioTotal(BigDecimal countRatioTotal) {
		this.countRatioTotal = countRatioTotal;
	}

}

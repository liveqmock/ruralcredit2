package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 虚拟现金流类
 * @author zhangman
 *
 */
public class ReturnSchedule implements Serializable{
	private	Integer	scheduleId;	//	虚拟现金流ID
	private	Integer	borrowerServiceAppid;	//	借款服务申请ID
	private	Date	returnDate;	//	预计现金流入日期
	private	Double	damount;	//	预计流入金额
	private	Double dcorpus;	//	本金
	private	Double	dinterest;	//	利息
	private	Double	doverhead;	//	管理费
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Integer getBorrowerServiceAppid() {
		return borrowerServiceAppid;
	}
	public void setBorrowerServiceAppid(Integer borrowerServiceAppid) {
		this.borrowerServiceAppid = borrowerServiceAppid;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Double getDamount() {
		return damount;
	}
	public void setDamount(Double damount) {
		this.damount = damount;
	}
	public Double getDcorpus() {
		return dcorpus;
	}
	public void setDcorpus(Double dcorpus) {
		this.dcorpus = dcorpus;
	}
	public Double getDinterest() {
		return dinterest;
	}
	public void setDinterest(Double dinterest) {
		this.dinterest = dinterest;
	}
	public Double getDoverhead() {
		return doverhead;
	}
	public void setDoverhead(Double doverhead) {
		this.doverhead = doverhead;
	}

}

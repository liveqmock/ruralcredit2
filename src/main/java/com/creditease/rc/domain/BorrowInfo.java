package com.creditease.rc.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.util.DateUtil;
/**
 * 
 * @author zhangman
 *
 */
public class BorrowInfo implements Serializable {

	private Integer borrowInfoId; 
	private String lender;			//贷款机构	
	private Date releaseDate;		//放款日期	
	private Date expirationDate;	//到期日期
	private Double creditLine;		//授信额度
	private Double useCredit;		//已使用额度
	private String amountStatus;	//账户状态
	private String repayWay;		//还款方式
	private Double interestRate;	//年利率
	private Double eachRepayAmount;//没次还款金额
	private Integer borrowerServiceAppId;//借款服务申请ID

	public Integer getBorrowInfoId() {
		return borrowInfoId;
	}

	public void setBorrowInfoId(Integer borrowInfoId) {
		this.borrowInfoId = borrowInfoId;
	}

	public String getLender() {
		return lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}


	public String getRepayWay() {
		return repayWay;
	}

	public void setRepayWay(String repayWay) {
		this.repayWay = repayWay;
	}


	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}


	@JsonSerialize(using=JsonYMDDateSerializer.class) 
	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	@JsonSerialize(using=JsonYMDDateSerializer.class) 
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAmountStatus() {
		return amountStatus;
	}

	public void setAmountStatus(String amountStatus) {
		this.amountStatus = amountStatus;
	}

	public Double getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(Double creditLine) {
		this.creditLine = creditLine;
	}

	public Double getUseCredit() {
		return useCredit;
	}

	public void setUseCredit(Double useCredit) {
		this.useCredit = useCredit;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getEachRepayAmount() {
		return eachRepayAmount;
	}

	public void setEachRepayAmount(Double eachRepayAmount) {
		this.eachRepayAmount = eachRepayAmount;
	}

}

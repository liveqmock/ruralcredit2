package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.CashStream;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Deposit;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.domain.Surveybusinessinfo;

/**
 * 现金流vo
 *
 */
public class CashStreamVo {
	
	private String name;
	private List<CashStream> csOperInList;		//经营现金流入项目
	private CashStream csOperInTotal;			//经营现金流入合计
	private List<CashStream> csOperOutList;		//经营现金流出项目
	private List<CashStream> csOperOutListChild;//经营现金流出项目子项目
	private CashStream csOperOutTotal;			//经营现金流出合计
	private CashStream csOperNet;				//经营净现金流
	private List<CashStream> csFamilyInList;	//家庭现金流入项目
	private CashStream csFamilyInTotal;			//家庭现金流入合计
	private List<CashStream> csFamilyOutList;	//家庭现金流出项目
	private CashStream csFamilyOutTotal;		//家庭现金流出合计
	private CashStream csFamilyNet;				//家庭净现金流
	private CashStream csNetTotal;				//净现金流合计
	private CashStream csNetEveryDayEq;			//每日等效净现金流
	private CashStream csNetEveryWeekEq;		//每周等效
	private CashStream csNetEveryMonthEq;		//每月等效
	private CashStream csNetEveryQuarterEq;		//每季度等效
	private CashStream csNetEveryHalfYearEq;	//每半年等效
	private CashStream csNetEverynineMonthEq;	//每九个月等效
	private CashStream csNetEveryYearEq;		//年度等效
	private CashStream canDominateMoney;		//可支配现金
	private CashStream csAdjCoefficient;		//现金流调整系数
	private CashStream yearInterestRate;		//年化利率
	private CashStream loanDeadline;			//借款期限
	private CashStream eqRepaymentPeriod;		//等额还款期数
	private CashStream applyLoanSum;			//申请借款金额
	private CashStream maxLoanSum;				//最大借款额度

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CashStream> getCsOperInList() {
		return csOperInList;
	}

	public void setCsOperInList(List<CashStream> csOperInList) {
		this.csOperInList = csOperInList;
	}

	public CashStream getCsOperInTotal() {
		return csOperInTotal;
	}

	public void setCsOperInTotal(CashStream csOperInTotal) {
		this.csOperInTotal = csOperInTotal;
	}

	public List<CashStream> getCsOperOutList() {
		return csOperOutList;
	}

	public void setCsOperOutList(List<CashStream> csOperOutList) {
		this.csOperOutList = csOperOutList;
	}

	public CashStream getCsOperOutTotal() {
		return csOperOutTotal;
	}

	public void setCsOperOutTotal(CashStream csOperOutTotal) {
		this.csOperOutTotal = csOperOutTotal;
	}

	public CashStream getCsOperNet() {
		return csOperNet;
	}

	public void setCsOperNet(CashStream csOperNet) {
		this.csOperNet = csOperNet;
	}

	public List<CashStream> getCsFamilyInList() {
		return csFamilyInList;
	}

	public void setCsFamilyInList(List<CashStream> csFamilyInList) {
		this.csFamilyInList = csFamilyInList;
	}

	public List<CashStream> getCsFamilyOutList() {
		return csFamilyOutList;
	}

	public void setCsFamilyOutList(List<CashStream> csFamilyOutList) {
		this.csFamilyOutList = csFamilyOutList;
	}

	public CashStream getCsNetTotal() {
		return csNetTotal;
	}

	public void setCsNetTotal(CashStream csNetTotal) {
		this.csNetTotal = csNetTotal;
	}

	public CashStream getCsNetEveryDayEq() {
		return csNetEveryDayEq;
	}

	public void setCsNetEveryDayEq(CashStream csNetEveryDayEq) {
		this.csNetEveryDayEq = csNetEveryDayEq;
	}

	public CashStream getCsNetEveryWeekEq() {
		return csNetEveryWeekEq;
	}

	public void setCsNetEveryWeekEq(CashStream csNetEveryWeekEq) {
		this.csNetEveryWeekEq = csNetEveryWeekEq;
	}

	public CashStream getCsFamilyInTotal() {
		return csFamilyInTotal;
	}

	public void setCsFamilyInTotal(CashStream csFamilyInTotal) {
		this.csFamilyInTotal = csFamilyInTotal;
	}

	public CashStream getCsFamilyOutTotal() {
		return csFamilyOutTotal;
	}

	public void setCsFamilyOutTotal(CashStream csFamilyOutTotal) {
		this.csFamilyOutTotal = csFamilyOutTotal;
	}

	public CashStream getCsFamilyNet() {
		return csFamilyNet;
	}

	public void setCsFamilyNet(CashStream csFamilyNet) {
		this.csFamilyNet = csFamilyNet;
	}

	public CashStream getCsNetEveryMonthEq() {
		return csNetEveryMonthEq;
	}

	public void setCsNetEveryMonthEq(CashStream csNetEveryMonthEq) {
		this.csNetEveryMonthEq = csNetEveryMonthEq;
	}

	public CashStream getCsNetEveryQuarterEq() {
		return csNetEveryQuarterEq;
	}

	public void setCsNetEveryQuarterEq(CashStream csNetEveryQuarterEq) {
		this.csNetEveryQuarterEq = csNetEveryQuarterEq;
	}

	public CashStream getCsNetEveryHalfYearEq() {
		return csNetEveryHalfYearEq;
	}

	public void setCsNetEveryHalfYearEq(CashStream csNetEveryHalfYearEq) {
		this.csNetEveryHalfYearEq = csNetEveryHalfYearEq;
	}

	public CashStream getCsNetEverynineMonthEq() {
		return csNetEverynineMonthEq;
	}

	public void setCsNetEverynineMonthEq(CashStream csNetEverynineMonthEq) {
		this.csNetEverynineMonthEq = csNetEverynineMonthEq;
	}

	public CashStream getCsNetEveryYearEq() {
		return csNetEveryYearEq;
	}

	public void setCsNetEveryYearEq(CashStream csNetEveryYearEq) {
		this.csNetEveryYearEq = csNetEveryYearEq;
	}

	public CashStream getCanDominateMoney() {
		return canDominateMoney;
	}

	public void setCanDominateMoney(CashStream canDominateMoney) {
		this.canDominateMoney = canDominateMoney;
	}

	public CashStream getYearInterestRate() {
		return yearInterestRate;
	}

	public void setYearInterestRate(CashStream yearInterestRate) {
		this.yearInterestRate = yearInterestRate;
	}

	public CashStream getLoanDeadline() {
		return loanDeadline;
	}

	public void setLoanDeadline(CashStream loanDeadline) {
		this.loanDeadline = loanDeadline;
	}

	public CashStream getEqRepaymentPeriod() {
		return eqRepaymentPeriod;
	}

	public void setEqRepaymentPeriod(CashStream eqRepaymentPeriod) {
		this.eqRepaymentPeriod = eqRepaymentPeriod;
	}

	public CashStream getApplyLoanSum() {
		return applyLoanSum;
	}

	public void setApplyLoanSum(CashStream applyLoanSum) {
		this.applyLoanSum = applyLoanSum;
	}

	public CashStream getMaxLoanSum() {
		return maxLoanSum;
	}

	public void setMaxLoanSum(CashStream maxLoanSum) {
		this.maxLoanSum = maxLoanSum;
	}

	public CashStream getCsAdjCoefficient() {
		return csAdjCoefficient;
	}

	public void setCsAdjCoefficient(CashStream csAdjCoefficient) {
		this.csAdjCoefficient = csAdjCoefficient;
	}

	public List<CashStream> getCsOperOutListChild() {
		return csOperOutListChild;
	}

	public void setCsOperOutListChild(List<CashStream> csOperOutListChild) {
		this.csOperOutListChild = csOperOutListChild;
	}

}

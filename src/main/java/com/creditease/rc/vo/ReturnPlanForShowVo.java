package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.ReturnPlan;

/**
 * 
 * @author haoqiang
 * 
 */
public class ReturnPlanForShowVo {
	private Double biggestRepaymentAmount;// 最大还款金额
	private Double total;// 总应收
	private Double penalbond;// 一次性提前还款产生的违约金
	private Double surplusPrincipal;// 未来的本金
	private Double behindInterests;// 未来的利息
	private Double charge;// 未来的服务费
	private Double currPrincipal;// 当期的本金
	private Double currInterest;// 当期的利息
	private Double currCharge;// 当期的服务费
	private Double currAmount;// 当期应收总金额
	private boolean overFlag;// 逾期标识
	private Double overAmount;// 逾期总应收
	private Double lastAmout;// 近期应收
	private List<ReturnPlan> returnPlanList;// 还款计划List

	public Double getLastAmout() {
		return lastAmout;
	}

	public void setLastAmout(Double lastAmout) {
		this.lastAmout = lastAmout;
	}

	public Double getOverAmount() {
		return overAmount;
	}

	public void setOverAmount(Double overAmount) {
		this.overAmount = overAmount;
	}

	public boolean isOverFlag() {
		return overFlag;
	}

	public void setOverFlag(boolean overFlag) {
		this.overFlag = overFlag;
	}

	public Double getBiggestRepaymentAmount() {
		return biggestRepaymentAmount;
	}

	public void setBiggestRepaymentAmount(Double biggestRepaymentAmount) {
		this.biggestRepaymentAmount = biggestRepaymentAmount;
	}

	public Double getCurrAmount() {
		return currAmount;
	}

	public void setCurrAmount(Double currAmount) {
		this.currAmount = currAmount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getPenalbond() {
		return penalbond;
	}

	public void setPenalbond(Double penalbond) {
		this.penalbond = penalbond;
	}

	public Double getSurplusPrincipal() {
		return surplusPrincipal;
	}

	public void setSurplusPrincipal(Double surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}

	public Double getBehindInterests() {
		return behindInterests;
	}

	public void setBehindInterests(Double behindInterests) {
		this.behindInterests = behindInterests;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getCurrPrincipal() {
		return currPrincipal;
	}

	public void setCurrPrincipal(Double currPrincipal) {
		this.currPrincipal = currPrincipal;
	}

	public Double getCurrInterest() {
		return currInterest;
	}

	public void setCurrInterest(Double currInterest) {
		this.currInterest = currInterest;
	}

	public Double getCurrCharge() {
		return currCharge;
	}

	public void setCurrCharge(Double currCharge) {
		this.currCharge = currCharge;
	}

	public List<ReturnPlan> getReturnPlanList() {
		return returnPlanList;
	}

	public void setReturnPlanList(List<ReturnPlan> returnPlanList) {
		this.returnPlanList = returnPlanList;
	}

}

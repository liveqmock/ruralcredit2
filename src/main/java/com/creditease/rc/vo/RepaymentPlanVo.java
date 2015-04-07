package com.creditease.rc.vo;

import com.creditease.rc.domain.RepaymentPlan;

/**
 * 
 * @author haoqiang
 * 
 */
public class RepaymentPlanVo extends RepaymentPlan {
	private Double nominalInterestRateVo;
	private Double firstServiceFreeVo;
	private Double followupServiceFreeVo;
	
	
	public Double getNominalInterestRateVo() {
		return nominalInterestRateVo;
	}
	public void setNominalInterestRateVo(Double nominalInterestRateVo) {
		this.nominalInterestRateVo = nominalInterestRateVo;
	}
	public Double getFirstServiceFreeVo() {
		return firstServiceFreeVo;
	}
	public void setFirstServiceFreeVo(Double firstServiceFreeVo) {
		this.firstServiceFreeVo = firstServiceFreeVo;
	}
	public Double getFollowupServiceFreeVo() {
		return followupServiceFreeVo;
	}
	public void setFollowupServiceFreeVo(Double followupServiceFreeVo) {
		this.followupServiceFreeVo = followupServiceFreeVo;
	}

}

package com.creditease.rc.vo;

import com.creditease.rc.domain.RepaymentPlanItem;

/**
 * 
 * @author haoqiang
 * 
 */
public class RepaymentPlanItemVo extends RepaymentPlanItem {
	private Double principalVo;
	private Double interestVo;
	private Double serviceFreeVo;

	public Double getPrincipalVo() {
		return principalVo;
	}
	public void setPrincipalVo(Double principalVo) {
		this.principalVo = principalVo;
	}
	public Double getInterestVo() {
		return interestVo;
	}
	public void setInterestVo(Double interestVo) {
		this.interestVo = interestVo;
	}
	public Double getServiceFreeVo() {
		return serviceFreeVo;
	}
	public void setServiceFreeVo(Double serviceFreeVo) {
		this.serviceFreeVo = serviceFreeVo;
	}

}

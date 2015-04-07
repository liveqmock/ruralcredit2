package com.creditease.rc.vo;

import java.math.BigDecimal;
import java.util.List;

import com.creditease.rc.domain.ReturnPlan;

public class AdvancePlanVo {

	private Double serviceCharge; 				//前期服务费
	private Double actualAmount;				//实际到手金额
	private List<ReturnPlan> returnPlanList;	//还款计划			
	public List<ReturnPlan> getReturnPlanList() {
		return returnPlanList;
	}
	public void setReturnPlanList(List<ReturnPlan> returnPlanList) {
		this.returnPlanList = returnPlanList;
	}
	public Double getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}
	public Double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
}

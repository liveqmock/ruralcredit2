package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.List;

import com.creditease.rc.app.pdf.ChargeInfo;
import com.creditease.rc.app.pdf.PeriodSchedule;
import com.creditease.rc.app.pdf.RateReqResult.FrontChargeList;

/**
 * 
 * Title:ChargePeriodScheduleInfo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-2-27上午11:12:51
 * @author 王毅峰
 * @version v2.0
 */
public class ChargePeriodScheduleInfo2 {
	private BigDecimal recvMoney;
	private String resultCode;
	private String resultMsg;
	private List<PeriodSchedule> periodSchedules;
	private FrontChargeList eChargeInfos;
	
	public BigDecimal getRecvMoney() {
		return recvMoney;
	}
	public void setRecvMoney(BigDecimal recvMoney) {
		this.recvMoney = recvMoney;
	}
	public List<PeriodSchedule> getPeriodSchedules() {
		return periodSchedules;
	}
	public void setPeriodSchedules(List<PeriodSchedule> periodSchedules) {
		this.periodSchedules = periodSchedules;
	}
	public FrontChargeList geteChargeInfos() {
		return eChargeInfos;
	}
	public void seteChargeInfos(FrontChargeList eChargeInfos) {
		this.eChargeInfos = eChargeInfos;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
}

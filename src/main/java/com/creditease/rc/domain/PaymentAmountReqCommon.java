package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约还款计算请求参数
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-3-22下午06:54:04
 * @author 王毅峰
 * @version v2.0
 */
public class PaymentAmountReqCommon {
	/**是否全部提前还款**/
	private boolean aLLAhead;
	/** 预约还款时间 **/
	private String appointmentDate;
	/**审核日期**/
	private String auditDate;
	/** 产品分类编号 **/
	private Long catagoryId;
	/** 合同金额 **/
	private BigDecimal contractMoney;
	/** 营业部编号 **/
	private Long departmentId;
	/** 上一次计算逾期费用时间 **/
	private String prevCalcDate;
	/** 放款日期 **/
	private String lenderDate;
	/** 请求系统日期 **/
	private String reqSysDate;
	/** 分期数 **/
	private int periodCount;
	/** 产品版本编号 **/
	private Long productInfoId;
	
	public boolean isaLLAhead() {
		return aLLAhead;
	}
	public void setaLLAhead(boolean aLLAhead) {
		this.aLLAhead = aLLAhead;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public Long getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
	}
	public BigDecimal getContractMoney() {
		return contractMoney;
	}
	public void setContractMoney(BigDecimal contractMoney) {
		this.contractMoney = contractMoney;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getPrevCalcDate() {
		return prevCalcDate;
	}
	public void setPrevCalcDate(String prevCalcDate) {
		this.prevCalcDate = prevCalcDate;
	}
	public String getLenderDate() {
		return lenderDate;
	}
	public void setLenderDate(String lenderDate) {
		this.lenderDate = lenderDate;
	}
	public String getReqSysDate() {
		return reqSysDate;
	}
	public void setReqSysDate(String reqSysDate) {
		this.reqSysDate = reqSysDate;
	}
	public int getPeriodCount() {
		return periodCount;
	}
	public void setPeriodCount(int periodCount) {
		this.periodCount = periodCount;
	}
	public Long getProductInfoId() {
		return productInfoId;
	}
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}

}

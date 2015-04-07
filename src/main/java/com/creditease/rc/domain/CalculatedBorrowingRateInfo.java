package com.creditease.rc.domain                ;

import java.math.BigDecimal                ;
import java.util.Date                ;

import org.codehaus.jackson.map.annotate.JsonSerialize                ;

import com.creditease.rc.common.JsonYMDDateSerializer                ;

/**
 * 
 * Title:CalculatedBorrowingRateInfo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-2-27上午10:03:27
 * @author 王毅峰
 * @version v2.0
 */
public class CalculatedBorrowingRateInfo {
	private Long 			departmentId                ;//3 部门ID ptiD
	private Long 			catagoryId                ;//1 产品大类
	private Date 			auditDate                ;//4 审批通过时间
	private Date 			reqDate                ;
	private Long 			productInfoId                ;//产品版本编号 RTid
    private BigDecimal 		contractMoney                ;//2合同金额
    private int 			periodCount                ;//7分期数
    private Date 			lenderDate                ;//6请求放款日期
    private String 			paymentTypeCode                ;//5还款方式
    private String			paramName                ;  //参数名称
    private BigDecimal 		paramValue                ;//参数值
    
	public Long getDepartmentId() {
		return departmentId                ;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId                ;
	}
	public Long getCatagoryId() {
		return catagoryId                ;
	}
	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId                ;
	}
	public Date getAuditDate() {
		return auditDate                ;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate                ;
	}
	public Long getProductInfoId() {
		return productInfoId                ;
	}
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId                ;
	}
	public BigDecimal getContractMoney() {
		return contractMoney                ;
	}
	public void setContractMoney(BigDecimal contractMoney) {
		this.contractMoney = contractMoney                ;
	}
	public int getPeriodCount() {
		return periodCount                ;
	}
	public void setPeriodCount(int periodCount) {
		this.periodCount = periodCount                ;
	}
	public Date getLenderDate() {
		return lenderDate                ;
	}
	public void setLenderDate(Date lenderDate) {
		this.lenderDate = lenderDate                ;
	}
	public String getPaymentTypeCode() {
		return paymentTypeCode                ;
	}
	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode                ;
	}
	public String getParamName() {
		return paramName                ;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName                ;
	}
	public BigDecimal getParamValue() {
		return paramValue                ;
	}
	public void setParamValue(BigDecimal paramValue) {
		this.paramValue = paramValue                ;
	}
	public Date getReqDate() {
		return reqDate                ;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate                ;
	}
}

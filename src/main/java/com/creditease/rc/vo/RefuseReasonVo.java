package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.domain.RefuseReason;

public class RefuseReasonVo extends RefuseReason {
	private Long creditapplicationId;		// 信贷申请单ID外键
	private String businessNumber;   //编号
	private String name;			 //借款人姓名
	private String companyId;		//营业部ID
	private String companyName;		//公司名称
	private String operatorName;    //操作人姓名
	private Date operateDate;		//操作时间
	private String refuseReasons;   //客户放弃原因
	private String auditStatus;		//当前的业务状态
	private String auditStatusShow; //显示的当前业务状态
	private String fxid;			//风险经理id
	
	
	
	public String getFxid() {
		return fxid;
	}
	public void setFxid(String fxid) {
		this.fxid = fxid;
	}
	public String getAuditStatusShow() {
		return auditStatusShow;
	}
	public void setAuditStatusShow(String auditStatusShow) {
		this.auditStatusShow = auditStatusShow;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getRefuseReasons() {
		return refuseReasons;
	}
	public void setRefuseReasons(String refuseReasons) {
		this.refuseReasons = refuseReasons;
	}
	
	
}

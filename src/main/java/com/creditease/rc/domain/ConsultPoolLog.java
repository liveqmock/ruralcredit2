package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销咨询-业务日志表-RL_CONSULT_POOL_DISTRIBUTE
 * User: zhangwei
 * Date: 14-5-12
 * Time: 上午10:21
 */
public class ConsultPoolLog implements Serializable {

	private Long consultPoolDistributeId; // 主键ID
	private String businessType; // 业务操作类型，目前为分配、分件两类操作
	private String operatorId; // 操作人ID
	private String operatorName; // 操作人姓名
	private Date operateDate; // 操作时间
	private String remark; // 备注信息
	private String distributeType; // 分配类型：手动 自动

	public Long getConsultPoolDistributeId() {
		return consultPoolDistributeId;
	}

	public void setConsultPoolDistributeId(Long consultPoolDistributeId) {
		this.consultPoolDistributeId = consultPoolDistributeId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDistributeType() {
		return distributeType;
	}

	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}
}

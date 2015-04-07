package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 营销管理-业务日志详情表-RL_CONSULT_POOL_DIS_DETAIL
 * User: zhangwei
 * Date: 14-5-19
 * Time: 下午5:19
 */
public class ConsultPoolLogDetail implements Serializable {

    private Long consultPoolDisDetailId; // 主键ID
    private Long consultPoolDistributeId; // 外键ID （业务日志表：RL_CONSULT_POOL_DISTRIBUTE）
    private Long consultPoolId; // 咨询池记录ID
    private Long distributeDepartmentId; // 分配营业部ID
    private String distributeDepartmentName; // 分配营业部名称
    private Long distributeLoanOfficerId; // 信贷员ID
    private String distributeLoanOfficerName; // 信贷员名称


    
    public Long getConsultPoolDisDetailId() {
		return consultPoolDisDetailId;
	}

	public void setConsultPoolDisDetailId(Long consultPoolDisDetailId) {
		this.consultPoolDisDetailId = consultPoolDisDetailId;
	}

	public Long getConsultPoolDistributeId() {
		return consultPoolDistributeId;
	}

	public void setConsultPoolDistributeId(Long consultPoolDistributeId) {
		this.consultPoolDistributeId = consultPoolDistributeId;
	}

	public Long getConsultPoolId() {
        return consultPoolId;
    }

    public void setConsultPoolId(Long consultPoolId) {
        this.consultPoolId = consultPoolId;
    }


    public String getDistributeDepartmentName() {
        return distributeDepartmentName;
    }

    public void setDistributeDepartmentName(String distributeDepartmentName) {
        this.distributeDepartmentName = distributeDepartmentName;
    }


    public Long getDistributeDepartmentId() {
		return distributeDepartmentId;
	}

	public void setDistributeDepartmentId(Long distributeDepartmentId) {
		this.distributeDepartmentId = distributeDepartmentId;
	}

	public Long getDistributeLoanOfficerId() {
		return distributeLoanOfficerId;
	}

	public void setDistributeLoanOfficerId(Long distributeLoanOfficerId) {
		this.distributeLoanOfficerId = distributeLoanOfficerId;
	}

	public String getDistributeLoanOfficerName() {
        return distributeLoanOfficerName;
    }

    public void setDistributeLoanOfficerName(String distributeLoanOfficerName) {
        this.distributeLoanOfficerName = distributeLoanOfficerName;
    }
}

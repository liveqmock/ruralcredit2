package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHmsDateSerializer;

public class SpecialApply {
	private Long specialApplyId; 			// 特殊申请ID
	private Long creditapplicationId;		// 信贷申请单ID外键
	private String specialApplyType;		// 特殊申请类别
	private String specialApplyState;		// 特殊申请时状态
	private String specialApplyViewContent;	// 特殊申请详细内容
	private Long auditorId;					// 审批人ID
	private String auditorName;				// 审批人姓名
	private Date auditTime;					// 审批时间
	private Date applyTime;					// 申请时间
	private String auditSpecialApplyState;  //特殊情况审批状态
	private Long proposerId;				//申请人ID
	private String proposerName;			// 申请人姓名
	private String businessState;			// 业务状态
	private String specialApplyComment;		//特殊申请备注
	
	private String specialApproveComment;  // 特殊申请审批备注
	private String customerManagerPhone;	//客户经理电话
	
	
	
    public String getCustomerManagerPhone() {
		return customerManagerPhone;
	}

	public void setCustomerManagerPhone(String customerManagerPhone) {
		this.customerManagerPhone = customerManagerPhone;
	}

	public String getSpecialApproveComment() {
		return specialApproveComment;
	}

	public void setSpecialApproveComment(String specialApproveComment) {
		this.specialApproveComment = specialApproveComment;
	}

	public String getSpecialApplyComment() {
		return specialApplyComment;
	}

	public void setSpecialApplyComment(String specialApplyComment) {
		this.specialApplyComment = specialApplyComment;
	}

	public String getAuditSpecialApplyState() {
		return auditSpecialApplyState;
	}

	public void setAuditSpecialApplyState(String auditSpecialApplyState) {
		this.auditSpecialApplyState = auditSpecialApplyState;
	}

	public Long getProposerId() {
		return proposerId;
	}

	public void setProposerId(Long proposerId) {
		this.proposerId = proposerId;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_ID
     *
     * @return the value of RL_SPECIAL_APPLY.SPECIAL_APPLY_ID
     *
     * @abatorgenerated
     */
    public Long getSpecialApplyId() {
        return specialApplyId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_ID
     *
     * @param specialApplyId the value for RL_SPECIAL_APPLY.SPECIAL_APPLY_ID
     *
     * @abatorgenerated
     */
    public void setSpecialApplyId(Long specialApplyId) {
        this.specialApplyId = specialApplyId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.CREDITAPPLICATION_ID
     *
     * @return the value of RL_SPECIAL_APPLY.CREDITAPPLICATION_ID
     *
     * @abatorgenerated
     */
    public Long getCreditapplicationId() {
        return creditapplicationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.CREDITAPPLICATION_ID
     *
     * @param creditapplicationId the value for RL_SPECIAL_APPLY.CREDITAPPLICATION_ID
     *
     * @abatorgenerated
     */
    public void setCreditapplicationId(Long creditapplicationId) {
        this.creditapplicationId = creditapplicationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_TYPE
     *
     * @return the value of RL_SPECIAL_APPLY.SPECIAL_APPLY_TYPE
     *
     * @abatorgenerated
     */
    public String getSpecialApplyType() {
        return specialApplyType;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_TYPE
     *
     * @param specialApplyType the value for RL_SPECIAL_APPLY.SPECIAL_APPLY_TYPE
     *
     * @abatorgenerated
     */
    public void setSpecialApplyType(String specialApplyType) {
        this.specialApplyType = specialApplyType == null ? null : specialApplyType.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_STATE
     *
     * @return the value of RL_SPECIAL_APPLY.SPECIAL_APPLY_STATE
     *
     * @abatorgenerated
     */
    public String getSpecialApplyState() {
        return specialApplyState;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_STATE
     *
     * @param specialApplyState the value for RL_SPECIAL_APPLY.SPECIAL_APPLY_STATE
     *
     * @abatorgenerated
     */
    public void setSpecialApplyState(String specialApplyState) {
        this.specialApplyState = specialApplyState == null ? null : specialApplyState.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_VIEW_CONTENT
     *
     * @return the value of RL_SPECIAL_APPLY.SPECIAL_APPLY_VIEW_CONTENT
     *
     * @abatorgenerated
     */
    public String getSpecialApplyViewContent() {
        return specialApplyViewContent;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.SPECIAL_APPLY_VIEW_CONTENT
     *
     * @param specialApplyViewContent the value for RL_SPECIAL_APPLY.SPECIAL_APPLY_VIEW_CONTENT
     *
     * @abatorgenerated
     */
    public void setSpecialApplyViewContent(String specialApplyViewContent) {
        this.specialApplyViewContent = specialApplyViewContent == null ? null : specialApplyViewContent.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.AUDITOR_ID
     *
     * @return the value of RL_SPECIAL_APPLY.AUDITOR_ID
     *
     * @abatorgenerated
     */
    public Long getAuditorId() {
        return auditorId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.AUDITOR_ID
     *
     * @param auditorId the value for RL_SPECIAL_APPLY.AUDITOR_ID
     *
     * @abatorgenerated
     */
    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.AUDITOR_NAME
     *
     * @return the value of RL_SPECIAL_APPLY.AUDITOR_NAME
     *
     * @abatorgenerated
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.AUDITOR_NAME
     *
     * @param auditorName the value for RL_SPECIAL_APPLY.AUDITOR_NAME
     *
     * @abatorgenerated
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.AUDIT_TIME
     *
     * @return the value of RL_SPECIAL_APPLY.AUDIT_TIME
     *
     * @abatorgenerated
     */
    @JsonSerialize(using = JsonYMDHmsDateSerializer.class)
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.AUDIT_TIME
     *
     * @param auditTime the value for RL_SPECIAL_APPLY.AUDIT_TIME
     *
     * @abatorgenerated
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.APPLY_TIME
     *
     * @return the value of RL_SPECIAL_APPLY.APPLY_TIME
     *
     * @abatorgenerated
     */
    @JsonSerialize(using = JsonYMDHmsDateSerializer.class)
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.APPLY_TIME
     *
     * @param applyTime the value for RL_SPECIAL_APPLY.APPLY_TIME
     *
     * @abatorgenerated
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.PROPOSER_NAME
     *
     * @return the value of RL_SPECIAL_APPLY.PROPOSER_NAME
     *
     * @abatorgenerated
     */
    public String getProposerName() {
        return proposerName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.PROPOSER_NAME
     *
     * @param proposerName the value for RL_SPECIAL_APPLY.PROPOSER_NAME
     *
     * @abatorgenerated
     */
    public void setProposerName(String proposerName) {
        this.proposerName = proposerName == null ? null : proposerName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_SPECIAL_APPLY.BUSINESS_STATE
     *
     * @return the value of RL_SPECIAL_APPLY.BUSINESS_STATE
     *
     * @abatorgenerated
     */
    public String getBusinessState() {
        return businessState;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_SPECIAL_APPLY.BUSINESS_STATE
     *
     * @param businessState the value for RL_SPECIAL_APPLY.BUSINESS_STATE
     *
     * @abatorgenerated
     */
    public void setBusinessState(String businessState) {
        this.businessState = businessState == null ? null : businessState.trim();
    }
}
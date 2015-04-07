package com.creditease.rc.domain;

import java.util.Date;

public class BusinessStatusChange {
    /**
     * CHANGE_ID
     *
     * 主键ID
     */
    private Long changeId;

    /**
     * CREDITAPPLICATION_ID
     *
     * 信贷申请ID
     */
    private Long creditapplicationId;

    /**
     * OPERATOR
     *
     * 操作人
     */
    private String operator;

    /**
     * OPERATE_DATE
     *
     * 操作时间
     */
    private Date operateDate;

    /**
     * CHANGE_REASON
     *
     * 变更原因
     */
    private String changeReason;

    /**
     * BEFORE_STATUS
     *
     * 变更前状态
     */
    private String beforeStatus;

    /**
     * AFTER_STATUS
     *
     * 变更后状态
     */
    private String afterStatus;

    /**
     * OPERATE_TYPE
     *
     * 操作类型：0 不良贷款、1 法律诉讼
     */
    private String operateType;

    /**
     * HISTORY_FLAG
     *
     * 历史记录
     */
    private String historyFlag;

    /**
     *
     * 备注
     */
    private String remark;

    /**
     * 附件
     */
    private String attachmentId;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getChangeId() {
        return changeId;
    }

    public void setChangeId(Long changeId) {
        this.changeId = changeId;
    }

    public Long getCreditapplicationId() {
        return creditapplicationId;
    }

    public void setCreditapplicationId(Long creditapplicationId) {
        this.creditapplicationId = creditapplicationId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public String getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(String afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getHistoryFlag() {
        return historyFlag;
    }

    public void setHistoryFlag(String historyFlag) {
        this.historyFlag = historyFlag;
    }
}
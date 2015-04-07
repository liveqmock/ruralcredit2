package com.creditease.rc.domain;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 17:36
 */
public class ComplianceCheck {

    /*对象*/
    private String complianceObject;

    /*系统资料*/
    private String systemMaterial;

    /*检查点*/
    private String checkPoint;

    /*操作人ID*/
    private String operatorId;

    /*操作人NAME*/
    private String operatorName;

    /*操作时间*/
    private Date operateDate;

    /*备注/意见*/
    private String checkRemark;

    /*信贷申请ID*/
    private Long creditApplicationId;

    /*检查ID*/
    private Long complianceCheckId;

    /*检查项ID*/
    private Long complianceCheckItemId;

    /*错误文件*/
    private String errorFile;

    /*检查点分数*/
    private Double complianceCheckPointScore;

    /*检查点分数配置ID*/
    private Long complianceCheckPointConfigId;

    /*检查点类型*/
    private String complianceCheckPointType;

    /*检查分数ID*/
    private Long complianceCheckScoreId;

    /*检查分数类型*/
    private String complianceCheckScoreType;

    /*申诉ID*/
    private Long complaintId;

    /*申诉状态*/
    private String complaintStatus;

    /*申诉内容*/
    private String complaintContent;

    /*审批备注*/
    private String approveContent;

    /*申诉审批ID*/
    private Long complaintApproveId;

    public String getComplianceObject() {
        return complianceObject;
    }

    public void setComplianceObject(String complianceObject) {
        this.complianceObject = complianceObject;
    }

    public String getSystemMaterial() {
        return systemMaterial;
    }

    public void setSystemMaterial(String systemMaterial) {
        this.systemMaterial = systemMaterial;
    }

    public String getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(String checkPoint) {
        this.checkPoint = checkPoint;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @JsonSerialize(using = JsonYMDHMDDateSerializer.class)
    public Date getOperateDate() {
        return operateDate;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public Long getCreditApplicationId() {
        return creditApplicationId;
    }

    public void setCreditApplicationId(Long creditApplicationId) {
        this.creditApplicationId = creditApplicationId;
    }

    public Long getComplianceCheckId() {
        return complianceCheckId;
    }

    public void setComplianceCheckId(Long complianceCheckId) {
        this.complianceCheckId = complianceCheckId;
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

    public Long getComplianceCheckItemId() {
        return complianceCheckItemId;
    }

    public void setComplianceCheckItemId(Long complianceCheckItemId) {
        this.complianceCheckItemId = complianceCheckItemId;
    }

    public String getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(String errorFile) {
        this.errorFile = errorFile;
    }

    public Double getComplianceCheckPointScore() {
        return complianceCheckPointScore;
    }

    public void setComplianceCheckPointScore(Double complianceCheckPointScore) {
        this.complianceCheckPointScore = complianceCheckPointScore;
    }

    public Long getComplianceCheckPointConfigId() {
        return complianceCheckPointConfigId;
    }

    public void setComplianceCheckPointConfigId(Long complianceCheckPointConfigId) {
        this.complianceCheckPointConfigId = complianceCheckPointConfigId;
    }

    public String getComplianceCheckPointType() {
        return complianceCheckPointType;
    }

    public void setComplianceCheckPointType(String complianceCheckPointType) {
        this.complianceCheckPointType = complianceCheckPointType;
    }

    public Long getComplianceCheckScoreId() {
        return complianceCheckScoreId;
    }

    public void setComplianceCheckScoreId(Long complianceCheckScoreId) {
        this.complianceCheckScoreId = complianceCheckScoreId;
    }

    public String getComplianceCheckScoreType() {
        return complianceCheckScoreType;
    }

    public void setComplianceCheckScoreType(String complianceCheckScoreType) {
        this.complianceCheckScoreType = complianceCheckScoreType;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

    public Long getComplaintApproveId() {
        return complaintApproveId;
    }

    public void setComplaintApproveId(Long complaintApproveId) {
        this.complaintApproveId = complaintApproveId;
    }
}

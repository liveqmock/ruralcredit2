package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 */
public class ReturnVisit {
    private Long returnvisitId;
    private Long borrowerId;
    private Date contactTime;
    private String contactWay;
    private String clienteleAttitude;
    private String customManager;
    private String surveyRecode;
    private String borrowerName;
    private String groupNumber;
    private String contactChange;
    private String addressChange;
    private String workingChange;
    private String monthIncome;
    private String vastexpenseReason;
    private String paypressureReason;
    private String familymembersChange;
    private String otherFamilystatusReason;
    private String otherWorkingChange;
    private String otherVastexpenseReason;
    private String otherPaypressureReason;
    private String keytoFollowup;
    private String contactChangeDetail;
    private String addressChangeDetail;
    private String workingChangeDetail;
    private String monthIncomeDetail;
    private String vastexpenseReasonDetail;
    private String paypressureReasonDetail;
    private String familymembersChangeDetail;
    private String otherFamilystatusDetail;
    private String otherWorkingDetail;
    private String otherVastexpenseDetail;
    private String otherPaypressureDetail;
    private String keytoFollowupDetail;
    public Long getReturnvisitId() {
        return returnvisitId;
    }

    public void setReturnvisitId(Long returnvisitId) {
        this.returnvisitId = returnvisitId;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getContactTime() {
        return contactTime;
    }

    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }
    public String getContactWay() {
        return contactWay;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.CONTACT_WAY
     *
     * @param contactWay the value for RL_RETURN_VISIT.CONTACT_WAY
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay == null ? null : contactWay.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.CLIENTELE_ATTITUDE
     *
     * @return the value of RL_RETURN_VISIT.CLIENTELE_ATTITUDE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getClienteleAttitude() {
        return clienteleAttitude;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.CLIENTELE_ATTITUDE
     *
     * @param clienteleAttitude the value for RL_RETURN_VISIT.CLIENTELE_ATTITUDE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setClienteleAttitude(String clienteleAttitude) {
        this.clienteleAttitude = clienteleAttitude == null ? null : clienteleAttitude.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.CUSTOM_MANAGER
     *
     * @return the value of RL_RETURN_VISIT.CUSTOM_MANAGER
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getCustomManager() {
        return customManager;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.CUSTOM_MANAGER
     *
     * @param customManager the value for RL_RETURN_VISIT.CUSTOM_MANAGER
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setCustomManager(String customManager) {
        this.customManager = customManager == null ? null : customManager.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.SURVEY_RECODE
     *
     * @return the value of RL_RETURN_VISIT.SURVEY_RECODE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getSurveyRecode() {
        return surveyRecode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.SURVEY_RECODE
     *
     * @param surveyRecode the value for RL_RETURN_VISIT.SURVEY_RECODE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setSurveyRecode(String surveyRecode) {
        this.surveyRecode = surveyRecode == null ? null : surveyRecode.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.BORROWER_NAME
     *
     * @return the value of RL_RETURN_VISIT.BORROWER_NAME
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getBorrowerName() {
        return borrowerName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.BORROWER_NAME
     *
     * @param borrowerName the value for RL_RETURN_VISIT.BORROWER_NAME
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName == null ? null : borrowerName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.GROUP_NUMBER
     *
     * @return the value of RL_RETURN_VISIT.GROUP_NUMBER
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getGroupNumber() {
        return groupNumber;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.GROUP_NUMBER
     *
     * @param groupNumber the value for RL_RETURN_VISIT.GROUP_NUMBER
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber == null ? null : groupNumber.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.CONTACT_CHANGE
     *
     * @return the value of RL_RETURN_VISIT.CONTACT_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getContactChange() {
        return contactChange;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.CONTACT_CHANGE
     *
     * @param contactChange the value for RL_RETURN_VISIT.CONTACT_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setContactChange(String contactChange) {
        this.contactChange = contactChange == null ? null : contactChange.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.ADDRESS_CHANGE
     *
     * @return the value of RL_RETURN_VISIT.ADDRESS_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getAddressChange() {
        return addressChange;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.ADDRESS_CHANGE
     *
     * @param addressChange the value for RL_RETURN_VISIT.ADDRESS_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setAddressChange(String addressChange) {
        this.addressChange = addressChange == null ? null : addressChange.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.WORKING_CHANGE
     *
     * @return the value of RL_RETURN_VISIT.WORKING_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getWorkingChange() {
        return workingChange;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.WORKING_CHANGE
     *
     * @param workingChange the value for RL_RETURN_VISIT.WORKING_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setWorkingChange(String workingChange) {
        this.workingChange = workingChange == null ? null : workingChange.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.MONTH_INCOME
     *
     * @return the value of RL_RETURN_VISIT.MONTH_INCOME
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getMonthIncome() {
        return monthIncome;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.MONTH_INCOME
     *
     * @param monthIncome the value for RL_RETURN_VISIT.MONTH_INCOME
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome == null ? null : monthIncome.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.VASTEXPENSE_REASON
     *
     * @return the value of RL_RETURN_VISIT.VASTEXPENSE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getVastexpenseReason() {
        return vastexpenseReason;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.VASTEXPENSE_REASON
     *
     * @param vastexpenseReason the value for RL_RETURN_VISIT.VASTEXPENSE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setVastexpenseReason(String vastexpenseReason) {
        this.vastexpenseReason = vastexpenseReason == null ? null : vastexpenseReason.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.PAYPRESSURE_REASON
     *
     * @return the value of RL_RETURN_VISIT.PAYPRESSURE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getPaypressureReason() {
        return paypressureReason;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.PAYPRESSURE_REASON
     *
     * @param paypressureReason the value for RL_RETURN_VISIT.PAYPRESSURE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setPaypressureReason(String paypressureReason) {
        this.paypressureReason = paypressureReason == null ? null : paypressureReason.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE
     *
     * @return the value of RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getFamilymembersChange() {
        return familymembersChange;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE
     *
     * @param familymembersChange the value for RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setFamilymembersChange(String familymembersChange) {
        this.familymembersChange = familymembersChange == null ? null : familymembersChange.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_FAMILYSTATUS_REASON
     *
     * @return the value of RL_RETURN_VISIT.OTHER_FAMILYSTATUS_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherFamilystatusReason() {
        return otherFamilystatusReason;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_FAMILYSTATUS_REASON
     *
     * @param otherFamilystatusReason the value for RL_RETURN_VISIT.OTHER_FAMILYSTATUS_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherFamilystatusReason(String otherFamilystatusReason) {
        this.otherFamilystatusReason = otherFamilystatusReason == null ? null : otherFamilystatusReason.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_WORKING_CHANGE
     *
     * @return the value of RL_RETURN_VISIT.OTHER_WORKING_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherWorkingChange() {
        return otherWorkingChange;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_WORKING_CHANGE
     *
     * @param otherWorkingChange the value for RL_RETURN_VISIT.OTHER_WORKING_CHANGE
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherWorkingChange(String otherWorkingChange) {
        this.otherWorkingChange = otherWorkingChange == null ? null : otherWorkingChange.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_VASTEXPENSE_REASON
     *
     * @return the value of RL_RETURN_VISIT.OTHER_VASTEXPENSE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherVastexpenseReason() {
        return otherVastexpenseReason;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_VASTEXPENSE_REASON
     *
     * @param otherVastexpenseReason the value for RL_RETURN_VISIT.OTHER_VASTEXPENSE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherVastexpenseReason(String otherVastexpenseReason) {
        this.otherVastexpenseReason = otherVastexpenseReason == null ? null : otherVastexpenseReason.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_PAYPRESSURE_REASON
     *
     * @return the value of RL_RETURN_VISIT.OTHER_PAYPRESSURE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherPaypressureReason() {
        return otherPaypressureReason;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_PAYPRESSURE_REASON
     *
     * @param otherPaypressureReason the value for RL_RETURN_VISIT.OTHER_PAYPRESSURE_REASON
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherPaypressureReason(String otherPaypressureReason) {
        this.otherPaypressureReason = otherPaypressureReason == null ? null : otherPaypressureReason.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.KEYTO_FOLLOWUP
     *
     * @return the value of RL_RETURN_VISIT.KEYTO_FOLLOWUP
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getKeytoFollowup() {
        return keytoFollowup;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.KEYTO_FOLLOWUP
     *
     * @param keytoFollowup the value for RL_RETURN_VISIT.KEYTO_FOLLOWUP
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setKeytoFollowup(String keytoFollowup) {
        this.keytoFollowup = keytoFollowup == null ? null : keytoFollowup.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.CONTACT_CHANGE_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.CONTACT_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getContactChangeDetail() {
        return contactChangeDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.CONTACT_CHANGE_DETAIL
     *
     * @param contactChangeDetail the value for RL_RETURN_VISIT.CONTACT_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setContactChangeDetail(String contactChangeDetail) {
        this.contactChangeDetail = contactChangeDetail == null ? null : contactChangeDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.ADDRESS_CHANGE_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.ADDRESS_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getAddressChangeDetail() {
        return addressChangeDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.ADDRESS_CHANGE_DETAIL
     *
     * @param addressChangeDetail the value for RL_RETURN_VISIT.ADDRESS_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setAddressChangeDetail(String addressChangeDetail) {
        this.addressChangeDetail = addressChangeDetail == null ? null : addressChangeDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.WORKING_CHANGE_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.WORKING_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getWorkingChangeDetail() {
        return workingChangeDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.WORKING_CHANGE_DETAIL
     *
     * @param workingChangeDetail the value for RL_RETURN_VISIT.WORKING_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setWorkingChangeDetail(String workingChangeDetail) {
        this.workingChangeDetail = workingChangeDetail == null ? null : workingChangeDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.MONTH_INCOME_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.MONTH_INCOME_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getMonthIncomeDetail() {
        return monthIncomeDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.MONTH_INCOME_DETAIL
     *
     * @param monthIncomeDetail the value for RL_RETURN_VISIT.MONTH_INCOME_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setMonthIncomeDetail(String monthIncomeDetail) {
        this.monthIncomeDetail = monthIncomeDetail == null ? null : monthIncomeDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.VASTEXPENSE_REASON_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.VASTEXPENSE_REASON_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getVastexpenseReasonDetail() {
        return vastexpenseReasonDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.VASTEXPENSE_REASON_DETAIL
     *
     * @param vastexpenseReasonDetail the value for RL_RETURN_VISIT.VASTEXPENSE_REASON_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setVastexpenseReasonDetail(String vastexpenseReasonDetail) {
        this.vastexpenseReasonDetail = vastexpenseReasonDetail == null ? null : vastexpenseReasonDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.PAYPRESSURE_REASON_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.PAYPRESSURE_REASON_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getPaypressureReasonDetail() {
        return paypressureReasonDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.PAYPRESSURE_REASON_DETAIL
     *
     * @param paypressureReasonDetail the value for RL_RETURN_VISIT.PAYPRESSURE_REASON_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setPaypressureReasonDetail(String paypressureReasonDetail) {
        this.paypressureReasonDetail = paypressureReasonDetail == null ? null : paypressureReasonDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getFamilymembersChangeDetail() {
        return familymembersChangeDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE_DETAIL
     *
     * @param familymembersChangeDetail the value for RL_RETURN_VISIT.FAMILYMEMBERS_CHANGE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setFamilymembersChangeDetail(String familymembersChangeDetail) {
        this.familymembersChangeDetail = familymembersChangeDetail == null ? null : familymembersChangeDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_FAMILYSTATUS_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.OTHER_FAMILYSTATUS_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherFamilystatusDetail() {
        return otherFamilystatusDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_FAMILYSTATUS_DETAIL
     *
     * @param otherFamilystatusDetail the value for RL_RETURN_VISIT.OTHER_FAMILYSTATUS_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherFamilystatusDetail(String otherFamilystatusDetail) {
        this.otherFamilystatusDetail = otherFamilystatusDetail == null ? null : otherFamilystatusDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_WORKING_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.OTHER_WORKING_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherWorkingDetail() {
        return otherWorkingDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_WORKING_DETAIL
     *
     * @param otherWorkingDetail the value for RL_RETURN_VISIT.OTHER_WORKING_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherWorkingDetail(String otherWorkingDetail) {
        this.otherWorkingDetail = otherWorkingDetail == null ? null : otherWorkingDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_VASTEXPENSE_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.OTHER_VASTEXPENSE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherVastexpenseDetail() {
        return otherVastexpenseDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_VASTEXPENSE_DETAIL
     *
     * @param otherVastexpenseDetail the value for RL_RETURN_VISIT.OTHER_VASTEXPENSE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherVastexpenseDetail(String otherVastexpenseDetail) {
        this.otherVastexpenseDetail = otherVastexpenseDetail == null ? null : otherVastexpenseDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.OTHER_PAYPRESSURE_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.OTHER_PAYPRESSURE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getOtherPaypressureDetail() {
        return otherPaypressureDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.OTHER_PAYPRESSURE_DETAIL
     *
     * @param otherPaypressureDetail the value for RL_RETURN_VISIT.OTHER_PAYPRESSURE_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setOtherPaypressureDetail(String otherPaypressureDetail) {
        this.otherPaypressureDetail = otherPaypressureDetail == null ? null : otherPaypressureDetail.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_RETURN_VISIT.KEYTO_FOLLOWUP_DETAIL
     *
     * @return the value of RL_RETURN_VISIT.KEYTO_FOLLOWUP_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public String getKeytoFollowupDetail() {
        return keytoFollowupDetail;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_RETURN_VISIT.KEYTO_FOLLOWUP_DETAIL
     *
     * @param keytoFollowupDetail the value for RL_RETURN_VISIT.KEYTO_FOLLOWUP_DETAIL
     *
     * @abatorgenerated Wed Apr 17 14:42:51 CST 2013
     */
    public void setKeytoFollowupDetail(String keytoFollowupDetail) {
        this.keytoFollowupDetail = keytoFollowupDetail == null ? null : keytoFollowupDetail.trim();
    }
}
package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
/**
 * 
 * cs
 * Title:DataBestowal.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:07:45
 * @author wyf
 * @version v2.0
 */
public class DataBestowal {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.DATA_BESTOWAL_ID
     *
     * @abatorgenerated
     */
    private Long dataBestowalId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_ID
     *
     * @abatorgenerated
     */
    private String oldLoanOfficerId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_NAME
     *
     * @abatorgenerated
     */
    private String oldLoanOfficerName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_ID
     *
     * @abatorgenerated
     */
    private String newLoanOfficerId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_NAME
     *
     * @abatorgenerated
     */
    private String newLoanOfficerName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.NOTE
     *
     * @abatorgenerated
     */
    private String note;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.OPERATING_ID
     *
     * @abatorgenerated
     */
    private String operatingId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.OPERATING_NAME
     *
     * @abatorgenerated
     */
    private String operatingName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_DATA_BESTOWAL.OPERATING_TIME
     *
     * @abatorgenerated
     */
    private Date operatingTime;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.DATA_BESTOWAL_ID
     *
     * @return the value of RL_DATA_BESTOWAL.DATA_BESTOWAL_ID
     *
     * @abatorgenerated
     */
    public Long getDataBestowalId() {
        return dataBestowalId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.DATA_BESTOWAL_ID
     *
     * @param dataBestowalId the value for RL_DATA_BESTOWAL.DATA_BESTOWAL_ID
     *
     * @abatorgenerated
     */
    public void setDataBestowalId(Long dataBestowalId) {
        this.dataBestowalId = dataBestowalId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_ID
     *
     * @return the value of RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_ID
     *
     * @abatorgenerated
     */
    public String getOldLoanOfficerId() {
        return oldLoanOfficerId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_ID
     *
     * @param oldLoanOfficerId the value for RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_ID
     *
     * @abatorgenerated
     */
    public void setOldLoanOfficerId(String oldLoanOfficerId) {
        this.oldLoanOfficerId = oldLoanOfficerId == null ? null : oldLoanOfficerId.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_NAME
     *
     * @return the value of RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_NAME
     *
     * @abatorgenerated
     */
    public String getOldLoanOfficerName() {
        return oldLoanOfficerName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_NAME
     *
     * @param oldLoanOfficerName the value for RL_DATA_BESTOWAL.OLD_LOAN_OFFICER_NAME
     *
     * @abatorgenerated
     */
    public void setOldLoanOfficerName(String oldLoanOfficerName) {
        this.oldLoanOfficerName = oldLoanOfficerName == null ? null : oldLoanOfficerName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_ID
     *
     * @return the value of RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_ID
     *
     * @abatorgenerated
     */
    public String getNewLoanOfficerId() {
        return newLoanOfficerId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_ID
     *
     * @param newLoanOfficerId the value for RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_ID
     *
     * @abatorgenerated
     */
    public void setNewLoanOfficerId(String newLoanOfficerId) {
        this.newLoanOfficerId = newLoanOfficerId == null ? null : newLoanOfficerId.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_NAME
     *
     * @return the value of RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_NAME
     *
     * @abatorgenerated
     */
    public String getNewLoanOfficerName() {
        return newLoanOfficerName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_NAME
     *
     * @param newLoanOfficerName the value for RL_DATA_BESTOWAL.NEW_LOAN_OFFICER_NAME
     *
     * @abatorgenerated
     */
    public void setNewLoanOfficerName(String newLoanOfficerName) {
        this.newLoanOfficerName = newLoanOfficerName == null ? null : newLoanOfficerName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.NOTE
     *
     * @return the value of RL_DATA_BESTOWAL.NOTE
     *
     * @abatorgenerated
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.NOTE
     *
     * @param note the value for RL_DATA_BESTOWAL.NOTE
     *
     * @abatorgenerated
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.OPERATING_ID
     *
     * @return the value of RL_DATA_BESTOWAL.OPERATING_ID
     *
     * @abatorgenerated
     */
    public String getOperatingId() {
        return operatingId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.OPERATING_ID
     *
     * @param operatingId the value for RL_DATA_BESTOWAL.OPERATING_ID
     *
     * @abatorgenerated
     */
    public void setOperatingId(String operatingId) {
        this.operatingId = operatingId == null ? null : operatingId.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.OPERATING_NAME
     *
     * @return the value of RL_DATA_BESTOWAL.OPERATING_NAME
     *
     * @abatorgenerated
     */
    public String getOperatingName() {
        return operatingName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.OPERATING_NAME
     *
     * @param operatingName the value for RL_DATA_BESTOWAL.OPERATING_NAME
     *
     * @abatorgenerated
     */
    public void setOperatingName(String operatingName) {
        this.operatingName = operatingName == null ? null : operatingName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_DATA_BESTOWAL.OPERATING_TIME
     *
     * @return the value of RL_DATA_BESTOWAL.OPERATING_TIME
     *
     * @abatorgenerated
     */
    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getOperatingTime() {
        return operatingTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_DATA_BESTOWAL.OPERATING_TIME
     *
     * @param operatingTime the value for RL_DATA_BESTOWAL.OPERATING_TIME
     *
     * @abatorgenerated
     */
    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }
}
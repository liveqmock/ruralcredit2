package com.creditease.rc.domain;

import java.util.Date;
/**
 * 
 * @author Administrator
 *
 */
public class PayplatformNotice {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PAYPLATFORM_NOTICE.PAYPLATFORM_NOTICE_ID
     *
     * @abatorgenerated
     */
    private Integer payplatformNoticeId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PAYPLATFORM_NOTICE.BIZID
     *
     * @abatorgenerated
     */
    private String bizid;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PAYPLATFORM_NOTICE.NOTICE_TYPE
     *
     * @abatorgenerated
     */
    private String noticeType;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PAYPLATFORM_NOTICE.TRADE_TIME
     *
     * @abatorgenerated
     */
    private Date tradeTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PAYPLATFORM_NOTICE.RESPONSE_MSG
     *
     * @abatorgenerated
     */
    private String responseMsg;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PAYPLATFORM_NOTICE.OPERATE_DATE
     *
     * @abatorgenerated
     */
    private Date operateDate;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PAYPLATFORM_NOTICE.PAYPLATFORM_NOTICE_ID
     *
     * @return the value of RL_PAYPLATFORM_NOTICE.PAYPLATFORM_NOTICE_ID
     *
     * @abatorgenerated
     */
    public Integer getPayplatformNoticeId() {
        return payplatformNoticeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PAYPLATFORM_NOTICE.PAYPLATFORM_NOTICE_ID
     *
     * @param payplatformNoticeId the value for RL_PAYPLATFORM_NOTICE.PAYPLATFORM_NOTICE_ID
     *
     * @abatorgenerated
     */
    public void setPayplatformNoticeId(Integer payplatformNoticeId) {
        this.payplatformNoticeId = payplatformNoticeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PAYPLATFORM_NOTICE.BIZID
     *
     * @return the value of RL_PAYPLATFORM_NOTICE.BIZID
     *
     * @abatorgenerated
     */
    public String getBizid() {
        return bizid;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PAYPLATFORM_NOTICE.BIZID
     *
     * @param bizid the value for RL_PAYPLATFORM_NOTICE.BIZID
     *
     * @abatorgenerated
     */
    public void setBizid(String bizid) {
        this.bizid = bizid == null ? null : bizid.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PAYPLATFORM_NOTICE.NOTICE_TYPE
     *
     * @return the value of RL_PAYPLATFORM_NOTICE.NOTICE_TYPE
     *
     * @abatorgenerated
     */
    public String getNoticeType() {
        return noticeType;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PAYPLATFORM_NOTICE.NOTICE_TYPE
     *
     * @param noticeType the value for RL_PAYPLATFORM_NOTICE.NOTICE_TYPE
     *
     * @abatorgenerated
     */
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PAYPLATFORM_NOTICE.TRADE_TIME
     *
     * @return the value of RL_PAYPLATFORM_NOTICE.TRADE_TIME
     *
     * @abatorgenerated
     */
    public Date getTradeTime() {
        return tradeTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PAYPLATFORM_NOTICE.TRADE_TIME
     *
     * @param tradeTime the value for RL_PAYPLATFORM_NOTICE.TRADE_TIME
     *
     * @abatorgenerated
     */
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PAYPLATFORM_NOTICE.RESPONSE_MSG
     *
     * @return the value of RL_PAYPLATFORM_NOTICE.RESPONSE_MSG
     *
     * @abatorgenerated
     */
    public String getResponseMsg() {
        return responseMsg;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PAYPLATFORM_NOTICE.RESPONSE_MSG
     *
     * @param responseMsg the value for RL_PAYPLATFORM_NOTICE.RESPONSE_MSG
     *
     * @abatorgenerated
     */
    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg == null ? null : responseMsg.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PAYPLATFORM_NOTICE.OPERATE_DATE
     *
     * @return the value of RL_PAYPLATFORM_NOTICE.OPERATE_DATE
     *
     * @abatorgenerated
     */
    public Date getOperateDate() {
        return operateDate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PAYPLATFORM_NOTICE.OPERATE_DATE
     *
     * @param operateDate the value for RL_PAYPLATFORM_NOTICE.OPERATE_DATE
     *
     * @abatorgenerated
     */
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
}
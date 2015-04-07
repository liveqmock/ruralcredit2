package com.creditease.rc.vo;

import java.util.Date;

/**
 * Created by v-guoxingye on 2014/12/18.
 */
public class MessageInfoVo {
    private Integer creditapplicationId;  //信贷申请ID
    private String  businessNumber;       //信贷申请编号
    private String account;              //还款账户
    private Date repayment_date;          //还款日期
    private String repaymentDateStr ;
    private String currAccountTotal;      //当前需还款金额
    private Integer year;                 //年
    private Integer month;                //月
    private Integer day;                  //日
    private Integer overdueDayCount;      //逾期天数
    private Integer remindOverdueCount;   //逾期提醒次数
    private String mobilephone;
    private String sysUUID;
    private String  updateDate; //逾期记录修改日期
    private String newDay; //是否新的一天开始

    public String getNewDay() {
        return newDay;
    }

    public void setNewDay(String newDay) {
        this.newDay = newDay;
    }

    public String getSysUUID() {
        return sysUUID;
    }

    public void setSysUUID(String sysUUID) {
        this.sysUUID = sysUUID;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    private String todayDate;   //今天日期
    public Integer getRemindOverdueCount() {
        return remindOverdueCount;
    }

    public void setRemindOverdueCount(Integer remindOverdueCount) {
        this.remindOverdueCount = remindOverdueCount;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Integer getCreditapplicationId() {
        return creditapplicationId;
    }

    public void setCreditapplicationId(Integer creditapplicationId) {
        this.creditapplicationId = creditapplicationId;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getRepayment_date() {
        return repayment_date;
    }

    public void setRepayment_date(Date repayment_date) {
        this.repayment_date = repayment_date;
    }

    public String getRepaymentDateStr() {
        return repaymentDateStr;
    }

    public void setRepaymentDateStr(String repaymentDateStr) {
        this.repaymentDateStr = repaymentDateStr;
    }

    public String getCurrAccountTotal() {
        return currAccountTotal;
    }

    public void setCurrAccountTotal(String currAccountTotal) {
        this.currAccountTotal = currAccountTotal;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getOverdueDayCount() {
        return overdueDayCount;
    }

    public void setOverdueDayCount(Integer overdueDayCount) {
        this.overdueDayCount = overdueDayCount;
    }
}

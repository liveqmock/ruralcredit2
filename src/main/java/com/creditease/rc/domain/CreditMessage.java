package com.creditease.rc.domain;

import java.util.Date;

/**
 * Created by v-guoxingye on 2014/12/18.
 */
public class CreditMessage {
    private Integer creditapplicationId;
    private Integer remindReturnRCount;
    private Integer remindBirthdayCount;
    private Integer remindOverdueCount;
    private Integer remindLoanclosedCount;
    private Date createDate;
    private String createUser;
    private  Date updateDate;

    public Integer getCreditapplicationId() {
        return creditapplicationId;
    }

    public void setCreditapplicationId(Integer creditapplicationId) {
        this.creditapplicationId = creditapplicationId;
    }

    public Integer getRemindeturnRCount() {
        return remindReturnRCount;
    }

    public void setRemindReturnRCount(Integer remindReturnRCount) {
        this.remindReturnRCount = remindReturnRCount;
    }

    public Integer getRemindBirthdayCount() {
        return remindBirthdayCount;
    }

    public void setRemindBirthdayCount(Integer remindBirthdayCount) {
        this.remindBirthdayCount = remindBirthdayCount;
    }

    public Integer getRemindOverdueCount() {
        return remindOverdueCount;
    }

    public void setRemindOverdueCount(Integer remindOverdueCount) {
        this.remindOverdueCount = remindOverdueCount;
    }

    public Integer getRemindLoanclosedCount() {
        return remindLoanclosedCount;
    }

    public void setRemindLoanclosedCount(Integer remindLoanclosedCount) {
        this.remindLoanclosedCount = remindLoanclosedCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    private String updateUser;
}

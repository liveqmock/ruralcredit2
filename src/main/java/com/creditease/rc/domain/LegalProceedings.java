package com.creditease.rc.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-6-25
 * Time: 9:54
 */
public class LegalProceedings {

    private Long follow_id;//跟进记录主键ID
    private String follow_log;//跟进记录内容
    private String operator;//操作人
    private Date operate_date;//操作时间
    private Long creditapplication_id;//信贷申请主键ID

    public Long getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(Long follow_id) {
        this.follow_id = follow_id;
    }

    public String getFollow_log() {
        return follow_log;
    }

    public void setFollow_log(String follow_log) {
        this.follow_log = follow_log;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperate_date() {
        return operate_date;
    }

    public void setOperate_date(Date operate_date) {
        this.operate_date = operate_date;
    }

    public Long getCreditapplication_id() {
        return creditapplication_id;
    }

    public void setCreditapplication_id(Long creditapplication_id) {
        this.creditapplication_id = creditapplication_id;
    }

    @Override
    public String toString() {
        return "LegalProceedings{" +
                "follow_id=" + follow_id +
                ", follow_log='" + follow_log + '\'' +
                ", operator='" + operator + '\'' +
                ", operate_date=" + operate_date +
                ", creditapplication_id=" + creditapplication_id +
                '}';
    }
}

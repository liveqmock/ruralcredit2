package com.creditease.rc.domain;

/**
 * Created by Administrator on 2014-11-13.
 */
public class CreditChannelConfig {

    private Long configId;//数据库主键ID
    private String departmentId;//营业部ID
    private String departmentName;//营业部Name
    private String channel;//渠道
    private String ids;//查询条件：营业部ids
    private String channelText;

    public String getChannelText() {
        return channelText;
    }

    public void setChannelText(String channelText) {
        this.channelText = channelText;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}

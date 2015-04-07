package com.creditease.rc.domain;

import java.util.Date;

public class BusinessStatusChangeVo extends BusinessStatusChange{

    private String groupNumber;//业务编码

    private String type;//查询类型：不良记录 法律诉讼

    private String businessStatus;//业务状态（变更时查询使用）

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
}
package com.creditease.rc.vo;

import java.util.List;

/**
 * Created by v-weizhang on 2014-12-16.
 */
public class ComplianceCheckReq {
    /*
    检查类型：客服检查、合规检查
     */
    private String checkType;

    /*
    检查点
     */
    private List<ComplianceCheckVo> list;

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public List<ComplianceCheckVo> getList() {
        return list;
    }

    public void setList(List<ComplianceCheckVo> list) {
        this.list = list;
    }
}

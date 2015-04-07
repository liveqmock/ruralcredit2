package com.creditease.rc.vo;

import com.creditease.rc.domain.CustomerConsultPoolLog;

/**
 * User: zhangwei
 * Date: 14-5-15
 * Time: 上午11:42
 */
public class CustomerConsultPoolLogVo extends CustomerConsultPoolLog {

    //起始操作时间
    private String startDate;
    //终止操作时间
    private String endDate;
    private String sort;
    private String order;

    private String fuzzyValue;
    private String teamdepartmentName;//营业部
    private String customerName;        //咨询人姓名
    private String funCode;                //所属模块
    private String teamdepartmentId;    //营业部
    private String discribeStatus;        //状态描述
    private String functionCode;        //所属模块


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFuzzyValue() {
        return fuzzyValue;
    }

    public void setFuzzyValue(String fuzzyValue) {
        this.fuzzyValue = fuzzyValue;
    }

    public String getTeamdepartmentName() {
        return teamdepartmentName;
    }

    public void setTeamdepartmentName(String teamdepartmentName) {
        this.teamdepartmentName = teamdepartmentName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getTeamdepartmentId() {
        return teamdepartmentId;
    }

    public void setTeamdepartmentId(String teamdepartmentId) {
        this.teamdepartmentId = teamdepartmentId;
    }

    public String getDiscribeStatus() {
        return discribeStatus;
    }

    public void setDiscribeStatus(String discribeStatus) {
        this.discribeStatus = discribeStatus;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }
}

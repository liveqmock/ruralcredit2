package com.creditease.rc.vo;



import com.creditease.rc.common.JsonYMDDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 逾期列表查询对象
 * User: ygx
 * Date: 14-10-11
 * Time: 下午4:01
 */
public class OverDueQueryInfo {
    private String businessNumber; //业务单号
    private String borrowerName;    //借款人姓名
    private String  customerName;   //客户经理
    private Date startDate;         //放款起始日期
    private Date endDate;            //放款结束日期
    private String returnWayType;    //还款方式
    private String companyId;         //分公司
    private String   authList;         //用户权限列表
    private String fuzzyValue;          //模糊查询字段

    private String sysUUID;
    public String getAuthList() {
        return authList;
    }

    public String getSysUUID() {
        return sysUUID;
    }

    public void setSysUUID(String sysUUID) {
        this.sysUUID = sysUUID;
    }

    public void setAuthList(String authList) {
        this.authList = authList;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getStartDate() {
        return startDate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getEndDate() {
        return endDate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReturnWayType() {
        return returnWayType;
    }

    public void setReturnWayType(String returnWayType) {
        this.returnWayType = returnWayType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }



    public String getFuzzyValue() {
        return fuzzyValue;
    }

    public void setFuzzyValue(String fuzzyValue) {
        this.fuzzyValue = fuzzyValue;
    }


}
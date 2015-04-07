package com.creditease.rc.vo;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-6-24
 * Time: 13:49
 */
public class LegalProceedingsVo {

    private String businessNumber;//业务单号
    private String borrowerName;//借款人姓名
    private String branchName;//分公司名称

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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return "LegalProceedingsVo{" +
                "businessNumber='" + businessNumber + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}

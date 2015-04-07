package com.creditease.rc.vo;

import com.creditease.rc.domain.BorrowerServiceApp;

import java.io.Serializable;

/**
 *创建申请单时        同时创建借款人信息     供传递参数使用 *
 *
* @author  ygx
 *  add on 2014-09-19
 *  *
 * *
 */
public class BorrowerInfo  implements Serializable {
        private  String    credentialsNumber;
        private  String    creditApplicationId;
        private  String    consultPoolId;
        private  String    isOldUser;
        private  String     mateIdNumber;

    public String getMateIdNumber() {
        return mateIdNumber;
    }

    public void setMateIdNumber(String mateIdNumber) {
        this.mateIdNumber = mateIdNumber;
    }

    public String getOldUser() {
        return isOldUser;
    }

    public void setOldUser(String oldUser) {
        isOldUser = oldUser;
    }

    public String getOldBorrowerServiceAppId() {
        return oldBorrowerServiceAppId;
    }

    public void setOldBorrowerServiceAppId(String oldBorrowerServiceAppId) {
        this.oldBorrowerServiceAppId = oldBorrowerServiceAppId;
    }

    private  String    oldBorrowerServiceAppId;
    public String getCustomerConsultId() {
        return customerConsultId;
    }

    public void setCustomerConsultId(String customerConsultId) {
        this.customerConsultId = customerConsultId;
    }

    private  String    borrowerServiceAppId;
        private  String    customerConsultId;
    public String getCredentialsNumber() {
        return credentialsNumber;
    }

    public void setCredentialsNumber(String credentialsNumber) {
        this.credentialsNumber = credentialsNumber;
    }

    public String getCreditApplicationId() {
        return creditApplicationId;
    }

    public void setCreditApplicationId(String creditApplicationId) {
        this.creditApplicationId = creditApplicationId;
    }

    public String getConsultPoolId() {
        return consultPoolId;
    }

    public void setConsultPoolId(String consultPoolId) {
        this.consultPoolId = consultPoolId;
    }

    public String getBorrowerServiceAppId() {
        return borrowerServiceAppId;
    }

    public void setBorrowerServiceAppId(String borrowerServiceAppId) {
        this.borrowerServiceAppId = borrowerServiceAppId;
    }
}

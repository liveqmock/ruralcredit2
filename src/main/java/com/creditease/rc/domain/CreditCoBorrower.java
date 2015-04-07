package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  @author  ygx
 *  *共同借款人 domain
 **
*/
public class CreditCoBorrower {
    private Long coBorrowerId;
    private Long borrowerServiceAppId;
    private String name;
    private String gender;
    private Integer age;
    private String telphone;
    private String borrowerreRationship;
    private String borrowerreRationshipView;
    private String workUnit;
    private Double yearIncome;
    private String education;
    private String profession; // 职业
    private String professionView; // 职业显示
    private String professionDetail;
    private String idNumber;//身份证号
    private String seq;

    public Long getCoBorrowerId() {
        return coBorrowerId;
    }

    public void setCoBorrowerId(Long coBorrowerId) {
        this.coBorrowerId = coBorrowerId;
    }

    public Long getBorrowerServiceAppId() {
        return borrowerServiceAppId;
    }

    public void setBorrowerServiceAppId(Long borrowerServiceAppId) {
        this.borrowerServiceAppId = borrowerServiceAppId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getBorrowerreRationship() {
        return borrowerreRationship;
    }

    public void setBorrowerreRationship(String borrowerreRationship) {
        this.borrowerreRationship = borrowerreRationship;
    }

    public String getBorrowerreRationshipView() {
        return borrowerreRationshipView;
    }

    public void setBorrowerreRationshipView(String borrowerreRationshipView) {
        this.borrowerreRationshipView = borrowerreRationshipView;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public Double getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(Double yearIncome) {
        this.yearIncome = yearIncome;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionView() {
        return professionView;
    }

    public void setProfessionView(String professionView) {
        this.professionView = professionView;
    }

    public String getProfessionDetail() {
        return professionDetail;
    }

    public void setProfessionDetail(String professionDetail) {
        this.professionDetail = professionDetail;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
package com.creditease.rc.vo;

import com.creditease.rc.domain.ComplianceCheck;
import com.creditease.rc.domain.CreditApplication;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 17:35
 */
public class ComplianceCheckVo extends ComplianceCheck {

    /*检查类型*/
    private String checkType;

    /*扣分类型*/
    private String deductionType;

    /*模糊查询条件*/
    private String fuzzyValue;

    /*分公司名称*/
    private String branchName;

    /*业务单号*/
    private String businessNumber;

    /*资料提交人*/
    private String materialMan;

    /*借款人*/
    private String borrowerMan;

    /*放款起始日期*/
    private String loanBegin;

    /*放款终止日期*/
    private String loanEnd;

    /*客户经理*/
    private String accountManager;

    /*业务状态*/
    private String businessStatus;

    /*数据权限*/
    private String authList;

    /*数据字典section*/
    private String section;

    /*检查点数据字典key值*/
    private String checkPointKey;

    /*资料提交人*/
    private String createLoanOfficerName;

    /*客服检查-检查点*/
    private String checkPointLoan;

    /*新旧数据标识*/
    private String isNew;

    /*操作标识：申诉、申诉审批*/
    private String complainingType;

    /*审批结果*/
    private String approveResult;

    /*检查点版本*/
    private String checkVersion;

    public String getCheckVersion() {
        return checkVersion;
    }

    public void setCheckVersion(String checkVersion) {
        this.checkVersion = checkVersion;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getFuzzyValue() {
        return fuzzyValue;
    }

    public void setFuzzyValue(String fuzzyValue) {
        this.fuzzyValue = fuzzyValue;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getMaterialMan() {
        return materialMan;
    }

    public void setMaterialMan(String materialMan) {
        this.materialMan = materialMan;
    }

    public String getBorrowerMan() {
        return borrowerMan;
    }

    public void setBorrowerMan(String borrowerMan) {
        this.borrowerMan = borrowerMan;
    }

    public String getLoanBegin() {
        return loanBegin;
    }

    public void setLoanBegin(String loanBegin) {
        this.loanBegin = loanBegin;
    }

    public String getLoanEnd() {
        return loanEnd;
    }

    public void setLoanEnd(String loanEnd) {
        this.loanEnd = loanEnd;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getAuthList() {
        return authList;
    }

    public void setAuthList(String authList) {
        this.authList = authList;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCheckPointKey() {
        return checkPointKey;
    }

    public void setCheckPointKey(String checkPointKey) {
        this.checkPointKey = checkPointKey;
    }

    public String getCreateLoanOfficerName() {
        return createLoanOfficerName;
    }

    public void setCreateLoanOfficerName(String createLoanOfficerName) {
        this.createLoanOfficerName = createLoanOfficerName;
    }

    public String getCheckPointLoan() {
        return checkPointLoan;
    }

    public void setCheckPointLoan(String checkPointLoan) {
        this.checkPointLoan = checkPointLoan;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getComplainingType() {
        return complainingType;
    }

    public void setComplainingType(String complainingType) {
        this.complainingType = complainingType;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }
}

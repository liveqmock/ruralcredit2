package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.*;

/**
 * 申请单 vo
 * @author zhangman
 *
 */
public class HouseServeyVo {
	private BorrowerService  borrowerService ; 		//个人申请
	private List<Familymember> familymemberList;					//家庭成员
	private List<JobInfo> jobInfoList;							//工作信息
	private List<Surveybusinessinfo> surveybusinessinfoList;		//经营情况
	private List<Deposit> depositList;							//存款和应收现金
	private List<Application> applicationList;					//借款申请
	private CreditApplication creditApplication  ;//申请单信息
	private CreditCoBorrower creditCoBorrower;  //共用借款人信息
    private CreditCoBorrowerSpe creditCoBorrowerSpe;  //是否特殊部门

    public CreditCoBorrowerSpe getCreditCoBorrowerSpe() {
        return creditCoBorrowerSpe;
    }

    public void setCreditCoBorrowerSpe(CreditCoBorrowerSpe creditCoBorrowerSpe) {
        this.creditCoBorrowerSpe = creditCoBorrowerSpe;
    }

    public CreditCoBorrower getCreditCoBorrower() {
        return creditCoBorrower;

    }

    public void setCreditCoBorrower(CreditCoBorrower creditCoBorrower) {
        this.creditCoBorrower = creditCoBorrower;
    }

    public BorrowerService getBorrowerService() {
		return borrowerService;
	}
	public void setBorrowerService(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}
	public List<Familymember> getFamilymemberList() {
		return familymemberList;
	}
	public void setFamilymemberList(List<Familymember> familymemberList) {
		this.familymemberList = familymemberList;
	}
	public List<JobInfo> getJobInfoList() {
		return jobInfoList;
	}
	public void setJobInfoList(List<JobInfo> jobInfoList) {
		this.jobInfoList = jobInfoList;
	}
	public List<Surveybusinessinfo> getSurveybusinessinfoList() {
		return surveybusinessinfoList;
	}
	public void setSurveybusinessinfoList(List<Surveybusinessinfo> surveybusinessinfoList) {
		this.surveybusinessinfoList = surveybusinessinfoList;
	}
	public List<Deposit> getDepositList() {
		return depositList;
	}
	public void setDepositList(List<Deposit> depositList) {
		this.depositList = depositList;
	}
	public List<Application> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	public CreditApplication getCreditApplication() {
		return creditApplication;
	}
	public void setCreditApplication(CreditApplication creditApplication) {
		this.creditApplication = creditApplication;
	}
	
}

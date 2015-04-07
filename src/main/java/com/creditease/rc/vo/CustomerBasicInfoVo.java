package com.creditease.rc.vo;

import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.CustomerConsult;

/**
 * 
 * @author zhangman
 *
 */
public class CustomerBasicInfoVo extends CustomerBasicInfo{
	private String leader;//可用标识
	private String flag;//可用标识
	private String nationalDetail; //民族详细
	private String maritalStatusDetail;//婚姻装款详细
	private String highestEducationDetail;//最高学历详细
	private String family;
	private String creditApplicationId;	//信贷id
	private String auditStatusShow;
	private String blackFlag;    //黑名单标识
	private String blackFlagShow; //黑名单信息
	private String borrowerServiceAppId; //borrowerServiceAppID
    private String specialRefuseFlag; //特定拒贷原因标识
	private String canBorrow;   // 借款人是否可用借款标识

    public String getCanBorrow() {
        return canBorrow;
    }

    public void setCanBorrow(String canBorrow) {
        this.canBorrow = canBorrow;
    }

    public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getNationalDetail() {
		return nationalDetail;
	}
	public void setNationalDetail(String nationalDetail) {
		this.nationalDetail = nationalDetail;
	}
	public String getMaritalStatusDetail() {
		return maritalStatusDetail;
	}
	public void setMaritalStatusDetail(String maritalStatusDetail) {
		this.maritalStatusDetail = maritalStatusDetail;
	}
	public String getHighestEducationDetail() {
		return highestEducationDetail;
	}
	public void setHighestEducationDetail(String highestEducationDetail) {
		this.highestEducationDetail = highestEducationDetail;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getCreditApplicationId() {
		return creditApplicationId;
	}
	public void setCreditApplicationId(String creditApplicationId) {
		this.creditApplicationId = creditApplicationId;
	}
	public String getAuditStatusShow() {
		return auditStatusShow;
	}
	public void setAuditStatusShow(String auditStatusShow) {
		this.auditStatusShow = auditStatusShow;
	}
	public String getBlackFlag() {
		return blackFlag;
	}
	public void setBlackFlag(String blackFlag) {
		this.blackFlag = blackFlag;
	}
	public String getBlackFlagShow() {
		return blackFlagShow;
	}
	public void setBlackFlagShow(String blackFlagShow) {
		this.blackFlagShow = blackFlagShow;
	}
	public String getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}
	public void setBorrowerServiceAppId(String borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
    public String getSpecialRefuseFlag() {
        return specialRefuseFlag;
    }
    public void setSpecialRefuseFlag(String specialRefuseFlag) {
        this.specialRefuseFlag = specialRefuseFlag;
    }
}

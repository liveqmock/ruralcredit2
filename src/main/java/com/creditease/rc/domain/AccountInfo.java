package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;
/**
 * 
 * @author zhangman
 * 账户中的身份证号  大写
 *
 */
public class AccountInfo implements Serializable {

	private Integer accountInfoId;  //公司财务账号表ID
	private String branchName;      //分公司名称
	private String bankPrefectureNum;//所在行地区号
	private String bankNum;    		//银行行别代码
	private String bankRank;		//银行行别名称
	private String bankName;		//银行名称
	private String accountName;		//账户名
	private String account;			//账号(放款卡号)
	private String onUsed;			//是否启用:0-停用,1-启用
	private String cardFlag;		//卡折标志:0-卡,1-折
	private String mobilephone;		//手机电话号码
	private String useType;			//账号用途:0-付款,1-收款,2-两者皆可
	private String payWay;			//支付方式:0:转帐，1:划扣
	private String payBranchno;		//支付行号 
	private String credentialsNumber;//身份证号码
	private Integer provinceId;		//省ID
	private Integer cityId;			//城市编号
	private Integer districtId;		//区ID
	private String accountType;		//财务账户类型:0-公司账户,1-个人账户
	private String companyId;
	private String borrowerName;	//借款人姓名
	private String borrowerCredentialsNumber; //借款人身份证号
	private String departmentId;				//分公司ID
	private String operaterName;				//操作人姓名
	private String operaterId;					//操作人ID
	private Date createTime = new Date();					//创建时间
	private Date updateTime = new Date();					//最后一次修改时间
	
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Integer getAccountInfoId() {
		return accountInfoId;
	}
	public void setAccountInfoId(Integer accountInfoId) {
		this.accountInfoId = accountInfoId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOnUsed() {
		return onUsed;
	}
	public void setOnUsed(String onUsed) {
		this.onUsed = onUsed;
	}
	public String getCardFlag() {
		return cardFlag;
	}
	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getCredentialsNumber() {
		return credentialsNumber;
	}
	public void setCredentialsNumber(String credentialsNumber) {
		if(credentialsNumber != null){
			this.credentialsNumber = credentialsNumber.toUpperCase();
		}else{
			this.credentialsNumber = credentialsNumber;
		}
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getBankPrefectureNum() {
		return bankPrefectureNum;
	}
	public void setBankPrefectureNum(String bankPrefectureNum) {
		this.bankPrefectureNum = bankPrefectureNum;
	}
	public String getPayBranchno() {
		return payBranchno;
	}
	public void setPayBranchno(String payBranchno) {
		this.payBranchno = payBranchno;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBankRank() {
		return bankRank;
	}
	public void setBankRank(String bankRank) {
		this.bankRank = bankRank;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowerCredentialsNumber() {
		return borrowerCredentialsNumber;
	}
	public void setBorrowerCredentialsNumber(String borrowerCredentialsNumber) {
		if(borrowerCredentialsNumber != null){
			this.borrowerCredentialsNumber = borrowerCredentialsNumber.toUpperCase();
		}else{
			this.borrowerCredentialsNumber = borrowerCredentialsNumber;
		}
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getOperaterName() {
		return operaterName;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

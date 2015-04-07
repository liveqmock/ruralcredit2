package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author zhangman
 * 
 */
public class CreditApplication implements Serializable {
	private Integer creditapplicationId; // 还款申请单ID
	private String creditapplicationDESId; // 还款申请单ID加密后
	private Integer repaymentPlanId; // 还款方案编码
	private Integer accountInfoId; // 公司财务账号ID
	private String contactNumber; // 合同编号
	private String groupNumber; // 小组编号
	private String groupName; // 组长姓名
	private String address; // 所在村镇
	private String loanOfficerId; // 信贷员ID
	private String loanOfficerName; // 信贷员姓名（客户经理）
	private String auditStatus; // 状态
	private Date createDate; // 创建时间
	private Date submitDate; // 提交时间
	private Date firstSubmitDate; //第一次提交时间
	private String uploadFile; // 上传文件
	private String loansStatus; // 放款状态
	private String reasonType; // 撤回原因类型
	private String revokeReason; // 撤回原因
	private Double groupAppTotal; // 小组申请总金额
	private Date signagreementDate; // 协议签订日期（即实际放款日期）
	private String applyRevokePerson; // 申请撤回人
	private Date applyRevokeTime; // 申请撤回时间
	private Date expectLoanDate; // 期望放款日期
	private String businessType; // 业务类型:0-分公司,1-个人
	private Integer villageId; // 村ID
	private Integer countyId; // 县ID
	private Integer townId; // 镇ID
	private String companyId; // 分公司ID
	private String companyName; // 分公司名称 
	private Long customerConsultId;//客户咨询外键
	private String defaultReturnmentWay;//还款方式0-自动划扣，1-现金
	private String departmentId;      //部门id
	private String repaymentPlanName; //产品名称
	private Integer returnAccountInfoId; //还款关联卡id
	private Double amount;//审批后金额
	private String instalments;//分期数
	private String repaymentType;//产品中心还款方式
	private String auditStatusShow; // 显示状态
	private Date repaymentDate;	//还款日期
	private String participateType;//参审角色

	public String getParticipateType() {
		return participateType;
	}

	public void setParticipateType(String participateType) {
		this.participateType = participateType;
	}

	private Long consultPoolId;//咨询池外键
	
	private String fxB;		//该属性是用来判断是否是从首页的“风险单提醒” 点进来的
	
	
	/*luohongjie   查询合同复核历史所需要的字段名称*/
	private String loanPerson;			//复核人(放款确认人)
	private String compoundNucleusResultsShow;//复核结果
	private String submitTime;//上传合同时间（放款日期）
	private String lendingConfigurationShow;//放款渠道显示
	//增加传给产品中心  下载合同的时候的参数
	private String trustAccountName;//信托账号开户名称
    private String accountName;//信托专户托管行名称 
    private String subAccountName;//信托专户托管行
    private String accountNo;//信托专户账号
    
	
	

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSubAccountName() {
		return subAccountName;
	}

	public void setSubAccountName(String subAccountName) {
		this.subAccountName = subAccountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTrustAccountName() {
		return trustAccountName;
	}

	public void setTrustAccountName(String trustAccountName) {
		this.trustAccountName = trustAccountName;
	}

	public String getLendingConfigurationShow() {
		return lendingConfigurationShow;
	}

	public void setLendingConfigurationShow(String lendingConfigurationShow) {
		this.lendingConfigurationShow = lendingConfigurationShow;
	}

	public String getFxB() {
		return fxB;
	}

	public void setFxB(String fxB) {
		this.fxB = fxB;
	}

	

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getCompoundNucleusResultsShow() {
		return compoundNucleusResultsShow;
	}

	public void setCompoundNucleusResultsShow(String compoundNucleusResultsShow) {
		this.compoundNucleusResultsShow = compoundNucleusResultsShow;
	}

	public String getLoanPerson() {
		return loanPerson;
	}

	public void setLoanPerson(String loanPerson) {
		this.loanPerson = loanPerson;
	}

    private String isCityParticipate;//城市参审
    private String cityParticipate;

    private String discountFlag;

    public String getCityParticipate() {
        return cityParticipate;
    }

    public void setCityParticipate(String cityParticipate) {
        this.cityParticipate = cityParticipate;
    }

    public String getIsCityParticipate() {
        return isCityParticipate;
    }

    public void setIsCityParticipate(String isCityParticipate) {
        this.isCityParticipate = isCityParticipate;
    }

    public String getDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(String discountFlag) {
        this.discountFlag = discountFlag;
    }

    public Long getConsultPoolId() {
		return consultPoolId;
	}

	public void setConsultPoolId(Long consultPoolId) {
		this.consultPoolId = consultPoolId;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	//加密标识
	private String laonDESId; // 放款登记协议
	private String examDESId; // 审批资料
	
	// 小组申请删除标识
	private String validState;   //1-有效 0-无效
	private String producttypeid;//产品分类编号
	private String createLoanOfficerId;//创建 客户经理id
	private String createLoanOfficerName;//创建 客户经理姓名
	private String loansStatusShow;// 财务状态 （财务付款导出）
	public String getAuditStatusShow() {
		return auditStatusShow;
	}

	public void setAuditStatusShow(String auditStatusShow) {
		this.auditStatusShow = auditStatusShow;
	}
	
	//风险经理提交标记哦
	private String fx; 
	//信用及背景调查id
	private Double fxid;
	//订单号
	private String bizId; 
	//首期服务费
	private Double serviceCharge;
	//实际放款金额
	private Double realAmount;
	// 操作标记
	private String operateFlag;
	// 小组数量-计算得到
	private int groupMemberCount;
	// 提交起始日期
	private Date beginSubmitDate;
	// 提交终止日期
	private Date endSubmitDate;
	// 期望放款起始日期
	private Date beginExpectLoanDate;
	// 期望放款终止日期
	private Date endExpectLoanDate;
	// 放款起始日期
	private Date beginLoanDate;
	// 放款终止日期
	private Date endLoanDate;
	
	//放款金额
	private Double loanAmount;
	
	//资金来源
	private String capitalSource;
	//财务类型
	private String receivedType;
	//收款状态
	private String receivedStatus;
	
	//操作日期
	private Date operateDate;
	//操作状态
	private String operateStatus;
	private String authList;
	
	//产品中金额上限
	private Double capitalUpperLimit;
	//产品中金额下限
	private Double capitalLowerLimit;
	//信用背景调查报告
	private Long creditInvestigatioId;
	//担保人
	private String leader;
	//角色
	private String role;
	//收款预约失败标志
	private String rf;
	//未收款预约标志
	private String rw;
	//模糊查询条件
	private String fuzzyValue;
	private String ids;//信贷申请贷ID集合
	
	private String familyName; //配偶姓名
	private String familyIdnumber;//配偶身份证号
	private String credentialsNumber;//借款人身份证号
	private String loanConfirmDate; //放款确认日期
	private String loanConfirmDateBegin; //放款确认开始日期
	private String loanConfirmDateEnd; //放款确认结束日期
	
	
	
	public String getLoanConfirmDateBegin() {
		return loanConfirmDateBegin;
	}

	public void setLoanConfirmDateBegin(String loanConfirmDateBegin) {
		this.loanConfirmDateBegin = loanConfirmDateBegin;
	}

	public String getLoanConfirmDateEnd() {
		return loanConfirmDateEnd;
	}

	public void setLoanConfirmDateEnd(String loanConfirmDateEnd) {
		this.loanConfirmDateEnd = loanConfirmDateEnd;
	}

	public String getLoanConfirmDate() {
		return loanConfirmDate;
	}

	public void setLoanConfirmDate(String loanConfirmDate) {
		this.loanConfirmDate = loanConfirmDate;
	}

	public String getValidState() {
		return validState;
	}

	public void setValidState(String validState) {
		this.validState = validState;
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public Integer getRepaymentPlanId() {
		return repaymentPlanId;
	}

	public void setRepaymentPlanId(Integer repaymentPlanId) {
		this.repaymentPlanId = repaymentPlanId;
	}

	public Integer getAccountInfoId() {
		return accountInfoId;
	}

	public void setAccountInfoId(Integer accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoanOfficerId() {
		return loanOfficerId;
	}

	public void setLoanOfficerId(String loanOfficerId) {
		this.loanOfficerId = loanOfficerId;
	}

	public String getLoanOfficerName() {
		return loanOfficerName;
	}

	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getLoansStatus() {
		return loansStatus;
	}

	public void setLoansStatus(String loansStatus) {
		this.loansStatus = loansStatus;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}


	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getSignagreementDate() {
		return signagreementDate;
	}

	public void setSignagreementDate(Date signagreementDate) {
		this.signagreementDate = signagreementDate;
	}

	public String getApplyRevokePerson() {
		return applyRevokePerson;
	}

	public void setApplyRevokePerson(String applyRevokePerson) {
		this.applyRevokePerson = applyRevokePerson;
	}

	public Date getApplyRevokeTime() {
		return applyRevokeTime;
	}

	public void setApplyRevokeTime(Date applyRevokeTime) {
		this.applyRevokeTime = applyRevokeTime;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getExpectLoanDate() {
		return expectLoanDate;
	}

	public void setExpectLoanDate(Date expectLoanDate) {
		this.expectLoanDate = expectLoanDate;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public int getGroupMemberCount() {
		return groupMemberCount;
	}

	public void setGroupMemberCount(int groupMemberCount) {
		this.groupMemberCount = groupMemberCount;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getBeginSubmitDate() {
		return beginSubmitDate;
	}

	public void setBeginSubmitDate(Date beginSubmitDate) {
		this.beginSubmitDate = beginSubmitDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getEndSubmitDate() {
		return endSubmitDate;
	}

	public void setEndSubmitDate(Date endSubmitDate) {
		this.endSubmitDate = endSubmitDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getBeginExpectLoanDate() {
		return beginExpectLoanDate;
	}

	public void setBeginExpectLoanDate(Date beginExpectLoanDate) {
		this.beginExpectLoanDate = beginExpectLoanDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getEndExpectLoanDate() {
		return endExpectLoanDate;
	}

	public void setEndExpectLoanDate(Date endExpectLoanDate) {
		this.endExpectLoanDate = endExpectLoanDate;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getBeginLoanDate() {
		return beginLoanDate;
	}

	public void setBeginLoanDate(Date beginLoanDate) {
		this.beginLoanDate = beginLoanDate;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getEndLoanDate() {
		return endLoanDate;
	}

	public void setEndLoanDate(Date endLoanDate) {
		this.endLoanDate = endLoanDate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	public Double getGroupAppTotal() {
		return groupAppTotal;
	}

	public void setGroupAppTotal(Double groupAppTotal) {
		this.groupAppTotal = groupAppTotal;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


	public Double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	public String getReceivedType() {
		return receivedType;
	}

	public void setReceivedType(String receivedType) {
		this.receivedType = receivedType;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getReceivedStatus() {
		return receivedStatus;
	}

	public void setReceivedStatus(String receivedStatus) {
		this.receivedStatus = receivedStatus;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getCustomerConsultId() {
		return customerConsultId;
	}

	public void setCustomerConsultId(Long customerConsultId) {
		this.customerConsultId = customerConsultId;
	}

	public String getDefaultReturnmentWay() {
		return defaultReturnmentWay;
	}

	public void setDefaultReturnmentWay(String defaultReturnmentWay) {
		this.defaultReturnmentWay = defaultReturnmentWay;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}

	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}

	public Integer getReturnAccountInfoId() {
		return returnAccountInfoId;
	}

	public void setReturnAccountInfoId(Integer returnAccountInfoId) {
		this.returnAccountInfoId = returnAccountInfoId;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getInstalments() {
		return instalments;
	}

	public void setInstalments(String instalments) {
		this.instalments = instalments;
	}

	public String getCreditapplicationDESId() {
		return creditapplicationDESId;
	}

	public void setCreditapplicationDESId(String creditapplicationDESId) {
		this.creditapplicationDESId = creditapplicationDESId;
	}

	public String getAuthList() {
		return authList;
	}

	public void setAuthList(String authList) {
		this.authList = authList;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public Double getFxid() {
		return fxid;
	}

	public void setFxid(Double fxid) {
		this.fxid = fxid;
	}

	public Double getCapitalUpperLimit() {
		return capitalUpperLimit;
	}

	public void setCapitalUpperLimit(Double capitalUpperLimit) {
		this.capitalUpperLimit = capitalUpperLimit;
	}

	public Double getCapitalLowerLimit() {
		return capitalLowerLimit;
	}

	public void setCapitalLowerLimit(Double capitalLowerLimit) {
		this.capitalLowerLimit = capitalLowerLimit;
	}

	public Long getCreditInvestigatioId() {
		return creditInvestigatioId;
	}

	public void setCreditInvestigatioId(Long creditInvestigatioId) {
		this.creditInvestigatioId = creditInvestigatioId;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFuzzyValue() {
		return fuzzyValue;
	}

	public void setFuzzyValue(String fuzzyValue) {
		this.fuzzyValue = fuzzyValue;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRf() {
		return rf;
	}

	public void setRf(String rf) {
		this.rf = rf;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	public Date getFirstSubmitDate() {
		return firstSubmitDate;
	}

	public void setFirstSubmitDate(Date firstSubmitDate) {
		this.firstSubmitDate = firstSubmitDate;
	}

	public String getProducttypeid() {
		return producttypeid;
	}

	public void setProducttypeid(String producttypeid) {
		this.producttypeid = producttypeid;
	}

	public String getCreateLoanOfficerId() {
		return createLoanOfficerId;
	}

	public void setCreateLoanOfficerId(String createLoanOfficerId) {
		this.createLoanOfficerId = createLoanOfficerId;
	}

	public String getCreateLoanOfficerName() {
		return createLoanOfficerName;
	}

	public void setCreateLoanOfficerName(String createLoanOfficerName) {
		this.createLoanOfficerName = createLoanOfficerName;
	}

	public String getLaonDESId() {
		return laonDESId;
	}

	public void setLaonDESId(String laonDESId) {
		this.laonDESId = laonDESId;
	}

	public String getExamDESId() {
		return examDESId;
	}

	public void setExamDESId(String examDESId) {
		this.examDESId = examDESId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyIdnumber() {
		return familyIdnumber;
	}

	public void setFamilyIdnumber(String familyIdnumber) {
		this.familyIdnumber = familyIdnumber;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	private String refuseReasons;//据贷/客户放弃原因

	public String getRefuseReasons() {
		return refuseReasons;
	}

	public void setRefuseReasons(String refuseReasons) {
		this.refuseReasons = refuseReasons;
	}

	public String getLoansStatusShow() {
		return loansStatusShow;
	}

	public void setLoansStatusShow(String loansStatusShow) {
		this.loansStatusShow = loansStatusShow;
	}
    private    String mateIdNumber;//借款人配偶身份证号码

    public String getMateIdNumber() {
        return mateIdNumber;
    }

    public void setMateIdNumber(String mateIdNumber) {
        this.mateIdNumber = mateIdNumber;
    }
}

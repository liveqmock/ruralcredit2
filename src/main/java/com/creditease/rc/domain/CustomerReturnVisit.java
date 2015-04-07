package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.common.JsonYMDHMDDateSerializer;

public class CustomerReturnVisit implements Serializable {
	public void CustomerReturnVisitVo(){
	}
	
	/**
	 * 带参数的构造函数
	 * @param creditapplicationId
	 */
	public void CustomerReturnVisitVo(Long creditapplicationId){
		this.setCreditapplicationId(creditapplicationId);	//设置申请id
		this.setStatus("0");									//设置状态（默认 没有回访）
	}
	private Long customerReturnVisitId; //id
	private Long creditapplicationId;   //申请id
	private String operatorId;			//操作人id
	private String operatorName;		//操作人姓名
	private Date operatorTime = new Date();			//操作日期
	private Date visitDate;				//回访日期
	private String visitDurationTimes;	//回访时长
	private String visitWay;			//回访方式
	private String loanUse;				//借款用途变更
	private String newLoanUse;			//新借款用途
	private String income;				//经营收入变更
	private String newIncome;			//新经营收入
	private String familyIncome;		//家庭收入变更
	private String newFamilyIncome;		//新家庭收入
	private String spending;			//有新大项开支
	private String newSpending;			//新大项开支明细
	private String contactWay;			//联系方式变更
	private String newContactWay;		//新联系方式
	private String newDebt;				//有无新增债务
	private String newDebtMoney;		//新增债务金额
	private String customerAttitude;	//客户态度
	private String familyNumberCondition;//家庭成员情况
	private String otherFactor;				//其他影响客户还款能力或意愿的情况
	private Date repaymentDate;				//对应还款日期
	private String status;					//状态
	private String visitDurationHours;		//回访的小时数
	private String visitDurationMinutes;	//回访的分钟
	private String validateStatus;        //是否有效：0有效；1无效
    private String attatchmentId;        //附件ID
	private String isPurposeConsistency;//是否接口用途一致
	private String attitudeForRepayment;//客户还款态度
	private String companyId;                      //分公司IDstr
	private String repaymentPlanName;					//产品类型
	private String isComplete;                      //照片是否齐全
	private String reasonForIncomplete;					//不齐全原因

	private String contractChangeType;      //联系方式变更
	private String changeBorrowerPhone;		//变更后借款人电话


	private String changeCoborrowerPhone;   //变更后共借人电话
	private String sourceIncomeChangedType;		//收入来源变化类型

	public String getSourceIncomeChangedType() {
		return sourceIncomeChangedType;
	}

	public void setSourceIncomeChangedType(String sourceIncomeChangedType) {
		this.sourceIncomeChangedType = sourceIncomeChangedType;
	}

	public String getSourceIncomeChangedContent() {
		return sourceIncomeChangedContent;
	}

	public void setSourceIncomeChangedContent(String sourceIncomeChangedContent) {
		this.sourceIncomeChangedContent = sourceIncomeChangedContent;
	}

	public String getSourceIncomeChangedContentStr() {
		return sourceIncomeChangedContentStr;
	}

	public void setSourceIncomeChangedContentStr(String sourceIncomeChangedContentStr) {
		this.sourceIncomeChangedContentStr = sourceIncomeChangedContentStr;
	}

	private String sourceIncomeChangedContent;   //收入来源变化内容 keys
	private String sourceIncomeChangedContentStr;   //收入来源变化内容 values

	public String getContractChangeType() {
		return contractChangeType;
	}

	public void setContractChangeType(String contractChangeType) {
		this.contractChangeType = contractChangeType;
	}

	public String getChangeBorrowerPhone() {
		return changeBorrowerPhone;
	}

	public void setChangeBorrowerPhone(String changeBorrowerPhone) {
		this.changeBorrowerPhone = changeBorrowerPhone;
	}

	public String getChangeCoborrowerPhone() {
		return changeCoborrowerPhone;
	}

	public void setChangeCoborrowerPhone(String changeCoborrowerPhone) {
		this.changeCoborrowerPhone = changeCoborrowerPhone;
	}

	public String getChangeGuaranteeFirstPhone() {
		return changeGuaranteeFirstPhone;
	}

	public void setChangeGuaranteeFirstPhone(String changeGuaranteeFirstPhone) {
		this.changeGuaranteeFirstPhone = changeGuaranteeFirstPhone;
	}

	public String getChangeGuaranteeSecondPhone() {
		return changeGuaranteeSecondPhone;
	}

	public void setChangeGuaranteeSecondPhone(String changeGuaranteeSecondPhone) {
		this.changeGuaranteeSecondPhone = changeGuaranteeSecondPhone;
	}

	private String changeGuaranteeFirstPhone; //变更后担保人1电话
	private String changeGuaranteeSecondPhone;//变更后担保人2电话
	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public String getReasonForIncomplete() {
		return reasonForIncomplete;
	}

	public void setReasonForIncomplete(String reasonForIncomplete) {
		this.reasonForIncomplete = reasonForIncomplete;
	}

	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}

	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private String highDangered;//是否高危可标记
	private String highDangerReason;//高危标记原因
	public String getHighDangered() {
		return highDangered;
	}

	public String getHighDangerReason() {
		return highDangerReason;
	}

	public void setHighDangerReason(String highDangerReason) {
		this.highDangerReason = highDangerReason;
	}

	public void setHighDangered(String highDangered) {
		this.highDangered = highDangered;
	}

	public String getAttitudeForRepayment() {
		return attitudeForRepayment;
	}

	public void setAttitudeForRepayment(String attitudeForRepayment) {
		this.attitudeForRepayment = attitudeForRepayment;
	}

	public String getAttitudeForCutomermanager() {
		return attitudeForCutomermanager;
	}

	public void setAttitudeForCutomermanager(String attitudeForCutomermanager) {
		this.attitudeForCutomermanager = attitudeForCutomermanager;
	}

	private String attitudeForCutomermanager;//对待客户经理态度

	public String getReasonNotConsistency() {
		return reasonNotConsistency;
	}

	public void setReasonNotConsistency(String reasonNotConsistency) {
		this.reasonNotConsistency = reasonNotConsistency;
	}

	public String getAttatchmentId() {
		return attatchmentId;
	}

	public void setAttatchmentId(String attatchmentId) {
		this.attatchmentId = attatchmentId;
	}

	public String getIsPurposeConsistency() {
		return isPurposeConsistency;
	}

	public void setIsPurposeConsistency(String isPurposeConsistency) {
		this.isPurposeConsistency = isPurposeConsistency;
	}

	private String reasonNotConsistency;//借款用途不一致原因

    public String getAttachmentId() {
        return attatchmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attatchmentId = attachmentId;
    }

    public String getValidateStatus() {
		return validateStatus;
	}

	public void setValidateStatus(String validateStatus) {
		this.validateStatus = validateStatus;
	}

	public Long getCustomerReturnVisitId() {
		return customerReturnVisitId;
	}
	public void setCustomerReturnVisitId(Long customerReturnVisitId) {
		this.customerReturnVisitId = customerReturnVisitId;
	}
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}
	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getVisitDurationTimes() {
		return visitDurationTimes;
	}
	public void setVisitDurationTimes(String visitDurationTimes) {
		this.visitDurationTimes = visitDurationTimes;
	}
	
	public String getVisitWay() {
		return visitWay;
	}
	public void setVisitWay(String visitWay) {
		this.visitWay = visitWay;
	}
	public String getLoanUse() {
		return loanUse;
	}
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}
	public String getNewLoanUse() {
		return newLoanUse;
	}
	public void setNewLoanUse(String newLoanUse) {
		this.newLoanUse = newLoanUse;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getSpending() {
		return spending;
	}
	public void setSpending(String spending) {
		this.spending = spending;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getNewContactWay() {
		return newContactWay;
	}
	public void setNewContactWay(String newContactWay) {
		this.newContactWay = newContactWay;
	}
	public String getNewDebt() {
		return newDebt;
	}
	public void setNewDebt(String newDebt) {
		this.newDebt = newDebt;
	}
	public String getNewDebtMoney() {
		return newDebtMoney;
	}
	public void setNewDebtMoney(String newDebtMoney) {
		this.newDebtMoney = newDebtMoney;
	}
	public String getCustomerAttitude() {
		return customerAttitude;
	}
	public void setCustomerAttitude(String customerAttitude) {
		this.customerAttitude = customerAttitude;
	}
	public String getFamilyNumberCondition() {
		return familyNumberCondition;
	}
	public void setFamilyNumberCondition(String familyNumberCondition) {
		this.familyNumberCondition = familyNumberCondition;
	}
	public String getOtherFactor() {
		return otherFactor;
	}
	public void setOtherFactor(String otherFactor) {
		this.otherFactor = otherFactor;
	}
	public String getNewIncome() {
		return newIncome;
	}
	public void setNewIncome(String newIncome) {
		this.newIncome = newIncome;
	}
	public String getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = familyIncome;
	}
	public String getNewFamilyIncome() {
		return newFamilyIncome;
	}
	public void setNewFamilyIncome(String newFamilyIncome) {
		this.newFamilyIncome = newFamilyIncome;
	}
	public String getNewSpending() {
		return newSpending;
	}
	public void setNewSpending(String newSpending) {
		this.newSpending = newSpending;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVisitDurationHours() {
		return visitDurationHours;
	}

	public void setVisitDurationHours(String visitDurationHours) {
		this.visitDurationHours = visitDurationHours;
	}

	public String getVisitDurationMinutes() {
		return visitDurationMinutes;
	}

	public void setVisitDurationMinutes(String visitDurationMinutes) {
		this.visitDurationMinutes = visitDurationMinutes;
	}
	
}

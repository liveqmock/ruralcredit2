package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.domain.CustomerReturnVisit;

public class CustomerReturnVisitVo extends CustomerReturnVisit {
	private String businessNumber;  			//业务编号
	private String borrowerName;				//借款人姓名
	private String loanOfficerName;				//客户经理姓名
	private String loanOfficerId;				//客户经理id
	private Date   beginRepaymentDate;			//还款日期（开始条件）
	private Date   endRepaymentDate;			//还款日期（结束条件）
	private Date   beginReturnVisitDate;		//回访日期（开始条件）
	private Date   endReturnVisitDate;			//回访日期（结束条件）
	
	private String visitWayShow;				//回访方式
	private String loanUseShow;					//借款用途变更
	private String incomeShow;						//经营收入变更
	private String familyIncomeShow;				//家庭收入变更
	private String spendingShow;					//有新大项开支
	private String contactWayShow;					//联系方式变更
	private String newDebtShow;						//有无新增债务
	private String statusShow;						//状态
	
	private String newLoanUseShow;					//新借款用途行业
	private String newIncomeShow;					//新借款用途行业
	private String newFamilyIncomeShow;					//新借款用途行业
	private String contractChangeType;      //联系方式变更


	@Override
	public String getSourceIncomeChangedType() {
		return sourceIncomeChangedType;
	}

	@Override
	public void setSourceIncomeChangedType(String sourceIncomeChangedType) {
		this.sourceIncomeChangedType = sourceIncomeChangedType;
	}

	@Override
	public String getSourceIncomeChangedContent() {
		return sourceIncomeChangedContent;
	}

	@Override
	public String getSourceIncomeChangedContentStr() {
		return sourceIncomeChangedContentStr;
	}

	@Override
	public void setSourceIncomeChangedContentStr(String sourceIncomeChangedContentStr) {
		this.sourceIncomeChangedContentStr = sourceIncomeChangedContentStr;
	}

	@Override
	public void setSourceIncomeChangedContent(String sourceIncomeChangedContent) {
		this.sourceIncomeChangedContent = sourceIncomeChangedContent;

	}

	private String changeBorrowerPhone;		//变更后借款人电话
	private String sourceIncomeChangedType;		//收入来源变化类型
	private String sourceIncomeChangedContent;   //收入来源变化内容
	private String sourceIncomeChangedContentStr;   //收入来源变化内容 values
	@Override
	public String getContractChangeType() {
		return contractChangeType;
	}

	@Override
	public void setContractChangeType(String contractChangeType) {
		this.contractChangeType = contractChangeType;
	}

	@Override
	public String getChangeBorrowerPhone() {
		return changeBorrowerPhone;
	}

	@Override
	public void setChangeBorrowerPhone(String changeBorrowerPhone) {
		this.changeBorrowerPhone = changeBorrowerPhone;
	}

	@Override
	public String getChangeCoborrowerPhone() {
		return changeCoborrowerPhone;
	}

	@Override
	public void setChangeCoborrowerPhone(String changeCoborrowerPhone) {
		this.changeCoborrowerPhone = changeCoborrowerPhone;
	}

	@Override
	public String getChangeGuaranteeFirstPhone() {
		return changeGuaranteeFirstPhone;
	}

	@Override
	public void setChangeGuaranteeFirstPhone(String changeGuaranteeFirstPhone) {
		this.changeGuaranteeFirstPhone = changeGuaranteeFirstPhone;
	}

	@Override
	public String getChangeGuaranteeSecondPhone() {
		return changeGuaranteeSecondPhone;
	}

	@Override
	public void setChangeGuaranteeSecondPhone(String changeGuaranteeSecondPhone) {
		this.changeGuaranteeSecondPhone = changeGuaranteeSecondPhone;
	}

	private String changeCoborrowerPhone;   //变更后共借人电话
	private String changeGuaranteeFirstPhone; //变更后担保人1电话
	private String changeGuaranteeSecondPhone;//变更后担保人2电话

	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}

	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}

	private String repaymentPlanName;					//产品类型

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private String companyId;                      //分公司IDstr

	@Override
	public String getAttitudeForRepayment() {
		return attitudeForRepayment;
	}

	@Override
	public void setAttitudeForRepayment(String attitudeForRepayment) {
		this.attitudeForRepayment = attitudeForRepayment;
	}

	@Override
	public String getAttitudeForCutomermanager() {
		return attitudeForCutomermanager;
	}

	@Override
	public void setAttitudeForCutomermanager(String attitudeForCutomermanager) {
		this.attitudeForCutomermanager = attitudeForCutomermanager;
	}

	private Date presentDate;						//当前日期
	private String visitDurationTimesShow;			//访问时间长度
	private String repayMentDateString;			//时间字符串
	private String nowDate;						//现在的时间
	private String sqlsid;						//权限
	private String isPurposeConsistency;//是否接口用途一致
	private String reasonNotConsistency;//借款用途不一致原因
	private String attitudeForRepayment;//客户还款态度
	private String attitudeForCutomermanager;//对待客户经理态度
	private String highDangered;//是否高危可标记


	public String getReasonForIncomplete() {
		return reasonForIncomplete;
	}

	public void setReasonForIncomplete(String reasonForIncomplete) {
		this.reasonForIncomplete = reasonForIncomplete;
	}


	public String getIsComplete() {
		return isComplete;
	}


	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	private String isComplete;                      //照片是否齐全
	private String reasonForIncomplete;					//不齐全原因
	@Override
	public String getHighDangerReason() {
		return highDangerReason;
	}

	@Override
	public void setHighDangerReason(String highDangerReason) {
		this.highDangerReason = highDangerReason;
	}

	private String highDangerReason;//高危标记原因

	public String getHighDangered() {
		return highDangered;
	}

	public void setHighDangered(String highDangered) {
		this.highDangered = highDangered;
	}

	@Override
	public String getIsPurposeConsistency() {
		return isPurposeConsistency;
	}

	@Override
	public void setIsPurposeConsistency(String isPurposeConsistency) {
		this.isPurposeConsistency = isPurposeConsistency;
	}

	@Override
	public String getReasonNotConsistency() {
		return reasonNotConsistency;
	}

	@Override
	public void setReasonNotConsistency(String reasonNotConsistency) {
		this.reasonNotConsistency = reasonNotConsistency;
	}

	public String getSqlsid() {
		return sqlsid;
	}
	public void setSqlsid(String sqlsid) {
		this.sqlsid = sqlsid;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getRepayMentDateString() {
		return repayMentDateString;
	}
	public void setRepayMentDateString(String repayMentDateString) {
		this.repayMentDateString = repayMentDateString;
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
	public String getLoanOfficerName() {
		return loanOfficerName;
	}
	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}
	public String getLoanOfficerId() {
		return loanOfficerId;
	}
	public void setLoanOfficerId(String loanOfficerId) {
		this.loanOfficerId = loanOfficerId;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getBeginRepaymentDate() {
		return beginRepaymentDate;
	}
	public void setBeginRepaymentDate(Date beginRepaymentDate) {
		this.beginRepaymentDate = beginRepaymentDate;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getEndRepaymentDate() {
		return endRepaymentDate;
	}
	public void setEndRepaymentDate(Date endRepaymentDate) {
		this.endRepaymentDate = endRepaymentDate;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getBeginReturnVisitDate() {
		return beginReturnVisitDate;
	}
	public void setBeginReturnVisitDate(Date beginReturnVisitDate) {
		this.beginReturnVisitDate = beginReturnVisitDate;
	}
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getEndReturnVisitDate() {
		return endReturnVisitDate;
	}
	public void setEndReturnVisitDate(Date endReturnVisitDate) {
		this.endReturnVisitDate = endReturnVisitDate;
	}
	public String getVisitWayShow() {
		return visitWayShow;
	}
	public void setVisitWayShow(String visitWayShow) {
		this.visitWayShow = visitWayShow;
	}
	public String getLoanUseShow() {
		return loanUseShow;
	}
	public void setLoanUseShow(String loanUseShow) {
		this.loanUseShow = loanUseShow;
	}
	public String getIncomeShow() {
		return incomeShow;
	}
	public void setIncomeShow(String incomeShow) {
		this.incomeShow = incomeShow;
	}
	public String getFamilyIncomeShow() {
		return familyIncomeShow;
	}
	public void setFamilyIncomeShow(String familyIncomeShow) {
		this.familyIncomeShow = familyIncomeShow;
	}
	public String getSpendingShow() {
		return spendingShow;
	}
	public void setSpendingShow(String spendingShow) {
		this.spendingShow = spendingShow;
	}
	public String getContactWayShow() {
		return contactWayShow;
	}
	public void setContactWayShow(String contactWayShow) {
		this.contactWayShow = contactWayShow;
	}
	public String getNewDebtShow() {
		return newDebtShow;
	}
	public void setNewDebtShow(String newDebtShow) {
		this.newDebtShow = newDebtShow;
	}
	public String getStatusShow() {
		return statusShow;
	}
	public void setStatusShow(String statusShow) {
		this.statusShow = statusShow;
	}
	public Date getPresentDate() {
		return presentDate;
	}
	public void setPresentDate(Date presentDate) {
		this.presentDate = presentDate;
	}
	public String getNewLoanUseShow() {
		return newLoanUseShow;
	}
	public void setNewLoanUseShow(String newLoanUseShow) {
		this.newLoanUseShow = newLoanUseShow;
	}
	public String getNewIncomeShow() {
		return newIncomeShow;
	}
	public void setNewIncomeShow(String newIncomeShow) {
		this.newIncomeShow = newIncomeShow;
	}
	public String getNewFamilyIncomeShow() {
		return newFamilyIncomeShow;
	}
	public void setNewFamilyIncomeShow(String newFamilyIncomeShow) {
		this.newFamilyIncomeShow = newFamilyIncomeShow;
	}
	public String getVisitDurationTimesShow() {
		return visitDurationTimesShow;
	}
	public void setVisitDurationTimesShow(String visitDurationTimesShow) {
		this.visitDurationTimesShow = visitDurationTimesShow;
	}
}

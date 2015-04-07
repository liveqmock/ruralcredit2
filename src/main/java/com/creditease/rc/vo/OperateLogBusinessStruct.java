/**
 * Title:OperateLogBusinessStruct.java  
 * Description:  
 */
package com.creditease.rc.vo;

import com.creditease.rc.domain.OperateLog;

/**
 * Title:OperateLogBusinessStruct.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-11 
 */
public class OperateLogBusinessStruct extends OperateLog{

	/**
	 * @Description 默认构造器 
	 */
	public OperateLogBusinessStruct() {
		// TODO Auto-generated constructor stub
	}
	/******************************通用字段**********************************/
	//结果
	private String result;
	//备注
	private String remark;
	
	
	
	
	/*****************************申请模块字段******************************/
	//借款人姓名
	private String borrowerName;
    //申请金额
	private String applyAmount;
	//申请日期
	private String applyDate;
	//业务单号
	private String businessNumber;
	//还款方案
	private String repaymentPlanName;
	
	/*****************************审批模块*********************************/
	//审查金额
	private String auditAmount;
	//审批金额
	private String auditConfirmAmount;
	
	
	/*****************************放款登记模块******************************/
	//额度确认金额
	private String confirmAmount;
	//期望放款日期
	private String expectLoanDate;
	//放款登记金额
	private String loanAmount;
	//实发金额（放款金额-服务费，向财务预约的金额）
	private String realAmount;
	//放款日期
	private String loanTime;
	//协议编号
	private String protocolNO;
	
	
	
	/*****************************收款模块*********************************/
	//收款登记金额
	private String receivedAmount;
	//实还日期-客户经理收到还款的日期
	private String receivedTime;
	
	
	
	
	/*****************************财务模块*********************************/
	//账号
	private String accountNo;
	//账户名称
	private String accountName;
	//证件号码
	private String idCard;
	//预约时间
	private String subscribeTime;
	//收款执行时间
	private String tradeTime;
	//预约金额
	private String subscribeAmount;
	//订单号
	private String bizid;
	
	
	
	/*****************************免罚模块*********************************/
	//免罚金额
	private String impunityAmount;
	
	
	
	
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getAuditAmount() {
		return auditAmount;
	}
	public void setAuditAmount(String auditAmount) {
		this.auditAmount = auditAmount;
	}
	public String getAuditConfirmAmount() {
		return auditConfirmAmount;
	}
	public void setAuditConfirmAmount(String auditConfirmAmount) {
		this.auditConfirmAmount = auditConfirmAmount;
	}
	public String getConfirmAmount() {
		return confirmAmount;
	}
	public void setConfirmAmount(String confirmAmount) {
		this.confirmAmount = confirmAmount;
	}
	public String getExpectLoanDate() {
		return expectLoanDate;
	}
	public void setExpectLoanDate(String expectLoanDate) {
		this.expectLoanDate = expectLoanDate;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanTime() {
		return loanTime;
	}
	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}
	public String getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getSubscribeAmount() {
		return subscribeAmount;
	}
	public void setSubscribeAmount(String subscribeAmount) {
		this.subscribeAmount = subscribeAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImpunityAmount() {
		return impunityAmount;
	}
	public void setImpunityAmount(String impunityAmount) {
		this.impunityAmount = impunityAmount;
	}
	
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getProtocolNO() {
		return protocolNO;
	}
	public void setProtocolNO(String protocolNO) {
		this.protocolNO = protocolNO;
	}
	public String getBizid() {
		return bizid;
	}
	public void setBizid(String bizid) {
		this.bizid = bizid;
	}
	public String getRepaymentPlanName() {
		return repaymentPlanName;
	}
	public void setRepaymentPlanName(String repaymentPlanName) {
		this.repaymentPlanName = repaymentPlanName;
	}
	
	
}

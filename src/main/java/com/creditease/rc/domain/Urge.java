package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class Urge {

	private Long urgeId; // 催收ID
	private Long creditapplicationId; // 信贷申请单ID外键
	private String operatorName; // 操作人姓名
	private Long operatorId; // 操作人ID
	private Date operateTime; // 操作时间
	private String repaymentCycle; // 所属还款周期
	private String urgeSummarize; // 催收概括
	private String urgeWay; // 催收方式
	private BigDecimal reginMoney;// 收回金额
	private String moneySource; // 金额来源
	private String urgeState;// 催收状态
	private Date urgeDate;// 催收时间
	private Date refundDate;// 还款日期
	private String urgeLongTime;// 催收时长
	private String urgeRemark;// 催收沟通评价
	private String bigWarning;// 重大风险警告
	private String urgeDescribe;// 催收描述
	private String ynPromise;// 是否履诺
	private BigDecimal previousPromiseMoney;// 上次承诺还款金额
	private Date previousPromiseTime;// 上次承诺还款时间
	private BigDecimal currentPromiseMoney;// 本次承诺还款金额
	private Date curentPromiseTime;// 本次承诺还款时间
	private Date breakbegindate;// 违约开始日期

	public Date getBreakbegindate() {
		return breakbegindate;
	}

	public void setBreakbegindate(Date breakbegindate) {
		this.breakbegindate = breakbegindate;
	}

	public Long getUrgeId() {
		return urgeId;
	}

	public void setUrgeId(Long urgeId) {
		this.urgeId = urgeId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.CREDITAPPLICATION_ID
	 * 
	 * @return the value of RL_URGE.CREDITAPPLICATION_ID
	 * 
	 * @abatorgenerated
	 */
	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.CREDITAPPLICATION_ID
	 * 
	 * @param creditapplicationId the value for RL_URGE.CREDITAPPLICATION_ID
	 * 
	 * @abatorgenerated
	 */
	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.OPERATOR_NAME
	 * 
	 * @return the value of RL_URGE.OPERATOR_NAME
	 * 
	 * @abatorgenerated
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.OPERATOR_NAME
	 * 
	 * @param operatorName the value for RL_URGE.OPERATOR_NAME
	 * 
	 * @abatorgenerated
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.OPERATOR_ID
	 * 
	 * @return the value of RL_URGE.OPERATOR_ID
	 * 
	 * @abatorgenerated
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.OPERATOR_ID
	 * 
	 * @param operatorId the value for RL_URGE.OPERATOR_ID
	 * 
	 * @abatorgenerated
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.OPERATE_TIME
	 * 
	 * @return the value of RL_URGE.OPERATE_TIME
	 * 
	 * @abatorgenerated
	 */
	public Date getOperateTime() {
		return operateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.OPERATE_TIME
	 * 
	 * @param operateTime the value for RL_URGE.OPERATE_TIME
	 * 
	 * @abatorgenerated
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.REPAYMENT_CYCLE
	 * 
	 * @return the value of RL_URGE.REPAYMENT_CYCLE
	 * 
	 * @abatorgenerated
	 */
	public String getRepaymentCycle() {
		return repaymentCycle;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.REPAYMENT_CYCLE
	 * 
	 * @param repaymentCycle the value for RL_URGE.REPAYMENT_CYCLE
	 * 
	 * @abatorgenerated
	 */
	public void setRepaymentCycle(String repaymentCycle) {
		this.repaymentCycle = repaymentCycle == null ? null : repaymentCycle.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_SUMMARIZE
	 * 
	 * @return the value of RL_URGE.URGE_SUMMARIZE
	 * 
	 * @abatorgenerated
	 */
	public String getUrgeSummarize() {
		return urgeSummarize;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.URGE_SUMMARIZE
	 * 
	 * @param urgeSummarize the value for RL_URGE.URGE_SUMMARIZE
	 * 
	 * @abatorgenerated
	 */
	public void setUrgeSummarize(String urgeSummarize) {
		this.urgeSummarize = urgeSummarize == null ? null : urgeSummarize.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_WAY
	 * 
	 * @return the value of RL_URGE.URGE_WAY
	 * 
	 * @abatorgenerated
	 */
	public String getUrgeWay() {
		return urgeWay;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.URGE_WAY
	 * 
	 * @param urgeWay the value for RL_URGE.URGE_WAY
	 * 
	 * @abatorgenerated
	 */
	public void setUrgeWay(String urgeWay) {
		this.urgeWay = urgeWay == null ? null : urgeWay.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.REGIN_MONEY
	 * 
	 * @return the value of RL_URGE.REGIN_MONEY
	 * 
	 * @abatorgenerated
	 */
	public BigDecimal getReginMoney() {
		return reginMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.REGIN_MONEY
	 * 
	 * @param reginMoney the value for RL_URGE.REGIN_MONEY
	 * 
	 * @abatorgenerated
	 */
	public void setReginMoney(BigDecimal reginMoney) {
		this.reginMoney = reginMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.MONEY_SOURCE
	 * 
	 * @return the value of RL_URGE.MONEY_SOURCE
	 * 
	 * @abatorgenerated
	 */
	public String getMoneySource() {
		return moneySource;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.MONEY_SOURCE
	 * 
	 * @param moneySource the value for RL_URGE.MONEY_SOURCE
	 * 
	 * @abatorgenerated
	 */
	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource == null ? null : moneySource.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_STATE
	 * 
	 * @return the value of RL_URGE.URGE_STATE
	 * 
	 * @abatorgenerated
	 */
	public String getUrgeState() {
		return urgeState;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.URGE_STATE
	 * 
	 * @param urgeState the value for RL_URGE.URGE_STATE
	 * 
	 * @abatorgenerated
	 */
	public void setUrgeState(String urgeState) {
		this.urgeState = urgeState == null ? null : urgeState.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_DATE
	 * 
	 * @return the value of RL_URGE.URGE_DATE
	 * 
	 * @abatorgenerated
	 */
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getUrgeDate() {
		return urgeDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.URGE_DATE
	 * 
	 * @param urgeDate the value for RL_URGE.URGE_DATE
	 * 
	 * @abatorgenerated
	 */
	public void setUrgeDate(Date urgeDate) {
		this.urgeDate = urgeDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.REFUND_DATE
	 * 
	 * @return the value of RL_URGE.REFUND_DATE
	 * 
	 * @abatorgenerated
	 */
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRefundDate() {
		return refundDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.REFUND_DATE
	 * 
	 * @param refundDate the value for RL_URGE.REFUND_DATE
	 * 
	 * @abatorgenerated
	 */
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_LONG_TIME
	 * 
	 * @return the value of RL_URGE.URGE_LONG_TIME
	 * 
	 * @abatorgenerated
	 */
	

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_REMARK
	 * 
	 * @return the value of RL_URGE.URGE_REMARK
	 * 
	 * @abatorgenerated
	 */
	public String getUrgeRemark() {
		return urgeRemark;
	}

	public String getUrgeLongTime() {
		return urgeLongTime;
	}

	public void setUrgeLongTime(String urgeLongTime) {
		this.urgeLongTime = urgeLongTime;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.URGE_REMARK
	 * 
	 * @param urgeRemark the value for RL_URGE.URGE_REMARK
	 * 
	 * @abatorgenerated
	 */
	public void setUrgeRemark(String urgeRemark) {
		this.urgeRemark = urgeRemark == null ? null : urgeRemark.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.BIG_WARNING
	 * 
	 * @return the value of RL_URGE.BIG_WARNING
	 * 
	 * @abatorgenerated
	 */
	public String getBigWarning() {
		return bigWarning;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.BIG_WARNING
	 * 
	 * @param bigWarning the value for RL_URGE.BIG_WARNING
	 * 
	 * @abatorgenerated
	 */
	public void setBigWarning(String bigWarning) {
		this.bigWarning = bigWarning == null ? null : bigWarning.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.URGE_DESCRIBE
	 * 
	 * @return the value of RL_URGE.URGE_DESCRIBE
	 * 
	 * @abatorgenerated
	 */
	public String getUrgeDescribe() {
		return urgeDescribe;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.URGE_DESCRIBE
	 * 
	 * @param urgeDescribe the value for RL_URGE.URGE_DESCRIBE
	 * 
	 * @abatorgenerated
	 */
	public void setUrgeDescribe(String urgeDescribe) {
		this.urgeDescribe = urgeDescribe == null ? null : urgeDescribe.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.YN_PROMISE
	 * 
	 * @return the value of RL_URGE.YN_PROMISE
	 * 
	 * @abatorgenerated
	 */
	public String getYnPromise() {
		return ynPromise;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.YN_PROMISE
	 * 
	 * @param ynPromise the value for RL_URGE.YN_PROMISE
	 * 
	 * @abatorgenerated
	 */
	public void setYnPromise(String ynPromise) {
		this.ynPromise = ynPromise == null ? null : ynPromise.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.PREVIOUS_PROMISE_MONEY
	 * 
	 * @return the value of RL_URGE.PREVIOUS_PROMISE_MONEY
	 * 
	 * @abatorgenerated
	 */
	public BigDecimal getPreviousPromiseMoney() {
		return previousPromiseMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.PREVIOUS_PROMISE_MONEY
	 * 
	 * @param previousPromiseMoney the value for RL_URGE.PREVIOUS_PROMISE_MONEY
	 * 
	 * @abatorgenerated
	 */
	public void setPreviousPromiseMoney(BigDecimal previousPromiseMoney) {
		this.previousPromiseMoney = previousPromiseMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.PREVIOUS_PROMISE_TIME
	 * 
	 * @return the value of RL_URGE.PREVIOUS_PROMISE_TIME
	 * 
	 * @abatorgenerated
	 */

	public Date getPreviousPromiseTime() {
		return previousPromiseTime;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.PREVIOUS_PROMISE_TIME
	 * 
	 * @param previousPromiseTime the value for RL_URGE.PREVIOUS_PROMISE_TIME
	 * 
	 * @abatorgenerated
	 */
	public void setPreviousPromiseTime(Date previousPromiseTime) {
		this.previousPromiseTime = previousPromiseTime;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.CURRENT_PROMISE_MONEY
	 * 
	 * @return the value of RL_URGE.CURRENT_PROMISE_MONEY
	 * 
	 * @abatorgenerated
	 */
	public BigDecimal getCurrentPromiseMoney() {
		return currentPromiseMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.CURRENT_PROMISE_MONEY
	 * 
	 * @param currentPromiseMoney the value for RL_URGE.CURRENT_PROMISE_MONEY
	 * 
	 * @abatorgenerated
	 */
	public void setCurrentPromiseMoney(BigDecimal currentPromiseMoney) {
		this.currentPromiseMoney = currentPromiseMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_URGE.CURENT_PROMISE_TIME
	 * 
	 * @return the value of RL_URGE.CURENT_PROMISE_TIME
	 * 
	 * @abatorgenerated
	 */
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getCurentPromiseTime() {
		return curentPromiseTime;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_URGE.CURENT_PROMISE_TIME
	 * 
	 * @param curentPromiseTime the value for RL_URGE.CURENT_PROMISE_TIME
	 * 
	 * @abatorgenerated
	 */
	public void setCurentPromiseTime(Date curentPromiseTime) {
		this.curentPromiseTime = curentPromiseTime;
	}

}
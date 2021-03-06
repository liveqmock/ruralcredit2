package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class AcceptConsultRollback {
	/**
	 * This field was generated by Abator for iBATIS.
	 * This field corresponds to the database column MFC_ACCEPT_CONSULT_ROLLBACK.ACCEPT_CONSULT_ROLLBACK_ID
	 * 
	 * @abatorgenerated
	 */
	private Long acceptConsultRollbackId;

	/**
	 * This field was generated by Abator for iBATIS.
	 * This field corresponds to the database column MFC_ACCEPT_CONSULT_ROLLBACK.CONSULT_POOL_ID
	 * 
	 * @abatorgenerated
	 */
	private Long consultPoolId;

	/**
	 * This field was generated by Abator for iBATIS.
	 * This field corresponds to the database column MFC_ACCEPT_CONSULT_ROLLBACK.ROLLBACK_REASON
	 * 
	 * @abatorgenerated
	 */
	private String rollbackReason;

	/**
	 * This field was generated by Abator for iBATIS.
	 * This field corresponds to the database column MFC_ACCEPT_CONSULT_ROLLBACK.OPERATOR
	 * 
	 * @abatorgenerated
	 */
	private String operator;

	/**
	 * This field was generated by Abator for iBATIS.
	 * This field corresponds to the database column MFC_ACCEPT_CONSULT_ROLLBACK.OPERATE_DATE
	 * 
	 * @abatorgenerated
	 */
	private Date operateDate;

	/**
	 * This field was generated by Abator for iBATIS.
	 * This field corresponds to the database column MFC_ACCEPT_CONSULT_ROLLBACK.HISTORY_FLAG
	 * 
	 * @abatorgenerated
	 */
	private String historyFlag;

	private String operatorId;	//操作人Id
	private String operatorBeforeState;//操作前状态
	private String operatorAfterState; 	//操作后状态
	private String operatorBeforeSection;		//操作前Section
	private String operatorAfterSection;	//操作后Section
	private String applyOperator;			//记录申请操作标识
	
	
	

	public String getApplyOperator() {
		return applyOperator;
	}

	public void setApplyOperator(String applyOperator) {
		this.applyOperator = applyOperator;
	}

	public String getOperatorBeforeSection() {
		return operatorBeforeSection;
	}

	public void setOperatorBeforeSection(String operatorBeforeSection) {
		this.operatorBeforeSection = operatorBeforeSection;
	}

	public String getOperatorAfterSection() {
		return operatorAfterSection;
	}

	public void setOperatorAfterSection(String operatorAfterSection) {
		this.operatorAfterSection = operatorAfterSection;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorBeforeState() {
		return operatorBeforeState;
	}

	public void setOperatorBeforeState(String operatorBeforeState) {
		this.operatorBeforeState = operatorBeforeState;
	}

	public String getOperatorAfterState() {
		return operatorAfterState;
	}

	public void setOperatorAfterState(String operatorAfterState) {
		this.operatorAfterState = operatorAfterState;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.ACCEPT_CONSULT_ROLLBACK_ID
	 * 
	 * @return the value of MFC_ACCEPT_CONSULT_ROLLBACK.ACCEPT_CONSULT_ROLLBACK_ID
	 * 
	 * @abatorgenerated
	 */
	public Long getAcceptConsultRollbackId() {
		return acceptConsultRollbackId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.ACCEPT_CONSULT_ROLLBACK_ID
	 * 
	 * @param acceptConsultRollbackId the value for MFC_ACCEPT_CONSULT_ROLLBACK.ACCEPT_CONSULT_ROLLBACK_ID
	 * 
	 * @abatorgenerated
	 */
	public void setAcceptConsultRollbackId(Long acceptConsultRollbackId) {
		this.acceptConsultRollbackId = acceptConsultRollbackId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.CONSULT_POOL_ID
	 * 
	 * @return the value of MFC_ACCEPT_CONSULT_ROLLBACK.CONSULT_POOL_ID
	 * 
	 * @abatorgenerated
	 */
	public Long getConsultPoolId() {
		return consultPoolId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.CONSULT_POOL_ID
	 * 
	 * @param consultPoolId the value for MFC_ACCEPT_CONSULT_ROLLBACK.CONSULT_POOL_ID
	 * 
	 * @abatorgenerated
	 */
	public void setConsultPoolId(Long consultPoolId) {
		this.consultPoolId = consultPoolId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.ROLLBACK_REASON
	 * 
	 * @return the value of MFC_ACCEPT_CONSULT_ROLLBACK.ROLLBACK_REASON
	 * 
	 * @abatorgenerated
	 */
	public String getRollbackReason() {
		return rollbackReason;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.ROLLBACK_REASON
	 * 
	 * @param rollbackReason the value for MFC_ACCEPT_CONSULT_ROLLBACK.ROLLBACK_REASON
	 * 
	 * @abatorgenerated
	 */
	public void setRollbackReason(String rollbackReason) {
		this.rollbackReason = rollbackReason == null ? null : rollbackReason.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.OPERATOR
	 * 
	 * @return the value of MFC_ACCEPT_CONSULT_ROLLBACK.OPERATOR
	 * 
	 * @abatorgenerated
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.OPERATOR
	 * 
	 * @param operator the value for MFC_ACCEPT_CONSULT_ROLLBACK.OPERATOR
	 * 
	 * @abatorgenerated
	 */
	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.OPERATE_DATE
	 * 
	 * @return the value of MFC_ACCEPT_CONSULT_ROLLBACK.OPERATE_DATE
	 * 
	 * @abatorgenerated
	 */
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getOperateDate() {
		return operateDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.OPERATE_DATE
	 * 
	 * @param operateDate the value for MFC_ACCEPT_CONSULT_ROLLBACK.OPERATE_DATE
	 * 
	 * @abatorgenerated
	 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.HISTORY_FLAG
	 * 
	 * @return the value of MFC_ACCEPT_CONSULT_ROLLBACK.HISTORY_FLAG
	 * 
	 * @abatorgenerated
	 */
	public String getHistoryFlag() {
		return historyFlag;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column MFC_ACCEPT_CONSULT_ROLLBACK.HISTORY_FLAG
	 * 
	 * @param historyFlag the value for MFC_ACCEPT_CONSULT_ROLLBACK.HISTORY_FLAG
	 * 
	 * @abatorgenerated
	 */
	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag == null ? null : historyFlag.trim();
	}
}
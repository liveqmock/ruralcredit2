package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author Administrator
 * 
 */
public class FinanceMoney {

	private Integer financeMoneyId;// 财务预约记录表ID

	private String type;// 财务类型(F:付款;S:收款;U:撤销)

	private Integer associationId;// 关联表ID(打款记录id/收款记录id/放款失败记录id)

	private String payWay;// 支付方式(N:网银netbank;D:划扣deduct)

	private String financeStatus;// 财务状态(0:未预约;1:已预约;2:成功;3:失败;4:撤销;5:作废)

	private String operatorId;// OPERATOR_ID

	private String operatorName;// 操作人姓名

	private Date operateDate;// 操作日期

	private String remark;// REMARK

	private String historyFlag;// 历史记录标志(T:历史记录;F:当前记录)

	private String bizId;// 订单号

	private String systemSourceId;// 系统来源ID

	private String chnId;// 渠道ID

	private String openBankName;// 开户行名称

	private String productId;// 产品ID

	private String bankId;// 银行行别代码

	private String accountFlag;// 卡拆标志

	private String accountNo;// 账号

	private String accountName;// 账户名称

	private String amount;// 收款金额

	private String cardKind;// 证件类别

	private String idCard;// 证件号码

	private String specialId;// 特殊标识

	private String mobile;// 手机号

	private Date tradeTime;// 收款执行时间

	private String returnMsg;// 返回信息

	private Date reserveTime;// 预约时间
	private String revocationName;// 撤销人
	private Date revocationTime;// 撤销时间

	private Integer creditapplicationId;// 信贷申请ID

	private String reserveId;// 预约编号（贷后用）

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public String getRevocationName() {
		return revocationName;
	}

	public void setRevocationName(String revocationName) {
		this.revocationName = revocationName;
	}

	public Date getRevocationTime() {
		return revocationTime;
	}

	public void setRevocationTime(Date revocationTime) {
		this.revocationTime = revocationTime;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.FINANCE_MONEY_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.FINANCE_MONEY_ID
	 * 
	 * @abatorgenerated
	 */
	public Integer getFinanceMoneyId() {
		return financeMoneyId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.FINANCE_MONEY_ID
	 * 
	 * @param financeMoneyId the value for RL_FINANCE_MONEY.FINANCE_MONEY_ID
	 * 
	 * @abatorgenerated
	 */
	public void setFinanceMoneyId(Integer financeMoneyId) {
		this.financeMoneyId = financeMoneyId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.TYPE
	 * 
	 * @return the value of RL_FINANCE_MONEY.TYPE
	 * 
	 * @abatorgenerated
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.TYPE
	 * 
	 * @param type the value for RL_FINANCE_MONEY.TYPE
	 * 
	 * @abatorgenerated
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.ASSOCIATION_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.ASSOCIATION_ID
	 * 
	 * @abatorgenerated
	 */
	public Integer getAssociationId() {
		return associationId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.ASSOCIATION_ID
	 * 
	 * @param associationId the value for RL_FINANCE_MONEY.ASSOCIATION_ID
	 * 
	 * @abatorgenerated
	 */
	public void setAssociationId(Integer associationId) {
		this.associationId = associationId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.PAY_WAY
	 * 
	 * @return the value of RL_FINANCE_MONEY.PAY_WAY
	 * 
	 * @abatorgenerated
	 */
	public String getPayWay() {
		return payWay;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.PAY_WAY
	 * 
	 * @param payWay the value for RL_FINANCE_MONEY.PAY_WAY
	 * 
	 * @abatorgenerated
	 */
	public void setPayWay(String payWay) {
		this.payWay = payWay == null ? null : payWay.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.FINANCE_STATUS
	 * 
	 * @return the value of RL_FINANCE_MONEY.FINANCE_STATUS
	 * 
	 * @abatorgenerated
	 */
	public String getFinanceStatus() {
		return financeStatus;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.FINANCE_STATUS
	 * 
	 * @param financeStatus the value for RL_FINANCE_MONEY.FINANCE_STATUS
	 * 
	 * @abatorgenerated
	 */
	public void setFinanceStatus(String financeStatus) {
		this.financeStatus = financeStatus == null ? null : financeStatus.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.OPERATOR_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.OPERATOR_ID
	 * 
	 * @abatorgenerated
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.OPERATOR_ID
	 * 
	 * @param operatorId the value for RL_FINANCE_MONEY.OPERATOR_ID
	 * 
	 * @abatorgenerated
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId == null ? null : operatorId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.OPERATOR_NAME
	 * 
	 * @return the value of RL_FINANCE_MONEY.OPERATOR_NAME
	 * 
	 * @abatorgenerated
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.OPERATOR_NAME
	 * 
	 * @param operatorName the value for RL_FINANCE_MONEY.OPERATOR_NAME
	 * 
	 * @abatorgenerated
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.OPERATE_DATE
	 * 
	 * @return the value of RL_FINANCE_MONEY.OPERATE_DATE
	 * 
	 * @abatorgenerated
	 */
	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getOperateDate() {
		return operateDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.OPERATE_DATE
	 * 
	 * @param operateDate the value for RL_FINANCE_MONEY.OPERATE_DATE
	 * 
	 * @abatorgenerated
	 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.REMARK
	 * 
	 * @return the value of RL_FINANCE_MONEY.REMARK
	 * 
	 * @abatorgenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.REMARK
	 * 
	 * @param remark the value for RL_FINANCE_MONEY.REMARK
	 * 
	 * @abatorgenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.HISTORY_FLAG
	 * 
	 * @return the value of RL_FINANCE_MONEY.HISTORY_FLAG
	 * 
	 * @abatorgenerated
	 */
	public String getHistoryFlag() {
		return historyFlag;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.HISTORY_FLAG
	 * 
	 * @param historyFlag the value for RL_FINANCE_MONEY.HISTORY_FLAG
	 * 
	 * @abatorgenerated
	 */
	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag == null ? null : historyFlag.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.BIZ_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.BIZ_ID
	 * 
	 * @abatorgenerated
	 */
	public String getBizId() {
		return bizId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.BIZ_ID
	 * 
	 * @param bizId the value for RL_FINANCE_MONEY.BIZ_ID
	 * 
	 * @abatorgenerated
	 */
	public void setBizId(String bizId) {
		this.bizId = bizId == null ? null : bizId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.SYSTEM_SOURCE_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.SYSTEM_SOURCE_ID
	 * 
	 * @abatorgenerated
	 */
	public String getSystemSourceId() {
		return systemSourceId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.SYSTEM_SOURCE_ID
	 * 
	 * @param systemSourceId the value for RL_FINANCE_MONEY.SYSTEM_SOURCE_ID
	 * 
	 * @abatorgenerated
	 */
	public void setSystemSourceId(String systemSourceId) {
		this.systemSourceId = systemSourceId == null ? null : systemSourceId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.CHN_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.CHN_ID
	 * 
	 * @abatorgenerated
	 */
	public String getChnId() {
		return chnId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.CHN_ID
	 * 
	 * @param chnId the value for RL_FINANCE_MONEY.CHN_ID
	 * 
	 * @abatorgenerated
	 */
	public void setChnId(String chnId) {
		this.chnId = chnId == null ? null : chnId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.OPEN_BANK_NAME
	 * 
	 * @return the value of RL_FINANCE_MONEY.OPEN_BANK_NAME
	 * 
	 * @abatorgenerated
	 */
	public String getOpenBankName() {
		return openBankName;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.OPEN_BANK_NAME
	 * 
	 * @param openBankName the value for RL_FINANCE_MONEY.OPEN_BANK_NAME
	 * 
	 * @abatorgenerated
	 */
	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName == null ? null : openBankName.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.PRODUCT_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.PRODUCT_ID
	 * 
	 * @abatorgenerated
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.PRODUCT_ID
	 * 
	 * @param productId the value for RL_FINANCE_MONEY.PRODUCT_ID
	 * 
	 * @abatorgenerated
	 */
	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.BANK_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.BANK_ID
	 * 
	 * @abatorgenerated
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.BANK_ID
	 * 
	 * @param bankId the value for RL_FINANCE_MONEY.BANK_ID
	 * 
	 * @abatorgenerated
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId == null ? null : bankId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.ACCOUNT_FLAG
	 * 
	 * @return the value of RL_FINANCE_MONEY.ACCOUNT_FLAG
	 * 
	 * @abatorgenerated
	 */
	public String getAccountFlag() {
		return accountFlag;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.ACCOUNT_FLAG
	 * 
	 * @param accountFlag the value for RL_FINANCE_MONEY.ACCOUNT_FLAG
	 * 
	 * @abatorgenerated
	 */
	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag == null ? null : accountFlag.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.ACCOUNT_NO
	 * 
	 * @return the value of RL_FINANCE_MONEY.ACCOUNT_NO
	 * 
	 * @abatorgenerated
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.ACCOUNT_NO
	 * 
	 * @param accountNo the value for RL_FINANCE_MONEY.ACCOUNT_NO
	 * 
	 * @abatorgenerated
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo == null ? null : accountNo.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.ACCOUNT_NAME
	 * 
	 * @return the value of RL_FINANCE_MONEY.ACCOUNT_NAME
	 * 
	 * @abatorgenerated
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.ACCOUNT_NAME
	 * 
	 * @param accountName the value for RL_FINANCE_MONEY.ACCOUNT_NAME
	 * 
	 * @abatorgenerated
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.AMOUNT
	 * 
	 * @return the value of RL_FINANCE_MONEY.AMOUNT
	 * 
	 * @abatorgenerated
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.AMOUNT
	 * 
	 * @param amount the value for RL_FINANCE_MONEY.AMOUNT
	 * 
	 * @abatorgenerated
	 */
	public void setAmount(String amount) {
		this.amount = amount == null ? null : amount.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.CARD_KIND
	 * 
	 * @return the value of RL_FINANCE_MONEY.CARD_KIND
	 * 
	 * @abatorgenerated
	 */
	public String getCardKind() {
		return cardKind;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.CARD_KIND
	 * 
	 * @param cardKind the value for RL_FINANCE_MONEY.CARD_KIND
	 * 
	 * @abatorgenerated
	 */
	public void setCardKind(String cardKind) {
		this.cardKind = cardKind == null ? null : cardKind.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.ID_CARD
	 * 
	 * @return the value of RL_FINANCE_MONEY.ID_CARD
	 * 
	 * @abatorgenerated
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.ID_CARD
	 * 
	 * @param idCard the value for RL_FINANCE_MONEY.ID_CARD
	 * 
	 * @abatorgenerated
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard == null ? null : idCard.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.SPECIAL_ID
	 * 
	 * @return the value of RL_FINANCE_MONEY.SPECIAL_ID
	 * 
	 * @abatorgenerated
	 */
	public String getSpecialId() {
		return specialId;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.SPECIAL_ID
	 * 
	 * @param specialId the value for RL_FINANCE_MONEY.SPECIAL_ID
	 * 
	 * @abatorgenerated
	 */
	public void setSpecialId(String specialId) {
		this.specialId = specialId == null ? null : specialId.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method returns the value of the database column RL_FINANCE_MONEY.MOBILE
	 * 
	 * @return the value of RL_FINANCE_MONEY.MOBILE
	 * 
	 * @abatorgenerated
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method sets the value of the database column RL_FINANCE_MONEY.MOBILE
	 * 
	 * @param mobile the value for RL_FINANCE_MONEY.MOBILE
	 * 
	 * @abatorgenerated
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

}
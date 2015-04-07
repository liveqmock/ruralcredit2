package com.creditease.rc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author haoqiang
 * 
 */
public class LocalReturnDTO implements Serializable {

	private String reserveId; // 预约编号
	private String applyId; // 借款申请编号
	private Date destineDate; // 预约还款时间
	private Double destineAmount; // 预约还款金额
	private String ifPayAhead; // 是否一次性提前还款
	// private String signInfo; // 摘要信息
	private String returnType; // 还款方式
	private String bankBook; // 卡折标志
	private String bankAccountName; // 银行账户名称
	private String bankAccount; // 银行账号
	private String bankName; // 银行名称
	private String bankIdnumber; // 持卡人身份证
	// private String singleRtReceipt; // 是否实时划扣
	private String sellId; // 客户经理编号
	/** 用于存在于自己系统的属性 **/
	private Long receivedRecordId;// 收款登记ID
	private String bankId;// 本系统存的银行代码
	private String productId;// 本系统存的产品ID
	private String idCard;// 证件号码
	private String mobile;// 手机号码
	private String businessNumber;// 业务单号
	private String bizId;// （用于结算平台）订单号

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Long getReceivedRecordId() {
		return receivedRecordId;
	}

	public void setReceivedRecordId(Long receivedRecordId) {
		this.receivedRecordId = receivedRecordId;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public Date getDestineDate() {
		return destineDate;
	}

	public void setDestineDate(Date destineDate) {
		this.destineDate = destineDate;
	}

	public Double getDestineAmount() {
		return destineAmount;
	}

	public void setDestineAmount(Double destineAmount) {
		this.destineAmount = destineAmount;
	}

	public String getIfPayAhead() {
		return ifPayAhead;
	}

	public void setIfPayAhead(String ifPayAhead) {
		this.ifPayAhead = ifPayAhead;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getBankBook() {
		return bankBook;
	}

	public void setBankBook(String bankBook) {
		this.bankBook = bankBook;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankIdnumber() {
		return bankIdnumber;
	}

	public void setBankIdnumber(String bankIdnumber) {
		this.bankIdnumber = bankIdnumber;
	}

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

}

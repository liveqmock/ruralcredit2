package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author haoqiang
 *
 */
public class LocalChgReturnTypeRequest implements Serializable {

	private String applyId; // 借款申请编号

	private String returnType; // 还款方式

	private String bankAccount; // 还款银行账号

	private String bankClientName; // 还款人银行户名

	private String bankName; // 还款开户行名称

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankClientName() {
		return bankClientName;
	}

	public void setBankClientName(String bankClientName) {
		this.bankClientName = bankClientName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}

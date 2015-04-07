package com.creditease.rc.app.sia.vo;

import java.util.List;

import com.creditease.rc.app.sia.util.CMQMessageRequest;

public class BorrowerApplyFormInfoReq extends CMQMessageRequest{
	
	private String systemCode;		//系统代码
	private String interfaceCode;	//接口代码
	private String applyCode;		//综合信贷唯一业务流水号（更新时带上）
	private BorrowerTransactionVo transactionVo;//进件信息
	private List<BorrowerBasicInfoVo> borrowerBasicInfoVo;//借款人信息
	
	public BorrowerTransactionVo getTransactionVo() {
		return transactionVo;
	}
	public void setTransactionVo(BorrowerTransactionVo transactionVo) {
		this.transactionVo = transactionVo;
	}
	public List<BorrowerBasicInfoVo> getBorrowerBasicInfoVo() {
		return borrowerBasicInfoVo;
	}
	public void setBorrowerBasicInfoVo(List<BorrowerBasicInfoVo> borrowerBasicInfoVo) {
		this.borrowerBasicInfoVo = borrowerBasicInfoVo;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	public String getApplyCode() {
		return applyCode;
	}
	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
}

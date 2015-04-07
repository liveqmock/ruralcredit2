package com.creditease.rc.vo;


public class ReceiveCreditMsg {

	private ReceiveCreditObjList receiveCreditObjList;// 系统更新信息集合
	private String signInfo; // 系统加密标识
	private String sysId; // 系统标识

	public ReceiveCreditObjList getReceiveCreditObjList() {
		return receiveCreditObjList;
	}

	public void setReceiveCreditObjList(ReceiveCreditObjList receiveCreditObjList) {
		this.receiveCreditObjList = receiveCreditObjList;
	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

}

package com.creditease.rc.vo;

public class NewApplicationInfo extends ApplicationInfo {

	// 共同借款人人法网查询标志（0：未查询过；1：查询过）
	private String comCourtQueryFlag;

	// 新增申请人人法网查询结果标识，0:无此人信息;1:存在查询结果;2:接口异常
	private String courtQueryResultFlag;

	public String getCourtQueryResultFlag() {
		return courtQueryResultFlag;
	}

	public void setCourtQueryResultFlag(String courtQueryResultFlag) {
		this.courtQueryResultFlag = courtQueryResultFlag;
	}

	public String getComCourtQueryFlag() {
		return comCourtQueryFlag;
	}

	public void setComCourtQueryFlag(String comCourtQueryFlag) {
		this.comCourtQueryFlag = comCourtQueryFlag;
	}
}

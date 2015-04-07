package com.creditease.rc.vo;

public class QyScoreRequestParam {

	private String userCode		; 	// 用户编码
	private String answerDate	; 	// 答題日期yyyy-mm-dd

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

}

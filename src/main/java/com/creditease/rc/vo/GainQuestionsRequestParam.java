package com.creditease.rc.vo;

public class GainQuestionsRequestParam {
	private String userCode; // 用户编码
	private String levelType;// 1:简单2：困难

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

}

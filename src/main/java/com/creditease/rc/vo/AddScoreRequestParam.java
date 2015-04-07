package com.creditease.rc.vo;

public class AddScoreRequestParam {

	private String 	userCode	;//用户编码
	private int 	score		;//加减分数

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}

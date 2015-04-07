package com.creditease.rc.vo;

import java.util.List;

public class AnswerCompleteRequestParam {

	private String 		userCode			; // 用户编码
	private String  	loginType           ; // 登陆类型	0-vip；1-伪球迷；2-资深球迷
	private List<QandA> qandAs				; // 题目和答案
	
	
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public List<QandA> getQandAs() {
		return qandAs;
	}
	public void setQandAs(List<QandA> qandAs) {
		this.qandAs = qandAs;
	}
	
	
}

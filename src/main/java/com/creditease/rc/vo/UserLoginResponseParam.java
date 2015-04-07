package com.creditease.rc.vo;

import java.util.List;

public class UserLoginResponseParam extends WebServiceMessage {
// 0-登陆成功；1-农贷系统内部错误 ； 2-今天已经答过题了；3-不是农贷系统用户
	private List<Question> questions ;//题目

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}

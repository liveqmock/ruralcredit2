package com.creditease.rc.vo;

import java.util.List;

public class GainQuestionsResponseParam extends WebServiceMessage {

	private List<Question> questions;// 题目

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}

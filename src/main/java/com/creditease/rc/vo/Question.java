package com.creditease.rc.vo;

import java.util.List;

public class Question {

	private Long 		 wxQuestionId	; //题目主键ID
    private String 		 type			; //题目类型：Y-宜信；R-容易；N-困难
    private String 		 question		; //题目内容
	private List<Answer> answers		; // 答案

	
	
	public Long getWxQuestionId() {
		return wxQuestionId;
	}

	public void setWxQuestionId(Long wxQuestionId) {
		this.wxQuestionId = wxQuestionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}

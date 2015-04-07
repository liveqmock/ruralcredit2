package com.creditease.rc.domain;

public class WxQuestion {
    private Long 	wxQuestionId	; //题目主键ID
    private String 	type			; //题目类型：Y-宜信；R-容易；N-困难
    private String 	question		; //题目内容
    
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

    
    
}
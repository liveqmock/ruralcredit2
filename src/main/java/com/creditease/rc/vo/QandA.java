package com.creditease.rc.vo;

public class QandA {

	private Long 	questionId	; // 题目主键ID
	private String 	question	; // 题目内容
	private Long 	answerId	; // 答案主键ID
	private String 	answer		; // 答案内容
	private String  trueFlag    ; // 正确答案标识：0-正确；1-错误
	private String 	type		; // 题目类型：Y-宜信；R-容易；N-困难

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTrueFlag() {
		return trueFlag;
	}

	public void setTrueFlag(String trueFlag) {
		this.trueFlag = trueFlag;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}

package com.creditease.rc.domain;

public class WxAnswer {
    private Long 		wxAnswerId		; //答案主键ID
    private Long 		wxQuestionId	; //题目表主键
    private String 		answer			; //答案内容
    private String 		trueFlag		; //正确答案标识：0-正确；1-错误

	public Long getWxAnswerId() {
		return wxAnswerId;
	}

	public void setWxAnswerId(Long wxAnswerId) {
		this.wxAnswerId = wxAnswerId;
	}

	public Long getWxQuestionId() {
		return wxQuestionId;
	}

	public void setWxQuestionId(Long wxQuestionId) {
		this.wxQuestionId = wxQuestionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getTrueFlag() {
		return trueFlag;
	}

	public void setTrueFlag(String trueFlag) {
		this.trueFlag = trueFlag;
	}

    
    
}
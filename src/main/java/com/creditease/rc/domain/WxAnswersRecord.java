package com.creditease.rc.domain;

import java.util.Date;

public class WxAnswersRecord {
    private Long 		wxAnswersRecordId	; //答题记录主键ID
    private Long 		wxUserId			; //用户表主键
    private Long 		wxQuestionId		; //题目主键
    private String 		isCorrect			; //是否正确：0-正确；1-错误
    private Long 		score				; //普通得分情况
    private Long        vipScore            ; //VIP得分情况
    private Date 		answerDate			; //答题日期
    
    
	public Long getVipScore() {
		return vipScore;
	}
	public void setVipScore(Long vipScore) {
		this.vipScore = vipScore;
	}
	public Long getWxAnswersRecordId() {
		return wxAnswersRecordId;
	}
	public void setWxAnswersRecordId(Long wxAnswersRecordId) {
		this.wxAnswersRecordId = wxAnswersRecordId;
	}
	public Long getWxUserId() {
		return wxUserId;
	}
	public void setWxUserId(Long wxUserId) {
		this.wxUserId = wxUserId;
	}
	public Long getWxQuestionId() {
		return wxQuestionId;
	}
	public void setWxQuestionId(Long wxQuestionId) {
		this.wxQuestionId = wxQuestionId;
	}
	public String getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

    
}
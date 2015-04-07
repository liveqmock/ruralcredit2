package com.creditease.rc.domain;

import java.util.Date;

public class WxUser {
	
    private Long 	wxUserId	       ; //用户主键ID
    private String 	userCode	       ; //用户编码
    private String 	name		       ; //姓名
    private String 	phone		       ; //电话号码
    private Date 	loginDate	       ; //登陆日期
    private Date 	answerDate	       ; //答题日期
    private Long 	score		       ; //积分
    private String  identityCardSub	   ; //身份证后四位
    private Long    aggregateScore     ; //总积分
    private Long    usedScore         ; //使用积分
    
    
	public Long getUsedScore() {
		return usedScore;
	}
	public void setUsedScore(Long usedScore) {
		this.usedScore = usedScore;
	}
	public Long getAggregateScore() {
		return aggregateScore;
	}
	public void setAggregateScore(Long aggregateScore) {
		this.aggregateScore = aggregateScore;
	}
	public String getIdentityCardSub() {
		return identityCardSub;
	}
	public void setIdentityCardSub(String identityCardSub) {
		this.identityCardSub = identityCardSub;
	}
	public Long getWxUserId() {
		return wxUserId;
	}
	public void setWxUserId(Long wxUserId) {
		this.wxUserId = wxUserId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}

    
}
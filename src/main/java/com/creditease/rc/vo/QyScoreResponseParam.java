package com.creditease.rc.vo;

import java.math.BigDecimal;

public class QyScoreResponseParam extends WebServiceMessage{

	private String  name			; 			//客戶姓名
	private int     tComplete		;			//今日完成
	private int     tRight			;			//今日正確
	private int     tWrong			;			//今日錯誤
	private int     tVIPScore		;			//今日vip獎勵積分
	private int     aComplete		;			//綜合完成
	private int     aRight			;			//綜合正確
	private int     aWrong			;			//綜合錯誤
	private int     aVIPScore		;			//綜合vip獎勵積分
	private int     currentScore	;			//當前積分
	private int     currentRank		;			//當前排名
	private BigDecimal 	rightRate	;			//正确率
	
	
	
	
	public BigDecimal getRightRate() {
		return rightRate;
	}
	public void setRightRate(BigDecimal rightRate) {
		this.rightRate = rightRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int gettComplete() {
		return tComplete;
	}
	public void settComplete(int tComplete) {
		this.tComplete = tComplete;
	}
	public int gettRight() {
		return tRight;
	}
	public void settRight(int tRight) {
		this.tRight = tRight;
	}
	public int gettWrong() {
		return tWrong;
	}
	public void settWrong(int tWrong) {
		this.tWrong = tWrong;
	}
	public int gettVIPScore() {
		return tVIPScore;
	}
	public void settVIPScore(int tVIPScore) {
		this.tVIPScore = tVIPScore;
	}
	public int getaComplete() {
		return aComplete;
	}
	public void setaComplete(int aComplete) {
		this.aComplete = aComplete;
	}
	public int getaRight() {
		return aRight;
	}
	public void setaRight(int aRight) {
		this.aRight = aRight;
	}
	public int getaWrong() {
		return aWrong;
	}
	public void setaWrong(int aWrong) {
		this.aWrong = aWrong;
	}
	public int getaVIPScore() {
		return aVIPScore;
	}
	public void setaVIPScore(int aVIPScore) {
		this.aVIPScore = aVIPScore;
	}
	public int getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	public int getCurrentRank() {
		return currentRank;
	}
	public void setCurrentRank(int currentRank) {
		this.currentRank = currentRank;
	}
	
	
	
	
}

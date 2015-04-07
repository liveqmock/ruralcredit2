package com.creditease.rc.vo;

public class PrizeHasFlag extends Prize{

	private String convertFlag;//可兑奖标志 0:不可兑换；1：可兑换

	public String getConvertFlag() {
		return convertFlag;
	}

	public void setConvertFlag(String convertFlag) {
		this.convertFlag = convertFlag;
	}
	
	private String firstFlag;//	一等奖有没有兑换标识：0：不可兑换；1：可兑换

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}
	
	
	
}

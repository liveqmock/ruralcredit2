package com.creditease.rc.vo;

import java.math.BigDecimal;

public class Prize {

	
	private Long 		wxPrizeId		;  //奖品主键ID
    private String 		prizeType		;  //奖品类型：0-特等奖；1-一等奖；2-二等奖；3-普通奖
    private String 		prizeName		;  //奖品名称
    private Long 		prizeScore		;  //兑奖积分
    private BigDecimal 	prizeTotalCount	;  //奖品总数
    private String 		pictureAddress	;  //奖品图片地址
    private String      condition       ;  //兑换条件
    private String      prizeContent    ;  //奖品描述
    
    
	public String getPrizeContent() {
		return prizeContent;
	}
	public void setPrizeContent(String prizeContent) {
		this.prizeContent = prizeContent;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Long getWxPrizeId() {
		return wxPrizeId;
	}
	public void setWxPrizeId(Long wxPrizeId) {
		this.wxPrizeId = wxPrizeId;
	}
	public String getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public Long getPrizeScore() {
		return prizeScore;
	}
	public void setPrizeScore(Long prizeScore) {
		this.prizeScore = prizeScore;
	}
	public BigDecimal getPrizeTotalCount() {
		return prizeTotalCount;
	}
	public void setPrizeTotalCount(BigDecimal prizeTotalCount) {
		this.prizeTotalCount = prizeTotalCount;
	}
	public String getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}
    
    
}

package com.creditease.rc.domain;
/**
 * 
 * @author Administrator
 *
 */
public class ProcessMessage {
	private String noticeType      ;  //通知类型    
	private String state           ;  //订单状态    
	private String bizId           ;  //订单号     
	private String systemSourceId  ;  //系统来源ID  
	private String productId       ;  //产品ID    
	private String retCode         ;  //状态码     
	private String retInfo         ;  //状态信息    
	private String receiveTime     ;  //付款请求接收时间
	private String tradeTime       ;  //付款执行时间  
	private String completeTime    ;  //付款完成时间  
	private String signInfo        ;  //摘要信息    
	private Double successAmount   ;  //成功金额    

	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getSystemSourceId() {
		return systemSourceId;
	}
	public void setSystemSourceId(String systemSourceId) {
		this.systemSourceId = systemSourceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getSignInfo() {
		return signInfo;
	}
	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}
	public Double getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(Double successAmount) {
		this.successAmount = successAmount;
	}
	
}

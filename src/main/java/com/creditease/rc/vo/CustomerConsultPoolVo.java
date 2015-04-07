package com.creditease.rc.vo;

import java.util.Date;

import com.creditease.rc.domain.CustomerConsultPool;


public class CustomerConsultPoolVo extends CustomerConsultPool{

	private String 	connectionWay; //联系方式
	private Date 	beginAddresseeDate; 	//	收件开始时间	
	private Date 	endAddresseeDate;		//	收件结束时间
	private Date 	beginDistributeDate;	//	分件开始时间
	private Date 	endDistributeDate;		//	分件结束时间
	private String 	businessPeriodShow;		//经营年限
	private String 	channelShow;			//经营渠道
	private String  acceptConsultStateShow;	//受理咨询状态
	private String 	conSexShow;				//性别
	private String 	cityShow;				//城市
	private String  isBusinessLicenseShow;	//是否营业执照
	private String 	cityAllShow;				//省市区 城市
	private String rollbackReason;				//退回原因
	private String operatorReturn;				//退回人
	private String feedbackResultShow;			//反馈结果
	private String saleConsultStatusShowUnless;	//无效登记状态
	private String scrapflag;					//废弃标识
	
	private String addRess;						//户籍地址
	private String borrowingShow;				//借款用途
	private String channelShows;					//信息来源
	private String consultWayShow;				//咨询方式
	private String consultTimes;				//咨询次数
	
	
	
	public String getConsultTimes() {
		return consultTimes;
	}
	public void setConsultTimes(String consultTimes) {
		this.consultTimes = consultTimes;
	}
	public String getAddRess() {
		return addRess;
	}
	public void setAddRess(String addRess) {
		this.addRess = addRess;
	}
	public String getBorrowingShow() {
		return borrowingShow;
	}
	public void setBorrowingShow(String borrowingShow) {
		this.borrowingShow = borrowingShow;
	}
	public String getChannelShows() {
		return channelShows;
	}
	public void setChannelShows(String channelShows) {
		this.channelShows = channelShows;
	}
	public String getConsultWayShow() {
		return consultWayShow;
	}
	public void setConsultWayShow(String consultWayShow) {
		this.consultWayShow = consultWayShow;
	}
	public String getConnectionWay() {
		return connectionWay;
	}
	public void setConnectionWay(String connectionWay) {
		this.connectionWay = connectionWay;
	}
	public Date getBeginAddresseeDate() {
		return beginAddresseeDate;
	}
	public void setBeginAddresseeDate(Date beginAddresseeDate) {
		this.beginAddresseeDate = beginAddresseeDate;
	}
	public Date getEndAddresseeDate() {
		return endAddresseeDate;
	}
	public void setEndAddresseeDate(Date endAddresseeDate) {
		this.endAddresseeDate = endAddresseeDate;
	}
	public Date getBeginDistributeDate() {
		return beginDistributeDate;
	}
	public void setBeginDistributeDate(Date beginDistributeDate) {
		this.beginDistributeDate = beginDistributeDate;
	}
	public Date getEndDistributeDate() {
		return endDistributeDate;
	}
	public void setEndDistributeDate(Date endDistributeDate) {
		this.endDistributeDate = endDistributeDate;
	}
	public String getBusinessPeriodShow() {
		return businessPeriodShow;
	}
	public void setBusinessPeriodShow(String businessPeriodShow) {
		this.businessPeriodShow = businessPeriodShow;
	}
	public String getChannelShow() {
		return channelShow;
	}
	public void setChannelShow(String channelShow) {
		this.channelShow = channelShow;
	}
	public String getAcceptConsultStateShow() {
		return acceptConsultStateShow;
	}
	public void setAcceptConsultStateShow(String acceptConsultStateShow) {
		this.acceptConsultStateShow = acceptConsultStateShow;
	}
	public String getConSexShow() {
		return conSexShow;
	}
	public void setConSexShow(String conSexShow) {
		this.conSexShow = conSexShow;
	}
	public String getCityShow() {
		return cityShow;
	}
	public void setCityShow(String cityShow) {
		this.cityShow = cityShow;
	}
	public String getIsBusinessLicenseShow() {
		return isBusinessLicenseShow;
	}
	public void setIsBusinessLicenseShow(String isBusinessLicenseShow) {
		this.isBusinessLicenseShow = isBusinessLicenseShow;
	}
	public String getCityAllShow() {
		return cityAllShow;
	}
	public void setCityAllShow(String cityAllShow) {
		this.cityAllShow = cityAllShow;
	}
	public String getRollbackReason() {
		return rollbackReason;
	}
	public void setRollbackReason(String rollbackReason) {
		this.rollbackReason = rollbackReason;
	}
	public String getOperatorReturn() {
		return operatorReturn;
	}
	public void setOperatorReturn(String operatorReturn) {
		this.operatorReturn = operatorReturn;
	}
	public String getFeedbackResultShow() {
		return feedbackResultShow;
	}
	public void setFeedbackResultShow(String feedbackResultShow) {
		this.feedbackResultShow = feedbackResultShow;
	}
	public String getSaleConsultStatusShowUnless() {
		return saleConsultStatusShowUnless;
	}
	public void setSaleConsultStatusShowUnless(String saleConsultStatusShowUnless) {
		this.saleConsultStatusShowUnless = saleConsultStatusShowUnless;
	}
	public String getScrapflag() {
		return scrapflag;
	}
	public void setScrapflag(String scrapflag) {
		this.scrapflag = scrapflag;
	}
	
	
}

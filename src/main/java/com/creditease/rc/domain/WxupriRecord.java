package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class WxupriRecord {

	private Long wxUpriRecordId; // 用户兑奖记录主键ID
	private Long wxUserId; // 用户主键ID
	private Long wxPrizeId; // 奖品主键ID
	private String upriRecordCode; // 兑奖编码
	private String provincehome; // 省
	private String cityhome; // 市
	private String countyhome; // 区
	private String branchofficeName; // 营业部名称
	private String branchofficeId; // 营业部ID
	private String isReceive; // 是否领取：0-未领取；1-领取
	private String recpriName; // 兑奖人姓名
	private String recpriPhone; // 兑奖人联系方式
	private Date recpriDate; // 兑奖日期
	private String realRecpriName; // 实际兑奖人姓名
	private String realRecpriPhone; // 实际对象人联系方式
	private String receiverId; // 接待人员ID
	private String receiverName; // 接待人员姓名
	private Date receiveTime; // 接待时间

	public Long getWxUpriRecordId() {
		return wxUpriRecordId;
	}

	public void setWxUpriRecordId(Long wxUpriRecordId) {
		this.wxUpriRecordId = wxUpriRecordId;
	}

	public Long getWxUserId() {
		return wxUserId;
	}

	public void setWxUserId(Long wxUserId) {
		this.wxUserId = wxUserId;
	}

	public Long getWxPrizeId() {
		return wxPrizeId;
	}

	public void setWxPrizeId(Long wxPrizeId) {
		this.wxPrizeId = wxPrizeId;
	}

	public String getUpriRecordCode() {
		return upriRecordCode;
	}

	public void setUpriRecordCode(String upriRecordCode) {
		this.upriRecordCode = upriRecordCode;
	}

	public String getProvincehome() {
		return provincehome;
	}

	public void setProvincehome(String provincehome) {
		this.provincehome = provincehome;
	}

	public String getCityhome() {
		return cityhome;
	}

	public void setCityhome(String cityhome) {
		this.cityhome = cityhome;
	}

	public String getCountyhome() {
		return countyhome;
	}

	public void setCountyhome(String countyhome) {
		this.countyhome = countyhome;
	}

	public String getBranchofficeName() {
		return branchofficeName;
	}

	public void setBranchofficeName(String branchofficeName) {
		this.branchofficeName = branchofficeName;
	}

	public String getBranchofficeId() {
		return branchofficeId;
	}

	public void setBranchofficeId(String branchofficeId) {
		this.branchofficeId = branchofficeId;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public String getRecpriName() {
		return recpriName;
	}

	public void setRecpriName(String recpriName) {
		this.recpriName = recpriName;
	}

	public String getRecpriPhone() {
		return recpriPhone;
	}

	public void setRecpriPhone(String recpriPhone) {
		this.recpriPhone = recpriPhone;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRecpriDate() {
		return recpriDate;
	}

	public void setRecpriDate(Date recpriDate) {
		this.recpriDate = recpriDate;
	}

	public String getRealRecpriName() {
		return realRecpriName;
	}

	public void setRealRecpriName(String realRecpriName) {
		this.realRecpriName = realRecpriName;
	}

	public String getRealRecpriPhone() {
		return realRecpriPhone;
	}

	public void setRealRecpriPhone(String realRecpriPhone) {
		this.realRecpriPhone = realRecpriPhone;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

}
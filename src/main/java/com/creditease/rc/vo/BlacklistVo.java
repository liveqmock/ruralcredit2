package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;
import com.creditease.rc.domain.CustomerBasicInfo;

public class BlacklistVo extends CustomerBasicInfo {

	private Long blacklistId; // 黑名单主键
	private String blackName; // 客户姓名
	private String blackCredentialsNumber; // 身份证号
	private Date blacklistedTime; // 拉入黑名单的时间
	private String blacklistedOperatorName; // 拉黑操作人姓名
	private Long blacklistedOperatorId; // 拉黑操作人编号
	private String blacklistedReason; // 拉黑原因
	private Date removeTime; // 剔除的时间
	private String removeOperatorName; // 剔除的操作人姓名
	private Long removeOperatorId; // 剔除的操作人ID
	private String removeReason; // 剔除的原因
	private String historyFlag; // 历史记录标识(T:历史记录;F:当前记录)
	private String nationalDetail;// 民族显示
	private String maritalStatusDetail;// 婚姻显示
	private String highestEducationDetail;// 最高教育显示

	public String getNationalDetail() {
		return nationalDetail;
	}

	public void setNationalDetail(String nationalDetail) {
		this.nationalDetail = nationalDetail;
	}

	public String getMaritalStatusDetail() {
		return maritalStatusDetail;
	}

	public void setMaritalStatusDetail(String maritalStatusDetail) {
		this.maritalStatusDetail = maritalStatusDetail;
	}

	public String getHighestEducationDetail() {
		return highestEducationDetail;
	}

	public void setHighestEducationDetail(String highestEducationDetail) {
		this.highestEducationDetail = highestEducationDetail;
	}

	public Long getBlacklistId() {
		return blacklistId;
	}

	public void setBlacklistId(Long blacklistId) {
		this.blacklistId = blacklistId;
	}

	public String getBlackName() {
		return blackName;
	}

	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}

	public String getBlackCredentialsNumber() {
		return blackCredentialsNumber;
	}

	public void setBlackCredentialsNumber(String blackCredentialsNumber) {
		this.blackCredentialsNumber = blackCredentialsNumber;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getBlacklistedTime() {
		return blacklistedTime;
	}

	public void setBlacklistedTime(Date blacklistedTime) {
		this.blacklistedTime = blacklistedTime;
	}

	public String getBlacklistedOperatorName() {
		return blacklistedOperatorName;
	}

	public void setBlacklistedOperatorName(String blacklistedOperatorName) {
		this.blacklistedOperatorName = blacklistedOperatorName;
	}

	public Long getBlacklistedOperatorId() {
		return blacklistedOperatorId;
	}

	public void setBlacklistedOperatorId(Long blacklistedOperatorId) {
		this.blacklistedOperatorId = blacklistedOperatorId;
	}

	public String getBlacklistedReason() {
		return blacklistedReason;
	}

	public void setBlacklistedReason(String blacklistedReason) {
		this.blacklistedReason = blacklistedReason;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getRemoveTime() {
		return removeTime;
	}

	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}

	public String getRemoveOperatorName() {
		return removeOperatorName;
	}

	public void setRemoveOperatorName(String removeOperatorName) {
		this.removeOperatorName = removeOperatorName;
	}

	public Long getRemoveOperatorId() {
		return removeOperatorId;
	}

	public void setRemoveOperatorId(Long removeOperatorId) {
		this.removeOperatorId = removeOperatorId;
	}

	public String getRemoveReason() {
		return removeReason;
	}

	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}

	public String getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}

}

package com.creditease.rc.domain;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * User: zhangwei
 * Date: 14-5-15
 * Time: 上午11:36
 */
public class CustomerConsultPoolLog {

	private Long optLogId;

	private Long operatorId;

	private String operatorName;

	private Date optDate;

	private String optModule;

	private String optBussinessContent;
	private String currStatus;
	private String nextStatus;

	private Long connectionId;
	private String connectionCetegory;
	private String currConnectionDicSection;
	private String nextConnectionDicSection;

	public Long getOptLogId() {
		return optLogId;
	}

	public void setOptLogId(Long optLogId) {
		this.optLogId = optLogId;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getOptModule() {
		return optModule;
	}

	public void setOptModule(String optModule) {
		this.optModule = optModule == null ? null : optModule.trim();
	}

	public String getOptBussinessContent() {
		return optBussinessContent;
	}

	public void setOptBussinessContent(String optBussinessContent) {
		this.optBussinessContent = optBussinessContent == null ? null : optBussinessContent.trim();
	}

	public String getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}

	public String getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}

	public Long getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Long connectionId) {
		this.connectionId = connectionId;
	}

	public String getConnectionCetegory() {
		return connectionCetegory;
	}

	public void setConnectionCetegory(String connectionCetegory) {
		this.connectionCetegory = connectionCetegory;
	}

	public String getCurrConnectionDicSection() {
		return currConnectionDicSection;
	}

	public void setCurrConnectionDicSection(String currConnectionDicSection) {
		this.currConnectionDicSection = currConnectionDicSection;
	}

	public String getNextConnectionDicSection() {
		return nextConnectionDicSection;
	}

	public void setNextConnectionDicSection(String nextConnectionDicSection) {
		this.nextConnectionDicSection = nextConnectionDicSection;
	}

}

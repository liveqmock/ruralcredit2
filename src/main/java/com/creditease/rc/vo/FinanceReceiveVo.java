package com.creditease.rc.vo;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHmsDateSerializer;
import com.creditease.rc.domain.ReceivedRecord;

/**
 * 
 * @author Administrator
 *
 */
public class FinanceReceiveVo extends ReceivedRecord{
	
	private Date receivedTime;
	private String remark;
	private String receiveRecordIds;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getReceivedTime() {
		return receivedTime;
	}
	@JsonSerialize(using = JsonYMDHmsDateSerializer.class)
	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getReceiveRecordIds() {
		return receiveRecordIds;
	}
	public void setReceiveRecordIds(String receiveRecordIds) {
		this.receiveRecordIds = receiveRecordIds;
	}
	
	
}

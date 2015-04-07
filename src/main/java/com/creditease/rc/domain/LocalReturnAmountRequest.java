package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author haoqiang
 *
 */
public class LocalReturnAmountRequest implements Serializable {

	private Long creditapplicationId; // 信贷申请主键
	private String ifPayAhead; // 是否一次性提前还款：估计以后会变成true或者false
	private Date registReturnTime; // 预约还款日期

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	

	public String getIfPayAhead() {
		return ifPayAhead;
	}

	public void setIfPayAhead(String ifPayAhead) {
		this.ifPayAhead = ifPayAhead;
	}

	public Date getRegistReturnTime() {
		return registReturnTime;
	}

	public void setRegistReturnTime(Date registReturnTime) {
		this.registReturnTime = registReturnTime;
	}

}

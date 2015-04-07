package com.creditease.rc.vo;

import java.io.Serializable;

import com.creditease.rc.domain.AccountInfo;

public class AccountInfoVo2ICP extends AccountInfo implements Serializable {
	
	private String creditapplicationId;//信贷申请id

	public String getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(String creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}
	

}

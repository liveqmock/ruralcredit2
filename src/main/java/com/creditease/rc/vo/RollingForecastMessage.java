package com.creditease.rc.vo;

import com.creditease.rc.domain.RollingForecast;

public class RollingForecastMessage extends RollingForecast {
	private boolean success = false;
	private String msg;
	private String code;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

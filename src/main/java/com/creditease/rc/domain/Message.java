package com.creditease.rc.domain;

/**
 * Title: Description: 农村商贷系统研发 Copyright: Copyright (c) 2012 Company:
 * 普信恒业科技发展（北京）有限公司 Date: 2012-6-4
 * 
 * @author: 解兵丰
 * @version: v1.0
 */
public class Message {

	private boolean success = false;
	private String msg;
	private String code;

	/**
	 * 
	 * @param success success
	 * @param msg msg
	 */
	public Message(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	/**
	 * 
	 * @param success success
	 */
	public Message(boolean success) {
		super();
		this.success = success;
	}
	/**
	 * 
	 */
	public Message() {
		super();
	}
	
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

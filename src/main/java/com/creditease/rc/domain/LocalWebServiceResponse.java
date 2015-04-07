package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author haoqiang
 *
 */
public class LocalWebServiceResponse implements Serializable {

	/** 目前对应的是贷后接口的响应 **/
	private String retCode;// 响应码
	private String retInfo;// 响应信息

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

}

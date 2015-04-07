package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.LocalReturnDTO;
/**
 * 
 * @author haoqiang
 *
 */
public class ReserveReturnRequestVo {

	private boolean isSuccess;// 调用接口是否成功
	
	private String remark;//备注
	
	private String code;
	private String info;
	private List<LocalReturnDTO> localReturnDTOs;
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<LocalReturnDTO> getLocalReturnDTOs() {
		return localReturnDTOs;
	}

	public void setLocalReturnDTOs(List<LocalReturnDTO> localReturnDTOs) {
		this.localReturnDTOs = localReturnDTOs;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}

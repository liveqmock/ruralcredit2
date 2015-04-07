package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author haoqiang
 *
 */
public class LocalOfficeIdDTO implements Serializable {

	private String officeId;// 分公司ID
	private List<LocalSellIdDTO> localSellIdDTOs;// 员工编号列表

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public List<LocalSellIdDTO> getLocalSellIdDTOs() {
		return localSellIdDTOs;
	}

	public void setLocalSellIdDTOs(List<LocalSellIdDTO> localSellIdDTOs) {
		this.localSellIdDTOs = localSellIdDTOs;
	}

}

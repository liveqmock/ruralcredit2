package com.creditease.rc.vo;

import com.creditease.rc.domain.RollingForecast;

public class RollingForecastVo extends RollingForecast {
	private String yearAndMonth;
	private String editFlag;// 1可编辑0不可编辑
	private String histroyShowFlag;

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getHistroyShowFlag() {
		return histroyShowFlag;
	}

	public void setHistroyShowFlag(String histroyShowFlag) {
		this.histroyShowFlag = histroyShowFlag;
	}

	public String getYearAndMonth() {
		return yearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

}

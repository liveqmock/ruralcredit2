package com.creditease.rc.vo;

import com.creditease.rc.domain.Website;

public class WebsiteVo extends Website {

	private String provinceName; // 省的name
	private String cityName; // 市的name
	private String countyName; // 区的name

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

}

package com.creditease.rc.vo;

import java.util.List;

public class WebsiteProvince {
	private String provinceCode;
	private String provinceName;
	private List<WebsiteCity> websiteCity; // 市的集合

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public List<WebsiteCity> getWebsiteCity() {
		return websiteCity;
	}

	public void setWebsiteCity(List<WebsiteCity> websiteCity) {
		this.websiteCity = websiteCity;
	}
}

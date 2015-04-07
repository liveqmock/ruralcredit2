package com.creditease.rc.vo;

import java.util.List;

public class WebsiteCity {
	private String cityCode;
	private String cityName;
	private String provinceName;
	private List<WebsiteVo> websiteVo; // 营业部的集合

	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<WebsiteVo> getWebsiteVo() {
		return websiteVo;
	}

	public void setWebsiteVo(List<WebsiteVo> websiteVo) {
		this.websiteVo = websiteVo;
	}

}

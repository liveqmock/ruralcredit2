package com.creditease.rc.vo;

/**
 * 
 * @author zhangman
 * 
 */
public class Area {
	private Long townshipinfoid; // 村id
	private Long parentId; // 镇id
	private String province;
	private String city;
	private String district;
	private String districtCode;
	private String town;
	private String townCode;
	private String village;
	private String code;
	private String onLine;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOnLine() {
		return onLine;
	}

	public void setOnLine(String onLine) {
		this.onLine = onLine;
	}

	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	public Long getTownshipinfoid() {
		return townshipinfoid;
	}

	public void setTownshipinfoid(Long townshipinfoid) {
		this.townshipinfoid = townshipinfoid;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

}

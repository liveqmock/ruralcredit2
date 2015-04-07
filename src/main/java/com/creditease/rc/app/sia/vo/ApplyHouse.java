package com.creditease.rc.app.sia.vo;

import java.util.Date;


/**
 * 宜楼产品需提供的材料
 */
@SuppressWarnings("serial")
public class ApplyHouse extends Entity {
	
	private Integer hid;//房产信息id
	
	private Integer applyId;//贷款信息id	
	
	private Integer clientId;//借款人id
	

	//宜楼产品需提供的资料
	private Date buyHouseDate;//房产购买时间
	
	private Integer houseOwnerType;//产权人类型
	
	private String houseOwnerTypeDesc;//产权人其他项详情
	
	private Integer houseAddressProvince;//房产地址省
	
	private Integer houseAddressCity;//房产地址市
	
	private Integer houseAddressArea;//房产地址区
	
	private String houseAddressInput;//房产地址输入项
	
	private String houseAddressZipcode;//房产地址邮编
	
	private Integer houseType;//房产类型
	
	private String houseTypeDesc;//房产类型其它项详情
	
	private Integer houseAddressSame;//房产地址与现住址是否相同  1：相同  0：不同

	//新增字段
	private Integer houseMortgage;//房产抵押情况  0：否  1：一次抵押   2：二次抵押
	
	private String houseArea;//房产面积 单位（平米）
	
	private String houseNumber;//房产数量 
	
	private String houseOwnerTypeDesc1;//产权人为共有时，与共有人关系
	
	public Date getBuyHouseDate() {
		return buyHouseDate;
	}

	public void setBuyHouseDate(Date buyHouseDate) {
		this.buyHouseDate = buyHouseDate;
	}

	public Integer getHouseOwnerType() {
		return houseOwnerType;
	}

	public void setHouseOwnerType(Integer houseOwnerType) {
		this.houseOwnerType = houseOwnerType;
	}

	public Integer getHouseAddressProvince() {
		return houseAddressProvince;
	}

	public void setHouseAddressProvince(Integer houseAddressProvince) {
		this.houseAddressProvince = houseAddressProvince;
	}

	public Integer getHouseAddressCity() {
		return houseAddressCity;
	}

	public void setHouseAddressCity(Integer houseAddressCity) {
		this.houseAddressCity = houseAddressCity;
	}

	public Integer getHouseAddressArea() {
		return houseAddressArea;
	}

	public void setHouseAddressArea(Integer houseAddressArea) {
		this.houseAddressArea = houseAddressArea;
	}

	public String getHouseAddressZipcode() {
		return houseAddressZipcode;
	}

	public void setHouseAddressZipcode(String houseAddressZipcode) {
		this.houseAddressZipcode = houseAddressZipcode;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}


	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getHouseAddressInput() {
		return houseAddressInput;
	}

	public void setHouseAddressInput(String houseAddressInput) {
		this.houseAddressInput = houseAddressInput;
	}

	public String getHouseOwnerTypeDesc() {
		return houseOwnerTypeDesc;
	}

	public void setHouseOwnerTypeDesc(String houseOwnerTypeDesc) {
		this.houseOwnerTypeDesc = houseOwnerTypeDesc;
	}

	public String getHouseTypeDesc() {
		return houseTypeDesc;
	}

	public void setHouseTypeDesc(String houseTypeDesc) {
		this.houseTypeDesc = houseTypeDesc;
	}

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public Integer getHouseAddressSame() {
		return houseAddressSame;
	}

	public void setHouseAddressSame(Integer houseAddressSame) {
		this.houseAddressSame = houseAddressSame;
	}
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getHouseMortgage() {
		return houseMortgage;
	}

	public void setHouseMortgage(Integer houseMortgage) {
		this.houseMortgage = houseMortgage;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseOwnerTypeDesc1() {
		return houseOwnerTypeDesc1;
	}

	public void setHouseOwnerTypeDesc1(String houseOwnerTypeDesc1) {
		this.houseOwnerTypeDesc1 = houseOwnerTypeDesc1;
	}

	
}

package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人农机信息
 */
public class BorrowerMachineVo extends Entity{
	private static final long serialVersionUID = -7119081325280483212L;
	
	private Integer borrowerInfoId;//借款人ID
	private Integer machineId;     //农机ID
	private String machineYype;    //机械类型  
	private String brand;          //品牌
	private String model;          //型号
	private String isMatachTool;   //是否配机具
	private String buyWay;         //购机方式
	private String rentType;       //租赁类型 
	private Integer num;			   //台数
	private BigDecimal machinePrice;//农机价格
	private Integer creater;			//创建人
	private Integer updaterPeople;		//更新人
	private Date updateDate;        //最近更新时间
	private Date createDate;		//新增时间
	
	public Integer getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Integer borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public String getMachineYype() {
		return machineYype;
	}
	public void setMachineYype(String machineYype) {
		this.machineYype = machineYype;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIsMatachTool() {
		return isMatachTool;
	}
	public void setIsMatachTool(String isMatachTool) {
		this.isMatachTool = isMatachTool;
	}
	public String getBuyWay() {
		return buyWay;
	}
	public void setBuyWay(String buyWay) {
		this.buyWay = buyWay;
	}
	public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getMachinePrice() {
		return machinePrice;
	}
	public void setMachinePrice(BigDecimal machinePrice) {
		this.machinePrice = machinePrice;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Integer getUpdaterPeople() {
		return updaterPeople;
	}
	public void setUpdaterPeople(Integer updaterPeople) {
		this.updaterPeople = updaterPeople;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}

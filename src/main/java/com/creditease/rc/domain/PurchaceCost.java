package com.creditease.rc.domain;

/**
 * @author Administrator
 * 采购或进货成本表
 *
 */
public class PurchaceCost {
	
	private Integer purchaceCostId;
	private String consumables;			//原材料或其他消耗品
	private String unit;				//单位
	private Double unitPrice;			//单价
	private Integer purchaceNum;		//采购数量
	private Double everyDay;			//每天
	private Double everyWeek;			//每周
	private Double everyMonth;			//每月
	private Double everyQuarter;		//每季度
	private Double everyHalfYear;		//每半年
	private Double everyYear;			//每年
	private Integer belongId;			//所属id
	
	public Integer getPurchaceCostId() {
		return purchaceCostId;
	}
	public void setPurchaceCostId(Integer purchaceCostId) {
		this.purchaceCostId = purchaceCostId;
	}
	
	public String getConsumables() {
		return consumables;
	}
	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getPurchaceNum() {
		return purchaceNum;
	}
	public void setPurchaceNum(Integer purchaceNum) {
		this.purchaceNum = purchaceNum;
	}
	public Double getEveryDay() {
		return everyDay;
	}
	public void setEveryDay(Double everyDay) {
		this.everyDay = everyDay;
	}
	public Double getEveryWeek() {
		return everyWeek;
	}
	public void setEveryWeek(Double everyWeek) {
		this.everyWeek = everyWeek;
	}
	public Double getEveryMonth() {
		return everyMonth;
	}
	public void setEveryMonth(Double everyMonth) {
		this.everyMonth = everyMonth;
	}
	public Double getEveryQuarter() {
		return everyQuarter;
	}
	public void setEveryQuarter(Double everyQuarter) {
		this.everyQuarter = everyQuarter;
	}
	public Double getEveryHalfYear() {
		return everyHalfYear;
	}
	public void setEveryHalfYear(Double everyHalfYear) {
		this.everyHalfYear = everyHalfYear;
	}
	public Double getEveryYear() {
		return everyYear;
	}
	public void setEveryYear(Double everyYear) {
		this.everyYear = everyYear;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	
}

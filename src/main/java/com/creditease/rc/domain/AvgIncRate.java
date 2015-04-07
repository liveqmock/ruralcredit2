package com.creditease.rc.domain;

public class AvgIncRate {
	
	private Integer avgIncRateId;
	private String sellProject;			//销售项目
	private Double purchaceCost;		//批发采购成本
	private Integer purchaceQuantity;	//批发采购数量
	private Double onePieceCost;		//单件成本
	private Double onePieceSalePrice;	//单件销售价格
	private Integer itemSort;			//排序字段
	private Integer belongId;			//所属ID
	
	public Integer getAvgIncRateId() {
		return avgIncRateId;
	}
	public void setAvgIncRateId(Integer avgIncRateId) {
		this.avgIncRateId = avgIncRateId;
	}
	public String getSellProject() {
		return sellProject;
	}
	public void setSellProject(String sellProject) {
		this.sellProject = sellProject;
	}
	public Double getPurchaceCost() {
		return purchaceCost;
	}
	public void setPurchaceCost(Double purchaceCost) {
		this.purchaceCost = purchaceCost;
	}
	public Integer getPurchaceQuantity() {
		return purchaceQuantity;
	}
	public void setPurchaceQuantity(Integer purchaceQuantity) {
		this.purchaceQuantity = purchaceQuantity;
	}
	public Double getOnePieceCost() {
		return onePieceCost;
	}
	public void setOnePieceCost(Double onePieceCost) {
		this.onePieceCost = onePieceCost;
	}
	public Double getOnePieceSalePrice() {
		return onePieceSalePrice;
	}
	public void setOnePieceSalePrice(Double onePieceSalePrice) {
		this.onePieceSalePrice = onePieceSalePrice;
	}
	
	public Integer getItemSort() {
		return itemSort;
	}
	public void setItemSort(Integer itemSort) {
		this.itemSort = itemSort;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	
}

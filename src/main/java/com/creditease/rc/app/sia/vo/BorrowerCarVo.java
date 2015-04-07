package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人车辆信息
 */
public class BorrowerCarVo extends Entity{
	private static final long serialVersionUID = 7964590038036594043L;
	
	private Integer borrowerInfoId;	//借款人ID    
	private Integer carId;			//车辆ID              
	private String carModel;		//品牌型号            
	private String carBrand;		//车辆品牌            
	private String carSeries;		//车系                
	private Date applyDate;			//申请时间            
	private Date startBoardDate;	//初次上牌时间
	private Integer carAge;			//车龄                
	private BigDecimal amount;		//成交金额1           
	private String frameNo;			//车架号              
	private String enginerNo;		//发动机号            
	private BigDecimal firstPaymentAmount;//首付款      
	private BigDecimal insureFee;	//保险费              
	private BigDecimal carValue;	//车价                
	private BigDecimal licenseFee;	//牌照费              
	private BigDecimal totalAmount;	//总价款              
	private String carNature;		//全新；二手          
	private String address;			//评估地址            
	private String contact;			//联系人              
	private String vender;			//生产厂家            
	private Integer creater;		//创建人              
	private Integer updaterPeople;	//更新人      
	private Date updateDate;		//最近更新时间        
	private Date createDate;		//新增时间  
	
	public Integer getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Integer borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarSeries() {
		return carSeries;
	}
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getStartBoardDate() {
		return startBoardDate;
	}
	public void setStartBoardDate(Date startBoardDate) {
		this.startBoardDate = startBoardDate;
	}
	public Integer getCarAge() {
		return carAge;
	}
	public void setCarAge(Integer carAge) {
		this.carAge = carAge;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	public String getEnginerNo() {
		return enginerNo;
	}
	public void setEnginerNo(String enginerNo) {
		this.enginerNo = enginerNo;
	}
	public BigDecimal getFirstPaymentAmount() {
		return firstPaymentAmount;
	}
	public void setFirstPaymentAmount(BigDecimal firstPaymentAmount) {
		this.firstPaymentAmount = firstPaymentAmount;
	}
	public BigDecimal getInsureFee() {
		return insureFee;
	}
	public void setInsureFee(BigDecimal insureFee) {
		this.insureFee = insureFee;
	}
	public BigDecimal getCarValue() {
		return carValue;
	}
	public void setCarValue(BigDecimal carValue) {
		this.carValue = carValue;
	}
	public BigDecimal getLicenseFee() {
		return licenseFee;
	}
	public void setLicenseFee(BigDecimal licenseFee) {
		this.licenseFee = licenseFee;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCarNature() {
		return carNature;
	}
	public void setCarNature(String carNature) {
		this.carNature = carNature;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
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

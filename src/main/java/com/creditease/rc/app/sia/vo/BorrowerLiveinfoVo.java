package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人居住信息
 */
public class BorrowerLiveinfoVo extends Entity{
	private static final long serialVersionUID = -701244574640743850L;
	
	private Long borrowerInfoId;	//借款人ID                                                                                 
	private Long liveId;			//居住ID                                                                                   
	private String liveNature;		//居住性质：1 住在店面   2 独立租住   3 与人合租   4 借住亲友处   5 自有住房               
	private String livingStatus;	//居住情况                                                                                 
	private String livingOtherRemark;//居住情况其它项备注                                                                       
	private String province;		//居住住址省份                                                                             
	private String city;			//居住住址市                                                                               
	private String area;			//居住住址县                                                                               
	private String address;			//详细地址                                                                                 
	private String applyAddressIsIdentity;//住址证明与申请表住址是否一致。0[不一致], 1[一致]                                         
	private String nowAddressIsIdentity;  //住址证明与现居住地是否一致。0[不一致], 1[一致]                                           
	private String remark;			      //描述                                                                                     
	private String monthRent;		      //月租金                                                                                   
	private String strCustomLng;		  //客户详细地点:地理经度                                                                    
	private String strCustomLat;		  //客户详细地点:地理纬度                                                                    
	private Date rentBeginDate;		      //租房起始日                                                                               
	private Date rentEndDate;		      //租房结束日                                                                               
	private String addressType;		      //家庭住址分类：1 自由商品房； 2 自有宅基地； 3 租住（到期日 年月日）  4 亲戚住房； 5 其他 
	private Integer addressArea;		  //家庭住址面积                                                                             
	private BigDecimal liveYear;		  //家庭住址居住年限                                                                         
	private String residentProvince;	  //家庭住址省                                                                               
	private String residentCity;		  //家庭住址城市                                                                             
	private String residentDistrict;	  //家庭住址县                                                                               
	private String familyAddressTown;	  //家庭住址镇                                                                               
	private String familyAddressVillage;  //家庭住址村                                                                               
	private String familyAddressHouseno;  //家庭住址门牌号                                                                           
	private String residentAddr;		  //家庭住址详细                                                                             
	private String rersidentTelAreacode; //家庭住址电话区号
	private String residentTelNumber;	  //家庭住址电话                                                                             
	private Date liveStartDate;		      //起始居住时间                                                                             
	private Short supportPersons;		  //供养亲属人数                                                                             
	private Short childNum;		      //子女个数                                                                                 
	private String togetherLiver;		  //共同居住者                                                                               
	private BigDecimal liveTimes;		  //在本地居住时间                                                                           
	private Date startYear;		       	  //初来本市年份                                                                             
	private BigDecimal validTime;	   	  //有效期限                                                                                 
	private Date validLimitTime;		  //有效期限截止                                                                             
	private String liveAddressPostCard;   //居住地址邮编                                                                             
	private String postCd2;			       //邮政编码1                                                                                
	private String postCd1;			       //邮政编码1                                                                                
	private Long creater;		       //创建人                                                                                   
	private Long updaterPeople;		   //更新人                                                                                   
	private Date updateTime;		       //最后更新时间                                                                             
	private Date createTime;		       //新增时间           
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getLiveId() {
		return liveId;
	}
	public void setLiveId(Long liveId) {
		this.liveId = liveId;
	}
	public String getLiveNature() {
		return liveNature;
	}
	public void setLiveNature(String liveNature) {
		this.liveNature = liveNature;
	}
	public String getLivingStatus() {
		return livingStatus;
	}
	public void setLivingStatus(String livingStatus) {
		this.livingStatus = livingStatus;
	}
	public String getLivingOtherRemark() {
		return livingOtherRemark;
	}
	public void setLivingOtherRemark(String livingOtherRemark) {
		this.livingOtherRemark = livingOtherRemark;
	}
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getApplyAddressIsIdentity() {
		return applyAddressIsIdentity;
	}
	public void setApplyAddressIsIdentity(String applyAddressIsIdentity) {
		this.applyAddressIsIdentity = applyAddressIsIdentity;
	}
	public String getNowAddressIsIdentity() {
		return nowAddressIsIdentity;
	}
	public void setNowAddressIsIdentity(String nowAddressIsIdentity) {
		this.nowAddressIsIdentity = nowAddressIsIdentity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMonthRent() {
		return monthRent;
	}
	public void setMonthRent(String monthRent) {
		this.monthRent = monthRent;
	}
	public String getStrCustomLng() {
		return strCustomLng;
	}
	public void setStrCustomLng(String strCustomLng) {
		this.strCustomLng = strCustomLng;
	}
	public String getStrCustomLat() {
		return strCustomLat;
	}
	public void setStrCustomLat(String strCustomLat) {
		this.strCustomLat = strCustomLat;
	}
	public Date getRentBeginDate() {
		return rentBeginDate;
	}
	public void setRentBeginDate(Date rentBeginDate) {
		this.rentBeginDate = rentBeginDate;
	}
	public Date getRentEndDate() {
		return rentEndDate;
	}
	public void setRentEndDate(Date rentEndDate) {
		this.rentEndDate = rentEndDate;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public Integer getAddressArea() {
		return addressArea;
	}
	public void setAddressArea(Integer addressArea) {
		this.addressArea = addressArea;
	}
	public BigDecimal getLiveYear() {
		return liveYear;
	}
	public void setLiveYear(BigDecimal liveYear) {
		this.liveYear = liveYear;
	}
	public String getResidentProvince() {
		return residentProvince;
	}
	public void setResidentProvince(String residentProvince) {
		this.residentProvince = residentProvince;
	}
	public String getResidentCity() {
		return residentCity;
	}
	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}
	public String getResidentDistrict() {
		return residentDistrict;
	}
	public void setResidentDistrict(String residentDistrict) {
		this.residentDistrict = residentDistrict;
	}
	public String getFamilyAddressTown() {
		return familyAddressTown;
	}
	public void setFamilyAddressTown(String familyAddressTown) {
		this.familyAddressTown = familyAddressTown;
	}
	public String getFamilyAddressVillage() {
		return familyAddressVillage;
	}
	public void setFamilyAddressVillage(String familyAddressVillage) {
		this.familyAddressVillage = familyAddressVillage;
	}
	public String getFamilyAddressHouseno() {
		return familyAddressHouseno;
	}
	public void setFamilyAddressHouseno(String familyAddressHouseno) {
		this.familyAddressHouseno = familyAddressHouseno;
	}
	public String getResidentAddr() {
		return residentAddr;
	}
	public void setResidentAddr(String residentAddr) {
		this.residentAddr = residentAddr;
	}
	public String getRersidentTelAreacode() {
		return rersidentTelAreacode;
	}
	public void setRersidentTelAreacode(String rersidentTelAreacode) {
		this.rersidentTelAreacode = rersidentTelAreacode;
	}
	public String getResidentTelNumber() {
		return residentTelNumber;
	}
	public void setResidentTelNumber(String residentTelNumber) {
		this.residentTelNumber = residentTelNumber;
	}
	public Date getLiveStartDate() {
		return liveStartDate;
	}
	public void setLiveStartDate(Date liveStartDate) {
		this.liveStartDate = liveStartDate;
	}
	public Short getSupportPersons() {
		return supportPersons;
	}
	public void setSupportPersons(Short supportPersons) {
		this.supportPersons = supportPersons;
	}
	public Short getChildNum() {
		return childNum;
	}
	public void setChildNum(Short childNum) {
		this.childNum = childNum;
	}
	public String getTogetherLiver() {
		return togetherLiver;
	}
	public void setTogetherLiver(String togetherLiver) {
		this.togetherLiver = togetherLiver;
	}
	public BigDecimal getLiveTimes() {
		return liveTimes;
	}
	public void setLiveTimes(BigDecimal liveTimes) {
		this.liveTimes = liveTimes;
	}
	public Date getStartYear() {
		return startYear;
	}
	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}
	public BigDecimal getValidTime() {
		return validTime;
	}
	public void setValidTime(BigDecimal validTime) {
		this.validTime = validTime;
	}
	public Date getValidLimitTime() {
		return validLimitTime;
	}
	public void setValidLimitTime(Date validLimitTime) {
		this.validLimitTime = validLimitTime;
	}
	public String getLiveAddressPostCard() {
		return liveAddressPostCard;
	}
	public void setLiveAddressPostCard(String liveAddressPostCard) {
		this.liveAddressPostCard = liveAddressPostCard;
	}
	public String getPostCd2() {
		return postCd2;
	}
	public void setPostCd2(String postCd2) {
		this.postCd2 = postCd2;
	}
	public String getPostCd1() {
		return postCd1;
	}
	public void setPostCd1(String postCd1) {
		this.postCd1 = postCd1;
	}
	public Long getCreater() {
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	public Long getUpdaterPeople() {
		return updaterPeople;
	}
	public void setUpdaterPeople(Long updaterPeople) {
		this.updaterPeople = updaterPeople;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

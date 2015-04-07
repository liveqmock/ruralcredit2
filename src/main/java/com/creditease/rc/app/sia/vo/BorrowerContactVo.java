package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人联系人信息
 */
public class BorrowerContactVo extends Entity{
	private static final long serialVersionUID = 6902346156268247778L;
	
	private Long borrowerInfoId;//借款人ID                                                           
	private Long contactId;  //联系人ID                                                         
	private String identityNo; //联系人身份证号码                                                 
	private String name;       //联系人姓名                                                       
	private String contactType;//联系人类型                                                       
	private String usedName;	//曾用名                                                           
	private String gender;		//联系人性别                                                       
	private String relation;	//与申请人关系                                                     
	private Short age;			//年龄                                                             
	private String liveAddress;//联系人居住地                                                     
	private String province;	//省                                                               
	private String city;		//市                                                               
	private String area;		//区                                                               
	private String source;		//1[进件联系人],2[补充联系人],3[手动添加联系人]                    
	private String jobLevel;	//职务                                                             
	private String job;		//职业                                                             
	private String mobile1;	//手机号1                                                          
	private String mobile2;	//手机号2                                                          
	private String mobile3;	//手机号3                                                          
	private String phone1Area;	//家庭电话区号                                                     
	private String faminlyPhone;//家庭电话号码                                                     
	private String homePhoneConnected;//家庭电话是否有效                                                 
	private String ownerName;	//家庭电话主人姓名                                                 
	private String phone1Area1;	//联系电话区号                                                     
	private String phone1;		//联系电话1                                                        
	private String phone2Area;	//联系电话2区号                                                    
	private String telSource;	//电话号码来源字典                                                 
	private String telphoneType;//电话号码类型。0[固定电话],1[小灵通],2[手机],3[未能查到信息],4[其它]
	private String telSourceDesc;//电话号码来源说明。                                               
	private String telLoc;		//号码归属地                                                       
	private String telRemark;	//号码类型说明                                                     
	private String phone2;		//联系电话2                                                        
	private String phone3;		//联系电话3                                                        
	private BigDecimal yearIncome;//月收入                                                           
	private String tradeCode;	//行业代码                                                         
	private String jobCode;		//职业代码                                                         
	private String jobDetail;	//职业明细                                                         
	private String workCase;	//工作或者就学情况                                                 
	private String isSupport;	//是否需要申请人供养                                               
	private String companyAddress;	//单位地址                                                         
	private String companyProvince;	//单位省                                                           
	private String companyCity;		//单位市                                                           
	private String companyArea;		//单位区                                                           
	private String companyName;		//单位名称                                                         
	private String companyScale;	//单位规模                                                         
	private Date relationTime;		//建立关系时间                                                     
	private String remark;			//联系人备注                                                       
	private String relationRemark;	//联系人备注                                                       
	private String houseAddress;	//家庭住址或者单位地址                                             
	private Long creater;		//创建人                                                           
	private Long updaterPeople;	//更新人                                                           
	private String isTogetherBorrower;//是否共同借款人                                                   
	private Date updateTime;		//最近更新时间                                                     
	private Date createTime;		//新增时间
	
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getUsedName() {
		return usedName;
	}
	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getPhone1Area() {
		return phone1Area;
	}
	public void setPhone1Area(String phone1Area) {
		this.phone1Area = phone1Area;
	}
	public String getFaminlyPhone() {
		return faminlyPhone;
	}
	public void setFaminlyPhone(String faminlyPhone) {
		this.faminlyPhone = faminlyPhone;
	}
	public String getHomePhoneConnected() {
		return homePhoneConnected;
	}
	public void setHomePhoneConnected(String homePhoneConnected) {
		this.homePhoneConnected = homePhoneConnected;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPhone1Area1() {
		return phone1Area1;
	}
	public void setPhone1Area1(String phone1Area1) {
		this.phone1Area1 = phone1Area1;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2Area() {
		return phone2Area;
	}
	public void setPhone2Area(String phone2Area) {
		this.phone2Area = phone2Area;
	}
	public String getTelSource() {
		return telSource;
	}
	public void setTelSource(String telSource) {
		this.telSource = telSource;
	}
	public String getTelphoneType() {
		return telphoneType;
	}
	public void setTelphoneType(String telphoneType) {
		this.telphoneType = telphoneType;
	}
	public String getTelSourceDesc() {
		return telSourceDesc;
	}
	public void setTelSourceDesc(String telSourceDesc) {
		this.telSourceDesc = telSourceDesc;
	}
	public String getTelLoc() {
		return telLoc;
	}
	public void setTelLoc(String telLoc) {
		this.telLoc = telLoc;
	}
	public String getTelRemark() {
		return telRemark;
	}
	public void setTelRemark(String telRemark) {
		this.telRemark = telRemark;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public BigDecimal getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobDetail() {
		return jobDetail;
	}
	public void setJobDetail(String jobDetail) {
		this.jobDetail = jobDetail;
	}
	public String getWorkCase() {
		return workCase;
	}
	public void setWorkCase(String workCase) {
		this.workCase = workCase;
	}
	public String getIsSupport() {
		return isSupport;
	}
	public void setIsSupport(String isSupport) {
		this.isSupport = isSupport;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyProvince() {
		return companyProvince;
	}
	public void setCompanyProvince(String companyProvince) {
		this.companyProvince = companyProvince;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyArea() {
		return companyArea;
	}
	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	public Date getRelationTime() {
		return relationTime;
	}
	public void setRelationTime(Date relationTime) {
		this.relationTime = relationTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRelationRemark() {
		return relationRemark;
	}
	public void setRelationRemark(String relationRemark) {
		this.relationRemark = relationRemark;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
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
	public String getIsTogetherBorrower() {
		return isTogetherBorrower;
	}
	public void setIsTogetherBorrower(String isTogetherBorrower) {
		this.isTogetherBorrower = isTogetherBorrower;
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

package com.creditease.rc.app.sia.vo;

import java.util.Date;
import java.util.List;
/**
 * 
 * @author hanjf
 * 借款人信息表
 */
public class BorrowerBasicInfoVo extends Entity{
	private static final long serialVersionUID = 5383922595166934539L;
	
	private Long borrowerInfoId;		//借款人ID	
	private Long transactionId;		//进件ID
	private Long businessBorrowerId;	//业务借款人ID
	private String systemSource;//系统来源  gedai，qihe，chedai                                                                                                    
	private String borrowerType;//字典 0.学生.1工薪2.小型企业主                                             
	private String mortgagorName;//客户姓名                                                                 
	private String nation;		//1 汉族；2 满族； 3 哈尼族； 4 彝族； 5 白族； 6 维吾尔族；7 傣族； 8 羌族
	private String formerName;	//曾用名                                                                   
	private Date birthday;	//出生日期                                                                 
	private String gender;	//1  男、 2  女                                                           
	private String idNumber;//证件号码                                                                 
	private String idType;	//1 身份证, 2 学生证, 3 工作证、 4 士兵证、 5 军官证、 6 护照 7 户口本     
	private Date idValidityDate;	  //证件有效期                                                               
	private Date cardValideEndDate;	  //证件有效期截止时间                                                       
	private String isTogetherBorrower;//是否共同借款人  0 是  1 否                                               
	private String houseHoldType;	  //1 本地城市 2 本地农村  3 外地城市  4 外地农村  5 其他                    
	private String marriage;		//1 已婚    2 未婚   3  离异    4 丧偶    5 初婚     6 复婚    7 其他      
	private String marriageRemark;	//婚姻状况其他项备注 
	private Short age;			//年龄
	private String tempCard;	  	//1 有 2 无                                                              
	private String customIdentity;	//1 法人、2 股东、3 其他                                                 
	private String maxDiploma;	  	//1 初中及以下；2 高中及以下, 3 专科； 4 本科； 5  硕士及以上；6 博士及以上
	private String domicileProvince;//户籍省                                                                 
	private String domicileCity;	//户籍市                                                                 
	private String domicileDistrict;//户籍区                                                                 
	private String domicileAddr;	//户籍地址                                                               
	private String registerPostcard;//户籍邮编                                                               
	private Date createTime;	  //创建日期                                                               
	private Date operDate;		  //最后更新时间                                                           
	private String hasChildren;	  //有无子女                                                               
	private String hasHouse;	  //房产情况                                                               
	private String hasCar;		  //本人名下是否有车                                                       
	private String isCreditReport;//是否代办信用报告                                                       
	private String loanState;	  //贷款状态                                                               
	private String ecifId;		  //ECIF客户ID                                                             
	private String remark;		  //备注                                                                   
	private Short historyFlag;  //历史标示                                                               
	private Long creater;	  //创建人                                                                 
	private Long updaterPeople;//更新人         
	
	private List<BorrowerImageVo> image;	//客户图像表
	private List<BorrowerLiveinfoVo> living;//借款人居住信息
	private List<BorrowerJobVo> job;		//借款人职业信息
	private List<BorrowerHouseVo> house;	//借款人房产信息
	private List<BorrowerFinanceVo> finance;//借款人财务
	private List<BorrowerContactVo> contact;//联系人信息
	private List<BorrowerContactWayVo> contactWay;//借款人联系方式表
	
	private BorrowerCompanyVo company;//助业产品需提供的资料
	private BorrowerEducationVo education;//借款人相关教育信息
	private BorrowerCarVo car;//借款人车辆信息
	private BorrowerMachineVo machine;//借款人农机信息
	
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getBusinessBorrowerId() {
		return businessBorrowerId;
	}
	public void setBusinessBorrowerId(Long businessBorrowerId) {
		this.businessBorrowerId = businessBorrowerId;
	}
	public String getSystemSource() {
		return systemSource;
	}
	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}
	public String getBorrowerType() {
		return borrowerType;
	}
	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
	}
	public String getMortgagorName() {
		return mortgagorName;
	}
	public void setMortgagorName(String mortgagorName) {
		this.mortgagorName = mortgagorName;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getFormerName() {
		return formerName;
	}
	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public Date getIdValidityDate() {
		return idValidityDate;
	}
	public void setIdValidityDate(Date idValidityDate) {
		this.idValidityDate = idValidityDate;
	}
	public Date getCardValideEndDate() {
		return cardValideEndDate;
	}
	public void setCardValideEndDate(Date cardValideEndDate) {
		this.cardValideEndDate = cardValideEndDate;
	}
	public String getIsTogetherBorrower() {
		return isTogetherBorrower;
	}
	public void setIsTogetherBorrower(String isTogetherBorrower) {
		this.isTogetherBorrower = isTogetherBorrower;
	}
	public String getHouseHoldType() {
		return houseHoldType;
	}
	public void setHouseHoldType(String houseHoldType) {
		this.houseHoldType = houseHoldType;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getMarriageRemark() {
		return marriageRemark;
	}
	public void setMarriageRemark(String marriageRemark) {
		this.marriageRemark = marriageRemark;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public String getTempCard() {
		return tempCard;
	}
	public void setTempCard(String tempCard) {
		this.tempCard = tempCard;
	}
	public String getCustomIdentity() {
		return customIdentity;
	}
	public void setCustomIdentity(String customIdentity) {
		this.customIdentity = customIdentity;
	}
	public String getMaxDiploma() {
		return maxDiploma;
	}
	public void setMaxDiploma(String maxDiploma) {
		this.maxDiploma = maxDiploma;
	}
	public String getDomicileProvince() {
		return domicileProvince;
	}
	public void setDomicileProvince(String domicileProvince) {
		this.domicileProvince = domicileProvince;
	}
	public String getDomicileCity() {
		return domicileCity;
	}
	public void setDomicileCity(String domicileCity) {
		this.domicileCity = domicileCity;
	}
	public String getDomicileDistrict() {
		return domicileDistrict;
	}
	public void setDomicileDistrict(String domicileDistrict) {
		this.domicileDistrict = domicileDistrict;
	}
	public String getDomicileAddr() {
		return domicileAddr;
	}
	public void setDomicileAddr(String domicileAddr) {
		this.domicileAddr = domicileAddr;
	}
	public String getRegisterPostcard() {
		return registerPostcard;
	}
	public void setRegisterPostcard(String registerPostcard) {
		this.registerPostcard = registerPostcard;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOperDate() {
		return operDate;
	}
	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}
	public String getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getHasHouse() {
		return hasHouse;
	}
	public void setHasHouse(String hasHouse) {
		this.hasHouse = hasHouse;
	}
	public String getHasCar() {
		return hasCar;
	}
	public void setHasCar(String hasCar) {
		this.hasCar = hasCar;
	}
	public String getIsCreditReport() {
		return isCreditReport;
	}
	public void setIsCreditReport(String isCreditReport) {
		this.isCreditReport = isCreditReport;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	public String getEcifId() {
		return ecifId;
	}
	public void setEcifId(String ecifId) {
		this.ecifId = ecifId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Short getHistoryFlag() {
		return historyFlag;
	}
	public void setHistoryFlag(Short historyFlag) {
		this.historyFlag = historyFlag;
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
	public List<BorrowerImageVo> getImage() {
		return image;
	}
	public void setImage(List<BorrowerImageVo> image) {
		this.image = image;
	}
	public List<BorrowerLiveinfoVo> getLiving() {
		return living;
	}
	public void setLiving(List<BorrowerLiveinfoVo> living) {
		this.living = living;
	}
	public List<BorrowerJobVo> getJob() {
		return job;
	}
	public void setJob(List<BorrowerJobVo> job) {
		this.job = job;
	}
	public List<BorrowerHouseVo> getHouse() {
		return house;
	}
	public void setHouse(List<BorrowerHouseVo> house) {
		this.house = house;
	}
	public List<BorrowerFinanceVo> getFinance() {
		return finance;
	}
	public void setFinance(List<BorrowerFinanceVo> finance) {
		this.finance = finance;
	}
	public List<BorrowerContactVo> getContact() {
		return contact;
	}
	public void setContact(List<BorrowerContactVo> contact) {
		this.contact = contact;
	}
	public List<BorrowerContactWayVo> getContactWay() {
		return contactWay;
	}
	public void setContactWay(List<BorrowerContactWayVo> contactWay) {
		this.contactWay = contactWay;
	}
	public BorrowerCompanyVo getCompany() {
		return company;
	}
	public void setCompany(BorrowerCompanyVo company) {
		this.company = company;
	}
	public BorrowerEducationVo getEducation() {
		return education;
	}
	public void setEducation(BorrowerEducationVo education) {
		this.education = education;
	}
	public BorrowerCarVo getCar() {
		return car;
	}
	public void setCar(BorrowerCarVo car) {
		this.car = car;
	}
	public BorrowerMachineVo getMachine() {
		return machine;
	}
	public void setMachine(BorrowerMachineVo machine) {
		this.machine = machine;
	}
	
}

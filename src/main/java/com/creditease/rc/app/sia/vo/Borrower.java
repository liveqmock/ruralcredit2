package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


/**
 * @author mw 
 * 2014-07-01
 */
public class Borrower extends Entity {

	
	private static final long serialVersionUID = 3121374032836838904L;

	private Integer applyId;// 申请表id

	private Integer clientId;// 客户id

	private Integer isTogether;// 是否共同贷款人  借款人类型(0 主借款人 1共同借款人 2失效借款人)

	private Integer isSubmit; // 是否已提交

	private String strclientName;// 客户姓名

	private String strformerName;// 曾用名

	private Integer strsex;// 性别

	private String strdegree;// 学历

	private Date datebirthday;// 出生日期

	private BigDecimal intcensusRegisterProvince;// 户籍详细地址省

	private BigDecimal intcensusRegisterCity;// 户籍详细地址市

	private Integer intcensusRegisterArea;// 户籍详细地址区

	private String intcensusRegisterInput;// 户籍详细地址
	
	private String strHomePhone;//家庭固定电话

	private String intcType;// 证件类型

	private String intidNumber;// 证件号码

	private Date intusefulLife;// 证件有效期

	private String intmarital;// 婚姻状况

	private String intisfamily;// 有无子女

	private String intishouse;// 房产状况

	private BigDecimal intaddressProvince;// 本人住址省

	private BigDecimal intaddressCity;// 本人住址市

	private BigDecimal intaddressArea;// 本人住址区

	private String straddressInput;// 本人住址

	private Integer intzipCode;// 邮政编码

	private String strlocalPhone;// 本市住址电话

	private String strmobilePhone;// 手机号码

	private String stremail;// 电子邮件

	private BigDecimal stryearIncome;// 个人年收入

	private BigDecimal strcreditAmount;// 信用卡额度
	
	private Integer intcohabit; // 共同居住者

	private String strworkCompany;// 工作单位全称( 就读高校全称)

	private BigDecimal intcomAddressProvince;// 工作单位地址省（高校地址省）

	private BigDecimal intcomAddressCity;// 工作单位地址市（高校地址市）

	private Integer intcomAddressArea;// 工作单位地址区（高校地址区）

	private String strcomAddressInput;// 工作单位地址（高校地址输入）

	private String strcompanyPhone;// 工作单位电话（院系联系电话）

	private Date dateworkTime;// 该单位工作时间

	private String strdepartment;// 所在部门（院系专业班级）

	private String strposition;// 职务输入项
	
	private String companyZipcode;// 公司邮编

	private String strunitCharacter;// 单位性质

	private Date operateTime; // 操作时间

	private Integer operator; // 操作人

	private Date createTime; // 创建时间

	private Integer creator; // 创建人

	private Integer fstatus;// 贷款状态
	
	private String strmessage;//QQ\MSN

	private String strremark;// 备注

	private Integer isAgencyreport;// 是否代办信用报告
	
	private String strcustomerLng; // 客户详细地点:地理经度
	
	private String strcustomerLat;// 客户详细地点:地理纬度

	private List<Contact> contacts;// 联系人

	private List<Image> images;// 上传资料
	
	private String ecifId;//ECIF客户ID
	
	private String monthPay;// 房产状况选有房有贷款时的月供
	
	private Integer hasCar;// 本人名下是否有车 0否 1是
	
	private Integer houseCondition;//居住情况
	
	private String houseConditionRemark;// 居住情况其它项备注
	
	private String strmobilePhone2;// 手机号码2
	
	private String registerZipcode;//户籍地址邮编
	
	private String inTheCityYears;//在此生活年数
	
	private Integer provideForCount;//供养人数
	
	private Integer intPosition;//职位
	
	private BigDecimal monthIncome;// 月收入
	
	private BigDecimal monthOtherIncome;// 月其它收入
	
	private String monthOtherIncomeRemark;//月其它收入备注
	
	private String maritalRemark;//婚姻状况其他项备注
	
	private String intWorkStatus;//工作状态 1、学生 2、在职3、无业   小企业主 工作人群 经营人群
	
	private ApplyHouse applyHouse;//宜楼产品需提供的资料
	
	private ApplyCompany applyCompany;//助业产品需提供的资料
	
	private Date dateenrollment; // 学贷入学时间
	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getIsTogether() {
		return isTogether;
	}

	public void setIsTogether(Integer isTogether) {
		this.isTogether = isTogether;
	}

	public Integer getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getStrclientName() {
		return strclientName;
	}

	public void setStrclientName(String strclientName) {
		this.strclientName = strclientName;
	}

	public String getStrformerName() {
		return strformerName;
	}

	public void setStrformerName(String strformerName) {
		this.strformerName = strformerName;
	}

	public Integer getStrsex() {
		return strsex;
	}

	public void setStrsex(Integer strsex) {
		this.strsex = strsex;
	}

	public String getStrdegree() {
		return strdegree;
	}

	public void setStrdegree(String strdegree) {
		this.strdegree = strdegree;
	}

	public Date getDatebirthday() {
		return datebirthday;
	}

	public void setDatebirthday(Date date) {
		this.datebirthday = date;
	}

	public BigDecimal getIntcensusRegisterProvince() {
		return intcensusRegisterProvince;
	}

	public void setIntcensusRegisterProvince(
			BigDecimal intcensusRegisterProvince) {
		this.intcensusRegisterProvince = intcensusRegisterProvince;
	}

	public BigDecimal getIntcensusRegisterCity() {
		return intcensusRegisterCity;
	}

	public void setIntcensusRegisterCity(BigDecimal intcensusRegisterCity) {
		this.intcensusRegisterCity = intcensusRegisterCity;
	}

	public Integer getIntcensusRegisterArea() {
		return intcensusRegisterArea;
	}

	public void setIntcensusRegisterArea(Integer intcensusRegisterArea) {
		this.intcensusRegisterArea = intcensusRegisterArea;
	}

	public String getIntcensusRegisterInput() {
		return intcensusRegisterInput;
	}

	public void setIntcensusRegisterInput(String intcensusRegisterInput) {
		this.intcensusRegisterInput = intcensusRegisterInput;
	}

	public String getIntcType() {
		return intcType;
	}

	public void setIntcType(String intcType) {
		this.intcType = intcType;
	}

	public String getIntidNumber() {
		return intidNumber;
	}

	public void setIntidNumber(String intidNumber) {
		this.intidNumber = intidNumber;
	}

	public Date getIntusefulLife() {
		return intusefulLife;
	}

	public void setIntusefulLife(Date intusefulLife) {
		this.intusefulLife = intusefulLife;
	}

	public String getIntmarital() {
		return intmarital;
	}

	public void setIntmarital(String intmarital) {
		this.intmarital = intmarital;
	}

	public String getIntisfamily() {
		return intisfamily;
	}

	public void setIntisfamily(String intisfamily) {
		this.intisfamily = intisfamily;
	}

	public String getIntishouse() {
		return intishouse;
	}

	public void setIntishouse(String intishouse) {
		this.intishouse = intishouse;
	}

	public BigDecimal getIntaddressProvince() {
		return intaddressProvince;
	}

	public void setIntaddressProvince(BigDecimal intaddressProvince) {
		this.intaddressProvince = intaddressProvince;
	}

	public BigDecimal getIntaddressCity() {
		return intaddressCity;
	}

	public void setIntaddressCity(BigDecimal intaddressCity) {
		this.intaddressCity = intaddressCity;
	}

	public BigDecimal getIntaddressArea() {
		return intaddressArea;
	}

	public void setIntaddressArea(BigDecimal intaddressArea) {
		this.intaddressArea = intaddressArea;
	}

	public String getStraddressInput() {
		return straddressInput;
	}

	public void setStraddressInput(String straddressInput) {
		this.straddressInput = straddressInput;
	}

	public Integer getIntzipCode() {
		return intzipCode;
	}

	public void setIntzipCode(Integer intzipCode) {
		this.intzipCode = intzipCode;
	}

	public String getStrlocalPhone() {
		return strlocalPhone;
	}

	public void setStrlocalPhone(String strlocalPhone) {
		this.strlocalPhone = strlocalPhone;
	}

	public String getStrmobilePhone() {
		return strmobilePhone;
	}

	public void setStrmobilePhone(String strmobilePhone) {
		this.strmobilePhone = strmobilePhone;
	}

	public String getStremail() {
		return stremail;
	}

	public void setStremail(String stremail) {
		this.stremail = stremail;
	}

	public BigDecimal getStryearIncome() {
		return stryearIncome;
	}

	public void setStryearIncome(BigDecimal stryearIncome) {
		this.stryearIncome = stryearIncome;
	}

	public BigDecimal getStrcreditAmount() {
		return strcreditAmount;
	}

	public void setStrcreditAmount(BigDecimal strcreditAmount) {
		this.strcreditAmount = strcreditAmount;
	}

	public String getStrworkCompany() {
		return strworkCompany;
	}

	public void setStrworkCompany(String strworkCompany) {
		this.strworkCompany = strworkCompany;
	}

	public BigDecimal getIntcomAddressProvince() {
		return intcomAddressProvince;
	}

	public void setIntcomAddressProvince(BigDecimal intcomAddressProvince) {
		this.intcomAddressProvince = intcomAddressProvince;
	}

	public BigDecimal getIntcomAddressCity() {
		return intcomAddressCity;
	}

	public void setIntcomAddressCity(BigDecimal intcomAddressCity) {
		this.intcomAddressCity = intcomAddressCity;
	}

	public Integer getIntcomAddressArea() {
		return intcomAddressArea;
	}

	public void setIntcomAddressArea(Integer intcomAddressArea) {
		this.intcomAddressArea = intcomAddressArea;
	}

	public String getStrcomAddressInput() {
		return strcomAddressInput;
	}

	public void setStrcomAddressInput(String strcomAddressInput) {
		this.strcomAddressInput = strcomAddressInput;
	}

	public String getStrcompanyPhone() {
		return strcompanyPhone;
	}

	public void setStrcompanyPhone(String strcompanyPhone) {
		this.strcompanyPhone = strcompanyPhone;
	}

	public Date getDateworkTime() {
		return dateworkTime;
	}

	public void setDateworkTime(Date dateworkTime) {
		this.dateworkTime = dateworkTime;
	}

	public String getStrdepartment() {
		return strdepartment;
	}

	public void setStrdepartment(String strdepartment) {
		this.strdepartment = strdepartment;
	}

	public String getStrposition() {
		return strposition;
	}

	public void setStrposition(String strposition) {
		this.strposition = strposition;
	}

	public String getStrunitCharacter() {
		return strunitCharacter;
	}

	public void setStrunitCharacter(String strunitCharacter) {
		this.strunitCharacter = strunitCharacter;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getStrremark() {
		return strremark;
	}

	public void setStrremark(String strremark) {
		this.strremark = strremark;
	}

	public Integer getIsAgencyreport() {
		return isAgencyreport;
	}

	public void setIsAgencyreport(Integer isAgencyreport) {
		this.isAgencyreport = isAgencyreport;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Image> getImages() {
		return images;
	}

	public Integer getFstatus() {
		return fstatus;
	}

	public void setFstatus(Integer fstatus) {
		this.fstatus = fstatus;
	}

	public Integer getIntcohabit() {
		return intcohabit;
	}

	public void setIntcohabit(Integer intcohabit) {
		this.intcohabit = intcohabit;
	}

	public String getStrcustomerLng() {
		return strcustomerLng;
	}

	public void setStrcustomerLng(String strcustomerLng) {
		this.strcustomerLng = strcustomerLng;
	}

	public String getStrcustomerLat() {
		return strcustomerLat;
	}

	public void setStrcustomerLat(String strcustomerLat) {
		this.strcustomerLat = strcustomerLat;
	}

	public String getCompanyZipcode() {
		return companyZipcode;
	}

	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}

	public String getStrHomePhone() {
		return strHomePhone;
	}

	public void setStrHomePhone(String strHomePhone) {
		this.strHomePhone = strHomePhone;
	}

	public String getStrmessage() {
		return strmessage;
	}

	public void setStrmessage(String strmessage) {
		this.strmessage = strmessage;
	}

	public String getEcifId() {
		return ecifId;
	}

	public void setEcifId(String ecifId) {
		this.ecifId = ecifId;
	}

	public String getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(String monthPay) {
		this.monthPay = monthPay;
	}

	public Integer getHasCar() {
		return hasCar;
	}

	public void setHasCar(Integer hasCar) {
		this.hasCar = hasCar;
	}

	public Integer getHouseCondition() {
		return houseCondition;
	}

	public void setHouseCondition(Integer houseCondition) {
		this.houseCondition = houseCondition;
	}

	public String getHouseConditionRemark() {
		return houseConditionRemark;
	}

	public void setHouseConditionRemark(String houseConditionRemark) {
		this.houseConditionRemark = houseConditionRemark;
	}

	public String getStrmobilePhone2() {
		return strmobilePhone2;
	}

	public void setStrmobilePhone2(String strmobilePhone2) {
		this.strmobilePhone2 = strmobilePhone2;
	}



	public String getRegisterZipcode() {
		return registerZipcode;
	}

	public void setRegisterZipcode(String registerZipcode) {
		this.registerZipcode = registerZipcode;
	}

	public String getInTheCityYears() {
		return inTheCityYears;
	}

	public void setInTheCityYears(String inTheCityYears) {
		this.inTheCityYears = inTheCityYears;
	}

	public Integer getProvideForCount() {
		return provideForCount;
	}

	public void setProvideForCount(Integer provideForCount) {
		this.provideForCount = provideForCount;
	}

	public Integer getIntPosition() {
		return intPosition;
	}

	public void setIntPosition(Integer intPosition) {
		this.intPosition = intPosition;
	}

	public BigDecimal getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

	public BigDecimal getMonthOtherIncome() {
		return monthOtherIncome;
	}

	public void setMonthOtherIncome(BigDecimal monthOtherIncome) {
		this.monthOtherIncome = monthOtherIncome;
	}

	public String getMonthOtherIncomeRemark() {
		return monthOtherIncomeRemark;
	}

	public void setMonthOtherIncomeRemark(String monthOtherIncomeRemark) {
		this.monthOtherIncomeRemark = monthOtherIncomeRemark;
	}

	public ApplyHouse getApplyHouse() {
		return applyHouse;
	}

	public void setApplyHouse(ApplyHouse applyHouse) {
		this.applyHouse = applyHouse;
	}

	public ApplyCompany getApplyCompany() {
		return applyCompany;
	}

	public void setApplyCompany(ApplyCompany applyCompany) {
		this.applyCompany = applyCompany;
	}

	public String getMaritalRemark() {
		return maritalRemark;
	}

	public void setMaritalRemark(String maritalRemark) {
		this.maritalRemark = maritalRemark;
	}

	public String getIntWorkStatus() {
		return intWorkStatus;
	}

	public void setIntWorkStatus(String intWorkStatus) {
		this.intWorkStatus = intWorkStatus;
	}

	public Date getDateenrollment() {
		return dateenrollment;
	}

	public void setDateenrollment(Date dateenrollment) {
		this.dateenrollment = dateenrollment;
	}
	
}

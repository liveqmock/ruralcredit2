package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人公司信息
 */
public class BorrowerCompanyVo extends Entity{
	private static final long serialVersionUID = 6982223448123083133L;

	private Long borrowerInfoId;     	//借款人ID
	private Long companyId;          	//公司ID
	private String registerName;        //注册名称                                                                   
	private BigDecimal registerCapital; //注册资本                                                                   
	private Date registerDate;        	//注册时间                                                                   
	private Date establishDate;      	//成立日期                                                                   
	private Integer staffNum;			//员工人数                                                                   
	private String customerIdentType;	//客户身份                                                                   
	private String remark;				//客户身份为其他时增加详情                                                   
	private String legalRepresentative;	//法人代表                                                                   
	private String annualSurveied;		//是否通过年检。0[否], 1[是]                                                 
	private String contentType;			//资料类型。0[身份证明],1[股权证明],2[地址证明],3[未知],4[借款人资料备注]    
	private BigDecimal portion;         //借款人占股比例                                                             
	private String relationship;		//与其他股东关系。                                                           
	private String trade;				//所在行业                                                                   
	private String trade2;				//所在行业2                                                                  
	private String registerProvince;	//注册地址省                                                                 
	private String registerCity;		//注册地址市                                                                 
	private String registerArea;		//注册地址区                                                                 
	private String registerAddress;		//注册地址                                                                   
	private String organizationCode;	//机构代码                                                                   
	private String companyHouseType;	//公司房产类型：0.贷款购置,有房贷1.租赁,有房租2.自有房产,无费用3.宿舍,有房租 
	private String mainBusiness;	    //主营业务                                                                   
	private String operateAddressType;	//经营场所地址分类                                                           
	private BigDecimal operatePlaceArea;//经营场所面积                                                               
	private BigDecimal alreadyUseYears;	//经营场所已使用年限                                                         
	private String province;	        //经营场所地址省                                                             
	private String city;		        //经营场所地址市                                                             
	private String area;		        //经营场所地址区                                                             
	private String operateAddress;		//经营场所地址                                                               
	private String workType;			//企业类型                                                                   
	private String workNature;			//公司性质                                                                   
	private String businessTrade;		//经营行业                                                                   
	private String workPhone;			//办公电话                                                                   
	private String businessContent1;	//经营内容／经营项目明细1                                                    
	private String businessContent2;	//经营内容／经营项目明细2                                                    
	private String businessContent3;	//经营内容／经营项目明细3                                                    
	private String businessContentDetail;//经营内容／经营项目明细                                                     
	private Short startYear;			//起始年限                                                                   
	private BigDecimal businessYear;	//经营年限                                                                   
	private BigDecimal monthIncome;		//每月收入／月收入                                                           
//	private String licenceNo2;			//营业执照类型                                                               
	private BigDecimal monthNetIncome;	//每月净收入                                                                 
	private String licenceType;			//营业执照                                                                   
	private Date businessStartTime;		//在本地开始经营时间                                                         
	private String taxRegisterNo;		//税务登记证                                                                 
	private String businessState;		//1 营业； 2 停业                                                            
	private String shopName;			//商号名称                                                                   
	private String tenantry; ;			//承租方                                                                     
	private String landlord;			//出租方                                                                     
	private BigDecimal rentTerm;		//租赁期限                                                                   
	private BigDecimal yearRent;		//店面年租金                                                                 
	private BigDecimal averageSaleAmount;//平均月销售额                                                               
	private BigDecimal yearNetProfit;	//年净利润                                                                   
	private BigDecimal turnoverCapital;	//现有周转资金                                                               
	private BigDecimal inventory;		//存货                                                                       
	private String traficc;				//交通工具／设备                                                             
	private BigDecimal recieveAmount;	//应收账款                                                                   
	private BigDecimal payableAmount;	//应付账款                                                                   
	private BigDecimal nopaySalary; ;	//未付工资                                                                   
	private BigDecimal loanBalance;		//生意借款                                                                   
	private BigDecimal otherDebit;		//其他负债                                                                   
	private BigDecimal assetTotalBalance;//该项生意资产总额                                                           
	private Long creater;				//创建人                                                                     
	private Long updaterPeople;			//更新人                                                                     
	private Date updateDate;			//最近跟新时间
	private Date createDate;			//新增时间
	
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public BigDecimal getRegisterCapital() {
		return registerCapital;
	}
	public void setRegisterCapital(BigDecimal registerCapital) {
		this.registerCapital = registerCapital;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
	public Integer getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	public String getCustomerIdentType() {
		return customerIdentType;
	}
	public void setCustomerIdentType(String customerIdentType) {
		this.customerIdentType = customerIdentType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getAnnualSurveied() {
		return annualSurveied;
	}
	public void setAnnualSurveied(String annualSurveied) {
		this.annualSurveied = annualSurveied;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public BigDecimal getPortion() {
		return portion;
	}
	public void setPortion(BigDecimal portion) {
		this.portion = portion;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getTrade2() {
		return trade2;
	}
	public void setTrade2(String trade2) {
		this.trade2 = trade2;
	}
	public String getRegisterProvince() {
		return registerProvince;
	}
	public void setRegisterProvince(String registerProvince) {
		this.registerProvince = registerProvince;
	}
	public String getRegisterCity() {
		return registerCity;
	}
	public void setRegisterCity(String registerCity) {
		this.registerCity = registerCity;
	}
	public String getRegisterArea() {
		return registerArea;
	}
	public void setRegisterArea(String registerArea) {
		this.registerArea = registerArea;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getCompanyHouseType() {
		return companyHouseType;
	}
	public void setCompanyHouseType(String companyHouseType) {
		this.companyHouseType = companyHouseType;
	}
	public String getMainBusiness() {
		return mainBusiness;
	}
	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}
	public String getOperateAddressType() {
		return operateAddressType;
	}
	public void setOperateAddressType(String operateAddressType) {
		this.operateAddressType = operateAddressType;
	}
	public BigDecimal getOperatePlaceArea() {
		return operatePlaceArea;
	}
	public void setOperatePlaceArea(BigDecimal operatePlaceArea) {
		this.operatePlaceArea = operatePlaceArea;
	}
	public BigDecimal getAlreadyUseYears() {
		return alreadyUseYears;
	}
	public void setAlreadyUseYears(BigDecimal alreadyUseYears) {
		this.alreadyUseYears = alreadyUseYears;
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
	public String getOperateAddress() {
		return operateAddress;
	}
	public void setOperateAddress(String operateAddress) {
		this.operateAddress = operateAddress;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getWorkNature() {
		return workNature;
	}
	public void setWorkNature(String workNature) {
		this.workNature = workNature;
	}
	public String getBusinessTrade() {
		return businessTrade;
	}
	public void setBusinessTrade(String businessTrade) {
		this.businessTrade = businessTrade;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getBusinessContent1() {
		return businessContent1;
	}
	public void setBusinessContent1(String businessContent1) {
		this.businessContent1 = businessContent1;
	}
	public String getBusinessContent2() {
		return businessContent2;
	}
	public void setBusinessContent2(String businessContent2) {
		this.businessContent2 = businessContent2;
	}
	public String getBusinessContent3() {
		return businessContent3;
	}
	public void setBusinessContent3(String businessContent3) {
		this.businessContent3 = businessContent3;
	}
	public String getBusinessContentDetail() {
		return businessContentDetail;
	}
	public void setBusinessContentDetail(String businessContentDetail) {
		this.businessContentDetail = businessContentDetail;
	}
	public Short getStartYear() {
		return startYear;
	}
	public void setStartYear(Short startYear) {
		this.startYear = startYear;
	}
	public BigDecimal getBusinessYear() {
		return businessYear;
	}
	public void setBusinessYear(BigDecimal businessYear) {
		this.businessYear = businessYear;
	}
	public BigDecimal getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}
	public BigDecimal getMonthNetIncome() {
		return monthNetIncome;
	}
	public void setMonthNetIncome(BigDecimal monthNetIncome) {
		this.monthNetIncome = monthNetIncome;
	}
	public String getLicenceType() {
		return licenceType;
	}
	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}
	public Date getBusinessStartTime() {
		return businessStartTime;
	}
	public void setBusinessStartTime(Date businessStartTime) {
		this.businessStartTime = businessStartTime;
	}
	public String getTaxRegisterNo() {
		return taxRegisterNo;
	}
	public void setTaxRegisterNo(String taxRegisterNo) {
		this.taxRegisterNo = taxRegisterNo;
	}
	public String getBusinessState() {
		return businessState;
	}
	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getTenantry() {
		return tenantry;
	}
	public void setTenantry(String tenantry) {
		this.tenantry = tenantry;
	}
	public String getLandlord() {
		return landlord;
	}
	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}
	public BigDecimal getRentTerm() {
		return rentTerm;
	}
	public void setRentTerm(BigDecimal rentTerm) {
		this.rentTerm = rentTerm;
	}
	public BigDecimal getYearRent() {
		return yearRent;
	}
	public void setYearRent(BigDecimal yearRent) {
		this.yearRent = yearRent;
	}
	public BigDecimal getAverageSaleAmount() {
		return averageSaleAmount;
	}
	public void setAverageSaleAmount(BigDecimal averageSaleAmount) {
		this.averageSaleAmount = averageSaleAmount;
	}
	public BigDecimal getYearNetProfit() {
		return yearNetProfit;
	}
	public void setYearNetProfit(BigDecimal yearNetProfit) {
		this.yearNetProfit = yearNetProfit;
	}
	public BigDecimal getTurnoverCapital() {
		return turnoverCapital;
	}
	public void setTurnoverCapital(BigDecimal turnoverCapital) {
		this.turnoverCapital = turnoverCapital;
	}
	public BigDecimal getInventory() {
		return inventory;
	}
	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}
	public String getTraficc() {
		return traficc;
	}
	public void setTraficc(String traficc) {
		this.traficc = traficc;
	}
	public BigDecimal getRecieveAmount() {
		return recieveAmount;
	}
	public void setRecieveAmount(BigDecimal recieveAmount) {
		this.recieveAmount = recieveAmount;
	}
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}
	public BigDecimal getNopaySalary() {
		return nopaySalary;
	}
	public void setNopaySalary(BigDecimal nopaySalary) {
		this.nopaySalary = nopaySalary;
	}
	public BigDecimal getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}
	public BigDecimal getOtherDebit() {
		return otherDebit;
	}
	public void setOtherDebit(BigDecimal otherDebit) {
		this.otherDebit = otherDebit;
	}
	public BigDecimal getAssetTotalBalance() {
		return assetTotalBalance;
	}
	public void setAssetTotalBalance(BigDecimal assetTotalBalance) {
		this.assetTotalBalance = assetTotalBalance;
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

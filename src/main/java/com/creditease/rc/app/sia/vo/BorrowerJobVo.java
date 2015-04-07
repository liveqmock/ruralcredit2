package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人职业信息
 */
public class BorrowerJobVo extends Entity{
	private static final long serialVersionUID = 7802135612581927755L;
	
	private Long borrowerInfoId;	//借款人ID                                                                                                                    
	private Long jobId;          //职业ID                                                                                                                      
	private String jobType;        	//职业类型：1 务农、2 待业、3 学生、4 打工、 5 政府机构、 6 经营                                                              
	private String jobDetail;      	//职业明细                                                                                                                    
	private String workStatus;     	//就业状况：1 失业；2 有工作（工作）                                                                                          
	private String orgName;        	//单位名称                                                                                                                    
	private String orgType;        	//单位性质                                                                                                                    
	private String department;      //所属部门                                                                                                                    
	private String major;           //专业名称                                                                                                                    
	private String orgProvice;     	//单位地址所在省                                                                                                              
	private String orgCity;        	//单位地址所在市                                                                                                              
	private String orgDistrict;    	//单位地址所在区                                                                                                              
	private String postCard;       	//单位地址邮编                                                                                                                
	private String privOrg;        	//原单位名称                                                                                                                  
	private String privWorkYears; 	//原单位工作年限                                                                                                              
	private String orgAddress;     	//单位地址                                                                                                                    
	private String companyScale;   	//单位规模                                                                                                                    
	private BigDecimal workYears;  	//工作年限                                                                                                                    
	private String orgTelArea;    	//单位电话区号                                                                                                                
	private String departmentTel;  	//单位电话                                                                                                                    
	private String companyQq;      	//QQ                                                                                                                          
	private String trade;           //客户行业                                                                                                                    
	private String jobLevel;       	//1 负责人、2 高级管理人员、3 中级管理人员、 4 一般管理人员、 5 一般正式员工、 6 派遣员工、7 非正式员工、 8 退休人员、 9 其他 
	private String title;           //担任职务                                                                                                                    
	private String customType;     	//工薪人群／经营人群                                                                                                          
	private BigDecimal monthPay;   	//月薪                                                                                                                        
	private BigDecimal otherIncome;	//其他收入                                                                                                                    
	private Date recruitmentDate;	//入职日期                                                                                                                    
	private String surveyRecord;	//调查记录                                                                                                                    
	private BigDecimal monthIncome;	//每月收入                                                                                                                    
	private BigDecimal monthOtherIncome;//每月其他收入                                                                                                                
	private String incomeExplain;	//每月其他收入说明                                                                                                            
	private BigDecimal yearIncome;  //个人年度总收入                                                                                                              
	private String operateHistory;	//过往说明/经营历史                                                                                                           
	private Long creater;		//创建人                                                                                                                      
	private Long updaterPeople;	//更新人                                                                                                                      
	private Date updateTime;		//最近更新时间                                                                                                                
	private Date createTime;		//新增时间                                                                                                                    
	private String trade2;			//客户行业2   
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getJobDetail() {
		return jobDetail;
	}
	public void setJobDetail(String jobDetail) {
		this.jobDetail = jobDetail;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getOrgProvice() {
		return orgProvice;
	}
	public void setOrgProvice(String orgProvice) {
		this.orgProvice = orgProvice;
	}
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	public String getOrgDistrict() {
		return orgDistrict;
	}
	public void setOrgDistrict(String orgDistrict) {
		this.orgDistrict = orgDistrict;
	}
	public String getPostCard() {
		return postCard;
	}
	public void setPostCard(String postCard) {
		this.postCard = postCard;
	}
	public String getPrivOrg() {
		return privOrg;
	}
	public void setPrivOrg(String privOrg) {
		this.privOrg = privOrg;
	}
	public String getPrivWorkYears() {
		return privWorkYears;
	}
	public void setPrivWorkYears(String privWorkYears) {
		this.privWorkYears = privWorkYears;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	public BigDecimal getWorkYears() {
		return workYears;
	}
	public void setWorkYears(BigDecimal workYears) {
		this.workYears = workYears;
	}
	public String getOrgTelArea() {
		return orgTelArea;
	}
	public void setOrgTelArea(String orgTelArea) {
		this.orgTelArea = orgTelArea;
	}
	public String getDepartmentTel() {
		return departmentTel;
	}
	public void setDepartmentTel(String departmentTel) {
		this.departmentTel = departmentTel;
	}
	public String getCompanyQq() {
		return companyQq;
	}
	public void setCompanyQq(String companyQq) {
		this.companyQq = companyQq;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	public BigDecimal getMonthPay() {
		return monthPay;
	}
	public void setMonthPay(BigDecimal monthPay) {
		this.monthPay = monthPay;
	}
	public BigDecimal getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Date getRecruitmentDate() {
		return recruitmentDate;
	}
	public void setRecruitmentDate(Date recruitmentDate) {
		this.recruitmentDate = recruitmentDate;
	}
	public String getSurveyRecord() {
		return surveyRecord;
	}
	public void setSurveyRecord(String surveyRecord) {
		this.surveyRecord = surveyRecord;
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
	public String getIncomeExplain() {
		return incomeExplain;
	}
	public void setIncomeExplain(String incomeExplain) {
		this.incomeExplain = incomeExplain;
	}
	public BigDecimal getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}
	public String getOperateHistory() {
		return operateHistory;
	}
	public void setOperateHistory(String operateHistory) {
		this.operateHistory = operateHistory;
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
	public String getTrade2() {
		return trade2;
	}
	public void setTrade2(String trade2) {
		this.trade2 = trade2;
	}
	
}

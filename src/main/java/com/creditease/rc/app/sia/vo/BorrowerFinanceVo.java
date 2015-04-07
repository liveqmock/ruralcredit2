package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author hanjf
 * 借款人财务
 */
public class BorrowerFinanceVo extends Entity{
	private static final long serialVersionUID = -4539848251707972978L;
	
	private Long borrowerInfoId;	//借款人ID                                                                    
	private Long financeId;		//财务ID                                                                      
	private String payCashName;		//存款机构或应支付现金方：农行；信用社；其他银行；小贷机构；个人              
	private String payCashAddress;	//存款机构或应支付现金方地址                                                  
	private BigDecimal loanAmountMax;//自述借款额度范围最大值                                                      
	private BigDecimal amount;      //金额                                                                        
	private String certDocument;	//证明文档                                                                    
	private BigDecimal socialFund;  //拥有社保基金                                                                
	private BigDecimal majorIncome; //主要收入来源                                                                
	private BigDecimal monthSalary; //每月工资                                                                    
	private BigDecimal monthIncome; //月收入                                                                      
	private BigDecimal monthOtherIncome;//月其他收入                                                                  
	private String otherIncomeRemark;	//其他收入说明                                                                
	private BigDecimal houseRent;       //房屋出租                                                                    
	private BigDecimal otherIncome;     //其他所得                                                                    
	private String capitalSource;		//还款资金来源                                                                
	private String hasCar;				//本人名下是否有车                                                            
	private String otherIncomeSource1;	//其他收入来源－1                                                             
	private String otherIncomeSource2;	//其他收入来源－2                                                             
	private String otherIncomeSource3;	//其他收入来源－3                                                             
	private Integer houseNum;		//住房套数                                                                    
	private BigDecimal houseValue;  //住房价值                                                                    
	private String houseRemark;		//住房备注                                                                    
	private Short carNum;			//汽车数量                                                                    
	private BigDecimal carValue;    //汽车价值                                                                    
	private String carRemark;		//汽车备注                                                                    
	private String investCase;		//投资情况                                                                    
	private BigDecimal investValue; //投资价值                                                                    
	private String investRemark;	//投资备注                                                                    
	private String otherCase;		//其他情况                                                                    
	private BigDecimal otherValue;	//其他价值                                                                    
	private String otherRemark;		//其他备注                                                                    
//	private Date lastUpdateDate;	//最近更新日期                                                                
//	private Date creatDate;			//创建日期                                                                    
	private BigDecimal creditCardLimit;	//信用卡额度                                                                  
	private Integer validAccountNum;	//信用卡有效账户数量                                                          
	private BigDecimal highAmount;		//信用卡单张最高额度                                                          
	private BigDecimal creditCardSum;	//信用卡总额度                                                                
	private String creditState;			//信用卡状态：a信用卡使用良好;b历史信用良好;c历史信用较差;d酌情考虑：e直接拒绝
	private BigDecimal familyYearIncome;//家庭年收入                                                                  
	private BigDecimal yearIncome;		//年收入                                                                      
	private BigDecimal averageMonthExpense;//平均月生活支出                                                              
	private BigDecimal repaymentAmount;	//还款（）元／月                                                              
	private BigDecimal otherExpense;	//其他支出（）元／年                                                          
	private String historyWork;			//申请人过往工作／经营历史                                                    
	private Integer currentLoanNum;		//当前进行中贷款账户数量                                                      
	private BigDecimal currentLoanAmount;//当前贷款余额总额                                                            
	private String loanState;		//贷款状态 a信用卡使用良好;b历史信用良好;c历史信用较差;d酌情考虑：e直接拒绝   
	private String isException;		//查询记录次数是否异常 良好 ；异常                                            
	private String creditLevel;		//客户信用等级 A  B  C  D  E                                                  
	private String specialAttenation;//建议回款特别关注轨迹：是 ；否                                               
	private Long creater;		//创建人                                                                      	
	private Long updaterPeople;	//更新人                                                                      
	private Date createTime;		//创建时间                                                                    
	private Date updateTime;		//最近更新时间                                                                
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getFinanceId() {
		return financeId;
	}
	public void setFinanceId(Long financeId) {
		this.financeId = financeId;
	}
	public String getPayCashName() {
		return payCashName;
	}
	public void setPayCashName(String payCashName) {
		this.payCashName = payCashName;
	}
	public String getPayCashAddress() {
		return payCashAddress;
	}
	public void setPayCashAddress(String payCashAddress) {
		this.payCashAddress = payCashAddress;
	}
	public BigDecimal getLoanAmountMax() {
		return loanAmountMax;
	}
	public void setLoanAmountMax(BigDecimal loanAmountMax) {
		this.loanAmountMax = loanAmountMax;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCertDocument() {
		return certDocument;
	}
	public void setCertDocument(String certDocument) {
		this.certDocument = certDocument;
	}
	public BigDecimal getSocialFund() {
		return socialFund;
	}
	public void setSocialFund(BigDecimal socialFund) {
		this.socialFund = socialFund;
	}
	public BigDecimal getMajorIncome() {
		return majorIncome;
	}
	public void setMajorIncome(BigDecimal majorIncome) {
		this.majorIncome = majorIncome;
	}
	public BigDecimal getMonthSalary() {
		return monthSalary;
	}
	public void setMonthSalary(BigDecimal monthSalary) {
		this.monthSalary = monthSalary;
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
	public String getOtherIncomeRemark() {
		return otherIncomeRemark;
	}
	public void setOtherIncomeRemark(String otherIncomeRemark) {
		this.otherIncomeRemark = otherIncomeRemark;
	}
	public BigDecimal getHouseRent() {
		return houseRent;
	}
	public void setHouseRent(BigDecimal houseRent) {
		this.houseRent = houseRent;
	}
	public BigDecimal getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getCapitalSource() {
		return capitalSource;
	}
	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}
	public String getHasCar() {
		return hasCar;
	}
	public void setHasCar(String hasCar) {
		this.hasCar = hasCar;
	}
	public String getOtherIncomeSource1() {
		return otherIncomeSource1;
	}
	public void setOtherIncomeSource1(String otherIncomeSource1) {
		this.otherIncomeSource1 = otherIncomeSource1;
	}
	public String getOtherIncomeSource2() {
		return otherIncomeSource2;
	}
	public void setOtherIncomeSource2(String otherIncomeSource2) {
		this.otherIncomeSource2 = otherIncomeSource2;
	}
	public String getOtherIncomeSource3() {
		return otherIncomeSource3;
	}
	public void setOtherIncomeSource3(String otherIncomeSource3) {
		this.otherIncomeSource3 = otherIncomeSource3;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public BigDecimal getHouseValue() {
		return houseValue;
	}
	public void setHouseValue(BigDecimal houseValue) {
		this.houseValue = houseValue;
	}
	public String getHouseRemark() {
		return houseRemark;
	}
	public void setHouseRemark(String houseRemark) {
		this.houseRemark = houseRemark;
	}
	public Short getCarNum() {
		return carNum;
	}
	public void setCarNum(Short carNum) {
		this.carNum = carNum;
	}
	public BigDecimal getCarValue() {
		return carValue;
	}
	public void setCarValue(BigDecimal carValue) {
		this.carValue = carValue;
	}
	public String getCarRemark() {
		return carRemark;
	}
	public void setCarRemark(String carRemark) {
		this.carRemark = carRemark;
	}
	public String getInvestCase() {
		return investCase;
	}
	public void setInvestCase(String investCase) {
		this.investCase = investCase;
	}
	public BigDecimal getInvestValue() {
		return investValue;
	}
	public void setInvestValue(BigDecimal investValue) {
		this.investValue = investValue;
	}
	public String getInvestRemark() {
		return investRemark;
	}
	public void setInvestRemark(String investRemark) {
		this.investRemark = investRemark;
	}
	public String getOtherCase() {
		return otherCase;
	}
	public void setOtherCase(String otherCase) {
		this.otherCase = otherCase;
	}
	public BigDecimal getOtherValue() {
		return otherValue;
	}
	public void setOtherValue(BigDecimal otherValue) {
		this.otherValue = otherValue;
	}
	public String getOtherRemark() {
		return otherRemark;
	}
	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}
	public BigDecimal getCreditCardLimit() {
		return creditCardLimit;
	}
	public void setCreditCardLimit(BigDecimal creditCardLimit) {
		this.creditCardLimit = creditCardLimit;
	}
	public Integer getValidAccountNum() {
		return validAccountNum;
	}
	public void setValidAccountNum(Integer validAccountNum) {
		this.validAccountNum = validAccountNum;
	}
	public BigDecimal getHighAmount() {
		return highAmount;
	}
	public void setHighAmount(BigDecimal highAmount) {
		this.highAmount = highAmount;
	}
	public BigDecimal getCreditCardSum() {
		return creditCardSum;
	}
	public void setCreditCardSum(BigDecimal creditCardSum) {
		this.creditCardSum = creditCardSum;
	}
	public String getCreditState() {
		return creditState;
	}
	public void setCreditState(String creditState) {
		this.creditState = creditState;
	}
	public BigDecimal getFamilyYearIncome() {
		return familyYearIncome;
	}
	public void setFamilyYearIncome(BigDecimal familyYearIncome) {
		this.familyYearIncome = familyYearIncome;
	}
	public BigDecimal getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}
	public BigDecimal getAverageMonthExpense() {
		return averageMonthExpense;
	}
	public void setAverageMonthExpense(BigDecimal averageMonthExpense) {
		this.averageMonthExpense = averageMonthExpense;
	}
	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public BigDecimal getOtherExpense() {
		return otherExpense;
	}
	public void setOtherExpense(BigDecimal otherExpense) {
		this.otherExpense = otherExpense;
	}
	public String getHistoryWork() {
		return historyWork;
	}
	public void setHistoryWork(String historyWork) {
		this.historyWork = historyWork;
	}
	public Integer getCurrentLoanNum() {
		return currentLoanNum;
	}
	public void setCurrentLoanNum(Integer currentLoanNum) {
		this.currentLoanNum = currentLoanNum;
	}
	public BigDecimal getCurrentLoanAmount() {
		return currentLoanAmount;
	}
	public void setCurrentLoanAmount(BigDecimal currentLoanAmount) {
		this.currentLoanAmount = currentLoanAmount;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	public String getIsException() {
		return isException;
	}
	public void setIsException(String isException) {
		this.isException = isException;
	}
	public String getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}
	public String getSpecialAttenation() {
		return specialAttenation;
	}
	public void setSpecialAttenation(String specialAttenation) {
		this.specialAttenation = specialAttenation;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditease.rc.domain.SpecialApply;
/**
 * 
 * @author hanjf
 * 借款人进件信息
 */
public class BorrowerTransactionVo extends Entity{
	private static final long serialVersionUID = -7813600792823698060L;
	
	private Long transactionId;		//进件ID
	private String systemSourceName;//系统来源                                                           
	private Long bsApplyId;			//进件ID                                                             
	private String productType;		//贷款产品                                                           
	private String repaymentMode;	//还款方式                                                           
	private String isCompliance;	//是否合规。0[否],1[是]                                              
	private String moneySource;		//还款资金来源 字典1.独立还款2.家人协助还款3.其他方式                
	private String otherMoneySource;//还款资金来源,其他备注                                              
	private Long loanCategory;		//借款类型                                                           
	private Integer loanPlan;		//借款方案                                                           
	private Short applyInstalments;	//申请期限 
	private Short applyPeriods;		//申请期数                                                           
	private BigDecimal rate;		//适用利率                                                           
	private BigDecimal firstPaymentAmount;//首付金额                                                           
	private BigDecimal overAmount;	//申请金额                                                           
	private BigDecimal loanAmount;	//借款总额                                                           
	private BigDecimal guarantAmount;//履约保证金                                                         
	private BigDecimal chargeRate;	//服务费率                                                           
	private BigDecimal yearTariffing;//年利率                                                             
	private BigDecimal monthTariffing;//月管理费率                                                         
	private BigDecimal actualRate;	//实际费率                                                           
	private String name;	//姓名
	private String cardNo;	//证件号码                                                            
	private String cardType;//1 身份证, 2 学生证, 3 工作证、 4 士兵证、 5 军官证、 6 护照 7 户口本
	private BigDecimal fee;	//手续费                                                              
	private String pledgeProduct;		//个人房屋抵押贷款产品                                                
	private Integer shortRepaymentTerm;	//申请最短还款期限                                                    
	private Integer longRepaymentTerm;	//最长还款期限                                                        
	private BigDecimal monthRepayAmount;//可接受月还款额                                                      
	private BigDecimal applyMaxAmount;	//申请额度最大                                                        
	private BigDecimal applyMinAmount;	//申请额度最小                                                        
	private Long loanCount;				//第几笔贷款                                                          
	private BigDecimal acceptMothRepay;	//最高月还款                                                          
	private String customSource;		//客户来源一级                                                        
	private String customSource2;		//客户来源二级                                                        
	private String customSourceRemark;	//客户来源input                                                       
	private String consultant;			//咨询人                                                              
	private Long querySeqId;			//人行报告代查批次                                                    
	private String hasSocialInsurance;	//是否具有社保                                                        
	private String isReported;		//已经查过信用报告了。0[否], 1[是]                                    
	private String loanPurposeOne;	//贷款目的一级                                                        
	private String loanPurposeTwo;	//贷款目的二级                                                        
	private String loanPurposeDesc;	//借款用途备注1                                                       
	private String purposeDetail;	//借款用途详细1                                                       
	private String purposeDatal2;	//借款用途详细2                                                       
	private String purposeDetailRemark;//借款用途详细备注                                                    
	private String isGuarant;		//可以为此笔借款提供担保                                              
	private String purposeRemark1;	//借款用途备注                                                        
	private String id5Verify;		//1 是  2否                                                           
	private String courtVerify;		//1 是  2否                                                           
	private Short amount;			//数量                                                                
	private BigDecimal unitPrice;	//单价（元）                                                          
	private BigDecimal totalAmount;	//总额                                                                
	private String certDocument;	//证明文档                                                            
	private String isInterest;		//是否为付息通                                                        
	private String isNoPay;			//是否有未结清的借款
	private String isSupportGuarantee;//是否有为其他人的贷款提供担保                                        
	private String isLawSuit;		//是否涉及到诉讼                                                      
	private String isTrust;			//是否信托账户                                                        
	private String inspectionResult;//是否有条件通过                                                      
	private String passCondition;	//是否有条件通过备注                                                  
	private Date destineTime;		//信审初定时间                                                        
	private String memo;			//注释(信审推送)                                                      
	private String isHasOldName;	//本人或配偶是否有“曾用名”                                            
	private String isHasTogetherBorrower;//是否有共同借款人
	private Long togetherBorrowerId;//共同借款人ID  
	private String loanType1;		//是否循环贷    
	private String state;			//进件状态  0:等待签订合同；1:合同签订中；2:客户放弃；3:等待经理面谈结果；97:等待原件初审;
	private String processNode;		//审核的最新状态
	private String remark;			//备注          
	private String serviceRemark;	//客服备注      
	private String creditCheckRemark;//信审备注      
	private String surveryRemark;	//上门备注      
	private String supplementExplain;//补充资料说明  
	private Date fillFormDate;		//申请人填表日期
	private Date transportDate;		//申请时间      
	private String isExpress;		//是否加急      
	private Date createTime;		//新增日期      
	private Date operDate;			//最近更新日期  
	private Long creater;			//创建人        
	private Long updaterPeople;		//更新人
	
	private String uuid;				//UUID
	private String contractPrefix;		//合同前缀
	private String productFirstType;	//产品大类
	private String productVersionCode;	//产品版本编号
	private String discountWay;			//打折方式
	private BigDecimal discountRate;	//折扣率
	private String productVersion;		//产品版本号
	
	private BorrowerMarketVo market;	//借款人营销信息
	private List<SpecialApply> specialApplyList;//特殊情况申请
	private String businessCode;		//农贷业务单号
	


	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public List<SpecialApply> getSpecialApplyList() {
		return specialApplyList;
	}

	public void setSpecialApplyList(List<SpecialApply> specialApplyList) {
		this.specialApplyList = specialApplyList;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getSystemSourceName() {
		return systemSourceName;
	}

	public void setSystemSourceName(String systemSourceName) {
		this.systemSourceName = systemSourceName;
	}

	public Long getBsApplyId() {
		return bsApplyId;
	}

	public void setBsApplyId(Long bsApplyId) {
		this.bsApplyId = bsApplyId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public String getIsCompliance() {
		return isCompliance;
	}

	public void setIsCompliance(String isCompliance) {
		this.isCompliance = isCompliance;
	}

	public String getMoneySource() {
		return moneySource;
	}

	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}

	public String getOtherMoneySource() {
		return otherMoneySource;
	}

	public void setOtherMoneySource(String otherMoneySource) {
		this.otherMoneySource = otherMoneySource;
	}

	public Long getLoanCategory() {
		return loanCategory;
	}

	public void setLoanCategory(Long loanCategory) {
		this.loanCategory = loanCategory;
	}

	public Integer getLoanPlan() {
		return loanPlan;
	}

	public void setLoanPlan(Integer loanPlan) {
		this.loanPlan = loanPlan;
	}

	public Short getApplyInstalments() {
		return applyInstalments;
	}

	public void setApplyInstalments(Short applyInstalments) {
		this.applyInstalments = applyInstalments;
	}

	public Short getApplyPeriods() {
		return applyPeriods;
	}

	public void setApplyPeriods(Short applyPeriods) {
		this.applyPeriods = applyPeriods;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getFirstPaymentAmount() {
		return firstPaymentAmount;
	}

	public void setFirstPaymentAmount(BigDecimal firstPaymentAmount) {
		this.firstPaymentAmount = firstPaymentAmount;
	}

	public BigDecimal getOverAmount() {
		return overAmount;
	}

	public void setOverAmount(BigDecimal overAmount) {
		this.overAmount = overAmount;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getGuarantAmount() {
		return guarantAmount;
	}

	public void setGuarantAmount(BigDecimal guarantAmount) {
		this.guarantAmount = guarantAmount;
	}

	public BigDecimal getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
	}

	public BigDecimal getYearTariffing() {
		return yearTariffing;
	}

	public void setYearTariffing(BigDecimal yearTariffing) {
		this.yearTariffing = yearTariffing;
	}

	public BigDecimal getMonthTariffing() {
		return monthTariffing;
	}

	public void setMonthTariffing(BigDecimal monthTariffing) {
		this.monthTariffing = monthTariffing;
	}

	public BigDecimal getActualRate() {
		return actualRate;
	}

	public void setActualRate(BigDecimal actualRate) {
		this.actualRate = actualRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getPledgeProduct() {
		return pledgeProduct;
	}

	public void setPledgeProduct(String pledgeProduct) {
		this.pledgeProduct = pledgeProduct;
	}

	public Integer getShortRepaymentTerm() {
		return shortRepaymentTerm;
	}

	public void setShortRepaymentTerm(Integer shortRepaymentTerm) {
		this.shortRepaymentTerm = shortRepaymentTerm;
	}

	public Integer getLongRepaymentTerm() {
		return longRepaymentTerm;
	}

	public void setLongRepaymentTerm(Integer longRepaymentTerm) {
		this.longRepaymentTerm = longRepaymentTerm;
	}

	public BigDecimal getMonthRepayAmount() {
		return monthRepayAmount;
	}

	public void setMonthRepayAmount(BigDecimal monthRepayAmount) {
		this.monthRepayAmount = monthRepayAmount;
	}

	public BigDecimal getApplyMaxAmount() {
		return applyMaxAmount;
	}

	public void setApplyMaxAmount(BigDecimal applyMaxAmount) {
		this.applyMaxAmount = applyMaxAmount;
	}

	public BigDecimal getApplyMinAmount() {
		return applyMinAmount;
	}

	public void setApplyMinAmount(BigDecimal applyMinAmount) {
		this.applyMinAmount = applyMinAmount;
	}

	public Long getLoanCount() {
		return loanCount;
	}

	public void setLoanCount(Long loanCount) {
		this.loanCount = loanCount;
	}

	public BigDecimal getAcceptMothRepay() {
		return acceptMothRepay;
	}

	public void setAcceptMothRepay(BigDecimal acceptMothRepay) {
		this.acceptMothRepay = acceptMothRepay;
	}

	public String getCustomSource() {
		return customSource;
	}

	public void setCustomSource(String customSource) {
		this.customSource = customSource;
	}

	public String getCustomSource2() {
		return customSource2;
	}

	public void setCustomSource2(String customSource2) {
		this.customSource2 = customSource2;
	}

	public String getCustomSourceRemark() {
		return customSourceRemark;
	}

	public void setCustomSourceRemark(String customSourceRemark) {
		this.customSourceRemark = customSourceRemark;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public Long getQuerySeqId() {
		return querySeqId;
	}

	public void setQuerySeqId(Long querySeqId) {
		this.querySeqId = querySeqId;
	}

	public String getHasSocialInsurance() {
		return hasSocialInsurance;
	}

	public void setHasSocialInsurance(String hasSocialInsurance) {
		this.hasSocialInsurance = hasSocialInsurance;
	}

	public String getIsReported() {
		return isReported;
	}

	public void setIsReported(String isReported) {
		this.isReported = isReported;
	}

	public String getLoanPurposeOne() {
		return loanPurposeOne;
	}

	public void setLoanPurposeOne(String loanPurposeOne) {
		this.loanPurposeOne = loanPurposeOne;
	}

	public String getLoanPurposeTwo() {
		return loanPurposeTwo;
	}

	public void setLoanPurposeTwo(String loanPurposeTwo) {
		this.loanPurposeTwo = loanPurposeTwo;
	}

	public String getLoanPurposeDesc() {
		return loanPurposeDesc;
	}

	public void setLoanPurposeDesc(String loanPurposeDesc) {
		this.loanPurposeDesc = loanPurposeDesc;
	}

	public String getPurposeDetail() {
		return purposeDetail;
	}

	public void setPurposeDetail(String purposeDetail) {
		this.purposeDetail = purposeDetail;
	}

	public String getPurposeDatal2() {
		return purposeDatal2;
	}

	public void setPurposeDatal2(String purposeDatal2) {
		this.purposeDatal2 = purposeDatal2;
	}

	public String getPurposeDetailRemark() {
		return purposeDetailRemark;
	}

	public void setPurposeDetailRemark(String purposeDetailRemark) {
		this.purposeDetailRemark = purposeDetailRemark;
	}

	public String getIsGuarant() {
		return isGuarant;
	}

	public void setIsGuarant(String isGuarant) {
		this.isGuarant = isGuarant;
	}

	public String getPurposeRemark1() {
		return purposeRemark1;
	}

	public void setPurposeRemark1(String purposeRemark1) {
		this.purposeRemark1 = purposeRemark1;
	}

	public String getId5Verify() {
		return id5Verify;
	}

	public void setId5Verify(String id5Verify) {
		this.id5Verify = id5Verify;
	}

	public String getCourtVerify() {
		return courtVerify;
	}

	public void setCourtVerify(String courtVerify) {
		this.courtVerify = courtVerify;
	}

	public Short getAmount() {
		return amount;
	}

	public void setAmount(Short amount) {
		this.amount = amount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCertDocument() {
		return certDocument;
	}

	public void setCertDocument(String certDocument) {
		this.certDocument = certDocument;
	}

	public String getIsInterest() {
		return isInterest;
	}

	public void setIsInterest(String isInterest) {
		this.isInterest = isInterest;
	}

	public String getIsNoPay() {
		return isNoPay;
	}

	public void setIsNoPay(String isNoPay) {
		this.isNoPay = isNoPay;
	}

	public String getIsSupportGuarantee() {
		return isSupportGuarantee;
	}

	public void setIsSupportGuarantee(String isSupportGuarantee) {
		this.isSupportGuarantee = isSupportGuarantee;
	}

	public String getIsLawSuit() {
		return isLawSuit;
	}

	public void setIsLawSuit(String isLawSuit) {
		this.isLawSuit = isLawSuit;
	}

	public String getIsTrust() {
		return isTrust;
	}

	public void setIsTrust(String isTrust) {
		this.isTrust = isTrust;
	}

	public String getInspectionResult() {
		return inspectionResult;
	}

	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}

	public String getPassCondition() {
		return passCondition;
	}

	public void setPassCondition(String passCondition) {
		this.passCondition = passCondition;
	}

	public Date getDestineTime() {
		return destineTime;
	}

	public void setDestineTime(Date destineTime) {
		this.destineTime = destineTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsHasOldName() {
		return isHasOldName;
	}

	public void setIsHasOldName(String isHasOldName) {
		this.isHasOldName = isHasOldName;
	}

	public String getIsHasTogetherBorrower() {
		return isHasTogetherBorrower;
	}

	public void setIsHasTogetherBorrower(String isHasTogetherBorrower) {
		this.isHasTogetherBorrower = isHasTogetherBorrower;
	}

	public Long getTogetherBorrowerId() {
		return togetherBorrowerId;
	}

	public void setTogetherBorrowerId(Long togetherBorrowerId) {
		this.togetherBorrowerId = togetherBorrowerId;
	}

	public String getLoanType1() {
		return loanType1;
	}

	public void setLoanType1(String loanType1) {
		this.loanType1 = loanType1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProcessNode() {
		return processNode;
	}

	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServiceRemark() {
		return serviceRemark;
	}

	public void setServiceRemark(String serviceRemark) {
		this.serviceRemark = serviceRemark;
	}

	public String getCreditCheckRemark() {
		return creditCheckRemark;
	}

	public void setCreditCheckRemark(String creditCheckRemark) {
		this.creditCheckRemark = creditCheckRemark;
	}

	public String getSurveryRemark() {
		return surveryRemark;
	}

	public void setSurveryRemark(String surveryRemark) {
		this.surveryRemark = surveryRemark;
	}

	public String getSupplementExplain() {
		return supplementExplain;
	}

	public void setSupplementExplain(String supplementExplain) {
		this.supplementExplain = supplementExplain;
	}

	public Date getFillFormDate() {
		return fillFormDate;
	}

	public void setFillFormDate(Date fillFormDate) {
		this.fillFormDate = fillFormDate;
	}

	public Date getTransportDate() {
		return transportDate;
	}

	public void setTransportDate(Date transportDate) {
		this.transportDate = transportDate;
	}

	public String getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(String isExpress) {
		this.isExpress = isExpress;
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

	public BorrowerMarketVo getMarket() {
		return market;
	}

	public void setMarket(BorrowerMarketVo market) {
		this.market = market;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContractPrefix() {
		return contractPrefix;
	}

	public void setContractPrefix(String contractPrefix) {
		this.contractPrefix = contractPrefix;
	}

	public String getProductFirstType() {
		return productFirstType;
	}

	public void setProductFirstType(String productFirstType) {
		this.productFirstType = productFirstType;
	}

	public String getProductVersionCode() {
		return productVersionCode;
	}

	public void setProductVersionCode(String productVersionCode) {
		this.productVersionCode = productVersionCode;
	}

	public String getDiscountWay() {
		return discountWay;
	}

	public void setDiscountWay(String discountWay) {
		this.discountWay = discountWay;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}
}
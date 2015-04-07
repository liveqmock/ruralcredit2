package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mw
 * 2014-07-01
 * 申请表
 */
public class Apply extends Entity
{
	
	private static final long serialVersionUID = -45232101253218215L;

	private Integer applyId;// 借款编号

	private Integer clientId;// 客户id

	private Integer isSubmit;//是否已提交（1是）

	private BigDecimal intloanIntent1;// 贷款目的一级

	private BigDecimal intloanIntent2;// 贷款目的二级

	private String intloanIntentInput;// 贷款目的输入

	private BigDecimal strcreditSum;// 申请授信额度（最高）

	private BigDecimal strableRepayment;// 可接受最高月还款额

	private Integer intapplyNo;// 申请还款期限

	private String strclientName;// 客户姓名

	private String intcType;// 证件类型

	private String intidNumber;// 证件号码

	private String strDescription;// 申请表备注

	private String intisurgent;// 是否加急

	private Integer intloanzType;// 贷款类型

	private BigDecimal intclientSource1;// 客户来源一级

	private Integer intclientSource2;// 客户来源二级

	private String strclientSource;// 客户来源input

	private String strconsultation;// 咨询人

	private Date operateTime;//操作时间

	private Integer operator;//操作人
	
	private String operateName;//操作人姓名

	private Date createTime;//创建时间

	private Integer creator;//创建人
	
	private String createName;//创建人姓名

	private String intisTogether;// 是否是共同贷款(1是)

	private String intiscyc;// 是否是循环贷

	private Integer intpesu;// 第几笔贷款

	private Integer strstatus;// 贷款状态

	private String strremark;// remark

	private String strexplain;// 补充资料说明

	private BigDecimal strcreditLowest;// 申请授信额度（最低）

	private String intisverzinsen;// 是否为付息通

	private String strsaleName;// 团队经理姓名

	private String consultationCityName;// 所属城市

	private String consultationDepartName;// 所属部门

	private String consultationAreaName;// 所属区域

	private Integer userId;// 咨询id
	
	private Integer intapplyShortno;//申请最短还款期限(现在已经不用)
	
	private Integer saleEmpId;// 团队经理Id

	private Integer consultation_city_id;// 所属城市id

	private Integer consultation_depart_id;// 所属部门id

	private Integer consultation_area_id;// 所属区域id

	private String realSaleName;// 销售人员

	private Integer realSaleId; // 销售人员id
	
	private Integer istrustaccount;//是否是信托账户
	
	private Integer trainId;// 培训机构编号
	
	private String trainName;//培训机构名称
	
	private SmoccApply smoccApply;//企合申请信息
	//培训类型
	private Integer trainType;
	
	public Integer getTrainType() {
		return trainType;
	}

	public void setTrainType(Integer trainType) {
		this.trainType = trainType;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getSaleEmpId() {
		return saleEmpId;
	}

	public void setSaleEmpId(Integer saleEmpId) {
		this.saleEmpId = saleEmpId;
	}

	public Integer getIstrustaccount() {
		return istrustaccount;
	}

	public void setIstrustaccount(Integer istrustaccount) {
		this.istrustaccount = istrustaccount;
	}

	public Integer getApplyId()
	{
		return applyId;
	}

	public void setApplyId(Integer applyId)
	{
		this.applyId = applyId;
	}

	public Integer getClientId()
	{
		return clientId;
	}

	public void setClientId(Integer clientId)
	{
		this.clientId = clientId;
	}

	public Integer getIsSubmit()
	{
		return isSubmit;
	}

	public void setIsSubmit(Integer isSubmit)
	{
		this.isSubmit = isSubmit;
	}

	public BigDecimal getIntloanIntent1()
	{
		return intloanIntent1;
	}

	public void setIntloanIntent1(BigDecimal intloanIntent1)
	{
		this.intloanIntent1 = intloanIntent1;
	}

	public BigDecimal getIntloanIntent2()
	{
		return intloanIntent2;
	}

	public void setIntloanIntent2(BigDecimal intloanIntent2)
	{
		this.intloanIntent2 = intloanIntent2;
	}

	public String getIntloanIntentInput()
	{
		return intloanIntentInput;
	}

	public void setIntloanIntentInput(String intloanIntentInput)
	{
		this.intloanIntentInput = intloanIntentInput;
	}

	public BigDecimal getStrcreditSum()
	{
		return strcreditSum;
	}

	public void setStrcreditSum(BigDecimal strcreditSum)
	{
		this.strcreditSum = strcreditSum;
	}

	public BigDecimal getStrableRepayment()
	{
		return strableRepayment;
	}

	public void setStrableRepayment(BigDecimal strableRepayment)
	{
		this.strableRepayment = strableRepayment;
	}

	public Integer getIntapplyNo()
	{
		return intapplyNo;
	}

	public void setIntapplyNo(Integer intapplyNo)
	{
		this.intapplyNo = intapplyNo;
	}

	public String getStrclientName()
	{
		return strclientName;
	}

	public void setStrclientName(String strclientName)
	{
		this.strclientName = strclientName;
	}


	public String getIntcType()
	{
		return intcType;
	}

	public void setIntcType(String intcType)
	{
		this.intcType = intcType;
	}

	public String getIntidNumber()
	{
		return intidNumber;
	}

	public void setIntidNumber(String intidNumber)
	{
		this.intidNumber = intidNumber;
	}

	

	public String getStrDescription()
	{
		return strDescription;
	}

	public void setStrDescription(String strDescription)
	{
		this.strDescription = strDescription;
	}


	public String getIntisurgent()
	{
		return intisurgent;
	}

	public void setIntisurgent(String intisurgent)
	{
		this.intisurgent = intisurgent;
	}

	public Integer getIntloanzType()
	{
		return intloanzType;
	}

	public void setIntloanzType(Integer intloanzType)
	{
		this.intloanzType = intloanzType;
	}

	public BigDecimal getIntclientSource1()
	{
		return intclientSource1;
	}

	public void setIntclientSource1(BigDecimal intclientSource1)
	{
		this.intclientSource1 = intclientSource1;
	}

	public Integer getIntclientSource2()
	{
		return intclientSource2;
	}

	public void setIntclientSource2(Integer intclientSource2)
	{
		this.intclientSource2 = intclientSource2;
	}

	public String getStrclientSource()
	{
		return strclientSource;
	}

	public void setStrclientSource(String strclientSource)
	{
		this.strclientSource = strclientSource;
	}


	public String getStrconsultation()
	{
		return strconsultation;
	}

	public void setStrconsultation(String strconsultation)
	{
		this.strconsultation = strconsultation;
	}

	public Date getOperateTime()
	{
		return operateTime;
	}

	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}

	public Integer getOperator()
	{
		return operator;
	}

	public void setOperator(Integer operator)
	{
		this.operator = operator;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Integer getCreator()
	{
		return creator;
	}

	public void setCreator(Integer creator)
	{
		this.creator = creator;
	}

	public String getIntisTogether()
	{
		return intisTogether;
	}

	public void setIntisTogether(String intisTogether)
	{
		this.intisTogether = intisTogether;
	}

	public String getIntiscyc()
	{
		return intiscyc;
	}

	public void setIntiscyc(String intiscyc)
	{
		this.intiscyc = intiscyc;
	}

	public Integer getIntpesu()
	{
		return intpesu;
	}

	public void setIntpesu(Integer intpesu)
	{
		this.intpesu = intpesu;
	}

	public Integer getStrstatus()
	{
		return strstatus;
	}

	public void setStrstatus(Integer strstatus)
	{
		this.strstatus = strstatus;
	}

	public String getStrremark()
	{
		return strremark;
	}

	public void setStrremark(String strremark)
	{
		this.strremark = strremark;
	}


	public String getStrexplain()
	{
		return strexplain;
	}

	public void setStrexplain(String strexplain)
	{
		this.strexplain = strexplain;
	}

	public BigDecimal getStrcreditLowest()
	{
		return strcreditLowest;
	}

	public void setStrcreditLowest(BigDecimal strcreditLowest)
	{
		this.strcreditLowest = strcreditLowest;
	}

	public String getIntisverzinsen()
	{
		return intisverzinsen;
	}

	public void setIntisverzinsen(String intisverzinsen)
	{
		this.intisverzinsen = intisverzinsen;
	}

	public String getStrsaleName()
	{
		return strsaleName;
	}

	public void setStrsaleName(String strsaleName)
	{
		this.strsaleName = strsaleName;
	}

	public String getConsultationCityName()
	{
		return consultationCityName;
	}

	public void setConsultationCityName(String consultationCityName)
	{
		this.consultationCityName = consultationCityName;
	}

	public String getConsultationDepartName()
	{
		return consultationDepartName;
	}

	public void setConsultationDepartName(String consultationDepartName)
	{
		this.consultationDepartName = consultationDepartName;
	}

	public String getConsultationAreaName()
	{
		return consultationAreaName;
	}

	public void setConsultationAreaName(String consultationAreaName)
	{
		this.consultationAreaName = consultationAreaName;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Integer getConsultation_city_id()
	{
		return consultation_city_id;
	}

	public void setConsultation_city_id(Integer consultation_city_id)
	{
		this.consultation_city_id = consultation_city_id;
	}

	public Integer getConsultation_depart_id()
	{
		return consultation_depart_id;
	}

	public void setConsultation_depart_id(Integer consultation_depart_id)
	{
		this.consultation_depart_id = consultation_depart_id;
	}

	public Integer getConsultation_area_id()
	{
		return consultation_area_id;
	}

	public void setConsultation_area_id(Integer consultation_area_id)
	{
		this.consultation_area_id = consultation_area_id;
	}

	public String getRealSaleName()
	{
		return realSaleName;
	}

	public void setRealSaleName(String realSaleName)
	{
		this.realSaleName = realSaleName;
	}

	public Integer getRealSaleId()
	{
		return realSaleId;
	}

	public void setRealSaleId(Integer realSaleId)
	{
		this.realSaleId = realSaleId;
	}

	public Integer getIntapplyShortno() {
		return intapplyShortno;
	}

	public void setIntapplyShortno(Integer intapplyShortno) {
		this.intapplyShortno = intapplyShortno;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public SmoccApply getSmoccApply() {
		return smoccApply;
	}

	public void setSmoccApply(SmoccApply smoccApply) {
		this.smoccApply = smoccApply;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
}

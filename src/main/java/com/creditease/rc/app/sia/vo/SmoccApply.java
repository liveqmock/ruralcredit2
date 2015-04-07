package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author mw
 * 2014-07-01
 */
public class SmoccApply extends Entity
{
	/**
	 * smocc申请信息
	 */
	private static final long serialVersionUID = 8269405929531017703L;
	
	private Integer id;//企合申请信息Id

	private Integer applyId;// 借款编号
	
	private Integer loanPlans;//借款方案
	
	private String brandModel;//品牌型号（乐购汽车分期）

	private String course;//培训课程

	private BigDecimal tuition;//培训总费用/车价款
	
	private BigDecimal payed;//目前已支付/首付
	
	private Integer period;//培训期长
	
	private Date startTime;//开课时间
	
	private String moneySource;//还款资金来源  1独立还款、2家人协助还款3其它\还款方式  
	
	private String otherMoneySource;//还款资金来源,其他备注
	
	private Integer trainId;// 合作商家Id

	private String trainName;//合作商家名称
	
	private String trainCode;//合作商家编码
	
	private Integer outletId;//合作门店Id
	
	private String outletName;//合作门店名称
	
	private String outletCode;//合作门店编码

	private String trainTeacher;//咨询老师
	
	private String trainTeacherPhone;//咨询老师电话
	
	private String trainCampus;//培训校区
	
	private Integer repaymentMode;//还款方式    等额本金及手续费 到期一次性还本   月等额手续费
	
	private  Integer  trainType;
	private  Integer  employment;
	private  String   iscus;
	
	public Integer getTrainType() {
		return trainType;
	}

	public void setTrainType(Integer trainType) {
		this.trainType = trainType;
	}

	public Integer getEmployment() {
		return employment;
	}

	public void setEmployment(Integer employment) {
		this.employment = employment;
	}

	public String getIscus() {
		return iscus;
	}

	public void setIscus(String iscus) {
		this.iscus = iscus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public BigDecimal getTuition() {
		return tuition;
	}

	public void setTuition(BigDecimal tuition) {
		this.tuition = tuition;
	}

	public BigDecimal getPayed() {
		return payed;
	}

	public void setPayed(BigDecimal payed) {
		this.payed = payed;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public String getTrainTeacher() {
		return trainTeacher;
	}

	public void setTrainTeacher(String trainTeacher) {
		this.trainTeacher = trainTeacher;
	}

	public String getTrainTeacherPhone() {
		return trainTeacherPhone;
	}

	public void setTrainTeacherPhone(String trainTeacherPhone) {
		this.trainTeacherPhone = trainTeacherPhone;
	}

	public String getTrainCampus() {
		return trainCampus;
	}

	public void setTrainCampus(String trainCampus) {
		this.trainCampus = trainCampus;
	}

	public Integer getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(Integer repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public Integer getLoanPlans() {
		return loanPlans;
	}

	public void setLoanPlans(Integer loanPlans) {
		this.loanPlans = loanPlans;
	}

	public Integer getOutletId() {
		return outletId;
	}

	public void setOutletId(Integer outletId) {
		this.outletId = outletId;
	}

	public String getOutletName() {
		return outletName;
	}

	public void setOutletName(String outletName) {
		this.outletName = outletName;
	}

	public String getOutletCode() {
		return outletCode;
	}

	public void setOutletCode(String outletCode) {
		this.outletCode = outletCode;
	}

	public String getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}


	
}

package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

public class CustomerConsultPool {
    private Long consultPoolId;//zw 主键ID
    private Long customerConsultId;
    private String customerName;//zw 客户姓名
    private String conSex;//zw 性别
    private Long conAge;//zw 年龄
    private String province;//zw 省代码
    private String city;//zw 市代码
    private String area;//zw 区代码
    private String mobilePhone;//zw 联系方式
    private String fixedTelephone;
    private String businessPeriod;
    private String isBusinessLicense;
    private BigDecimal borrowAmount;
    private String channel;//zw 信息来源
    private String marketConsultRemark;//zw 备注
    private Date registDate;//zw 咨询日期
    private Date distributionDate; //分配日期
    private String marketConsultState;//zw 咨询状态 如：待分配、无营业网点等等
    private String feedbackResult;
    private String distributeDepartmentId;//zw 分配营业部id
    private String distributeDepartmentName;//zw 分配营业部name
    private String marketConsultRegistType;//zw 登记方式 (0 手工登记 1 批量导入)
    private Date receiveDate;	//受理日期
    private Date sentDate;		//分件日期
    private String acceptConsultState;   //受理咨询状态
    private String acceptConsultRemark;
    private String communicationRecord;
    private String loanOfficerName;
    private Long loanOfficerId;
    private Date feedbackDate;
    private Date operateDate;
    private String operator;

    private Date createDate;//zw 创建时间
    private String creater;//zw 创建人
    private String departmentId;
    private String departmentName;
    private String areaDepartmentId;
    private String areaDepartmentName;
    private String cityDepartmentId;
    private String cityDepartmentName;
    private String salesDepartmentManagerId;
    private String salesDepartmentManagerName;
    private String businessStatus;
    private String applicationStatus;
    private String businessStatusText;
    private String borrowing;//zw 借款用途
    private String consultAmount;//zw 借款额度
    private String channelText; //zw 信息来源文本显示
    private String residenceAddress;//zw 户籍地址
    private String consultWay;//zw 咨询方式
    private String consultMoney;//zw 咨询金额
    private String invalidRegStatus;//zw 无效登记状态
    private String customerTag;//zw 客户标签
    private String distributeOperationFlag;//zw 分配操作权限标识
    private String componentOperationFlag; // hq 分件操作权限标识

   
    private String traceStatus;//gyh 跟踪状态
    private String customerTagName;
    private String borrowUse;

    public Long getConsultPoolId() {
        return consultPoolId;
    }

    public void setConsultPoolId(Long consultPoolId) {
        this.consultPoolId = consultPoolId;
    }

    public Long getCustomerConsultId() {
        return customerConsultId;
    }

    public void setCustomerConsultId(Long customerConsultId) {
        this.customerConsultId = customerConsultId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getConSex() {
        return conSex;
    }

    public void setConSex(String conSex) {
        this.conSex = conSex;
    }

    public Long getConAge() {
        return conAge;
    }

    public void setConAge(Long conAge) {
        this.conAge = conAge;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFixedTelephone() {
        return fixedTelephone;
    }

    public void setFixedTelephone(String fixedTelephone) {
        this.fixedTelephone = fixedTelephone;
    }

    public String getBusinessPeriod() {
        return businessPeriod;
    }

    public void setBusinessPeriod(String businessPeriod) {
        this.businessPeriod = businessPeriod;
    }

    public String getIsBusinessLicense() {
        return isBusinessLicense;
    }

    public void setIsBusinessLicense(String isBusinessLicense) {
        this.isBusinessLicense = isBusinessLicense;
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMarketConsultRemark() {
        return marketConsultRemark;
    }

    public void setMarketConsultRemark(String marketConsultRemark) {
        this.marketConsultRemark = marketConsultRemark;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(Date distributionDate) {
        this.distributionDate = distributionDate;
    }

    public String getMarketConsultState() {
        return marketConsultState;
    }

    public void setMarketConsultState(String marketConsultState) {
        this.marketConsultState = marketConsultState;
    }

    public String getFeedbackResult() {
        return feedbackResult;
    }

    public void setFeedbackResult(String feedbackResult) {
        this.feedbackResult = feedbackResult;
    }

    public String getDistributeDepartmentId() {
        return distributeDepartmentId;
    }

    public void setDistributeDepartmentId(String distributeDepartmentId) {
        this.distributeDepartmentId = distributeDepartmentId;
    }

    public String getDistributeDepartmentName() {
        return distributeDepartmentName;
    }

    public void setDistributeDepartmentName(String distributeDepartmentName) {
        this.distributeDepartmentName = distributeDepartmentName;
    }

    public String getMarketConsultRegistType() {
        return marketConsultRegistType;
    }

    public void setMarketConsultRegistType(String marketConsultRegistType) {
        this.marketConsultRegistType = marketConsultRegistType;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getAcceptConsultState() {
        return acceptConsultState;
    }

    public void setAcceptConsultState(String acceptConsultState) {
        this.acceptConsultState = acceptConsultState;
    }

    public String getAcceptConsultRemark() {
        return acceptConsultRemark;
    }

    public void setAcceptConsultRemark(String acceptConsultRemark) {
        this.acceptConsultRemark = acceptConsultRemark;
    }

    public String getCommunicationRecord() {
        return communicationRecord;
    }

    public void setCommunicationRecord(String communicationRecord) {
        this.communicationRecord = communicationRecord;
    }

    public String getLoanOfficerName() {
        return loanOfficerName;
    }

    public void setLoanOfficerName(String loanOfficerName) {
        this.loanOfficerName = loanOfficerName;
    }

    public Long getLoanOfficerId() {
        return loanOfficerId;
    }

    public void setLoanOfficerId(Long loanOfficerId) {
        this.loanOfficerId = loanOfficerId;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAreaDepartmentId() {
        return areaDepartmentId;
    }

    public void setAreaDepartmentId(String areaDepartmentId) {
        this.areaDepartmentId = areaDepartmentId;
    }

    public String getAreaDepartmentName() {
        return areaDepartmentName;
    }

    public void setAreaDepartmentName(String areaDepartmentName) {
        this.areaDepartmentName = areaDepartmentName;
    }

    public String getCityDepartmentId() {
        return cityDepartmentId;
    }

    public void setCityDepartmentId(String cityDepartmentId) {
        this.cityDepartmentId = cityDepartmentId;
    }

    public String getCityDepartmentName() {
        return cityDepartmentName;
    }

    public void setCityDepartmentName(String cityDepartmentName) {
        this.cityDepartmentName = cityDepartmentName;
    }

    public String getSalesDepartmentManagerId() {
        return salesDepartmentManagerId;
    }

    public void setSalesDepartmentManagerId(String salesDepartmentManagerId) {
        this.salesDepartmentManagerId = salesDepartmentManagerId;
    }

    public String getSalesDepartmentManagerName() {
        return salesDepartmentManagerName;
    }

    public void setSalesDepartmentManagerName(String salesDepartmentManagerName) {
        this.salesDepartmentManagerName = salesDepartmentManagerName;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getBusinessStatusText() {
        return businessStatusText;
    }

    public void setBusinessStatusText(String businessStatusText) {
        this.businessStatusText = businessStatusText;
    }

    public String getBusinessLicense() {
        return isBusinessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        isBusinessLicense = businessLicense;
    }

    public String getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(String borrowing) {
        this.borrowing = borrowing;
    }

    public String getConsultAmount() {
        return consultAmount;
    }

    public void setConsultAmount(String consultAmount) {
        this.consultAmount = consultAmount;
    }

    public String getChannelText() {
        return channelText;
    }

    public void setChannelText(String channelText) {
        this.channelText = channelText;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getConsultWay() {
        return consultWay;
    }

    public void setConsultWay(String consultWay) {
        this.consultWay = consultWay;
    }
    public String getTraceStatus() {
        return traceStatus;
    }

    public void setTraceStatus(String traceStatus) {
        this.traceStatus = traceStatus;
    }

    public String getConsultMoney() {
        return consultMoney;
    }

    public void setConsultMoney(String consultMoney) {
        this.consultMoney = consultMoney;
    }

    public String getInvalidRegStatus() {
        return invalidRegStatus;
    }

    public void setInvalidRegStatus(String invalidRegStatus) {
        this.invalidRegStatus = invalidRegStatus;
    }

    public String getCustomerTag() {
        return customerTag;
    }

    public void setCustomerTag(String customerTag) {
        this.customerTag = customerTag;
    }

	public String getDistributeOperationFlag() {
		return distributeOperationFlag;
	}

	public void setDistributeOperationFlag(String distributeOperationFlag) {
		this.distributeOperationFlag = distributeOperationFlag;
	}

	public String getComponentOperationFlag() {
		return componentOperationFlag;
	}

	public void setComponentOperationFlag(String componentOperationFlag) {
		this.componentOperationFlag = componentOperationFlag;
	}

	public String getCustomerTagName() {
		return customerTagName;
	}

	public void setCustomerTagName(String customerTagName) {
		this.customerTagName = customerTagName;
	}

	public String getBorrowUse() {
		return borrowUse;
	}

	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}



}

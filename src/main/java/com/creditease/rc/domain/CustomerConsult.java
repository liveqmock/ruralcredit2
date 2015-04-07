package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;

/**
 * 
 * @author zhangman
 *
 */
public class CustomerConsult implements Serializable {
	private 	Long 	customerConsultId; //
	private 	String 	departmentName;		//部门
	private 	Date 	consultDate;		//咨询日期
	private 	String 	consultWay;			//咨询方式
	private 	String 	customerName;		//客户姓名
	private 	String 	address;			//地址
	private 	String 	telphone;			//电话
	private 	Double 	borrowAmount;		//借款金额
	private 	String 	borrowUse;			//借款用途
	private 	String 	infomationSource;	//信息来源
	private 	String 	receptionist;		//接待人
	private 	String 	processResult;		//客户标签
	private 	String 	processCauses;		//客户分类
	private 	String 	remark;				//备注
	private     String  optionFlag;			//操作标记
	private 	Date  operateTime = new Date();		//操作时间
	private 	String departmentId;				//分公司ID
	private 	String receptionistId;				//接待人ID
	
	private String history="0";		//历史标记（0 ： 非历史）		
	private String gender;			//性别
	private String  borrowUseDetail;//借款用途
	private String  consultWayDetail;//咨询方式
	private String borrowAmountDetail;//借款金额
	private String infomationSourceDetail;//信息来源
	private String processResultDetail;//	处理结果
	private String processCausesDetail;//处理原因
	private String genderDetail;//性别
	private String industryCategory;//行业
	private String industryCategoryDetail;//行业
	private Long count;    //历史计数				
	private String status; //借款状态(1 借款，0 没借款)
	private String auditStatusDetail;
	private String telphoneMd5; //加密的电话号码
	
	private String businessNumber;//业务单号
	
	private String createManager;//创建客户经理
	private String createManagerId;//创建客户经理Id

    private Date createDate;//首次创建时间

    private String baseInfSrcKey;//大类key
    private String baseInfSrcValue;//大类value
    private String subInfSrcKey;//子类key
    private String subInfSrcValue;//子类value

	public String getCreateManager() {
		return createManager;
	}
	public void setCreateManager(String createManager) {
		this.createManager = createManager;
	}
	public String getCreateManagerId() {
		return createManagerId;
	}
	public void setCreateManagerId(String createManagerId) {
		this.createManagerId = createManagerId;
	}
	public Long getCustomerConsultId() {
		return customerConsultId;
	}
	public void setCustomerConsultId(Long customerConsultId) {
		this.customerConsultId = customerConsultId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getConsultDate() {
		return consultDate;
	}
	public void setConsultDate(Date consultDate) {
		this.consultDate = consultDate;
	}
	public String getConsultWay() {
		return consultWay;
	}
	public void setConsultWay(String consultWay) {
		this.consultWay = consultWay;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Double getBorrowAmount() {
		return borrowAmount;
	}
	public void setBorrowAmount(Double borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	public String getBorrowUse() {
		return borrowUse;
	}
	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}
	public String getInfomationSource() {
		return infomationSource;
	}
	public void setInfomationSource(String infomationSource) {
		this.infomationSource = infomationSource;
	}
	public String getReceptionist() {
		return receptionist;
	}
	public void setReceptionist(String receptionist) {
		this.receptionist = receptionist;
	}
	public String getProcessResult() {
		return processResult;
	}
	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}
	public String getProcessCauses() {
		return processCauses;
	}
	public void setProcessCauses(String processCauses) {
		this.processCauses = processCauses;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOptionFlag() {
		return optionFlag;
	}
	public void setOptionFlag(String optionFlag) {
		this.optionFlag = optionFlag;
	}
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getReceptionistId() {
		return receptionistId;
	}
	public void setReceptionistId(String receptionistId) {
		this.receptionistId = receptionistId;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBorrowUseDetail() {
		return borrowUseDetail;
	}
	public void setBorrowUseDetail(String borrowUseDetail) {
		this.borrowUseDetail = borrowUseDetail;
	}
	public String getConsultWayDetail() {
		return consultWayDetail;
	}
	public void setConsultWayDetail(String consultWayDetail) {
		this.consultWayDetail = consultWayDetail;
	}
	public String getBorrowAmountDetail() {
		return borrowAmountDetail;
	}
	public void setBorrowAmountDetail(String borrowAmountDetail) {
		this.borrowAmountDetail = borrowAmountDetail;
	}
	public String getInfomationSourceDetail() {
		return infomationSourceDetail;
	}
	public void setInfomationSourceDetail(String infomationSourceDetail) {
		this.infomationSourceDetail = infomationSourceDetail;
	}
	public String getProcessResultDetail() {
		return processResultDetail;
	}
	public void setProcessResultDetail(String processResultDetail) {
		this.processResultDetail = processResultDetail;
	}
	public String getProcessCausesDetail() {
		return processCausesDetail;
	}
	public void setProcessCausesDetail(String processCausesDetail) {
		this.processCausesDetail = processCausesDetail;
	}
	public String getGenderDetail() {
		return genderDetail;
	}
	public void setGenderDetail(String genderDetail) {
		this.genderDetail = genderDetail;
	}
	public String getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}
	public String getIndustryCategoryDetail() {
		return industryCategoryDetail;
	}
	public void setIndustryCategoryDetail(String industryCategoryDetail) {
		this.industryCategoryDetail = industryCategoryDetail;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 
	 * @return  String
	 */
	public String getAuditStatusDetail() {
		if("1".equals(status)){
			return "已申请";
		}else{
			return "未申请";
		}
	}
	public void setAuditStatusDetail(String auditStatusDetail) {
		this.auditStatusDetail = auditStatusDetail;
	}
	public String getTelphoneMd5() {
		return telphoneMd5;
	}
	public void setTelphoneMd5(String telphoneMd5) {
		this.telphoneMd5 = telphoneMd5;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

    @JsonSerialize(using = JsonYMDHMDDateSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBaseInfSrcKey() {
        return baseInfSrcKey;
    }

    public void setBaseInfSrcKey(String baseInfSrcKey) {
        this.baseInfSrcKey = baseInfSrcKey;
    }

    public String getBaseInfSrcValue() {
        return baseInfSrcValue;
    }

    public void setBaseInfSrcValue(String baseInfSrcValue) {
        this.baseInfSrcValue = baseInfSrcValue;
    }

    public String getSubInfSrcValue() {
        return subInfSrcValue;
    }

    public void setSubInfSrcValue(String subInfSrcValue) {
        this.subInfSrcValue = subInfSrcValue;
    }

    public String getSubInfSrcKey() {
        return subInfSrcKey;
    }

    public void setSubInfSrcKey(String subInfSrcKey) {
        this.subInfSrcKey = subInfSrcKey;
    }
}

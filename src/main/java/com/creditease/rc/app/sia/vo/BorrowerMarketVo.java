package com.creditease.rc.app.sia.vo;

import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人营销信息
 */
public class BorrowerMarketVo extends Entity{
	private static final long serialVersionUID = -8515449928556626498L;
	
	private Long transactionId;		//进件ID		
	private String knowCreditease;	//如何了解宜信一级		
	private String knowCreditease2;	//如何了解宜信二级	
	private String saleChannel;		//销售渠道          
	private String saleChannelRemark;//销售渠道备注      
	private String customSource;	//客户来源		
	private Long saleCityId;		//销售城市ID   **(城市id)     
	private String saleCity;		//销售城市         
	private Long saleDepartmentId;//销售部门ID   **  （营业部id） 
	private String saleDepartment;	//销售部门          
	private Long saleAreaId;		//销售区域ID    
	private String saleArea;		//销售区域          
	private String submitDeptNo;	//进件单位    **    （营业部id）  
	private Long consultId;			//咨询ID            
	private Long teamManagerId;		//团队经理ID    
	private String teamManagerName;	//团队经理         
	private String salePhone;		//销售电话          
	private Long sellUserId;		//销售人员ID **       
	private String sellUserName;	//销售姓名          **
	private Long serviceUserId;		//服务人员ID **       
	private String serviceUserName;	//服务人员姓名     ** 
	private String dealerName;		//经销商名称        
	private String businessNamager;	//宜信业务负责人    
	private String dealerMobile;	//经销商联系人手机号
	private String dealerContact;	//经销商联系人      
	private String approverRemark;	//客户审批备注      
	private String accepter;		//受理人员          
	private String manager;			//分部经理          
	private String markerter;		//此笔借款营销人员  
	private String sourceChannel;	//客户来源渠道      
	private String sourceChannel1;	//客户来源渠道1     
	private Date updateTime;		//最近更新时间      
	private Date createTime;		//新增时间          
	private Long creater;			//创建人            
	private Long updaterPeople;		//更新人
	
	private Long directlyDepartmentId;//直属部门ID **（营业部id）
	private Long saleAreaCountryId;	//销售区县ID （区id）   
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getKnowCreditease() {
		return knowCreditease;
	}
	public void setKnowCreditease(String knowCreditease) {
		this.knowCreditease = knowCreditease;
	}
	public String getKnowCreditease2() {
		return knowCreditease2;
	}
	public void setKnowCreditease2(String knowCreditease2) {
		this.knowCreditease2 = knowCreditease2;
	}
	public String getSaleChannel() {
		return saleChannel;
	}
	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	public String getSaleChannelRemark() {
		return saleChannelRemark;
	}
	public void setSaleChannelRemark(String saleChannelRemark) {
		this.saleChannelRemark = saleChannelRemark;
	}
	public String getCustomSource() {
		return customSource;
	}
	public void setCustomSource(String customSource) {
		this.customSource = customSource;
	}
	public Long getSaleCityId() {
		return saleCityId;
	}
	public void setSaleCityId(Long saleCityId) {
		this.saleCityId = saleCityId;
	}
	public String getSaleCity() {
		return saleCity;
	}
	public void setSaleCity(String saleCity) {
		this.saleCity = saleCity;
	}
	public Long getSaleDepartmentId() {
		return saleDepartmentId;
	}
	public void setSaleDepartmentId(Long saleDepartmentId) {
		this.saleDepartmentId = saleDepartmentId;
	}
	public String getSaleDepartment() {
		return saleDepartment;
	}
	public void setSaleDepartment(String saleDepartment) {
		this.saleDepartment = saleDepartment;
	}
	public Long getSaleAreaId() {
		return saleAreaId;
	}
	public void setSaleAreaId(Long saleAreaId) {
		this.saleAreaId = saleAreaId;
	}
	public String getSaleArea() {
		return saleArea;
	}
	public void setSaleArea(String saleArea) {
		this.saleArea = saleArea;
	}
	public String getSubmitDeptNo() {
		return submitDeptNo;
	}
	public void setSubmitDeptNo(String submitDeptNo) {
		this.submitDeptNo = submitDeptNo;
	}
	public Long getConsultId() {
		return consultId;
	}
	public void setConsultId(Long consultId) {
		this.consultId = consultId;
	}
	public Long getTeamManagerId() {
		return teamManagerId;
	}
	public void setTeamManagerId(Long teamManagerId) {
		this.teamManagerId = teamManagerId;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	public String getSalePhone() {
		return salePhone;
	}
	public void setSalePhone(String salePhone) {
		this.salePhone = salePhone;
	}
	public Long getSellUserId() {
		return sellUserId;
	}
	public void setSellUserId(Long sellUserId) {
		this.sellUserId = sellUserId;
	}
	public String getSellUserName() {
		return sellUserName;
	}
	public void setSellUserName(String sellUserName) {
		this.sellUserName = sellUserName;
	}
	public Long getServiceUserId() {
		return serviceUserId;
	}
	public void setServiceUserId(Long serviceUserId) {
		this.serviceUserId = serviceUserId;
	}
	public String getServiceUserName() {
		return serviceUserName;
	}
	public void setServiceUserName(String serviceUserName) {
		this.serviceUserName = serviceUserName;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getBusinessNamager() {
		return businessNamager;
	}
	public void setBusinessNamager(String businessNamager) {
		this.businessNamager = businessNamager;
	}
	public String getDealerMobile() {
		return dealerMobile;
	}
	public void setDealerMobile(String dealerMobile) {
		this.dealerMobile = dealerMobile;
	}
	public String getDealerContact() {
		return dealerContact;
	}
	public void setDealerContact(String dealerContact) {
		this.dealerContact = dealerContact;
	}
	public String getApproverRemark() {
		return approverRemark;
	}
	public void setApproverRemark(String approverRemark) {
		this.approverRemark = approverRemark;
	}
	public String getAccepter() {
		return accepter;
	}
	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getMarkerter() {
		return markerter;
	}
	public void setMarkerter(String markerter) {
		this.markerter = markerter;
	}
	public String getSourceChannel() {
		return sourceChannel;
	}
	public void setSourceChannel(String sourceChannel) {
		this.sourceChannel = sourceChannel;
	}
	public String getSourceChannel1() {
		return sourceChannel1;
	}
	public void setSourceChannel1(String sourceChannel1) {
		this.sourceChannel1 = sourceChannel1;
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
	public Long getDirectlyDepartmentId() {
		return directlyDepartmentId;
	}
	public void setDirectlyDepartmentId(Long directlyDepartmentId) {
		this.directlyDepartmentId = directlyDepartmentId;
	}
	public Long getSaleAreaCountryId() {
		return saleAreaCountryId;
	}
	public void setSaleAreaCountryId(Long saleAreaCountryId) {
		this.saleAreaCountryId = saleAreaCountryId;
	}
}

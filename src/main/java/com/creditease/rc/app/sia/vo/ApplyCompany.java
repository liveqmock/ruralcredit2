package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 助业产品和宜楼产品需提供的材料
 */
@SuppressWarnings("serial")
public class ApplyCompany extends Entity {
	private Integer cid;//公司信息id	
	
	private Integer applyId;//贷款信息id	
	
	private Integer clientId;//借款人id
	
	//新增字段
	 private String employeesNums;//新申请表使用，企业员工人数 
	 
	 private String shareProportion1;//新申请表使用，客户身份为其他时增加详情
	 
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	//助业产品需提供的资料
	private Integer	customerIdentity;//客户身份
	
	private BigDecimal shareProportion;//占股比例
	
	private Date interpriceRegisterDate;//企业注册时间
	
	private BigDecimal interpriceRegisterCapital;//企业注册资本
	
	private Integer	companyNature;//公司性质
	
	private String companyNatureDesc;//公司性质其他项详情
	
	public Integer getCustomerIdentity() {
		return customerIdentity;
	}

	public void setCustomerIdentity(Integer customerIdentity) {
		this.customerIdentity = customerIdentity;
	}

	public BigDecimal getShareProportion() {
		return shareProportion;
	}

	public void setShareProportion(BigDecimal shareProportion) {
		this.shareProportion = shareProportion;
	}

	public Date getInterpriceRegisterDate() {
		return interpriceRegisterDate;
	}

	public void setInterpriceRegisterDate(Date interpriceRegisterDate) {
		this.interpriceRegisterDate = interpriceRegisterDate;
	}

	public BigDecimal getInterpriceRegisterCapital() {
		return interpriceRegisterCapital;
	}

	public void setInterpriceRegisterCapital(BigDecimal interpriceRegisterCapital) {
		this.interpriceRegisterCapital = interpriceRegisterCapital;
	}

	public Integer getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(Integer companyNature) {
		this.companyNature = companyNature;
	}



	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}



	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCompanyNatureDesc() {
		return companyNatureDesc;
	}

	public void setCompanyNatureDesc(String companyNatureDesc) {
		this.companyNatureDesc = companyNatureDesc;
	}

	public String getEmployeesNums() {
		return employeesNums;
	}

	public void setEmployeesNums(String employeesNums) {
		this.employeesNums = employeesNums;
	}

	public String getShareProportion1() {
		return shareProportion1;
	}

	public void setShareProportion1(String shareProportion1) {
		this.shareProportion1 = shareProportion1;
	}



	
}

/**
 * Title:OperateLogVO.java  
 * Description:  
 */
package com.creditease.rc.vo;

import java.util.Date;

/**
 * Title:OperateLogVO.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-12 
 */
public class OperateLogVO extends OperateLogBusinessStruct {

	/**
	 * @Description 默认构造器 
	 */
	public OperateLogVO() {
		// TODO Auto-generated constructor stub
	}
	//客户经理姓名
	private String loanOfficerName;
	//分公司名称
	private String companyName;
	//起始操作时间
	private String startDate;
	//终止操作时间
	private String endDate;
	//业务模块标志位
	private String functionCode;
	//模糊匹配客户经理
	private String fuzzyLoanOfficerName;
	//模糊匹配借款人姓名
	private String fuzzyBorrowerName;
	//模糊匹配业务单号
	private String fuzzyBusinessNumber;
	//模糊匹配操作人
	private String fuzzyOperatorName;
	public String getLoanOfficerName() {
		return loanOfficerName;
	}
	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFuzzyLoanOfficerName() {
		return fuzzyLoanOfficerName;
	}
	public void setFuzzyLoanOfficerName(String fuzzyLoanOfficerName) {
		this.fuzzyLoanOfficerName = fuzzyLoanOfficerName;
	}
	public String getFuzzyBorrowerName() {
		return fuzzyBorrowerName;
	}
	public void setFuzzyBorrowerName(String fuzzyBorrowerName) {
		this.fuzzyBorrowerName = fuzzyBorrowerName;
	}
	public String getFuzzyBusinessNumber() {
		return fuzzyBusinessNumber;
	}
	public void setFuzzyBusinessNumber(String fuzzyBusinessNumber) {
		this.fuzzyBusinessNumber = fuzzyBusinessNumber;
	}
	public String getFuzzyOperatorName() {
		return fuzzyOperatorName;
	}
	public void setFuzzyOperatorName(String fuzzyOperatorName) {
		this.fuzzyOperatorName = fuzzyOperatorName;
	}

	
}

/**
 * Title:ProtocolVO.java  
 * Description:  
 */
package com.creditease.rc.vo;

/**
 * Title:ProtocolVO.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
public class ProtocolVO {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolVO() {
		// TODO Auto-generated constructor stub
	}
	//分公司名称
	private String branchofficeName;
	//分公司id
	private String branchofficeId;
	//协议编号
	private String protocolNumber ;
	//年度简码
	private String simpleYear ;
	//业务单号
	private String  businessNumber;
    //产品类型
    private String productTypeName;
	public String getBranchofficeName() {
		return branchofficeName;
	}
	public void setBranchofficeName(String branchofficeName) {
		this.branchofficeName = branchofficeName;
	}
	public String getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	public String getSimpleYear() {
		return simpleYear;
	}
	public void setSimpleYear(String simpleYear) {
		this.simpleYear = simpleYear;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getBranchofficeId() {
		return branchofficeId;
	}
	public void setBranchofficeId(String branchofficeId) {
		this.branchofficeId = branchofficeId;
	}

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}

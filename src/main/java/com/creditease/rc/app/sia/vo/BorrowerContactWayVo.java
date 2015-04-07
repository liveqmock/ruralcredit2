package com.creditease.rc.app.sia.vo;

/**
 * 
 * @author hanjf
 * 借款人联系方式表
 */
public class BorrowerContactWayVo extends Entity{
	private static final long serialVersionUID = 3514471981307624706L;
	
	private Long borrowerInfoId;	//借款人ID
	private Long contactWayId;	//联系方式ID
	private String contactWay;		//1.手机号码 2.邮箱地址 3 .固定电话  4.院系/单位电话 5. 联系人联系电话 6.QQ/MSN  7.微信号
	private String contactValue;	//联系方式值
	private String phoneArea;		//当联系方式为固定电话时，存放区号
	
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getContactWayId() {
		return contactWayId;
	}
	public void setContactWayId(Long contactWayId) {
		this.contactWayId = contactWayId;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getContactValue() {
		return contactValue;
	}
	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}
	public String getPhoneArea() {
		return phoneArea;
	}
	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}
	
}

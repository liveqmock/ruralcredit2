package com.creditease.rc.vo;

import java.io.Serializable;

import com.creditease.rc.domain.BorrowerServiceApp;

/**
 * 借款人申请信息 标记类
 * @author Administrator
 *
 */
public class BorrowerInfoVo extends BorrowerServiceApp implements Serializable {
	private String contactFlag;   //联系人数据是否添加
	
	//紧急联系人
	private String emergencyContact;
	//紧急联系人电话
	private String emergencyContactTel;
	
 
	public String getContactFlag() {
		return contactFlag;
	}
	public void setContactFlag(String contactFlag) {
		this.contactFlag = contactFlag;
	}
	 
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyContactTel() {
		return emergencyContactTel;
	}
	public void setEmergencyContactTel(String emergencyContactTel) {
		this.emergencyContactTel = emergencyContactTel;
	}
}

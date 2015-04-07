package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author zhangman
 *
 */
public class Contact implements Serializable {
	private Integer  contactId;
	private Integer borrowerServiceAppId;
	private String contactType;
	private String name;
	private String telphone;
	private String borrowerreRationship;
	private String  workUnit;
	private String addressfamilyOrWorkunit;
	private String  post;


	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressfamilyOrWorkunit() {
		return addressfamilyOrWorkunit;
	}
	public void setAddressfamilyOrWorkunit(String addressfamilyOrWorkunit) {
		this.addressfamilyOrWorkunit = addressfamilyOrWorkunit;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public Integer getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}
	public void setBorrowerServiceAppId(Integer borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getBorrowerreRationship() {
		return borrowerreRationship;
	}
	public void setBorrowerreRationship(String borrowerreRationship) {
		this.borrowerreRationship = borrowerreRationship;
	}

}

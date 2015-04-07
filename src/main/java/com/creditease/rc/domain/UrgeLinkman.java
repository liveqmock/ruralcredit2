package com.creditease.rc.domain;

public class UrgeLinkman {

	private Long urgeLinkmanId; // 催收联系人姓名

	private Long urgeId; // 催收表外键

	private String urgeLinkName; // 催收联系人姓名

	private String borrowerRelation; // 与借款人关系

	private String linkTelephone; // 联系人电话

	public Long getUrgeLinkmanId() {
		return urgeLinkmanId;
	}

	public void setUrgeLinkmanId(Long urgeLinkmanId) {
		this.urgeLinkmanId = urgeLinkmanId;
	}

	public Long getUrgeId() {
		return urgeId;
	}

	public void setUrgeId(Long urgeId) {
		this.urgeId = urgeId;
	}

	public String getUrgeLinkName() {
		return urgeLinkName;
	}

	public void setUrgeLinkName(String urgeLinkName) {
		this.urgeLinkName = urgeLinkName;
	}

	public String getBorrowerRelation() {
		return borrowerRelation;
	}

	public void setBorrowerRelation(String borrowerRelation) {
		this.borrowerRelation = borrowerRelation;
	}

	public String getLinkTelephone() {
		return linkTelephone;
	}

	public void setLinkTelephone(String linkTelephone) {
		this.linkTelephone = linkTelephone;
	}

}
package com.creditease.rc.domain;

import java.util.Date;

public class ComplaintProposalRequestParam {

	private Long   proposalId;
	private String complaintProposal;
	private String phoneNumber;
	private Date creatDate;

	private String proposer; //建议人姓名（应研发六部需要添加）
	
	public Long getProposalId() {
		return proposalId;
	}
	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
	public String getComplaintProposal() {
		return complaintProposal;
	}
	public void setComplaintProposal(String complaintProposal) {
		this.complaintProposal = complaintProposal;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
}

package com.creditease.rc.domain;

/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 */
public class Deposit {
    private Long depositid;

    private Long borrowerServiceAppId;

    private String depositOrganization;
    
    private String depositOrganizationView;

    private String depositOrganizationDetail;

    private String address;

    private String telephone;

    private Double balance;

    private String proveDocument;
    private String seq;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
    public Long getDepositid() {
        return depositid;
    }

    public void setDepositid(Long depositid) {
        this.depositid = depositid;
    }
    public Long getBorrowerServiceAppId() {
        return borrowerServiceAppId;
    }

    public void setBorrowerServiceAppId(Long borrowerServiceAppId) {
        this.borrowerServiceAppId = borrowerServiceAppId;
    }

    public String getDepositOrganization() {
        return depositOrganization;
    }

    public void setDepositOrganization(String depositOrganization) {
        this.depositOrganization = depositOrganization == null ? null : depositOrganization.trim();
    }

    public String getDepositOrganizationDetail() {
        return depositOrganizationDetail;
    }

    public void setDepositOrganizationDetail(String depositOrganizationDetail) {
        this.depositOrganizationDetail = depositOrganizationDetail == null ? null : depositOrganizationDetail.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getProveDocument() {
        return proveDocument;
    }
    public void setProveDocument(String proveDocument) {
        this.proveDocument = proveDocument == null ? null : proveDocument.trim();
    }

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getDepositOrganizationView() {
		return depositOrganizationView;
	}

	public void setDepositOrganizationView(String depositOrganizationView) {
		this.depositOrganizationView = depositOrganizationView;
	}
}
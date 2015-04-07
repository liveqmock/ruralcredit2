package com.creditease.rc.domain;

public class Application {
    private Long applicationid;

    private Long borrowerServiceAppId;

    private String borrowUse;
    
    private String borrowUseView;

    private String borrowUseDetail;

    private Long quantity;

    private Double unitPrice;

    private Double totalAmount;

    private String proveDocument;
    private String seq;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
    public Long getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Long applicationid) {
        this.applicationid = applicationid;
    }

    public Long getBorrowerServiceAppId() {
        return borrowerServiceAppId;
    }

    public void setBorrowerServiceAppId(Long borrowerServiceAppId) {
        this.borrowerServiceAppId = borrowerServiceAppId;
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse == null ? null : borrowUse.trim();
    }

    public String getBorrowUseDetail() {
        return borrowUseDetail;
    }

    public void setBorrowUseDetail(String borrowUseDetail) {
        this.borrowUseDetail = borrowUseDetail == null ? null : borrowUseDetail.trim();
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public String getProveDocument() {
        return proveDocument;
    }

    public void setProveDocument(String proveDocument) {
        this.proveDocument = proveDocument == null ? null : proveDocument.trim();
    }

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBorrowUseView() {
		return borrowUseView;
	}

	public void setBorrowUseView(String borrowUseView) {
		this.borrowUseView = borrowUseView;
	}
}
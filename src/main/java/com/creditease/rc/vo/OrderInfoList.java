package com.creditease.rc.vo;

public class OrderInfoList {

	private String businessNumber;// 业务单号
	private String uuid; // uuid
	private Integer loanOfficerId;// 信贷员ID
	private String loanOfficerName;// 信贷员姓名
	private String clientIDNumber;// 客户身份证号
	private String productcategoryId;// 产品分类编号
	private String productInfoId;// 产品信息编号
	private Integer salesDepartmentId;// 营业部Id
	private String salesDepartmentName;// 营业部名称（本地和smp可能不同）
    private String cityId;     //城市id
    private String cityName;   //城市名称
    private String cashierTime;//放款时间

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getLoanOfficerId() {
		return loanOfficerId;
	}

	public void setLoanOfficerId(Integer loanOfficerId) {
		this.loanOfficerId = loanOfficerId;
	}

	public String getLoanOfficerName() {
		return loanOfficerName;
	}

	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}

	public String getClientIDNumber() {
		return clientIDNumber;
	}

	public void setClientIDNumber(String clientIDNumber) {
		this.clientIDNumber = clientIDNumber;
	}

	public String getProductcategoryId() {
		return productcategoryId;
	}

	public void setProductcategoryId(String productcategoryId) {
		this.productcategoryId = productcategoryId;
	}

	public String getProductInfoId() {
		return productInfoId;
	}

	public void setProductInfoId(String productInfoId) {
		this.productInfoId = productInfoId;
	}

	public Integer getSalesDepartmentId() {
		return salesDepartmentId;
	}

	public void setSalesDepartmentId(Integer salesDepartmentId) {
		this.salesDepartmentId = salesDepartmentId;
	}

	public String getSalesDepartmentName() {
		return salesDepartmentName;
	}

	public void setSalesDepartmentName(String salesDepartmentName) {
		this.salesDepartmentName = salesDepartmentName;
	}

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCashierTime() {
        return cashierTime;
    }

    public void setCashierTime(String cashierTime) {
        this.cashierTime = cashierTime;
    }
}

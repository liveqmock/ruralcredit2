package com.creditease.rc.domain;

/**
 * 
 * @author haoqiang
 * 
 */
public class LocalReserveListDTO {

	/**
	 * reserveResult状态值
	 * 1=>''付款请求成功'',
	 * 2=>''付款请求失败'',
	 * 3=>''收款请求成功'',
	 * 4=>''收款请求失败'',
	 * 5=>''撤销请求成功'',
	 * 6=>''撤销请求失败'',
	 * 9=>''收款失败'',
	 * 10=>''收款成功'',
	 * 11=>''等待收款'',
	 * 12=>''收款中...''
	 * 14=>''渠道已受理'',
	 * 18=>''等待撤销'',
	 * 19=>''已撤销'',
	 * 24=>''付款失败'',
	 * 25=>''付款成功'',
	 * 26=>''等待付款'',
	 * 27=>''付款中...''
	 */
	private String applyId;// 申请编号
	private String reserveId;// 预约编号
	private String bizid;// 结算订单号
	private String clientName;// 客户姓名
	private String returnName;// 还款人姓名
	private String idNumber;// 身份证号
	private String productId;// 产品编号
	private String officeId;// 分公司编号
	private String applyAmount;// 借款金额
	private String amortisation;// 分期数
	private String loanAmount;// 放款金额
	private String startTime;// 第一个还款日
	private String returnDate;// 所属还款月份
	private String dAmount;// 本期应还款
	private String keapAmount;// 本期已还款
	private String keapDate;// 还款时间
	private String returnType;// 还款方式
	private String reserveResult;// 状态
	private String reserveInfo;// 状态描述

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getReturnName() {
		return returnName;
	}

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getAmortisation() {
		return amortisation;
	}

	public void setAmortisation(String amortisation) {
		this.amortisation = amortisation;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getdAmount() {
		return dAmount;
	}

	public void setdAmount(String dAmount) {
		this.dAmount = dAmount;
	}

	public String getKeapAmount() {
		return keapAmount;
	}

	public void setKeapAmount(String keapAmount) {
		this.keapAmount = keapAmount;
	}

	public String getKeapDate() {
		return keapDate;
	}

	public void setKeapDate(String keapDate) {
		this.keapDate = keapDate;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReserveResult() {
		return reserveResult;
	}

	public void setReserveResult(String reserveResult) {
		this.reserveResult = reserveResult;
	}

	public String getReserveInfo() {
		return reserveInfo;
	}

	public void setReserveInfo(String reserveInfo) {
		this.reserveInfo = reserveInfo;
	}

}

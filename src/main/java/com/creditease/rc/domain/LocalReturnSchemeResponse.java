package com.creditease.rc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;

/**
 * 
 * @author haoqiang
 * 
 */
public class LocalReturnSchemeResponse implements Serializable {

	private Integer period; // 分期数
	private String isOverdue; // 是否逾期
	private String isReturned;// 是否还清
	private Date repayDate; // 还款日期

	private BigDecimal receivableMoney; // 应收总金额
	private BigDecimal receivablePrincipal; // 应收本金
	private BigDecimal receivableInterest; // 应收利息

	private BigDecimal receivedMoney; // 实收总金额
	private BigDecimal receivedPrincipal; // 实收本金
	private BigDecimal receivedInterest;// 实收利息

	private BigDecimal fQFWFReceivableCharge; // 应收分期服务费
	private BigDecimal fQFWFReceivedCharge; // 实收分期服务费
	private BigDecimal fXReceivableCharge; // 应收罚息
	private BigDecimal fXReceivedCharge; // 实收罚息
	private BigDecimal zNJReceivableCharge; // 应收滞纳金
	private BigDecimal zNJReceivedCharge; // 实收滞纳金

	/** 以下为新增的字段 **/

	private BigDecimal receivedlft; // 当月应收滞纳金罚息合计
	private BigDecimal receivablelft; // 当月实收滞纳金罚息合计
	private BigDecimal receivableMarrearage; // 当月欠款
	private BigDecimal receivableArrearage; // 截至当月累计欠款
	private String overDays; // 逾期天数

	private Date returnDate; // 减免月份
	private BigDecimal dForfeit; // 减免罚息
	private BigDecimal dLatefee; // 减免滞纳金
	private Date fDate; // 罚息截止时间
	private Date lDate; // 滞纳金截止时间
	private String dReason; // 减免原因
	private Date nDate; // 减免时间

    public String getCanUseReturnButton() {
        return canUseReturnButton;
    }

    public void setCanUseReturnButton(String canUseReturnButton) {
        this.canUseReturnButton = canUseReturnButton;
    }

    private String canUseReturnButton;
	private String needRemindReceived;//是否还款已完成 需要提醒

	public String getNeedRemindReceived() {
		return needRemindReceived;
	}

	public void setNeedRemindReceived(String needRemindReceived) {
		this.needRemindReceived = needRemindReceived;
	}

	public Integer getPeriod() {
		return period;
	}

	public String getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(String isReturned) {
		this.isReturned = isReturned;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public BigDecimal getReceivableMoney() {
		return receivableMoney;
	}

	public void setReceivableMoney(BigDecimal receivableMoney) {
		this.receivableMoney = receivableMoney;
	}

	public BigDecimal getReceivablePrincipal() {
		return receivablePrincipal;
	}

	public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
		this.receivablePrincipal = receivablePrincipal;
	}

	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}

	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}

	public BigDecimal getReceivedMoney() {
		return receivedMoney;
	}

	public void setReceivedMoney(BigDecimal receivedMoney) {
		this.receivedMoney = receivedMoney;
	}

	public BigDecimal getReceivedPrincipal() {
		return receivedPrincipal;
	}

	public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
		this.receivedPrincipal = receivedPrincipal;
	}

	public BigDecimal getReceivedInterest() {
		return receivedInterest;
	}

	public void setReceivedInterest(BigDecimal receivedInterest) {
		this.receivedInterest = receivedInterest;
	}

	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getfQFWFReceivableCharge() {
		return fQFWFReceivableCharge;
	}

	/**
	 * 
	 * @param fQFWFReceivableCharge fQFWFReceivableCharge
	 */
	public void setfQFWFReceivableCharge(BigDecimal fQFWFReceivableCharge) {
		this.fQFWFReceivableCharge = fQFWFReceivableCharge;
	}

	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getfQFWFReceivedCharge() {
		return fQFWFReceivedCharge;
	}

	/**
	 * 
	 * @param fQFWFReceivedCharge fQFWFReceivedCharge
	 */
	public void setfQFWFReceivedCharge(BigDecimal fQFWFReceivedCharge) {
		this.fQFWFReceivedCharge = fQFWFReceivedCharge;
	}

	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getfXReceivableCharge() {
		return fXReceivableCharge;
	}

	/**
	 * 
	 * @param fXReceivableCharge fXReceivableCharge
	 */
	public void setfXReceivableCharge(BigDecimal fXReceivableCharge) {
		this.fXReceivableCharge = fXReceivableCharge;
	}

	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getfXReceivedCharge() {
		return fXReceivedCharge;
	}

	/**
	 * 
	 * @param fXReceivedCharge fXReceivedCharge
	 */
	public void setfXReceivedCharge(BigDecimal fXReceivedCharge) {
		this.fXReceivedCharge = fXReceivedCharge;
	}

	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getzNJReceivableCharge() {
		return zNJReceivableCharge;
	}

	/**
	 * 
	 * @param zNJReceivableCharge zNJReceivableCharge
	 */
	public void setzNJReceivableCharge(BigDecimal zNJReceivableCharge) {
		this.zNJReceivableCharge = zNJReceivableCharge;
	}

	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getzNJReceivedCharge() {
		return zNJReceivedCharge;
	}

	/**
	 * 
	 * @param zNJReceivedCharge zNJReceivedCharge
	 */
	public void setzNJReceivedCharge(BigDecimal zNJReceivedCharge) {
		this.zNJReceivedCharge = zNJReceivedCharge;
	}

	public BigDecimal getReceivedlft() {
		return receivedlft;
	}

	public void setReceivedlft(BigDecimal receivedlft) {
		this.receivedlft = receivedlft;
	}

	public BigDecimal getReceivablelft() {
		return receivablelft;
	}

	public void setReceivablelft(BigDecimal receivablelft) {
		this.receivablelft = receivablelft;
	}

	public BigDecimal getReceivableMarrearage() {
		return receivableMarrearage;
	}

	public void setReceivableMarrearage(BigDecimal receivableMarrearage) {
		this.receivableMarrearage = receivableMarrearage;
	}

	public BigDecimal getReceivableArrearage() {
		return receivableArrearage;
	}

	public void setReceivableArrearage(BigDecimal receivableArrearage) {
		this.receivableArrearage = receivableArrearage;
	}

	public String getOverDays() {
		return overDays;
	}

	public void setOverDays(String overDays) {
		this.overDays = overDays;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getdForfeit() {
		return dForfeit;
	}

	public void setdForfeit(BigDecimal dForfeit) {
		this.dForfeit = dForfeit;
	}

	public BigDecimal getdLatefee() {
		return dLatefee;
	}

	public void setdLatefee(BigDecimal dLatefee) {
		this.dLatefee = dLatefee;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}

	public String getdReason() {
		return dReason;
	}

	public void setdReason(String dReason) {
		this.dReason = dReason;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

}

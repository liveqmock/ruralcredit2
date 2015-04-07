package com.creditease.rc.domain;

import java.math.BigDecimal;
/**
 * 
 * @author haoqiang
 *
 */
public class OtherQuestions {

	private Long otherQuestionsId; // 其他信息主键

	private Long borrowerServiceAppId;// 借款担保人信息主键

	private String guarantee;// 以前是否为别人做过担保？ 0-是，1-否

	private String guaranteeDetails;// 担保详细信息

	private String howLong;// 你认识（申请人姓名）多久了？ 0-小于1年，1-1~3年，2-大于3年

	private String gambling;// （申请人夫妇姓名）是否是有长期赌博的习惯呢？ 1-有，2-无，3-没回答

	private String gamblingSpouse;// 赌博详细信息

	private String help;// （申请人夫妇姓名）以前有没有向你家请求过帮助？ 1-有，2-无

	private String helpDetails;// 帮助详细信息

	private BigDecimal borrowAmount;// 这次你担保的借款是多少额度？

	private BigDecimal guaranteeAmount;// 如果（申请人姓名）不能还款，你愿意替他/她还款多少？

	private String enoughDeposits;// 你有这么多存款替他/她还款吗？ 1-是，2-否

	private String howEnough;// 如果否，那么你计划如何还款呢？请详细说明。

	private String spouseInformed;// 你的配偶是否知道并同意你担保这笔借款呢？ 1-是，2-否

	private String spouseExplanation;// 如果否，请解释。

	public Long getOtherQuestionsId() {
		return otherQuestionsId;
	}

	public void setOtherQuestionsId(Long otherQuestionsId) {
		this.otherQuestionsId = otherQuestionsId;
	}

	public Long getBorrowerServiceAppId() {
		return borrowerServiceAppId;
	}

	public void setBorrowerServiceAppId(Long borrowerServiceAppId) {
		this.borrowerServiceAppId = borrowerServiceAppId;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee == null ? null : guarantee.trim();
	}

	public String getGuaranteeDetails() {
		return guaranteeDetails;
	}

	public void setGuaranteeDetails(String guaranteeDetails) {
		this.guaranteeDetails = guaranteeDetails == null ? null : guaranteeDetails.trim();
	}

	public String getHowLong() {
		return howLong;
	}

	public void setHowLong(String howLong) {
		this.howLong = howLong == null ? null : howLong.trim();
	}

	public String getGambling() {
		return gambling;
	}

	public void setGambling(String gambling) {
		this.gambling = gambling == null ? null : gambling.trim();
	}

	public String getGamblingSpouse() {
		return gamblingSpouse;
	}

	public void setGamblingSpouse(String gamblingSpouse) {
		this.gamblingSpouse = gamblingSpouse == null ? null : gamblingSpouse.trim();
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help == null ? null : help.trim();
	}

	public String getHelpDetails() {
		return helpDetails;
	}

	public void setHelpDetails(String helpDetails) {
		this.helpDetails = helpDetails == null ? null : helpDetails.trim();
	}

	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public BigDecimal getGuaranteeAmount() {
		return guaranteeAmount;
	}

	public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}

	public String getEnoughDeposits() {
		return enoughDeposits;
	}

	public void setEnoughDeposits(String enoughDeposits) {
		this.enoughDeposits = enoughDeposits == null ? null : enoughDeposits.trim();
	}

	public String getHowEnough() {
		return howEnough;
	}

	public void setHowEnough(String howEnough) {
		this.howEnough = howEnough == null ? null : howEnough.trim();
	}

	public String getSpouseInformed() {
		return spouseInformed;
	}

	public void setSpouseInformed(String spouseInformed) {
		this.spouseInformed = spouseInformed == null ? null : spouseInformed.trim();
	}

	public String getSpouseExplanation() {
		return spouseExplanation;
	}

	public void setSpouseExplanation(String spouseExplanation) {
		this.spouseExplanation = spouseExplanation == null ? null : spouseExplanation.trim();
	}
}

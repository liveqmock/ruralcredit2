package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class BorrowerCar extends BorrowerCarKey {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CAR_MODEL
	 * @abatorgenerated
	 */
	private String carModel;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CAR_BRAND
	 * @abatorgenerated
	 */
	private String carBrand;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CAR_SERIES
	 * @abatorgenerated
	 */
	private String carSeries;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.APPLY_DATE
	 * @abatorgenerated
	 */
	private Date applyDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.START_BOARD_DATE
	 * @abatorgenerated
	 */
	private Date startBoardDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CAR_AGE
	 * @abatorgenerated
	 */
	private Short carAge;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.AMOUNT
	 * @abatorgenerated
	 */
	private BigDecimal amount;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.FRAME_NO
	 * @abatorgenerated
	 */
	private String frameNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.ENGINER_NO
	 * @abatorgenerated
	 */
	private String enginerNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.FIRST_PAYMENT_AMOUNT
	 * @abatorgenerated
	 */
	private BigDecimal firstPaymentAmount;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.INSURE_FEE
	 * @abatorgenerated
	 */
	private BigDecimal insureFee;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CAR_VALUE
	 * @abatorgenerated
	 */
	private BigDecimal carValue;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.LICENSE_FEE
	 * @abatorgenerated
	 */
	private BigDecimal licenseFee;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.TOTAL_AMOUNT
	 * @abatorgenerated
	 */
	private BigDecimal totalAmount;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CAR_NATURE
	 * @abatorgenerated
	 */
	private String carNature;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.ADDRESS
	 * @abatorgenerated
	 */
	private String address;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CONTACT
	 * @abatorgenerated
	 */
	private String contact;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.VENDER
	 * @abatorgenerated
	 */
	private String vender;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CREATER
	 * @abatorgenerated
	 */
	private Long creater;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.UPDATER_PEOPLE
	 * @abatorgenerated
	 */
	private Long updaterPeople;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.UPDATE_DATE
	 * @abatorgenerated
	 */
	private Date updateDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column ICP_BORROWER_CAR.CREATE_DATE
	 * @abatorgenerated
	 */
	private Date createDate;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CAR_MODEL
	 * @return  the value of ICP_BORROWER_CAR.CAR_MODEL
	 * @abatorgenerated
	 */
	public String getCarModel() {
		return carModel;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CAR_MODEL
	 * @param carModel  the value for ICP_BORROWER_CAR.CAR_MODEL
	 * @abatorgenerated
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel == null ? null : carModel.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CAR_BRAND
	 * @return  the value of ICP_BORROWER_CAR.CAR_BRAND
	 * @abatorgenerated
	 */
	public String getCarBrand() {
		return carBrand;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CAR_BRAND
	 * @param carBrand  the value for ICP_BORROWER_CAR.CAR_BRAND
	 * @abatorgenerated
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand == null ? null : carBrand.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CAR_SERIES
	 * @return  the value of ICP_BORROWER_CAR.CAR_SERIES
	 * @abatorgenerated
	 */
	public String getCarSeries() {
		return carSeries;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CAR_SERIES
	 * @param carSeries  the value for ICP_BORROWER_CAR.CAR_SERIES
	 * @abatorgenerated
	 */
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries == null ? null : carSeries.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.APPLY_DATE
	 * @return  the value of ICP_BORROWER_CAR.APPLY_DATE
	 * @abatorgenerated
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.APPLY_DATE
	 * @param applyDate  the value for ICP_BORROWER_CAR.APPLY_DATE
	 * @abatorgenerated
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.START_BOARD_DATE
	 * @return  the value of ICP_BORROWER_CAR.START_BOARD_DATE
	 * @abatorgenerated
	 */
	public Date getStartBoardDate() {
		return startBoardDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.START_BOARD_DATE
	 * @param startBoardDate  the value for ICP_BORROWER_CAR.START_BOARD_DATE
	 * @abatorgenerated
	 */
	public void setStartBoardDate(Date startBoardDate) {
		this.startBoardDate = startBoardDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CAR_AGE
	 * @return  the value of ICP_BORROWER_CAR.CAR_AGE
	 * @abatorgenerated
	 */
	public Short getCarAge() {
		return carAge;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CAR_AGE
	 * @param carAge  the value for ICP_BORROWER_CAR.CAR_AGE
	 * @abatorgenerated
	 */
	public void setCarAge(Short carAge) {
		this.carAge = carAge;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.AMOUNT
	 * @return  the value of ICP_BORROWER_CAR.AMOUNT
	 * @abatorgenerated
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.AMOUNT
	 * @param amount  the value for ICP_BORROWER_CAR.AMOUNT
	 * @abatorgenerated
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.FRAME_NO
	 * @return  the value of ICP_BORROWER_CAR.FRAME_NO
	 * @abatorgenerated
	 */
	public String getFrameNo() {
		return frameNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.FRAME_NO
	 * @param frameNo  the value for ICP_BORROWER_CAR.FRAME_NO
	 * @abatorgenerated
	 */
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo == null ? null : frameNo.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.ENGINER_NO
	 * @return  the value of ICP_BORROWER_CAR.ENGINER_NO
	 * @abatorgenerated
	 */
	public String getEnginerNo() {
		return enginerNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.ENGINER_NO
	 * @param enginerNo  the value for ICP_BORROWER_CAR.ENGINER_NO
	 * @abatorgenerated
	 */
	public void setEnginerNo(String enginerNo) {
		this.enginerNo = enginerNo == null ? null : enginerNo.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.FIRST_PAYMENT_AMOUNT
	 * @return  the value of ICP_BORROWER_CAR.FIRST_PAYMENT_AMOUNT
	 * @abatorgenerated
	 */
	public BigDecimal getFirstPaymentAmount() {
		return firstPaymentAmount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.FIRST_PAYMENT_AMOUNT
	 * @param firstPaymentAmount  the value for ICP_BORROWER_CAR.FIRST_PAYMENT_AMOUNT
	 * @abatorgenerated
	 */
	public void setFirstPaymentAmount(BigDecimal firstPaymentAmount) {
		this.firstPaymentAmount = firstPaymentAmount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.INSURE_FEE
	 * @return  the value of ICP_BORROWER_CAR.INSURE_FEE
	 * @abatorgenerated
	 */
	public BigDecimal getInsureFee() {
		return insureFee;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.INSURE_FEE
	 * @param insureFee  the value for ICP_BORROWER_CAR.INSURE_FEE
	 * @abatorgenerated
	 */
	public void setInsureFee(BigDecimal insureFee) {
		this.insureFee = insureFee;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CAR_VALUE
	 * @return  the value of ICP_BORROWER_CAR.CAR_VALUE
	 * @abatorgenerated
	 */
	public BigDecimal getCarValue() {
		return carValue;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CAR_VALUE
	 * @param carValue  the value for ICP_BORROWER_CAR.CAR_VALUE
	 * @abatorgenerated
	 */
	public void setCarValue(BigDecimal carValue) {
		this.carValue = carValue;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.LICENSE_FEE
	 * @return  the value of ICP_BORROWER_CAR.LICENSE_FEE
	 * @abatorgenerated
	 */
	public BigDecimal getLicenseFee() {
		return licenseFee;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.LICENSE_FEE
	 * @param licenseFee  the value for ICP_BORROWER_CAR.LICENSE_FEE
	 * @abatorgenerated
	 */
	public void setLicenseFee(BigDecimal licenseFee) {
		this.licenseFee = licenseFee;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.TOTAL_AMOUNT
	 * @return  the value of ICP_BORROWER_CAR.TOTAL_AMOUNT
	 * @abatorgenerated
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.TOTAL_AMOUNT
	 * @param totalAmount  the value for ICP_BORROWER_CAR.TOTAL_AMOUNT
	 * @abatorgenerated
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CAR_NATURE
	 * @return  the value of ICP_BORROWER_CAR.CAR_NATURE
	 * @abatorgenerated
	 */
	public String getCarNature() {
		return carNature;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CAR_NATURE
	 * @param carNature  the value for ICP_BORROWER_CAR.CAR_NATURE
	 * @abatorgenerated
	 */
	public void setCarNature(String carNature) {
		this.carNature = carNature == null ? null : carNature.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.ADDRESS
	 * @return  the value of ICP_BORROWER_CAR.ADDRESS
	 * @abatorgenerated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.ADDRESS
	 * @param address  the value for ICP_BORROWER_CAR.ADDRESS
	 * @abatorgenerated
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CONTACT
	 * @return  the value of ICP_BORROWER_CAR.CONTACT
	 * @abatorgenerated
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CONTACT
	 * @param contact  the value for ICP_BORROWER_CAR.CONTACT
	 * @abatorgenerated
	 */
	public void setContact(String contact) {
		this.contact = contact == null ? null : contact.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.VENDER
	 * @return  the value of ICP_BORROWER_CAR.VENDER
	 * @abatorgenerated
	 */
	public String getVender() {
		return vender;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.VENDER
	 * @param vender  the value for ICP_BORROWER_CAR.VENDER
	 * @abatorgenerated
	 */
	public void setVender(String vender) {
		this.vender = vender == null ? null : vender.trim();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CREATER
	 * @return  the value of ICP_BORROWER_CAR.CREATER
	 * @abatorgenerated
	 */
	public Long getCreater() {
		return creater;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CREATER
	 * @param creater  the value for ICP_BORROWER_CAR.CREATER
	 * @abatorgenerated
	 */
	public void setCreater(Long creater) {
		this.creater = creater;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.UPDATER_PEOPLE
	 * @return  the value of ICP_BORROWER_CAR.UPDATER_PEOPLE
	 * @abatorgenerated
	 */
	public Long getUpdaterPeople() {
		return updaterPeople;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.UPDATER_PEOPLE
	 * @param updaterPeople  the value for ICP_BORROWER_CAR.UPDATER_PEOPLE
	 * @abatorgenerated
	 */
	public void setUpdaterPeople(Long updaterPeople) {
		this.updaterPeople = updaterPeople;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.UPDATE_DATE
	 * @return  the value of ICP_BORROWER_CAR.UPDATE_DATE
	 * @abatorgenerated
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.UPDATE_DATE
	 * @param updateDate  the value for ICP_BORROWER_CAR.UPDATE_DATE
	 * @abatorgenerated
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column ICP_BORROWER_CAR.CREATE_DATE
	 * @return  the value of ICP_BORROWER_CAR.CREATE_DATE
	 * @abatorgenerated
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column ICP_BORROWER_CAR.CREATE_DATE
	 * @param createDate  the value for ICP_BORROWER_CAR.CREATE_DATE
	 * @abatorgenerated
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
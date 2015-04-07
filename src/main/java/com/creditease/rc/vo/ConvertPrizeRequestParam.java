package com.creditease.rc.vo;

public class ConvertPrizeRequestParam {

	private String 		userCode			; // 用户编码
	private Long 		wxPrizeId			; // 奖品主键ID
	private String 		provincehome		; // 省
	private String 		cityhome			; // 市
	private String 		countyhome			; // 区
	private String 		branchofficeName	; // 营业部名称
	private String 		branchofficeId		; // 营业部ID

	private String recpriName; // 兑奖人姓名
	private String recpriPhone; // 兑奖人联系方式

	private int score;// 扣除的積分
	
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getWxPrizeId() {
		return wxPrizeId;
	}

	public void setWxPrizeId(Long wxPrizeId) {
		this.wxPrizeId = wxPrizeId;
	}

	public String getProvincehome() {
		return provincehome;
	}

	public void setProvincehome(String provincehome) {
		this.provincehome = provincehome;
	}

	public String getCityhome() {
		return cityhome;
	}

	public void setCityhome(String cityhome) {
		this.cityhome = cityhome;
	}

	public String getCountyhome() {
		return countyhome;
	}

	public void setCountyhome(String countyhome) {
		this.countyhome = countyhome;
	}

	public String getBranchofficeName() {
		return branchofficeName;
	}

	public void setBranchofficeName(String branchofficeName) {
		this.branchofficeName = branchofficeName;
	}

	public String getBranchofficeId() {
		return branchofficeId;
	}

	public void setBranchofficeId(String branchofficeId) {
		this.branchofficeId = branchofficeId;
	}

	public String getRecpriName() {
		return recpriName;
	}

	public void setRecpriName(String recpriName) {
		this.recpriName = recpriName;
	}

	public String getRecpriPhone() {
		return recpriPhone;
	}

	public void setRecpriPhone(String recpriPhone) {
		this.recpriPhone = recpriPhone;
	}

}

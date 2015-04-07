package com.creditease.rc.vo;

import java.util.List;

public class QyCanGetPrizeListResponseParam extends WebServiceMessage {

	private Long score; // 当前积分
	private String phone;// 联系电话
	private List<PrizeHasFlag> prizeHasFlags;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public List<PrizeHasFlag> getPrizeHasFlags() {
		return prizeHasFlags;
	}

	public void setPrizeHasFlags(List<PrizeHasFlag> prizeHasFlags) {
		this.prizeHasFlags = prizeHasFlags;
	}

}

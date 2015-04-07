package com.creditease.rc.vo;

import com.creditease.rc.domain.WxupriRecord;

public class WxupriRecordVo extends WxupriRecord {
	private String prizeType;
	private String prizeName;
	private String prizeContent; // 奖品描述
	
	

	public String getPrizeContent() {
		return prizeContent;
	}

	public void setPrizeContent(String prizeContent) {
		this.prizeContent = prizeContent;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}

}

package com.creditease.rc.vo;

import java.util.List;

public class QyConvertPrizeHistoryResponseParam extends WebServiceMessage {
	private List<ConvertPrizeHistory> convertPrizeHistories;

	public List<ConvertPrizeHistory> getConvertPrizeHistories() {
		return convertPrizeHistories;
	}

	public void setConvertPrizeHistories(List<ConvertPrizeHistory> convertPrizeHistories) {
		this.convertPrizeHistories = convertPrizeHistories;
	}

}

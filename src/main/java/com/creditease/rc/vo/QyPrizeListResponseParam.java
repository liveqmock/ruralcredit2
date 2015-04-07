package com.creditease.rc.vo;

import java.util.List;

public class QyPrizeListResponseParam extends WebServiceMessage{

	private List<Prize> prizes;

	public List<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}
	
	
}

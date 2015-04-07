package com.creditease.rc.vo;

import java.util.List;

public class QyHistoryQuestionsWrongResponseParam extends WebServiceMessage {

	private List<QandA> qandAs;

	public List<QandA> getQandAs() {
		return qandAs;
	}

	public void setQandAs(List<QandA> qandAs) {
		this.qandAs = qandAs;
	}

}

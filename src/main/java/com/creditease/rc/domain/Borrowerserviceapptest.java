package com.creditease.rc.domain;

import java.io.Serializable;

/**
 * 
 * @author haoqiang
 * 
 */
public class Borrowerserviceapptest implements Serializable {
	private Integer borrowerServiceAppIdTest;
	private Integer borrowersurveyIdTest;
	private String name;

	public Integer getBorrowerServiceAppIdTest() {
		return borrowerServiceAppIdTest;
	}

	public void setBorrowerServiceAppIdTest(Integer borrowerServiceAppIdTest) {
		this.borrowerServiceAppIdTest = borrowerServiceAppIdTest;
	}

	public Integer getBorrowersurveyIdTest() {
		return borrowersurveyIdTest;
	}

	public void setBorrowersurveyIdTest(Integer borrowersurveyIdTest) {
		this.borrowersurveyIdTest = borrowersurveyIdTest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

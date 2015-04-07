package com.creditease.rc.vo;

import java.io.Serializable;

import com.creditease.rc.domain.Borrowersurvey;

/**
 * 
 * @author haoqiang
 * 
 */
public class BorrowersurveyVo implements Serializable {
	private Borrowersurvey borrowersurvey;

	public Borrowersurvey getBorrowersurvey() {
		return borrowersurvey;
	}

	public void setBorrowersurvey(Borrowersurvey borrowersurvey) {
		this.borrowersurvey = borrowersurvey;
	}

}

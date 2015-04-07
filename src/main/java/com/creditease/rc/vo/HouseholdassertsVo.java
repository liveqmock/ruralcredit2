package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Householdasserts;

/**
 * 
 * @author haoqiang
 * 
 */
public class HouseholdassertsVo implements Serializable {
	private List<Householdasserts> householdassertss;

	public List<Householdasserts> getHouseholdassertss() {
		return householdassertss;
	}

	public void setHouseholdassertss(List<Householdasserts> householdassertss) {
		this.householdassertss = householdassertss;
	}

}

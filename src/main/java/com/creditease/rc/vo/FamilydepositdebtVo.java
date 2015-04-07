package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Familydepositdebt;

/**
 * 
 * @author haoqiang
 * 
 */
public class FamilydepositdebtVo implements Serializable {

	private List<Familydepositdebt> familydepositdebts;

	public List<Familydepositdebt> getFamilydepositdebts() {
		return familydepositdebts;
	}

	public void setFamilydepositdebts(List<Familydepositdebt> familydepositdebts) {
		this.familydepositdebts = familydepositdebts;
	}

}

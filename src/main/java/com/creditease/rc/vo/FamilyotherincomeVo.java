package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Familyotherincome;

/**
 * 
 * @author haoqiang
 * 
 */
public class FamilyotherincomeVo implements Serializable {

	private List<Familyotherincome> familyotherincomes;

	public List<Familyotherincome> getFamilyotherincomes() {
		return familyotherincomes;
	}

	public void setFamilyotherincomes(List<Familyotherincome> familyotherincomes) {
		this.familyotherincomes = familyotherincomes;
	}

}

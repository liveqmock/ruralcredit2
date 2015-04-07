package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Familymember;

/**
 * 
 * @author haoqiang
 * 
 */
public class FamilymemberVo implements Serializable {

	private List<Familymember> familymembers;

	public List<Familymember> getFamilymembers() {
		return familymembers;
	}

	public void setFamilymembers(List<Familymember> familymembers) {
		this.familymembers = familymembers;
	}

}

package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Familytotalcost;

/**
 * 
 * @author haoqiang
 * 
 */
public class FamilytotalcostVo implements Serializable {

	private List<Familytotalcost> familytotalcosts;

	public List<Familytotalcost> getFamilytotalcosts() {
		return familytotalcosts;
	}

	public void setFamilytotalcosts(List<Familytotalcost> familytotalcosts) {
		this.familytotalcosts = familytotalcosts;
	}

}
